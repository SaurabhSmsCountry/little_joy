package com.littlejoyindia.littlejoyindia.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.littlejoyindia.littlejoyindia.BuildConfig;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ui.dashboard.DashboardActivity;

import static com.littlejoyindia.littlejoyindia.LittleJoyIndiaApp.mFileUploadNotification;
import static com.littlejoyindia.littlejoyindia.LittleJoyIndiaApp.totalFileCount;
import static com.littlejoyindia.littlejoyindia.LittleJoyIndiaApp.totalFileCountUploaded;


public class FileUploadService extends JobIntentService {
    private static final String TAG = "FileUploadService";

   // FileUploadNotification mFileUploadNotification = null;
    /**
     * Unique job ID for this service.
     */
    private static final int JOB_ID = 102;
    public static void enqueueWork(Context context, Intent intent) {
        enqueueWork(context, FileUploadService.class, JOB_ID, intent);
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        /**
         * Download/Upload of file
         * The system or framework is already holding a wake lock for us at this point
         */
        // get file file here
        String picturePath = intent.getStringExtra("picturePath");
        String typeOfJob = intent.getStringExtra("typeOfJob");
        String opId = intent.getStringExtra("opId");
        String jobAssignId = intent.getStringExtra("jobAssignId");
        String time = intent.getStringExtra("time");

        String key = intent.getStringExtra("key");
        int partNumber = Integer.parseInt(intent.getStringExtra("partNumber"));
        String uploadId = intent.getStringExtra("uploadId");



        if (picturePath == null) {
            Log.e(TAG, "onHandleWork: Invalid file URI");
            return;
        }


        // get upload URL here

        final int cSize = 5242880 ; //1024 * 1024;  // size of chunk
        File file = new File(picturePath);
        final long pieces = file.length();  //cSize // used to return file length.

        String nameOfFile = file.getName();
//        mFileUploadNotification = new FileUploadNotification(getApplicationContext(), nameOfFile);
        onProgressShown(0, nameOfFile);

        int CHUNK_SIZE = 5242880;  //1024 * 1024;  //5242880; //5MB
        long fileSize = file.length();
        int CHUNKS_COUNT = (int) (fileSize / CHUNK_SIZE) + 1;

        Log.e("CHUNKS_COUNT", ""+CHUNKS_COUNT+"size\n"+fileSize);
        BufferedInputStream stream = null;
        try {

            stream = new BufferedInputStream(new FileInputStream(file));
            int totalRequests ;
            for (int i =  partNumber ; i < CHUNKS_COUNT + 1; i++) {
                Log.e("partNumberI", ""+i);
                totalRequests = i;
                byte[] buffer = new byte[cSize];

                int start = (i - 1) * CHUNK_SIZE;
                int end ;
                if(i == CHUNKS_COUNT){
                    end = (int) fileSize -  i * CHUNK_SIZE;
                } else {
                    end = i * CHUNK_SIZE;
                }


                if (stream.read(buffer) == -1)
                    break;

                // Send image data to server
                ByteArrayOutputStream byteStream = null;
                byteStream = new ByteArrayOutputStream();
                //byteStream.write(buffer, start, end );
                byteStream.write(buffer );
                byte[] bytes = byteStream.toByteArray();

                // First get upload URL
                int finalTotalRequests = totalRequests;
                AndroidNetworking.post(BuildConfig.BASE_URL + "upload/get-upload-url")
                        .addBodyParameter("key", key)
                        .addBodyParameter("uploadId", uploadId)
                        .addBodyParameter("partNumber", ""+i)
                        .setTag("test")
                        .setPriority(Priority.HIGH)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // do anything with response
                                JSONObject user = null;

                                try {
                                    user = response.getJSONObject("data");
                                    String presignedUrl = user.getString("presignedUrl");
                                    Log.e("presignedUrl",presignedUrl);

                                    AndroidNetworking.put(presignedUrl)
                                            .addByteBody(bytes)
                                            .setTag("test")
                                            .setPriority(Priority.HIGH)
                                            .build()
                                            .getAsString(new StringRequestListener() {
                                                @Override
                                                public void onResponse(String response) {
                                                    // do anything with response
                                                    Log.e("response",""+response);
                                                    Log.e("finalTotalRequests",""+finalTotalRequests);
                                                    Log.e("CHUNKS_COUNT",""+CHUNKS_COUNT);

                                                    int progressUpload =  finalTotalRequests * 100 / CHUNKS_COUNT ;





                                                    if(finalTotalRequests == CHUNKS_COUNT){
                                                        AndroidNetworking.post(BuildConfig.BASE_URL + "upload/complete-data-upload")
                                                                .addBodyParameter("key", key)
                                                                .addBodyParameter("uploadId", uploadId)
                                                                .setTag("test")
                                                                .setPriority(Priority.HIGH)
                                                                .build()
                                                                .getAsJSONObject(new JSONObjectRequestListener() {
                                                                    @Override
                                                                    public void onResponse(JSONObject response) {

                                                                        onSuccess(nameOfFile);

                                                                        // do anything with response
                                                                        JSONObject user = null;
                                                                        try {
                                                                            user = response.getJSONObject("data");
                                                                            Log.e("user",user.toString());
                                                                            Log.e("OnComplete", "File Uploaded successfully");
                                                                        } catch (JSONException e) {
                                                                            e.printStackTrace();
                                                                        }
                                                                    }
                                                                    @Override
                                                                    public void onError(ANError error) {
                                                                        // handle error
                                                                    }
                                                                });
                                                    }
                                                }
                                                @Override
                                                public void onError(ANError error) {
                                                    // handle error

                                                }
                                            });


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            @Override
                            public void onError(ANError error) {
                                // handle error
                            }
                        });





            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void onErrors(Throwable throwable) {
        sendBroadcastMeaasge("Error in file upload " + throwable.getMessage());
        Log.e(TAG, "onErrors: ", throwable);
    }
    private void onProgressShown(int progress, String nameOfFile) {
        sendBroadcastMeaasge(nameOfFile + " uploading in progress... " + progress);
        Log.e(TAG, "onProgress: " + progress);
//        if(mFileUploadNotification != null){
//            mFileUploadNotification.updateNotification(""+progress,  nameOfFile, "Uploading in progress...");
//        } else {
//            mFileUploadNotification = new FileUploadNotification(getApplicationContext(), nameOfFile);
//            mFileUploadNotification.updateNotification(""+progress,  nameOfFile, "Uploading in progress...");
//
//        }

    }
    private void onSuccess(String nameOfFile) {
        sendBroadcastMeaasge(nameOfFile+" uploading successful ");
        Log.e(TAG, "onSuccess: File Uploaded");
        //showNotification();


        totalFileCountUploaded = totalFileCountUploaded + 1;

        int progress =  totalFileCountUploaded / totalFileCount ;

        if(mFileUploadNotification != null){
            mFileUploadNotification.updateNotification(String.valueOf(progress* 100),  ""+totalFileCountUploaded
                    + "/"+  totalFileCount + " file uploading...");
        } else {
            mFileUploadNotification = new FileUploadNotification(this);
            mFileUploadNotification.updateNotification(String.valueOf(progress* 100),  ""+totalFileCountUploaded
                    + "/"+  totalFileCount + " file uploading...");
        }


//        if(mFileUploadNotification != null){
//            mFileUploadNotification.updateNotification("100",  nameOfFile, "Upload Done");
//        } else {
//            mFileUploadNotification = new FileUploadNotification(getApplicationContext(), nameOfFile);
//            mFileUploadNotification.updateNotification("100",  nameOfFile, "Upload Done");
//
//        }

    }


    private void showNotification() {

        Intent intent = new Intent(this, DashboardActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // Build notification
        // Actions are just fake
        Notification noti = new Notification.Builder(this)
                .setContentTitle("Data Upload")
                .setContentText("File uploaded successfully !").setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pIntent)
                .build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, noti);

    }

    public void sendBroadcastMeaasge(String message) {
        Intent localIntent = new Intent("my.own.broadcast");
        localIntent.putExtra("result", message);
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
    }

}
