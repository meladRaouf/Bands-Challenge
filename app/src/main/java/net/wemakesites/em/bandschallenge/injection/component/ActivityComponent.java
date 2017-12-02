package net.wemakesites.em.bandschallenge.injection.component;

import net.wemakesites.em.bandschallenge.features.search.SearchBandsByNameActivity;
import net.wemakesites.em.bandschallenge.injection.PerActivity;
import net.wemakesites.em.bandschallenge.injection.module.ActivityModule;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(SearchBandsByNameActivity searchBandsByNameActivity);
}
