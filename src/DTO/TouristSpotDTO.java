package DTO;

import java.io.Serializable;

public class TouristSpotDTO implements Serializable {
	private static final long serialVersionUID = 1L;
    private String code;
    private String name;
    private String introduction;
    private String phone_num;
    private String Do;
    private String city;
    private String address;
    private double latitude;
    private double longitude;
    private String amenities;
    private int possibleParking;
    private String managementAgency;
    public TouristSpotDTO(String code, String name, String introduction, String phone_num, String aDo, String city, String address, double latitude, double longitude, String amenities, int possibleParking, String managementAgency) {
        this.code = code;
        this.name = name;
        this.introduction = introduction;
        this.phone_num = phone_num;
        Do = aDo;
        this.city = city;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.amenities = amenities;
        this.possibleParking = possibleParking;
        this.managementAgency = managementAgency;
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

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
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

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public int getPossibleParking() {
        return possibleParking;
    }

    public void setPossibleParking(int possibleParking) {
        this.possibleParking = possibleParking;
    }

    public String getManagementAgency() {
        return managementAgency;
    }

    public void setManagementAgency(String managementAgency) {
        this.managementAgency = managementAgency;
    }
    
}
