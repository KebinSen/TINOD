package com.marsforce.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.marsforce.common.BaseService;
import com.marsforce.entity.User;
import com.marsforce.service.UserService;

@Controller
@SpringBootApplication
@RequestMapping("/test")
public class TestController {
	@Autowired
	private UserService userService;
	Logger logger = LoggerFactory.getLogger(TestController.class); // 注入Mapper<T>

	@RequestMapping("/showUser")
	public String toIndex(HttpServletRequest request, Model model) {
		logger.info("what");
		User user =null;
		if (request.getParameter("id") != null) {
			Long userId = Long.parseLong(request.getParameter("id"));
			user = this.userService.query(userId);
		} else {
			user = this.userService.query(1L);
		}
		model.addAttribute("user", user);
		return "Test";
	}
}