package service.user.api;

import baseFunction.Login;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class UserApi {
    @Test
    public void createDepart() {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/create";
        HashMap<String, Object> postData = new HashMap<>();
        String randomString = RandomStringUtils.random(5, "abcdefgABCDEFG");
        postData.put("name", "a_song"+randomString);
        postData.put("parentid", "1");
        //
        given().
                queryParam("access_token", Login.getLogin().getToken())
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
