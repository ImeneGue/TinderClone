package com.example.demo.controller;

import com.example.demo.Service.UserService;
import com.example.demo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AppController {
    @Autowired
    UserService service;
    @GetMapping("")
    public String pageAccueil (Model model){
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers",listUsers);
        return "index";
    }



//
//    @GetMapping("/login")
//    public String login(){
////        List<User> listUsers = service.listAll();
////        model.addAttribute("listUsers",listUsers);
//        return "login";
//    }
    @GetMapping("/forfaits")
    public String forfaits(){
//        List<User> listUsers = service.listAll();
//        model.addAttribute("listUsers",listUsers);
        return "forfaits";
    }
    @GetMapping("/apropos")
    public String apropos(){
//        List<User> listUsers = service.listAll();
//        model.addAttribute("listUsers",listUsers);
        return "apropos";
    }

//    @GetMapping("/profile")
//    public String monprofile(){
////        List<User> listUsers = service.listAll();
////        model.addAttribute("listUsers",listUsers);
//        return "profile";
//    }


}
