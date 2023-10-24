package jdbc_test;

import dao.PassportDAO;
import dao.impl.EmployeeDAOImpl;
import dao.impl.OfficeDAOImpl;
import dao.impl.PassportDAOImpl;
import model.Employee;
import model.Office;
import model.Passport;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;


public class Test {

    public static void main(String[] args) {

//        Employee empl1 = new EmployeeDAOImpl().findById(2);
//        System.out.println(empl1.getOffice());
//        System.out.println(empl1.getPassport());
//
//        System.out.println(new PassportDAOImpl().all().size());
//        System.out.println(new PassportDAOImpl().all());
//
//        System.out.println(new EmployeeDAOImpl().all().size());
        System.out.println(new PassportDAOImpl().findById(12));


        Office office = new Office();
        office.setAddress("address");
        office.setPhone1("123");
        office.setPhone2("123");
        office.setPostalCode(2222222);
        office.setTitle("MAIN 2");

//        boolean isCreated = new OfficeDAOImpl().createOffice(office);
//        System.out.println("New office has been created? " + isCreated);

//        boolean isDelete = new OfficeDAOImpl().deleteById(12);
//        System.out.println("Office deleted? " + isDelete);

// updated new office
        Office office2 = new Office();
        office2.setId(4);
        office2.setAddress("GERM");
        office2.setPhone1("123");
        office2.setPhone2("321");
        office2.setPostalCode(220222);
        office2.setTitle("MAIN");
        office2.setCreatedTS(Timestamp.from(Instant.now()));
        boolean isUpDate = new OfficeDAOImpl().updateOffice(office2);
        System.out.println("Office updated? " + isUpDate);

// show all offices
        System.out.println(new OfficeDAOImpl().all().size());

// created new passport
        Passport passport = new Passport();
        passport.setPersonalId("MP0987654");
        passport.setIndId("2345678AO76PB6");
        passport.setExpTS(Date.valueOf("2023-10-31"));
        passport.setCreatedTs(Date.valueOf("2018-10-31"));
//        boolean isCreatedPassport = new PassportDAOImpl().createPassport(passport);
//        System.out.println("New passport is created? " + isCreatedPassport);

// deleted passport
//        boolean isDeletedPassport = new PassportDAOImpl().deleteById(9);
//        System.out.println("Passport is deleted? " + isDeletedPassport);


// updated passport
        Passport passport1 = new Passport();
        passport1.setId(12);
        passport1.setIndId("1115678AO76PB6");
        passport1.setPersonalId("MP3456765");
        passport1.setCreatedTs(Date.valueOf(LocalDate.now()));
        passport1.setExpTS(Date.valueOf("2018-10-31"));

//        boolean isUpdatedPassport = new PassportDAOImpl().updatePassport(passport1);
//        System.out.println("Passport is updated? " + isUpdatedPassport);

// created employee
        Employee employee = new Employee();
        employee.setName("Max");
        employee.setLastName("D");
        employee.setAge(34);
        employee.setPassport(passport1);

//delete employee
//        boolean isDeletedEmployee = new EmployeeDAOImpl().deleteById(6);
//        System.out.println("Employee is deleted? "+ isDeletedEmployee);

        Office office1 = new Office();
        office1.setId(1);
                System.out.println(new EmployeeDAOImpl().getAllByOfficeId(office1).size());

    }
}
