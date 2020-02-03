package com.andromeda.araserver.persona

import com.andromeda.araserver.util.SortWords
import java.sql.Connection
import java.sql.DriverManager

class GetDbArray {
    private val link = "araresdb.database.windows.net"
    private val userName = "pholtor"
    private val password =  System.getenv("PASSWORD");
    private var url = String.format(
        "jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;" + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;",
        link,
        "ara",
        userName,
        password)
    fun likes(search: String){
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
        val connection: Connection = DriverManager.getConnection(url)
        var link = ""
        println(password)
        val statement = connection.createStatement()
        val selectSql = "SELECT * from likes"
        val resultSet = statement.executeQuery(selectSql)
        while (resultSet.next())
        {

        }
    }
}