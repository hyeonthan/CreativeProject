package DTO;

import java.io.Serializable;

public class BeachDTO  implements Serializable {
    private String code;
    private String name;
    private String introduction;
    private String Do;
    private String city;
    private String address;
    private double latitude;
    private double longitude;
    private String phone_num;
    private String home_page;
    private String opening_period;
    private String available_time;
    private String amenities;

    public BeachDTO(String code, String name, String introduction, String aDo, String city, String address, double latitude, double longitude, String phone_num, String home_page, String opening_period, String available_time, String amenities) {
        this.code = code;
        this.name = name;
        this.introduction = introduction;
        Do = aDo;
        this.city = city;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phone_num = phone_num;
        this.home_page = home_page;
        this.opening_period = opening_period;
        this.available_time = available_time;
        this.amenities = amenities;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getDo() {
        return Do;
    }

    public void setDo(String aDo) {
        Do = aDo;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getHome_page() {
        return home_page;
    }

    public void setHome_page(String home_page) {
        this.home_page = home_page;
    }

    public String getOpening_period() {
        return opening_period;
    }

    public void setOpening_period(String opening_period) {
        this.opening_period = opening_period;
    }

    public String getAvailable_time() {
        return available_time;
    }

    public void setAvailable_time(String available_time) {
        this.available_time = available_time;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }
}
