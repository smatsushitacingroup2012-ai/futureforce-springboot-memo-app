package com.lesson.memo.controller;

import java.time.LocalDateTime;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lesson.memo.model.Admin;
import com.lesson.memo.repository.AdminRepository;



@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
	@GetMapping("/signup")
	public String signupForm(Model model) {

	    model.addAttribute("admin", new Admin());
		
	    return "signup";
	}
	
	@GetMapping("/signin")
	public String signinForm() {
	    return "signin";
	}

	@PostMapping("/signup")
    public String signup(@ModelAttribute @Valid Admin admin,
            BindingResult result,Model model){

		if (result.hasErrors()) {
	        return "signup";
	        }
	
		admin.setCreatedAt(LocalDateTime.now());
		admin.setUpdatedAt(LocalDateTime.now());
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		adminRepository.save(admin);
		
		return "redirect:/admin/signin";
    }

}