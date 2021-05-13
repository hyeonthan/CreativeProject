package DTO;

import java.sql.Timestamp;

public class UserDTO{
    private String id;
    private String password;
    private String name;
    private int age;
    private String gender;
    private String Do;
    private String city;
    private String address;
    private Timestamp creation_date;
    private Timestamp modify_date;

    public UserDTO(String id, String password, String name, int age, String gender, String do1, String city,
            String address, Timestamp creation_date, Timestamp modify_date) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.age = age;
        this.gender = gender;
        Do = do1;
        this.city = city;
        this.address = address;
        this.creation_date = creation_date;
        this.modify_date = modify_date;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getDo() {
        return Do;
    }
    public void setDo(String do1) {
        Do = do1;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Timestamp getCreation_date() {
        return creation_date;
    }
    public void setCreation_date(Timestamp creation_date) {
        this.creation_date = creation_date;
    }
    public Timestamp getModify_date() {
        return modify_date;
    }
    public void setModify_date(Timestamp modify_date) {
        this.modify_date = modify_date;
    }
}
