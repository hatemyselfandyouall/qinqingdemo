package com.wangxinenpu.springbootdemo.controller;

import com.github.pagehelper.PageInfo;
import com.wangxinenpu.springbootdemo.dataobject.vo.root.ResultVo;
import com.wangxinenpu.springbootdemo.service.AnwswerFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.wangxinenpu.springbootdemo.dataobject.Anwswer;
import com.wangxinenpu.springbootdemo.dataobject.vo.Anwswer.AnwswerDeleteVO;
import com.wangxinenpu.springbootdemo.dataobject.vo.Anwswer.AnwswerDetailVO;
import com.wangxinenpu.springbootdemo.dataobject.vo.Anwswer.AnwswerListVO;
import com.wangxinenpu.springbootdemo.dataobject.vo.Anwswer.AnwswerSaveVO;


@RestController
@RequestMapping("anwswer")
@Api(tags ="管理")
@Slf4j
public class AnwswerController  {

    @Autowired
    AnwswerFacade anwswerFacade;

    @ApiOperation(value = "列表")
    @RequestMapping(value = "/list",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResultVo<Anwswer> getAnwswerList(@RequestBody AnwswerListVO anwswerListVO){
        ResultVo resultVo=new ResultVo();
        try {
            PageInfo<Anwswer> anwswerList=anwswerFacade.getAnwswerList(anwswerListVO);
            if(anwswerList!=null){
                DataListResultDto<Anwswer> dataListResultDto=new DataListResultDto<>(anwswerList.getList(),(int)anwswerList.getTotal());
                resultVo.setResult(dataListResultDto);
                resultVo.setSuccess(true);
            }else {
                resultVo.setResultDes("分页数据缺失");
            }
        }catch (Exception e){
            resultVo.setResultDes("获取列表异常");
            log.error("获取列表异常",e);
        }
        return resultVo;
    }

    @ApiOperation(value = "详情")
    @RequestMapping(value = "/detail",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResultVo<Anwswer> getAnwswerDetail(@RequestBody AnwswerDetailVO anwswerDetailVO){
        ResultVo resultVo=new ResultVo();
        try {
        Anwswer anwswer=anwswerFacade.getAnwswerDetail(anwswerDetailVO);
        if(anwswer!=null){
            resultVo.setResult(anwswer);
            resultVo.setSuccess(true);
        }else {
            resultVo.setResultDes("获取详情失败");
        }
        } catch (Exception e){
        resultVo.setResultDes("获取详情异常");
        log.error("获取详情异常",e);
    }
        return resultVo;
    }

    @ApiOperation(value = "保存")
    @RequestMapping(value = "/save",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public ResultVo<Anwswer> saveAnwswer(@RequestBody AnwswerSaveVO anwswerSaveVO){
        ResultVo resultVo=new ResultVo();
        try {
            Integer flag = anwswerFacade.saveAnwswer(anwswerSaveVO);
            if (1 == flag) {
                resultVo.setResultDes("保存成功");
                resultVo.setSuccess(true);
            } else {
                resultVo.setResultDes("保存失败");
            }
        }catch (Exception e){
                resultVo.setResultDes("接口保存异常");
                log.error("保存异常",e);
            }
        return resultVo;
    }

    @ApiOperation(value = "删除")
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE,produces = {"application/json;charset=UTF-8"})
    public ResultVo<Anwswer> deleteAnwswer(@RequestBody AnwswerDeleteVO anwswerDeleteVO){
        ResultVo resultVo=new ResultVo();
        try {
            Integer flag = anwswerFacade.deleteAnwswer(anwswerDeleteVO);
            if (1 == flag) {
                resultVo.setResultDes("删除成功");
                resultVo.setSuccess(true);
            } else {
                resultVo.setResultDes("删除失败");
            }
        }catch (Exception e){
            resultVo.setResultDes("删除异常");
            log.error("删除异常",e);
        }
        return resultVo;
    }


}
