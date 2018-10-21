package com.donation.shefa.soleeklab;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button btnLogin;
    EditText input_email,input_password;
    TextView btnSignUp,btnForgetPass;

   RelativeLayout activity_main;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
       // AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);


        btnLogin = (Button)findViewById(R.id.login_btn_btn);
        input_email = (EditText)findViewById(R.id.login_email);
        input_password = (EditText)findViewById(R.id.login_password);
        btnSignUp = (TextView)findViewById(R.id.login_btn_signup);
        btnForgetPass = (TextView)findViewById(R.id.login_btn_forget_password);
        activity_main = (RelativeLayout)findViewById(R.id.activaty_main) ;


        btnSignUp.setOnClickListener(this);
        btnForgetPass.setOnClickListener(this);
        btnLogin.setOnClickListener(this);


        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null)

            startActivity(new Intent(MainActivity.this,DashBoard.class));




    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.login_btn_forget_password)
        {

            startActivity(new Intent(MainActivity.this,ForgetPassword.class));
            finish();
        }

        else  if (view.getId() == R.id.login_btn_signup)
        {

            startActivity(new Intent(MainActivity.this,SignUp.class));
            finish();
        }
        else  if (view.getId() == R.id.login_btn_btn)
        {

            loginUser(input_email.getText().toString(),input_password.getText().toString());
        }

    }

    private void loginUser(String email, final String password) {

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful())
                        {
                            if (password.length()<6)
                            {
                                Snackbar snackbar = Snackbar.make(activity_main,"Password length must be over 6 ",Snackbar.LENGTH_SHORT);
                                        snackbar.show();
                            }
                        }
                        else
                            {
                                startActivity(new Intent(MainActivity.this,DashBoard.class));


                        }
                    }
                });
    }
}
