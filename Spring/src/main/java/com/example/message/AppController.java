package com.example.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin(origins="*")
public class AppController {

//    @Autowired
//    private TableRepository repo;

//    @Autowired
//    private TestRepository jRepo;
//
//
    @RequestMapping("/jenkinData")
    public String test2(Model model) {
        //List<ActualData> list = jRepo.findAll();
        //model.addAttribute("c",list );
        return "client";
    }

    @RequestMapping("/test")
    public String test(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "test";
    }

}
