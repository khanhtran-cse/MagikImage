package com.qkt.app.magikimage.service;

import android.util.Log;

import com.qkt.app.magikimage.constant.Constant;
import com.qkt.app.magikimage.model.Word;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by qkt on 25/07/2017.
 */

public class DownloadImage {
    private static final String TAG = "DownloadImage";
    private int mPosition;
    private Word mWord;
    private DownloadImageCallback mCallback;

    public DownloadImage(int position, Word word, DownloadImageCallback callback){
        mPosition = position;
        mWord = word;
        mCallback = callback;
    }

    public void startDownload(){
        final String imageUrl = Constant.BASE_IMAGE_URL + mWord.getSet() + "/" + mWord.getWord() + ".jpg";

        Observable observable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(@NonNull ObservableEmitter e) throws Exception {
                InputStream is = null;
                FileOutputStream os = null;
                try {
                    URL url = new URL(imageUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.connect();
                    is = connection.getInputStream();

                    File dir = new File(Constant.BASE_PATH);
                    if(!dir.exists()) {
                        boolean r = dir.mkdir();
                        if(!r){
                            Log.i(TAG,"Make dir error - " + dir.getAbsolutePath());
                            e.onError(new Exception());
                            return;
                        }
                    }

                    dir = new File(dir,"Images");
                    if(!dir.exists()) {
                        boolean r = dir.mkdir();
                        if(!r){
                            Log.i(TAG,"Make dir error - " + dir.getAbsolutePath());
                            e.onError(new Exception());
                            return;
                        }
                    }

                    dir = new File(dir,mWord.getSet());
                    if(!dir.exists()) {
                        boolean r = dir.mkdir();
                        if(!r){
                            Log.i(TAG,"Make dir error - " + dir.getAbsolutePath());
                            e.onError(new Exception());
                            return;
                        }
                    }

                    File f = new File(dir,mWord.getWord() + ".jpg");
                    if(!f.exists()){
                        boolean r = f.createNewFile();
                        if(!r){
                            Log.i(TAG,"Make file error - " + f.getAbsolutePath());
                            e.onError(new Exception());
                            return;
                        }
                    }
                    else{
                        f.delete();
                    }

                    os = new FileOutputStream(f);

                    byte[] buffer = new byte[1024];
                    int len = is.read(buffer);

                    while(len >0){
                        os.write(buffer,0,len);
                        len = is.read(buffer);
                    }
                    os.close();
                    is.close();
                    Log.i(TAG,"Download image " + mWord.getWord() + ".jpg success.");
                    e.onComplete();
                } catch (Exception ex){
                    Log.i(TAG,"Download image error.");
                    ex.printStackTrace();
                    try {
                        if(os != null) os.close();
                        if(is != null) is.close();
                    }catch (Exception exc){
                    }
                    e.onError(ex);
                }
            }
        });

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Object o) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mCallback.onError(mPosition,mWord);
                    }

                    @Override
                    public void onComplete() {
                        mCallback.onSuccess(mPosition,mWord);
                    }
                });
    }

    public interface DownloadImageCallback{
        void onSuccess(int position, Word word);
        void onError(int position, Word word);
    }
}
