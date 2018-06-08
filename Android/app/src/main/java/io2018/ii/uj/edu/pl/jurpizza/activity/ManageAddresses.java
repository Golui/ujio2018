package io2018.ii.uj.edu.pl.jurpizza.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.osmdroid.config.Configuration;

import java.util.ArrayList;

import io2018.ii.uj.edu.pl.jurpizza.R;
import io2018.ii.uj.edu.pl.jurpizza.adapter.AddressManagerAdapter;
import io2018.ii.uj.edu.pl.jurpizza.exception.AddressFormatException;
import io2018.ii.uj.edu.pl.jurpizza.io.AddressManager;
import io2018.ii.uj.edu.pl.jurpizza.io.impl.MockAddressManager;
import io2018.ii.uj.edu.pl.jurpizza.model.BasketEntry;
import io2018.ii.uj.edu.pl.jurpizza.model.DeliveryAddress;

public class ManageAddresses extends Activity {

    public static final String PREVIOUS_ORDERS_INTENT = "orders";

    public static final int CREATE_NEW = 1;
    public static final int ALTER = 2;

    AddressManager addressManager;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        setContentView(R.layout.address_manager);

        lv = findViewById(R.id.address_manager_list);

        this.addressManager = new MockAddressManager();
        this.addressManager.loadAddresses(getApplicationContext());

        lv.setAdapter(new AddressManagerAdapter(this.addressManager.getAddresses(), this));
        lv.addHeaderView(getLayoutInflater().inflate(R.layout.address_manager_header, null));

        if (getIntent().getBooleanExtra("isOrdering", false)) {
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent newIntent = new Intent(ManageAddresses.this, Payment.class);

                    newIntent.putExtra("basket", (ArrayList<BasketEntry>) getIntent().getExtras().get("basket"));
                    newIntent.putExtra("address", ManageAddresses.this.addressManager.getAddresses().get(position - 1));

                    startActivity(newIntent);
                }
            });

        } else {
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(ManageAddresses.this, DetailsAddress.class);
                    DeliveryAddress adr = ManageAddresses.this.addressManager.getAddresses().get(position - 1);
                    if (adr == null) return;
                    // Offset because header
                    intent.putExtra(DetailsAddress.ADDRESS_INTENT, adr);
                    intent.putExtra(DetailsAddress.ADDRESS_INTENT_POS, position - 1);
                    startActivityForResult(intent, ALTER);
                }
            });
        }

        Button b = findViewById(R.id.address_manager_add_address);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageAddresses.this, DetailsAddress.class);
                DeliveryAddress adr = new DeliveryAddress();
                try {
                    adr.setIdentifier("New DeliveryAddress");
                } catch (AddressFormatException e) {
                }

                intent.putExtra(DetailsAddress.ADDRESS_INTENT, adr);
                startActivityForResult(intent, CREATE_NEW);
            }
        });

        lv.deferNotifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 0) return;

        DeliveryAddress old = (DeliveryAddress) data.getSerializableExtra(DetailsAddress.ADDRESS_INTENT);
        DeliveryAddress newAddr = (DeliveryAddress) data.getSerializableExtra(DetailsAddress.NEW_ADDRESS);

        switch (requestCode) {
            case CREATE_NEW: {
                if (this.addressManager.getAddresses().size() == 1 && this.addressManager.getAddresses().get(0) == null)
                    this.addressManager.getAddresses().remove(0);
                this.addressManager.getAddresses().add(newAddr);
                break;
            }
            case ALTER: {
                this.addressManager.getAddresses().set(data.getIntExtra(DetailsAddress.ADDRESS_INTENT_POS, -1), newAddr);
                break;
            }
            default:
                break;
        }

        this.addressManager.saveAddresses(getApplicationContext());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ManageAddresses.this.lv.deferNotifyDataSetChanged();
            }
        }, 100);

    }
}
