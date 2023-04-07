package api.specifications;

import api.BaseTest.BaseSpecClass;
import api.dto.CreateCourierDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static api.constants.Constants.PATH_COURIER_CREATION;
import static io.restassured.RestAssured.given;

public class CourierCreationSpec {
    @Step("Сборка спецификации для отправки запроса на создание курьера")
    public static Response createCourier(String login, int password, String firstName) {
        CreateCourierDto createCourier = new CreateCourierDto(login, password, firstName);
        return given()
                .spec(BaseSpecClass.requestSpec())
                .body(createCourier).log().all()
                .when()
                .post(PATH_COURIER_CREATION);
    }

    @Step("Сборка спецификации для отправки запроса на создание курьера без логина")
    public static Response createCourierWithoutLoginField(int password, String firstName) {
        CreateCourierDto createCourier = new CreateCourierDto(password, firstName);
        return given()
                .spec(BaseSpecClass.requestSpec())
                .body(createCourier).log().all()
                .when()
                .post(PATH_COURIER_CREATION);
    }

    @Step("Сборка спецификации для отправки запроса на создание курьера без пароля")
    public static Response createCourierWithoutPasswordField(String login, String firstName) {
        CreateCourierDto createCourier = new CreateCourierDto(login, firstName);
        return given()
                .spec(BaseSpecClass.requestSpec())
                .body(createCourier).log().all()
                .when()
                .post(PATH_COURIER_CREATION);
    }

    @Step("Сборка спецификации для отправки запроса на создание курьера без имени")
    public static Response createCourierWithoutFirstnameField(String login, int password) {
        CreateCourierDto createCourier = new CreateCourierDto(login, password);
        return given()
                .spec(BaseSpecClass.requestSpec())
                .body(createCourier).log().all()
                .when()
                .post(PATH_COURIER_CREATION);
    }
}
