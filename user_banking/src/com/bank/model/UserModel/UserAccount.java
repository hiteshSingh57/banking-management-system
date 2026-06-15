package com.bank.model.UserModel;

public class UserAccount {
        private int userId  ;
        private String userName ;
        private long accountNumber;
        private  String passwordHash;

        public UserAccount(int userId, String userName, long accountNumber, String passwordHash) {
                this.userId = userId;
                this.userName = userName;
                this.accountNumber = accountNumber;
                this.passwordHash = passwordHash;
        }

        public int getUserId() { return userId; }
        public String getUserName() { return userName; }
        public long getAccountNumber() { return accountNumber; }
        public String getPasswordHash() { return passwordHash; }

}
