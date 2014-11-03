package com.cbedoy.apprende.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.cbedoy.apprende.interfaces.ICameraInformationDelegate;
import com.cbedoy.apprende.interfaces.ICameraInformationHandler;
import com.cbedoy.apprende.service.InjectionManager;

/**
 * Created by Carlos on 17/10/2014.
 */
public class MainActivity extends MasterViewController implements ICameraInformationHandler
{
    private final int CAMERA_REQUEST = 96543;
    private ICameraInformationDelegate cameraInformationDelegate;

    public void setCameraInformationDelegate(ICameraInformationDelegate cameraInformationDelegate) {
        this.cameraInformationDelegate = cameraInformationDelegate;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        InjectionManager.getInstance().performApprendeFlow(this);
    }

    @Override
    public void userWantsShowCamera() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            cameraInformationDelegate.setPreviusImage(photo);
        }

    }
}
