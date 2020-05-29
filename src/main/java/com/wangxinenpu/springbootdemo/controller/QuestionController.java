package com.wangxinenpu.springbootdemo.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import star.vo.result.ResultVo;



@RestController
@RequestMapping("question")
@Api(tags ="题目管理")
@Slf4j
public class QuestionController  {

    @Autowired
    QuestionFacade questionFacade;

    @ApiOperation(value = "题目列表")
    @RequestMapping(value = "/list",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResultVo<Question> getQuestionList(@RequestBody QuestionListVO questionListVO){
        ResultVo resultVo=new ResultVo();
        try {
            PageInfo<Question> questionList=questionFacade.getQuestionList(questionListVO);
            if(questionList!=null){
                DataListResultDto<Question> dataListResultDto=new DataListResultDto<>(questionList.getList(),(int)questionList.getTotal());
                resultVo.setResult(dataListResultDto);
                resultVo.setSuccess(true);
            }else {
                resultVo.setResultDes("分页数据缺失");
            }
        }catch (Exception e){
            resultVo.setResultDes("获取题目列表异常");
            log.error("获取题目列表异常",e);
        }
        return resultVo;
    }

    @ApiOperation(value = "题目详情")
    @RequestMapping(value = "/detail",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResultVo<Question> getQuestionDetail(@RequestBody QuestionDetailVO questionDetailVO){
        ResultVo resultVo=new ResultVo();
        try {
        Question question=questionFacade.getQuestionDetail(questionDetailVO);
        if(question!=null){
            resultVo.setResult(question);
            resultVo.setSuccess(true);
        }else {
            resultVo.setResultDes("获取详情失败");
        }
        } catch (Exception e){
        resultVo.setResultDes("获取题目详情异常");
        log.error("获取题目详情异常",e);
    }
        return resultVo;
    }

    @ApiOperation(value = "题目保存")
    @RequestMapping(value = "/save",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResultVo<Question> saveQuestion(@RequestBody QuestionSaveVO questionSaveVO){
        ResultVo resultVo=new ResultVo();
        try {
            Integer flag = questionFacade.saveQuestion(questionSaveVO);
            if (1 == flag) {
                resultVo.setResultDes("题目保存成功");
                resultVo.setSuccess(true);
            } else {
                resultVo.setResultDes("题目保存失败");
            }
        }catch (Exception e){
                resultVo.setResultDes("接口保存异常");
                log.error("题目保存异常",e);
            }
        return resultVo;
    }

    @ApiOperation(value = "题目删除")
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE,produces = {"application/json;charset=UTF-8"})
    public ResultVo<Question> deleteQuestion(@RequestBody QuestionDeleteVO questionDeleteVO){
        ResultVo resultVo=new ResultVo();
        try {
            Integer flag = questionFacade.deleteQuestion(questionDeleteVO);
            if (1 == flag) {
                resultVo.setResultDes("题目删除成功");
                resultVo.setSuccess(true);
            } else {
                resultVo.setResultDes("题目删除失败");
            }
        }catch (Exception e){
            resultVo.setResultDes("题目删除异常");
            log.error("题目删除异常",e);
        }
        return resultVo;
    }


}
