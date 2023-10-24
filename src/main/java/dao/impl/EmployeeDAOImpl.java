package dao.impl;

import dao.EmployeeDAO;
import dao.OfficeDAO;
import dao.PassportDAO;
import model.Employee;
import model.Office;
import util.DBUtils;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class EmployeeDAOImpl implements EmployeeDAO {
    private OfficeDAO officeDAO = new OfficeDAOImpl();
    private PassportDAO passportDAO = new PassportDAOImpl();

    @Override
    public boolean createEmployee(Employee employee) {


        return false;
    }

    @Override
    public Employee findById(int id) {

        try (Connection conn = DBUtils.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM employees where id  = " + id);
        ) {
            if (rs.next()) {
                return createEmployee(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        findById(id);
        Connection conn = DBUtils.getConnection();
        String sql = "DELETE FROM employees WHERE id = " + id;
        int count;
        try {
            Statement stmt = conn.createStatement();
            count = stmt.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return count == 1;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        return false;
    }

    @Override
    public Set<Employee> all() {
        Set<Employee> all = new HashSet<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM employees");
            while (rs.next())
                all.add(createEmployee(rs));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.release(conn, stmt, rs);
        }
        return all;
    }

    @Override
    public Set<Employee> getAllByOfficeId(Office office) {
        Set<Employee> allEmployeeByOfficeId = new HashSet<>();
        Connection conn = DBUtils.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employees WHERE office_id =  " + office.getId());
            while (rs.next()) {
                allEmployeeByOfficeId.add(createEmployee(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allEmployeeByOfficeId;
    }

    private Employee createEmployee(ResultSet rs) throws SQLException {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setOffice(officeDAO.findById(rs.getInt("office_id")));
        employee.setAge(rs.getInt("age"));
        employee.setPassport(passportDAO.findById(rs.getInt("passport_id")));
        employee.setCreatedTs(rs.getTimestamp("created_ts"));
        employee.setUpdatedTs(rs.getTimestamp("updated_ts"));
        return employee;
    }
}
