package net.wemakesites.em.bandschallenge.features.search.searchhistory;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;

import net.wemakesites.em.bandschallenge.data.model.local.SearchHistoryItem;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

class SearchHistoryItemsAdapter extends RecyclerView.Adapter<SearchHistoryItemsAdapter.SearchHistoryItemViewHolder>
        implements Consumer<List<SearchHistoryItem>> {

    final PublishSubject<SearchHistoryItem> clickSubject;
    private List<SearchHistoryItem> items;

    @Inject
    SearchHistoryItemsAdapter() {
        super();
        items = Collections.emptyList();
        clickSubject = PublishSubject.create();
    }

    @Override
    public void accept(final List<SearchHistoryItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public long getItemId(final int position) {
        return items.get(position).getId();
    }


    Observable<SearchHistoryItem> getItemClickedObservable() {
        return clickSubject;
    }

    @Override
    public void onDetachedFromRecyclerView(final RecyclerView recyclerView) {
        clickSubject.onComplete();
    }

    @Override
    public SearchHistoryItemViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new SearchHistoryItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SearchHistoryItemViewHolder holder, final int position) {
        holder.onBind(items.get(position));
    }

    class SearchHistoryItemViewHolder extends RecyclerView.ViewHolder {
        final TextView textView;

        SearchHistoryItemViewHolder(final View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }

        void onBind(final SearchHistoryItem searchHistoryItem) {
            textView.setText(searchHistoryItem.getName());
            RxView.clicks(itemView)
                    .map(__ -> searchHistoryItem)
                    .subscribe(clickSubject);

        }
    }
}
