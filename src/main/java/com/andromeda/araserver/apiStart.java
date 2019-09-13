package com.andromeda.araserver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;

class apiStart {
    private GsonBuilder gsonBuilder = new GsonBuilder();
     private ArrayList<RssFeedModel> test = new ArrayList<>();
     private ArrayList<SqlModel> sqlmodel = new ArrayList<>();
     private String linkval;


    String apiMain(String mainUri){
        String searchterm = mainUri.replaceFirst("/api/", "");
        Gson output = gsonBuilder.create();
        test.add(new RssFeedModel("hi","hi","hi","hi"));
        test.add(new RssFeedModel("hi","hi","hi","hi"));
        sqltest(searchterm);


        return output.toJson(test);
    }
    private void sqltest(String search){
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
        for (int i = 0; i < sqlmodel.size() ; i++) {
            if (search.startsWith(sqlmodel.get(i).title)){
                linkval = sqlmodel.get(i).link;
            }
            else if (search.startsWith(sqlmodel.get(i).description)){
                linkval = sqlmodel.get(i).link;

            }
        }
        if (linkval == null){
            linkval = "error";
        }
        System.out.println(linkval + "/" + search);
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
