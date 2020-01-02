package com.andromeda.araserver

import com.andromeda.araserver.iot.Main
import com.andromeda.araserver.iot.NewDevice
import com.andromeda.araserver.iot.Status
import com.andromeda.araserver.pages.ApiStart
import com.andromeda.araserver.pages.GetSkillData
import com.andromeda.araserver.pages.RssMain.rssMain1
import com.andromeda.araserver.pages.Update
import com.andromeda.araserver.skills.*
import com.andromeda.araserver.util.KeyWord
import com.rometools.rome.feed.synd.SyndFeed
import com.rometools.rome.io.FeedException
import com.rometools.rome.io.SyndFeedOutput
import fi.iki.elonen.NanoHTTPD
import io.ktor.application.Application
import io.ktor.application.ApplicationCallPipeline
import io.ktor.application.call
import io.ktor.request.queryString
import io.ktor.request.uri
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import opennlp.tools.parser.Parser
import opennlp.tools.parser.ParserFactory
import opennlp.tools.parser.ParserModel
import java.io.IOException
import java.net.URL
import java.sql.SQLException
import java.util.*

object Run  {
    private var keyWord: KeyWord? = null
    private var model: ParserModel? = null
    private var parser: Parser? = null
    //If connected to
    fun serve(session: NanoHTTPD.IHTTPSession) {
        val tag: Int
        //URI passed from client
        val sessionUri = session.uri
        //Feed if any
        var syndFeed: SyndFeed? = null
        //Text to be outputted
        var main2: String? = "err"
        //Functions related to the search api
        //Start API function
        when {
            sessionUri.startsWith("/api") -> main2 =
                ApiStart().apiMain(sessionUri, keyWord, parser)
            sessionUri.startsWith("/hi") -> main2 =
                Hello().hello()
            sessionUri.startsWith("/yelpclient") -> main2 =
                Locdec().main(sessionUri, keyWord, parser)
            sessionUri.startsWith("/weath") -> main2 =
                Weather().mainPart(sessionUri, keyWord!!, parser!!)
            sessionUri.startsWith("/devices/") -> main2 =
                Main().main(sessionUri)
            sessionUri.startsWith("/deviceinfo/") -> main2 =
                Status().main(sessionUri)
            sessionUri.startsWith("/search") -> {
                main2 = GetInfo().main(sessionUri)
            }
            sessionUri.startsWith("/newdevice/") -> {
                main2 = ""
                NewDevice().main(sessionUri)
            }
            sessionUri.startsWith("/math") -> main2 =
                Equations().main(sessionUri)
            sessionUri.startsWith("/skillsdata/") -> main2 =
                GetSkillData().main(sessionUri)
            sessionUri.startsWith("/update") -> main2 =
                Update().update(sessionUri)
            sessionUri.startsWith("/what") -> main2 =
                SocialSkills().doYouLike(sessionUri.replace("/what/", ""))
            sessionUri.startsWith("/store") -> {
                try {
                    main2 = com.andromeda.araserver.store.Main().GetStoreContent()
                } catch (e: SQLException) {
                    e.printStackTrace()
                }
            }
            else -> { // if getting RSS info set tag value this will be used to get the correct feed
                tag = when (sessionUri) {
                    "/world" -> 1
                    "/us" -> 2
                    "/tech" -> 3
                    "/money" -> 4
                    else -> 0
                }
                try { // get Rss feed from RssMain.kt
                    syndFeed = rssMain1(tag)
                } catch (e: IOException) { // if any issues
                    e.printStackTrace()
                } catch (e: FeedException) {
                    e.printStackTrace()
                }
                // turn feed content in to XML text
                try {
                    assert(syndFeed != null)
                    main2 = SyndFeedOutput().outputString(syndFeed)
                } catch (e: FeedException) {
                    e.printStackTrace()
                }
            }
        }
        println(sessionUri)
        //Output response

    }
    fun serverCode(a:Application) {
        a.routing {
            println("")
            get("/api/{param?}") {
                call.respondText{ApiStart().apiMain(call.request.uri.replace("%20", " "), keyWord, parser)}
            }
            get("/hi/{param?}"){
                call.respondText{Hello().hello()}
            }
            get("/searcht/{param?}"){
                call.respondText{GetInfo().main(call.request.uri.replace("%20", " "))}
            }
            get("/store/{param?}"){
                call.respondText{com.andromeda.araserver.store.Main().GetStoreContent()}
            }
            get("/devices/{param?}"){
                call.respondText{Main().main(call.request.uri.replace("%20", " "))}
            }
            get("/skillsdata/{param?}"){
                call.respondText{GetSkillData().main(call.request.uri.replace("%20", " "))!!}
            }
            get("/newdevice/{param?}"){
                NewDevice().main(call.request.uri)
            }
            get("/weath/{param?}"){call.respondText(Weather().mainPart(call.request.uri.replace("%20", " "), keyWord!!, parser!!)!!)}
            get("/yelpclient/{param?}"){call.respondText(Locdec().main(call.request.uri.replace("%20", " "), keyWord, parser))}
        }
    }
        // Static function, to be run on start.
        @JvmStatic
        fun main(args: Array<String>) {
            val server = embeddedServer(Netty, 8080){
                serverCode(this)
            }
            println(" Ara server is running and is available on your domain, IP, or http://localhost:8080/")
            println(
                "This program is free software: you can redistribute it and/or modify\n" +
                        "    it under the terms of the GNU General Public License as published by\n" +
                        "    the Free Software Foundation, either version 3 of the License, or\n" +
                        "    (at your option) any later version.\n" +
                        "\n" +
                        "    This program is distributed in the hope that it will be useful,\n" +
                        "    but WITHOUT ANY WARRANTY; without even the implied warranty of\n" +
                        "    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\n" +
                        "    GNU General Public License for more details.\n" +
                        "\n" +
                        "    You should have received a copy of the GNU General Public License\n" +
                        "    along with this program.  If not, see <https://www.gnu.org/licenses/>."
            )
            val classloader = javaClass.classLoader
            var `is` = classloader.getResourceAsStream("resources/parse.bin")
            println("test")
            if (`is` == null) {
                val url =
                    URL("https://arafilestore.file.core.windows.net/ara-server-files/parse.bin?sv=2019-02-02&ss=bfqt&srt=sco&sp=rwdlacup&se=2024-04-01T22:11:11Z&st=2019-12-19T15:11:11Z&spr=https&sig=lfjMHSahA6fw8enCbx0hFTE1uAVJWvPmC4m6blVSuuo%3D")
                `is` = url.openStream()
            }
            model = ParserModel(`is`)
            parser = ParserFactory.create(model)
            keyWord = KeyWord(`is`!!)
            println("Press any key to exit...")
            server.start(wait = false)


    }


}