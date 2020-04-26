package com.tracker.outbreaktracker.controller;

import com.tracker.outbreaktracker.services.OutbreakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class DataController {

    @Autowired
    private  OutbreakService outbreakService;
    @GetMapping("/data")
    public String getData(Model model)
    {
        model.addAttribute("locationStats",outbreakService.getCountryStatsList());
        int totalReportedCases = outbreakService.getCountryStatsList().stream().mapToInt(stat->stat.getLastCount()).sum();
        model.addAttribute("totalcases",totalReportedCases);
        System.out.println(totalReportedCases+" here are the cases");
        return "home";
    }
}
