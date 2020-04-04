package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView editText;
    String numberDisplay = "";
    Double valueas = 0.0 , value1 = 0.0 , value2 = 0.0, valuemd = 1.0, ans = 0.0 ;
    int count1 = 0 , count2 = 0 , countdot = 0;
    boolean add = false , sub = false , mult = false , div = false , bang = false , cl = false , sq = false , pow2 = false , log = false ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int [] id = {R.id.Number0, R.id.Number1 , R.id.Number2, R.id.Number3 , R.id.Number4, R.id.Number5 , R.id.Number6 , R.id.Number7 ,
                R.id.Number8 , R.id.Number9 , R.id.add, R.id.sub , R.id.multiply , R.id.div, R.id.bang , R.id.CLEAR , R.id.dot , R.id.sqrt
        ,R.id.pow2 , R.id.log};
        for ( int i : id ){
            View v = findViewById(i);
            v.setOnClickListener(this);
        }
        editText = findViewById(R.id.DisplayNumber);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sqrt:
                sq = true;
                if( numberDisplay.equals("") ){
                    editText.setText("√");
                }
                break;
            case R.id.pow2:
                pow2 = true;
                if( numberDisplay.equals("") ){
                    editText.setText("");
                } else {
                     editText.setText("");
                     editText.setText(numberDisplay + "^2");
                }
                break;
            case R.id.log:
                log = true;
                if( numberDisplay.equals("") ){
                    editText.setText("log(");
                }
                break;
            case R.id.add:
                countdot = 0;
                tong();
                break;
            case R.id.sub:
                countdot = 0;
                hieu();
                break;
            case R.id.multiply:
                countdot = 0;
                tich();
                break;
            case R.id.div:
                countdot = 0;
                chia();
                break;
            case R.id.bang:
                if ( sq == false && pow2 == false &&  log == false){
                    bang = true;
                    countdot = 0;
                    value2 = Double.parseDouble(editText.getText().toString());
                    if ( add == true ) ans = valueas + value2;
                    if ( sub == true ) ans = valueas - value2;
                    if ( mult == true ) ans = valuemd * value2;
                    if ( div == true ) {
                        if (value2 == 0 ) ans = Double.POSITIVE_INFINITY;
                        else ans = valuemd/value2;
                    }
                    editText.setText(String.valueOf(ans));
                }
                else if (sq == true && pow2 == false && log == false){
                    editText.setText(String.valueOf("√" + numberDisplay + " = " + Math.sqrt(Double.valueOf(numberDisplay))));
                    sq = false;
                }
                else if ( sq == false && pow2 == true && log == false){
                    editText.setText(String.valueOf( numberDisplay + "^2  = " + Math.pow(Double.valueOf(numberDisplay), 2)));
                    pow2 = false;
                }
                else if ( sq == false && pow2 == false && log == true){
                    editText.setText("log(" + numberDisplay + ") =" + String.valueOf(Math.log(Double.parseDouble(numberDisplay))));
                    log = false;
                }
                break;
            case R.id.CLEAR:
                cl = true;
                clear();
                break;
            case R.id.dot:
                countdot++;
                if ( countdot == 1 && !numberDisplay.equals("+") && !numberDisplay.equals("-") && !numberDisplay.equals("x")
                        && !numberDisplay.equals("/")){
                    numberDisplay += ".";
                    editText.setText(numberDisplay);
                }
                else if ( countdot > 1 || numberDisplay.equals("+") || numberDisplay.equals("-") || numberDisplay.equals("x")
                || numberDisplay.equals("/") ){}
                break;
            default:
                numberDisplay += ((Button)v).getText();
                editText.setText(numberDisplay);
        }
    }

    private void clear (){
        numberDisplay = "";
        editText.setText("CLEAR");
        if ( bang == true ){
            countdot = 0;
            if (add == true) {ans = 0.0;tong();}
            if ( sub == true ) {ans = 0.0;hieu();}
            if (mult == true ) {ans = 1.0 ; tich();}
            if ( div == true ) {ans = 1.0 ;chia();}

        }
        else { value1 = 0.0 ; value2 = 0.0 ; valueas = 0.0; valuemd = 1.0 ; countdot = 0;}
    }

    private void tong(){
        if ( numberDisplay.equals("") && add == false && sub == false && mult == false && div == false){
            editText.setText("");
        }
        else{
            add = true;
            sub = false;
            mult = false;
            div = false;
            if ( bang == false ) {
                value1 = Double.parseDouble(numberDisplay);
                numberDisplay = "";
                editText.setText("");
                valueas += value1;
                editText.setText("+");
            }
            else {
                valueas = ans;
                numberDisplay = "";
                editText.setText("");
                if ( cl == false ) editText.setText("+");
                else {
                    editText.setText("CLEAR");
                    cl = false;
                }
                bang = false;
            }
        }
    }

    private void hieu(){
        if ( numberDisplay.equals("") && add == false && sub == false && mult == false && div == false){
            editText.setText("");
        }
        else {
            sub = true;
            add = false;
            mult = false;
            div = false;
            if ( bang == false ) {
                value1 = Double.parseDouble(numberDisplay);
                if(count1 == 0 ){
                    valueas = value1;
                    count1++;
                }else{
                    valueas -= value1;
                }
                numberDisplay = "";
                editText.setText("");
                editText.setText("-");
            }
            else {
                valueas = ans;
                numberDisplay = "";
                editText.setText("");
                if ( cl == false ) editText.setText("-");
                else {
                    editText.setText("CLEAR");
                    cl = false;
                }
                bang = false;
            }
        }
    }

    private void tich(){
        if ( numberDisplay.equals("") && add == false && sub == false && mult == false && div == false){
            editText.setText("");
        }
        else {
            add = false;
            sub = false;
            mult = true;
            div = false;
            if ( bang == false ) {
                value1 = Double.parseDouble(numberDisplay);
                numberDisplay = "";
                editText.setText("");
                valuemd *= value1;
                editText.setText("x");
            }
            else {
                valuemd = ans;
                numberDisplay = "";
                editText.setText("");
                if ( cl == false ) editText.setText("x");
                else {
                    editText.setText("CLEAR");
                    cl = false;
                }
                bang = false;
            }
        }
    }

    private void chia(){
        if ( numberDisplay.equals("") && add == false && sub == false && mult == false && div == false){
            editText.setText("");
        }
        else{
            add = false;
            sub = false;
            mult = false;
            div = true;
            if ( bang == false ) {
                value1 = Double.parseDouble(numberDisplay);
                if(count2 == 0 ){
                    valuemd = value1;
                    count2++;
                }else{
                    valuemd /= value1;
                }
                numberDisplay = "";
                editText.setText("");
                editText.setText("/");
            }
            else {
                valuemd = ans;
                numberDisplay = "";
                editText.setText("");
                if ( cl == false ) editText.setText("/");
                else {
                    editText.setText("CLEAR");
                    cl = false;
                }
                bang = false;
            }
        }
    }
}

