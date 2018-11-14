package com.digitalhouse.firebaseapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.digitalhouse.firebaseapp.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";
    private FirebaseAuth mAuth;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        myRef = database.getReference("users/"+mAuth.getCurrentUser().getUid());

        TextView userText = findViewById(R.id.text_user_id);
        userText.setText(mAuth.getCurrentUser().getDisplayName());

        Button logout = findViewById(R.id.button_logout_id);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finish();
            }
        });

        Button button = findViewById(R.id.button_salvar_id);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarDados();
            }
        });
        text = findViewById(R.id.text_firebase_data_id);

        Query query = myRef.child("historico").orderByChild("date");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                text.setText("");
                GenericTypeIndicator<Map<String, User>> genericTypeIndicator = new GenericTypeIndicator<Map<String, User>>() {};
                Collection<User> userList = dataSnapshot.getValue(genericTypeIndicator).values();
                for (User user : userList) {
                    text.setText(text.getText()+"\nPeso: "+user.getPeso()+"\nAltura: "+user.getAltura()+"\nData: "+user.getDate()+"\n");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void salvarDados() {

        final User user = new User();
        EditText pesoEdit = findViewById(R.id.editext_peso_id);
        user.setPeso(Double.parseDouble(pesoEdit.getText().toString()));

        EditText alturaEdit = findViewById(R.id.editext_altura_id);
        user.setAltura(Double.parseDouble(alturaEdit.getText().toString()));

        user.setDate(new Date());

        myRef.child("historico").push().setValue(user);

    }
}
