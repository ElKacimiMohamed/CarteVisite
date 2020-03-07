package com.example.mycartevisite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView nom ;
    Button telephone , maill ;
    Intent intent ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         nom = findViewById(R.id.nom);
         telephone = findViewById(R.id.tele);
         maill = findViewById(R.id.mail);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),"fonts/Pacifico-Regular.ttf");
        nom.setTypeface(custom_font);
        intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+"+216 53337507"));

      telephone.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED) {
                  startActivity(intent);
                  return;

              }else{
                  ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
              }

          }
      });

      maill.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {


              Intent email = new Intent(intent.ACTION_SEND);
              email.putExtra(Intent.EXTRA_EMAIL, new String[]{"medelkacimi93@gmail.com"});
              email.putExtra(Intent.EXTRA_SUBJECT,"Hello Mohamed-Salih");
              email.putExtra(Intent.EXTRA_TEXT,"Cordialement");
              email.setType("message/rfc822");

              startActivity(Intent.createChooser(email,"choose an Email Client: "));
          }
      });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1){
            if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                startActivity(intent);
            }else {
                Toast.makeText(MainActivity.this, "Permission denied to call phone", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
