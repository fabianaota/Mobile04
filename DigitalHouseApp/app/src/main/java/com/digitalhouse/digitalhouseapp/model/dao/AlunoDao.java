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
    @Query("SELECT * FROM Aluno ORDER BY idade DESC")
    LiveData<List<Aluno>> getAll();

    @Query("SELECT * FROM Aluno WHERE nome LIKE (:nomeAluno)")
    LiveData<List<Aluno>> loadByName(String nomeAluno);

    @Insert
    void insertAll(Aluno... alunos);

    @Delete
    void delete(Aluno aluno);
}