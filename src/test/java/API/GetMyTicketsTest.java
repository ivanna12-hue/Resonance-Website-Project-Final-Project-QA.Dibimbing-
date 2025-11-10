package API;

import Utils.DriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import Utils.ConfigReader;
import java.io.IOException;
import static io.restassured.RestAssured.given;

public class GetMyTicketsTest {


    @Test
    public void testGetMyTickets() throws IOException {
        RestAssured.baseURI = ConfigReader.getProperty("baseUrl");


        // Gunakan token hasil dari LoginTest
        String token = DriverManager.getToken();
        System.out.println("====== "+token);
        Assert.assertNotNull(token, "Token should not be null");

        Response response = given()
                .header("accept", "application/json")
                .header("Cookie","__Host-next-auth.csrf-token=fec10dc645ff6f2b0b8d66eb2556d2f5c87e2a985fc8fb9de026272df27cf8c5%7Cc78850cfeba882c9eeb1996437f5ece16dd7f620849360e69abed706f99f9dae; __Secure-next-auth.callback-url=https%3A%2F%2Fresonance.dibimbing.id; __Secure-next-auth.session-token=eyJhbGciOiJkaXIiLCJlbmMiOiJBMjU2R0NNIn0..4N5FoI8t6m44K5rg.uMBADmPjZpBjipvNofOckKAu_Rj85GqBRJnPjecqAj2Kgb6ZUaFgsW3pSAjhfLShGlayV4OASEt0fk1apMiQqoikRdvyitb4CpF97301yQom_62LD6vNxOzzYMKSVvWa-oHjXyFGv36BLEvYuw4KVPWM0btQcv4d4Su2Ut1DPcdFeBksgUzqlvOCm2t3Wk4_MasvPHaNu0viOis-nkqEkua32ee7ovxrWrbflIT5Vv-k7vd8Vvg57c6kl7bG-FWD90pxEatnFA1Exz_bhu8tx3mIJFwROA.pdK7mVhI2OTSnVXcxx8Y4A")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/api/rest/activeTickets?date=2025-10-12&order=VOTE")
                .then()
                .extract().response();

        System.out.println("Response: " + response.asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 200, "error");
        Assert.assertTrue(response.asString().contains("title"), "Not found");

    }
}
