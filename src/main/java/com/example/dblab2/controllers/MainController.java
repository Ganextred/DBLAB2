package com.example.dblab2.controllers;

import com.example.dblab2.data.dto.Apartment;
import com.example.dblab2.data.dto.Status;
import com.example.dblab2.data.repository.Repository;
import com.google.common.math.Quantiles;
import org.apache.commons.math3.stat.descriptive.rank.Percentile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    Repository repository;

    @GetMapping("/")
    public String greeting(Model model) {
        return "index";
    }

    @GetMapping("/query0")
    @ResponseBody
    public String executeQuery1(@RequestParam("id0") Long id,
                                Model model) {

        return repository.findUserById(id).toString();
    }

    @GetMapping("/query1")
    @ResponseBody
    public String handleQuery1(@RequestParam("status") String status,
                               @RequestParam("start_date") String startDate,
                               @RequestParam("end_date") String endDate,
                               Model model) {
        List<Apartment> apartments = repository.query1(LocalDate.parse(startDate), LocalDate.parse(endDate), status);
        return apartments.toString();
    }
}
