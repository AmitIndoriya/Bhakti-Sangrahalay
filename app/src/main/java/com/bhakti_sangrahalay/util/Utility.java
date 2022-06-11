package com.bhakti_sangrahalay.util;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utility {

    public String readFromFile(Resources resources, int file) {
        String jsonStr = "";
        try {
            InputStream inputStream = resources.openRawResource(file);
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }
                inputStream.close();
                jsonStr = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("readFromFile", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("readFromFile", "Can not read file: " + e.toString());
        }
        return jsonStr;
    }

    public int[] getIntArray(Resources resources, int id) {
        final TypedArray sounds = resources.obtainTypedArray(id);
        int[] resIds = new int[sounds.length()];
        for (int i = 0; i < sounds.length(); i++) {
            resIds[i] = sounds.getResourceId(i, -1);
        }
        sounds.recycle();
        return resIds;
    }


    public static boolean checkPermission(Activity activity) {
        boolean boolVal = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                boolVal = false;
            }
        }
        return boolVal;
    }


    public static void requestPermission(final Activity activity, Fragment fragment, final int permissionRequestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setMessage("Need Read external storage permission.");
                builder.setTitle("Please grant permission");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(
                                activity,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                permissionRequestCode
                        );
                    }
                });
                builder.setNeutralButton("Cancel", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                fragment.requestPermissions(
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        permissionRequestCode
                );
            }
        } else {
            ActivityCompat.requestPermissions(
                    activity,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    permissionRequestCode
            );
        }

    }
    public void requestPermission(final Activity activity,final int permissionRequestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setMessage("Need Read external storage permission.");
                builder.setTitle("Please grant permission");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(
                                activity,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                permissionRequestCode
                        );
                    }
                });
                builder.setNeutralButton("Cancel", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                activity.requestPermissions(
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        permissionRequestCode
                );
            }
        } else {
            ActivityCompat.requestPermissions(
                    activity,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    permissionRequestCode
            );
        }
    }

    public static boolean isFileExist(String fileName, long filesize) {
        boolean val = false;
        File file = new File(Environment.getExternalStorageDirectory() + "/aarti/" + fileName);
        if (file.exists()) {
            long len = file.length();
            if (len >= filesize) {
                val = true;
            } else {
                file.delete();
            }
        }
        return val;
    }

    public static boolean isConnectedWithInternet(Context context) {
        boolean _isNetAvailable = false;
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null) {
            _isNetAvailable = wifiNetwork.isConnectedOrConnecting();
        }

        NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetwork != null) {
            _isNetAvailable = mobileNetwork.isConnectedOrConnecting();
        }

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            _isNetAvailable = activeNetwork.isConnectedOrConnecting();
        }
        return _isNetAvailable;
    }
    public static void printLog(String msg){
        Log.i("LogMsg>>",msg);
    }
}
