
package com.steven.hrsystem.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.steven.hrsystem.DAO.DepartmentDAO;
import com.steven.hrsystem.DAO.EmployeeDAO;
import com.steven.hrsystem.entity.Employee;

@Service
public class EmployeeWebServiceImpl implements EmployeeWebService{

	@Autowired
	EmployeeDAO empDAO;
	
	@Autowired
	DepartmentDAO deptDAO;
	
	@Override
	public List<Employee> getEmpByPartialName(String empName) {
		
		return null;
	}
	
	@Override
	@Transactional
	public Integer deleteEmpById(Integer empId) {
		
		try {
			empDAO.delete(empDAO.findOne(empId));
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public void updateEmp(Integer empId, String empFirstName, String empLastName, Integer empAge, Integer deptNo) {
		Employee emp = empDAO.findOne(empId);
		emp.setDepartment(deptDAO.findOne(deptNo));
		emp.setEmpFirstName(empFirstName);
		emp.setEmpLastName(empLastName);
		emp.setEmpAge(empAge);
		empDAO.save(emp);
	}	
	
}
