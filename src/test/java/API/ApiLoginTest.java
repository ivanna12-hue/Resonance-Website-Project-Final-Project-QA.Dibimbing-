package API;
import Body.auth.LoginBody;
import Utils.DriverManager;
import io.restassured.RestAssured;
import Utils.ConfigReader;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.FileWriter;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class ApiLoginTest {
    public static String token;

    @Test
    public void Login() throws IOException {
        // Set base URI dari ConfigReader
        RestAssured.baseURI = ConfigReader.getProperty("baseUrl");

        // Buat body login
        LoginBody loginBody = new LoginBody();

        // Kirim request POST ke endpoint login
        Response response = given()
                .header("Content-Type", "application/json")
                .body(loginBody.loginData().toString())
                .when()
                .post("/api/rest/login") // endpoint
                .then()
                .extract().response();

        // Print response
        System.out.println("Response: " + response.asString());

        // Assert status code 200
        Assert.assertEquals(response.getStatusCode(), 200);

        // Validasi token
        String token = response.jsonPath().getString("token");
        DriverManager.setToken(token);
        Assert.assertFalse(token.isEmpty(), "Token should not be empty");
        System.out.println("Token: " + token);

        // Validasi id
        String id = response.jsonPath().getString("user.id");
        Assert.assertEquals(id, "cmgksgqeb0000l5046cja2gt6", "id does not match");

        // Simpan token ke file resources/json/token.json
        JSONObject tokenJson = new JSONObject();
        tokenJson.put("token", token);
        tokenJson.put("id", id);

        try (FileWriter file = new FileWriter("src/resources/json/token.json")) {
            file.write(tokenJson.toString(4)); // 4 = indentation
            file.flush();
        }

        System.out.println("Success save in resources/json/token.json");
    }
}
