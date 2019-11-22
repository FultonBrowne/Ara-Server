package com.andromeda.araserver.util

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet

class MsSql {
    private val link = "araresdb.database.windows.net"
    private val userName = "pholtor"
    private val password =  ""//System.getenv("PASSWORD");
    var url = String.format(
        "jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;" + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;",
        link,
        "ara",
        userName,
        password
    )
    fun getSkills(): String {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        var connection: Connection? = null
        val link = "test"
        println(password)
        connection = DriverManager.getConnection(url)
        val statement = connection.createStatement()
        val selectSql = "SELECT id, link, hotWord from skills"
        val resultSet = statement.executeQuery(selectSql)
        while (resultSet.next())
        {
            println(resultSet.getString("link") + " "
                    + resultSet.getString("hotWord"));
        }


        return link

    }
}