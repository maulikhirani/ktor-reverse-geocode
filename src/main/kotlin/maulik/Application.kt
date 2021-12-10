package maulik

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import maulik.plugins.*

fun main() {
    embeddedServer(Netty, port = 8081, host = "0.0.0.0") {
        configureMonitoring()
        configureRouting()
    }.start(wait = true)
}
