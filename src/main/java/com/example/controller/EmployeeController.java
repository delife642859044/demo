package com.example.controller;

import com.example.dao.DepartmentDao;
import com.example.dao.EmployeeDao;
import com.example.pojo.Department;
import com.example.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
public class EmployeeController {
//    获取所有员工
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping(value = "/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("Employees",employees);
//        System.out.println(">>>>>");
//        System.out.println(employees.toString());
        return "emp/list";
    }

    @GetMapping("/toAdd")
    public String toAdd(Model model){

//        查出所有部门的信息
        Collection<Department> department = departmentDao.getDepartment();
        model.addAttribute("deppartments",department);
        return "emp/add";
    }

    @PostMapping("/toAdd")
    public String toAdd(Employee employee){

        employeeDao.save(employee);

        return "redirect:/emps";
    }

//  修改员工数据
    @GetMapping("/update/{eid}")
    public String toUpdateEmp(@PathVariable("eid") Integer id, Model model){
        Employee employeeById = employeeDao.getEmployeeById(id);
        Collection<Department> department = departmentDao.getDepartment();
        model.addAttribute("emp",employeeById);
        model.addAttribute("depts",department);
        System.out.println("++++++");
        return "emp/update";
    }

//    删除员工
    @GetMapping("/delEmp/{eid}")
    public String deleteEmp(@PathVariable("eid") Integer eid){
        employeeDao.delete(eid);
        return "redirect:/emps";
    }

//    注销
    @RequestMapping("/user/logout")
    public String logout(HttpSession session, Model model){
        session.removeAttribute("loginUser");
        model.addAttribute("msg","请重新登录");
        return "redirect:/index";
    }
}
