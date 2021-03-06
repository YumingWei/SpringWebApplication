
package com.steven.hrsystem.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.steven.hrsystem.DAO.DepartmentDAO;
import com.steven.hrsystem.entity.Department;

@Service
public class DepartmentWebServiceImpl implements DepartmentWebService{

	@Autowired
	DepartmentDAO deptDAO;
	
	@Override
	@Transactional
	public List<Department> getDeptByPartialName(String deptName) {
		return deptDAO.getDeprtmentByPartialName(deptName);
	}
	
	@Override
	@Transactional
	public Integer deleteDeptById(Integer deptId) {
		
		try {
			deptDAO.delete(deptDAO.findOne(deptId));
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	@Override
	@Transactional
	public void updateDeptBy(Integer deptId, String deptName, String deptEmail) {
		Department dept = deptDAO.findOne(deptId);
		dept.setDeptName(deptName);
		dept.setDeptEmail(deptEmail);
		deptDAO.save(dept);
	}

	@Override
	public List<Department> getAllDept() {
		return deptDAO.findAll();
	}

	@Override
	public Department getDeptById(Integer deptId) {
		return deptDAO.findOne(deptId);
	}
	
}
