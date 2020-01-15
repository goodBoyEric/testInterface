package baseFunction;

import static io.restassured.RestAssured.given;

public class Login {
    public static Login getLogin(){
        Login l = new Login();
        return l;
    }

    public String getToken(){
    String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ID&corpsecret=SECRECT";
    return given()
                    .queryParam("corpid", "ww915bad8d85a1b7c0")
                    .queryParam("corpsecret", "9FsyGoQe6dPKvS7anxq3vfDf-kzTlQS4gQ1jv-Jx9_Q")
            .when()
                    .log().all()
                    .get(url)
            .then()
                    .log().all()
                    .statusCode(200)
                    .extract().body().path("access_token");
    }
}
