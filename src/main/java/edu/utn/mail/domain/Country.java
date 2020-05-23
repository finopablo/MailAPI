package edu.utn.mail.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="countries")
public class Country implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_country")
    Integer countryId;
    @Column(name="country_name")
    String country;

    public Country() {

    }

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
