package net.wemakesites.em.bandschallenge.features.search.searchhistory;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.wemakesites.em.bandschallenge.R;
import net.wemakesites.em.bandschallenge.data.model.local.SearchHistoryItem;
import net.wemakesites.em.bandschallenge.features.base.AbstractBaseFragment;
import net.wemakesites.em.bandschallenge.features.details.DetailsActivity;
import net.wemakesites.em.bandschallenge.injection.component.FragmentComponent;

import javax.inject.Inject;

import butterknife.BindView;

public class SearchHistoryFragment extends AbstractBaseFragment implements SearchHistoryView {


    @BindView(R.id.searchHistoryRecyclerView)
    RecyclerView searchHistoryRecyclerView;


    @Inject
    SearchHistoryPresenter presenter;

    @Inject
    SearchHistoryItemsAdapter adapter;


    @Override
    protected int getLayout() {
        return R.layout.fragment_search_history;
    }

    @Override
    protected void inject(final FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void attachView() {
        presenter.attachView(this);
    }

    @Override
    protected void initViews() {
        searchHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        searchHistoryRecyclerView.setAdapter(adapter);
        presenter.addHistoryChangeSubscriber();
        presenter.addRecyclerViewOnItemClickSubscriber();
    }

    @Override
    protected void detachPresenter() {
        presenter.detachView();

    }


    @Override
    public SearchHistoryItemsAdapter getAdapter() {
        return adapter;
    }

    @Override
    public void showDetails(final SearchHistoryItem searchHistoryItem) {
        final Intent intent = DetailsActivity.getStartIntent(getContext(),
                searchHistoryItem.getId(),
                searchHistoryItem.getName());
        startActivity(intent);
    }


}
