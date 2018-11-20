package com.digitalhouse.firebaseapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.digitalhouse.firebaseapp.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
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

    FirebaseStorage storage = FirebaseStorage.getInstance();
    private ImageView imageUser;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        myRef = database.getReference("users/" + mAuth.getCurrentUser().getUid());

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
                GenericTypeIndicator<Map<String, User>> genericTypeIndicator = new GenericTypeIndicator<Map<String, User>>() {
                };
                if (dataSnapshot.getValue(genericTypeIndicator) != null) {
                    Collection<User> userList = dataSnapshot.getValue(genericTypeIndicator).values();
                    for (User user : userList) {
                        text.setText(text.getText() + "\nPeso: " + user.getPeso() + "\nAltura: " + user.getAltura() + "\nData: " + user.getDate() + "\n");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        imageUser = findViewById(R.id.image_user_id);

        Button changeButton = findViewById(R.id.button_change_image_id);
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserImage();
            }
        });

        // Create a storage reference from our app
        StorageReference storageRef = storage.getReference();

        // Create a child reference
        StorageReference imagesRef = storageRef.child(mAuth.getUid());

        imagesRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(imageUser);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }


    private void getUserImage() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            final Bitmap imageBitmap = (Bitmap) extras.get("data");


            // Get the data from an ImageView as bytes
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] dataBytes = baos.toByteArray();

            // Create a storage reference from our app
            StorageReference storageRef = storage.getReference();

            // Create a child reference
            StorageReference imagesRef = storageRef.child(mAuth.getUid());

            UploadTask uploadTask = imagesRef.putBytes(dataBytes);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    imageUser.setImageBitmap(imageBitmap);
                }
            });
        }
    }
}
