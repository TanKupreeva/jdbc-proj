package dao;

import model.Office;

import java.sql.SQLException;
import java.util.Set;

public interface OfficeDAO {
    boolean createOffice(Office office);
    Office findById(int id);
    boolean deleteById(int id) throws SQLException;
    boolean updateOffice(Office office);
    Set<Office> all();
}
