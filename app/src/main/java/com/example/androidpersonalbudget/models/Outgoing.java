package com.example.androidpersonalbudget.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.sql.Date;

@Entity(tableName = "outgoings")
public class Outgoing implements Serializable {
    @PrimaryKey(autoGenerate=true)
    @ColumnInfo(name="id")
    private long id;

    @ColumnInfo(name="amount")
    private float amount;

    @ColumnInfo(name="category")
    private String category;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name="date")
    private Date date;

    public Outgoing(long id, float amount, String category, String description, Date date) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;
    }

    @Ignore
    public Outgoing(float amount, String category, String description, Date date) {
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
