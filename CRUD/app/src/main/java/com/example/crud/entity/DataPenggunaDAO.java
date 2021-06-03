package com.example.crud.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataPenggunaDAO {
    @Insert
    Long insertData(DataPengguna dataPengguna);

    @Query("Select * from pengguna_db")
    List<DataPengguna> getData();

    @Update
    int updateData(DataPengguna item);

    @Delete
    void deleteData(DataPengguna item);
}
