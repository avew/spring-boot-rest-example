package com.aseprojali.web.rest;

import com.aseprojali.domain.User;
import com.aseprojali.repository.UserRepository;
import com.aseprojali.web.service.UserService;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

/**
 * Created by avew on 3/3/15.
 */

/**
 * Rest Controller for managing users*
 */
@RestController
@RequestMapping("/api")
public class UserController implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;


    /**
     * Get All user
     *
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @Timed
    @Override
    public Iterable<User> all() {
        log.debug("REST request to get all users");
        return userRepository.findAll();
    }

    /**
     * Create user
     *
     * @param user
     * @param request
     * @param response
     */
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @Timed
    @Override
    public User create(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        log.debug("REST request to create users");
        Long newId = user.getId();
        String requestUrl = request.getRequestURL().toString();
        URI uri = new UriTemplate("requestUrl/{id}").expand(requestUrl, newId);
        response.setHeader("Location", uri.toASCIIString());

        return userRepository.save(user);

    }


    /**
     * Get user by id
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    @Timed
    @Override
    public User findById(@PathVariable Long id) {
        log.debug("REST request to find user by id {id} ", id);
        return userRepository.findOne(id);
    }


    /**
     * Delete user by id
     *
     * @param id
     */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @Timed
    @Override
    public void delete(@PathVariable Long id) {
        userRepository.delete(id);
    }


    /**
     * Update user by id
     * * @param id
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Timed
    @Override
    public User update(@PathVariable Long id, @RequestBody User user) {
        User u = userRepository.findOne(id);
        if (u == null) {
            throw new IllegalStateException("No User wiht id " + id);
        }
        user.setId(u.getId());
        return userRepository.save(user);

    }

}
