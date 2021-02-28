package com.atguigu.smsservice.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

@Service
public class SmsService {
    /**
     * 发送短信
     */
    public boolean send(String phoneNumbers, String templeteCode, Map<String,Object> params){
        if(StringUtils.isEmpty(phoneNumbers)){
            return  false;
        }
        // todo
        DefaultProfile profile = DefaultProfile.getProfile("default","","");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setSysMethod(MethodType.POST);
        commonRequest.setSysDomain("dysmsapi.aliyuncs.com");
        commonRequest.setSysVersion("2021-02-27");
        commonRequest.setSysAction("SendSms");

        commonRequest.putQueryParameter("PhoneNumbers",phoneNumbers);
        //todo 签名
        commonRequest.putQueryParameter("SignName","");
        commonRequest.putQueryParameter("TempletCode",templeteCode);
        commonRequest.putQueryParameter("TempleParam", JSONObject.toJSONString(params));
        try {
            CommonResponse commonResponse = client.getCommonResponse(commonRequest);
            System.out.println(commonResponse.getData());
            return commonResponse.getHttpResponse().isSuccess();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return false;
    }
}
