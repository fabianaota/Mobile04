package com.digitalhouse.digitalhouseapp.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.digitalhouse.digitalhouseapp.model.Aluno;

import java.util.List;

@Dao
public interface AlunoDao {
    @Query("SELECT * FROM Aluno")
    LiveData<List<Aluno>> getAll();

    @Query("SELECT * FROM Aluno WHERE id IN (:userIds)")
    LiveData<List<Aluno>> loadAllByIds(int[] userIds);

    @Insert
    void insertAll(Aluno... users);

    @Delete
    void delete(Aluno user);
}