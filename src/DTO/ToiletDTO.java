package DTO;

import java.io.Serializable;

public class ToiletDTO implements Serializable {
	private static final long serialVersionUID = 1L;
    private String code;
    private String name;
    private String Do;
    private String city;
    private String address;
    private String Public;
    private String opening_time;
    private double latitude;
    private double longitude;
    private String managementAgency;
    public ToiletDTO(String code, String name, String aDo, String city, String address, String aPublic, String opening_time, double latitude, double longitude, String managementAgency) {
        this.code = code;
        this.name = name;
        Do = aDo;
        this.city = city;
        this.address = address;
        Public = aPublic;
        this.opening_time = opening_time;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public String getPublic() {
        return Public;
    }

    public void setPublic(String aPublic) {
        Public = aPublic;
    }

    public String getOpening_time() {
        return opening_time;
    }

    public void setOpening_time(String opening_time) {
        this.opening_time = opening_time;
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

    public String getManagementAgency() {
        return managementAgency;
    }

    public void setManagementAgency(String managementAgency) {
        this.managementAgency = managementAgency;
    }
    
}
