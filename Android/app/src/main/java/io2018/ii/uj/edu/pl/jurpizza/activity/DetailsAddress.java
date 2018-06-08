package io2018.ii.uj.edu.pl.jurpizza.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import io2018.ii.uj.edu.pl.jurpizza.exception.AddressFormatException;
import io2018.ii.uj.edu.pl.jurpizza.model.DeliveryAddress;

import io2018.ii.uj.edu.pl.jurpizza.R;

public class DetailsAddress extends Activity implements View.OnClickListener {

    public static final String ADDRESS_INTENT = "currentAddress";
    public static final String ADDRESS_INTENT_POS = "currentAddress_pos";
    public static final String NEW_ADDRESS = "newAddr";

    private DeliveryAddress theAddress;
    private EditText identifierEdit;
    private EditText streetEdit;
    private EditText houseEdit;
    private EditText flatEdit;
    private EditText phoneEdit;

    private Spinner townSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.address_input);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        this.identifierEdit = findViewById(R.id.address_input_text0);
        this.streetEdit = findViewById(R.id.address_input_text2);
        this.houseEdit = findViewById(R.id.address_input_text3);
        this.flatEdit = findViewById(R.id.address_input_text4);
        this.phoneEdit = findViewById(R.id.address_input_text5);

        townSpinner = findViewById(R.id.address_input_spinner1);

        Button add = findViewById(R.id.address_input_add_address);
        if (getIntent().getBooleanExtra("DELETE", false)) {
            add.setVisibility(View.INVISIBLE);
            this.identifierEdit.setClickable(false);
            this.identifierEdit.setFocusable(false);

            this.streetEdit.setClickable(false);
            this.streetEdit.setFocusable(false);

            this.houseEdit.setClickable(false);
            this.houseEdit.setFocusable(false);

            this.flatEdit.setClickable(false);
            this.flatEdit.setFocusable(false);
        } else {
            add.setOnClickListener(this);
        }

        ArrayAdapter sizeAdapter = ArrayAdapter.createFromResource(this, R.array.towns, android.R.layout.simple_spinner_item);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        townSpinner.setAdapter(sizeAdapter);

        this.theAddress = (DeliveryAddress) getIntent().getSerializableExtra(ADDRESS_INTENT);

        identifierEdit.setText(this.theAddress.getIdentifier());

        if (this.theAddress.getTown() != DeliveryAddress.Town.MAGICAL_FAIRY_SPACE_TOWN) {
            streetEdit.setText(this.theAddress.getStreet());
            houseEdit.setText(this.theAddress.getHouse());
            flatEdit.setText(this.theAddress.getFlat());
            townSpinner.setSelection(this.theAddress.getTown().ordinal());
        }

        TelephonyManager tMgr = (TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        String mPhoneNumber = tMgr.getLine1Number();
        phoneEdit.setText(mPhoneNumber);
        phoneEdit.setFocusable(false);
        phoneEdit.setClickable(false);

    }

    @Override
    public void onClick(View v) {
        DeliveryAddress adr = this.theAddress;
        Intent i = DetailsAddress.this.getIntent();
        DeliveryAddress da2 = new DeliveryAddress();
        try {
            da2.setIdentifier(this.identifierEdit.getText().toString());
            da2.setTown(DeliveryAddress.Town.values()[this.townSpinner.getSelectedItemPosition()]);
            da2.setStreet(this.streetEdit.getText().toString());
            da2.setHouse(this.houseEdit.getText().toString());
            da2.setFlat(this.flatEdit.getText().toString());
            i.putExtra(NEW_ADDRESS, da2);
            this.setResult(1, i);
            this.finish();

        } catch (AddressFormatException e) {
            Toast.makeText(getBaseContext(), "Uzupelnij puste pola", Toast.LENGTH_LONG).show();
        }


    }
}
