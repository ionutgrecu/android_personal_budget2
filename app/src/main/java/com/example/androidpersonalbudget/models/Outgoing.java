package com.example.androidpersonalbudget.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.sql.Date;

@Entity(tableName = "outgoings")
public class Outgoing implements Serializable {
    @PrimaryKey(autogenerate=true)
    private int id;

    private float amount;

    private String description;

    private Date date;

    public Outgoing(int id, float amount, String description, Date date) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    @Ignore
    public Outgoing(float amount, String description, Date date) {
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Outgoing{" +
                "id=" + id +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
