package net.wemakesites.em.bandschallenge;

import android.app.Application;
import android.content.Context;

import net.wemakesites.em.bandschallenge.injection.component.AppComponent;
import net.wemakesites.em.bandschallenge.injection.component.DaggerAppComponent;
import net.wemakesites.em.bandschallenge.injection.module.AppModule;
import net.wemakesites.em.bandschallenge.injection.module.NetworkModule;

import timber.log.Timber;


public class BandsChallengeApplication extends Application {
    private AppComponent appComponent;


    public static BandsChallengeApplication get(Context context) {
        return (BandsChallengeApplication) context.getApplicationContext();
    }

    public AppComponent getComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .networkModule(new NetworkModule())
                    .appModule(new AppModule(this))
                    .build();
        }
        return appComponent;
    }


    public void setComponent(AppComponent appComponent) {
        this.appComponent = appComponent;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
