package com.example.clean_city;

public class Member {
    private String Name,Email,Address,Phone,UuID;

    public Member() {

    }

    public String getName() {
        return Name;
    }

    public String getUuID() {
        return UuID;
    }

    public void setUuID(String uuID) {
        UuID = uuID;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() { return Email;  }

    public void setEmail(String email) { Email = email;  }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

}
