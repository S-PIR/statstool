package com.egartech.edu.statstool.controller;

import com.egartech.edu.statstool.domain.Instrument;
import com.egartech.edu.statstool.repos.DailyStatsEntityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.HashMap;

@Controller
@RequestMapping
public class MainController {
    private final DailyStatsEntityRepo repo;

    @Autowired
    public MainController(DailyStatsEntityRepo repo) {
        this.repo = repo;
    }

    @GetMapping
    public String main(Model model) {
        HashMap<Object, Object> data = new HashMap<>();
        data.put("stats", repo.findAll());
        data.put("instruments", Instrument.values());
        model.addAttribute("frontendData", data);
        return "index";
    }
}
