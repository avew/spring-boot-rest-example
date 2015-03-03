package com.aseprojali.web.service;

import com.aseprojali.domain.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by avew on 3/4/15.
 */
public interface UserService {
    public Iterable<User> all();

    public User create(@RequestBody User user, HttpServletRequest request, HttpServletResponse response);

    public User findById(@PathVariable Long id);

    public void delete(@PathVariable Long id);

    public User update(@PathVariable Long id, @RequestBody User user);
}