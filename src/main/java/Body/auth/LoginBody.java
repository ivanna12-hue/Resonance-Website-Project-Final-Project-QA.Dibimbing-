package Body.auth;

import org.json.JSONObject;
import Utils.ConfigReader;

public class LoginBody {

    public JSONObject loginData() {
        JSONObject body = new JSONObject();
        body.put("usernameOrEmail", ConfigReader.getProperty("email"));
        body.put("password", ConfigReader.getProperty("password"));
        return body;
    }
}
