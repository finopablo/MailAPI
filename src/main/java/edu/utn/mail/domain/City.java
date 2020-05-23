package edu.utn.mail.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="cities")
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_city")
    Integer cityId;
    @Column(name="city_name")
    String name;

    @ManyToOne
    @JoinColumn(name = "id_country")
    @Fetch(FetchMode.JOIN)
    Country country;


    public City() {

    }

    public City(Integer cityId, String name, Country country) {
        this.cityId = cityId;
        this.name = name;
        this.country = country;
    }

    public City(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        return this.cityId != ((City) o).getCityId();

    }

    @Override
    public int hashCode() {
        return cityId.hashCode();
    }
}
