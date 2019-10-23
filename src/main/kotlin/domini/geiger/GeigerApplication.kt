package domini.geiger

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GeigerApplication

fun main(args: Array<String>) {
    runApplication<GeigerApplication>(*args)
}
