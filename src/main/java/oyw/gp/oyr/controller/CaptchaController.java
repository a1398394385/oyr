package oyw.gp.oyr.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import oyw.gp.oyr.common.Captcha;
import oyw.gp.oyr.common.Sms;
import oyw.gp.oyr.entity.Response;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping(value = "/captcha")
public class CaptchaController {
    @Autowired
    HttpSession session;
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    /**
     * 获取图形验证码 Base64 值
     */
    @GetMapping(value = "/image")
    public Response<Object> getImageCaptcha() {
        session.setAttribute("captchaValue", Captcha.getValue(true));
        return Response.result(200, Captcha.getBase64Str());
    }

    /**
     * 验证用户输入的图形验证码值
     */
    @PostMapping(value = "/image")
    public Response<Object> verifyImageCaptcha(@RequestBody Map<String, String> request) {
        Sms sms = new Sms(redisTemplate);
        if (request.get("captchaValue").equals(session.getAttribute("captchaValue")))
            return Response.result(200, sms.getRequestAddress());
        else
            return Response.error(500, "验证码错误");
    }

    /**
     * 发送短信验证码
     *
     * @param requestAddress 序列化随机请求地址
     * @param phoneNumber    手机号
     */
    @GetMapping(value = "/sms/{requestAddress}/{phoneNumber}")
    public Response<Object> sendSMSCaptcha(@PathVariable String requestAddress, @PathVariable String phoneNumber) {
        Sms sms = new Sms(redisTemplate);
        if (!sms.verifyRequestAddress(requestAddress))
            return Response.error(400, "请求地址错误");
        JSONObject response = sms.send(phoneNumber, "OYR手机回收", "SMS_189615892");
        return Response.result(200, response);
    }

    /**
     * 验证短信验证码
     *
     * @param phoneNumber 手机号
     * @param code        验证码
     */
    @PostMapping(value = "/sms")
    public Response<Object> verifySMSCaptcha(@RequestBody Map<String, String> request) {
        Sms sms = new Sms(redisTemplate);
        if (sms.verifySMSCode(request.get("phoneNumber"), request.get("code")))
            return Response.result(200);
        return Response.error(500, "手机验证码错误");
    }
}
