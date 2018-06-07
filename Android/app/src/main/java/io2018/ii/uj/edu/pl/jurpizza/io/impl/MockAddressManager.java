package io2018.ii.uj.edu.pl.jurpizza.io.impl;

import android.content.Context;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import io2018.ii.uj.edu.pl.jurpizza.io.AddressManager;
import io2018.ii.uj.edu.pl.jurpizza.model.DeliveryAddress;

public class MockAddressManager implements AddressManager {

    List<DeliveryAddress> adr = new ArrayList<>();

    @Override
    public void loadAddresses(Context ctx) {


        try {
            ObjectInputStream ois = new ObjectInputStream(ctx.openFileInput("adresses"));
            this.adr = (List<DeliveryAddress>) ois.readObject();
        } catch (java.io.IOException e) {
            //e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (this.adr == null) {
            this.adr = new ArrayList<>();
        }
    }

    @Override
    public List<DeliveryAddress> getAddresses() {
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
