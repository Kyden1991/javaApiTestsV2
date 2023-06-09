package api;

import api.BaseTest.BaseMethods;
import api.BaseTest.BaseSpecClass;
import api.specifications.CourierLoginSpec;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static api.BaseTest.BaseSpecClass.responseStatusCode;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class CourierLoginTest extends BaseMethods {
    private Response courierLogin = CourierLoginSpec.courierLogin(getLogin(), getPassword());

    @Test
    @Description("HTTP 200 status code is returned by the POST/api/v1/courier/login method with valid request parameters")
    public void postCourierLoginReturnStatusCode200() {
        BaseSpecClass.responseStatusCode(courierLogin, HttpStatus.SC_OK);
    }

    @Description("HTTP 200 status code and correct body is returned by the POST/api/v1/courier/login method with valid request parameters")
    @Test
    public void postCourierLoginReturnStatusCode200WithCorrectBody() {
        BaseSpecClass.responseWithBodyAssert(courierLogin, HttpStatus.SC_OK, "id", notNullValue());
    }

    @Description("HTTP 400 status code is returned by the POST/api/v1/courier/login method without required login field")
    @Test
    public void postCourierLoginWithoutLoginFieldReturnStatusCode400() {
        Response courierLogin = CourierLoginSpec.courierLoginWithoutLoginField(getPassword());

        BaseSpecClass.responseStatusCode(courierLogin, HttpStatus.SC_BAD_REQUEST);
    }

    @Description("HTTP 400 status code is returned by the POST/api/v1/courier/login method without required password field")
    @Test
    public void postCourierLoginWithoutPasswordFieldReturnStatusCode400() {
        Response courierLogin = CourierLoginSpec.courierLoginWithoutPasswordField(getLogin());

        BaseSpecClass.responseStatusCode(courierLogin, HttpStatus.SC_BAD_REQUEST);
    }

    @Description("HTTP 404 status code is returned by the POST/api/v1/courier/login method with invalid password field")
    @Test
    public void postCourierLoginWithInvalidPasswordReturnStatusCode404() {
        Response courierLogin = CourierLoginSpec.courierLogin(getLogin(), -123);

        BaseSpecClass.responseStatusCode(courierLogin, HttpStatus.SC_NOT_FOUND);
    }

    @Description("HTTP 404 status code is returned by the POST/api/v1/courier/login method with invalid login field")
    @Test
    public void postCourierLoginWithInvalidLoginReturnStatusCode404() {
        Response courierLogin = CourierLoginSpec.courierLogin("1231231", getPassword());

        BaseSpecClass.responseStatusCode(courierLogin, HttpStatus.SC_NOT_FOUND);
    }

    @Description("HTTP 404 status code is returned by the POST/api/v1/courier/login method with non exist login")
    @Test
    public void postCourierLoginWithNonExistLoginReturnStatusCode404() {
        Response courierLogin = CourierLoginSpec.courierLogin(randomString(), getPassword());

        BaseSpecClass.responseStatusCode(courierLogin, HttpStatus.SC_NOT_FOUND);
    }

    @Description("HTTP 404 status code is returned by the POST/api/v1/courier/login method with non exist password")
    @Test
    public void postCourierLoginWithNonExistPasswordReturnStatusCode404() {
        Response courierLogin = CourierLoginSpec.courierLogin(getLogin(), 00001);

        BaseSpecClass.responseStatusCode(courierLogin, HttpStatus.SC_NOT_FOUND);
    }
}
