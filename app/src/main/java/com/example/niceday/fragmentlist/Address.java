package com.example.niceday.fragmentlist;

/**
 * Created by NiceDay on 2017-09-30.
 */

public class Address {

    String street, suite, city, zipcode;
    Geo geo;

    public Address(String street, String suite, String city, String zipcode, Geo geo){
        this.street=street;
        this.suite=suite;
        this.city=city;
        this.zipcode=zipcode;
        this.geo=geo;
    }

    class Geo{
        String lat, lng;

    }

}
