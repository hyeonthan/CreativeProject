package DTO;

import java.io.Serializable;
import java.sql.Timestamp;

public class FavoriteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
    private int no;
    private String user_id;
    private String destination_code;
    private String destination_name;
    private Timestamp add_date;
    private String sortation;

    public FavoriteDTO(int no, String user_id, String destination_code, String destination_name, Timestamp add_date, String sortation) {
        this.no = no;
        this.user_id = user_id;
        this.destination_code = destination_code;
        this.destination_name = destination_name;
        this.add_date = add_date;
        this.sortation = sortation;
    }

    public FavoriteDTO(String user_id, String destination_code, String destination_name, Timestamp add_date, String sortation) {
        this.user_id = user_id;
        this.destination_code = destination_code;
        this.destination_name = destination_name;
        this.add_date = add_date;
        this.sortation = sortation;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDestination_code() {
        return destination_code;
    }

    public void setDestination_code(String destination_code) {
        this.destination_code = destination_code;
    }

    public String getDestination_name() {
        return destination_name;
    }

    public void setDestination_name(String destination_name) {
        this.destination_name = destination_name;
    }

    public Timestamp getAdd_date() {
        return add_date;
    }

    public void setAdd_date(Timestamp add_date) {
        this.add_date = add_date;
    }

    public String getSortation() {
        return sortation;
    }

    public void setSortation(String sortation) {
        this.sortation = sortation;
    }

    





}
