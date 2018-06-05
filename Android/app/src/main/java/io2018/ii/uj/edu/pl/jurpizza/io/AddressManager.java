package io2018.ii.uj.edu.pl.jurpizza.io;

import android.content.Context;

import java.util.List;

import io2018.ii.uj.edu.pl.jurpizza.model.Address;

public interface AddressManager{

    public List<Address> loadAdresses(Context ctx);
    public void saveAdresses(Context ctx, List<Address> adr);
}
