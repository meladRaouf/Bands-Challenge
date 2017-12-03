package net.wemakesites.em.bandschallenge.features.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.LongSparseArray;
import android.view.MenuItem;

import net.wemakesites.em.bandschallenge.BandsChallengeApplication;
import net.wemakesites.em.bandschallenge.injection.component.ActivityComponent;
import net.wemakesites.em.bandschallenge.injection.component.ConfigPersistentComponent;
import net.wemakesites.em.bandschallenge.injection.module.ActivityModule;

import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Nullable;

import butterknife.ButterKnife;

import static android.R.id;
import static net.wemakesites.em.bandschallenge.injection.component.DaggerConfigPersistentComponent.builder;

public abstract class AbstractBaseActivity extends AppCompatActivity {

    private static final String KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID";
    private static final AtomicLong NEXT_ID = new AtomicLong(0);
    private static final LongSparseArray<ConfigPersistentComponent> COMPONENTS_ARRAY =
            new LongSparseArray<>();

    private long activityId;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);

        // Create the ActivityComponent and reuses cached
        // ConfigPersistentComponent if this is being called after a configuration change.
        activityId = savedInstanceState == null ? NEXT_ID.getAndIncrement() : savedInstanceState.getLong(KEY_ACTIVITY_ID);
        ConfigPersistentComponent configPersistentComponent;
        if (COMPONENTS_ARRAY.get(activityId) == null) {

            configPersistentComponent =
                    builder()
                            .appComponent(BandsChallengeApplication.get(this).getComponent())
                            .build();
            COMPONENTS_ARRAY.put(activityId, configPersistentComponent);
        } else {

            configPersistentComponent = COMPONENTS_ARRAY.get(activityId);
        }
        final ActivityComponent activityComponent =
                configPersistentComponent.activityComponent(new ActivityModule(this));
        inject(activityComponent);
        attachView();
    }

    @Override
    protected void onPostCreate(@Nullable final Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initViews(savedInstanceState);
    }

    protected abstract int getLayout();

    protected abstract void inject(ActivityComponent activityComponent);

    protected abstract void attachView();

    protected abstract void initViews(final Bundle savedInstanceState);

    protected abstract void detachPresenter();

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        boolean result;
        if (item.getItemId() == id.home) {
            finish();
            result = true;
        } else {
            result = super.onOptionsItemSelected(item);
        }
        return result;

    }

    @Override
    protected void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putLong(KEY_ACTIVITY_ID, activityId);
    }

    @Override
    protected void onDestroy() {
        if (!isChangingConfigurations()) {
            COMPONENTS_ARRAY.remove(activityId);
        }
        detachPresenter();
        super.onDestroy();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
