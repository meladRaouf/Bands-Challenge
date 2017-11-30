package net.wemakesites.em.bandschallenge.utils;

import net.wemakesites.em.bandschallenge.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;



public class ApiKeyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder().addQueryParameter("api_key", BuildConfig.METAL_BANDS_API_KEY).build();
        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}
