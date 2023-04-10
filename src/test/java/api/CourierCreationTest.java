package api;

import api.BaseTest.BaseMethods;
import api.BaseTest.BaseSpecClass;
import api.specifications.CourierCreationSpec;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static api.BaseTest.BaseSpecClass.responseStatusCode;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CourierCreationTest extends BaseMethods {
    private Response courier = CourierCreationSpec.createCourier(randomString(), randomPassword(), randomString());

    @Description("HTTP 201 status code is returned by the POST/api/v1/courier method with valid request parameters in all required fields")
    @Test
    public void postCourierCreationReturnStatusCode201() {
        BaseSpecClass.responseStatusCode(courier, HttpStatus.SC_CREATED);
    }

    @Description("HTTP 409 status code is returned by the POST/api/v1/courier method when create a duplicate courier")
    @Test
    public void postCourierCreationWithDuplicateLoginReturnStatusCode409() {
        Response courierLocalVar = CourierCreationSpec.createCourier(getLogin(), getPassword(), getFirstName());

        BaseSpecClass.responseStatusCode(courierLocalVar, HttpStatus.SC_CONFLICT);
    }

    @Description("HTTP 201 status code and correct body is returned by the POST/api/v1/courier method with valid request parameters")
    @Test
    public void postCourierCreationWithCorrectBodyReturnStatusCode201() {
        BaseSpecClass.responseWithBodyAssert(courier, HttpStatus.SC_CREATED, "ok", equalTo(true));
    }

    @Description("HTTP 400 status code is returned by the POST/api/v1/courier method without login field")
    @Test
    public void postCourierCreationWithoutLoginRequiredFieldReturnStatusCode400() {
        Response courierLocalVar = CourierCreationSpec.createCourierWithoutLoginField(randomPassword(), randomString());

        BaseSpecClass.responseStatusCode(courierLocalVar, HttpStatus.SC_BAD_REQUEST);
    }

    @Description("HTTP 400 status code is returned by the POST/api/v1/courier method without password field")
    @Test
    public void postCourierCreationWithoutPasswordRequiredFieldReturnStatusCode400() {
        Response courierLocalVar = CourierCreationSpec.createCourierWithoutPasswordField(randomString(), randomString());

        BaseSpecClass.responseStatusCode(courierLocalVar, HttpStatus.SC_BAD_REQUEST);
    }
}


