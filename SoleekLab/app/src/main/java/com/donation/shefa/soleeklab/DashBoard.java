package com.donation.shefa.soleeklab;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.scrounger.countrycurrencypicker.library.Buttons.CountryCurrencyButton;
import com.scrounger.countrycurrencypicker.library.Country;
import com.scrounger.countrycurrencypicker.library.Currency;
import com.scrounger.countrycurrencypicker.library.Listener.CountryCurrencyPickerListener;

public class DashBoard extends AppCompatActivity implements View.OnClickListener {

    private TextView txtWelcome;
    private Button btnLogout ,CountryCurrencyButton;
    private RelativeLayout activity_dashboard;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        txtWelcome = (TextView) findViewById(R.id.dashboard_welcome);
        btnLogout = (Button) findViewById(R.id.dashboard_btn_logout);
        activity_dashboard = (RelativeLayout)findViewById(R.id.activity_dashboard);

        btnLogout.setOnClickListener(this);

        CountryCurrencyButton button = (CountryCurrencyButton) findViewById(R.id.button);
        button.setOnClickListener(new CountryCurrencyPickerListener() {
            @Override
            public void onSelectCountry(Country country) {
                if (country.getCurrency() == null) {
                    Toast.makeText(DashBoard.this,
                            String.format("name: %s\ncode: %s", country.getName(), country.getCode())
                            , Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DashBoard.this,
                            String.format("name: %s\ncurrencySymbol: %s", country.getName(), country.getCurrency().getSymbol())
                            , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onSelectCurrency(Currency currency) {

            }
        });



        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null)
            txtWelcome.setText("Welcome , "+auth.getCurrentUser().getEmail());
    }

    @Override
    public void onClick(View view) {

         if (view.getId() == R.id.dashboard_btn_logout)

            logoutUser();


    }

    private void logoutUser() {

        auth.signOut();
        if (auth.getCurrentUser() == null)
        {

            startActivity(new Intent(DashBoard.this,MainActivity.class));
            finish();
        }

    }


}
