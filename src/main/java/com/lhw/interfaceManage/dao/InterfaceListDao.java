package com.lhw.interfaceManage.dao;

import java.util.List;

import com.lhw.interfaceManage.entity.InterfaceList;

public interface InterfaceListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(InterfaceList record);

    int insertSelective(InterfaceList record);

    InterfaceList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InterfaceList record);

    int updateByPrimaryKey(InterfaceList record);
    List<InterfaceList> selectAllList();
    
    List<InterfaceList> selectSomeByKeyword(String keyword);
    int deleteSomeList(int[] id);
    
    List<InterfaceList> autoCompleteByKeyword(String keyword);
}