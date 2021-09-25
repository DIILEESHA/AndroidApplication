package com.example.masterparts;

public class DriverModel {
    String id,firstname, lastname, nic, tpnumber, email;


    public DriverModel(String id, String firstname, String lastname, String nic, String tpnumber, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.nic = nic;
        this.tpnumber = tpnumber;
        this.email = email;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getTpnumber() {
        return tpnumber;
    }

    public void setTpnumber(String tpnumber) {
        this.tpnumber = tpnumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


