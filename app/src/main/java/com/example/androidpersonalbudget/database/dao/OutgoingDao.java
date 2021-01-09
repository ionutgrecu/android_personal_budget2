package com.example.androidpersonalbudget.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.androidpersonalbudget.database.models.Outgoing;

import java.util.List;

@Dao
public interface OutgoingDao {

    @Query("SELECT * FROM outgoings")
    public List<Outgoing> getAll();

    @Insert
    long insert(Outgoing outgoing);

    @Update
    int update(Outgoing outgoing);

    @Delete
    int delete(Outgoing outgoing);

}
