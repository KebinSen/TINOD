/**
 * @copyright marsforce.com
 */
package com.marsforce.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marsforce.entity.User;
import com.marsforce.mapper.UserMapper;

/**
 * @author KebinSen
 * @date Feb 19, 2017 10:56:18 AM
 */
@Service
public class UserService {
	Logger logger = LoggerFactory.getLogger(UserService.class); // 注入Mapper<T>
	
	@Autowired
	private UserMapper mapper;
	
	public User query(Long id) {
		logger.info("and what");

		logger.info("FUCK" + mapper.selectByPrimaryKey(id).getClass().getName());
		return mapper.selectByPrimaryKey(id);
	}

}
