package com.digitalhouse.digitalhouseapp.db;

import android.provider.BaseColumns;

public final class AlunoContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private AlunoContract() {}

    /* Inner class that defines the table contents */
    public static class AlunoEntry implements BaseColumns {
        public static final String TABLE_NAME = "alunos";
        public static final String COLUMN_NAME_NAME = "nome";
        public static final String COLUMN_NAME_AGE = "age";
    }
}