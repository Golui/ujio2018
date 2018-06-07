package io2018.ii.uj.edu.pl.jurpizza.io.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import io2018.ii.uj.edu.pl.jurpizza.io.AddressManager;
import io2018.ii.uj.edu.pl.jurpizza.model.Address;

public class MockAddressManager implements AddressManager {

    List<Address> adr = null;

    @Override
    public void loadAddresses(Context ctx) {


        try {
            ObjectInputStream ois = new ObjectInputStream(ctx.openFileInput("adresses"));
            this.adr = (List<Address>) ois.readObject();
        } catch (java.io.IOException e) {
            //e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Address> getAddresses() {
        if (this.adr == null) throw new IllegalStateException("Load addresses first");
        return this.adr;
    }

    @Override
    public void saveAddresses(Context ctx) {
        try {
            ObjectOutputStream fos = new ObjectOutputStream(ctx.openFileOutput("adresses", Context.MODE_PRIVATE));
            fos.writeObject(this.adr);
            fos.close();

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

    }
}
