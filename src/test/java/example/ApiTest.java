package example;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiTest {

    public static String url = ConfProperties.getProperty("urlapi");

    @Test
    @Description(value = "Тест проверяет создание и поиск пользователя через API (petstore.swagger.io)")
    public void apitest1() {

        JSONObject map = new JSONObject();
        map.put("id", "0");
        map.put("username", "TestApiUser");
        map.put("firstName", "Иван");
        map.put("lastName", "Иванов");
        map.put("email", "ivanovivan@gmail.com");
        map.put("password", "qwerty123");
        map.put("phone", "+7(962)973-34-67");
        map.put("userStatus", "0");

        step1(map);
        step2("TestApiUser");
    }

    @Step("Создать пользователя с парметрами: {map}")
    public void step1(JSONObject map) {
        Response res = given()
                .baseUri(url)
                .header("Content-type", "application/json")
                .and()
                .body(map.toString())
                .when()
                .post("/user")
                .then()
                .extract()
                .response();
        assertEquals(200, res.statusCode(), "Пользователь не создан");
        Allure.addAttachment("Response", "application/json", res.jsonPath().get().toString());
    }

    @Step("Получить данные пользователя с username ='{userName}'")
    public void step2(String userName) {
        Response res = given()
                .baseUri(url)
                .header("Content-type", "application/json")
                .when()
                .get("/user/" + userName)
                .then()
                .extract()
                .response();
        assertEquals(userName, res.jsonPath().getString("username"), "Данные пользователя с username ='" + userName + "' не получены ");
        Allure.addAttachment("Response", "application/json", res.jsonPath().get().toString());
    }

}

