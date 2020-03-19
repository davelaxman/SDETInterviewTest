package org.SDET.interviewtest.StarWarsApi.tests.example;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;



@Test
public class ApiRootTests {

    @Test
    public void LogHome() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://swapi.co/api")
                .then()
                .statusCode(200)
                .log()
                .body();

    }
    @Test
    public void apiHome() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("https://swapi.co/api")
        .then()
            .statusCode(200)
            .assertThat()
                .body("films", equalTo("https://swapi.co/api/films/"),
                "people", equalTo("https://swapi.co/api/people/"),
                         "planets", equalTo("https://swapi.co/api/planets/"),
                         "species", equalTo("https://swapi.co/api/species/"),
                         "starships", equalTo("https://swapi.co/api/starships/"),
                         "vehicles", equalTo("https://swapi.co/api/vehicles/")
                );

    }
}
