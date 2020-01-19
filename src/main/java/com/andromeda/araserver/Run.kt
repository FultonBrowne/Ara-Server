package com.andromeda.araserver

import com.andromeda.araserver.iot.GetDeviceClass
import com.andromeda.araserver.iot.Main
import com.andromeda.araserver.iot.NewDevice
import com.andromeda.araserver.iot.Status
import com.andromeda.araserver.pages.ApiStart
import com.andromeda.araserver.pages.GetSkillData
import com.andromeda.araserver.pages.RssMain.rssMain1
import com.andromeda.araserver.pages.Update
import com.andromeda.araserver.skills.*
import com.andromeda.araserver.skills.Timer
import com.andromeda.araserver.util.KeyWord
import com.rometools.rome.feed.synd.SyndFeed
import com.rometools.rome.io.FeedException
import com.rometools.rome.io.SyndFeedOutput
import fi.iki.elonen.NanoHTTPD
import opennlp.tools.parser.Parser
import opennlp.tools.parser.ParserFactory
import opennlp.tools.parser.ParserModel
import java.io.IOException
import java.net.URL

public object Run : NanoHTTPD(80) {
    private var keyWord: KeyWord? = null
    private var model: ParserModel? = null
    private var parser: Parser? = null
    var port = listeningPort
    //If connected to
    override fun serve(session: IHTTPSession): Response {
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
            sessionUri.startsWith("/call") ->
                    main2 = keyWord?.let { parser?.let { it1 -> Call().main(sessionUri) } }
            sessionUri.startsWith("/call") ->
                main2 = keyWord?.let { parser?.let { it1 -> Text().main(sessionUri) } }
            sessionUri.startsWith("/skillsdata/") -> main2 =
                GetSkillData().main(sessionUri)
            sessionUri.startsWith("/update") -> main2 =
                Update().update(sessionUri)
            sessionUri.startsWith("/class") -> main2 =
                GetDeviceClass().main(sessionUri)
            sessionUri.startsWith("/what") -> main2 =
                SocialSkills().doYouLike(sessionUri.replace("/what/", ""))
            sessionUri.startsWith("/time") -> main2 =
                parser?.let { keyWord?.let { it1 -> Timer().main(sessionUri, it1, it) } }
            sessionUri.startsWith("/store") -> {

                    main2 = com.andromeda.araserver.store.Main().GetStoreContent()

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
        return newFixedLengthResponse(main2)
    }
    // Static function, to be run on start.
    @JvmStatic
    fun main(args: Array<String>) { // If this is in a heroku environment, get the port number
        start(SOCKET_READ_TIMEOUT, false)
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
        //new MsSql().getSkills();
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


    }

}