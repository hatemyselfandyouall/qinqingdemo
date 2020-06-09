package com.wangxinenpu.springbootdemo.controller;

import com.github.pagehelper.PageInfo;
import com.wangxinenpu.springbootdemo.util.TokenCompoent;
import com.wangxinenpu.springbootdemo.vo.ResultVo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "token")
public class TokenController {

    @Autowired
    TokenCompoent tokenCompoent;

    @ApiOperation(value = "获取token")
    @RequestMapping(value = "/getToken",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    public ResultVo<String> getQuestionList(){
        ResultVo resultVo=new ResultVo();
        try {
            String token=tokenCompoent.getToken();
            resultVo.setSuccess(true);
            resultVo.setResult(token);
        }catch (Exception e){
            resultVo.setResultDes("获取token异常");
            log.error("获取token异常",e);
        }
        return resultVo;
    }
}
