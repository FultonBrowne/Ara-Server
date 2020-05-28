package com.andromeda.araserver

import com.andromeda.araserver.iot.*
import com.andromeda.araserver.localSearchData.GetUserSkills
import com.andromeda.araserver.pages.*
import com.andromeda.araserver.skills.*
import com.andromeda.araserver.skills.Timer
import com.andromeda.araserver.util.*
import com.rometools.rome.feed.synd.SyndFeed
import fi.iki.elonen.NanoHTTPD

object Run : NanoHTTPD(Port().main()!!) {

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

        println(sessionUri)
	main2 = RouteLegacy().main(sessionUri, session.headers["data"])
        //Output response
        return newFixedLengthResponse(main2?.replace(",{}", "")?.replace("{}", ""))
    }
    // Static function, to be run on start.
    @JvmStatic
    fun main(args: Array<String>) {
        start(SOCKET_READ_TIMEOUT, false)
        News()
        SecurityDBCheck()
        println(" Ara server is running and is available on your domain, IP, or http://localhost:8080/")
        println(
            "Copy write (c) 2020 Fulton Browne " +
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
        println("test")
        Thread{
            NLPManager()

        }.start()

        println("start")


    }

}
