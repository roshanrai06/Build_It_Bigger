/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.github.roshan.myapplication.backendjokes;

import com.github.roshan.JokesGenerator;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;


/** An endpoint class we are exposing */
@Api(
  name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "backendjokes.myapplication.roshan.github.com",
    ownerName = "backendjokes.myapplication.roshan.github.com",
    packagePath=""
  )
)
public class MyEndpoint {

    /** A simple endpoint method that return a joke from JokesGenerator */
    @ApiMethod(name = "getJokes")
    public MyBean getJokes() {
        MyBean response = new MyBean();
        response.setData(JokesGenerator.getJokes());

        return response;
    }
}
