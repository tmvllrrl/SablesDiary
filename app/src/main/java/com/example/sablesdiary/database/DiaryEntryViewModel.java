package com.example.sablesdiary.database;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class DiaryEntryViewModel extends AndroidViewModel {

    private DiaryEntryRepository mRepository;
    private LiveData<List<DiaryEntry>> mEntries;

    public DiaryEntryViewModel(Application application) {
        super(application);
        mRepository = new DiaryEntryRepository(application);
        mEntries = mRepository.getEntries();
    }

    public LiveData<List<DiaryEntry>> getEntries() {
        return mEntries;
    }

    public LiveData<DiaryEntry> getEntryById(int id) {
        return mRepository.getEntryById(id);
    }

    public void insertEntry(DiaryEntry entry) {
        mRepository.insert(entry);
    }

//    public void update(String dayOfWeek, String date, Double foodOvernight, Double foodDay, Double waterOvernight, Double waterDay, Double weight, int id) {
//        mRepository.update(dayOfWeek, date, foodOvernight, foodDay, waterOvernight, waterDay, weight, id);
//    }

    public void deleteAll() {
        mRepository.deleteAll();
    }

    public void deleteById(int id) {
        mRepository.deleteById(id);
    }

    public void update(DiaryEntry entry) {
        mRepository.update(entry);
    }

}
