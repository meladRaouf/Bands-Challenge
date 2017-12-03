package net.wemakesites.em.bandschallenge.injection.component;

import android.app.Application;
import android.content.Context;

import net.wemakesites.em.bandschallenge.data.DataManager;
import net.wemakesites.em.bandschallenge.injection.ApplicationContext;
import net.wemakesites.em.bandschallenge.injection.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    @ApplicationContext
    Context context();

    Application application();

    DataManager apiManager();
}
