package com.lhw.interfaceManage.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lhw.interfaceManage.dao.InterfaceListDao;
import com.lhw.interfaceManage.entity.InterfaceList;

@Service("interfaceListService")
public class InterfaceListService {
	
	@Resource
	private InterfaceListDao interfaceListDao;
	public InterfaceList getOneInterface(int id){		
		return this.interfaceListDao.selectByPrimaryKey(id);	
	}
	
	public List<InterfaceList> getAllList(){
		return this.interfaceListDao.selectAllList();
	}
	
	public int addOneList(InterfaceList record){
		
		return this.interfaceListDao.insertSelective(record);
		
	}
	
	public List<InterfaceList> selectSomeByKeyword(String keyword){
		return this.interfaceListDao.selectSomeByKeyword(keyword);
	}
	
	public int deleteOneList(int id){
		return this.interfaceListDao.deleteByPrimaryKey(id);
	}
	
	public int deleteSomeList(int[] ids){
		
		return this.interfaceListDao.deleteSomeList(ids);
		
		
		
	}
	
	public int modifyOneList(InterfaceList record){
		
		return this.interfaceListDao.updateByPrimaryKey(record);
		
	}
	
	public List<InterfaceList> autoCompleteByKeyword(String keyword){
		
		return this.interfaceListDao.autoCompleteByKeyword(keyword);
		
	}

}
