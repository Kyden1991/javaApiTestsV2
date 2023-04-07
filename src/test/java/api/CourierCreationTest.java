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
    public void postCourierCreationReturnStatusCode409WithDuplicateLogin() {
        Response courierLocalVar = CourierCreationSpec.createCourier(getLogin(), getPassword(), getFirstName());

        BaseSpecClass.responseStatusCode(courierLocalVar, HttpStatus.SC_CONFLICT);
    }

    @Description("HTTP 201 status code and correct body is returned by the POST/api/v1/courier method with valid request parameters")
    @Test
    public void postCourierCreationReturnStatusCode201WithCorrectBody() {
        BaseSpecClass.responseWithBodyAssert(courier, 201, "ok", equalTo(true));
    }

    @Description("HTTP 400 status code is returned by the POST/api/v1/courier method without login field")
    @Test
    public void postCourierCreationReturnStatusCode400WithoutLoginRequiredField() {
        Response courierLocalVar = CourierCreationSpec.createCourierWithoutLoginField(randomPassword(), randomString());

        BaseSpecClass.responseStatusCode(courierLocalVar, HttpStatus.SC_BAD_REQUEST);
    }

    @Description("HTTP 400 status code is returned by the POST/api/v1/courier method without password field")
    @Test
    public void postCourierCreationReturnStatusCode400WithoutPasswordRequiredField() {
        Response courierLocalVar = CourierCreationSpec.createCourierWithoutPasswordField(randomString(), randomString());

        BaseSpecClass.responseStatusCode(courierLocalVar, HttpStatus.SC_BAD_REQUEST);
    }

    @Description("HTTP 400 status code is returned by the POST/api/v1/courier method without first name required field")
    @Test
    public void postCourierCreationReturnStatusCode400WithoutFirstNameRequiredField() {
        Response courierLocalVar = CourierCreationSpec.createCourierWithoutFirstnameField(randomString(), randomPassword());

        BaseSpecClass.responseStatusCode(courierLocalVar, HttpStatus.SC_CREATED);
    }
}

