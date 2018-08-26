package com.zyz.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.zyz.dto.User;
import com.zyz.dto.UserQueryCondition;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yizhi on 2018-08-26.
 */
@RestController
@RequestMapping("/user")
public class UserController {

  @GetMapping
  @JsonView(User.UserSimpleView.class)
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
  public User getInfo(@PathVariable String id) {
    User user = new User();
    user.setUsername("tom");
    return user;
  }

  @PostMapping
  public User create(@Valid @RequestBody User user, BindingResult errors) {
    if (errors.hasErrors()) {
      errors.getAllErrors().stream()
          .forEach(error -> System.out.println(error.getDefaultMessage()));
    }

    System.out.println(user.getId());
    System.out.println(user.getUsername());
    System.out.println(user.getPassword());
    System.out.println(user.getBirthday());

    user.setId("1");
    return user;
  }
}
