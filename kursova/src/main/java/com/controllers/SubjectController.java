package com.controllers;

import com.dao.SubjectDAO;
import com.dao.TeacherDAO;
import com.models.Subject;
import com.models.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectDAO subjectDAO;

    @PostMapping("/create")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Subject subject) throws SQLException {
        this.subjectDAO.save(subject);
    }

    @GetMapping(value = "/get/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.FOUND)
    public ArrayList<Subject> get() throws SQLException {
        return subjectDAO.getAll();
    }

    @GetMapping(value = "/get/{request_data}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public Subject get(@PathVariable("request_data") String request_data) throws SQLException {
        return subjectDAO.get(request_data);
    }

    @PatchMapping("/update/{request_data}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("request_data") String requestData, @RequestBody Subject subject) throws SQLException {
        subjectDAO.update(subject, requestData);
    }

    @DeleteMapping("/delete/{request_data}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("request_data") String requestData) throws SQLException {
        subjectDAO.delete(requestData);
    }

}
