package com.management.employee.controller;

import com.management.employee.enums.Role;
import com.management.employee.model.UserInfo;
import com.management.employee.service.UserInfoService;
import com.management.employee.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@Controller
public class LoginRegisterController {
    @Autowired
    private UserInfoService userService;

    @GetMapping("/login")
    public String login(Model model, @RequestParam(required = false) String message) {
        if(message != null)
            model.addAttribute("message", message);
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("register", new UserInfo());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("register") UserInfo register, BindingResult bindingResult, Model model,
                               HttpServletRequest req, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        String confirmPass = req.getParameter("password-confirm");

        if(register.getPassword() != null && !register.getPassword().equals(confirmPass)) {
            bindingResult.rejectValue("password", "password.required", "Password and Confirm Password not match.");
        }

        if(bindingResult.hasErrors()){
            return "register";
        }

        register.setRole(Role.USER);
        String fileName = "";
        if(multipartFile != null){
            fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            register.setPhotos(fileName);
        }

        register = userService.addUser(register);
        if(multipartFile != null){
            String uploadDir = "user-photos/" + register.getId();
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        }



        return "redirect:/login?message=Your account is under review." ;
    }
}
