package com.andromeda.araserver

import com.andromeda.araserver.iot.*
import com.andromeda.araserver.localSearchData.GetUserSkills
import com.andromeda.araserver.pages.*
import com.andromeda.araserver.skills.*
import com.andromeda.araserver.skills.Timer
import com.andromeda.araserver.util.*
import com.google.gson.*
import com.rometools.rome.feed.synd.SyndFeed
import fi.iki.elonen.NanoHTTPD
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.jetty.*
import io.ktor.server.netty.*
import io.ktor.request.*

/** 
this object starts and handles the server routing and json.
the server uses the ktor (ktor.io) system to handle request

any bugs in this file are critical and should be fixed at once

no active devolpment of this file on the main branch unless working on a new and/or currently inactive api

 **/
object Run{
	@JvmStatic
    fun main(args: Array<String>) {
        News()
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
        Thread{
            NLPManager()

        }.start()
	
	

        println("start")
	embeddedServer(Netty, port = Port().main()) {
        install(ContentNegotiation) {
            jackson {
                enable(SerializationFeature.INDENT_OUTPUT)
            }
        }
		routing {
		route("/v1"){
			get{
				val payload = (ServerInfo.getAsJson())
				call.respondText(outputToApi(payload), ContentType.parse("application/json"))
			}
			route("skills"){
            route("call"){
					get{
					val params = ParseUrl().parseApi(this.call.request.queryParameters)
					val payload = (Call().main(params))
					call.respondText(outputToApi(payload), ContentType.parse("application/json"))
					}

            }

            route("text"){
					get{
					val params = ParseUrl().parseApi(this.call.request.queryParameters)
					val payload = (Text().main(params))
					call.respondText(outputToApi(payload), ContentType.parse("application/json"))
					}
            }
				route("timer"){
					get{
					val params = ParseUrl().parseApi(this.call.request.queryParameters)
					val payload = (Timer().main(params))
					call.respondText(outputToApi(payload), ContentType.parse("application/json"))
					}
				}
				route("weather"){
					get{
					val params = ParseUrl().parseApi(this.call.request.queryParameters)
               val payload = (Weather().main(params))
					call.respondText(outputToApi(payload), ContentType.parse("application/json"))
					}
				}
			}

			route("search"){
				get{
				val params = ParseUrl().parseApi(this.call.request.queryParameters)
            val payload = (Search.ara(params))
				call.respondText(outputToApi(payload), ContentType.parse("application/json"))
				}
				route("web"){
					get{	
						val params = ParseUrl().parseApi(this.call.request.queryParameters)
						val payload = Search.web(params)
						call.respondText(outputToApi(payload), ContentType.parse("application/json"))
					}
				}
			}
			route("db"){
				val db = DatabaseClient()
				post{

					val params = this.call.receive<dbActions>()
					db.new(params.user, params.id, params.data)
					val payload = ok(true)
					call.respondText( outputToApi(payload), ContentType.parse("application/json"), HttpStatusCode.Created)
				}
				get("{user}/{id}"){
					val payload = db.get(call.parameters["user"]!!, call.parameters["id"]!!)!!
					call.respondText(outputToApi(payload), ContentType.parse("application/json"))
				}
				put("{user}/{id}"){
					val params = this.call.receive<Any>()
					db.edit(call.parameters["user"]!!, call.parameters["id"]!!, params)
					val payload = ok(true)
					call.respondText(outputToApi(payload), ContentType.parse("application/json"))
				}
				get("{user}"){
					val payload = db.getAll<Any>(call.parameters["user"]!!, Any::class.java)
					call.respondText(outputToApi(payload), ContentType.parse("application/json"))
				}
				delete{
					db.delete(call.parameters["user"]!!, call.parameters["id"]!!)
					val payload = ok(true)
					call.respondText(outputToApi(payload), ContentType.parse("application/json"))
				}
			}
			route("feed"){
				get{
					val params = ParseUrl().parseFeed(this.call.request.queryParameters)
					val payload = NewsCache.us
					call.respondText(outputToApi(To().feedModelArray(payload)), ContentType.parse("application/json"))

				}

			}
		}
			get("teapot"){
				call.respondText(outputToApi(Message("I preffer coffee")), ContentType.parse("application/json"), HttpStatusCode(418, "I am a teapot"))
			}
	}

	intercept(ApplicationCallPipeline.Features) {
		 println(call.request.path())
		 val theData = RouteLegacy().main(call.request.path().replace("//", "/"), call.request.headers["data"])
		 println(theData)
		 if(theData == "") return@intercept
		call.respondText(RouteLegacy().main(call.request.path().replace("//", "/"), call.request.headers["data"]))
		}
	}.start(wait = true)
}

fun outputToApi(payload:Any):String{
	val jobj = Gson().toJsonTree(payload)
	println(jobj)
	val toReturn = JsonObject()
	toReturn.add("version", JsonPrimitive("v1.0.0"))
	toReturn.add("data", jobj)
	return toReturn.toString()

}
data class dbActions(val user:String, val id:String, val data:Any)
data class ok(val ok:Boolean)
data class Message(val message:String)
}
