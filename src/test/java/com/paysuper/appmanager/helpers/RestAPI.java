package com.paysuper.appmanager.helpers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;


public class RestAPI {

    public String createSimpleOrder(String project, Integer amount, String currency, String apiURL) {
        RestAssured.baseURI = apiURL;
        RequestSpecification request = given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("project", project);
        requestParams.put("amount", amount);
        requestParams.put("currency", currency);
        requestParams.put("type", "simple");
        request.header("Content-Type", "application/json");
        request.body(requestParams.toJSONString());
        return request.post("/order").then().extract().path("payment_form_url");
    }

    public String createProductOrder(String project, String products, String apiURL, String type) {
        RestAssured.baseURI = apiURL;
        List<String> list = new ArrayList<String>();
        list.add(products);
        JSONArray array = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            array.add(list.get(i));
        }
        RequestSpecification request = given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("project", project);
        requestParams.put("products", array);
        requestParams.put("type", type);
        request.header("Content-Type", "application/json");
        request.body(requestParams.toJSONString());
        return request.post("/order").then().extract().path("payment_form_url");
    }

    public String createTokenOrder(String apiURL, String project, String secret, String email) {
        String payload = "{\n    \"user\": {\n        \"id\": \"0000000000003\",\n        \"email\": {\n            \"value\": \"" + email + "\",\n            \"verified\": true\n        },\n        \"phone\": {\n            \"value\": \"0123456\",\n            \"verified\": true\n        },\n        \"name\": {\n            \"value\": \"Человек Х -токен\"\n        },\n        \"ip\": {\n            \"value\": \"194.54.160.79\"\n        },\n        \"locale\": {\n            \"value\": \"de-DE\"\n        },\n        \"address\": {\n            \"country\": \"DE\",\n            \"city\": \"Odessa\",\n            \"state\": \"Odessa\",\n            \"postal_code\": \"a300\"\n        },\n        \"metadata\": {\n            \"status\": \"VIP\"\n        }\n    },\n    \"settings\": {\n        \"project_id\": \""+project+"\",\n        \"currency\": \"EUR\",\n        \"amount\": 100,\n        \"description\": \"Какое-то описание платежа\",\n        \"metadata\": {\n            \"balance\": \"999.99\"\n        },\n        \"type\": \"simple\"\n    }\n}";
        return
                given()
                        .header("X-API-SIGNATURE", secret)
                        .header("Content-Type", "application/json")
                        .body(payload)
                        .post(apiURL+"/tokens")
                        .then()
                        .statusCode(200)
                        .extract()
                        .path("payment_form_url");
    }
}
