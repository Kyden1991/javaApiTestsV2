package api.specifications;

import api.BaseTest.BaseSpecClass;
import api.dto.CreateOrderDto;
import io.restassured.response.Response;

import static api.constants.Constants.ORDER_CREATION;
import static io.restassured.RestAssured.given;

public class OrderCreationSpec {
    public static Response createOrder(String firstName, String lastName, String address, int metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] color) {
        CreateOrderDto createOrder = new CreateOrderDto(firstName,
                lastName, address,
                metroStation, phone,
                rentTime, deliveryDate,
                comment, color);

        return given()
                .spec(BaseSpecClass.requestSpec())
                .body(createOrder).log().all()
                .when()
                .post(ORDER_CREATION);
    }
}
