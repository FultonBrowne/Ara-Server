package com.andromeda.araserver.util

import opennlp.tools.parser.Parser
import java.net.URL
import java.sql.Connection
import java.sql.DriverManager

class MsSql {
    private val link = "araresdb.database.windows.net"
    private val userName = "pholtor"
    private val password =  ""//System.getenv("PASSWORD");
    private var url = String.format(
        "jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;" + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;",
        link,
        "ara",
        userName,
        password
    )
    fun getSkills(phrase:String,fullDir:String, keyWord: KeyWord, parse: Parser): String {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
        val connection: Connection = DriverManager.getConnection(url)
        val phrases = SortWords(keyWord,phrase).getTopics(parse)
        println(phrases.size)
        var link = ""
        println(password)
        val statement = connection.createStatement()
        val selectSql = "SELECT id, link, hotWord from skills"
        val resultSet = statement.executeQuery(selectSql)
        while (resultSet.next())
        {
            print("test")
            val dbLink = resultSet.getString("link")
            val dbWord = resultSet.getString("hotWord")
            for (i in phrases){
                if (i.word.startsWith(dbWord.replace(" ", ""))){
                    print("is true")
                    link = dbLink
                    break
                }
            }
        }
        if (link == "") link = "https://araserver.herokuapp.com/searcht"


        return URL(link.replace(" ", "") + "/" +fullDir.replace(" ", "%20")).readText()

    }
}