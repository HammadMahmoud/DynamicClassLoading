package edu.uci.seal.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        loadCode();
    }

    public void loadCode(){
        // read the jar file which contains classes.dex file.
        // You can download the file from any source, SD card or internet.
        // This exaple reads the JAR file from Download folder of the sd_card

//        String jarContainerPath =  Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/example.jar";

        //avd_nexus4_sdcard is a shared folder in my Genymotion emulator
        String jarContainerPath =  "/mnt/shared/avd_nexus4_sdcard/dexHiddenBehavior.jar";
        File dexOutputDir = getDir("dex", MODE_PRIVATE);
        //load the code
        DexClassLoader mDexClassLoader = new DexClassLoader(jarContainerPath,
                dexOutputDir.getAbsolutePath(),
                null,
                getClass().getClassLoader());
        try {
            //use java reflection to call a method in the loaded class
            Class<?> loadedClass = mDexClassLoader.loadClass("edu.uci.seal.icc.HiddenBehavior");

            //list all methods in the class
            Method[] methods = loadedClass.getDeclaredMethods();
            for (int i=0; i<methods.length; i++){
                Log.i("Dynamic","Method: "+methods[i].getName());
            }

            Method methodGetIntent = loadedClass.getMethod("getIntent", java.lang.String.class);
            Object object = loadedClass.newInstance();
            Intent intent = (Intent) methodGetIntent.invoke(object, "activity");
            if (intent!=null) {
                startActivity(intent);
            }


        }catch (Exception e){
            e.printStackTrace();
        }


    }

}

