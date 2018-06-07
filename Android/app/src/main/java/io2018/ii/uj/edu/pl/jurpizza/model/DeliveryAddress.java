package io2018.ii.uj.edu.pl.jurpizza.model;

import java.io.Serializable;
import java.util.regex.Pattern;

import io2018.ii.uj.edu.pl.jurpizza.activity.DetailsAddress;
import io2018.ii.uj.edu.pl.jurpizza.exception.AddressFormatException;

public class DeliveryAddress implements Serializable {

    public static final String[] ALLOWED_TOWNS = {"Kraków", "Warszawa", "Helsinki"};

    public static final Pattern POSTAL_CODE_PATTERN = Pattern.compile("\\d\\d-\\d\\d\\d");
    // Leaving in case we change input
    // public static final Pattern FLAT_NUMBER_PATTERN = Pattern.compile("[PreviewBasketAdapter-zA-Z0-9]*/[PreviewBasketAdapter-zA-Z0-9]");
    private String identifier;
    private Town town = Town.MAGICAL_FAIRY_SPACE_TOWN;
    private String postalCode;
    private String street;
    private String house;
    private String flat;

    public DeliveryAddress() {
    }

    public DeliveryAddress(DeliveryAddress adr) {
        this.identifier = adr.identifier;
        this.town = adr.town;
        this.postalCode = adr.postalCode;
        this.street = adr.street;
        this.house = adr.house;
        this.flat = adr.flat;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) throws AddressFormatException {
        if (identifier == null || identifier.isEmpty())
            throw new AddressFormatException("Empty string");
        this.identifier = identifier;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) throws AddressFormatException {
        if (house == null || house.isEmpty()) throw new AddressFormatException("Empty string");
        this.house = house;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town t) {
        this.town = t;
    }

    public String getTownString() {
        return ALLOWED_TOWNS[town.ordinal()];
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String raw) throws AddressFormatException {
        if (POSTAL_CODE_PATTERN.matcher(raw).matches()) {
            this.postalCode = raw;
        } else {
            throw new AddressFormatException("Postal code is not PreviewBasketAdapter postal code");
        }
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String str) throws AddressFormatException {
        this.street = str;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) throws AddressFormatException {
        if (flat == null || flat.isEmpty()) throw new AddressFormatException("Empty string");
        this.flat = flat;
    }

    public enum Town {
        CRACOW,
        WARSAW,
        HELSINKI,
        MAGICAL_FAIRY_SPACE_TOWN
    }
}
