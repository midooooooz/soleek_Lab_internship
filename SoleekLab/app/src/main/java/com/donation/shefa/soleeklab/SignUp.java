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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    Button btnsignup;
    TextView btnLogin,btnForgetpassword;
    EditText input_email,inputpass,confirm_pass;
    RelativeLayout activity_sign_Up;
    private FirebaseAuth auth;
    Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnsignup = (Button) findViewById(R.id.signup_btn_register);
        btnLogin = (TextView) findViewById(R.id.signup_btn_login);
        btnForgetpassword = (TextView) findViewById(R.id.signup_btn_forget_password);
        input_email = (EditText) findViewById(R.id.signup_email);
        inputpass = (EditText) findViewById(R.id.signup_password);
        confirm_pass = (EditText)findViewById(R.id.signup_confirm_password);
        activity_sign_Up = (RelativeLayout)findViewById(R.id.activaty_sign_up);

        btnsignup.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnForgetpassword.setOnClickListener(this);


        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.signup_btn_login){

            startActivity(new Intent(SignUp.this,MainActivity.class));
            finish();
        }

        else if (view.getId() == R.id.signup_btn_forget_password){

            startActivity(new Intent(SignUp.this,ForgetPassword.class));
            finish();
        }
        else if (view.getId() == R.id.signup_btn_register){

             if(!inputpass.getText().toString().equals(confirm_pass.getText().toString())) {

                 Toast.makeText(SignUp.this, "Password not match", Toast.LENGTH_SHORT).show();

            }

            else {
                 signUpUser(input_email.getText().toString(), inputpass.getText().toString());
             }
        }





    }

    private void signUpUser(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful())
                        {
                            snackbar = Snackbar.make(activity_sign_Up,"Error: "+task.getException(),Snackbar.LENGTH_SHORT);
                            snackbar.show();
                        }

                        else if(task.isSuccessful())
                        {
                            snackbar = Snackbar.make(activity_sign_Up,"Register Success:  "+task.getException(),Snackbar.LENGTH_SHORT);
                            snackbar.show();
                            startActivity(new Intent(SignUp.this,DashBoard.class));
                            finish();
                        }
                    }
                });
    }
}
