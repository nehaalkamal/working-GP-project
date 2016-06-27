package com.example.samsung.gp;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.samsung.gp.app.AppConfig;
import com.example.samsung.gp.app.AppController;
import com.example.samsung.gp.helper.SQLiteHandler;
import com.example.samsung.gp.helpers.DocumentHelper;
import com.example.samsung.gp.helpers.IntentHelper;
import com.example.samsung.gp.imgurmodel.ImageResponse;
import com.example.samsung.gp.imgurmodel.Upload;
import com.example.samsung.gp.services.UploadService;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;


public class user_profile extends AppCompatActivity {
    ImageView imageView1;
    private Context mContext;
    private Resources mResources;
    private RelativeLayout mRelativeLayout;
    private Button mBTNCircular;
    private ImageView mImageView;
    private Bitmap bitmap;

    private int PICK_IMAGE_REQUEST = 1;

    private String UPLOAD_URL ="http://simplifiedcoding.16mb.com/VolleyUpload/upload.php";

    private String KEY_IMAGE = "image";
    private Dialog list_dialog;
    private Button Update;
    static SQLiteHandler db;
    static HashMap<String, String> user;
    NestedScrollView nestedScrollView;
    private FloatingActionButton EditButton;
    private ImageView imageView;
    private EditText Email;
    private EditText Password;
    private EditText Location;
    private EditText Birthdate;
    private EditText Gender;
    private EditText Name;
    private CardView CardViewName;
    private static final String TAG = LoginActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private EditText Biography;
        //imgur
    private Upload upload; // Upload object containging image and meta data
    private File chosenFile; //chosen file from intent
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(getname(getApplicationContext()));
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        ImageView header = (ImageView) findViewById(R.id.header);

        EditButton =(FloatingActionButton)findViewById(R.id.edit_floatingbutton);

         nestedScrollView = (NestedScrollView) findViewById(R.id.scrollableview);
        Password=(EditText) findViewById(R.id.desc_password);
        Email=(EditText) findViewById(R.id.desc_email);
        Update=(Button) findViewById(R.id.updatebutton);
        Location=(EditText) findViewById(R.id.desc_location);
        Birthdate=(EditText) findViewById(R.id.desc_birthdate);
        Gender=(EditText) findViewById(R.id.desc_gender);
        Biography=(EditText) findViewById(R.id.desc_biography);
        Name=(EditText) findViewById(R.id.desc_name);
        CardViewName=(CardView)findViewById(R.id.card_viewname);
       CardViewName.setVisibility(View.GONE);
        Update.setVisibility(View.GONE);
        Email.setText(getemail(getApplicationContext()));
        Password.setText(getpassword(getApplicationContext()));
        Location.setText(getlocation(getApplicationContext()));
        Birthdate.setText(getbirthdate(getApplicationContext()));
        Gender.setText(getgender(getApplicationContext()));
        Biography.setText(getbiography(getApplicationContext()));

    /*    RecyclerAdapter adapter=new RecyclerAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));*/




        mContext = getApplicationContext();

        // Get the Resources
        mResources = getResources();

        // Get the widgets reference from XML layout
        mRelativeLayout = (RelativeLayout) findViewById(R.id.rl);
        mImageView = (ImageView) findViewById(R.id.iv);

        // Get the bitmap from drawable resources
       // final Bitmap srcBitmap = BitmapFactory.decodeResource(mResources,Integer.parseInt(getuserimage(getApplicationContext())));
        //final Bitmap srcBitmap =(Bitmap)
               // Picasso.with(getApplicationContext()).load(getuserimage(getApplicationContext())).placeholder(R.drawable.no_image).into(((ImageView) findViewById(R.id.iv)));

        // Display the bitmap in ImageView
        //mImageView.setImageBitmap(srcBitmap);
       Picasso.with(getApplicationContext()).load(getuserimage(getApplicationContext())).placeholder(R.drawable.no_image).into(((ImageView) findViewById(R.id.iv)));

                // Initialize a new Paint instance
                Paint paint = new Paint();

                // Get source bitmap width and height
               // int srcBitmapWidth = srcBitmap.getWidth();
               // int srcBitmapHeight = srcBitmap.getHeight();

