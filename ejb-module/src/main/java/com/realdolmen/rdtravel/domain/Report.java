package com.realdolmen.rdtravel.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by OCPAX79 on 12/10/2015.
 */
public class Report implements Serializable {
    private Double average;
    private Double min;
    private Double max;

    public Report() {
    }

    public Report(Double average, Double min, Double max) {
        this.average = (new BigDecimal(average)).setScale(2, RoundingMode.HALF_UP).doubleValue();
        this.min = (new BigDecimal(min)).setScale(2, RoundingMode.HALF_UP).doubleValue();
        this.max = (new BigDecimal(max)).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }
}
