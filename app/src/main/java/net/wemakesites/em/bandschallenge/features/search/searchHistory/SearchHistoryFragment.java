package net.wemakesites.em.bandschallenge.features.search.searchHistory;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.wemakesites.em.bandschallenge.R;
import net.wemakesites.em.bandschallenge.features.base.BaseFragment;
import net.wemakesites.em.bandschallenge.injection.component.FragmentComponent;

import javax.inject.Inject;

import butterknife.BindView;

public class SearchHistoryFragment extends BaseFragment implements SearchHistoryView {


    @BindView(R.id.searchHistoryRecyclerView)
    RecyclerView searchHistoryRecyclerView;


    @Inject
    SearchHistoryPresenter presenter;

    @Inject
    SearchHistoryItemsAdapter adapter;

    public SearchHistoryFragment() {
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_search_history;
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
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
        presenter.addLocalSearchHistoryChangesObserver();
        presenter.addRecyclerViewOnItemClickObserver();
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
    public void showDetails(long bandId) {
        //TODO start activity details
    }
}
