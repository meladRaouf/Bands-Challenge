package net.wemakesites.em.bandschallenge.features.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import net.wemakesites.em.bandschallenge.R;
import net.wemakesites.em.bandschallenge.data.model.response.banddetails.BandData;
import net.wemakesites.em.bandschallenge.features.base.AbstractBaseActivity;
import net.wemakesites.em.bandschallenge.injection.component.ActivityComponent;
import net.wemakesites.em.bandschallenge.injection.module.GlideApp;
import net.wemakesites.em.bandschallenge.utils.UiUtils;

import javax.inject.Inject;

import butterknife.BindView;

public class DetailsActivity extends AbstractBaseActivity implements DetailsView {

    public static final String EXTRA_BAND_ID = "EXTRA_BAND_ID";
    public static final String EXTRA_BAND_NAME = "EXTRA_BAND_NAME";
    private static final long DEFAULT_BAND_ID = 0;

    @BindView(R.id.bandPhoto)
    ImageView bandPhoto;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.bandName)
    TextView bandNameextView;

    @BindView(R.id.yearsOfActivityTextView)
    TextView yearsOfActivityTextView;

    @BindView(R.id.genreTextView)
    TextView genreTextView;

    @BindView(R.id.countryTextView)
    TextView countryTextView;

    @BindView(R.id.albumsRecyclerView)
    RecyclerView albumsRecyclerView;


    @Inject
    DetailsPresenter presenter;

    @Inject
    AlbumsAdapter adapter;

    public static Intent getStartIntent(final Context context, final long bandId, final String bandName) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(EXTRA_BAND_ID, bandId);
        intent.putExtra(EXTRA_BAND_NAME, bandName);
        return intent;
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_details;
    }

    @Override
    protected void inject(final ActivityComponent activityComponent) {
        activityComponent.inject(this);

    }

    @Override
    protected void attachView() {
        presenter.attachView(this);

    }

    @Override
    protected void initViews(final Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        final Intent intent = getIntent();
        if (!intent.hasExtra(EXTRA_BAND_NAME) || !intent.hasExtra(EXTRA_BAND_ID)) {
            throw new IllegalArgumentException("Details Activity requires a band name and id");
        }
        final String bandName = getIntent().getStringExtra(EXTRA_BAND_NAME);
        final long bandId = getIntent().getLongExtra(EXTRA_BAND_ID, DEFAULT_BAND_ID);
        setTitle(bandName);
        bandNameextView.setText(bandName);

        albumsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        albumsRecyclerView.setAdapter(adapter);

        presenter.loadBandDetails(bandId);

    }

    @Override
    protected void detachPresenter() {
        presenter.detachView();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void showBandData(BandData bandData) {
        //TODO validate banddata
        yearsOfActivityTextView.setText(bandData.getDetails().getYearsActive());
        countryTextView.setText(bandData.getDetails().getCountryOfOrigin());
        genreTextView.setText(bandData.getDetails().getGenre());
        GlideApp.with(this).load(bandData.getPhoto()).into(bandPhoto);
        adapter.setAlbums(bandData.getDiscography());
    }

    @Override
    public void showError(Throwable e) {
        UiUtils.showError(this, R.string.error_message);
        //TODO show reload button

    }
}
