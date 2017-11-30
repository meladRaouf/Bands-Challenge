package net.wemakesites.em.bandschallenge.injection.component;

import net.wemakesites.em.bandschallenge.injection.ConfigPersistent;
import net.wemakesites.em.bandschallenge.injection.module.ActivityModule;
import net.wemakesites.em.bandschallenge.injection.module.FragmentModule;

import dagger.Component;

@ConfigPersistent
@Component(dependencies = AppComponent.class)
public interface ConfigPersistentComponent {
    ActivityComponent activityComponent(ActivityModule activityModule);
    FragmentComponent fragmentComponent(FragmentModule fragmentModule);


}
