package com.qkt.app.magikimage.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by qkt on 24/07/2017.
 */

public class Word {
    public static final String STATE_NO_DOWNLOAD = "no_download";
    public static final String STATE_DOWNLOADING = "downloading";
    public static final String STATE_DOWNLOADED = "downloaded";
    public static final String STATE_DOWNLOAD_ERROR = "error";

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("set")
    @Expose
    private String set;
    @SerializedName("word")
    @Expose
    private String word;
    @SerializedName("audio")
    @Expose
    private String audio;
    @SerializedName("transliteration")
    @Expose
    private String transliteration;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("mean_ru")
    @Expose
    private String meanRu;
    @SerializedName("mean_zh")
    @Expose
    private String meanZh;
    @SerializedName("mean_ja")
    @Expose
    private String meanJa;
    @SerializedName("mean_de")
    @Expose
    private String meanDe;
    @SerializedName("mean_it")
    @Expose
    private String meanIt;
    @SerializedName("mean_ko")
    @Expose
    private String meanKo;
    @SerializedName("mean_fr")
    @Expose
    private String meanFr;
    @SerializedName("mean_es")
    @Expose
    private String meanEs;
    @SerializedName("mean_ar")
    @Expose
    private String meanAr;
    @SerializedName("mean_tr")
    @Expose
    private String meanTr;
    @SerializedName("mean_pt")
    @Expose
    private String meanPt;
    @SerializedName("mean_vi")
    @Expose
    private String meanVi;
    @SerializedName("mean_en")
    @Expose
    private String meanEn;
    @SerializedName("example")
    @Expose
    private String example;
    @SerializedName("version")
    @Expose
    private String version;
    private String state;
    public Word(){
        this.state = STATE_NO_DOWNLOAD;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getTransliteration() {
        return transliteration;
    }

    public void setTransliteration(String transliteration) {
        this.transliteration = transliteration;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMeanRu() {
        return meanRu;
    }

    public void setMeanRu(String meanRu) {
        this.meanRu = meanRu;
    }

    public String getMeanZh() {
        return meanZh;
    }

    public void setMeanZh(String meanZh) {
        this.meanZh = meanZh;
    }

    public String getMeanJa() {
        return meanJa;
    }

    public void setMeanJa(String meanJa) {
        this.meanJa = meanJa;
    }

    public String getMeanDe() {
        return meanDe;
    }

    public void setMeanDe(String meanDe) {
        this.meanDe = meanDe;
    }

    public String getMeanIt() {
        return meanIt;
    }

    public void setMeanIt(String meanIt) {
        this.meanIt = meanIt;
    }

    public String getMeanKo() {
        return meanKo;
    }

    public void setMeanKo(String meanKo) {
        this.meanKo = meanKo;
    }

    public String getMeanFr() {
        return meanFr;
    }

    public void setMeanFr(String meanFr) {
        this.meanFr = meanFr;
    }

    public String getMeanEs() {
        return meanEs;
    }

    public void setMeanEs(String meanEs) {
        this.meanEs = meanEs;
    }

    public String getMeanAr() {
        return meanAr;
    }

    public void setMeanAr(String meanAr) {
        this.meanAr = meanAr;
    }

    public String getMeanTr() {
        return meanTr;
    }

    public void setMeanTr(String meanTr) {
        this.meanTr = meanTr;
    }

    public String getMeanPt() {
        return meanPt;
    }

    public void setMeanPt(String meanPt) {
        this.meanPt = meanPt;
    }

    public String getMeanVi() {
        return meanVi;
    }

    public void setMeanVi(String meanVi) {
        this.meanVi = meanVi;
    }

    public String getMeanEn() {
        return meanEn;
    }

    public void setMeanEn(String meanEn) {
        this.meanEn = meanEn;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setState(String state){
        if(state.equals(STATE_DOWNLOADING)){
            this.state = STATE_DOWNLOADING;
        } else if(state.equals(STATE_DOWNLOADED)){
            this.state = STATE_DOWNLOADED;
        } else if(state.equals(STATE_NO_DOWNLOAD)){
            this.state = STATE_NO_DOWNLOAD;
        }
        else{
            this.state = STATE_DOWNLOAD_ERROR;
        }
    }

    public String getState(){
        return this.state;
    }
}
