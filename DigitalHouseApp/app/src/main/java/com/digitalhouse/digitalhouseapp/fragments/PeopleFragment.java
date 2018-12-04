package com.digitalhouse.digitalhouseapp.fragments;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.digitalhouse.digitalhouseapp.R;
import com.digitalhouse.digitalhouseapp.adapter.RecyclerViewAlunoAdapter;
import com.digitalhouse.digitalhouseapp.database.AlunosContract;
import com.digitalhouse.digitalhouseapp.database.AlunosDbHelper;
import com.digitalhouse.digitalhouseapp.model.Aluno;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PeopleFragment extends Fragment {

    private EditText editNome;
    private EditText editIdade;
    private Button button;
    private RecyclerView recyclerViewAlunos;
    private AlunosDbHelper mDbHelper;
    private RecyclerViewAlunoAdapter alunoAdapter;

    public PeopleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_people, container, false);

        button = view.findViewById(R.id.button_cadastrar_id);

        editNome = view.findViewById(R.id.edittext_nome_id);
        editIdade = view.findViewById(R.id.edittext_idade_id);

        recyclerViewAlunos = view.findViewById(R.id.recyclerview_alunos_id);

        alunoAdapter = new RecyclerViewAlunoAdapter();
        recyclerViewAlunos.setAdapter(alunoAdapter);
        recyclerViewAlunos.setLayoutManager(new LinearLayoutManager(getContext()));

        mDbHelper = new AlunosDbHelper(getContext());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarAluno();
            }
        });

        exibirAlunos();

        return view;
    }

    public void cadastrarAluno() {

        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        String nome = editNome.getEditableText().toString();
        int idade = Integer.parseInt(editIdade.getEditableText().toString());

        values.put(AlunosContract.AlunoEntry.COLUMN_NAME_NOME, nome);
        values.put(AlunosContract.AlunoEntry.COLUMN_NAME_IDADE, idade);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(AlunosContract.AlunoEntry.TABLE_NAME, null, values);

        exibirAlunos();
    }

    public void exibirAlunos() {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                AlunosContract.AlunoEntry.COLUMN_NAME_NOME,
                AlunosContract.AlunoEntry.COLUMN_NAME_IDADE
        };

// Filter results WHERE "title" = 'My Title'
//        String selection = AlunosContract.AlunoEntry.COLUMN_NAME_TITLE + " = ?";
//        String[] selectionArgs = { "My Title" };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                AlunosContract.AlunoEntry.COLUMN_NAME_NOME + " ASC";

        Cursor cursor = db.query(
                AlunosContract.AlunoEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List<Aluno> alunosList = new ArrayList<>();
        while (cursor.moveToNext()) {

            int id = cursor.getInt(
                    cursor.getColumnIndexOrThrow(AlunosContract.AlunoEntry._ID));

            String nome = cursor.getString(
                    cursor.getColumnIndexOrThrow(AlunosContract.AlunoEntry.COLUMN_NAME_NOME));

            int idade = cursor.getInt(
                    cursor.getColumnIndexOrThrow(AlunosContract.AlunoEntry.COLUMN_NAME_IDADE));

            Aluno aluno = new Aluno();

            aluno.setId(id);
            aluno.setNome(nome);
            aluno.setIdade(idade);

            alunosList.add(aluno);
        }
        cursor.close();

        alunoAdapter.setListaAlunos(alunosList);

    }



}
