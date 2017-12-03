package net.wemakesites.em.bandschallenge.injection.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import net.wemakesites.em.bandschallenge.injection.ActivityContext;

import dagger.Module;
import dagger.Provides;



@Module
public class FragmentModule {
    private final Fragment fragment;

    public FragmentModule(final Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    Fragment providesFragment() {
        return fragment;
    }

    @Provides
    Activity provideActivity() {
        return fragment.getActivity();
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return fragment.getActivity();
    }
}
