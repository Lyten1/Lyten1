package com.controllers;


import com.dao.UserTypesDAO;
import com.models.Teacher;
import com.models.UserTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/usertypes")
public class UserTypesController {

    @Autowired
    private UserTypesDAO userTypesDAO;

    @PostMapping("/create")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody UserTypes userTypes) throws SQLException {
        this.userTypesDAO.save(userTypes);
    }

    @GetMapping(value = "/get/{request_data}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public UserTypes get(@PathVariable("request_data") String request_data) throws SQLException {
        return userTypesDAO.get(request_data);
    }

    @GetMapping(value = "/get/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public ArrayList<UserTypes> get() throws SQLException {
        return userTypesDAO.getAll();
    }

    @GetMapping(value = "/permit/{request_data}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public ArrayList<UserTypes> findByPermit(@PathVariable("request_data") String request_data) throws SQLException {
        return userTypesDAO.findByPermit(request_data);
    }

    @PatchMapping("/update/{request_data}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("request_data") String requestData, @RequestBody UserTypes userTypes) throws SQLException {
        userTypesDAO.update(userTypes, requestData);
    }

    @DeleteMapping( "/delete/{request_data}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("request_data") String requestData) throws SQLException {
        userTypesDAO.delete(requestData);
    }

}
