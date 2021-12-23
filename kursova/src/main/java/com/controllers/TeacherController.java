package com.controllers;

import com.dao.TeacherDAO;
import com.models.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherDAO teacherDAO;

    @PostMapping("/create")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Teacher teacher) throws SQLException {
        this.teacherDAO.save(teacher);
    }

    @GetMapping(value = "/get/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.FOUND)
    public ArrayList<Teacher> get() throws SQLException {
        return teacherDAO.getAll();
    }

    @GetMapping(value = "/get/{request_data}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public Teacher get(@PathVariable("request_data") String request_data) throws SQLException {
        return teacherDAO.get(request_data);
    }

    @GetMapping(value = "/subject/{request_data}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public ArrayList<Teacher> findBySubject(@PathVariable("request_data") String request_data) throws SQLException {
        return teacherDAO.findBySubject(request_data);
    }

    @PatchMapping("/update/{request_data}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("request_data") String requestData, @RequestBody Teacher teacher) throws SQLException {
        teacherDAO.update(teacher, requestData);
    }


    @DeleteMapping("/delete/{request_data}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("request_data") String requestData) throws SQLException {
        teacherDAO.delete(requestData);
    }
}
