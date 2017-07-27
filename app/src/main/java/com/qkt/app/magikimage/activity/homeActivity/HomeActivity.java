package com.qkt.app.magikimage.activity.homeActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.qkt.app.magikimage.adapter.ImageAdapter;
import com.qkt.app.magikimage.R;
import com.qkt.app.magikimage.constant.WordList;
import com.qkt.app.magikimage.helper.DbContract;
import com.qkt.app.magikimage.model.Word;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeInterface.View{
    private static final String TAG = "HomeActivity";
    private static final int REQUEST_PERMISSION =  1;
    private ImageAdapter mAdapter;
    private HomePresenter mPresenter;
    @BindView(R.id.rv_words) RecyclerView mWordList;
    @BindView(R.id.pb_wait) ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        requestPermission();

        //Test
//        this.deleteDatabase(DbContract.DATABASE_NAME);

        mPresenter = new HomePresenter(this,this);
        mPresenter.initializeWordList();
    }

    @Override
    public void updateWordImage(int position) {
        mAdapter.notifyItemChanged(position);
    }

    @Override
    public void updateWordList() {
        //calculate column
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = (int) (metrics.widthPixels/(metrics.densityDpi/160f));
        int column = width/150;
        if(column <= 0) column = 1;
        Log.i(TAG,"Column: " + column);

        mAdapter = new ImageAdapter(this,mPresenter);
        mWordList.setAdapter(mAdapter);
        mWordList.setLayoutManager(new GridLayoutManager(this,column));
    }

    @Override
    public void onError() {
        Toast.makeText(this,getText(R.string.error_update_word_error),Toast.LENGTH_LONG).show();
    }

    private void requestPermission(){
        if(Build.VERSION.SDK_INT >= 23) {
            int readable = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            int writable = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (readable != PackageManager.PERMISSION_GRANTED || writable != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if(requestCode == REQUEST_PERMISSION){
            boolean per = true;
            for(int i = 0;i < grantResults.length;i++){
                if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                    per = false;
                    break;
                }
            }
            if(!per){
                Toast.makeText(this,getText(R.string.error_permission),Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void showProgress() {
        mWordList.setVisibility(View.INVISIBLE);
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mWordList.setVisibility(View.VISIBLE);
        mProgress.setVisibility(View.INVISIBLE);
    }
}
