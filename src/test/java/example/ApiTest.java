package example;


import example.pages.WebElementsPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.image.ImageProducer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ApiTest {

    public static String url;

    @BeforeAll
    public static void open() {
        url = ConfProperties.getProperty("urlapi");
    }





    @Test
    @Description(value = "Тест проверяет создание и поиск пользователя через API (petstore.swagger.io)")
    public void apitest1() {
        Map<String, Object> map = new HashMap<>();
        map.put("id",0);
        map.put("username","TestApiUser");
        map.put("firstName","Иван");
        map.put("lastName","Иванов");
        map.put("email","ivanovivan@gmail.com");
        map.put("password","qwerty123");
        map.put("phone","+7(962)973-34-67");
        map.put("userStatus",0);

        step1(map);
        step2("TestApiUser");
    }

    @Step("Создать пользователя с парметрами: {map}")
    public void step1(Map<String, Object> map) {
        Response res = RestAssured.given()
                .baseUri(url)
                .header("Content-type", "application/json")
                .and()
                .body(map.toString())
                .when()
                .post("/user")
                .then()
                .extract()
                .response();
        assertEquals(200, res.statusCode(),"Пользователь не создан");
        System.out.println("Пользователь успешно создан");
    }

    @Step("Получить данные пользователя с username ='{text}'")
    public void step2(String userName) {
        Response res = given()
                .baseUri(url)
                .header("Content-type", "application/json")
                .when()
                .get("/user/"+userName)
                .then()
                .extract()
                .response();
        assertEquals( userName, res.jsonPath().getString("username"),"Данные пользователя с username ='" + userName + "' не получены ");
        System.out.println("Получены данные пользователя с username ='" + userName + "':");
        System.out.println("- Фамилия Имя: '" + res.jsonPath().getString("lastName") + " " + res.jsonPath().getString("firstName"));
        System.out.println("- Телефон : '" + res.jsonPath().getString("phone"));
        System.out.println("- Email : '" + res.jsonPath().getString("email"));
    }




}

