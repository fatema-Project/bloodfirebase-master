package com.example.myapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

public class profileact2 extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private TextView logout;
    private Button logoutsubmit;
    //notification coding//
    private TextView need;
    private TextView textView;
    public static final String CHANNEL_ID = "simplified coding";
    private static final String CHANNEL_NAME = "Simplified Coding";
    private static final String CHANNEL_DESC = "simplified coding notification";
    //notification coding//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileact2);
        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseMessaging.getInstance().subscribeToTopic("updates");

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, loginActivity.class));
        }
        //notification coding
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        textView = findViewById(R.id.textViewToken);



        FirebaseUser user = firebaseAuth.getCurrentUser();

        logout = (TextView) findViewById(R.id.logout);

        logoutsubmit = (Button) findViewById(R.id.logoutsubmit);


        logoutsubmit.setOnClickListener(this);
          need = (Button)findViewById(R.id.need);
          need = (TextView) findViewById(R.id.need);
          need.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view==logoutsubmit){
            firebaseAuth.signOut();
            finish();

            startActivity(new Intent(this,loginActivity.class));
        }
        if (view==need) {


               /* NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(this, CHANNEL_ID)
                                .setSmallIcon(R.drawable.ic_633584)
                                .setContentTitle("Hurray! it is working")
                                .setContentText("Your first notification")
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                NotificationManagerCompat mnotificationMgr = NotificationManagerCompat.from(this);
                mnotificationMgr.notify(1 ,mBuilder.build());*/

        }

    }
}
