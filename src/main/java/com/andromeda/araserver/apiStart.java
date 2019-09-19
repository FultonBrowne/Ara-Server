package com.andromeda.araserver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;



class apiStart {
    private String result;
    private GsonBuilder gsonBuilder = new GsonBuilder();
     private ArrayList<OutputModel> test = new ArrayList<>();
     private ArrayList<SqlModel> sqlmodel = new ArrayList<>();
     private String linkval;


    String apiMain(String mainUri){
        String searchterm = mainUri.replaceFirst("/api/", "");
        Gson output = gsonBuilder.create();
        test.add(new OutputModel("Blank Input Received", "Please Try Again", "https://github.com/fultonbrowne/ara-android","","Error Was Encountered", ""));




        return sqltest(searchterm);
    }
    private String sqltest(String search){
        String out = "hi";
        try {
           Class.forName("org.postgresql.Driver");
          Connection c = getConnection();
            Statement stmt = c.createStatement();
          // sqladd(stmt, c);
            ResultSet rs = stmt.executeQuery( "SELECT * FROM skills;" );
            // I dont know how this works but it does :)
            while ( rs.next() ) {

                String  start = rs.getString("start");
                String  endtxt = rs.getString("endtxt");
                String link = rs.getString("link");
                System.out.println( "start = " + start );

                System.out.println( "endtxt = " + endtxt );
                System.out.println( "link = " + link );
                SqlModel temsqlmodel = new SqlModel(start, endtxt, link);
                sqlmodel.add(temsqlmodel);
                System.out.println();
            }


        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        System.out.println(search);
        System.out.println(search.startsWith(sqlmodel.get(0).title));
        if(!search.equals("")){

            for (SqlModel sqlModel : sqlmodel) {
                if (search.startsWith(sqlModel.title)) {
                    linkval = sqlModel.link;
                    System.out.println("BT");
                } else if (search.endsWith(sqlModel.description)) {

                    linkval = sqlModel.link;
                }
                System.out.println("AT");


            }
        }

        if (linkval == null){
            linkval = "error";
        }
        //System.out.println(linkval + "/" + search);
        String url = linkval;
        //if (!linkval.endsWith("/")) url = url +"/";

        URL obj = null;
        try {
            obj = new URL(url);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        //con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);

        }
            Scanner s = new Scanner(in).useDelimiter("\\A");
        result = s.hasNext() ? s.next() : "";
            System.out.println(result + "hi");
            //out = result;


        in.close();


        //print result
        out = response.toString();
        System.out.println(out);
        } catch (Exception e) {
            e.printStackTrace();
            out = new Gson().toJson(test);
            result = "err";
        }
        return out;
    }
    public void sqladd(Statement stmt, Connection c) throws SQLException {
       // String sql = "CREATE TABLE skills(start varchar(80) ,endtxt varchar(225), link varchar(225)); ";
        //TODO work on this \/
        String sql = "INSERT INTO SKILLS(start,endtxt,link) VALUES ('nearest','near me', 'https://araserver.herokuapp.com/yelpclient')";
        stmt.executeUpdate(sql);
        //String sql = "INSERT INTO SKILLS (ID,NAME,AGE,ADDRESS,SALARY) "
          //      + "VALUES (1, 'Paul', 32, 'California', 20000.00 );";

        //stmt.executeUpdate(sql);
        //c.commit();
        c.close();
    }
    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI("jdbc:postgresql://ztpysbtdtzkeyt:344eac98ba90d4b8b94166fbdc368bc1b3e887c20bce983cf469b2d1b64293a6@ec2-54-221-214-3.compute-1.amazonaws.com:5432/d40qc3ivndkhlh");
        String dbUrl = "jdbc:postgresql://" + "ec2-54-221-214-3.compute-1.amazonaws.com" + ':' + "5432" + "/d40qc3ivndkhlh" + "?ssl=true" + "&sslfactory=org.postgresql.ssl.NonValidatingFactory" ;
        return DriverManager.getConnection(dbUrl,
                             "ztpysbtdtzkeyt", "344eac98ba90d4b8b94166fbdc368bc1b3e887c20bce983cf469b2d1b64293a6");
    }

}
