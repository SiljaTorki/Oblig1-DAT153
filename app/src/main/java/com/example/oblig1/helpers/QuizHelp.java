package com.example.oblig1.helpers;

import com.example.oblig1.domain.Cat;
import com.example.oblig1.sqlDAOs.CatDao;
//import com.example.oblig1.domain.CatList;

import java.util.ArrayList;
import java.util.List;

/**
* This class is helping QuizActivity.java
*  - checking that the user´s guess
*  - creates a random list of cats
*  - return the amount of correct answers
 */
public class QuizHelp {

    private List<Cat> original;
    private List<Cat> cats;
    private int correct = 0;

    private CatDao catDao;

    /*
    * Returns a response/answer to user´s guess, used in a toast
    * Method is called in btnCheckAnswer in QuizActivity.java
    */
    public CharSequence checkAnswer(String answer, String name){
        CharSequence response;
        if(answer.equals(name)){
            response = "Correct answer!";      //if correct guess
            correct++;
        }else{
            response = "Wrong answer!";        //else wrong guess
        }
        return response;

    }
    /*
    * Creates a random list of cats
    * method is called in btnNext onClickListener
    */
    public List<Cat> randomList(List<Cat> original) {
        // Get the full list from data structure
        //original = CatList.getCatList();
        int tall;

        cats = new ArrayList<Cat>(original.size());
        //Loop that generates a random list
        for(int j = 0; j < original.size(); j++) {
            tall = (int) (Math.random()*original.size());
            if(!cats.contains(original.get(tall)))           //Check if it already exist
                cats.add(j, original.get(tall));             //AddActivity to the list
            else
                j--;      //To secure that all cats are transferred to the new list
        }
        return cats;

    }
    /*
    * To get the number of correct answers
    * Method is called in btnCheckAnswer
    */
    public int getCorrect(){
        return correct;
    }
}
