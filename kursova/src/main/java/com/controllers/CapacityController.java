package com.controllers;

import com.dao.AccountDAO;
import com.dao.CapacityDAO;
import com.models.Account;
import com.models.Capacity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/capacity")
public class CapacityController {
    @Autowired
    private CapacityDAO capacityDAO;

    @PostMapping("/create")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Capacity capacity) throws SQLException {
        this.capacityDAO.save(capacity);
    }

    @GetMapping(value = "/get/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.FOUND)
    public ArrayList<Capacity> get() throws SQLException {
        return capacityDAO.getAll();
    }

    @GetMapping(value = "/get/{request_data}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.FOUND)
    public Capacity get(@PathVariable("request_data") String requestData) throws SQLException {
        return capacityDAO.get(requestData);
    }

    @PatchMapping("/update/{request_data}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("request_data") String requestData, @RequestBody Capacity capacity) throws SQLException {
        capacityDAO.update(capacity, requestData);
    }


    @DeleteMapping("/delete/{request_data}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("request_data") String requestData) throws SQLException {
        capacityDAO.delete(requestData);
    }

}
