package net.wemakesites.em.bandschallenge.injection.module;

import net.wemakesites.em.bandschallenge.data.remote.BandsService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module(includes = {NetworkModule.class})
public class ApiModule {

    @Provides
    @Singleton
    BandsService provideBandsService(final Retrofit retrofit) {
        return retrofit.create(BandsService.class);
    }
}
