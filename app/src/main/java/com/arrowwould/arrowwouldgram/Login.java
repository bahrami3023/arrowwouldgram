package com.arrowwould.arrowwouldgram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.arrowwould.arrowwouldgram.ReusableCode.ReusableCodeForAll;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private TextView createacc;
    private TextInputLayout Email, Pass;
    private Button login;
    private TextView Forgotpassword;
    private FirebaseAuth FAuth;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize UI elements
        createacc = findViewById(R.id.signup);
        Email = findViewById(R.id.login_email);
        Pass = findViewById(R.id.login_password);
        login = findViewById(R.id.Login_btn);
        Forgotpassword = findViewById(R.id.forgotpass);

        // Initialize FirebaseAuth
        FAuth = FirebaseAuth.getInstance();

        // Set click listener for the Create Account text
        createacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Registration.class);
                startActivity(intent);
                finish();
            }
        });

        // Set click listener for the Login button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = Email.getEditText().getText().toString().trim();
                password = Pass.getEditText().getText().toString().trim();

                if (isValid()) {
                    final ProgressDialog mDialog = new ProgressDialog(Login.this);
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.setCancelable(false);
                    mDialog.setMessage("Logging in...");
                    mDialog.show();

                    FAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            mDialog.dismiss();
                            if (task.isSuccessful()) {
                                if (FAuth.getCurrentUser().isEmailVerified()) {
                                    Toast.makeText(Login.this, "You are logged in", Toast.LENGTH_SHORT).show();
                                    Intent z = new Intent(Login.this, Home.class);
                                    startActivity(z);
                                    finish();
                                } else {
                                    ReusableCodeForAll.ShowAlert(Login.this, "", "Please Verify your Email");
                                }
                            } else {
                                ReusableCodeForAll.ShowAlert(Login.this, "Error", task.getException().getMessage());
                            }
                        }
                    });
                }
            }
        });

        // Set click listener for the Forgot Password text
//        Forgotpassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent a = new Intent(Login.this, ForgotPassword.class);
//                startActivity(a);
//                finish();
//            }
//        });
    }

    // Email validation pattern
    private final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    // Method to validate email and password
    public boolean isValid() {
        Email.setErrorEnabled(false);
        Email.setError("");
        Pass.setErrorEnabled(false);
        Pass.setError("");

        boolean isValidEmail = false, isValidPassword = false;

        if (TextUtils.isEmpty(email)) {
            Email.setErrorEnabled(true);
            Email.setError("Email is required");
        } else {
            if (email.matches(emailPattern)) {
                isValidEmail = true;
            } else {
                Email.setErrorEnabled(true);
                Email.setError("Enter a valid Email Address");
            }
        }

        if (TextUtils.isEmpty(password)) {
            Pass.setErrorEnabled(true);
            Pass.setError("Password is required");
        } else {
            isValidPassword = true;
        }

        return isValidEmail && isValidPassword;
    }
}


//package com.arrowwould.arrowwouldgram;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.arrowwould.arrowwouldgram.ReusableCode.ReusableCodeForAll;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.android.material.textfield.TextInputLayout;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//
//
//
//public class Login extends AppCompatActivity {
//
//    TextView createacc;
//    TextInputLayout Email, Pass;
//    Button login;
//    TextView Forgotpassword;
//    TextView loginwithfacebook;
//    FirebaseAuth FAuth;
//    String email;
//    String password;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        createacc = (TextView) findViewById(R.id.signup);
////        createacc.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent intent = new Intent(Login.this, Registration.class);
////                startActivity(intent);
////                finish();
////            }
////        });
//
//        try {
//            Email = (TextInputLayout) findViewById(R.id.login_email);
//            Pass = (TextInputLayout) findViewById(R.id.login_password);
//            login = (Button) findViewById(R.id.Login_btn);
//            loginwithfacebook = (TextView) findViewById(R.id.login_facebook);
//            Forgotpassword = (TextView) findViewById(R.id.forgotpass);
//
//
//            FAuth = FirebaseAuth.getInstance();
//
//            login.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    email = Email.getEditText().getText().toString().trim();
//                    password = Pass.getEditText().getText().toString().trim();
//                    if (isValid()) {
//
//                        final ProgressDialog mDialog = new ProgressDialog(Login.this);
//                        mDialog.setCanceledOnTouchOutside(false);
//                        mDialog.setCancelable(false);
//                        mDialog.setMessage("Logging in...");
//                        mDialog.show();
//                        FAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful()) {
//
//                                    mDialog.dismiss();
//                                    if (FAuth.getCurrentUser().isEmailVerified()) {
//                                        mDialog.dismiss();
//                                        Toast.makeText(Login.this, "You are logged in", Toast.LENGTH_SHORT).show();
//                                        Intent z = new Intent(Login.this, Home.class);
//                                        startActivity(z);
//                                        finish();
//
//
//                                    } else {
//                                        ReusableCodeForAll.ShowAlert(Login.this, "", "Please Verify your Email");
//                                    }
//
//                                } else {
//
//                                    mDialog.dismiss();
//                                    ReusableCodeForAll.ShowAlert(Login.this, "Error", task.getException().getMessage());
//                                }
//                            }
//                        });
//
//                    }
//                }
//            });
//
////            txt.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////
////                    Intent Register = new Intent(ChefLogin.this, ChefRegisteration.class);
////                    startActivity(Register);
////                    finish();
////
////                }
////            });
//        createacc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Login.this, Registration.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//
////            Forgotpassword.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    Intent a = new Intent(Login.this, ForgotPassword.class);
////                    startActivity(a);
////                    finish();
////
////                }
////            });
//
//        } catch (Exception e) {
//            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//
//        }
//    }
//    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
//
//    public boolean isValid() {
//        Email.setErrorEnabled(false);
//        Email.setError("");
//        Pass.setErrorEnabled(false);
//        Pass.setError("");
//
//        boolean isvalidemail = false, isvalidpassword = false, isvalid = false;
//        if (TextUtils.isEmpty(email)) {
//            Email.setErrorEnabled(true);
//            Email.setError("Email is required");
//        } else {
//            if (email.matches(emailpattern)) {
//                isvalidemail = true;
//            } else {
//                Email.setErrorEnabled(true);
//                Email.setError("Enter a valid Email Address");
//            }
//
//        }
//        if (TextUtils.isEmpty(password)) {
//            Pass.setErrorEnabled(true);
//            Pass.setError("Password is required");
//        } else {
//            isvalidpassword = true;
//        }
//        isvalid = (isvalidemail && isvalidpassword) ? true : false;
//        return isvalid;
//    }
//}
