package com.example.oblig1;

import android.net.Uri;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CatList {

    private ArrayList<Cat> catList;

    public CatList(){
        ArrayList<Cat> catList = new ArrayList<Cat>();
    }

    public void addCat(String navn, Uri bilde){
        Cat cat = new Cat(navn, bilde);
        catList.add(cat);
    }

    public ArrayList<Cat> getCatList() {
        return catList;
    }

    public void setCatList(ArrayList<Cat> catList) {
        this.catList = catList;
    }
}
