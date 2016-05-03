package com.udacity.gradle.builditbigger;

import android.content.Context;

import com.github.roshan.myapplication.backendjokes.myApi.model.MyBean;
import com.udacity.gradle.builditbigger.service.JokesService;
import com.udacity.gradle.builditbigger.ui.base.BasePresenter;
import com.udacity.gradle.builditbigger.util.NetworkUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainPresenter extends BasePresenter<MainView> {

    private final Context mContext;
    private Call<MyBean> mCall;

    public MainPresenter(Context context) {
        mContext = context;
    }

    @Override
    public void attachView(MainView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void loadJoke() {
        checkViewAttached();
        if (NetworkUtil.isNetworkConnected(mContext)) {
            JokesService jokesService = JokesService.Factory.newJokesService();
            mCall = jokesService.getJokes();
            mCall.enqueue(new Callback<MyBean>() {
                @Override
                public void onResponse(Response<MyBean> response) {
                    getMvpView().displayJokes(response.body().getData());
                }

                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                    getMvpView().showError("An error occurred. Please, try again.");
                }
            });
        }
    }

    public void cancelLoad() {
        if (mCall != null) {
            mCall.cancel();
        }
    }
}
