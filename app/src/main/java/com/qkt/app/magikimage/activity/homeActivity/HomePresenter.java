package com.qkt.app.magikimage.activity.homeActivity;

import android.content.Context;
import android.util.Log;

import com.qkt.app.magikimage.constant.WordList;
import com.qkt.app.magikimage.model.MagikResponse;
import com.qkt.app.magikimage.model.Word;

import java.util.List;

import retrofit2.Response;

/**
 * Created by qkt on 25/07/2017.
 */

public class HomePresenter implements HomeInterface.Presenter{
    private static final String TAG = "HomePresenter";
    private Context mContext;
    private HomeInterface.View mView;
    private HomeModel mModel;
    private List<Word> mWords;

    public HomePresenter(Context context, HomeInterface.View view){
        mContext = context;
        mView = view;
        mModel = new HomeModel(mContext,this);
        mWords = WordList.getIns().getList();
    }

    public void initializeWordList(){
        mView.showProgress();
        if(mModel.getWordCount() <= 0){
            Log.i(TAG,"Download words from internet.");
            mModel.downloadWordList();
        }
        else{
            Log.i(TAG,"Load words from database.");
            List<Word> words = mModel.getWordList();
            if(words != null){
                mWords.clear();
                for(int i = 0;i < words.size();i++){
                    if(words.get(i) != null){
                        mWords.add(words.get(i));
                    }
                }
            }
            showWordList();
        }
    }

    @Override
    public void onDownloadDataSuccess(Response<MagikResponse> response) {
        if(response.isSuccessful()){
            try {
                MagikResponse magikResponse = response.body();
                List<Word> words = magikResponse.getData();

                if(words != null){
                    for(int i = 0;i < words.size();i++){
                        if(words.get(i) != null){
                            mWords.add(words.get(i));
                        }
                    }
                }
                showWordList();
                mModel.updateDatabase(mWords);
                Log.i(TAG,"Count" + mWords.size());
                return;
            } catch(Exception ex){
                Log.i(TAG,"Response error.");
                ex.printStackTrace();
            }
        }
        onDownloadDataError();
    }

    @Override
    public void onDownloadDataError() {
        Log.i(TAG,"Download error.");
        mView.hideProgress();
        mView.onError();
    }

    private void showWordList() {
        mView.updateWordList();
        mView.hideProgress();
    }

    public void downloadImage(int position){
        if(position < mWords.size() && position >= 0) {
            Word word = mWords.get(position);
            if(word.getState().equals(Word.STATE_NO_DOWNLOAD)) {
                Log.i(TAG, "Download image: " + word.getWord());

                word.setState(Word.STATE_DOWNLOADING);
                mModel.downloadImage(position, word);
            }
            else if(word.getState().equals(Word.STATE_DOWNLOADED)){
                Log.i(TAG,"The image " +  word.getSet() + "/" + word.getWord() +
                ".jpg downloaded. The file will be check before re-download.");
                word.setState(Word.STATE_DOWNLOADING);
                mModel.downloadImage(position,word);
            }
            else if(word.getState().equals(Word.STATE_DOWNLOAD_ERROR)){
                Log.i(TAG,"The image " +  word.getSet() + "/" + word.getWord() +
                        ".jpg downloaded error. The file will be check before re-download.");
                word.setState(Word.STATE_DOWNLOADING);
                mModel.downloadImage(position,word);
            }
        }
    }

    @Override
    public void onDownloadImageSuccess(int position, Word word) {
        if(position >= 0 && position < mWords.size()){
            if(mWords.get(position).getId().equals(word.getId())){
                Log.i(TAG,"Download image " + word.getWord() + ".jpg success.");
                mModel.updateWordState(word.getId(),Word.STATE_DOWNLOADED);
                mWords.get(position).setState(Word.STATE_DOWNLOADED);
                mView.updateWordImage(position);
            }
        }
        Log.i(TAG,mWords.get(position).getWord() + " - " + mWords.get(position).getState());
    }

    @Override
    public void onDownloadImageError(int position, Word word) {
        if(position >= 0 && position < mWords.size()){
            if(mWords.get(position).getId().equals(word.getId())){
                Log.i(TAG,"Download image " + word.getWord() + ".jpg success.");
                mModel.updateWordState(word.getId(),Word.STATE_DOWNLOAD_ERROR);
                mWords.get(position).setState(Word.STATE_DOWNLOAD_ERROR);
            }
        }
        Log.i(TAG,mWords.get(position).getWord() + " - " + mWords.get(position).getState());
    }
}
