/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.GeoRegulariza.Generic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author johnny
 */

@MappedSuperclass
@Data
public class GenericEntity implements Serializable {
    
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date creationDate;
    
    private Boolean active;
    
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date updateDate;
    
    @Version
    @JsonIgnore
    private int version;
    
    public GenericEntity() {
        this.active = true;
        this.creationDate = new Date();
    }
}
