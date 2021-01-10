package com.example.androidpersonalbudget.asyncTask;

public interface Callback<R> {

    void runResultOnUiThread(R result);
}
