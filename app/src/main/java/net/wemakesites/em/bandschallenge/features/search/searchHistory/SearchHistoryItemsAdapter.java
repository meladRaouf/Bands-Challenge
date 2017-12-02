package net.wemakesites.em.bandschallenge.features.search.searchHistory;

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
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

class SearchHistoryItemsAdapter extends RecyclerView.Adapter<SearchHistoryItemsAdapter.SearchHistoryItemViewHolder>
        implements Consumer<List<SearchHistoryItem>> {

    private List<SearchHistoryItem> items;
    private PublishSubject<Long> clickSubject;

    @Inject
    SearchHistoryItemsAdapter() {
        items = Collections.emptyList();
        clickSubject = PublishSubject.create();
    }

    @Override
    public void accept(List<SearchHistoryItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }


    public Observable<Long> getItemClickedObservable() {
        return clickSubject;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        clickSubject.onComplete();
    }

    @Override
    public SearchHistoryItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new SearchHistoryItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchHistoryItemViewHolder holder, int position) {
        holder.onBind(items.get(position));
    }

    public class SearchHistoryItemViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public SearchHistoryItemViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }

        public void onBind(SearchHistoryItem searchHistoryItem) {
            textView.setText(searchHistoryItem.getName());
            RxView.clicks(itemView)
                    .map(__ -> searchHistoryItem.getId())
                    .subscribe(clickSubject);

        }
    }
}
