package oyw.gp.oyr.common;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class Sms {
    /**
     * AccessKey
     */
    private final static String ACCESS_KEY_ID = "LTAI4G87JhHdG5g2a9FPMTJh";

    /**
     * AccessKeySecret
     */
    private final static String ACCESS_KEY_SECRET = "AcQiU4Dar2cLOrKLPurFDw5Zo7RmXn";

    /**
     * RegionId
     */
    private static final String REGION_ID = "cn-hangzhou";

    /**
     * Expiry time - minutes
     */
    private static final int TIMEOUT = 5;

    /**
     * redis
     */
    private ValueOperations<String, String> redis;

    /**
     * @param redisTemplate
     */
    public Sms(RedisTemplate<String, String> redisTemplate) {
        this.redis = redisTemplate.opsForValue();
    }

    /**
     * send messages
     * 
     * @param phoneNumber  手机号
     * @param signName     签名名称
     * @param templateCode 模版CODE
     * @return JSONObject
     */
    public JSONObject send(String phoneNumber, String signName, String templateCode) {
        JSONObject result = new JSONObject();
        DefaultProfile profile = DefaultProfile.getProfile(REGION_ID, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        Map<String, Object> templateParam = new HashMap<>();
        templateParam.put("code", getSMSCode(phoneNumber));

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", new JSONObject(templateParam).toJSONString());
        try {
            CommonResponse response = client.getCommonResponse(request);
            result = JSON.parseObject(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 获取发送手机验证码的随机请求地址
     */
    public String getRequestAddress() {
        String requestAddress = Long.toString(System.currentTimeMillis());
        redis.set(requestAddress, "1", Duration.ofMinutes(1));
        return requestAddress;
    }

    /**
     * 验证请求地址随机值段
     * 
     * @param requestAddress 请求地址随机值段
     * @return 当请求地址有效时返回 true
     */
    public Boolean verifyRequestAddress(String requestAddress) {
        return redis.get(requestAddress) != null;
    }

    /**
     * 获取手机验证码
     * 
     * @param phoneNumber
     * @return code
     */
    private String getSMSCode(String phoneNumber) {
        String code = Public.randomCode();
        redis.set(phoneNumber, code, Duration.ofMinutes(TIMEOUT));
        return code;
    }

    /**
     * 验证手机验证码
     * 
     * @param phoneNumber
     * @param code
     * @return 当验证码正确时返回 true
     */
    public Boolean verifySMSCode(String phoneNumber, String code) {
        return code != null && code.equals(redis.get(phoneNumber));
    }
}
