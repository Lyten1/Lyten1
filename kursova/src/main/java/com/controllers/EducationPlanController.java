package com.controllers;


import com.dao.EducationPlanDAO;
import com.models.EducationPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/educationplan")
public class EducationPlanController {

    @Autowired
    private EducationPlanDAO educationPlanDAO;

    @PostMapping("/create")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody EducationPlan educationPlan) throws SQLException {
        this.educationPlanDAO.save(educationPlan);
    }

    @GetMapping(value = "/get/{request_data}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public EducationPlan get(@PathVariable("request_data") String request_data) throws SQLException {
        return educationPlanDAO.get(request_data);
    }

    @GetMapping(value = "/get/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public ArrayList<EducationPlan> get() throws SQLException {
        return educationPlanDAO.getAll();
    }

    @GetMapping(value = "/speciality/{request_data}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public ArrayList<EducationPlan> findBySpeciality(@PathVariable("request_data") String request_data) throws SQLException {
        return educationPlanDAO.findBySpeciality(request_data);
    }

    @PatchMapping("/update/{request_data}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("request_data") String requestData, @RequestBody EducationPlan educationPlan) throws SQLException {
        educationPlanDAO.update(educationPlan, requestData);
    }

    @DeleteMapping( "/delete/{request_data}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("request_data") String requestData) throws SQLException {
        educationPlanDAO.delete(requestData);
    }

}
