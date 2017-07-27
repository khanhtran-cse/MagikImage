package com.qkt.app.magikimage.constant;

import com.qkt.app.magikimage.model.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qkt on 26/07/2017.
 */

public class WordList {
    private static WordList instances = null;
    private List<Word> words;
    private WordList(){
        words = new ArrayList<>();
    }

    public static WordList getIns(){
        if(instances == null){
            instances = new WordList();
        }
        return instances;
    }

    public List<Word> getList(){
        return words;
    }
}
