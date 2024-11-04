package com.example.activity_3_ipt;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainContent extends AppCompatActivity {
    private TextView CurrentUserSignedInTextView;
    private Button LogoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_content);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        CurrentUserSignedInTextView = findViewById(R.id.CurrentUserSignedInTextView);
        LogoutBtn = findViewById(R.id.LogoutBtn);

        CurrentUserSignedInTextView.setText(new FetchCurrentUser().getUser());
//        CurrentUserSignedInTextView.setText("WEW");
        // when the user's click the button, it will go back the Main Activity
        LogoutBtn.setOnClickListener(v -> {
            new DatabaseHandler().Logout(MainContent.this);
        });
    }
}