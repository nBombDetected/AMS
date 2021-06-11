package js.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class AuthorityFilter extends ZuulFilter {
    @Autowired
    RestTemplate restTemplate;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return -2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext rc = RequestContext.getCurrentContext();

        HttpServletResponse response = rc.getResponse();
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:18888");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");

        HttpServletRequest request = rc.getRequest();

        log.debug("-----经过权限验证过滤器");
        log.debug("请求路径：{}", request.getRequestURI());
        log.debug(request.getHeader("withCredentials"));
        // 判断是否需要进行权限验证
        if(request.getMethod().equals("OPTIONS")
                ||request.getRequestURI().equals("/user/verifyAuthority")
                ||request.getRequestURI().equals("/user/login")
                ||request.getHeader("withCredentials").equals("false")
                ||rc.getResponseStatusCode()==200) {
            log.debug("权限不需要验证");
            log.debug("-----权限验证完毕");
            System.out.println();
            return null;
        }

        log.debug("权限需要验证");
        //获取权限验证路径和token
        JSONObject requestBody = new JSONObject();
        requestBody.put("uri", request.getServletPath());
        requestBody.put("token", request.getHeader("X-user-token"));
        requestBody.put("userId", request.getHeader("X-user-id"));

        //进行权限验证并获得返回信息
        JSONObject responseInfo = new JSONObject();
        responseInfo = restTemplate
                .postForObject("http://localhost:18080/user/verifyAuthority", requestBody, JSONObject.class);


        //验证不通过时终止请求
        System.out.println(responseInfo.getInteger("code"));
        if(responseInfo.getInteger("code")!=200) {
            rc.setResponseBody(responseInfo.toJSONString());
            rc.getResponse().setContentType("application/json; charset=utf-8");
            rc.setSendZuulResponse(false);
        } else {
            log.debug("权限验证通过");
            log.debug("-----权限验证完毕");
        }
        return null;
    }
}
