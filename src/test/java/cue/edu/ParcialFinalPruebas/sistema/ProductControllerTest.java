package cue.edu.ParcialFinalPruebas.sistema;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductControllerTest {

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/api/products";
    }

    @Test
    public void testCreateProduct() {
        String productJson = "{\"name\":\"Test Product\", \"price\":100.0}";

        given()
                .contentType(ContentType.JSON)
                .body(productJson)
                .when()
                .post()
                .then()
                .statusCode(201)
                .body("name", equalTo("Test Product"))
                .body("price", equalTo(100.0f));
    }

    @Test
    public void testGetAllProducts() {
        given()
                .when()
                .get()
                .then()
                .statusCode(200)
                .body("$", is(not(empty())));
    }

    @Test
    public void testGetProductById() {

        String productJson = "{\"name\":\"Test Product\", \"price\":100.0}";
        Long productId = given()
                .contentType(ContentType.JSON)
                .body(productJson)
                .when()
                .post()
                .then()
                .extract().path("id");

        given()
                .when()
                .get("/" + productId)
                .then()
                .statusCode(200)
                .body("id", equalTo(productId.intValue()))
                .body("name", equalTo("Test Product"));
    }

    @Test
    public void testUpdateProduct() {

        String productJson = "{\"name\":\"Test Product\", \"price\":100.0}";
        Long productId = given()
                .contentType(ContentType.JSON)
                .body(productJson)
                .when()
                .post()
                .then()
                .extract().path("id");

        String updatedProductJson = "{\"name\":\"Updated Product\", \"price\":150.0}";

        given()
                .contentType(ContentType.JSON)
                .body(updatedProductJson)
                .when()
                .put("/" + productId)
                .then()
                .statusCode(200)
                .body("name", equalTo("Updated Product"))
                .body("price", equalTo(150.0f));
    }

    @Test
    public void testDeleteProduct() {

        String productJson = "{\"name\":\"Test Product\", \"price\":100.0}";
        Long productId = given()
                .contentType(ContentType.JSON)
                .body(productJson)
                .when()
                .post()
                .then()
                .extract().path("id");

        given()
                .when()
                .delete("/" + productId)
                .then()
                .statusCode(204);


        given()
                .when()
                .get("/" + productId)
                .then()
                .statusCode(404);
    }
}
