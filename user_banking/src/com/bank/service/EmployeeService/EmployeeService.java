package com.bank.service.EmployeeService;

import com.bank.dao.EmployeeDAO.EmployeeDAO;
import com.bank.model.EmployeeModel.EmployeeAccount;
import org.mindrot.jbcrypt.BCrypt;

public class EmployeeService {
    private EmployeeDAO emm_dao = new EmployeeDAO();

    public EmployeeAccount login(String employee_id, String pass) throws Exception {
        EmployeeAccount emm = emm_dao.getEmployeeByEmployeeEmail(employee_id);

        if (emm == null)
            throw new RuntimeException("Invalid Employee ID");
        if (!BCrypt.checkpw(pass, emm.getPasswordHash()))
            throw new RuntimeException("Invalid Password");
        return emm;
    }


}
