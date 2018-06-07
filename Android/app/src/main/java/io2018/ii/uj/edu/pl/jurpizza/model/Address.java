package io2018.ii.uj.edu.pl.jurpizza.model;

import java.util.regex.Pattern;

import io2018.ii.uj.edu.pl.jurpizza.exception.AddressFormatException;

public class Address {

    public static final String[] ALLOWED_TOWNS = {"Kraków", "Warszawa", "Helsinki"};

    public static final Pattern POSTAL_CODE_PATTERN = Pattern.compile("\\d\\d-\\d\\d\\d");
    // Leaving in case we change input
    // public static final Pattern FLAT_NUMBER_PATTERN = Pattern.compile("[PreviewBasketAdapter-zA-Z0-9]*/[PreviewBasketAdapter-zA-Z0-9]");

    int town;
    String postalCode;
    String street;
    String house;
    String flat;

    public void setTown(int t)
    {
        this.town = t;
    }

    public void setPostalCode(String raw) throws AddressFormatException
    {
        if(POSTAL_CODE_PATTERN.matcher(raw).matches())
        {
            this.postalCode = raw;
        }else
        {
            throw new AddressFormatException("Postal code is not PreviewBasketAdapter postal code");
        }
    }

    public void setStreet(String str)
    {
        this.street = str;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public int getTown() {
        return town;
    }

    public String getTownString() {
        return ALLOWED_TOWNS[town];
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getStreet() {
        return street;
    }

    public String getFlat() {
        return flat;
    }
}
