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

    String apiMain(String mainUri){
        String searchterm = mainUri.replaceFirst("/api/", "");
        Gson output = gsonBuilder.create();
        test.add(new RssFeedModel("hi","hi","hi","hi"));
        test.add(new RssFeedModel("hi","hi","hi","hi"));
        sqltest();


        return output.toJson(test);
    }
    private void sqltest(){
        try {
           Class.forName("org.postgresql.Driver");
          Connection c = getConnection();
            Statement stmt = c.createStatement();
           /** ResultSet rs = stmt.executeQuery( "SELECT * FROM SKILLS;" );
            while ( rs.next() ) {
                int id = rs.getInt("id");

                String  name = rs.getString("name");
                int age  = rs.getInt("age");
                String  address = rs.getString("address");
                float salary = rs.getFloat("salary");
                System.out.println( "ID = " + id );
                System.out.println( "NAME = " + name );
                System.out.println( "AGE = " + age );
                System.out.println( "ADDRESS = " + address );
                System.out.println( "SALARY = " + salary );
                System.out.println();

            }
            rs.close();
            stmt.close();
            c.close();**/
           sqladd(stmt, c);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
    public void sqladd(Statement stmt, Connection c) throws SQLException {
        String sql = "CREATE TABLE skills(start varchar(80) ,endtxt varchar(225), link varchar(225)); ";
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
