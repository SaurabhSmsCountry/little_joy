package com.littlejoyindia.littlejoyindia.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import androidx.core.app.NotificationCompat;


public class FileUploadNotification {

    public static NotificationManager mNotificationManager;
    static NotificationCompat.Builder builder;
    static Context context;
    static int NOTIFICATION_ID = 111;
    static FileUploadNotification fileUploadNotification;

    String CHANNEL_ID = "1233454";
    String CHANNEL_NAME = "Notification";


//    public static FileUploadNotification createInsance(Context context) {
//        if(fileUploadNotification == null)
//            fileUploadNotification = new FileUploadNotification(context);
//
//        return fileUploadNotification;
//    }
    public FileUploadNotification(Context context) {
        this.context = context;
        mNotificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // I would suggest that you use IMPORTANCE_DEFAULT instead of IMPORTANCE_HIGH
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            channel.enableVibration(true);
            channel.setLightColor(Color.BLUE);
            channel.enableLights(true);
            channel.setShowBadge(true);

            mNotificationManager.createNotificationChannel(channel);
        }

        builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle("File Upload !")
                .setSmallIcon(android.R.drawable.stat_sys_upload)
                .setProgress(100, 0, false)
                .setAutoCancel(true)
                ;
    }




    public  void updateNotification(String percent, String fileName) {
        try {
//            NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
//            inboxStyle.addLine(contentText);

            builder
                    .setContentTitle(fileName)
                    //.setSmallIcon(android.R.drawable.stat_sys_download)
                    .setOngoing(true)
                    .setContentInfo(percent + "%")
                    .setProgress(100, Integer.parseInt(percent), false);

            Log.e("updated","yes");

// Don't forget to set the ChannelID!!
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                builder.setChannelId(CHANNEL_ID);
            }

            mNotificationManager.notify(CHANNEL_ID, 1, builder.build());


            if (Integer.parseInt(percent) == 100)
                deleteNotification();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e("Error...Notification.", e.getMessage() + ".....");
            e.printStackTrace();
        }
    }

    public static void failUploadNotification(/*int percentage, String fileName*/) {
        Log.e("downloadsize", "failed notification...");

        if (builder != null) {
            /* if (percentage < 100) {*/
            builder.setContentText("Uploading Failed")
                    //.setContentTitle(fileName)
                    .setSmallIcon(android.R.drawable.stat_sys_upload_done)
                    .setOngoing(false);
            mNotificationManager.notify(NOTIFICATION_ID, builder.build());
        /*} else {
            mNotificationManager.cancel(NOTIFICATION_ID);
            builder = null;
        }*/
        } else {
            mNotificationManager.cancel(NOTIFICATION_ID);
        }
    }

    public  void deleteNotification() {
        Log.e("deletenoti", "yes");

        try {
            Thread.sleep(3000);
            Log.e("deletenoti", "yesFianl");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                mNotificationManager.deleteNotificationChannel(CHANNEL_ID);
            } else {
                mNotificationManager.cancel(CHANNEL_ID, 1);
            }
          //  builder = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
