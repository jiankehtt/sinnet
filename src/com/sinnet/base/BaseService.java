package com.sinnet.base;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.log4j.Logger;

import com.sinnet.base.exception.AppException;
import com.sinnet.utils.BeanUtils;


public class BaseService<Bean, PK, Mapper extends BaseMapper<Bean, PK>> {

	private Mapper mapper;
	protected Logger logger = Logger.getLogger(getClass());
	
	//取得泛型类型     
    @SuppressWarnings("unchecked")
	protected Class<Mapper> typeClass() {     
        return (Class<Mapper>) ((ParameterizedType) getClass()     
                .getGenericSuperclass()).getActualTypeArguments()[2];     
    }
    
    protected Mapper getMapper(){
    	if(mapper == null){
    		mapper = BeanUtils.getBean(typeClass());
    	}
    	return mapper;
    }
	
	/**
	 * 
	 * 标题: queryPage 
	 * 描述: 分页 
	 * @param conditions
	 * @param page
	 */
	public List<Bean> queryPage(List<SearchCondition> conditions, PageBean page){
		return getMapper().queryPage(conditions, page);
	}
	
	/**
	 * 
	 * 标题: getById 
	 * 描述: 根据主键来获取数据 
	 * @param id
	 * @return
	 */
	public Bean getById(PK id){
		return getMapper().selectByPrimaryKey(id);
	}
	
	/**
	 * 
	 * 标题: save 
	 * 描述: 保存或者更新信息 
	 * @param bean 数据
	 * @param isInsert 如果是添加则输入true，否则输入false
	 * @throws AppException
	 */
	public void save(Bean bean, boolean isInsert) throws AppException{
		try {
			if(isInsert){
				getMapper().insert(bean);
			}else{
				getMapper().updateByPrimaryKey(bean);
			}
		} catch (Exception e) {
			logger.error("添加或者修改失败！", e);
			throw new AppException((isInsert ? "添加" : "更新") + "失败！");
		}
	}
	
	/**
	 * 
	 * 标题: save 
	 * 描述: 保存或者更新局部信息 
	 * @param bean 数据
	 * @param isInsert 如果是添加则输入true，否则输入false
	 * @throws AppException
	 */
	public void saveSelective(Bean bean, boolean isInsert) throws AppException{
		try {
			if(isInsert){
				getMapper().insertSelective(bean);
			}else{
				getMapper().updateByPrimaryKeySelective(bean);
			}
		} catch (Exception e) {
			logger.error("添加或者修改失败！", e);
			throw new AppException((isInsert ? "添加" : "更新") + "失败！");
		}
	}
	
	/**
	 * 
	 * 标题: deletById 
	 * 描述: 根据主键来删除信息 
	 * @param id
	 * @throws AppException
	 */
	public void deleteById(PK id) throws AppException{
		try {
			getMapper().deleteByPrimaryKey(id);
		} catch (Exception e) {
			logger.error("删除失败！", e);
			throw new AppException("删除失败！");
		}
	}
	
	public List<Bean> loadAll(){
		return getMapper().selectAll();
	}
}
