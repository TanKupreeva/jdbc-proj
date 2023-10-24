package model;

import lombok.Data;

import java.sql.Date;

@Data
public class Passport {
    private int id;
    private String personalId;
    private String indId;
    private Date expTS;
    private Date createdTs;

}
