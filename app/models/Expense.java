package models;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PrePersist;

import play.data.validation.Constraints.Required;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class to store expense
 * 
 * @author ashutosh
 * 
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Expense {
    @JsonIgnore
    @Id
    ObjectId _id = new ObjectId();

    String id = _id.toString();
    
    @Required
    public String description = null;
    public String notes = null;
    @Required
    public Double amount = 0.0;
    
    @Embedded
    public List<Balance> balances = new ArrayList<Balance>();
    
    public boolean isVerified = false;
    public boolean isActive = true;
    
    public String lastUpdatedAt = new DateTime(DateTimeZone.UTC).toString();
    public String createdAt = new DateTime(DateTimeZone.UTC).toString();
    
    @PrePersist
    void prePersist() {
        lastUpdatedAt = new DateTime(DateTimeZone.UTC).toString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
        this.id = _id.toString();
    }

    public void setId(String id) {
        this.id = id;
        this._id = new ObjectId(id);
    }

    public ObjectId get_id() {
        return _id;
    }

    public String getId() {
        return id;
    }
}
