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
@RequestMapping("exam")
@Api(tags ="试卷管理")
@Slf4j
public class ExamController  {

    @Autowired
    ExamFacade examFacade;

    @ApiOperation(value = "试卷列表")
    @RequestMapping(value = "/list",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResultVo<Exam> getExamList(@RequestBody ExamListVO examListVO){
        ResultVo resultVo=new ResultVo();
        try {
            PageInfo<Exam> examList=examFacade.getExamList(examListVO);
            if(examList!=null){
                DataListResultDto<Exam> dataListResultDto=new DataListResultDto<>(examList.getList(),(int)examList.getTotal());
                resultVo.setResult(dataListResultDto);
                resultVo.setSuccess(true);
            }else {
                resultVo.setResultDes("分页数据缺失");
            }
        }catch (Exception e){
            resultVo.setResultDes("获取试卷列表异常");
            log.error("获取试卷列表异常",e);
        }
        return resultVo;
    }

    @ApiOperation(value = "试卷详情")
    @RequestMapping(value = "/detail",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResultVo<Exam> getExamDetail(@RequestBody ExamDetailVO examDetailVO){
        ResultVo resultVo=new ResultVo();
        try {
        Exam exam=examFacade.getExamDetail(examDetailVO);
        if(exam!=null){
            resultVo.setResult(exam);
            resultVo.setSuccess(true);
        }else {
            resultVo.setResultDes("获取详情失败");
        }
        } catch (Exception e){
        resultVo.setResultDes("获取试卷详情异常");
        log.error("获取试卷详情异常",e);
    }
        return resultVo;
    }

    @ApiOperation(value = "试卷保存")
    @RequestMapping(value = "/save",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResultVo<Exam> saveExam(@RequestBody ExamSaveVO examSaveVO){
        ResultVo resultVo=new ResultVo();
        try {
            Integer flag = examFacade.saveExam(examSaveVO);
            if (1 == flag) {
                resultVo.setResultDes("试卷保存成功");
                resultVo.setSuccess(true);
            } else {
                resultVo.setResultDes("试卷保存失败");
            }
        }catch (Exception e){
                resultVo.setResultDes("接口保存异常");
                log.error("试卷保存异常",e);
            }
        return resultVo;
    }

    @ApiOperation(value = "试卷删除")
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE,produces = {"application/json;charset=UTF-8"})
    public ResultVo<Exam> deleteExam(@RequestBody ExamDeleteVO examDeleteVO){
        ResultVo resultVo=new ResultVo();
        try {
            Integer flag = examFacade.deleteExam(examDeleteVO);
            if (1 == flag) {
                resultVo.setResultDes("试卷删除成功");
                resultVo.setSuccess(true);
            } else {
                resultVo.setResultDes("试卷删除失败");
            }
        }catch (Exception e){
            resultVo.setResultDes("试卷删除异常");
            log.error("试卷删除异常",e);
        }
        return resultVo;
    }


}
