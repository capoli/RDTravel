package com.realdolmen.rdtravel.domain;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class AbstractEntity implements Serializable {
    public AbstractEntity() {
    }
}
