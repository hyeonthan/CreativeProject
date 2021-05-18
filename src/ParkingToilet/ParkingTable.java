package ParkingToilet;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class ParkingTable {
    private final StringProperty sort;
    private final StringProperty name;
    private final StringProperty address;
    private final IntegerProperty size;
    private final StringProperty opening_days;
    private final StringProperty weekdays_opening_time;
    private final StringProperty sat_opening_time;
    private final StringProperty holiday_opening_time;
    private final IntegerProperty fee;

    public ParkingTable(StringProperty sort, StringProperty name, StringProperty address, IntegerProperty size, StringProperty opening_days, StringProperty weekdays_opening_time, StringProperty sat_opening_time, StringProperty holiday_opening_time, IntegerProperty fee) {
        this.name = name;
        this.address = address;
        this.sort = sort;
        this.size = size;
        this.opening_days = opening_days;
        this.weekdays_opening_time = weekdays_opening_time;
        this.sat_opening_time = sat_opening_time;
        this.holiday_opening_time = holiday_opening_time;
        this.fee = fee;
    }

    public StringProperty sortProperty() {
        return sort;
    }
    public StringProperty nameProperty() {
        return name;
    }
    public StringProperty addressProperty() {
        return address;
    }
    public IntegerProperty sizeProperty() {
        return size;
    }
    public StringProperty opening_daysProperty() {
        return opening_days;
    }
    public StringProperty weekdays_opening_timeProperty() {
        return weekdays_opening_time;
    }
    public StringProperty sat_opening_timeProperty() {
        return sat_opening_time;
    }
    public StringProperty holiday_opening_timeProperty() {
        return holiday_opening_time;
    }
    public IntegerProperty feeProperty() {
        return fee;
    }
}
