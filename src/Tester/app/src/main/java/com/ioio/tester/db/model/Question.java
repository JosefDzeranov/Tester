package com.ioio.tester.db.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "questions")
public class Question {
    @PrimaryKey
    public int number;
    public String question;
    public ArrayList<String> answers = new ArrayList<>();
    public String rightAnswer;

    public Question(){
        number = 0;
        answers = new ArrayList<>();
        rightAnswer = "";
        question = "";
    }
    public Question(int number, ArrayList<String> answers, String rightAnswer, String question){
        this.number = number;
        this.answers.addAll(answers);
        this.rightAnswer = rightAnswer;
        this.question = question;
    }

}
