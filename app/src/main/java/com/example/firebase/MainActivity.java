package com.example.firebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button login;
    Button signup;
    EditText inputEmail,inputPassword;
    TextView forgotpassword;


    private FirebaseAuth auth;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        login=findViewById(R.id.button);
        signup=findViewById(R.id.button2);
        inputEmail = (EditText) findViewById(R.id.editText);
        inputPassword = (EditText) findViewById(R.id.editText2);
        forgotpassword=findViewById(R.id.textView2);

        progressBar = new ProgressBar(this);

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(MainActivity.this, MainActivity.class));
            finish();
        }

//        public void logout(View view) {
//            auth.signOut();
//            //closing activity
//            finish();
//            // starting login activity
//            startActivity(new Intent(this, .class));
//             }


        //  auth = FirebaseAuth.getInstance();


        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, forgotpassword.class);
                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, signup.class);
                startActivity(intent);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString().trim();
                final String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(email) && TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(), "Enter EmailID and password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(getApplicationContext(),datepicker.class);
                                    startActivity(intent);

                                    //Toast.makeText(MainActivity.this, " Login was Successful", Toast.LENGTH_LONG).show();
                                }else
                                {
                                    Toast.makeText(MainActivity.this, " Login was not Successful", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });


    }
}


