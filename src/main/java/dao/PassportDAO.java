package dao;

import model.Office;
import model.Passport;

import java.util.Set;

public interface PassportDAO {
    boolean createPassport(Passport passport);

    Passport findById(int id);

    boolean deleteById(int id);

    boolean updatePassport(Passport passport);

    Set<Passport> all();
}
