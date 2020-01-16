package service.department.api;

import baseFunction.Login;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DepartApi {
    final static String ip = "https://qyapi.weixin.qq.com";
    final static String token = Login.getLogin().getToken();

    public Response create(String name){
        //
        String url = ip + "/cgi-bin/department/create";
        HashMap<String, Object> postData = new HashMap<>();
        postData.put("name", name);
        postData.put("parentid", "1");
        //
        return (Response)
        given().
                queryParam("access_token", token)
                .body(postData)
        .when()
                .log().all()
                .post(url)
        .then()
                .log().all()
                .body("errcode",equalTo(0))
                .extract().response();
    }


}
