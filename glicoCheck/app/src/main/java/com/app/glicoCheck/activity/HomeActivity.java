package com.app.glicoCheck.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.glicoCheck.R;
import com.app.glicoCheck.Util.ConfiguracaoBd;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String userId;
    TextView username;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        auth = ConfiguracaoBd.Firebaseautenticacao();
        homeList();


    }

    private void homeList(){
        ArrayList<Object> mHomeList = new ArrayList<>();
        mHomeList.add(R.id.calcularBfCard);
        mHomeList.add(R.id.perfilCard);
        mHomeList.add(R.id.calcularImcCard);
        mHomeList.add(R.id.lembreteInsulinaCard);
        mHomeList.add(R.id.logoutCard);
        mHomeList.add(R.id.calcularBfCard);

    }


    protected void onStart(){
        super.onStart();
        username = findViewById(R.id.txtUsername);
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentReference = db.collection("Usuarios").document(userId);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if(documentSnapshot != null){
                    username.setText("Olá, "+documentSnapshot.getString("nome"));
                }
            }
        });
    }

    public void goPerfil(View v){
        Intent i = new Intent(this, PerfilActivity.class);
        startActivity(i);
    }
    public void goImc(View v){
        Intent i = new Intent(this, ImcActivity.class);
        startActivity(i);
    }
    public void goBf(View v){
        Intent i = new Intent(this, BfActivity.class);
        startActivity(i);
    }
    public void goRegGlicose(View v){
        Intent i = new Intent(this, regGlicoseActivity.class);
        startActivity(i);
    }
    public void goLembreteInsulina(View v){
        Intent i = new Intent(this, LembreteInsulinaActivity.class);
        startActivity(i);
    }
    public void goTMB(View v){
        Intent i = new Intent(this, TMBActivity.class);
        startActivity(i);
    }




    public void deslogar(View view){
        try{
            auth.signOut();
            finish();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}