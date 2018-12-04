package com.digitalhouse.digitalhouseapp.database;

import android.provider.BaseColumns;

public final class AlunosContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private AlunosContract() {}

    /* Inner class that defines the table contents */
    public static class AlunoEntry implements BaseColumns {
        public static final String TABLE_NAME = "alunos";
        public static final String COLUMN_NAME_NOME = "nome";
        public static final String COLUMN_NAME_IDADE = "idade";
    }
}