package com.andromeda.araserver.store;

import com.andromeda.araserver.util.OutputModel;
import com.google.gson.Gson;

import java.sql.*;
import java.util.ArrayList;

public class Main {
    public String GetStoreContent() throws SQLException {
        Gson gson = new Gson();
        Connection c = null;
        ArrayList<OutputModel> outputModelArrayList = new ArrayList<>();
        try {
            c = getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assert c != null;
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM COMPANY;");
        // I dont know how this works but it does 😀
        while (rs.next()) {
            // get values from the data base
            String name = rs.getString("NAME");
            String endtxt = rs.getString("INFO");
            String link = rs.getString("LINK");
            String outText = rs.getString("ACTIONMAIN");
            //to avoid a null pointer
            outputModelArrayList.add(new OutputModel(name, endtxt,link,"", "", outText));
            System.out.println();
        }


        return gson.toJson(outputModelArrayList);
    }

    private static Connection getConnection() throws SQLException {
        //get a SQL connection (Note these creds are for test DB)
        String dbUrl = "jdbc:postgresql://" + "ec2-54-235-92-244.compute-1.amazonaws.com" + ':' + "5432" + "/dbrvk9v0ing68b" + "?ssl=true" + "&sslfactory=org.postgresql.ssl.NonValidatingFactory";
        return DriverManager.getConnection(dbUrl,
                "nrpnuktgkcsxdh", "2f1dbd4488f547b9b358b059221ee69da1965f9d96b3db36035e896d2c5c6808");
    }

    void add(Connection c) throws SQLException {
        Statement stmt = c.createStatement();
        String sql;
        sql = "INSERT INTO COMPANY(ID, NAME, INFO, LINK, PRE, SUF, ACTIONMAIN) VALUES (0, 'toggle WI-FI', 'switch your Wi-Fi settings','','turn on wifi','', '---"+
                "    - action: !WIFI!" +
                "      arg1: !TERM!" +
                "      arg2: !!')";

        stmt.executeUpdate(sql);
        stmt.close();
        c.close();

    }
}
