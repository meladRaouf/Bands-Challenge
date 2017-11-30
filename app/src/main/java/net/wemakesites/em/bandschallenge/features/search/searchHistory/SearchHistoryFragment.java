package net.wemakesites.em.bandschallenge.features.search.searchHistory;



import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import net.wemakesites.em.bandschallenge.R;
import net.wemakesites.em.bandschallenge.features.base.BaseFragment;
import net.wemakesites.em.bandschallenge.features.common.ErrorView;
import net.wemakesites.em.bandschallenge.injection.component.FragmentComponent;
import net.wemakesites.em.bandschallenge.utils.UiUtils;

import butterknife.BindView;

public class SearchHistoryFragment extends BaseFragment implements SearchHistoryView {

//    @BindView(R.id.errorView)
//    ErrorView errorView;

    @BindView(R.id.searchHistoryRecyclerView)
    RecyclerView searchHistoryRecyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;



    public SearchHistoryFragment() {
        // Required empty public constructor
    }




    @Override
    protected int getLayout() {
        return R.layout.fragment_search_history;
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {

    }

    @Override
    protected void attachView() {

    }

    @Override
    protected void detachPresenter() {

    }

    @Override
    public void showProgressBar() {
        UiUtils.showView(progressBar);
    }

    @Override
    public void hideProgressBar() {
        UiUtils.hideView(progressBar);
    }

    @Override
    public void showErrorView() {
//        UiUtils.showView(errorView);
    }

    @Override
    public void hideErrorView() {
//        UiUtils.hideView(errorView);
    }

}
