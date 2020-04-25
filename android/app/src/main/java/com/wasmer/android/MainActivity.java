package com.wasmer.android;

import androidx.annotation.Keep;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private static native void JNIExecuteWasm(MainActivity self) throws Exception;

    @Keep
    public void Test(String test) {
        System.out.println(test);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            // Wait for debugger to attach so
            // we can see all output
            Thread.sleep(2000);

            // Load runtime code
            System.loadLibrary("wasmer_android");

            // default code of the empty activity example
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // Run the file
            System.out.println("Calling JNIExecuteWasm!");
            JNIExecuteWasm(this);
            System.out.println("Finished calling JNIExecuteWasm!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Finished program!");
    }
}
