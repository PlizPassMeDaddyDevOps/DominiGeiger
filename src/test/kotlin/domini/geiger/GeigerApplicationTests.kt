package domini.geiger

import io.restassured.http.ContentType
import io.restassured.RestAssured
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [(GeigerApplication::class)],
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpaRestBackendApplicationTest {

    @LocalServerPort
    protected var port = 0

    @Autowired
    protected lateinit var repository: DeviceRepository

    @BeforeEach
    @AfterEach
    fun clean() {

        RestAssured.baseURI = "http://localhost"
        RestAssured.port = port
        RestAssured.basePath = "/api/devices"
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()

        repository.run {
            deleteAll()
            save(Device(1, 1, 1))
            save(Device(2, 2, 2))
            save(Device(3, 3, 3))
            save(Device(4, 4, 4))
            save(Device(5, 5, 5))
            save(Device(6, 6, 6))
        }
    }

    @Test
    fun testGetAll() {

        given().accept(ContentType.JSON)
                .get()
                .then()
                .statusCode(200)
                .body("size()", equalTo(6))
    }

    @Test
    fun testNotFoundDevice() {

        given().accept(ContentType.JSON)
                .get("/-3")
                .then()
                .statusCode(404)
    }
    /*
    @Test
    fun testRetrieveEachSingleDevice() {

        val devices = given().accept(ContentType.JSON)
                .get()
                .then()
                .statusCode(200)
                .body("size()", equalTo(6))
                .extract().body().jsonPath().getList("", DeviceDto::class.java)

        for (d in devices) {

            given().accept(ContentType.JSON)
                    .get("/${d.id}")
                    .then()
                    .statusCode(200)
                    .body("lat", equalTo(d.lat))
                    .body("lng", equalTo(d.lng.toString()))
                    .body("sievert", equalTo(d.sievert.toString()))
        }
    }
    @Test
    fun testCreateDevice() {

        val n = given().accept(ContentType.JSON)
                .get()
                .then()
                .statusCode(200)
                .extract().body().path<Int>("size()")

        val sievert = 6

        val location = given().contentType(ContentType.JSON)
                .body(DeviceDto(6, 6, sievert))
                .post()
                .then()
                .statusCode(201)
                .extract().header("location")

        given().accept(ContentType.JSON)
                .basePath("")
                .get(location)
                .then()
                .statusCode(200)
                .body("sievert", equalTo(sievert))

        given().accept(ContentType.JSON)
                .get()
                .then()
                .statusCode(200)
                .body("size()", equalTo(n + 1))
    }*/
}
