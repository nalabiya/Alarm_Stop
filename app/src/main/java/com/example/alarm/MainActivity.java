package com.example.alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.app.AlarmManager.RTC_WAKEUP;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textview = (TextView)findViewById(R.id.textview);

        final Intent intent = new Intent(this, Alarm.class);
        final AlarmManager alarmManager = (AlarmManager)this.getSystemService(ALARM_SERVICE);
        final PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        int hour, minute;
        hour = 10;
        minute = 36;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        // 지정한 시간에 매일 알림
        try {
            //alarmManager.setRepeating(RTC_WAKEUP, calendar.getTimeInMillis(), 60 * 1000, pendingIntent);
            alarmManager.set(RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

            textview.setText("출근하세요 ~~~~~~!!");

        } catch (Exception e){
            textview.setText(e.toString());
        }

        // 알람 정지 버튼
        Button alarm_off = findViewById(R.id.stopBtn);
        alarm_off.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // todo add click action for your button
                if(Utility.ringtoneHelper != null) {
                    Utility.ringtoneHelper.stopRingtone();
                }
            }
                /*
            }
                Context context = MainActivity.this;
                TextView textview = (TextView)findViewById(R.id.textview);

                Intent intent = new Intent(MainActivity.this, Alarm.class);
                PendingIntent pIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
                pendingIntent.cancel(); //<- added based on SO post, doesn't help
                alarmManager.cancel(pIntent);

                Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                try {
                    Toast.makeText(MainActivity.this, "알람 종료", Toast.LENGTH_LONG).show();
                    textview.setText("알람 종료 ~~~~~~!!");

                    Ringtone ringtone = RingtoneManager.getRingtone(context.getApplicationContext(), uri);
                    AudioAttributes audioAttributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_ALARM).build();

                    ringtone.setAudioAttributes(audioAttributes);
                    ringtone.stop();

                } catch (Exception e){
                    textview.setText(e.toString());
                }
                //////////////////////////////////////////////////////////////
                private interface RingtoneHelper {
                    void stopRingtone();
                }

                class Utility {

                    public static RingtoneHelper ringtoneHelper;


                }*/

        });

    }
}