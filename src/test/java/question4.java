import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;


//Get email id from response
public class question4 {

    @Test(description = "Fetch email id from response")
    public void getEmail(){
        Response response = RestAssured.given().get("https://reqres.in/api/users?page=2");

        int sizeOfData = response.jsonPath().getList("data").size();

        for (int i=0;i<sizeOfData;i++)
        System.out.println(response.jsonPath().get("data["+i+"].email").toString());
    }
}
