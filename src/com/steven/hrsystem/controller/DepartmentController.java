
package com.steven.hrsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.steven.hrsystem.entity.Department;
import com.steven.hrsystem.service.DepartmentService;
import com.steven.hrsystem.service.EmployeeService;
import com.steven.hrsystem.ws.DepartmentWebService;

@Controller
public class DepartmentController {
	
	@Autowired
	DepartmentService deptService;
	
	@Autowired
	EmployeeService empService;
	
	@Autowired
	DepartmentWebService deptWebService;
	
	@RequestMapping(value="/addDept")//, method=RequestMethod.POST)
	public String addDepartment(Department newDept) {
		deptService.saveDepartment(newDept);
		
		return "forward:/reloadDeptEmpListAtDepartment";
	}
	
	@RequestMapping(value="/departmentPage")
	public String goToDepartmentPage() {
		return "forward:/reloadDeptEmpListAtDepartment";
	}
	
	@RequestMapping(value="/reloadDeptEmpListAtDepartment")
	public String reloadDeptList(ModelMap model) {
		model.addAttribute("deptList", deptService.getDeptList());
		model.addAttribute("empList", empService.getEmpList());
		return "department";
	}
	
	@RequestMapping(value="/deleteDept")
	@ResponseBody
	public String deleteDepartment(@RequestParam Integer deptId) {
		System.out.println("In the deleting method...");
		return deptWebService.deleteDeptById(deptId).toString();
	}
	
	@RequestMapping(value="/updateDept")
	@ResponseBody
	public String updateDepartment(@RequestParam Integer deptId, @RequestParam String deptName, @RequestParam String deptEmail) {
		System.out.println("In the update method...");
		deptWebService.updateDeptBy(deptId, deptName, deptEmail);
		return "1";
	}
	
	@RequestMapping(value="/getDeptList")
	@ResponseBody
	public List<Department> getDeptList() {
		return deptWebService.getAllDept();
	}
	
	@RequestMapping(value="/getDeptDetail")
	@ResponseBody
	public Department getDeptDetail(Integer deptId) {
		return deptWebService.getDeptById(deptId);
	}
	
	@RequestMapping(value="/getDeptByPartialName")
	@ResponseBody
	public List<Department> getDeptByPartialName(String deptName) {
		return deptWebService.getDeptByPartialName(deptName);
	}
	
	@ExceptionHandler(Exception.class)
	public String stopDirectAddDept() {
		System.out.println("In the exception handling in dept...");
		return "errorpage";
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public String stopDirectAddDept4() {
		System.out.println("In the exception handling in dept Method internal server error...");
		return "errorpage";
	}
	
}



