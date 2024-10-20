package model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseModel {

    @JsonAlias("name")
    public String name;

    @JsonAlias("data")
    public DataModel data;

    @JsonAlias("id")
    public String id;

    @JsonAlias("createdAt")
    public String createdAt;
}
