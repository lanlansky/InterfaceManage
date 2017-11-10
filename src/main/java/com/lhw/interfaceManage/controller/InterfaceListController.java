package com.lhw.interfaceManage.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.json.Json;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhw.interfaceManage.entity.InterfaceList;
import com.lhw.interfaceManage.service.InterfaceListService;
import com.lhw.test.TestMyBatis;

@Controller
@RequestMapping("/interfaceList")
public class InterfaceListController {
	private static Logger logger = Logger.getLogger(InterfaceListController.class); 
	@Resource
	private InterfaceListService interfaceListService;
	
	@RequestMapping("/getAll")
	@ResponseBody
	public String getAllList(HttpServletRequest request){
		
		List<InterfaceList> lists=this.interfaceListService.getAllList();
		JSONArray objs = new JSONArray();
		for (InterfaceList list : lists) {
			JSONObject obj = new JSONObject();
			obj.put("id", list.getId());
			obj.put("keyword", list.getKeyword());
			obj.put("description", list.getDescription());
			obj.put("URL", list.getUrl());
			obj.put("paras", list.getParas());
			obj.put("remark", list.getRemark());
			objs.put(obj);
		}
		return objs.toString();	
	}
	
	@RequestMapping(value="/addOne",method= RequestMethod.POST)
	@ResponseBody
	public String addOneList(HttpServletRequest request){
		
		logger.info("*****************addOneList");
		
		String keyword=request.getParameter("addKeyword");
		String description=request.getParameter("addDescription");
		String URL=request.getParameter("addURL");
		String paras=request.getParameter("addParas");
		String remark=request.getParameter("addRemark");
		
		InterfaceList list=new InterfaceList();
		list.setKeyword(keyword);
		list.setDescription(description);
		list.setUrl(URL);
		list.setParas(paras);
		list.setRemark(remark);
		
		int result=this.interfaceListService.addOneList(list);
		//得到自插入的ID
		//int id=list.getId();
		
		JSONObject obj = new JSONObject();		
		obj.put("result", result==1?"success":"false");
		obj.put("id", list.getId());
		
		
		return obj.toString();	
	}
	@RequestMapping(value="/selectSome/{keyword}",method= RequestMethod.POST)
	@ResponseBody
	public String selectSomeByKeyword(@PathVariable("keyword") String keyword,HttpServletRequest request){
		List<InterfaceList> lists=null;
		logger.info("***********selectSomeByKeyword");
		logger.info(keyword);
		if(keyword.equals("all")){
			lists=this.interfaceListService.getAllList();
		}
		else{
			lists=this.interfaceListService.selectSomeByKeyword(keyword);
		}
		JSONArray objs = new JSONArray();
		for (InterfaceList list : lists) {
			JSONObject obj = new JSONObject();
			obj.put("id", list.getId());
			obj.put("keyword", list.getKeyword());
			obj.put("description", list.getDescription());
			obj.put("URL", list.getUrl());
			obj.put("paras", list.getParas());
			obj.put("remark", list.getRemark());
			objs.put(obj);
		}
		return objs.toString();
		
	}
	
	
	
	@RequestMapping(value="/autoComplete",method= RequestMethod.POST)
	@ResponseBody
	public String autoCompleteByKeyword(HttpServletRequest request){
		List<InterfaceList> lists=null;
		logger.info("***********autoComplete");
		
		String keyword=request.getParameter("keyword");
		logger.info(keyword);
		
		
		lists=this.interfaceListService.autoCompleteByKeyword(keyword);	
		
		JSONArray objs = new JSONArray();
		for (InterfaceList list : lists) {
			JSONObject obj = new JSONObject();
			obj.put("id", list.getId());
			obj.put("value", list.getKeyword());
			obj.put("label", list.getDescription());			
			objs.put(obj);			
		}	
		
		return objs.toString();
		
		/*
		String keyword=request.getParameter("keyword");
		logger.info(keyword);
		
		
		lists=this.interfaceListService.autoCompleteByKeyword(keyword);	
	
		JSONArray objs = new JSONArray();
		for (InterfaceList list : lists) {
			JSONObject obj = new JSONObject();
			
			obj.put("title", list.getKeyword());
					
			objs.put(obj);			
		}	
		

		
		return objs.toString();
		*/
		
		
	}
	
	@RequestMapping(value="/deleteOne/{id}")
	@ResponseBody
	public String deleteOneList(@PathVariable("id") int id,HttpServletRequest request){
		List<InterfaceList> lists=null;
		logger.info("***********deleteOneList");
		logger.info(id);
		
		int result=this.interfaceListService.deleteOneList(id);
		
		
		return result==1?"success":"false";
		
	}
	
	@RequestMapping(value="/deleteSomeByID")
	@ResponseBody
	public String deleteSomeList(HttpServletRequest request){
		List<InterfaceList> lists=null;
		logger.info("***********deleteSomeList");
		
		String data=request.getParameter("ids");
		logger.info(data);
		String[] ids=data.split("-");
		int[] id=new int[ids.length];
		for(int i=0;i<ids.length;i++){
			id[i]=Integer.parseInt(ids[i]);
		}
		
		int result=this.interfaceListService.deleteSomeList(id);
		
		
		return result==ids.length?"success":"false";
		
	}
	
	
	@RequestMapping(value="/modifyOne",method= RequestMethod.POST)
	@ResponseBody
	public String modifyOneList(HttpServletRequest request){
		
		logger.info("*****************modifyOneList");
		int id=Integer.parseInt(request.getParameter("data-id"));
		String keyword=request.getParameter("keyword");
		String description=request.getParameter("description");
		String URL=request.getParameter("URL");
		String paras=request.getParameter("paras");
		String remark=request.getParameter("remark");
		
		InterfaceList list=new InterfaceList();
		list.setId(id);
		list.setKeyword(keyword);
		list.setDescription(description);
		list.setUrl(URL);
		list.setParas(paras);
		list.setRemark(remark);
		
		int result=this.interfaceListService.modifyOneList(list);
		
		
		
		
		
		return result==1?"success":"false";	
	}
	
	
	

}
