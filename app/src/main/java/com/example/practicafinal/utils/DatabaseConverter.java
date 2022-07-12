package com.example.practicafinal.utils;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseConverter {
    @TypeConverter
    public static String toString(ArrayList<String> list){
        return String.join(",",list);
    }

    public static ArrayList<String> toArrayList(String list) {
        return new ArrayList<>(Arrays.asList(list.split(",")));
    }
}
