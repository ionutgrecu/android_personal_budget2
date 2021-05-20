package com.example.androidpersonalbudget.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import androidx.room.TypeConverter;

public class DateConverter {
    private static final String FORMAT_DATE = "dd/MM/yyyy";
    private static final SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE, Locale.US);

    @TypeConverter
    public static Date toDate(Long timestamp){
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date){
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static String toString(Date value) {
        if (value == null) {
            return null;
        }
        //metoda format este utilizata pentru conversia Date to String
        return formatter.format(value);
    }

    @TypeConverter
    public static java.util.Date fromString(String value) {
        try {
            //metoda parsa este folosita pentru conversia String to Date
            return formatter.parse(value);
        } catch (ParseException e) {
            return null;
        }
    }
}
