package com.company.employee_management_system.controller;


import com.company.employee_management_system.config.AdminCredential;
import com.company.employee_management_system.model.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private Admin admin;



    LoginController() {
        admin = new Admin();
    }

    @GetMapping
    public String somePage(Model model) {
        // Determine if content should be shown or not
         // replace with your actual condition logic

        // check if the admin is still valid

        model.addAttribute("adminDetails", getAdminInController());


        return "index";
    }

    @GetMapping("/logout")
    public String logoutAdmin(Model model) {
        // Determine if content should be shown or not
        // replace with your actual condition logic
        admin = new Admin();
        AdminCredential.setAdminCredentials(null, null);
        setAdminInController(admin);
        return "redirect:/";
    }

    // method to login
    @PostMapping("/")
    public String loginNow(@ModelAttribute("adminDetails") Admin alreadyAdmin){
    AdminCredential.setAdminCredentials(AdminCredential.ADMIN_NAME, AdminCredential.ADMIN_PASSWORD);
    Admin loginAdmin = AdminCredential.getAdminCredentials();
    admin = new Admin();

    if(loginAdmin.equals(alreadyAdmin)){
         admin = alreadyAdmin;
    }
        setAdminInController(admin);
        return "redirect:/";
    }


    // this is where the admin is actually set
    // sets and get the values
    private Admin getAdminInController(){
        return admin;
    }

    private void setAdminInController(Admin admin) {
        this.admin = admin;
    }





}
