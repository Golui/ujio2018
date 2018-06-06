package io2018.ii.uj.edu.pl.jurpizza.io;

import android.content.Context;

import java.util.List;

import io2018.ii.uj.edu.pl.jurpizza.model.Address;

public interface AddressManager {

    List<Address> loadAddresses(Context ctx);

    void saveAddresses(Context ctx, List<Address> adr);
}
