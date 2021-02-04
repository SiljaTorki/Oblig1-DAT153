package com.example.oblig1.hjelpeklasse;

import android.widget.EditText;

import com.example.oblig1.domain.Cat;
import com.example.oblig1.domain.CatList;

import java.util.ArrayList;
import java.util.List;

public class QuizHelp {

    private List<Cat> original;
    private List<Cat> cats;
    private int correct = 0;

    //Returns a response to user´s quess
    //Method is called in btnCheckAnswer in Quiz.java
    public CharSequence checkAnswer(String answer, String name){
        CharSequence response;
        if(answer.equals(name)){          //check if editText equals the text connected with the photo
            response = "Rett svar!";      //if it is correct, then a toast saying its correct
            correct++;
        }else{
            response = "Feil svar!";      //if incorrect, the toast should say it is wrong
        }
        return response;

    }

    //Creates a random list of cats
    //method is called in btnNext onClickListener
    public List<Cat> randomList(){
        original = CatList.getCatList();      // Get the full list from data structure
        int max = original.size();

        int tall;
        cats = new ArrayList<Cat>(original.size());
        //Loop that generates a random list
        for(int j = 0; j < original.size(); j++) {
            tall = (int) (Math.random()*original.size());
            if(!cats.contains(original.get(tall)))           //Check if it already exist
                cats.add(j, original.get(tall));             //Add to the list
            else
                j--;      //To secure that you get all cats transferred to the new list
        }
        return cats;

    }

    //To get the number of correct answers
    //Method is called in btnCheckAnswer
    public int getCorrect(){
        return correct;
    }
}
