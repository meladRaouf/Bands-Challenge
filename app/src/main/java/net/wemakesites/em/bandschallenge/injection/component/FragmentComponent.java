package net.wemakesites.em.bandschallenge.injection.component;

import net.wemakesites.em.bandschallenge.features.search.searchHistory.SearchHistoryFragment;
import net.wemakesites.em.bandschallenge.injection.PerFragment;
import net.wemakesites.em.bandschallenge.injection.module.FragmentModule;

import dagger.Subcomponent;

/**
 * This component inject dependencies to all Fragments across the application
 */
@PerFragment

@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(SearchHistoryFragment searchHistoryFragment);

}
