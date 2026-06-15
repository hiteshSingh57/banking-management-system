package com.bank.service.UserService;

import com.bank.dao.UserDAO.CustomerDAO;
import com.bank.model.UserModel.Customer;

import java.util.List;

public class CustomerService {

    private CustomerDAO dao = new CustomerDAO();

    public List<Customer> fetchAllCustomers() {
        return dao.getAllCustomers();
    }

    public Customer searchCustomer(String accNo) {
        if (accNo == null || accNo.isEmpty()) {
            return null;
        }
        return dao.getCustomerByAccount(accNo);
    }
}