package edu.utn.mail.domain;

public class City {

    Integer cityId;
    String name;
    Country country;

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
