package com.andlab.tugasandroid.Result;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.xml.transform.Result;

/**
 * Created by greatsoft on 09/11/18.
 */

public class Value {
    @SerializedName("value")
    String value;

    @SerializedName("message")
    String message;

    @SerializedName("result")
    List<Result> result;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }
}
