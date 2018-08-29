package com.example.prigui.gamesapp.utils;


import com.example.prigui.gamesapp.BuildConfig;

/**
 * Created by prigui on 14/08/2018.
 */

public class Constants {

    public static final String PACKAGE_NAME = BuildConfig.APPLICATION_ID;

    public static final String API_KEY = "29cd233442a3f6021e90f6e88e40d415";

    public static final String URL_IMAGE = "https://images.igdb.com/igdb/image/upload/t_cover_small_2x/";

    //EXTRA INTENT
    public static final String EXTRA_GAMES = PACKAGE_NAME + "EXTRA_MOVIE";

    //Requests Query and Columns

    public static final String QUERY_GAMES = "cover, name, genres, platforms, summary";
    public static final String QUERY_GENERAL_PLARFORMS = "id, name, games";
    //public static final String QUERY_PLARFORMS = "platforms";

    public static final String QUERY_LIMMIT_30 = "30";
    public static final String QUERY_LIMMIT_20 = "20";

    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_GAMES = "games";
    public static final String FIELD_SUMMARY = "summary";
    public static final String FIELD_PLATFORM = "platforms";
    public static final String FIELD_COVER = "cover";
    public static final String FIELD_GENRES = "genres";
    public static final String FIELD_CLOUDINARY_ID = "cloudinary_id";

    public static final String FIELD_IMAGE_EXTENTION = ".jpg";






}
