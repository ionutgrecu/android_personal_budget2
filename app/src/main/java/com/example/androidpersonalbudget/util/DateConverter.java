package com.example.androidpersonalbudget.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import androidx.room.TypeConverter;

public class DateConverter {
    @TypeConverter
    public static Date toDate(Long timestamp){
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date){
        return date == null ? null : date.getTime();
    }
}
