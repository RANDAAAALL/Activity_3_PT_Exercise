package com.example.activity_3_ipt;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Activity 3 IPT
    // Made by: Lester Jon R. Andig - 2A

    private Button SignInBtn;
    private Button SignUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // Initialize views
        SignInBtn = findViewById(R.id.SignInBtn);
        SignUpBtn = findViewById(R.id.SignUpBtn);


        SignInBtn.setOnClickListener(v -> {new ShowLoadingScreen().showDialog(MainActivity.this,SignInPage.class);});
        SignUpBtn.setOnClickListener(v -> {new ShowLoadingScreen().showDialog(MainActivity.this,SignUpPage.class);});
    }
}