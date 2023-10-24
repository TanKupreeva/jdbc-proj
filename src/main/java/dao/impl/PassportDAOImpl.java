package dao.impl;

import dao.PassportDAO;
import model.Office;
import model.Passport;
import util.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class PassportDAOImpl implements PassportDAO {
    @Override
    public boolean createPassport(Passport passport) {
        Connection conn = DBUtils.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO `passport` (`personal_id`, `ind_id`, `exp_ts`, `created_ts`) " +
                    "VALUES ('" + passport.getPersonalId() + "', '" + passport.getIndId() + "', '"
                    + passport.getExpTS() + "', '" + passport.getCreatedTs() + "')";
            int count = stmt.executeUpdate(sql);
            return count == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Passport findById(int id) {
        Connection conn = DBUtils.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM passport where id  = " + id);
            if (rs.next()) {
                System.out.println("Passport with ID = " + id + " found!");
                Passport passport = new Passport();
                passport.setId(id);
                passport.setPersonalId(rs.getString("personal_id"));
                passport.setIndId(rs.getString("ind_id"));
                passport.setExpTS(rs.getDate("exp_ts"));
                passport.setCreatedTs(rs.getDate("created_ts"));
                return passport;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        findById(id);
        String sql = "DELETE FROM passport WHERE id =" + id;
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
    public boolean updatePassport(Passport passport) {
        findById(passport.getId());
        int count;
        Connection conn = DBUtils.getConnection();
        String sql = "UPDATE passport SET  personal_id='" + passport.getPersonalId() +
                "', ind_id = '" + passport.getIndId() + "', exp_ts = '" + passport.getExpTS() +
                "', created_ts = '" + passport.getCreatedTs() + "' WHERE id = " + passport.getId();

        try {
            Statement stmt = conn.createStatement();
            count = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count == 1;
    }

    @Override
    public Set<Passport> all() {
        Set<Passport> passports = new HashSet<>();
        Connection connection = DBUtils.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM passport");
            while (rs.next()) {
                Passport passport = new Passport();
                passport.setId(rs.getInt("id"));
                passport.setPersonalId(rs.getString("personal_id"));
                passport.setIndId(rs.getString("ind_id"));
                passport.setExpTS(rs.getDate("exp_ts"));
                passport.setCreatedTs(rs.getDate("created_ts"));
                passports.add(passport);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return passports;
    }
}
