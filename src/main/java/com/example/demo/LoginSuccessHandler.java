package com.example.demo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.entities.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String redirectURL = request.getContextPath();

        if (userDetails.hasAccountType("admin")) {
            redirectURL = "usersadmin";
        } else if (userDetails.hasAccountType("premium")) {
            redirectURL = "userspremium";
        } else if (userDetails.hasAccountType("premiumplus")) {
            redirectURL = "userspremiumplus";
        }else {
            redirectURL = "index";

    }

        response.sendRedirect(redirectURL);

    }

}