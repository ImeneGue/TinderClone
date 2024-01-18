package com.example.demo.controller;


import com.example.demo.Service.UserService;
import com.example.demo.entities.AccountType;
import com.example.demo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
@Transactional
@Controller

public class UserController {
    @Autowired
    UserService service;

    @GetMapping("/signup")
    public String pageSignUp(Model model){
        model.addAttribute("user", new User());
        return "users_form";
    }


    @GetMapping("/users")
    public String afficherUtilisateur(Model model){
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers",listUsers);
        return "users";
    }


    @GetMapping("users/new")
    public String afficherFormulaireUtilisateur(Model model){
        List<AccountType> listAccountType = service.listAccountType();
        User user = new User();
//        utilisateur.setActive(true);
        model.addAttribute("user", user);
        model.addAttribute("listAccountType", listAccountType);
        return "users_form";
    }

    @PostMapping("/users/save")
    public String ajouterUtilisateur(User user, RedirectAttributes redirectAttributes, @RequestParam("fileImage") MultipartFile multipartFile){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        System.out.println("fileName " + fileName);
        user.setPhoto(fileName);
        service.save(user);
        return "registration_success";
    }



    @GetMapping("/login")
    public String login(Model model){
//        List<User> listUsers = service.listAll();
//        model.addAttribute("listUsers",listUsers);
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }


    @GetMapping("/usersadmin")
    public String afficherUtilisateursPourAdmin(Model model) {
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);
        return "usersadmin";

    }

    @GetMapping("/userspremium")
    public String afficherUtilisateursPourP(Model model) {
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);
        return "userspremium";
    }

    @RequestMapping("/userspremium/searchP")
    public String viewsearchresult(Model model, @Param("keyword") String keyword) {

        if(keyword!=null) {
            model.addAttribute("keyword", keyword);

            List<User> listUsers = service.listAllS(keyword);
            model.addAttribute("listUsers", listUsers);
        }else {
            List<User> listUsers = service.listAll();
            model.addAttribute("listUsers", listUsers);
        }
        return "userspremium";
    }
//    @RequestMapping("/userspremium/searchP")
//    public String search(User user, Model model, String keyword) {
//        model.addAttribute("keyword", keyword);
//
//        if(keyword!=null) {
//            List<User> listUsers = service.getByKeyword(keyword);
//            model.addAttribute("listUsers", listUsers);
//        }else {
//            List<User> listUsers = service.listAll();
//            model.addAttribute("listUsers", listUsers);}
//
//        return "redirect:/userspremium";
//    }

//    @GetMapping("/userspremiumResult")
//    public String afficherUtilisateursafterSearch(Model model) {
//        List<User> listUsers = service.listAll();
//        model.addAttribute("listUsers", listUsers);
//        return "redirect:/userspremium";
//
//
//    }

    @GetMapping("/userspremiumplus")
    public String afficherUtilisateursPourPP(Model model) {
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);
        return "userspremiumplus";


    }

    @GetMapping("/profile")
    public String afficherProfile(Model model) {
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);
        return "profile";
    }

//    @GetMapping("")
//    public String deleteUser(Model model) {
//        List<User> listUsers = service.deleteUser();
//        model.addAttribute("listUsers", listUsers);
//        return "profile";
//    }

    @RequestMapping(value = "/delete/{id}", method = GET)
    public String delete(Model model, @PathVariable("id") int id){
        List<User> listUsers = service.listAll();
        User user = service.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("user id : " + id));
        service.deleteById(id);
        model.addAttribute("listUsers", listUsers);

        return "redirect:/usersadmin";
    }


}