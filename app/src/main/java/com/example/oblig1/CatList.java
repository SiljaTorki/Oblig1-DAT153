package com.example.oblig1;

import android.net.Uri;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CatList {

    private static ArrayList<Cat> catList;
    //private ArrayList<Cat> catList;

    public CatList(){
        catList = new ArrayList<Cat>();
    }

    public void addCat(String navn, Uri bilde){
        Cat cat = new Cat(navn, bilde);
        catList.add(cat);
    }

    public static ArrayList<Cat> getCatList() {
        return catList;
    }

    public void setCatList(ArrayList<Cat> catList) {
        this.catList = catList;
    }

    public void setUp(){

        Uri uri1 = Uri.parse("android.resource://com.example.oblig1/drawable/cat_one");
        Uri uri2 = Uri.parse("android.resource://com.example.oblig1/drawable/cat_two");
        Uri uri3 = Uri.parse("android.resource://com.example.oblig1/drawable/cat_three3");

        catList.add(new Cat("Cat one", uri1));
        catList.add(new Cat("Cat two", uri2));
        catList.add(new Cat("Cat three", uri3));
    }
}
