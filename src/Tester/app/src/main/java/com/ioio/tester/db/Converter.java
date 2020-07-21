package com.ioio.tester.db;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;

public class Converter {

    @TypeConverter
    public String fromArray(ArrayList<String> strings) {
        StringBuilder string = new StringBuilder();
        for(String s : strings) string.append(s).append(" ");

        return string.toString().trim();
    }

    @TypeConverter
    public ArrayList<String> toArray(String concatenatedStrings) {

        return new ArrayList<>(Arrays.asList(concatenatedStrings.split(" ")));
    }
}
