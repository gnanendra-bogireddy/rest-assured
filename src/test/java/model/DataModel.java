package model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataModel {

    @JsonAlias("year")
    public int year;

    @JsonAlias("price")
    public double price;

    @JsonAlias("CPU model")
    public String CPUModel;

    @JsonAlias("Hard disk size")
    public String size;
}
