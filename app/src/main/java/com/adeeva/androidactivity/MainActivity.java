package com.adeeva.androidactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtLength;
    private EditText edtWidth;
    private EditText edtHeight;
    private Button btnCalculate;
    private TextView tvResult;

    private static final String STATE_RESULT = "state_result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtLength = findViewById(R.id.edt_length);
        edtWidth = findViewById(R.id.edt_width);
        edtHeight = findViewById(R.id.edt_height);
        btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);

        btnCalculate.setOnClickListener(this);

        if (savedInstanceState != null){
            String result = savedInstanceState.getString(STATE_RESULT);
            tvResult.setText(result);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_calculate){
            String inputLength = edtLength.getText().toString().trim();
            String inputWidth = edtWidth.getText().toString().trim();
            String inputHeight = edtHeight.getText().toString().trim();

            boolean isEmptyFields = false;
            boolean isInvalidDouble = false;

            if (TextUtils.isEmpty(inputLength)){
                isEmptyFields = true;
                edtLength.setError("This field cannot be empty");
            }

            if (TextUtils.isEmpty(inputWidth)){
                isEmptyFields = true;
                edtWidth.setError("This field cannot be empty");
            }

            if (TextUtils.isEmpty(inputHeight)){
                isEmptyFields = true;
                edtHeight.setError("This field cannot be empty");
            }

            Double length = toDouble(inputLength);
            Double width = toDouble(inputWidth);
            Double height = toDouble(inputHeight);

            if (length == null){
                isInvalidDouble = true;
                edtLength.setError("This field must be a valid number");
            }

            if (width == null){
                isInvalidDouble = true;
                edtWidth.setError("This field must be a valid number");
            }

            if (height == null){
                isInvalidDouble = true;
                edtHeight.setError("This field must be a valid number");
            }

            if (!isEmptyFields && !isInvalidDouble){
                double volume = length * width * height;
                tvResult.setText(String.valueOf(volume));
            }

        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, tvResult.getText().toString().trim());
    }

    private Double toDouble (String str){
        try {
            return Double.valueOf(str);
        } catch (NumberFormatException e){
            return null;
        }
    }
}