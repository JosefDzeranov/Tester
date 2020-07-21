package com.ioio.tester.db.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "results")
public class Result {
    @PrimaryKey(autoGenerate = true)
    public int id = 0;
    public int rightAnswers;
    public String userName;
    public Result(){}
    public Result(int right, String name){
        rightAnswers = right;
        userName = name;
    }
}
