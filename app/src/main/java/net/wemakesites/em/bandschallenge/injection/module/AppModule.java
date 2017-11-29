package net.wemakesites.em.bandschallenge.injection.module;

import android.app.Application;
import android.content.Context;

import net.wemakesites.em.bandschallenge.injection.ApplicationContext;

import dagger.Module;
import dagger.Provides;


@Module(includes = {ApiModule.class})
public class AppModule {
    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }


}
