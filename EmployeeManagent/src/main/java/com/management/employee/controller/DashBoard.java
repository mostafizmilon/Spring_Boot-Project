package com.management.employee.controller;

import com.management.employee.enums.Role;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashBoard {

    @GetMapping("/")
    public String getAllUser(SecurityContextHolder auth) {
        final Role[] role = {null};
        auth.getContext().getAuthentication().getAuthorities().forEach(grantedAuthority -> {
           if(grantedAuthority.getAuthority().equals(Role.ADMIN.name()))
               role[0] = Role.ADMIN;
        });
        
        if(role[0] != null){
            return "redirect:/employee";
        }
        return "redirect:/user";
    }


}
