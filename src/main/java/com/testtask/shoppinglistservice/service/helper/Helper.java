package com.testtask.shoppinglistservice.service.helper;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Helper {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd");

    public static boolean dateIsValid(String date){

        try {
            return DATE_FORMAT.format(DATE_FORMAT.parse(date)).equals(date);
        }catch (ParseException ex){
            return false;
        }
    }
}
