package io2018.ii.uj.edu.pl.jurpizza.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.regex.Pattern;

import io2018.ii.uj.edu.pl.jurpizza.exception.AddressFormatException;

public class Address implements Serializable {

    public static final String[] TOWNS_STR = {"Kraków", "Warszawa", "Helsinki"};

    public enum Town {
        CRACOW,
        WARSAW,
        HELSINKI
    }

    public static final Pattern POSTAL_CODE_PATTERN = Pattern.compile("\\d\\d-\\d\\d\\d");
    // Leaving in case we change input
    // public static final Pattern FLAT_NUMBER_PATTERN = Pattern.compile("[a-zA-Z0-9]*/[a-zA-Z0-9]");

    Town town;
    String postalCode;
    String street;
    String house;
    String flat;

    public void setTown(Town t)
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
            throw new AddressFormatException("Postal code is not a postal code");
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

    public Town getTown() {
        return town;
    }

    public String getTownString() {
        return TOWNS_STR[town.ordinal()];
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
