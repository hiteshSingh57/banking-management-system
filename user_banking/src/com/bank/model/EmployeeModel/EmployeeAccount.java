package com.bank.model.EmployeeModel;

public class EmployeeAccount {
    private int employeeId;
    private String name;
    private String passwordHash;

    public EmployeeAccount(int employeeId, String name, String passwordHash ) {
        this.employeeId = employeeId;
        this.name = name;
        this.passwordHash = passwordHash;
    }

    public int getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public String getPasswordHash() { return passwordHash;}
}
