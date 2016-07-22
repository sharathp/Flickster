package com.sharathp.flickster.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sharathp.flickster.R;
import com.sharathp.flickster.models.Movie;
import com.sharathp.flickster.util.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private List<Movie> mMovies;
    private Context mContext;

    public MovieListAdapter(final List<Movie> movies, final Context context) {
        mMovies = movies;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final Context context = parent.getContext();
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View movieView = inflater.inflate(R.layout.item_movie, parent, false);
        final ViewHolder viewHolder = new ViewHolder(movieView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Movie movie = mMovies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        if (mMovies == null) {
            return 0;
        }

        return mMovies.size();
    }

    public void setMovies(final List<Movie> movies) {
        mMovies = movies;
        notifyDataSetChanged();
    }

    public void addMovies(final List<Movie> movies) {
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_movie_poster)
        ImageView mPosterImageView;

        @BindView(R.id.tv_movie_title)
        TextView mTitleTextView;

        @BindView(R.id.tv_movie_desc)
        TextView mDescriptionTextView;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final Movie movie) {
            mTitleTextView.setText(movie.getTitle());
            mDescriptionTextView.setText(movie.getOverview());
            Picasso.with(itemView.getContext())
                    .load(Constants.getPosterImageUrl(movie.getPosterPath()))
                    .fit()
                    .centerInside()
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error_placeholder)
                    .into(mPosterImageView);
        }
    }
}
