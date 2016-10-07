package models;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class to store balances
 * 
 * @author ashutosh
 * 
 */
@Embedded
@JsonIgnoreProperties(ignoreUnknown = true)
public class Balance {
    @Embedded
    public UserRef user;
    
    public Integer owed;
    public Integer paid;

}
