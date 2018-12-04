package com.digitalhouse.digitalhouseapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.digitalhouse.digitalhouseapp.model.Aluno;
import com.digitalhouse.digitalhouseapp.model.dao.AlunoDao;

@Database(entities = {Aluno.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AlunoDao alunoDao();
}