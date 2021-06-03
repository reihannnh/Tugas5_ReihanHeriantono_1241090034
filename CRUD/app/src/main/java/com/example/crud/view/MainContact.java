package com.example.crud.view;

import android.view.View;

import com.example.crud.entity.AppDatabase;
import com.example.crud.entity.DataPengguna;

import java.util.List;

public interface MainContact {
    interface view extends View.OnClickListener{
        void successAdd();
        void successDelete();
        void resetForm();
        void getData(List<DataPengguna> list);
        void editData(DataPengguna item);
        void deleteData(DataPengguna item);
    }

    interface presenter{
        void insertData(String nama, String lahir, String agama, String hp, String email, AppDatabase database);
        void readData(AppDatabase database);
        void editData(String nama, String lahir, String agama, String hp, String email, int id, AppDatabase database);
        void deleteData(DataPengguna dataLamaran, AppDatabase database);
    }
}

