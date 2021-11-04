package com.example.sablesdiary.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DiaryEntryRepository {

    private EntryDao mEntryDao;
    private LiveData<List<DiaryEntry>> mEntries;

    public DiaryEntryRepository(Application application) {
        DiaryEntryRoomDatabase db = DiaryEntryRoomDatabase.getDatabase(application);
        mEntryDao = db.entryDao();
        mEntries = mEntryDao.getEntries();
    }

    public LiveData<List<DiaryEntry>> getEntries() {
        return this.mEntries;
    }

    public void insert(DiaryEntry entry) {
        DiaryEntryRoomDatabase.databaseWriteExecutor.execute(() -> {
            mEntryDao.insert(entry);
        });
    }

//    public void update(String dayOfWeek, String date, Double foodOvernight, Double foodDay, Double waterOvernight, Double waterDay, Double weight, int id) {
//        DiaryEntryRoomDatabase.databaseWriteExecutor.execute(() -> {
//            mEntryDao.update(dayOfWeek, date, foodOvernight, foodDay, waterOvernight, waterDay, weight, id);
//        });
//    }

    public void deleteAll() {
        DiaryEntryRoomDatabase.databaseWriteExecutor.execute(() -> {
            mEntryDao.deleteAll();
        });
    }

    public void deleteById(int id) {
        DiaryEntryRoomDatabase.databaseWriteExecutor.execute(() -> {
            mEntryDao.deleteById(id);
        });
    }

    public LiveData<DiaryEntry> getEntryById(int id) {
        return mEntryDao.getEntryById(id);
    }

    public void update(DiaryEntry entry) {
        DiaryEntryRoomDatabase.databaseWriteExecutor.execute(() -> {
            mEntryDao.update(entry);
        });
    }
}
