package api;

import api.BaseTest.BaseSpecClass;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static api.constants.Constants.PATH_ORDER_LIST;
import static io.restassured.RestAssured.given;

public class GettingOrderListTest {

    @Description("HTTP 200 status code is returned by the GET/api/v1/ method with valid request parameters and return list of orders like in json schema")
    @Test
    public void getOrderListReturnStatusCode200AndListOfOrders(){
        Response response =
                given().spec(BaseSpecClass.requestSpec())
                .get(PATH_ORDER_LIST+"?limit=10&page=0&nearestStation=[\"10\"]");

        BaseSpecClass.responseWithMatchToJsonSchema(response, HttpStatus.SC_OK, "OrdersJsonSchema.json");
    }
}
