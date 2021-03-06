package com.zyz.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.zyz.dto.User;
import com.zyz.dto.UserQueryCondition;
import com.zyz.exception.BusinessException;
import com.zyz.validator.CheckType;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Created by yizhi on 2018-08-26.
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private ProviderSignInUtils providerSignInUtils;

	@PostMapping("/regist")
	public void regist(User user, HttpServletRequest request) {
		// 注册用户
		providerSignInUtils.doPostSignUp(user.getUsername(), new ServletWebRequest(request));
	}

	@GetMapping("/me")
	public Object getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
		return userDetails;
	}

	@GetMapping
	@JsonView(User.UserSimpleView.class)
	@ApiOperation(value = "用户查询服务")
	public List<User> query(UserQueryCondition condition,
	                        @PageableDefault(page = 2, size = 17, sort = "username asc") Pageable pageable) {
		List<User> users = new ArrayList<>();

		System.out
				.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));

		System.out.println(pageable);

		users.add(new User());
		users.add(new User());
		users.add(new User());

		return users;
	}

	@GetMapping("/{id:\\d+}")
	@JsonView(User.UserDetailView.class)
	public User getInfo(@ApiParam("用户id") @PathVariable String id) {
		if (id.equals("100")) {
			throw new BusinessException("user not exist");
		}
		if (id.equals("200")) {
			throw new RuntimeException("user is illegal");
		}
		System.out.println("进入getInfo服务");
		User user = new User();
		user.setUsername("tom");
		return user;
	}

	@PostMapping
	public User create(@Validated(CheckType.CreateCheck.class) @RequestBody User user) {
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());

		user.setId("1");
		return user;
	}

	@PutMapping("/{id:\\d+}")
	public User update(@Valid @RequestBody User user, BindingResult errors) {
		if (errors.hasErrors()) {
			errors.getAllErrors().stream()
					.forEach(error -> {
						System.out.println(error.getDefaultMessage());
					});
		}

		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());

		user.setId("1");
		return user;
	}

	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable String id) {
		System.out.println("删除ID为:" + id);
	}
}
