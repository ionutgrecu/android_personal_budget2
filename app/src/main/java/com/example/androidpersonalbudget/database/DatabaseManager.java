package com.example.androidpersonalbudget.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.androidpersonalbudget.database.dao.OutgoingDao;
import com.example.androidpersonalbudget.database.models.Outgoing;
import com.example.androidpersonalbudget.util.DateConverter;

@Database(entities = {Outgoing.class}, exportSchema = false,version = 1)
@TypeConverters({DateConverter.class})
public abstract class DatabaseManager extends RoomDatabase {
    public static final String DAM_DB = "dam_db";
    private static DatabaseManager databaseManager;

    public static DatabaseManager getInstance(Context context){
        if(databaseManager==null){
            synchronized (DatabaseManager.class){
                if(databaseManager==null) {
                    databaseManager = Room.databaseBuilder(context, DatabaseManager.class, DAM_DB).fallbackToDestructiveMigration().build();
                }
            }
        }

        return databaseManager;
    }

    public abstract OutgoingDao getOutgoingDao();
}
