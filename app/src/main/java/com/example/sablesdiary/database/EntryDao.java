package com.example.sablesdiary.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EntryDao {

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    void insert(DiaryEntry entry);

    @Query("SELECT * FROM entries ORDER BY month DESC, day DESC")
    LiveData<List<DiaryEntry>> getEntries();

    @Query("SELECT * FROM entries WHERE id = :id")
    LiveData<DiaryEntry> getEntryById(int id);

    @Update
    void update(DiaryEntry entry);

    @Query("DELETE FROM entries")
    void deleteAll();

    @Query("DELETE FROM entries WHERE ID = :id")
    void deleteById(int id);

//    @Query("UPDATE entries SET day_of_week = :dayOfWeek, " +
//            "date = :date, " +
//            "food_eaten_overnight = :foodOvernight, " +
//            "food_eaten_during_day = :foodDay, " +
//            "water_drinken_overnight = :waterOvernight, " +
//            "water_drinken_during_day = :waterDay," +
//            "weight = :weight WHERE ID = :id")
//    void update(String dayOfWeek, String date, Double foodOvernight, Double foodDay, Double waterOvernight, Double waterDay, Double weight, int id);
}
