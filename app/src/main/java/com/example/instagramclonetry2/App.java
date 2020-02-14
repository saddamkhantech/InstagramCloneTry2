package com.example.instagramclonetry2;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("CXZnL9yu6QagwajwG7HQJVDx7bakld1wBGN6sxA9")
                // if defined
                .clientKey("ZQRg4wl4x8ROs2R26rKGGf1MiO7GjEmqVuBL4mKk")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
