package io2018.ii.uj.edu.pl.jurpizza.io;

import android.content.Context;

import java.io.Serializable;
import java.util.List;

import io2018.ii.uj.edu.pl.jurpizza.model.DeliveryAddress;

public interface AddressManager extends Serializable {

    void loadAddresses(Context ctx);

    void saveAddresses(Context ctx);

    List<DeliveryAddress> getAddresses();
}