                /*
                    IMPORTANT NOTE : You should experiment with border and shadow width
                    to get better circular ImageView as you expected.
                    I am confused about those size.
                */
                // Define border and shadow width
                int borderWidth = 12;
                int shadowWidth = 10;

                // destination bitmap width
               // int dstBitmapWidth = Math.min(srcBitmapWidth,srcBitmapHeight)+borderWidth*2;
                //float radius = Math.min(srcBitmapWidth,srcBitmapHeight)/2;

                // Initializing a new bitmap to draw source bitmap, border and shadow
               // Bitmap dstBitmap = Bitmap.createBitmap(dstBitmapWidth,dstBitmapWidth, Bitmap.Config.ARGB_8888);

                // Initialize a new canvas
               // Canvas canvas = new Canvas(dstBitmap);

                // Draw a solid color to canvas
                //canvas.drawColor(Color.WHITE);

                // Draw the source bitmap to destination bitmap by keeping border and shadow spaces
                //canvas.drawBitmap(srcBitmap, (dstBitmapWidth - srcBitmapWidth) / 2, (dstBitmapWidth - srcBitmapHeight) / 2, null);

                // Use Paint to draw border
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(borderWidth * 2);
                paint.setColor(Color.WHITE);

                // Draw the border in destination bitmap
                //canvas.drawCircle(canvas.getWidth() / 2, canvas.getHeight() / 2, canvas.getWidth() / 2, paint);

                // Use Paint to draw shadow
                paint.setColor(Color.LTGRAY);
                paint.setStrokeWidth(shadowWidth);

                // Draw the shadow on circular bitmap
               // canvas.drawCircle(canvas.getWidth()/2,canvas.getHeight()/2,canvas.getWidth()/2,paint);

                /*
                    RoundedBitmapDrawable
                        A Drawable that wraps a bitmap and can be drawn with rounded corners. You
                        can create a RoundedBitmapDrawable from a file path, an input stream, or
                        from a Bitmap object.
                */
                // Initialize a new RoundedBitmapDrawable object to make ImageView circular
               // RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(mResources, dstBitmap);

                /*
                    setCircular(boolean circular)
                        Sets the image shape to circular.
                */
                // Make the ImageView image to a circular image
                //roundedBitmapDrawable.setCircular(true);

                /*
                    setAntiAlias(boolean aa)
                        Enables or disables anti-aliasing for this drawable.
                */
              //  roundedBitmapDrawable.setAntiAlias(true);

                // Set the ImageView image as drawable object
               // mImageView.setImageDrawable(roundedBitmapDrawable);



        EditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Update.setVisibility(View.VISIBLE);
                CardViewName.setVisibility(View.VISIBLE);
                Password.setEnabled(true);
                Password.setFocusableInTouchMode(true);
                Location.setEnabled(true);
                Name.setEnabled(true);
                Name.setFocusableInTouchMode(true);
                Name.setText(collapsingToolbar.getTitle());
                Location.setFocusableInTouchMode(true);
                Birthdate.setEnabled(true);

                Birthdate.setFocusableInTouchMode(true);
                Gender.setEnabled(true);
                Gender.setFocusableInTouchMode(true);

