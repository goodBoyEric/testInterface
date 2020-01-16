package service.tag.api;

import baseFunction.Login;
import io.restassured.response.Response;
import java.util.HashMap;
import static io.restassured.RestAssured.given;

public class TagApi {
    final static String ip = "https://qyapi.weixin.qq.com";
    final static String token = Login.getLogin().getToken();

    public Response create(String tagname){
        // {"tagname": "UI","tagid": 12}
        HashMap<String, Object> postData = new HashMap<>();
        postData.put("tagname", tagname);
        String url = ip + "/cgi-bin/tag/create?access_token=ACCESS_TOKEN";
        return (Response)
                given()
                        .queryParam("access_token", token)
                        .body(postData)
                .when()
                        .post(url)
                .then()
                        .log().all()
                        .statusCode(200)
                        .extract().response();
    }

    public Response update(String tagname, Integer tagid){
        // {"tagid": 12,"tagname": "UI design"}
        HashMap<String, Object> postData = new HashMap<>();
        postData.put("tagname", tagname);
        postData.put("tagid", tagid);
        String url = ip + "/cgi-bin/tag/update";
        return (Response)
                given()
                        .queryParam("access_token", token)
                        .body(postData)
                        .log().all()
                .when()
                        .post(url)
                .then()
                        .statusCode(200)
                        .extract().response();
    }

    public Response delete(String tagid){
        // tagid=TAGID
        String url = ip + "/cgi-bin/tag/delete";
        return (Response)
                given()
                        .queryParam("access_token", token)
                        .queryParam("tagid", tagid)
                        .when()
                        .get(url)
                        .then()
                        .statusCode(200)
                        .extract().response();
    }

    public Response getTagElement(String tagid){
        // tagid=TAGID
        String url = ip + "/cgi-bin/tag/get";
        return (Response)
                given()
                        .queryParam("access_token", token)
                        .queryParam("tagid", tagid)
                        .when()
                        .get(url)
                        .then()
                        .statusCode(200)
                        .extract().response();
    }

    public Response addTagUsers(String tagid, String[] userlist, Integer[] partylist){
        HashMap<String, Object> postData = new HashMap<>();
        postData.put("tagid", tagid);
        postData.put("userlist", userlist);
        postData.put("partylist", partylist);
        return addTagUsers(postData);
    }

    public Response addTagUsers(String tagid, String[] userlist){
        HashMap<String, Object> postData = new HashMap<>();
        postData.put("tagid", tagid);
        postData.put("userlist", userlist);
        return addTagUsers(postData);
    }

    public Response addTagUsers(Integer tagid, Integer[] partylist){
        HashMap<String, Object> postData = new HashMap<>();
        postData.put("tagid", tagid);
        postData.put("partylist", partylist);
        return addTagUsers(postData);
    }

    private Response addTagUsers(HashMap<String, Object> postData){
        // tagid=TAGID
        String url = ip + "/cgi-bin/tag/addtagusers";
        return (Response)
                given()
                        .queryParam("access_token", token)
                        .body(postData)
                        .log().all()
                .when()
                        .post(url)
                .then()
                        .log().all()
                        .statusCode(200)
                        .extract().response();
    }

    public Response delTagUsers(Integer tagid, String[] userlist, Integer[] partylist){
        HashMap<String, Object> postData = new HashMap<>();
        postData.put("tagid", tagid);
        postData.put("userlist", userlist);
        postData.put("partylist", partylist);
        return delTagUsers(postData);
    }

    public Response delTagUsers(Integer tagid, String[] userlist){
        HashMap<String, Object> postData = new HashMap<>();
        postData.put("tagid", tagid);
        postData.put("userlist", userlist);
        return delTagUsers(postData);
    }

    public Response delTagUsers(Integer tagid, Integer[] partylist){
        HashMap<String, Object> postData = new HashMap<>();
        postData.put("tagid", tagid);
        postData.put("partylist", partylist);
        return delTagUsers(postData);
    }

    public Response delTagUsers(HashMap<String,Object> postData){
        // tagid=TAGID
        String url = ip + "/cgi-bin/tag/deltagusers";
        return (Response)
                given()
                        .queryParam("access_token", token)
                        .body(postData)
                        .log().all()
                .when()
                        .post(url)
                .then()
                        .statusCode(200)
                        .extract().response();
    }

    public Response tagList(){
        // tagid=TAGID
        String url = ip + "/cgi-bin/tag/list";
        return (Response)
                given()
                        .queryParam("access_token", token)
                .when()
                        .get(url)
                .then()
                        .log().all()
                        .statusCode(200)
                        .extract().response();
    }
}

