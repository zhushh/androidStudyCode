package com.example.zhushh.myapplication;

import android.content.Context;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by zhushh on 2015/11/27.
 */
public class FileUtils {
    public void saveContent(Context context,String fileName, String fileText) {
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(fileText.getBytes());
            fos.close();
            Toast.makeText(context, "Save Content Success", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            Toast.makeText(context, "Save Content Fail", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(context, "Save Content Fail", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public String getContent(Context context, String fileName) {
        try {
            FileInputStream fis = context.openFileInput(fileName);
            byte[] contents = new byte[fis.available()];
            fis.read(contents);
            fis.close();
            Toast.makeText(context, "Read Content Success", Toast.LENGTH_SHORT).show();
            return new String(contents);
        } catch (FileNotFoundException e) {
            Toast.makeText(context, "Read Content Fail", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return new String("");
        } catch (IOException e) {
            Toast.makeText(context, "Read Content Fail", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return new String("");
        }
    }

    public void deleteFile(Context context, String fileName) {
        context.deleteFile(fileName);
        Toast.makeText(context, "Delete File Success", Toast.LENGTH_SHORT).show();
    }
}
