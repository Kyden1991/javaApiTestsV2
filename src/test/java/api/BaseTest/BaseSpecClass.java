package api.BaseTest;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matcher;

import static api.constants.Constants.BASE_URL;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class BaseSpecClass {
    @Step("Сборка спецификации запроса")
    public static RequestSpecification requestSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .build();
    }

    @Step("Сборка спецификации ответа")
    public static ResponseSpecification responseStatusCode(int statusCode){
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .log(LogDetail.ALL)
                .build();
    }

    @Step("Собираем данные для сверки статус кода в ответе, а так же вывод логов")
    public static ValidatableResponse responseStatusCode(Response response, int statusCode){
        return response
                .then()
                .log().all()
                .statusCode(statusCode);
    }

    @Step("Собираем данные для сверки статус кода в ответе, тело ответа, а так же вывод логов")
    public static ValidatableResponse responseWithBodyAssert(Response response, int statusCode, String path, Matcher<?> matcher) {
        return response
                .then()
                .spec(responseStatusCode(statusCode))
                .body(path, matcher);
    }

    @Step("Собираем данные для сверки статус кода в ответе и сверкой тела с json схемой, а так же вывод логов")
    public static ValidatableResponse responseWithMatchToJsonSchema(Response response, int statusCode, String jsonPath) {
        return response
                .then()
                .spec(responseStatusCode(statusCode))
                .body(matchesJsonSchemaInClasspath(jsonPath));
    }
}
