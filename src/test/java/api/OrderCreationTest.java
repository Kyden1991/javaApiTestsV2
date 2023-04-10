package api;

import api.BaseTest.BaseMethods;
import api.BaseTest.BaseSpecClass;
import api.specifications.OrderCreationSpec;
import io.qameta.allure.Description;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class OrderCreationTest extends BaseMethods {
    private final String[][] color =
            new String[][]{
                    {"BLACK"},
                    {"GREY"},
                    {"BLACK", "GREY"},
                    {""}
            };

    @Test
    @Description("Return HTTP 201 status code and track in body by the POST/api/v1/orders method with valid request parameters and different colors")
    public void postOrderCreationWithDiffParametersReturnStatusCode201() {
        for (String[] color : this.color) {
            BaseSpecClass.responseWithBodyAssert(OrderCreationSpec.createOrder(
                    "Art",
                    "Kaz",
                    "MARRO",
                    1,
                    "+12345678910",
                    2,
                    "2024-01-31",
                    "Test",
                    color), HttpStatus.SC_CREATED, "track", notNullValue());
        }
    }
}
