package com.example.samsung.gp.helpers;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.samsung.gp.R;
import com.example.samsung.gp.app.AppConfig;
import com.example.samsung.gp.app.AppController;
import com.example.samsung.gp.helper.SQLiteHandler;
import com.example.samsung.gp.imgurmodel.ImageResponse;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by AKiniyalocts on 1/15/15.
 * <p/>
 * This class is just created to help with notifications, definitely not necessary.
 */
public class NotificationHelper extends Activity{
    public final static String TAG = NotificationHelper.class.getSimpleName();
    static SQLiteHandler db;
    static HashMap<String, String> user;

    private WeakReference<Context> mContext;


    public NotificationHelper(Context context) {
        this.mContext = new WeakReference<>(context);
    }

    public void createUploadingNotification() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext.get());
        mBuilder.setSmallIcon(android.R.drawable.ic_menu_upload);
        mBuilder.setContentTitle(mContext.get().getString(R.string.notification_progress));


        mBuilder.setColor(mContext.get().getResources().getColor(R.color.colorPrimary));

        mBuilder.setAutoCancel(true);

        NotificationManager mNotificationManager =
                (NotificationManager) mContext.get().getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(mContext.get().getString(R.string.app_name).hashCode(), mBuilder.build());

    }

    public void createUploadedNotification(ImageResponse response) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext.get());
        mBuilder.setSmallIcon(android.R.drawable.ic_menu_gallery);
        mBuilder.setContentTitle(mContext.get().getString(R.string.notifaction_success));

        //mBuilder.setContentText(response.data.link);

        mBuilder.setColor(mContext.get().getResources().getColor(R.color.colorPrimary));



        //uploadImage(getuid(mContext.get()), response.data.link);
        /*Intent resultIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(response.data.link));
        PendingIntent intent = PendingIntent.getActivity(mContext.get(), 0, resultIntent, 0);
        mBuilder.setContentIntent(intent);
        mBuilder.setAutoCancel(true);

        Intent shareIntent = new Intent(Intent.ACTION_SEND, Uri.parse(response.data.link));
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, response.data.link);
        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent pIntent = PendingIntent.getActivity(mContext.get(), 0, shareIntent, 0);
        mBuilder.addAction(new NotificationCompat.Action(R.drawable.abc_ic_menu_share_mtrl_alpha,
                mContext.get().getString(R.string.notification_share_link), pIntent));*/

        NotificationManager mNotificationManager =
                (NotificationManager) mContext.get().getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(mContext.get().getString(R.string.app_name).hashCode(), mBuilder.build());
        if(!response.data.link.isEmpty()){
        uploadImage(getuid(mContext.get()), response.data.link);
            db.UpdatePhoto(getemail(mContext.get()), response.data.link);


        }

    }

    public void createFailedUploadNotification() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext.get());
        mBuilder.setSmallIcon(android.R.drawable.ic_dialog_alert);
        mBuilder.setContentTitle(mContext.get().getString(R.string.notification_fail));
        mBuilder.setContentText(mContext.get().getString(R.string.failed_to_upload));

        mBuilder.setColor(mContext.get().getResources().getColor(R.color.colorPrimary));

        mBuilder.setAutoCancel(true);

        NotificationManager mNotificationManager =
                (NotificationManager) mContext.get().getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(mContext.get().getString(R.string.app_name).hashCode(), mBuilder.build());
    }

    private void uploadImage(final String uid , final String Photo_URL){
        //Showing the progress dialog
       // final ProgressDialog loading = ProgressDialog.show(mContext.get(),"Uploading...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.UPLOAD_PHOTO_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                      //  loading.dismiss();
                        //Showing toast message of the response
                        //Toast.makeText(mContext.get(), s, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                      //  loading.dismiss();

                        //Showing toast
                        Toast.makeText(mContext.get(), volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                //String image = getStringImage(bitmap);

                //Getting Image Name
                ;

                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters
                //params.put(KEY_IMAGE, image);
                params.put("uid", uid);
                params.put("Photo_URL", Photo_URL);


                //returning parameters
                return params;
            }
        };
/*
        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(mContext.get());

        //Adding request to the queue
        requestQueue.add(stringRequest);*/

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest, TAG);

    }

    public String getuid(Context context) {
        db = new SQLiteHandler(context);
        user = db.getUserDetails();
        String uid = user.get("uid");
        return uid;
    }

    public String getemail(Context context) {
        db = new SQLiteHandler(context);
        user = db.getUserDetails();
        String email = user.get("email");
        return email;
    }
}
