package testdemo;

import com.sun.org.apache.xpath.internal.operations.Equals;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PinterApiTest {
    @Test
    public void getPhone(){
        given()
                .queryParam("id",1)
                .log().all()
                .when()
                .get("http://82.156.74.26:9088/pinter/com/phone/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("message", equalTo("success"))
                .body("data.cpuCore",equalTo("8核"))
                .body("data.id",greaterThanOrEqualTo(1));  //lessThanOrEqualTo数值型比较<=1,greaterThanOrEqualTo数值型比较>=1
                /*
                .body("message", equalTo("success"),        //断言body中message是否是success
                        "data.cpuCore",equalTo("8核"));      //方法2：断言多组数据加上逗号
                 */
    }

    @Test
   public void deletePhone()
   {
       String json="{\"brand\":\"Huawei\",\"color\":\"yellow\",\"memorySize\":\"64G\",\"cpuCore\":\"8核\",\"price\":\"8848\",\"desc\":\"全新上市\"}";
       given()
               .contentType("application/json")
               .body(json)
               .log().all()
        .when()
                .delete("http://82.156.74.26:9088/pinter/com/phone")
        .then()
            .log().all()
            .statusCode(200);

   }

    @Test
    public void putPhone()
    {
        String json="{\"brand\":\"Huawei\",\"color\":\"yellow\",\"memorySize\":\"64G\",\"cpuCore\":\"8核\",\"price\":\"8848\",\"desc\":\"全新上市\"}";
        given()
                .contentType("application/json")
                .body(json)
                .log().all()
         .when()
                .put("http://82.156.74.26:9088/pinter/com/phone")
         .then()
                .log().all()
                .statusCode(200);

    }
   @Test
  public void  getUserList()
    {
        given()
              //  .queryParam("id",1)
                .log().all()
                .when()
                .get("http://82.156.74.26:9088/pinter/com/userList?genderType=0")
                .then()
                .log().all()
                .statusCode(200)
                .body("data.userName",hasItems("码同学学员0","码同学学员1"))//断言包含数组元素
                .body("data.userName",hasSize(10));                     //断言个数
    }
}
