 
package com.wangxinenpu.springbootdemo.service;

import com.github.pagehelper.PageInfo;
import com.wangxinenpu.springbootdemo.dataobject.Anwswer;
import com.wangxinenpu.springbootdemo.dataobject.vo.Anwswer.AnwswerDeleteVO;
import com.wangxinenpu.springbootdemo.dataobject.vo.Anwswer.AnwswerDetailVO;
import com.wangxinenpu.springbootdemo.dataobject.vo.Anwswer.AnwswerListVO;
import com.wangxinenpu.springbootdemo.dataobject.vo.Anwswer.AnwswerSaveVO;


public interface AnwswerFacade{

	PageInfo<Anwswer> getAnwswerList(AnwswerListVO listVO);

    Anwswer getAnwswerDetail(AnwswerDetailVO detailVO);

    Integer saveAnwswer(AnwswerSaveVO saveVO);

    Integer deleteAnwswer(AnwswerDeleteVO deleteVO);

	 

}

 
