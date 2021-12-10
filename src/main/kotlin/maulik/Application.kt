package maulik

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import maulik.plugins.*

fun main() {
    embeddedServer(Netty, port = System.getenv("PORT").toInt()) {
        configureMonitoring()
        configureRouting()
    }.start(wait = true)
}
