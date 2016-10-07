package models.restapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDeleteForm {
    
    public String q = "{isActive:true}";

}
