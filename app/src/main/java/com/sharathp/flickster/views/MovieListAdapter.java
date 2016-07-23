package com.sharathp.flickster.views;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
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
        if (movies != null) {
            mMovies = movies;
        } else {
            mMovies.clear();
        }

        notifyDataSetChanged();
    }

    public void addMovies(final List<Movie> movies) {
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_movie_title)
        TextView mTitleTextView;

        @BindView(R.id.tv_movie_desc)
        TextView mDescriptionTextView;

        @Nullable
        @BindView(R.id.iv_movie_backdrop)
        ImageView mBackDropImageView;

        @Nullable
        @BindView(R.id.iv_movie_poster)
        ImageView mPosterImageView;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final Movie movie) {
            mTitleTextView.setText(movie.getTitle());
            mDescriptionTextView.setText(movie.getOverview());
            loadImage(movie);
        }

        private void loadImage(final Movie movie) {
            final int orientation = itemView.getContext().getResources().getConfiguration().orientation;

            int placeHolderImageRes;
            int errorPlaceHolderImageRes;
            ImageView imageView;
            String imagePath;

            if (Configuration.ORIENTATION_LANDSCAPE == orientation) {
                placeHolderImageRes = R.drawable.placeholder_land;
                errorPlaceHolderImageRes = R.drawable.error_placeholder_land;
                imageView = mBackDropImageView;
                imagePath = Constants.getBackdropImageUrl(movie.getBackdropPath());
            } else {
                placeHolderImageRes = R.drawable.placeholder;
                errorPlaceHolderImageRes = R.drawable.error_placeholder;
                imageView = mPosterImageView;
                imagePath = Constants.getPosterImageUrl(movie.getPosterPath());
            }

            Picasso.with(itemView.getContext())
                    .load(imagePath)
                    .fit()
                    .centerInside()
                    .placeholder(placeHolderImageRes)
                    .error(errorPlaceHolderImageRes)
                    .into(imageView);
        }
    }
}
