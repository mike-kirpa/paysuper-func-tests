package com.paysuper.appmanager.helpers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;


public class RestAPI {

    public Response createSimpleOrder(String project, Integer amount, String currency, String apiURL) {
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

    public Response createProductOrder(String project, String products, String apiURL) {
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
        requestParams.put("type", "product");
        request.header("Content-Type", "application/json");
        System.out.println(requestParams.toJSONString());
        request.body(requestParams.toJSONString());
        return request.post("/order");
    }
}
