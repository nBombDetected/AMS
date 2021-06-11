package js.Controller;

import com.alibaba.fastjson.JSONObject;
import js.service.UserService;
import js.utils.ErrorException;
import js.utils.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AuthorizeController {
    @Autowired
    private UserService userService;

    @PostMapping("/verifyAuthority")
    @ResponseBody @Info
    public JSONObject verifyAuthority(@RequestBody JSONObject requestBody) throws ErrorException {
        JSONObject responseInfo = new JSONObject();
        String token = requestBody.getString("token");
        String resource = requestBody.getString("uri");  //需要验证的资源地址
        userService.verifyAuthority(token, resource);
        System.out.println("权限验证通过");
        return responseInfo;
    }
}
