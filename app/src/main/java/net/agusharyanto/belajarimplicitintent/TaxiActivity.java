package net.agusharyanto.belajarimplicitintent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class TaxiActivity extends AppCompatActivity {

    String[] arrtaxi = {
            "Bluebird",
            "Express",
            "Prima Jasa"

    };
    String [] arrnotaxi ={"021222", "02129393", "0212828"};
    Spinner spinnerTaxi;
    Button buttonCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxi);
        spinnerTaxi = (Spinner) findViewById(R.id.spinnerTaxi);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, arrtaxi);

        spinnerTaxi.setAdapter(adapter);
        buttonCall = (Button) findViewById(R.id.buttonCall);

        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callTaxi();
            }
        });


    }

    private void callTaxi() {
        String notlptaxi="";
        int posselect = spinnerTaxi.getSelectedItemPosition();
        notlptaxi= arrnotaxi[posselect];
        Log.d("TaxiActivity","notlptaxi:"+notlptaxi);
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+notlptaxi));
        startActivity(intent);
    }
}
