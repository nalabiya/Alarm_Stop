package com.example.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;

interface RingtoneHelper {
    void stopRingtone();
}

class Utility {
    public static RingtoneHelper ringtoneHelper;
}

public class Alarm extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "출근하세요 ~~~~~~!!", Toast.LENGTH_LONG).show();    // AVD 확인용

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        final Ringtone ringtone = RingtoneManager.getRingtone(context.getApplicationContext(), uri);
        AudioAttributes audioAttributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_ALARM).build();

        ringtone.setAudioAttributes(audioAttributes);
        ringtone.play();

        Utility.ringtoneHelper = new RingtoneHelper() {
            @Override
            public void stopRingtone() {
                if(ringtone.isPlaying()) {
                    ringtone.stop();
                }
            }
        };
    }
}

