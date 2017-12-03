package net.wemakesites.em.bandschallenge.injection.module;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.wemakesites.em.bandschallenge.BuildConfig;
import net.wemakesites.em.bandschallenge.utils.ApiKeyInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class NetworkModule {


    private String getBaseUrl() {
        return BuildConfig.METAL_BANDS_API_URL;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(final OkHttpClient okHttpClient, final Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(final ApiKeyInterceptor apiKeyInterceptor) {
        final OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.addInterceptor(apiKeyInterceptor);
        return httpClientBuilder.build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().create();
    }


    @Provides
    @Singleton
    ApiKeyInterceptor provideApiKeyInterceptor() {
        return new ApiKeyInterceptor();
    }
}
