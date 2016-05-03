package com.udacity.gradle.builditbigger.ui.base;


public interface Presenter<T extends MvpView> {
    void attachView(T mvpView);
    void detachView();
}
