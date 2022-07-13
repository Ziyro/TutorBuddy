package com.safe.tutorbuddy;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {

    // TODO: Add member variables here:
    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private FirebaseAuth mAuth;//this variable to authenticate the user

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailView = findViewById(R.id.login_email);
        mPasswordView = findViewById(R.id.login_password);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.integer.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        //  instance of FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

    }

    // Executed when Sign in button pressed
    public void signInExistingUser(View v)   {
        // Call attemptLogin() method to try and log in user
        attemptLogin();

    }
    // Executed when Register button pressed
    public void registerNewUser(View v) {
        Intent intent = new Intent(this, com.safe.tutorbuddy.RegisterActivity.class);
        finish();
        startActivity(intent);
    }

    // method to check credentials, connect to firebase and log in user if correct
    private void attemptLogin() {
        String email = mEmailView.getText().toString(); //get the email
        String password = mPasswordView.getText().toString();//get the password
        if (email.isEmpty() || password.isEmpty()) //check so its not left empty
            if (email.equals("") || password.equals("")) {//give an alert to user
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
                showErrorDialog("Please enter both email and password");
                return;
            }
        Toast.makeText(this, "Login in progress...", Toast.LENGTH_SHORT).show();
        // FirebaseAuth to sign in with email & password
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("firebase", "signInWithEmail() onComplete: " + task.isSuccessful());
                if (!task.isSuccessful()) {
                    Log.d("firebase", "Problem signing in: " + task.getException());//exception gives us info why not successful
                    showErrorDialog("There was a problem signing in");// letting user know about erro in signing in
                } else {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    finish();//finish login activity and start the next one if log in successful

                    startActivity(intent);
                }
            }
        });
    }

    // Show error on screen with an alert dialog if login not successful
        private void showErrorDialog(String message) {
            new AlertDialog.Builder(this)
            .setTitle("Oops")
            .setMessage(message)
            .setPositiveButton(android.R.string.ok, null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show();
        }

}