package com.wangxinenpu.springbootdemo.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.wangxinenpu.springbootdemo.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.UUID;

@Component
@Slf4j
public class TokenCompoent {

    @Value("${getTokenUrl}")
    private String getTokenUrl;
    @Value("${appKey}")
    private String appKey;
    @Value("${appSecret}")
    private String appSecret;

    private static String paramString="{\"target\":\"getToken\"}";

    public static String createSign(JSONObject parameters,String  appSecret){
        parameters.put("secret",appSecret);
        String result=parameters.toString();
        System.out.println(result);
        log.info(result);
        String signature = MD5Util.md5Password(result).toUpperCase();
        parameters.remove("secret");
        return signature;
    }

    public  String getToken()throws Exception{
        JSONObject haeder=new JSONObject(true);
        haeder.put("appKey",appKey);
        haeder.put("time",new Date().getTime()+"");
        haeder.put("nonceStr", UUID.randomUUID().toString().replaceAll("-",""));//随机字符串
        JSONObject param=JSONObject.parseObject(paramString, Feature.OrderedField);
        param.putAll(haeder);
        String signature = createSign(param,appSecret);
        System.out.println(signature);
        haeder.put("signature",signature);
        param=getParamWithoutsignatureParam(param);
        ResponseEntity<ResultVo> resultVoResponseEntity=postTest(haeder,param,getTokenUrl,ResultVo.class);
        if (HttpStatus.OK.equals(resultVoResponseEntity.getStatusCode())&&resultVoResponseEntity.getBody().isSuccess()){
            String token=resultVoResponseEntity.getBody().getResult()+"";
            return token;
        }else {
            throw new Exception("获取token异常，返回为"+resultVoResponseEntity);
        }
    }

    public  JSONObject getParamWithoutsignatureParam(JSONObject params) {
        params.remove("appKey");
        params.remove("time");
        params.remove("nonceStr");
        params.remove("signatureType");
        params.remove("signature");
        return params;
    }

    public static <T> ResponseEntity<T> postTest(JSONObject haeder, Object paramJson,String testUrl,Class clazz) {
        RestTemplate restTemplate=new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        haeder.keySet().forEach(
                i->{
                    requestHeaders.add(i,haeder.get(i).toString()!=null?haeder.get(i).toString():"");
                }
        );
        requestHeaders.add("Content-Type", "application/json");
        //body
        //HttpEntity
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity(paramJson, requestHeaders);
        ResponseEntity<T>  result= restTemplate.postForEntity(testUrl,requestEntity,clazz);
        System.out.println(result);
        return result;
    }


}
