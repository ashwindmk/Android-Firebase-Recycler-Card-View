package com.example.ashwin.firebaserecyclercardview;

/**
 * Created by ashwin on 12/9/16.
 */

public class Person {

    private String name, address, photo;

    public Person() {
    }

    public Person(String name, String address, String photoUrl) {
        this.name = name;
        this.address = address;
        this.photo = photoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoto(String photoUrl) {
        this.photo = photoUrl;
    }

    public String getPhoto() {
        return photo;
    }

}
