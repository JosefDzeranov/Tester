package com.ioio.tester.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.ioio.tester.db.model.Question;
import com.ioio.tester.db.model.Result;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface ResultsDao {
    @Query("SELECT * FROM questions")
    Single<List<Result>> getAll();

    @Insert
    Single<Void> insert(Result results);

    @Delete
    Single<Void> delete(Result result);
}