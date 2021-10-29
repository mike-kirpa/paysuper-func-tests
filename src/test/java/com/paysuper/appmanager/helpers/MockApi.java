package com.paysuper.appmanager.helpers;

import com.paysuper.appmanager.models.Webhook;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.experimental.theories.Theories;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class MockApi {

    private String token;
    private final String url = "https://mockapi.io";
    private Response response;
    final private String mockapi_email = "autotest.protocolone@gmail.com";
    final private String mockapi_pass = "Pw,:Af44=8XN8)~:";


    public  MockApi(){
        login();
        resetAll();
    }

    public void login(){
        RestAssured.baseURI = url;
        RequestSpecification request = given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", mockapi_email);
        requestParams.put("password", mockapi_pass);
        request.header("Content-Type", "application/json");
        request.body(requestParams.toJSONString());
        response = request.post("/auth/local");
        token = response.then().extract().path("token");
    }


    public String getToken(){
        return token;
    }

    public void getLastWebhook(Webhook webhook){
        RestAssured.baseURI = url;
        response = given()
                .header("token", token)
                .when()
                .get("/api/mocks/6007fe89309f8b0017ee51ca/resources/" + GetProperties.value("EndpointId") +"/data")
                .then()
                .contentType(JSON)
                .assertThat()
                .statusCode(200)
                .extract()
                .response();
        webhook.setEvent(response.jsonPath().getString("event[0]"));
        webhook.setObject_id(response.jsonPath().getString("object[0].id"));
        webhook.setObject_object(response.jsonPath().getString("object[0].object"));
        webhook.setObject_status(response.jsonPath().getString("object[0].status"));
        webhook.setObject_canceled(Boolean.parseBoolean(response.jsonPath().getString("object[0].canceled")));
        webhook.setObject_cancellation(response.jsonPath().getString("object[0].cancellation"));
        webhook.setObject_refunded(Boolean.parseBoolean(response.jsonPath().getString("object[0].refunded")));
    }

    public void resetAll(){
        RestAssured.baseURI = url;
        given()
                .contentType(ContentType.JSON)
                .header("token", token)
                .when()
                .post("/api/mocks/6007fe89309f8b0017ee51ca/resources/reset_all")
                .then()
                .assertThat()
                .statusCode(200);
    }

    public void checkEvent() throws InterruptedException {
        RestAssured.baseURI = url;
        int count;
        int repeat = 24;
        RestAssured.baseURI = url;
        do{
            response = given()
                    .header("token", token)
                    .when()
                    .get("/api/mocks/6007fe89309f8b0017ee51ca/resources")
                    .then()
                    .contentType(JSON)
                    .assertThat()
                    .statusCode(200)
                    .extract()
                    .response();
            count = Integer.parseInt(response.jsonPath().getString("resources[0].count"));
            Thread.sleep(3000);
            repeat--;
        }while (count < 1 && repeat > 0);
    }

    public void checkAndCleatEvent(Webhook webhook) throws InterruptedException {
        checkEvent();
        getLastWebhook(webhook);
        resetAll();
    }
}
