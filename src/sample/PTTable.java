package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class PTTable {
    private StringProperty sort;
    private StringProperty name;
    private StringProperty address;
    private IntegerProperty fee;

    public PTTable(StringProperty sort, StringProperty name, StringProperty address, IntegerProperty fee) {
        this.name = name;
        this.address = address;
        this.sort = sort;
        this.fee = fee;
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
    public IntegerProperty feeProperty() {
        return fee;
    }
}