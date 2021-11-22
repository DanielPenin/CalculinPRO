package com.phivehicle.calculinpro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

// Pedro Amador Diaz
// 07/08/2021
// For Phivehicle

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "CalculonPRO";
    private enum Operations {add, sub, div, mul, equal}
    private Operations lastOperation = null;
    private Double num1 = 0d, num2 = 0d;
    private boolean buttonOperationActivate = false;
    private Button btClear, btZero, btOne, btTwo, btThree, btFour, btFive, btSix, btSeven, btEight, btNine;
    private Button btdivide, btMultiply, btAdd, btSubstract, btEqual, btDot;
    private ImageView ivBack;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btZero = findViewById(R.id.btZero);
        btZero.setOnClickListener(this);

        btOne = findViewById(R.id.btOne);
        btOne.setOnClickListener(this);

        btTwo = findViewById(R.id.btTwo);
        btTwo.setOnClickListener(this);

        btThree = findViewById(R.id.btThree);
        btThree.setOnClickListener(this);

        btFour = findViewById(R.id.btFour);
        btFour.setOnClickListener(this);

        btFive = findViewById(R.id.btFive);
        btFive.setOnClickListener(this);

        btSix = findViewById(R.id.btSix);
        btSix.setOnClickListener(this);

        btSeven = findViewById(R.id.btSeven);
        btSeven.setOnClickListener(this);

        btEight = findViewById(R.id.btEight);
        btEight.setOnClickListener(this);

        btNine = findViewById(R.id.btNine);
        btNine.setOnClickListener(this);

        btDot = findViewById(R.id.btDot);
        btDot.setOnClickListener(this);

        btEqual = findViewById(R.id.btEqual);
        btEqual.setOnClickListener(this);

        btAdd = findViewById(R.id.btAdd);
        btAdd.setOnClickListener(this);

        btSubstract = findViewById(R.id.btSubtract);
        btSubstract.setOnClickListener(this);

        btMultiply = findViewById(R.id.btMultiply);
        btMultiply.setOnClickListener(this);

        btdivide = findViewById(R.id.btDivide);
        btdivide.setOnClickListener(this);

        btClear = findViewById(R.id.btClear);
        btClear.setOnClickListener(this);

        ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(this);

        tvResult = findViewById(R.id.tvResult);

    }

    @Override
    public void onClick(View view) {

        if(view == btZero)
            addNumber("0");

        if(view == btOne)
            addNumber("1");

        if(view == btTwo)
            addNumber("2");

        if(view == btThree)
            addNumber("3");

        if(view == btFour)
            addNumber("4");

        if(view == btFive)
            addNumber("5");

        if(view == btSix)
            addNumber("6");

        if(view == btSeven)
            addNumber("7");

        if(view == btEight)
            addNumber("8");

        if(view == btNine)
            addNumber("9");

        if(view == btDot && !tvResult.getText().toString().contains("."))
            addNumber(".");

        if(view == ivBack){
            if(tvResult.getText().length() > 1) // Borramos el ultimo valor introducido
                tvResult.setText(tvResult.getText().subSequence(0, tvResult.getText().length() - 1));
            else
                tvResult.setText("0");
        }


        if(view == btClear && tvResult.getText().length() > 0){ // Borramos todo el contenido del display
            tvResult.setText("0");
            lastOperation = null;
            num1 = null;
        }

        if(view == btAdd)
            arithmeticOperation(Operations.add);

        if(view == btSubstract)
            arithmeticOperation(Operations.sub);

        if(view == btMultiply)
            arithmeticOperation(Operations.mul);

        if(view == btdivide)
            arithmeticOperation(Operations.div);

        if(view == btEqual && lastOperation != null)
            arithmeticOperation(Operations.equal);

    }

    // Añade un nuevo numero a la cadena que aparece en el display
    private void addNumber(String number){

        if(lastOperation == Operations.equal){
            lastOperation = null;
            tvResult.setText("0");
        }

        if(buttonOperationActivate){
            buttonOperationActivate = false;
            tvResult.setText("0");}

        if(tvResult.getText().toString().equals("0") && !number.equals("."))
            tvResult.setText("");

        if(tvResult.getText().length() < 10)
            tvResult.setText(tvResult.getText() + number);
    }

    // Realiza la operacion aritmetica basica seleccionada
    private void arithmeticOperation(Operations operation){

        buttonOperationActivate = true;

        try {
            num2 = Double.parseDouble(tvResult.getText().toString());
        }catch (NumberFormatException e) {
            Log.i(TAG, e.getMessage());
        }

        if(lastOperation != null && operation == Operations.equal){
            if(lastOperation == Operations.add)
                num1 = num1 + num2;
            if(lastOperation == Operations.sub)
                num1 = num1 - num2;
            if(lastOperation == Operations.mul)
                num1 = num1 * num2;
            if(lastOperation == Operations.div)
                num1 = num1 / num2;
        }
        else{
            lastOperation = operation;
            num1 = num2;}

        if(num1 % 1 == 0){
            tvResult.setText(String.valueOf(num1.intValue()));
        }
        else
            tvResult.setText(String.valueOf(num1));



        lastOperation = operation;

    }
}
