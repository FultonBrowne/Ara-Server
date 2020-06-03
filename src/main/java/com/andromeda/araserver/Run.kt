package com.andromeda.araserver

import com.andromeda.araserver.iot.*
import com.andromeda.araserver.localSearchData.GetUserSkills
import com.andromeda.araserver.pages.*
import com.andromeda.araserver.skills.*
import com.andromeda.araserver.skills.Timer
import com.andromeda.araserver.util.*
import com.rometools.rome.feed.synd.SyndFeed
import fi.iki.elonen.NanoHTTPD
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.jetty.*
import io.ktor.request.path

object Run{
	@JvmStatic
    fun main(args: Array<String>) {
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
	val server = embeddedServer(Jetty, port = Port().main()) {
		  intercept(ApplicationCallPipeline.Features) {
			  println(call.request.path())
			  call.respondText(RouteLegacy().main(call.request.path().replace("//", "/"), call.request.headers["data"]))
		  }

        routing {
		route("/v1"){
			route("search"){
				get{
					println(this.call.request.queryParameters.get("term"))
				}
			}
			route("skills"){

			}
			route("db"){

			}
			route("feed"){

			}
		}
            get("/") {
                call.respondText("Hello World!", ContentType.Text.Plain)
            }
	    
            get("/demo") {
                call.respondText("HELLO WORLD!")
            }
	}
}.start()
}

}
