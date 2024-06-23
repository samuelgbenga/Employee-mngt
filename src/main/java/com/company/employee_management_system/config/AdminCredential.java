package com.company.employee_management_system.config;

import com.company.employee_management_system.model.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AdminCredential {
    private static Admin admin = new Admin();
    public static final String ADMIN_NAME = "Samuel";
    public static final String ADMIN_PASSWORD = "password";

    public static Admin getAdminCredentials() {
        return admin;
    }

    public static void setAdminCredentials() {

        admin = new Admin("admin@admin", "1234");

    }

    public static void setAdminCredentials(String userName, String password) {

        admin = new Admin(userName, password);

    }

}
