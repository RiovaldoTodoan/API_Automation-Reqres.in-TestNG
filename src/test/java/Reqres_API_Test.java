import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.HashMap;


public class Reqres_API_Test {

    @Test
    //Get Single User
    void getsingleuser (){
        Response response =   get("https://reqres.in/api/users/10");
        System.out.println("Response : "+response.asString());
        System.out.println("Status Code : "+response.getStatusCode());
        System.out.println("Body : "+response.getBody().asString());}

    @Test
    //Get Single User with Invalid ID > 10
    void getsingleuserinvalidId (){
        Response response =   get("https://reqres.in/api/users/100");
        System.out.println("Response : "+response.asString());
        System.out.println("Status Code : "+response.getStatusCode());
        System.out.println("Body : "+response.getBody().asString());}

    @Test
    // Get Single User with Char ID
    void getsingleuserCharId () {
        Response response =   get("https://reqres.in/api/users/AA");
        System.out.println("Response : "+response.asString());
        System.out.println("Status Code : "+response.getStatusCode());
        System.out.println("Body : "+response.getBody().asString());}

    @Test
    // Get Single User with Special Char
    void getsingleUserwithSpecChar () {
        Response response =   get("https://reqres.in/api/users/@@");
        System.out.println("Response : "+response.asString());
        System.out.println("Status Code : "+response.getStatusCode());
        System.out.println("Body : "+response.getBody().asString());}


    @Test
        // Get Single User
    void GetListUser () {
        Response response =   get("https://reqres.in/api/users?page=1");
        System.out.println("Response : "+response.asString());
        System.out.println("Status Code : "+response.getStatusCode());
        System.out.println("Body : "+response.getBody().asString());}


    @Test
    void CreateUser (){
        //with hashmap

        HashMap data =new HashMap();

        data.put("name","Eltodoan");
        data.put("job", "Striker");

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name",equalTo ("Eltodoan"))
                .body("job", equalTo ("Striker"))
                .header("Content-Type","application/json; charset=utf-8");}


    //Negative Case
    @Test
    void createUserwithEmptyValue (){
        HashMap data =new HashMap();
        data.put("name","");
        data.put("job", "");
        given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://reqres.in/api/users/10")
                .then()
                .statusCode(201);}

    @Test
    void UpdateUser () {
        HashMap data = new HashMap();
        data.put("name", "Eltodoan");
        data.put("job", "Striker");

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .patch("https://reqres.in/api/users/12")
                .then()
                .statusCode(200)
                .body("name", equalTo("Eltodoan"))
                .body("job", equalTo("Striker"))
                .header("Content-Type", "application/json; charset=utf-8");}

    @Test
    void DeleteUser (){
        when()
                .delete("https://reqres.in/api/users/1").
                then().
                statusCode(204)
                .log().all();}




}




