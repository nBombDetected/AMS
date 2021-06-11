package js.utils;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/** 相应切面 */
@Aspect
@Component
public class ResponseAOP {

    @Pointcut("@annotation(Info)")
    public void responsePointCut(){}

    @Around("responsePointCut()")
    public JSONObject setRequestResult(ProceedingJoinPoint joinPoint) throws Throwable {
        JSONObject responseInfo = new JSONObject();
        try {
            responseInfo.putAll((JSONObject)joinPoint.proceed());
            responseInfo.put("code", 200);
            responseInfo.put("msg", "执行成功");
        } catch (ErrorException e){
            responseInfo.put("code", e.getError().getCode());
            responseInfo.put("msg", e.getError().getDescription());
        }
        return responseInfo;
    }
}
