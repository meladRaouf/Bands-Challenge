package net.wemakesites.em.bandschallenge.features.details;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.wemakesites.em.bandschallenge.R;
import net.wemakesites.em.bandschallenge.data.model.response.banddetails.Discography;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder> {

    private List<Discography> items;

    @Inject
    AlbumsAdapter() {
        super();
        items = Collections.emptyList();
    }

     void setAlbums(final List<Discography> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public AlbumsViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_album, parent, false);
        return new AlbumsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AlbumsViewHolder holder, final int position) {
        holder.onBind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

     class AlbumsViewHolder extends ViewHolder {
        @BindView(R.id.albumTitle)
        TextView albumTitleTextView;

        @BindView(R.id.albumYear)
        TextView albumYearTextView;

         AlbumsViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind(final Discography album) {
            albumTitleTextView.setText(album.getTitle());
            albumYearTextView.setText(album.getYear());
        }
    }
}
