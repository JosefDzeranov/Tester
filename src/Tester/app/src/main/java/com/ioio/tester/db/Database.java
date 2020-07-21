package com.ioio.tester.db;

import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.ioio.tester.db.dao.QuestionsDao;
import com.ioio.tester.db.dao.ResultsDao;
import com.ioio.tester.db.model.Question;
import com.ioio.tester.db.model.Result;

@androidx.room.Database(entities = {Question.class}, version = 1)
@TypeConverters(Converter.class)
public abstract class Database extends RoomDatabase {
    public abstract QuestionsDao questionsDao();
}
