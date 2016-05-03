package com.udacity.gradle.builditbigger;

import com.udacity.gradle.builditbigger.ui.base.MvpView;


public interface MainView extends MvpView {

    void displayJokes(String joke);
    void showError(String message);
}
