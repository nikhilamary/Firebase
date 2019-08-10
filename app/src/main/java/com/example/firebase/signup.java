package com.example.firebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {

    Button signup;
    EditText email;
    EditText password;
    EditText confirmpassword;
    ProgressDialog progressDialog;

    FirebaseAuth authDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        authDb = FirebaseAuth.getInstance();


        signup = findViewById(R.id.button3);
        email = findViewById(R.id.editText8);
        password = findViewById(R.id.editText9);
        confirmpassword = findViewById(R.id.editText10);
        progressDialog = new ProgressDialog(this);

        if (authDb.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));

            finish();

        }


         signup.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {



                {
                    String Email = email.getText().toString().trim();
                    String Password = password.getText().toString().trim();
                    String Confirmpassword = confirmpassword.getText().toString().trim();


                    if (TextUtils.isEmpty(Email)) {
                        Toast.makeText(getApplicationContext(), "enter the email", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(Password)) {
                        Toast.makeText(getApplicationContext(), "enter the password", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(Confirmpassword)) {
                        Toast.makeText(getApplicationContext(), "please confirm the password", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    if (!Password.equals(Confirmpassword)) {
                        Toast.makeText(getApplicationContext(), "password doesn't match", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    progressDialog.setMessage("Registering the account...");
                    progressDialog.show();


                    authDb.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(signup.this, "Register success", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            } else {
                                Toast.makeText(signup.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }


                        }

                    });


                }
            }
        });
    }
}
//    @Override
//    protected void onResume() {
//        super.onResume();
//        progressDialog.setVisibility(View.GONE);
//    }
//}