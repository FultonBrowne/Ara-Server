package com.andromeda.araserver.pages;

import com.andromeda.araserver.util.OutputModel;
import com.andromeda.araserver.util.SqlModel;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class ApiStart {
    //output to be printed if error
    private ArrayList<OutputModel> outputModels = new ArrayList<>();
    //output from DB
    private ArrayList<SqlModel> sqlmodel = new ArrayList<>();
    private String linkval;


    public String apiMain(String mainUri) {
        String searchterm = mainUri.replaceFirst("/api/", "");
        outputModels.add(new OutputModel("Blank Input Received", "Please Try Again", "https://github.com/fultonbrowne/ara-android", "", "Error Was Encountered", ""));


        return ParseApi(searchterm);
    }

    private String ParseApi(String search) {
        //text to be outputted
        String out;
        String term;
        //parse for search term
        ArrayList<String> pairs = new ArrayList<>(Arrays.asList(search.split("&")));
        term = pairs.get(0);
        System.out.println(term);
        //connect to DB
        try {
            //get DB driver
            Class.forName("org.postgresql.Driver");
            //get connection
            Connection c = getConnection();
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM skills;");
            // I dont know how this works but it does 😀
            while (rs.next()) {
                // get values from the data base
                String start = rs.getString("start");
                String endtxt = rs.getString("endtxt");
                String link = rs.getString("link");
                //Print to logs for debugging
                System.out.println("start = " + start);
                System.out.println("endtxt = " + endtxt);
                System.out.println("link = " + link);
                //to avoid a null pointer
                SqlModel temsqlmodel = new SqlModel(start, endtxt, link);
                sqlmodel.add(temsqlmodel);
                System.out.println();
            }


        } catch (Exception e) {
            //print errors
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return new Gson().toJson(outputModels);
        }
        System.out.println("Opened database successfully");
        System.out.println(search);
        //figure out what links to go to
        for (SqlModel sqlModel : sqlmodel) {
            System.out.println(sqlModel.description);
            System.out.println(search.startsWith(sqlModel.description));

            assert term != null;
            if (search.startsWith(sqlModel.description)) {
                linkval = sqlModel.link + "/" + search.replace(sqlModel.description, "");
                break;
            }
            if (!sqlModel.title.equals("") && term.endsWith(sqlModel.title)) {
                linkval = sqlModel.link + "/" + search.replace(sqlModel.title, "");

                break;
            }
        }
        // parse to avoid errors
        if (linkval != null && linkval.contains(" "))
            linkval = linkval.replace(" ", "");
        System.out.println(linkval);
        String url = linkval;
        URL obj;
        String result;
        try {
            //get data from correct link
            obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            Scanner s = new Scanner(in).useDelimiter("\\A");
            result = s.hasNext() ? s.next() : "";
            System.out.println(result + "hi");
            in.close();
            //print result
            out = response.toString();
            System.out.println(out);
        } catch (Exception e) {
            //get the problems
            e.printStackTrace();
            //print error msg
            out = new Gson().toJson(outputModels);
        }
        //return the output
        return out;
    }

    /*public void sqladd(Statement stmt, Connection c) throws SQLException {
        //this is for testing
        String sql = "INSERT INTO SKILLS(start,endtxt,link) VALUES ('calculate','', 'https://araserver.herokuapp.com/math')";
        stmt.executeUpdate(sql);
        c.close();
    }*/

    private static Connection getConnection() throws SQLException {
        //get a SQL connection (Note these creds are for test DB)
        String dbUrl = "jdbc:postgresql://" + "ec2-54-221-214-3.compute-1.amazonaws.com" + ':' + "5432" + "/d40qc3ivndkhlh" + "?ssl=true" + "&sslfactory=org.postgresql.ssl.NonValidatingFactory";
        return DriverManager.getConnection(dbUrl,
                "ztpysbtdtzkeyt", "344eac98ba90d4b8b94166fbdc368bc1b3e887c20bce983cf469b2d1b64293a6");
    }

}
