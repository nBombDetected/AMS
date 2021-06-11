package js.Controller;

import com.alibaba.fastjson.JSONObject;
import js.entity.User;
import js.service.UserService;
import js.utils.ErrorException;
import js.utils.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    @ResponseBody @Info
    public JSONObject login(@RequestBody JSONObject requestBody) throws ErrorException {
        JSONObject responseInfo = new JSONObject();
        User user = new User(requestBody.getString("name"), requestBody.getString("password"));
        userService.login(user, responseInfo);
        return responseInfo;
    }
}
