package com.bank.service.UserService;

import com.bank.dao.UserDAO.UserDAO;
import com.bank.model.UserModel.UserAccount;
import org.mindrot.jbcrypt.BCrypt;

public class UserService {
    private UserDAO userDAO =  new UserDAO();

    public UserAccount login(long account_Number, String password) throws Exception {
        UserAccount user = userDAO.getUserByAccountNumber(account_Number);

        if (user == null){
            throw new RuntimeException("Invalid Account Number");
        }
        if (!BCrypt.checkpw(password, user.getPasswordHash())) {
            throw new RuntimeException("Invalid Password");
        }
        return user;
    }
}