                Biography.setEnabled(true);
                Biography.setFocusableInTouchMode(true);
            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateprofile(Name.getText().toString(), Password.getText().toString(), Location.getText().toString(), Birthdate.getText().toString(), Gender.getText().toString(), Biography.getText().toString(), getuserimage(getApplicationContext()).toString(), getuid(getApplicationContext()).toString());
                db.Updateuser(Name.getText().toString(), Password.getText().toString(), Location.getText().toString(), Birthdate.getText().toString(), Gender.getText().toString(), Biography.getText().toString(), getemail(getApplicationContext()).toString());
                Update.setVisibility(View.GONE);
                Location.setEnabled(false);
                Birthdate.setEnabled(false);
                Gender.setEnabled(false);
                Biography.setEnabled(false);
                CardViewName.setVisibility(View.GONE);
                autoRefresh();
            }
        });

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogue();
            }
        });

}
    //getting user data

    public String getuid(Context context) {
        db = new SQLiteHandler(context);
        user = db.getUserDetails();
        String uid = user.get("uid");
        return uid;
    }
    public String getlocation(Context context) {
        db = new SQLiteHandler(context);
        user = db.getUserDetails();
        String location = user.get("Location");
        return location;
    }
    public String getname(Context context) {
        db = new SQLiteHandler(context);
        user = db.getUserDetails();
        String name = user.get("name");
        return name;

    }
    public String getemail(Context context) {
        db = new SQLiteHandler(context);
        user = db.getUserDetails();
        String email = user.get("email");
        return email;
    }
    public String getpassword(Context context) {
        db = new SQLiteHandler(context);
        user = db.getUserDetails();
        String password = user.get("password");
        return password;
    }

    public String getgender(Context context) {
        db = new SQLiteHandler(context);
        user = db.getUserDetails();
        String gender = user.get("Gender");
        return gender;
    }

    public String getbirthdate(Context context) {
        db = new SQLiteHandler(context);
        user = db.getUserDetails();
        String birthdate = user.get("birthDate");
        return birthdate;
    }

    public String getbiography(Context context) {
        db = new SQLiteHandler(context);
        user = db.getUserDetails();
        String biography = user.get("Biography");
        return biography;
    }

    public String getuserimage(Context context) {
        db = new SQLiteHandler(context);
        user = db.getUserDetails();
        String userimage = user.get("UserImage");
        return userimage;
    }
//name, encrypted_password, Location , birthDate, Gender, Biography, UserImage,unique_id
    private void updateprofile(final String name, final String password, final String Location, final String birthDate
            , final String Gender, final String Biography, final String UserImage, final String unique_id) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";




        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_update_user, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());


                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {

                        // Launch main activity

                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                //name, encrypted_password, Location , birthDate, Gender, Biography, UserImage,unique_id
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", name);
                params.put("password", password);
                params.put("Location", Location);
                params.put("birthDate", birthDate);
                params.put("Gender", Gender);
                params.put("Biography", Biography);
                params.put("UserImage", UserImage);
                params.put("unique_id", unique_id);

                return params;
            }

        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    public void autoRefresh() {

        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);

    }

    private void showDialogue() {


        list_dialog = new Dialog(user_profile.this);
        list_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        list_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        list_dialog.setContentView(R.layout.upload_photo);

        imageView  = (ImageView) list_dialog.findViewById(R.id.ppimage);
        final TextView uploadButton = (TextView) list_dialog.findViewById(R.id.txtupload);
        final TextView chooseButton = (TextView) list_dialog.findViewById(R.id.txtchoose);
        uploadButton.setVisibility(View.GONE);
        chooseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                //showFileChooser();
                IntentHelper.chooseFileIntent(user_profile.this);
                chooseButton.setVisibility(View.GONE);
                uploadButton.setVisibility(View.VISIBLE);
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

               //uploadImage(getuid(getApplicationContext()).toString());

    /*
      Create the @Upload object
     */
                    if (chosenFile == null) return;
                    createUpload(chosenFile);

    /*
      Start upload
     */
                    new UploadService(user_profile.this).Execute(upload, new UiCallback());
                    autoRefresh();
                list_dialog.dismiss();
            }
        });

        list_dialog.show();
    }


    /*private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image*//*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri returnUri;

        if (requestCode != IntentHelper.FILE_PICK) {
            return;
        }

        if (resultCode != RESULT_OK) {
            return;
        }

        returnUri = data.getData();
        String filePath = DocumentHelper.getPath(this, returnUri);
        //Safety check to prevent null pointer exception
        if (filePath == null || filePath.isEmpty()) return;
        chosenFile = new File(filePath);

                /*
                    Picasso is a wonderful image loading tool from square inc.
                    https://github.com/square/picasso
                 */
        Picasso.with(getBaseContext())
                .load(chosenFile)
                .placeholder(R.mipmap.profile)
                .fit()
                .into(imageView);

    }

    /*public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
*/

    private void createUpload(File image) {
        upload = new Upload();

        upload.image = image;

    }

    private class UiCallback implements Callback<ImageResponse> {

        @Override
        public void success(ImageResponse imageResponse, retrofit.client.Response response) {
            autoRefresh();
        }

        @Override
        public void failure(RetrofitError error) {
            //Assume we have no connection, since error is null
            if (error == null) {
                Snackbar.make(findViewById(R.id.rootview), "No internet connection", Snackbar.LENGTH_SHORT).show();
            }
        }
    }
}
