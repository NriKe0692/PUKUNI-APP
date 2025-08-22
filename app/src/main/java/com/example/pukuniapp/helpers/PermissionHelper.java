package com.example.pukuniapp.helpers;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionHelper {

    public static final int REQUEST_CAMERA = 100;
    public static final int REQUEST_GALLERY = 101;
    public static final int REQUEST_LOCATION = 102;

    private final Activity activity;

    public PermissionHelper(Activity activity) {
        this.activity = activity;
    }

    public boolean hasPermission(String permission) {
        return ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public void requestPermission(String permission, int requestCode) {
        ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
    }

    public boolean hasCameraPermission() {
        return hasPermission(Manifest.permission.CAMERA);
    }

    public void requestCameraPermission() {
        requestPermission(Manifest.permission.CAMERA, REQUEST_CAMERA);
    }

    public boolean hasGalleryPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return hasPermission(Manifest.permission.READ_MEDIA_IMAGES);
        } else {
            return hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }

    public void requestGalleryPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermission(Manifest.permission.READ_MEDIA_IMAGES, REQUEST_GALLERY);
        } else {
            requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE, REQUEST_GALLERY);
        }
    }

    public boolean hasLocationPermission() {
        return hasPermission(Manifest.permission.ACCESS_FINE_LOCATION);
    }

    public void requestLocationPermission() {
        requestPermission(Manifest.permission.ACCESS_FINE_LOCATION, REQUEST_LOCATION);
    }
}
