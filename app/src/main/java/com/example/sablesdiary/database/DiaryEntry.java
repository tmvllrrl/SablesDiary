package com.example.sablesdiary.database;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "entries")
public class DiaryEntry {

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name = "ID")
    private int ID;

    @ColumnInfo (name = "day_of_week")
    private String dayOfWeek; //Monday

    @ColumnInfo (name = "date")
    private String date; //Ex. 11/20/2020

    @ColumnInfo (name = "month")
    private int month;

    @ColumnInfo (name = "day")
    private int day; //28

    @ColumnInfo (name = "year")
    private int year;

    @ColumnInfo (name = "food_originally")
    private int foodOriginally;

    @ColumnInfo (name = "food_after")
    private int foodAfter;

    @ColumnInfo (name = "water_originally")
    private double waterOriginally;

    @ColumnInfo (name = "water_after")
    private double waterAfter;

    @ColumnInfo (name = "weight")
    private int weight;

    public DiaryEntry(String dayOfWeek,
                      String date,
                      int month,
                      int day,
                      int year,
                      int foodOriginally,
                      int foodAfter,
                      double waterOriginally,
                      double waterAfter,
                      int weight) {
        this.dayOfWeek = dayOfWeek;
        this.month = month;
        this.day = day;
        this.year = year;
        this.date = date;
        this.foodOriginally = foodOriginally;
        this.foodAfter = foodAfter;
        this.waterOriginally = waterOriginally;
        this.waterAfter = waterAfter;
        this.weight = weight;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public int getID() {
        return this.ID;
    }

    public String getDayOfWeek() {
        return this.dayOfWeek;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public String getDate() {
        return this.date;
    }

    public int getFoodOriginally() {
        return foodOriginally;
    }

    public int getFoodAfter() {
        return foodAfter;
    }

    public double getWaterOriginally() {
        return waterOriginally;
    }

    public double getWaterAfter() {
        return waterAfter;
    }

    public int getWeight() {
        return weight;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setFoodOriginally(int foodOriginally) {
        this.foodOriginally = foodOriginally;
    }

    public void setFoodAfter(int foodAfter) {
        this.foodAfter = foodAfter;
    }

    public void setWaterOriginally(double waterOriginally) {
        this.waterOriginally = waterOriginally;
    }

    public void setWaterAfter(double waterAfter) {
        this.waterAfter = waterAfter;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


}
