package com.marsforce.common;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;

/**
 * @author KebinSen
 * @param <T>
 * @date Feb 19, 2017 10:22:05 AM
 * @param <T>
 */
public abstract class BaseService<T extends BaseEntity> {
	// public abstract Mapper<T> getMapper();

	Logger logger = LoggerFactory.getLogger(BaseService.class); // 注入Mapper<T>
	@Autowired
	private MyMapper<T> mapper;

	/**
	 * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
	 * 
	 * @param id
	 * @return
	 */
	public T query(Long id) {
		logger.info("and what");

		logger.info("FUCK" + mapper.selectByPrimaryKey(id).getClass().getName());
		return mapper.selectByPrimaryKey(id);
	}

	/**
	 * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
	 * 
	 * @param record
	 * @return
	 */
	public T query(T record) {
		return mapper.selectOne(record);
	}

	/**
	 * 查询全部结果
	 * 
	 * @return
	 */
	public List<T> queryList() {
		return mapper.selectAll();
	}

	/**
	 * 根据实体中的属性值进行查询，查询条件使用等号
	 * 
	 * @param record
	 * @return
	 */
	public List<T> queryList(T record) {
		if (record.getPage() != null && record.getRows() != null) {
			PageHelper.startPage(record.getPage(), record.getRows());
		}
		return mapper.select(record);
	}

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageInfo<T> queryList(Integer page, Integer rows) {
		// 设置分页条件
		PageHelper.startPage(page, rows);
		List<T> list = this.queryList();
		return new PageInfo<T>(list);
	}

	/**
	 * 存一个实体，null的属性也会保存，不会使用数据库默认值
	 * 
	 * @param record
	 * @return
	 */
	public Integer save(T record) {
		return mapper.insert(record);
	}

	/**
	 * 保存一个实体，null的属性不会保存，会使用数据库默认值
	 * 
	 * @param record
	 * @return
	 */
	public Integer saveSelective(T record) {
		return mapper.insertSelective(record);
	}

	/**
	 * 修改数据，返回成功的条数
	 * 
	 * @param record
	 * @return
	 */
	public Integer update(T record) {
		return mapper.updateByPrimaryKey(record);
	}

	/**
	 * 修改数据，使用不为null的字段，返回成功的条数
	 * 
	 * @param record
	 * @return
	 */
	public Integer updateSelective(T record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 根据主键字段进行删除，方法参数必须包含完整的主键属性
	 * 
	 * @param id
	 * @return
	 */
	public Integer delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 * 
	 * @param clazz
	 * @param property
	 * @param values
	 * @return
	 */
	public Integer delete(Class<T> clazz, String property, List<Object> values) {
		Example example = new Example(clazz);
		example.createCriteria().andIn(property, values);
		return mapper.deleteByExample(example);
	}

	/**
	 * 根据条件做删除
	 * 
	 * @param record
	 * @return
	 */
	public Integer delete(T record) {
		return mapper.delete(record);
	}

}