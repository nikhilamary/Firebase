package com.example.firebase;

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
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText resetEmail;
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        mAuth = FirebaseAuth.getInstance();

        resetEmail = findViewById(R.id.editText3);
        submitBtn = findViewById(R.id.button4);
    }

    public void submitBtn(View view) {

        String email = resetEmail.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this,"Enter a valid email",Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener(this, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()) {

                        Intent i = new Intent(forgotpassword.this, signup.class);
                        startActivity(i);
                    }
                }
            });
        }


    }
}

