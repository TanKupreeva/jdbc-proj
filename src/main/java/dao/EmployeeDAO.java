package dao;

import model.Employee;
import model.Office;

import java.util.Set;

public interface EmployeeDAO {
    boolean createEmployee(Employee employee);
    Employee findById(int id);
    boolean deleteById(int id);
    boolean updateEmployee(Employee employee);
    Set<Employee> all();
    Set<Employee> getAllByOfficeId(Office office);
}
