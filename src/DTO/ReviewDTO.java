package DTO;

import java.io.Serializable;
import java.sql.Timestamp;

public class ReviewDTO implements Serializable {
    int no;
    private String user_id;
    private String content;
    private int scope;
    private String destination_code;
    private String destination_name;
    private Timestamp modify_date;
    private Timestamp reporting_date;
    private byte[] image;

    public ReviewDTO(int no, String user_id, String content, int scope, String destination_code, String destination_name, Timestamp modify_date, Timestamp reporting_date, byte[] image) {
        this.no = no;
        this.user_id = user_id;
        this.content = content;
        this.scope = scope;
        this.destination_code = destination_code;
        this.destination_name = destination_name;
        this.modify_date = modify_date;
        this.reporting_date = reporting_date;
        this.image = image;
    }
    public ReviewDTO(String user_id, String content, int scope, String destination_code, String destination_name, Timestamp modify_date, Timestamp reporting_date, byte[] image) {
        this.user_id = user_id;
        this.content = content;
        this.scope = scope;
        this.destination_code = destination_code;
        this.destination_name = destination_name;
        this.modify_date = modify_date;
        this.reporting_date = reporting_date;
        this.image = image;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getScope() {
        return scope;
    }

    public void setScope(int scope) {
        this.scope = scope;
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

    public Timestamp getModify_date() {
        return modify_date;
    }

    public void setModify_date(Timestamp modify_date) {
        this.modify_date = modify_date;
    }

    public Timestamp getReporting_date() {
        return reporting_date;
    }

    public void setReporting_date(Timestamp reporting_date) {
        this.reporting_date = reporting_date;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    
}
