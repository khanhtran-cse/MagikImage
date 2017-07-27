package com.qkt.app.magikimage.activity.homeActivity;

import com.qkt.app.magikimage.model.MagikResponse;
import com.qkt.app.magikimage.model.Word;

import java.util.List;

import retrofit2.Response;

/**
 * Created by qkt on 25/07/2017.
 */

public interface HomeInterface {
    interface View{
        void updateWordList();
        void updateWordImage(int position);
        void onError();
        void showProgress();
        void hideProgress();
    }

    interface Presenter{
        void onDownloadDataSuccess(Response<MagikResponse> response);
        void onDownloadDataError();
        void onDownloadImageSuccess(int position, Word word);
        void onDownloadImageError(int position, Word word);
    }
}
