package com.example.firestoreapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firestoreapplication.models.Friends;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ListUserActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Friends> friendsList = new ArrayList<>();
    FirestoreSingleton firestoreSingleton = FirestoreSingleton.getInstance();
    CollectionReference friendsCollection = firestoreSingleton.getCollectionReference();
    DocumentReference friendsDocRef = firestoreSingleton.getFriendRef();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_user_acrivity);
        listAllFriends();
        System.out.println("friendsList.size() = " + friendsList.size());

    }
    private void listAllFriends(){
        friendsCollection.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                StringBuilder data = new StringBuilder();
                for (QueryDocumentSnapshot sn: queryDocumentSnapshots) {
                    Friends friends = sn.toObject(Friends.class);
                    friends.setId(sn.getId());
                    friendsList.add(friends);
                    data.append("Name: ").append(friends.getName()).append(" Email: ").append(friends.getEmail()).append("\n");
                }
                recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                AdapterFriends adapter = new AdapterFriends(friendsList);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}