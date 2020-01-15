package service.tag.testCase;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import service.tag.api.TagApi;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;

import static org.hamcrest.Matchers.equalTo;


public class TagTestCase {
    TagApi ta = new TagApi();

    @Test
    public void createTag(){
        // 产生随机tag名称
        Random rr = new Random();
        StringBuilder tagName = new StringBuilder();
        for(int i=0;i<15;i++){
            tagName.append((char)(65 + rr.nextInt(52)));
        }
        // 创建tag
        Response rData = ta.create(tagName.toString());
        rData.then().body("errmsg", equalTo("created"));
        int tagId = rData.body().path("tagid");
        // 查询tag是否创建成功
        Response tl = ta.tagList().then().log().all().extract().response();
        ArrayList ob = tl.body().path("taglist");
        Boolean result = false;
        for(Object i : ob){
            LinkedHashMap aa = (LinkedHashMap) i;
            String apiTagName = (String) aa.get("tagname");
            if(apiTagName.equals(tagName.toString())){
                result = true;
            }
        }
        Assert.assertTrue(result);
    }

    @Test
    public void updateTag(){
        // 产生随机tag名称
        Random rr = new Random();
        StringBuilder tagName = new StringBuilder();
        for(int i=0;i<15;i++){
            tagName.append((char)(65 + rr.nextInt(52)));
        }
        // 创建tag
        Response rData = ta.create(tagName.toString());
        rData.then().body("errmsg", equalTo("created"));
        int tagId = rData.body().path("tagid");
        // 更新tag
        StringBuilder newTagName = new StringBuilder();
        for(int i=0;i<15;i++) {
            newTagName.append((char) (65 + rr.nextInt(52)));
        }
        ta.update(newTagName.toString(), tagId)
                .then()
                .log().all()
                .body("errmsg", equalTo("updated"));
    }

    @Test
    public void deleteTag(){
        // 产生随机tag名称
        Random rr = new Random();
        StringBuilder tagName = new StringBuilder();
        for(int i=0;i<15;i++){
            tagName.append((char)(65 + rr.nextInt(52)));
        }
        // 创建tag
        Response rData = ta.create(tagName.toString());
        rData.then().body("errmsg", equalTo("created"));
        int tagId = rData.body().path("tagid");
        // 删除tag
        ta.delete(String.valueOf(tagId))
                .then()
                .log().all()
                .body("errmsg", equalTo("deleted"));
    }

    @Test
    public void getTagElement(){
        // 产生随机tag名称
        Random rr = new Random();
        StringBuilder tagName = new StringBuilder();
        for(int i=0;i<15;i++){
            tagName.append((char)(65 + rr.nextInt(52)));
        }
        // 创建tag
        Response rData = ta.create(tagName.toString());
        rData.then().body("errmsg", equalTo("created"));
        int tagId = rData.body().path("tagid");
        // 查询tag成员
        ta.getTagElement(String.valueOf(tagId))
                .then()
                .log().all()
                .body("tagname",equalTo(tagName.toString()));
    }

    @Test
    public void addTagElement(){

    }

    @Test
    public void deleteTagElement(){

    }

    @Test
    public void getTagList(){

    }
}
