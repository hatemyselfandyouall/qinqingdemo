 
package com.wangxinenpu.springbootdemo.service;

import com.github.pagehelper.PageInfo;
import com.wangxinenpu.springbootdemo.dataobject.Choice;
import com.wangxinenpu.springbootdemo.dataobject.vo.Choice.ChoiceDeleteVO;
import com.wangxinenpu.springbootdemo.dataobject.vo.Choice.ChoiceDetailVO;
import com.wangxinenpu.springbootdemo.dataobject.vo.Choice.ChoiceListVO;
import com.wangxinenpu.springbootdemo.dataobject.vo.Choice.ChoiceSaveVO;


public interface ChoiceFacade{

	PageInfo<Choice> getChoiceList(ChoiceListVO listVO);

    Choice getChoiceDetail(ChoiceDetailVO detailVO);

    Integer saveChoice(ChoiceSaveVO saveVO);

    Integer deleteChoice(ChoiceDeleteVO deleteVO);

	 

}

 
