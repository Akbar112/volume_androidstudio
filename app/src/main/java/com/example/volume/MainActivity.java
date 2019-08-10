package com.example.volume;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtWidth, edtHeight, edtLength;
    private Button btnCalculate;
    private TextView tvResult;
    private static final String STATE_RESULT = "state_result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtWidth = findViewById(R.id.edt_width);
        edtHeight = findViewById(R.id.edt_height);
        edtLength = findViewById(R.id.edt_length);
        btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);

        btnCalculate.setOnClickListener(this);

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, tvResult.getText().toString());
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_calculate) {
            String inputLenght = edtLength.getText().toString().trim();
            String inputWidth = edtWidth.getText().toString().trim();
            String inputHeight = edtHeight.getText().toString().trim();

            boolean isEmptyFields = false;
            boolean isInvalidDouble = false;

            if (TextUtils.isEmpty(inputLenght)) {
                isEmptyFields = true;
                edtLength.setError("Kolom ini ngga boleh kosong");
            }

            if (TextUtils.isEmpty(inputWidth)) {
                isEmptyFields = true;
                edtWidth.setError("Kolom ini ngga boleh kosong");
            }

            if (TextUtils.isEmpty(inputHeight)) {
                isEmptyFields = true;
                edtHeight.setError("Kolom ini ngga boleh kosong");
            }

            Double length = toDouble(inputLenght);
            Double width = toDouble(inputWidth);
            Double height = toDouble(inputHeight);

            if (length == null) {
                isInvalidDouble = true;
                edtLength.setError("Kolom ini harus berupa nomer yang valid");
            }

            if (width == null) {
                isInvalidDouble = true;
                edtWidth.setError("Kolom ini harus berupa nomer yang valid");
            }

            if (height == null) {
                isInvalidDouble = true;
                edtHeight.setError("Kolom ini harus berupa nomer yang valid");
            }

            if (!isEmptyFields && !isInvalidDouble) {
                double volume = length * width * height;

                tvResult.setText(String.valueOf(volume));
            }
        }
    }

    public Double toDouble(String str) {
        try {
            return Double.valueOf(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
