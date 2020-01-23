package com.paysuper.appmanager.helpers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;


public class RestAPI {

    public Response createOrder(String project, int amount, String currency, String apiURL) {
        RestAssured.baseURI = apiURL;
        RequestSpecification request = given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("project", project);
        requestParams.put("amount", amount);
        requestParams.put("currency", currency);
        requestParams.put("type", "simple");
        request.header("Content-Type", "application/json");
        request.body(requestParams.toJSONString());
        return request.post("/order");
    }
}
