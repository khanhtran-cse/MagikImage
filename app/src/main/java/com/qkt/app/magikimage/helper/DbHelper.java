package com.qkt.app.magikimage.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.qkt.app.magikimage.helper.DbContract;
import com.qkt.app.magikimage.model.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qkt on 25/07/2017.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final String TAG = "DbHelper";
    private static final int VERSION = 1;
    private Context mContext;

    public DbHelper(Context context){
        super(context,DbContract.DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCreate = "CREATE TABLE " + DbContract.WordDbContract.TABLE_NAME + " ("
                + DbContract.WordDbContract.COLUMN_ID + " TEXT PRIMARY KEY, "
                + DbContract.WordDbContract.COLUMN_SET + " TEXT, "
                + DbContract.WordDbContract.COLUMN_WORD + " TEXT, "
                + DbContract.WordDbContract.COLUMN_AUDIO + " TEXT, "
                + DbContract.WordDbContract.COLUMN_TRANSLITERATION + " TEXT, "
                + DbContract.WordDbContract.COLUMN_CATEGORY + " TEXT, "
                + DbContract.WordDbContract.COLUMN_MEAN_RU + " TEXT, "
                + DbContract.WordDbContract.COLUMN_MEAN_ZH + " TEXT, "
                + DbContract.WordDbContract.COLUMN_MEAN_JA + " TEXT, "
                + DbContract.WordDbContract.COLUMN_MEAN_DE + " TEXT, "
                + DbContract.WordDbContract.COLUMN_MEAN_IT + " TEXT, "
                + DbContract.WordDbContract.COLUMN_MEAN_KO + " TEXT, "
                + DbContract.WordDbContract.COLUMN_MEAN_FR + " TEXT, "
                + DbContract.WordDbContract.COLUMN_MEAN_ES + " TEXT, "
                + DbContract.WordDbContract.COLUMN_MEAN_AR + " TEXT, "
                + DbContract.WordDbContract.COLUMN_MEAN_TR+ " TEXT, "
                + DbContract.WordDbContract.COLUMN_MEAN_PT + " TEXT, "
                + DbContract.WordDbContract.COLUMN_MEAN_VI + " TEXT, "
                + DbContract.WordDbContract.COLUMN_MEAN_EN + " TEXT, "
                + DbContract.WordDbContract.COLUMN_EXAMPLE + " TEXT, "
                + DbContract.WordDbContract.COLUMN_VERSION + " TEXT, "
                + DbContract.WordDbContract.COLUMN_STATE + " TEXT)";
        sqLiteDatabase.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sqlDrop = "DROP TABLE IF EXISTS " + DbContract.WordDbContract.TABLE_NAME;
        sqLiteDatabase.execSQL(sqlDrop);

        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void addWord(Word word){
        ContentValues values = new ContentValues();
        values.put(DbContract.WordDbContract.COLUMN_ID, word.getId());
        values.put(DbContract.WordDbContract.COLUMN_SET, word.getSet());
        values.put(DbContract.WordDbContract.COLUMN_WORD, word.getWord());
        values.put(DbContract.WordDbContract.COLUMN_AUDIO, word.getAudio());
        values.put(DbContract.WordDbContract.COLUMN_TRANSLITERATION, word.getTransliteration());
        values.put(DbContract.WordDbContract.COLUMN_CATEGORY, word.getCategory());
        values.put(DbContract.WordDbContract.COLUMN_MEAN_RU, word.getMeanRu());
        values.put(DbContract.WordDbContract.COLUMN_MEAN_ZH, word.getMeanZh());
        values.put(DbContract.WordDbContract.COLUMN_MEAN_JA, word.getMeanJa());
        values.put(DbContract.WordDbContract.COLUMN_MEAN_DE, word.getMeanDe());
        values.put(DbContract.WordDbContract.COLUMN_MEAN_IT, word.getMeanIt());
        values.put(DbContract.WordDbContract.COLUMN_MEAN_KO, word.getMeanKo());
        values.put(DbContract.WordDbContract.COLUMN_MEAN_FR, word.getMeanFr());
        values.put(DbContract.WordDbContract.COLUMN_MEAN_ES, word.getMeanEs());
        values.put(DbContract.WordDbContract.COLUMN_MEAN_AR, word.getMeanAr());
        values.put(DbContract.WordDbContract.COLUMN_MEAN_TR, word.getMeanTr());
        values.put(DbContract.WordDbContract.COLUMN_MEAN_PT, word.getMeanPt());
        values.put(DbContract.WordDbContract.COLUMN_MEAN_VI, word.getMeanVi());
        values.put(DbContract.WordDbContract.COLUMN_MEAN_EN, word.getMeanEn());
        values.put(DbContract.WordDbContract.COLUMN_EXAMPLE, word.getExample());
        values.put(DbContract.WordDbContract.COLUMN_VERSION, word.getVersion());
        values.put(DbContract.WordDbContract.COLUMN_STATE, word.getState());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DbContract.WordDbContract.TABLE_NAME,null,values);
    }

    public void  addWordList(List<Word> words){
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            for (int i = 0; i < words.size(); i++) {
                Word word = words.get(i);
                ContentValues values = new ContentValues();
                values.put(DbContract.WordDbContract.COLUMN_ID, word.getId());
                values.put(DbContract.WordDbContract.COLUMN_SET, word.getSet());
                values.put(DbContract.WordDbContract.COLUMN_WORD, word.getWord());
                values.put(DbContract.WordDbContract.COLUMN_AUDIO, word.getAudio());
                values.put(DbContract.WordDbContract.COLUMN_TRANSLITERATION, word.getTransliteration());
                values.put(DbContract.WordDbContract.COLUMN_CATEGORY, word.getCategory());
                values.put(DbContract.WordDbContract.COLUMN_MEAN_RU, word.getMeanRu());
                values.put(DbContract.WordDbContract.COLUMN_MEAN_ZH, word.getMeanZh());
                values.put(DbContract.WordDbContract.COLUMN_MEAN_JA, word.getMeanJa());
                values.put(DbContract.WordDbContract.COLUMN_MEAN_DE, word.getMeanDe());
                values.put(DbContract.WordDbContract.COLUMN_MEAN_IT, word.getMeanIt());
                values.put(DbContract.WordDbContract.COLUMN_MEAN_KO, word.getMeanKo());
                values.put(DbContract.WordDbContract.COLUMN_MEAN_FR, word.getMeanFr());
                values.put(DbContract.WordDbContract.COLUMN_MEAN_ES, word.getMeanEs());
                values.put(DbContract.WordDbContract.COLUMN_MEAN_AR, word.getMeanAr());
                values.put(DbContract.WordDbContract.COLUMN_MEAN_TR, word.getMeanTr());
                values.put(DbContract.WordDbContract.COLUMN_MEAN_PT, word.getMeanPt());
                values.put(DbContract.WordDbContract.COLUMN_MEAN_VI, word.getMeanVi());
                values.put(DbContract.WordDbContract.COLUMN_MEAN_EN, word.getMeanEn());
                values.put(DbContract.WordDbContract.COLUMN_EXAMPLE, word.getExample());
                values.put(DbContract.WordDbContract.COLUMN_VERSION, word.getVersion());
                values.put(DbContract.WordDbContract.COLUMN_STATE, word.getState());

                db.insert(DbContract.WordDbContract.TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } catch (Exception ex){
            Log.i(TAG,"Add word failed.");
        }
        db.endTransaction();
    }

    public Word getWord(String id){
        String sql = "SELECT * FROM " + DbContract.WordDbContract.TABLE_NAME
                + " WHERE " + DbContract.WordDbContract.COLUMN_ID
                + " = " + id;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);

        Word word = null;
        if(cursor.moveToNext()){
            word = getWordFromCursor(cursor);
        }
        return word;
    }

    public void updateWordState(String id, String state){
        ContentValues values = new ContentValues();
        values.put(DbContract.WordDbContract.COLUMN_STATE,state);

        SQLiteDatabase db = this.getWritableDatabase();
        db.update(DbContract.WordDbContract.TABLE_NAME,values,
                DbContract.WordDbContract.COLUMN_ID + " = " + id,null);
    }

    public List<Word> getWordList(){
        String sql = "SELECT * FROM " + DbContract.WordDbContract.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);

        List<Word> words = new ArrayList<>(cursor.getCount());
        if(cursor.moveToFirst()){
            do{
                words.add(getWordFromCursor(cursor));
            } while (cursor.moveToNext());
        }
        return words;
    }

    private Word getWordFromCursor(Cursor cursor){
        Word word = new Word();

        int idIndex = cursor.getColumnIndex(DbContract.WordDbContract.COLUMN_ID);
        int setIndex = cursor.getColumnIndex(DbContract.WordDbContract.COLUMN_SET);
        int wordIndex = cursor.getColumnIndex(DbContract.WordDbContract.COLUMN_WORD);
        int audioIndex = cursor.getColumnIndex(DbContract.WordDbContract.COLUMN_AUDIO);
        int transIndex = cursor.getColumnIndex(DbContract.WordDbContract.COLUMN_TRANSLITERATION);
        int categoryIndex = cursor.getColumnIndex(DbContract.WordDbContract.COLUMN_CATEGORY);
        int ruIndex = cursor.getColumnIndex(DbContract.WordDbContract.COLUMN_MEAN_RU);
        int zhIndex = cursor.getColumnIndex(DbContract.WordDbContract.COLUMN_MEAN_ZH);
        int jaIndex = cursor.getColumnIndex(DbContract.WordDbContract.COLUMN_MEAN_JA);
        int deIndex = cursor.getColumnIndex(DbContract.WordDbContract.COLUMN_MEAN_DE);
        int itIndex = cursor.getColumnIndex(DbContract.WordDbContract.COLUMN_MEAN_IT);
        int koIndex = cursor.getColumnIndex(DbContract.WordDbContract.COLUMN_MEAN_KO);
        int frIndex = cursor.getColumnIndex(DbContract.WordDbContract.COLUMN_MEAN_FR);
        int esIndex = cursor.getColumnIndex(DbContract.WordDbContract.COLUMN_MEAN_ES);
        int arIndex = cursor.getColumnIndex(DbContract.WordDbContract.COLUMN_MEAN_AR);
        int trIndex = cursor.getColumnIndex(DbContract.WordDbContract.COLUMN_MEAN_TR);
        int ptIndex = cursor.getColumnIndex(DbContract.WordDbContract.COLUMN_MEAN_PT);
        int viIndex = cursor.getColumnIndex(DbContract.WordDbContract.COLUMN_MEAN_VI);
        int enIndex = cursor.getColumnIndex(DbContract.WordDbContract.COLUMN_MEAN_EN);
        int exampleIndex = cursor.getColumnIndex(DbContract.WordDbContract.COLUMN_EXAMPLE);
        int versionIndex = cursor.getColumnIndex(DbContract.WordDbContract.COLUMN_VERSION);
        int stateIndex = cursor.getColumnIndex(DbContract.WordDbContract.COLUMN_STATE);

        word.setId(cursor.getString(idIndex));
        word.setSet(cursor.getString(setIndex));
        word.setWord(cursor.getString(wordIndex));
        word.setAudio(cursor.getString(audioIndex));
        word.setTransliteration(cursor.getString(transIndex));
        word.setCategory(cursor.getString(categoryIndex));
        word.setMeanRu(cursor.getString(ruIndex));
        word.setMeanZh(cursor.getString(zhIndex));
        word.setMeanJa(cursor.getString(jaIndex));
        word.setMeanDe(cursor.getString(deIndex));
        word.setMeanIt(cursor.getString(itIndex));
        word.setMeanKo(cursor.getString(koIndex));
        word.setMeanFr(cursor.getString(frIndex));
        word.setMeanEs(cursor.getString(esIndex));
        word.setMeanAr(cursor.getString(arIndex));
        word.setMeanTr(cursor.getString(trIndex));
        word.setMeanPt(cursor.getString(ptIndex));
        word.setMeanVi(cursor.getString(viIndex));
        word.setMeanEn(cursor.getString(enIndex));
        word.setExample(cursor.getString(exampleIndex));
        word.setVersion(cursor.getString(versionIndex));
        word.setState(cursor.getString(stateIndex));

        return word;
    }

    public int getWordCount(){
        String sql = "SELECT * FROM " + DbContract.WordDbContract.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql,null);
        return cursor.getCount();
    }
}
