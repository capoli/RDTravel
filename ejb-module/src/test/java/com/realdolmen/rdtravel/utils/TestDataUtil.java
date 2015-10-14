package com.realdolmen.rdtravel.utils;

import com.realdolmen.rdtravel.domain.Continent;
import com.realdolmen.rdtravel.domain.Location;

public class TestDataUtil {

    public static Location hawai() {
        return new Location("Hawai", "HAW", new Continent("America", "AM"));
    }

    public static Location london() {
        return new Location("London", "LON", new Continent("Europe", "EUR"));
    }

    public static Location tokyo() {
        return new Location("Tokyo", "TOK", new Continent("Asia", "AS"));
    }

    public static Location sydney() {
        return new Location("Sydney", "SYD", new Continent("Oceania", "OC"));
    }
}
