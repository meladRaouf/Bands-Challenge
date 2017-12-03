package net.wemakesites.em.bandschallenge.features.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.LongSparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.wemakesites.em.bandschallenge.BandsChallengeApplication;
import net.wemakesites.em.bandschallenge.injection.component.ConfigPersistentComponent;
import net.wemakesites.em.bandschallenge.injection.component.FragmentComponent;
import net.wemakesites.em.bandschallenge.injection.module.FragmentModule;

import java.util.concurrent.atomic.AtomicLong;

import butterknife.ButterKnife;

import static net.wemakesites.em.bandschallenge.injection.component.DaggerConfigPersistentComponent.builder;

/**
 * Abstract Fragment that every other Fragment in this application must implement.
 * It handles creation of Dagger components and makes sure that instances of
 * ConfigPersistentComponent are kept across configuration changes.
 */
public abstract class AbstractBaseFragment extends Fragment {

    private static final String KEY_FRAGMENT_ID = "KEY_FRAGMENT_ID";
    private static final LongSparseArray<ConfigPersistentComponent> COMPONENTS_ARRAY =
            new LongSparseArray<>();
    private static final AtomicLong NEXT_ID = new AtomicLong(0);

    private long fragmentId;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create the FragmentComponent and reuses cached ConfigPersistentComponent
        // if this is being called after a configuration change.
        fragmentId = savedInstanceState == null ? NEXT_ID.getAndIncrement() : savedInstanceState.getLong(KEY_FRAGMENT_ID);
        ConfigPersistentComponent configPersistentComponent;
        if (COMPONENTS_ARRAY.get(fragmentId) == null) {
            configPersistentComponent =
                    builder()
                            .appComponent(BandsChallengeApplication.get(getActivity()).getComponent())
                            .build();
            COMPONENTS_ARRAY.put(fragmentId, configPersistentComponent);
        } else {
            configPersistentComponent = COMPONENTS_ARRAY.get(fragmentId);
        }
        final FragmentComponent fragmentComponent =
                configPersistentComponent.fragmentComponent(new FragmentModule(this));
        inject(fragmentComponent);
        attachView();
    }

    @Nullable
    @Override
    public View onCreateView(
            final LayoutInflater inflater,
            @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState) {
        final View view = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    protected abstract void initViews();

    protected abstract int getLayout();

    protected abstract void inject(FragmentComponent fragmentComponent);

    protected abstract void attachView();

    protected abstract void detachPresenter();

    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putLong(KEY_FRAGMENT_ID, fragmentId);
    }

    @Override
    public void onDestroy() {
        if (!getActivity().isChangingConfigurations()) {
            COMPONENTS_ARRAY.remove(fragmentId);
        }
        detachPresenter();
        super.onDestroy();
    }
}
