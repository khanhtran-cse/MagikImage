package com.qkt.app.magikimage.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by qkt on 24/07/2017.
 */

public class MagikResponse {
    @SerializedName("error_code")
    @Expose
    private Integer errorCode;
    @SerializedName("message")
    @Expose
    private Integer message;
    @SerializedName("data")
    @Expose
    private List<Word> data = null;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Integer getMessage() {
        return message;
    }

    public void setMessage(Integer message) {
        this.message = message;
    }

    public List<Word> getData() {
        return data;
    }

    public void setData(List<Word> data) {
        this.data = data;
    }
}
