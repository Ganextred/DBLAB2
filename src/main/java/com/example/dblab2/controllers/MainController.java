package com.example.dblab2.controllers;

import com.example.dblab2.data.dto.Apartment;
import com.example.dblab2.data.dto.Status;
import com.example.dblab2.data.dto.User;
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
    public String handleQuery0(@RequestParam("id0") Long id,
                                Model model) {

        return repository.findUserById(id).toString();
    }

    @GetMapping("/query1")
    @ResponseBody
    public String handleQuery1(@RequestParam("status") String status,
                               @RequestParam("start_date") String startDate,
                               @RequestParam("end_date") String endDate,
                               Model model) {
        List<Integer> apartments = repository.query1(LocalDate.parse(startDate), LocalDate.parse(endDate), status);
        return apartments.toString();
    }

    @GetMapping("/query2")
    @ResponseBody
    public String handleQuery2(@RequestParam("username") String username,
                               Model model) {
        List<Integer> apartments = repository.query2(username);
        return apartments.toString();
    }

    @GetMapping("/query3")
    @ResponseBody
    public String handleQuery3(Model model) {
        List<String> apartments = repository.query3();
        return apartments.toString();
    }

    @GetMapping("/query4")
    @ResponseBody
    public String handleQuery4(@RequestParam("apartmentId") Integer apartmentId,
                               Model model) {
        List<Integer> apartments = repository.query4(apartmentId);
        return apartments.toString();
    }

    @GetMapping("/query5")
    @ResponseBody
    public String handleQuery5(@RequestParam("username") String username,
                               Model model) {
        List<String> apartments = repository.query5(username);
        return apartments.toString();
    }


}
