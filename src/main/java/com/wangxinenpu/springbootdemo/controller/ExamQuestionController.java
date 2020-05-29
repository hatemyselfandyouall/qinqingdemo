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
@RequestMapping("examQuestion")
@Api(tags ="题目-试卷关联管理")
@Slf4j
public class ExamQuestionController  {

    @Autowired
    ExamQuestionFacade examQuestionFacade;

    @ApiOperation(value = "题目-试卷关联列表")
    @RequestMapping(value = "/list",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResultVo<ExamQuestion> getExamQuestionList(@RequestBody ExamQuestionListVO examQuestionListVO){
        ResultVo resultVo=new ResultVo();
        try {
            PageInfo<ExamQuestion> examQuestionList=examQuestionFacade.getExamQuestionList(examQuestionListVO);
            if(examQuestionList!=null){
                DataListResultDto<ExamQuestion> dataListResultDto=new DataListResultDto<>(examQuestionList.getList(),(int)examQuestionList.getTotal());
                resultVo.setResult(dataListResultDto);
                resultVo.setSuccess(true);
            }else {
                resultVo.setResultDes("分页数据缺失");
            }
        }catch (Exception e){
            resultVo.setResultDes("获取题目-试卷关联列表异常");
            log.error("获取题目-试卷关联列表异常",e);
        }
        return resultVo;
    }

    @ApiOperation(value = "题目-试卷关联详情")
    @RequestMapping(value = "/detail",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResultVo<ExamQuestion> getExamQuestionDetail(@RequestBody ExamQuestionDetailVO examQuestionDetailVO){
        ResultVo resultVo=new ResultVo();
        try {
        ExamQuestion examQuestion=examQuestionFacade.getExamQuestionDetail(examQuestionDetailVO);
        if(examQuestion!=null){
            resultVo.setResult(examQuestion);
            resultVo.setSuccess(true);
        }else {
            resultVo.setResultDes("获取详情失败");
        }
        } catch (Exception e){
        resultVo.setResultDes("获取题目-试卷关联详情异常");
        log.error("获取题目-试卷关联详情异常",e);
    }
        return resultVo;
    }

    @ApiOperation(value = "题目-试卷关联保存")
    @RequestMapping(value = "/save",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResultVo<ExamQuestion> saveExamQuestion(@RequestBody ExamQuestionSaveVO examQuestionSaveVO){
        ResultVo resultVo=new ResultVo();
        try {
            Integer flag = examQuestionFacade.saveExamQuestion(examQuestionSaveVO);
            if (1 == flag) {
                resultVo.setResultDes("题目-试卷关联保存成功");
                resultVo.setSuccess(true);
            } else {
                resultVo.setResultDes("题目-试卷关联保存失败");
            }
        }catch (Exception e){
                resultVo.setResultDes("接口保存异常");
                log.error("题目-试卷关联保存异常",e);
            }
        return resultVo;
    }

    @ApiOperation(value = "题目-试卷关联删除")
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE,produces = {"application/json;charset=UTF-8"})
    public ResultVo<ExamQuestion> deleteExamQuestion(@RequestBody ExamQuestionDeleteVO examQuestionDeleteVO){
        ResultVo resultVo=new ResultVo();
        try {
            Integer flag = examQuestionFacade.deleteExamQuestion(examQuestionDeleteVO);
            if (1 == flag) {
                resultVo.setResultDes("题目-试卷关联删除成功");
                resultVo.setSuccess(true);
            } else {
                resultVo.setResultDes("题目-试卷关联删除失败");
            }
        }catch (Exception e){
            resultVo.setResultDes("题目-试卷关联删除异常");
            log.error("题目-试卷关联删除异常",e);
        }
        return resultVo;
    }


}
