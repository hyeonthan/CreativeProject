package sample;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;

public class DestinationTable {
    private StringProperty sort;
    private StringProperty name;
    private StringProperty address;
    private DoubleProperty grade;

    public DestinationTable(StringProperty sort, StringProperty name, StringProperty address, DoubleProperty grade) {
        this.name = name;
        this.address = address;
        this.sort = sort;
        this.grade = grade;
    }

    public StringProperty nameProperty() {
        return name;
    }
    public StringProperty addressProperty() {
        return address;
    }
    public StringProperty sortProperty() {
        return sort;
    }
    public DoubleProperty gradeProperty() {
        return grade;
    }
}