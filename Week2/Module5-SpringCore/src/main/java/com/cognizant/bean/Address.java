package com.cognizant.bean;

public class Address {
    private String street;
    private String city;
    private String state;
    private String pincode;

    // Default constructor (required by Spring XML config)
    public Address() {}

    public Address(String street, String city,
                   String state, String pincode) {
        this.street  = street;
        this.city    = city;
        this.state   = state;
        this.pincode = pincode;
    }

    // Getters and Setters
    public String getStreet()  { return street;  }
    public String getCity()    { return city;    }
    public String getState()   { return state;   }
    public String getPincode() { return pincode; }

    public void setStreet(String street)   { this.street  = street;  }
    public void setCity(String city)       { this.city    = city;    }
    public void setState(String state)     { this.state   = state;   }
    public void setPincode(String pincode) { this.pincode = pincode; }

    @Override
    public String toString() {
        return street + ", " + city + ", " + state + " - " + pincode;
    }
}