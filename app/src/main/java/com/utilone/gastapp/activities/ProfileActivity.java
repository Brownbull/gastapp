package com.utilone.gastapp.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.utilone.gastapp.R;
import com.utilone.gastapp.model.User;
import com.utilone.gastapp.sql.DatabaseHelper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {
  // SYSTEM
  private final AppCompatActivity activity = ProfileActivity.this;
  private DatabaseHelper databaseHelper;
  private static final int CAMERA_REQUEST = 1888;
  private static final int MY_CAMERA_PERMISSION_CODE = 100;
  // FRONT END
  private TextView tvName;
  private ImageView imgProfile;
  private TextView tvMail;
  // BACK END
  long userID;
  private User user;

  

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    Intent Panel = getIntent();
    String sUserID = Panel.getStringExtra("USERID");
    userID = Long.valueOf(sUserID);

    // INIT
    initViews();
    initObjects(userID);
  }

  private void initViews() {
    tvName = (TextView) findViewById(R.id.tvName);
    imgProfile = (ImageView) findViewById(R.id.imageProfile);
    tvMail = (TextView) findViewById(R.id.tvMail);
    
  }

  private void initObjects(long userID) {
    databaseHelper = new DatabaseHelper(activity);
    user = databaseHelper.getUser(userID); 
    Log.i("Profile initObjects", "user" + user.toString());
    // imgProfile.setImageResource(R.drawable.my_image);
    updateObjects();
  }

  private void updateObjects() {
    tvName.setText(user.getName());
    tvMail.setText(user.getEmail());
    // imgProfile.setImageResource(R.drawable.my_image);
    }

  public void setImage(View view){
    Toast.makeText(this, "set Image", Toast.LENGTH_SHORT).show();
    // Intent setImageIntent = new Intent(activity, CameraActivity.class);

    // startActivity(setImageIntent);
    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
    {
      requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
    }
    else
    {
      Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
      startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
  {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == MY_CAMERA_PERMISSION_CODE)
    {
      if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
      {
        Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
      }
      else
      {
        Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
      }
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data)
  {
    if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK)
    {
      Bitmap photo = (Bitmap) data.getExtras().get("data");
      imgProfile.setImageBitmap(photo);
    }
  }

}
