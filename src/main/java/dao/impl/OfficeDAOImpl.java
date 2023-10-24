package dao.impl;

import dao.OfficeDAO;
import model.Employee;
import model.Office;
import util.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class OfficeDAOImpl implements OfficeDAO {

    @Override
    public boolean createOffice(Office office) {
        Connection conn = DBUtils.getConnection();

        try {
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO offices (title, address, `phone 1`, `phone 2`, postal_code, created_ts) " +
                    "VALUES ('" + office.getTitle() + "', '" + office.getAddress() + "', '" + office.getPhone1() +
                    "', '" + office.getPhone2() + "', '" + office.getPostalCode() + "', CURRENT_TIMESTAMP)";
            int count = stmt.executeUpdate(sql);
            return count == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Office findById(int id) {
        Connection conn = DBUtils.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM offices where id  = " + id);
            if (rs.next()) {
                System.out.println("Office with ID = " + id + " found!");
                Office office = new Office();
                office.setId(id);
                office.setTitle(rs.getString("title"));
                office.setAddress(rs.getString("address"));
                office.setPhone1(rs.getString("phone 1"));
                office.setPhone2(rs.getString("phone 2"));
                office.setPostalCode(rs.getInt("postal_code"));
                office.setUpdatedTS(rs.getTimestamp("updated_ts"));
                office.setCreatedTS(rs.getTimestamp("created_ts"));
                return office;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        findById(id);
        String sql = "DELETE FROM offices WHERE id = " + id;
        Connection conn = DBUtils.getConnection();
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
    public boolean updateOffice(Office office) {
        // ищем офис к-ый хотим изменить
        findById(office.getId());
        Connection conn = DBUtils.getConnection();

        int count;
        try {
            Statement stmt = conn.createStatement();

            String sql = "UPDATE offices SET id =" + office.getId() + ", title='" + office.getTitle() +
                    "', address = '" + office.getAddress() + "', `phone 1` = '" + office.getPhone1() + "', " +
                    "`phone 2` = '" + office.getPhone2() + "', postal_code = " + office.getPostalCode() +
                    ", created_ts = '" + office.getCreatedTS() + "' WHERE id = " + office.getId();
            count = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count == 1;
    }

    @Override
    public Set<Office> all() {
        Set<Office> offices = new HashSet<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM offices");
            while (rs.next()) {
                Office office = new Office();
                office.setId(rs.getInt("id"));
                office.setTitle(rs.getString("title"));
                office.setAddress(rs.getString("address"));
                office.setPhone1(rs.getString("phone 1"));
                office.setPhone2(rs.getString("phone 2"));
                office.setPostalCode(rs.getInt("postal_code"));
                office.setUpdatedTS(rs.getTimestamp("updated_ts"));
                office.setCreatedTS(rs.getTimestamp("created_ts"));
                offices.add(office);
            }
            return offices;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.release(conn, stmt, rs);
        }


    }
}
