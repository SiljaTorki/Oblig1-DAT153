package com.example.oblig1.helpers;

/**
* This class is helping QuizActivity.java
*  - checking that the user´s guess
*  - return the number of correct answers
 */
public class QuizHelp {

    private int correct = 0;

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
    * To get the number of correct answers
    * Method is called in btnCheckAnswer
    */
    public int getCorrect(){
        return correct;
    }
}
