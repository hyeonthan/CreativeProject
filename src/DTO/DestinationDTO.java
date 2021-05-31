package DTO;


import java.io.Serializable;

public class DestinationDTO implements Serializable {
    private String code;
    private String sortation;
    private String forestLodge_code;
    private String beach_code;
    private String touristSpot_code;
    private String name;
    private String Do;
    private String city;
    private String address;
    private double scope;
    private int views;

    // not column
    private int count;
    
    public DestinationDTO(String code, String sortation, String forestLodge_code, String beach_code, String touristSpot_code, String name, String aDo, String city, String address, double scope, int views) {
        this.code = code;
        this.sortation = sortation;
        this.forestLodge_code = forestLodge_code;
        this.beach_code = beach_code;
        this.touristSpot_code = touristSpot_code;
        this.name = name;
        Do = aDo;
        this.city = city;
        this.address = address;
        this.scope = scope;
        this.views = views;
    }
    public DestinationDTO(String code, String sortation, String name, String Do) {
        this.code = code;
        this.sortation = sortation;
        this.name = name;
        this.Do = Do;
    }
    public DestinationDTO(String code, String sortation, String name, double scope) {
        this.code = code;
        this.sortation = sortation;
        this.name = name;
        this.scope = scope;
    }
    public DestinationDTO(String code, String sortation, String name, int count) {
        this.code = code;
        this.sortation = sortation;
        this.name = name;
        this.count = count;
    }
    public DestinationDTO(String code, String sortation, String name) {
        this.code = code;
        this.sortation = sortation;
        this.name = name;
    }
    public DestinationDTO(String code, String name, double scope) {
        this.code = code;
        this.name = name;
        this.scope = scope;
    }
    public DestinationDTO(String code, String name, int count) {
        this.code = code;
        this.name = name;
        this.count = count;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSortation() {
        return sortation;
    }

    public void setSortation(String sortation) {
        this.sortation = sortation;
    }

    public String getForestLodge_code() {
        return forestLodge_code;
    }

    public void setForestLodge_code(String forestLoge_code) {
        this.forestLodge_code = forestLoge_code;
    }

    public String getBeach_code() {
        return beach_code;
    }

    public void setBeach_code(String beach_code) {
        this.beach_code = beach_code;
    }

    public String getTouristSpot_code() {
        return touristSpot_code;
    }

    public void setTouristSpot_code(String touristSpot_code) {
        this.touristSpot_code = touristSpot_code;
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

    public double getScope() {
        return scope;
    }

    public void setScope(double scope) {
        this.scope = scope;
    }
    public int getViews() {
        return views;
    }
    public void setViews(int views) {
        this.views = views;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    
}
