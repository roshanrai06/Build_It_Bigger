package com.udacity.gradle.builditbigger.ui.base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.github.roshan.mylibrary.JokesActivity;
import com.udacity.gradle.builditbigger.MainPresenter;
import com.udacity.gradle.builditbigger.MainView;
import com.udacity.gradle.builditbigger.R;


public abstract class BaseActivity extends AppCompatActivity implements MainView {

    private ProgressDialog mProgressDialog;
    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainPresenter = new MainPresenter(this);
        mMainPresenter.attachView(this);
        if (savedInstanceState != null) {
            if (savedInstanceState.getBoolean("isShowing")) {
                showProgressDialog();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("isShowing", mProgressDialog != null && mProgressDialog.isShowing());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        mMainPresenter.detachView();
        super.onDestroy();
    }

    private void showProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Fetching delicious joke...");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                mMainPresenter.cancelLoad();
            }
        });
        mProgressDialog.show();
    }

    protected void giveMeAJoke() {
        showProgressDialog();
        mMainPresenter.loadJoke();
    }

    @Override
    public void displayJokes(String joke) {
        if (mProgressDialog != null)
            mProgressDialog.dismiss();
        Intent intent = new Intent(getApplicationContext(), JokesActivity.class);
        intent.putExtra(JokesActivity.ARG_JOKES, joke);
        startActivity(intent);
    }

    @Override
    public void showError(String message) {
        if (mProgressDialog != null)
            mProgressDialog.dismiss();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
