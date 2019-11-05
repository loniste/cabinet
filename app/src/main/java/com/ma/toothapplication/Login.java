package com.ma.toothapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.login);



    }
    private Toast backtoast;


    public void onBackPressed() {

            if (backtoast != null && backtoast.getView().getWindowToken() != null) {
                finish();
            } else {
                backtoast = Toast.makeText(this, "Press back to exit", Toast.LENGTH_SHORT);
                backtoast.show();
            }
    }



    public void seConnecter(View view) {
        Toast.makeText(this, "Le process de connexion doit se déclancher", Toast.LENGTH_SHORT).show();
    }
    public void accessWithoutConneion(View view) {
        Intent intent =new Intent(this, Details.class);
        startActivity(intent);
    }

    public void subscribeScreenshouldAppear(View view) {
        Toast.makeText(this, "l'écran d'inscription doit s'afficher", Toast.LENGTH_SHORT).show();

    }
}
