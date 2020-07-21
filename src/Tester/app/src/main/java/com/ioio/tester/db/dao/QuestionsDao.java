package com.ioio.tester.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ioio.tester.db.model.Question;

import java.util.List;

import io.reactivex.Single;


@Dao
public interface QuestionsDao {
    @Query("SELECT * FROM questions")
    Single<List<Question>> getAll();

    @Query("SELECT * FROM questions WHERE number = :number")
    Single<Question> get(int number);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<List<Long>> insert(Question... questions);

    @Delete
    Single<Integer> delete(Question question);
}