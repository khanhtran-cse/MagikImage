package com.qkt.app.magikimage.activity.homeActivity;

import android.content.Context;
import android.util.Log;

import com.qkt.app.magikimage.constant.Constant;
import com.qkt.app.magikimage.constant.Database;
import com.qkt.app.magikimage.helper.DbHelper;
import com.qkt.app.magikimage.model.MagikResponse;
import com.qkt.app.magikimage.model.Word;
import com.qkt.app.magikimage.service.DownloadImage;
import com.qkt.app.magikimage.service.MagikService;

import java.io.File;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by qkt on 25/07/2017.
 */

public class HomeModel {
    private static final String TAG = "HomeModel";
    private HomeInterface.Presenter mPresenter;
    private MagikService mService;
    private Context mContext;
    private DbHelper dbHelper;

    public HomeModel(Context context,HomeInterface.Presenter presenter){
        mContext = context;
        mPresenter = presenter;
        mService = new MagikService();
        dbHelper = Database.getInstance(mContext).getDbHelper();
    }

    public void downloadWordList(){
        mService.downloadWordList().enqueue(new Callback<MagikResponse>() {
            @Override
            public void onResponse(Call<MagikResponse> call, Response<MagikResponse> response) {
                mPresenter.onDownloadDataSuccess(response);
            }

            @Override
            public void onFailure(Call<MagikResponse> call, Throwable t) {
                mPresenter.onDownloadDataError();
            }
        });
    }

    public void updateWordState(String id, String state){
        dbHelper.updateWordState(id,state);
    }

    public void updateDatabase(List<Word> words){
        dbHelper.addWordList(words);
    }

    public int getWordCount(){
        return dbHelper.getWordCount();
    }

    public Word getWord(String id){
        return dbHelper.getWord(id);
    }

    public List<Word> getWordList(){
        return dbHelper.getWordList();
    }

    public void downloadImage(int position, Word word){
        DownloadImage downloadImage = new DownloadImage(position, word, new DownloadImage.DownloadImageCallback() {
            @Override
            public void onSuccess(int position, Word word) {
                mPresenter.onDownloadImageSuccess(position,word);
            }

            @Override
            public void onError(int position, Word word) {
                mPresenter.onDownloadImageError(position,word);
            }
        });
        downloadImage.startDownload();
    }
}
