package com.sinnet.base;

import java.util.List;

public interface BaseMapper<Bean, PK> {

	public List<Bean> queryPage(List<SearchCondition> conditions, PageBean page);

	public Bean selectByPrimaryKey(PK id);

	public List<Bean> selectAll();

	public int insert(Bean bean);

	public int updateByPrimaryKey(Bean bean);

	public void deleteByPrimaryKey(PK id);
	
	public int insertSelective(Bean bean);

    public int updateByPrimaryKeySelective(Bean bean);
}
