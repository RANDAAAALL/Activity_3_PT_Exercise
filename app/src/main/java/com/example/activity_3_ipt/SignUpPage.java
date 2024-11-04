package com.example.activity_3_ipt;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class SignUpPage extends AppCompatActivity {
    private TextInputLayout textInputLayout1, textInputLayout2;
    private TextInputEditText input_email, input_password;
    private Button SubmitButton;
    private TextView goBackArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        textInputLayout1 = findViewById(R.id.textInputLayout1);
        textInputLayout2 = findViewById(R.id.textInputLayout2);
        input_email = findViewById(R.id.input_email);
        input_password = findViewById(R.id.input_password);
        SubmitButton = findViewById(R.id.SubmitButton);
        goBackArrow = findViewById(R.id.goBackArrow);

        // Text fields aesthetics
        TextInputLayout[] layoutsArray = {textInputLayout1,textInputLayout2};
        TextInputEditText[] editTextArray = {input_email,input_password};

        for(int i = 0; i < layoutsArray.length; i++){
            final TextInputLayout layout = layoutsArray[i];
            final TextInputEditText text = editTextArray[i];

            if(text.getText().toString().isEmpty()){
                layout.setHelperText("Required*");
                layout.setBoxStrokeColor(Color.parseColor("#FFFF0000"));
            }

            text.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(text.getText().toString().isEmpty()){
                        layout.setHelperText("Required*");
                        layout.setBoxStrokeColor(Color.parseColor("#FFFF0000"));
                    }
                    else{
                        layout.setHelperText(null);
                        layout.setBoxStrokeColor(Color.parseColor("#00FF00"));
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {}
            });
        }

        SubmitButton.setOnClickListener(v -> {
            try {

                String email = input_email.getText().toString();
                String password = input_password.getText().toString();

                if(email.isEmpty() || password.isEmpty()){
                    Toast.makeText(SignUpPage.this, "Invalid field/s must not be empty!", Toast.LENGTH_SHORT).show();
                    throw new Exception("Invalid field/s must not be empty!");
                }

                // Validate Email with '@' and '.com'
                boolean hasValidEmailFormat = Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[cC][oO][mM]$", email);
                if(!hasValidEmailFormat){
                    Toast.makeText(SignUpPage.this, "Invalid Email!", Toast.LENGTH_SHORT).show();
                    throw new Exception("Email Email!");
                }

                // Validate Password: no spaces, no special characters, between 5 and 20 length
                boolean validPassword = (password.length() >= 5 && password.length() <= 20) && (Pattern.matches("^[a-zA-Z0-9]+$", password));
                if(!validPassword){
                    Toast.makeText(SignUpPage.this, "Invalid Password!", Toast.LENGTH_SHORT).show();
                    throw new Exception("Email Password!");
                }

                // if those 2 booleans are valid then we proceed to the database insertion of data's
                new DatabaseHandler().SignUp(SignUpPage.this,email,password);

            }catch (Exception err){
                Log.d("Error", err.getMessage());
            }
        });


        goBackArrow.setOnClickListener(v -> {new ShowLoadingScreen().showDialog(SignUpPage.this,MainActivity.class);});
    }
}