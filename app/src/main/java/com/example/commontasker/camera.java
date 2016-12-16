package com.example.commontasker;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Αρης on 21/10/2016.
 */
public class camera extends AppCompatActivity {
    public static final int REQUEST_CAPTURE=1;
    ImageView  result;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
      Button btn=(Button) findViewById(R.id.button19);
        result=(ImageView)findViewById(R.id.photo);
        
        if(!hasCamera()){
            btn.setEnabled(false);
        }

    }

    private boolean hasCamera() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    public void launchCamera(View v){

        Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i,REQUEST_CAPTURE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_CAPTURE &&resultCode==RESULT_OK){
            Bundle extras=data.getExtras();
            Bitmap photo=(Bitmap) extras.get("data");
            result.setImageBitmap(photo);
        }
    }
}
