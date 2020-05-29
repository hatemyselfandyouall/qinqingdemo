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
@RequestMapping("choice")
@Api(tags ="选项管理")
@Slf4j
public class ChoiceController  {

    @Autowired
    ChoiceFacade choiceFacade;

    @ApiOperation(value = "选项列表")
    @RequestMapping(value = "/list",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResultVo<Choice> getChoiceList(@RequestBody ChoiceListVO choiceListVO){
        ResultVo resultVo=new ResultVo();
        try {
            PageInfo<Choice> choiceList=choiceFacade.getChoiceList(choiceListVO);
            if(choiceList!=null){
                DataListResultDto<Choice> dataListResultDto=new DataListResultDto<>(choiceList.getList(),(int)choiceList.getTotal());
                resultVo.setResult(dataListResultDto);
                resultVo.setSuccess(true);
            }else {
                resultVo.setResultDes("分页数据缺失");
            }
        }catch (Exception e){
            resultVo.setResultDes("获取选项列表异常");
            log.error("获取选项列表异常",e);
        }
        return resultVo;
    }

    @ApiOperation(value = "选项详情")
    @RequestMapping(value = "/detail",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResultVo<Choice> getChoiceDetail(@RequestBody ChoiceDetailVO choiceDetailVO){
        ResultVo resultVo=new ResultVo();
        try {
        Choice choice=choiceFacade.getChoiceDetail(choiceDetailVO);
        if(choice!=null){
            resultVo.setResult(choice);
            resultVo.setSuccess(true);
        }else {
            resultVo.setResultDes("获取详情失败");
        }
        } catch (Exception e){
        resultVo.setResultDes("获取选项详情异常");
        log.error("获取选项详情异常",e);
    }
        return resultVo;
    }

    @ApiOperation(value = "选项保存")
    @RequestMapping(value = "/save",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResultVo<Choice> saveChoice(@RequestBody ChoiceSaveVO choiceSaveVO){
        ResultVo resultVo=new ResultVo();
        try {
            Integer flag = choiceFacade.saveChoice(choiceSaveVO);
            if (1 == flag) {
                resultVo.setResultDes("选项保存成功");
                resultVo.setSuccess(true);
            } else {
                resultVo.setResultDes("选项保存失败");
            }
        }catch (Exception e){
                resultVo.setResultDes("接口保存异常");
                log.error("选项保存异常",e);
            }
        return resultVo;
    }

    @ApiOperation(value = "选项删除")
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE,produces = {"application/json;charset=UTF-8"})
    public ResultVo<Choice> deleteChoice(@RequestBody ChoiceDeleteVO choiceDeleteVO){
        ResultVo resultVo=new ResultVo();
        try {
            Integer flag = choiceFacade.deleteChoice(choiceDeleteVO);
            if (1 == flag) {
                resultVo.setResultDes("选项删除成功");
                resultVo.setSuccess(true);
            } else {
                resultVo.setResultDes("选项删除失败");
            }
        }catch (Exception e){
            resultVo.setResultDes("选项删除异常");
            log.error("选项删除异常",e);
        }
        return resultVo;
    }


}
