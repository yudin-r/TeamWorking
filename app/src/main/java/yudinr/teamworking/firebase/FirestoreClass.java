package yudinr.teamworking.firebase;


import com.google.firebase.firestore.FirebaseFirestore;

import yudinr.teamworking.activities.SignUpActivity;
import yudinr.teamworking.models.User;

public class FirestoreClass {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    void registerUser(SignUpActivity signUpActivity, User user) {}
}
