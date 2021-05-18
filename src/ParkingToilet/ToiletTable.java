package ParkingToilet;

import javafx.beans.property.StringProperty;

public class ToiletTable {
    private final StringProperty sort;
    private final StringProperty name;
    private final StringProperty address;
    private final StringProperty Public; //남녀 공용
    private final StringProperty opening_time; //개방시간
    private final StringProperty managementAgency; //관리기관

    public ToiletTable(StringProperty sort, StringProperty name, StringProperty address, StringProperty Public, StringProperty opening_time, StringProperty managementAgency) {
        this.name = name;
        this.address = address;
        this.sort = sort;
        this.Public = Public;
        this.opening_time = opening_time;
        this.managementAgency = managementAgency;
    }

    public StringProperty name() {
        return name;
    }
    public StringProperty address() {
        return address;
    }
    public StringProperty sort() {
        return sort;
    }
    public StringProperty Public() { return Public;}
    public StringProperty opening_time() {return opening_time;}
    public StringProperty managementAgency() {return managementAgency;}
}
