package com.bank.model.EmployeeModel;

public class Employee {
    private int employeeId;
    private String name;
    private String role;
    private String status;
    private String email;
    private String phone;
    public Employee(int employeeId, String name, String role,
                           String status, String email, String phone) {
        this.employeeId = employeeId;
        this.name = name;
        this.role = role;
        this.status = status;
        this.email = email;
        this.phone = phone;
    }

    public int getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public String getRole() { return role; }
    public String getStatus() { return status; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
}
