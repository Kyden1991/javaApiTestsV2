package api.BaseTest;

import api.specifications.CourierCreationSpec;
import api.specifications.CourierLoginSpec;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.security.SecureRandom;


import static api.constants.Constants.BASE_URL;
import static api.constants.Constants.PATH_COURIER_DELETE;
import static io.restassured.RestAssured.given;


public class BaseMethods {
    public static List<Integer> createdCourierIds = new ArrayList<>();
    private static String login = randomString();
    private static int password = randomPassword();
    private static String firstName = randomString();

    public static String getLogin() {
        return login;
    }
    public static int getPassword() {
        return password;
    }
    public static String getFirstName() {
        return firstName;
    }


    @BeforeClass
    public static void prepareTestCourier() {
        Response createCourier =
            CourierCreationSpec.createCourier(getLogin(), getPassword(), randomString());
        createCourier.then().log().all();

        Response loginCourier =
            CourierLoginSpec.courierLogin(getLogin(), getPassword());
        loginCourier.then().log().all();

        JSONObject responseLoginBody = new JSONObject(loginCourier.getBody().asString());
        createdCourierIds.add(responseLoginBody.getInt("id"));
    }

    @AfterClass
    public static void deleteTestCourier() {
        Response response =
                given()
                        .log().all()
                        .when()
                        .delete(BASE_URL + PATH_COURIER_DELETE + createdCourierIds.get(0));
        response.then().log().all().assertThat().statusCode(200);
    }

    public static String randomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static int randomPassword() {
        // Instance of SecureRandom class
        SecureRandom rand = new SecureRandom();
        // Setting the upper bound to generate
        // the random numbers between the specific range
        int upperbound = 999999;
        return rand.nextInt(upperbound);
    }
}
