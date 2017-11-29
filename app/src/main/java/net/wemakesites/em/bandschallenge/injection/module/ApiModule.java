package net.wemakesites.em.bandschallenge.injection.module;

import net.wemakesites.em.bandschallenge.data.remote.BandsService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by shivam on 29/5/17.
 */
@Module(includes = {NetworkModule.class})
public class ApiModule {

    @Provides
    @Singleton
    BandsService providePokemonApi(Retrofit retrofit) {
        return retrofit.create(BandsService.class);
    }
}
