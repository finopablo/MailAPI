package edu.utn.mail.domain;



public class Country {

    Integer countryId;
    String country;


    public Country(Integer countryId, String country) {
        this.countryId = countryId;
        this.country = country;
    }

    public Country(String country) {
        this.country = country;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


}
