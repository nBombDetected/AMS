package js.service;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import js.entity.User;
import js.repo.UserRepo;
import js.utils.ErrorEnum;
import js.utils.ErrorException;
import js.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    /** 用户登录 */
    public void login(User user, JSONObject responseInfo) throws ErrorException {
        User userInDB = userRepo.findByName(user.getName());
        if(userInDB == null) throw new ErrorException(ErrorEnum.UNAUTHORIZED);

        String passwordOfUserInDB =userInDB.getPassword();

        if(!passwordOfUserInDB.equals(user.getPassword())) throw new ErrorException(ErrorEnum.UNAUTHORIZED);
        userInDB.setToken();

        responseInfo.put("token", userInDB.getToken());
        responseInfo.put("userRealName", userInDB.getRealName());
        responseInfo.put("userId", userInDB.getId());
    }

    /** 验证令牌的权限 */
    public void verifyAuthority(String token, String resource) throws ErrorException {
        DecodedJWT decodedJWT;
        try {
            decodedJWT = JWTUtils.verify(token);
        } catch (Exception e) {
            throw new ErrorException(ErrorEnum.UNSPECIFIED);
        }

        int userId = Integer.parseInt(decodedJWT.getClaim("id").asString());

        User user = userRepo.findById(userId).orElseThrow(()->new ErrorException(ErrorEnum.UNSPECIFIED));
        if(user.isAuthorizedTo(resource)) return;

        throw new ErrorException(ErrorEnum.FORBIDDEN);
    }
}
