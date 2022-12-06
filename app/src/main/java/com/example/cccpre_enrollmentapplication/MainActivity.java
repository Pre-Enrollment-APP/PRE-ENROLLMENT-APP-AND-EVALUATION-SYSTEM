package com.example.cccpre_enrollmentapplication;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;


public class MainActivity extends AppCompatActivity {
    private static final String TAG="PushNotification";
    private static final String CHANNEL_ID = "101";

    Handler handler;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        if (user != null && user.isEmailVerified()) {
                Intent intent = new Intent(MainActivity.this, Home_Page.class);
                 intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                 intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                 intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);


        } else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {


                    Intent intent = new Intent(MainActivity.this, login.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);


                }
            }, 2400);


        }
        createNotificationChannel();
        getToken();
    }
    private void getToken()
    {
    FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
    //if task is failed then
    if (!task.isSuccessful())
    {
    Log.d(TAG, "onComplete: Failed to get the Token");
    }
        String token=task.getResult();
        Log.d(TAG, "onComplete" + token);
    });
    }
    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "firebaseNotificationChannel";
            String description = "Receive Firebase notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}