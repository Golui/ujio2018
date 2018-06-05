package io2018.ii.uj.edu.pl.jurpizza.io.impl;

import android.content.Context;

import java.util.List;

import io2018.ii.uj.edu.pl.jurpizza.io.AddressManager;
import io2018.ii.uj.edu.pl.jurpizza.model.Address;

public class MockAddressManager implements AddressManager {
    @Override
    public List<Address> loadAdresses(Context ctx) {
        // TODO Stub
        return null;
    }

    @Override
    public void saveAdresses(Context ctx, List<Address> adr) {
        // TODO Stub
    }
}
