package com.qkt.app.magikimage.helper;

/**
 * Created by qkt on 25/07/2017.
 */

public class DbContract {
    public static final String DATABASE_NAME = "magik.db";

    public class WordDbContract{
        public static final String TABLE_NAME = "word";

        public static final String COLUMN_ID = "id";
        public static final String COLUMN_SET = "set_number";
        public static final String COLUMN_WORD = "word";
        public static final String COLUMN_AUDIO = "audio";
        public static final String COLUMN_TRANSLITERATION = "transliteration";
        public static final String COLUMN_CATEGORY = "category";

        public static final String COLUMN_MEAN_RU = "mean_ru";
        public static final String COLUMN_MEAN_ZH = "mean_zh";
        public static final String COLUMN_MEAN_JA = "mean_ja";
        public static final String COLUMN_MEAN_DE = "mean_de";
        public static final String COLUMN_MEAN_IT = "mean_it";
        public static final String COLUMN_MEAN_KO = "mean_ko";
        public static final String COLUMN_MEAN_FR = "mean_fr";
        public static final String COLUMN_MEAN_ES = "mean_es";
        public static final String COLUMN_MEAN_AR = "mean_ar";
        public static final String COLUMN_MEAN_TR = "mean_tr";
        public static final String COLUMN_MEAN_PT = "mean_pt";
        public static final String COLUMN_MEAN_VI = "mean_vi";
        public static final String COLUMN_MEAN_EN = "mean_en";

        public static final String COLUMN_EXAMPLE = "example";
        public static final String COLUMN_VERSION = "version";
        public static final String COLUMN_STATE = "state";
    }
}
