package DTO;

public class ParkingLotsDTO {
    private String code;
    private String name;
    private String Do;
    private String city;
    private String address;
    private int size;
    private String opening_days;
    private String weekdays_opening_time;
    private String sat_opening_time;
    private String holiday_opening_time;
    private String fee;
    private String phone_num;
    private double latitude;
    private double longitude;

    public ParkingLotsDTO(String code, String name, String aDo, String city, String address, int size, String opening_days, String weekdays_opening_time, String sat_opening_time, String holiday_opening_time, String fee, String phone_num, double latitude, double longitude) {
        this.code = code;
        this.name = name;
        Do = aDo;
        this.city = city;
        this.address = address;
        this.size = size;
        this.opening_days = opening_days;
        this.weekdays_opening_time = weekdays_opening_time;
        this.sat_opening_time = sat_opening_time;
        this.holiday_opening_time = holiday_opening_time;
        this.fee = fee;
        this.phone_num = phone_num;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getOpening_days() {
        return opening_days;
    }

    public void setOpening_days(String opening_days) {
        this.opening_days = opening_days;
    }

    public String getWeekdays_opening_time() {
        return weekdays_opening_time;
    }

    public void setWeekdays_opening_time(String weekdays_opening_time) {
        this.weekdays_opening_time = weekdays_opening_time;
    }

    public String getSat_opening_time() {
        return sat_opening_time;
    }

    public void setSat_opening_time(String sat_opening_time) {
        this.sat_opening_time = sat_opening_time;
    }

    public String getHoliday_opening_time() {
        return holiday_opening_time;
    }

    public void setHoliday_opening_time(String holiday_opening_time) {
        this.holiday_opening_time = holiday_opening_time;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
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
}
