package com.zpi.FileManager;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileManager<T> {
    private final String path = Environment.getExternalStorageDirectory() + "/BudgetApp";
    private String fileName;

    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    public void saveToFile(List<T> elements) {
        createDir();
        createFile(elements);
    }

    public List<T> getFromFile() {
        List<T>resultList;
        FileInputStream fin;
        ObjectInputStream ois;

        try {
            fin = new FileInputStream(path + "/" + fileName);
            ois = new ObjectInputStream(fin);
            resultList = (ArrayList<T>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            resultList = new ArrayList<>();
            Log.d("error", e.getMessage());
        }
        return resultList;
    }

    private void createFile(List<T> elements) {
        File file = new File(path + "/" + fileName);
        FileOutputStream fOut;
        ObjectOutputStream oos;
        try {
            fOut = new FileOutputStream(file);
            oos = new ObjectOutputStream(fOut);
            oos.writeObject(elements);
            fOut.close();
        } catch (Exception e) {
            Log.d("error", e.getMessage());
        }

    }

    private void createDir() {
        File folder = new File(path);
        if (!folder.exists()) {
            try {
                folder.mkdir();
            } catch (Exception e) {
                Log.d("error", e.getMessage());
            }
        }
    }


}




