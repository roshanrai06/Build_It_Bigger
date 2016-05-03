package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.github.roshan.myapplication.backendjokes.myApi.model.MyBean;
import com.udacity.gradle.builditbigger.service.JokesService;

import java.util.concurrent.CountDownLatch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testAsyncTask() throws InterruptedException {

        final CountDownLatch latch = new CountDownLatch(1);
        JokesService jokesService = JokesService.Factory.newJokesService();
        Call<MyBean> call = jokesService.getJokes();
        call.enqueue(new Callback<MyBean>() {
            @Override
            public void onResponse(Response<MyBean> response) {
                assertNotNull(response.body().getData());
                latch.countDown();
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        latch.await();
    }

}