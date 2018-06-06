package io2018.ii.uj.edu.pl.jurpizza.io.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import io2018.ii.uj.edu.pl.jurpizza.io.AddressManager;
import io2018.ii.uj.edu.pl.jurpizza.model.Address;

public class MockAddressManager implements AddressManager {


    @Override
    public List<Address> loadAddresses(Context ctx) {
        try {
            ObjectInputStream ois = new ObjectInputStream(ctx.openFileInput("adresses"));
            return (List<Address>) ois.readObject();

        } catch (java.io.IOException e) {
            //e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void saveAddresses(Context ctx, List<Address> adr) {
        try {
            ObjectOutputStream fos = new ObjectOutputStream(ctx.openFileOutput("adresses", Context.MODE_PRIVATE));
            fos.writeObject(adr);
            fos.close();

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

    }
}
