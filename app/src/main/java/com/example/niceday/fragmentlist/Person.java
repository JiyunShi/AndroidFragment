package com.example.niceday.fragmentlist;

/**
 * Created by NiceDay on 2017-09-30.
 */

public class Person {

    public int id;
    String name, username,email,phone,website;
    Address address;
    Company company;
    String img = "nothing yet";





    public Person(int id, String name, String username, String email, String phone, String website, Address address, Company company){
        this.id = id;
        this.name=name;
        this.username=username;
        this.email=email;
        this.phone=phone;
        this.website=website;
        this.address=address;
        this.company=company;
        this.img = "nothing yet";

    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }




}


