package com.cognizant.model;

public class Country {

    private int id;
    private String countryCode;
    private String countryName;
    private String capital;
    private String currency;
    private long population;

    // Default constructor
    public Country() {}

    // Parameterized constructor
    public Country(int id, String countryCode,
                   String countryName, String capital,
                   String currency, long population) {
        this.id          = id;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.capital     = capital;
        this.currency    = currency;
        this.population  = population;
    }

    // Getters
    public int    getId()          { return id;          }
    public String getCountryCode() { return countryCode; }
    public String getCountryName() { return countryName; }
    public String getCapital()     { return capital;     }
    public String getCurrency()    { return currency;    }
    public long   getPopulation()  { return population;  }

    // Setters
    public void setId(int id)                    { this.id          = id;          }
    public void setCountryCode(String code)      { this.countryCode = code;        }
    public void setCountryName(String name)      { this.countryName = name;        }
    public void setCapital(String capital)       { this.capital     = capital;     }
    public void setCurrency(String currency)     { this.currency    = currency;    }
    public void setPopulation(long population)   { this.population  = population;  }

    @Override
    public String toString() {
        return "Country{" +
               "id="          + id          +
               ", code="      + countryCode +
               ", name="      + countryName +
               ", capital="   + capital     +
               ", currency="  + currency    +
               ", population="+ population  +
               "}";
    }
}