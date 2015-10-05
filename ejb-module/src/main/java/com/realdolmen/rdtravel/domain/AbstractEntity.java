package com.realdolmen.rdtravel.domain;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by OCPAX79 on 5/10/2015.
 */
@MappedSuperclass
public class AbstractEntity implements Serializable {
    public AbstractEntity() {
    }
}
