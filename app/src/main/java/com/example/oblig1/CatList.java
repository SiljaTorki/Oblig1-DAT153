package com.example.oblig1;

import android.graphics.Bitmap;
import android.net.Uri;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CatList {

    private static ArrayList<Cat> catList;

    public CatList(){
        catList = new ArrayList<Cat>();
    }

    public static void addCat(String navn, Bitmap bilde){
        Cat cat = new Cat(navn, bilde);
        catList.add(cat);
    }

    public static ArrayList<Cat> getCatList() {
        return catList;
    }

    public void setCatList(ArrayList<Cat> catList) {
        this.catList = catList;
    }


}
