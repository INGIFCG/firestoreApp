package com.example.firestoreapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firestoreapplication.models.Friends;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button save,delete,read,update;
    private EditText name,email;
    private TextView listUsers ;
    FirestoreSingleton firestoreSingleton = FirestoreSingleton.getInstance();
    CollectionReference friendsCollection = firestoreSingleton.getCollectionReference();
    DocumentReference friendsDocRef = firestoreSingleton.getFriendRef();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initializeComponent();
    }

    private void initializeComponent() {
        save = findViewById(R.id.button_save);
        delete = findViewById(R.id.button_delete);
        read = findViewById(R.id.button_read);
        update = findViewById(R.id.button_update);
        name = findViewById(R.id.imput_name);
        email = findViewById(R.id.imput_email);
        listUsers = findViewById(R.id.listUsers);
        save.setOnClickListener(this);
        delete.setOnClickListener(this);
        read.setOnClickListener(this);
        update.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.button_save){
            savePersonInDB();
        }else if(v.getId()== R.id.button_read){
            Intent intent = new Intent(this, ListUserActivity.class);
            startActivity(intent);
        }else if(v.getId()== R.id.button_delete){
            showMessaje("ingreso en delete");
            deleteFriend();
        }else if(v.getId()== R.id.button_update){
        }
    }
    private void savePersonInDB() {
        String nameInsert = name.getText().toString();
        String emailInsert = email.getText().toString();
        Friends friends = new Friends(nameInsert,emailInsert);
        friendsCollection.add(friends).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                showMessaje("se ha insertado correctamente el cliente: "+documentReference.getId());
            }
        });
    }
    private void deleteFriend(){
        //esta seccion borrara un objeto especifico especificado en el DocumentReference
        friendsDocRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                showMessaje("se ha insertado correctamente el cliente: ");

            }
        });
    }
    private void updateFrind(){
        // para actualizar un objeto especifico se debe colocar la llave del objeto consultado en la base de datos ejemplo
        //DocumentReference friendRef = db.collection("Users").document("AQUI VA LA ID DEL OBJETO");
        //collectionReference = db.collection("Friends");
    }
    private void showMessaje(String messaje){
        Toast.makeText(this, messaje, Toast.LENGTH_SHORT).show();
    }
}