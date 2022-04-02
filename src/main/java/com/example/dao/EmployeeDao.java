package com.example.dao;

import com.example.pojo.Department;
import com.example.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
//员工DAO
@Repository
public class EmployeeDao {
//    模拟数据库 中的数据
    private static Map<Integer, Employee> Employees=null;
//    员工所属部门
    @Autowired
    private DepartmentDao departmentDao;
    static{
        Employees = new HashMap<Integer, Employee>();
        Employees.put(101,new Employee(101,"aa","1123@qq.com",0,new Department(101,"教学部")));
        Employees.put(102,new Employee(102,"bb","2123@qq.com",1,new Department(102,"市场部")));
        Employees.put(103,new Employee(103,"cc","3123@qq.com",0,new Department(103,"运营部")));
        Employees.put(104,new Employee(104,"dd","4123@qq.com",1,new Department(104,"营销部")));
        Employees.put(105,new Employee(105,"ee","5123@qq.com",0,new Department(105,"后勤部")));
        Employees.put(106,new Employee(106,"ff","1623@qq.com",1,new Department(106,"嗨嗨部")));
    }
//    主键自增
    private static Integer initId=1006;

//    增加一个员工
    public void save(Employee employee){
        if (employee.getId()==null){
            employee.setId(initId++);
        }

        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        Employees.put(employee.getId(),employee);
    }

//    查询全部员工信息
    public Collection<Employee> getAll(){
        return Employees.values();
    }

//    通过ID查询员工
    public Employee getEmployeeById(Integer id){
        return Employees.get(id);
    }

//     删除员工
    public void delete(Integer id){
        Employees.remove(id);
    }


}
