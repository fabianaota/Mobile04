package com.digitalhouse.digitalhouseapp.fragments;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.digitalhouse.digitalhouseapp.R;
import com.digitalhouse.digitalhouseapp.db.AlunoContract;
import com.digitalhouse.digitalhouseapp.db.AlunoDbHelper;
import com.digitalhouse.digitalhouseapp.model.Aluno;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PeopleFragment extends Fragment {


    private AlunoDbHelper dbHelper;
    private TextInputEditText editTextName;
    private TextInputEditText editTextAge;
    private TextView textAlunos;

    public PeopleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_people, container, false);

        dbHelper = new AlunoDbHelper(getContext());

        Button cadastrar = view.findViewById(R.id.button_cadastrar_id);
        editTextName = view.findViewById(R.id.textinput_name);
        editTextAge = view.findViewById(R.id.textinput_age);
        textAlunos = view.findViewById(R.id.text_alunos_list_id);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarAluno();
            }
        });

        atualizarDados();

        return view;
    }

    private void cadastrarAluno() {
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String name = editTextName.getEditableText().toString();
        int age = Integer.parseInt(editTextAge.getEditableText().toString());

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(AlunoContract.AlunoEntry.COLUMN_NAME_NAME, name);
        values.put(AlunoContract.AlunoEntry.COLUMN_NAME_AGE, age);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(AlunoContract.AlunoEntry.TABLE_NAME, null, values);

        atualizarDados();
    }

    private void atualizarDados() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                AlunoContract.AlunoEntry.COLUMN_NAME_NAME,
                AlunoContract.AlunoEntry.COLUMN_NAME_AGE
        };

        // Filter results WHERE "title" = 'My Title'
//        String selection = AlunoContract.AlunoEntry.COLUMN_NAME_TITLE + " = ?";
//        String[] selectionArgs = {"My Title"};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                AlunoContract.AlunoEntry.COLUMN_NAME_NAME + " DESC";

        Cursor cursor = db.query(
                AlunoContract.AlunoEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List<Aluno> alunoList = new ArrayList();
        while(cursor.moveToNext()) {
            Aluno aluno = new Aluno();

            String name = cursor.getString(
                    cursor.getColumnIndexOrThrow(AlunoContract.AlunoEntry.COLUMN_NAME_NAME));

            int age = cursor.getInt(
                    cursor.getColumnIndexOrThrow(AlunoContract.AlunoEntry.COLUMN_NAME_AGE));

            aluno.setName(name);
            aluno.setAge(age);

            alunoList.add(aluno);
        }
        cursor.close();

        textAlunos.setText("");

        for (Aluno aluno : alunoList) {
            textAlunos.setText(textAlunos.getText() + "Nome: "+aluno.getName()+" - Idade: "+aluno.getAge()+"\n");
        }
    }


}
