package service.tag.testCase;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import service.department.api.DepartApi;
import service.tag.api.TagApi;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;

import static org.hamcrest.Matchers.equalTo;


public class TagTestCase {
    public static TagApi ta = new TagApi();
    public static DepartApi da = new DepartApi();

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
        ArrayList ob = (ArrayList) ta.tagList().body().path("taglist");
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

    public static Object[] publicTagElement(){
        /* 产生随机depart名称 */
        Random rr = new Random();
        StringBuilder departName = new StringBuilder();
        for(int i=0;i<15;i++){
            departName.append((char)(65 + rr.nextInt(26)));
        }
        Response daData = da.create(departName.toString());
        daData.then().body("errmsg", equalTo("created"));
        int daId = daData.body().path("id");
        Integer[] partylist = new Integer[1];
        partylist[0] = daId;
        // 创建tag
        Response rData = ta.create(departName.toString());
        rData.then().body("errmsg", equalTo("created"));
        int tagId = rData.body().path("tagid");
        // 标签添加元素--部门
        Response response = ta.addTagUsers(tagId, partylist);
        response.then().body("errmsg", equalTo("ok"));
        //
        Object[] allList = new Object[2];
        allList[0] = tagId;
        allList[1] = partylist;
        return allList;
    }

    @Test
    public void addTagElement(){
        //
        publicTagElement();
    }

    @Test
    public void deleteTagElement(){
        // tag添加element
        Object[] objectList = publicTagElement();
        int tagId = (Integer) objectList[0];
        Integer[] partylist = (Integer[]) objectList[1];
        // tag删除element
        Response rs = ta.delTagUsers(tagId, partylist);
        rs.then().log().all();
        rs.then().body("errmsg",equalTo("deleted"));
    }

    @Test
    public void deleteAllTag(){
        //
        Response rs = ta.tagList();
        ArrayList<LinkedHashMap> tl = (ArrayList<LinkedHashMap>) rs.body().path("taglist");
        for(LinkedHashMap i :tl){
            ta.delete(i.get("tagid").toString()).then().body("errmsg",equalTo("deleted"));
        }
    }

    @Test
    public void getTagList(){
        //
        ta.tagList().then().body("errmsg", equalTo("ok"));
    }
}
