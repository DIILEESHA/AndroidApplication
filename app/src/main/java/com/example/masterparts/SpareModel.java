package com.example.masterparts;




public class SpareModel {
    String id, vehicleName, sparePart, place, modle, price, contactNumber, description;

    public SpareModel(String id, String vehicleName, String string) {
    }

    public SpareModel(String id, String vehicleName, String sparePart, String place, String modle, String price, String contactNumber,String description) {
        this.id = id;
        this.vehicleName = vehicleName;
        this.sparePart = sparePart;
        this.place = place;
        this.modle = modle;
        this.price = price;
        this.contactNumber = contactNumber;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String VehicleName) {
        this.vehicleName = VehicleName;
    }

    public String getSparePart() {
        return sparePart;
    }

    public void setSparePart(String sparePart) {
        this.sparePart = sparePart;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getModle() {
        return modle;
    }

    public void setModle(String modle) {
        this.modle = modle;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


