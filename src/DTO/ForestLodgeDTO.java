package DTO;

public class ForestLodgeDTO {
    private String code;
    private String name;
    private String Do;
    private String city;
    private String address;
    private String phone_num;
    private double latitude;
    private double longitude;
    private String amenities;
    private String capacity_people;
    private String enter_fee;
    private String accommodation;
    private String home_page;
    
    public ForestLodgeDTO(String code, String name, String do1, String city, String address, String phone_num,
            double latitude, double longitude, String amenities, String capacity_people, String enter_fee,
            String accommodation, String home_page) {
        this.code = code;
        this.name = name;
        Do = do1;
        this.city = city;
        this.address = address;
        this.phone_num = phone_num;
        this.latitude = latitude;
        this.longitude = longitude;
        this.amenities = amenities;
        this.capacity_people = capacity_people;
        this.enter_fee = enter_fee;
        this.accommodation = accommodation;
        this.home_page = home_page;
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
    public String getAmenities() {
        return amenities;
    }
    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }
    public String getCapacity_people() {
        return capacity_people;
    }
    public void setCapacity_people(String capacity_people) {
        this.capacity_people = capacity_people;
    }
    public String getEnter_fee() {
        return enter_fee;
    }
    public void setEnter_fee(String enter_fee) {
        this.enter_fee = enter_fee;
    }
    public String getAccommodation() {
        return accommodation;
    }
    public void setAccommodation(String accommodation) {
        this.accommodation = accommodation;
    }
    public String getHome_page() {
        return home_page;
    }
    public void setHome_page(String home_page) {
        this.home_page = home_page;
    }

    
    
}
