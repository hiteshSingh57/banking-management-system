package com.bank.model.UserModel;

public class Customer {
        private String createdAt;
        private String accountNumber;
        private String name;
        private double amount;
        private String mobile;
        private String status;

        // Constructor
        public Customer(String createdAt, String accountNumber, String mobile, String fullName, String status, double amount) {
            this.createdAt = createdAt;
            this.accountNumber = accountNumber;
            this.name = fullName;
            this.amount = amount;
            this.mobile = mobile;
            this.status = status;
        }

    // Getters
        public String getCreatedAt() { return createdAt; }
        public String getAccountNumber() { return accountNumber; }
        public String getName() { return name; }
        public String getStatus() { return status; }
        public String getMobile() { return mobile; }
        public double getAmount() { return amount; }
}
