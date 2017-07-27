package com.qkt.app.magikimage.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.os.EnvironmentCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qkt.app.magikimage.R;
import com.qkt.app.magikimage.activity.homeActivity.HomePresenter;
import com.qkt.app.magikimage.constant.Constant;
import com.qkt.app.magikimage.constant.WordList;
import com.qkt.app.magikimage.custom.CustomImageView;
import com.qkt.app.magikimage.model.Word;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

/**
 * Created by qkt on 25/07/2017.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder>{
    private static final String TAG = "ImageAdapter";
    private Context mContext;
    private List<Word> mWords;
    private LayoutInflater inflater;
    private HomePresenter mPresenter;

    public ImageAdapter(Context context, HomePresenter presenter){
        mContext = context;
        mWords = WordList.getIns().getList();
        inflater = LayoutInflater.from(mContext);
        mPresenter = presenter;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Word word = mWords.get(position);
        holder.text.setText(word.getWord());

        if(word.getState().equals(Word.STATE_DOWNLOADED)){
            String path = Constant.BASE_PATH + "/Images/" + word.getSet() + "/" + word.getWord() + ".jpg";
            try{
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                if(bitmap != null){
                    Log.i(TAG,"Load image " + path);
                } else {
                    Log.i(TAG,"Load image from storage failed. This image will be re-download from internet.");
                    mPresenter.downloadImage(position);
                }
                holder.image.setImageBitmap(bitmap);
            } catch (Exception ex){
                holder.image.setImageBitmap(null);
                Log.i(TAG,"Load image from storage failed. This image will be re-download from internet.");
                ex.printStackTrace();
                mPresenter.downloadImage(position);
            }
        } else {
            holder.image.setImageBitmap(null);
            mPresenter.downloadImage(position);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_word,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mWords.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener{
        public CustomImageView image;
        public TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_image);
            text = itemView.findViewById(R.id.tv_word);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(mContext,"Clicked " + mWords.get(getAdapterPosition()).getState(),Toast.LENGTH_SHORT).show();
        }
    }
}
