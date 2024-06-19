package com.example.firestoreapplication;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirestoreSingleton {
    private static FirestoreSingleton instance;
    private FirebaseFirestore db;
    private DocumentReference friendRef;
    private CollectionReference collectionReference;

    private FirestoreSingleton() {
        db = FirebaseFirestore.getInstance();
        friendRef = db.collection("Users").document("Friends");
        collectionReference = db.collection("Users");
    }

    public static synchronized FirestoreSingleton getInstance() {
        if (instance == null) {
            instance = new FirestoreSingleton();
        }
        return instance;
    }

    public FirebaseFirestore getDb() {
        return db;
    }

    public DocumentReference getFriendRef() {
        return friendRef;
    }

    public CollectionReference getCollectionReference() {
        return collectionReference;
    }
}
