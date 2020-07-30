package com.example.mychat20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity<requestCode> extends AppCompatActivity {


    EditText txt_phNumber, txt_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_msg = (EditText) findViewById(R.id.txt_message);
        txt_phNumber = (EditText) findViewById(R.id.txt_phoneNumber);

    }

    public void Btn_send(View view) {

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            
            MyMessage ();
            
        }

        else  {

            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS, }, 0);


        }

    }

    private void MyMessage() {

        String phoneNumber = txt_phNumber.getText().toString().trim();
        String Message = txt_msg.getText().toString().trim();

        if (!txt_phNumber.getText().toString().equals("") ||  !txt_msg.getText().toString().equals("")) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, Message, null, null);

            Toast.makeText(this, "Message Sent Successfully!", Toast.LENGTH_SHORT).show();


        }

        else {
            Toast.makeText(this, "Please enter Phone NUmber or Message", Toast.LENGTH_SHORT).show();
        }

        }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 0:

                if ( grantResults.length >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    MyMessage();

                }

                 else {

                    Toast.makeText(this, "Permission Is Required", Toast.LENGTH_SHORT).show();


                }

                break;
        }

    }

}