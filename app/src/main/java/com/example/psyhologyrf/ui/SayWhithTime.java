package com.example.psyhologyrf.ui;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.psyhologyrf.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SayWhithTime {

    public String CurrentTime(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH.MM");
        String message = formatter.format(date);
        double discount = Double.parseDouble(message);
        System.out.println(discount);

        if (discount >  6.00 && discount < 12.00){
            return  "доброе утро";
        } else if (discount >  12.00  && discount < 18.00){
            return  "Добрый день";
        } else if (discount >  18.00 && discount < 23.00){
            return "Добрый вечер";
        } else { return "Доброй ночи";}



    }
    public void SetcolorWithCurrentTime(ConstraintLayout keyId){


        if( CurrentTime().equals("Добрый день")){
            keyId.setBackgroundResource(R.drawable.ic_gradientday_01);
        }else if(CurrentTime().equals("доброе утро")) {
            keyId.setBackgroundResource(R.drawable.ic_gradientmorning_01);
        } else if(CurrentTime().equals("Добрый вечер")){

            keyId.setBackgroundResource(R.drawable.ic_gradientevning_01);
        }else if(CurrentTime().equals("Доброй ночи")){
            keyId.setBackgroundResource(R.drawable.ic_gradientnight_01_01);
        }else {keyId.setBackgroundResource(R.drawable.ic_gradientday_01);}



    }




}
