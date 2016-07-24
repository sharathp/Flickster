package com.sharathp.flickster.views;

import android.content.Context;
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
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.AbstractMovieViewHolder> {
    private static final int TYPE_REGULAR_MOVIE = 0;
    private static final int TYPE_POPULAR_MOVIE = 1;

    private static final float RATING_MOVIE_POPULAR = 5.0f;

    private List<Movie> mMovies;
    private final MovieItemCallback mMovieItemCallback;

    public MovieListAdapter(final List<Movie> movies, final MovieItemCallback movieItemCallback) {
        mMovies = movies;
        mMovieItemCallback = movieItemCallback;
    }

    @Override
    public int getItemViewType(final int position) {
        final Movie movie = mMovies.get(position);
        if (isMoviePopular(movie)) {
            return TYPE_POPULAR_MOVIE;
        } else {
            return TYPE_REGULAR_MOVIE;
        }
    }

    @Override
    public AbstractMovieViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final Context context = parent.getContext();

        final LayoutInflater inflater = LayoutInflater.from(context);

        switch (viewType) {
            case TYPE_POPULAR_MOVIE: {
                final View movieView = inflater.inflate(R.layout.item_movie_popular, parent, false);
                return new PopularMovieViewHolder(movieView, mMovieItemCallback);
            }
            case TYPE_REGULAR_MOVIE: {
                final View movieView = inflater.inflate(R.layout.item_movie, parent, false);
                return new RegularMovieViewHolder(movieView, mMovieItemCallback);
            }
            default: {
                throw new IllegalArgumentException("Invalid viewType: " + viewType);
            }
        }
    }

    @Override
    public void onBindViewHolder(final AbstractMovieViewHolder holder, final int position) {
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

    private boolean isMoviePopular(final Movie movie) {
        int compare = Float.compare(movie.getVoteAverage(), RATING_MOVIE_POPULAR);
        return (compare >= 0);
    }

    public static abstract class AbstractMovieViewHolder extends RecyclerView.ViewHolder {
        protected final MovieItemCallback mMovieItemCallback;
        protected Movie mMovie;

        public AbstractMovieViewHolder(final View itemView, final MovieItemCallback movieItemCallback) {
            super(itemView);
            mMovieItemCallback = movieItemCallback;
        }

        public final void bind(final Movie movie) {
            mMovie = movie;
            doBind();
        }

        protected abstract void doBind();
    }

    public static class PopularMovieViewHolder extends AbstractMovieViewHolder {

        @BindView(R.id.iv_movie_backdrop)
        ImageView mBackDropImageView;

        @BindView(R.id.iv_movie_play)
        ImageView mPlayImageView;

        @Nullable
        @BindView(R.id.tv_movie_title)
        TextView mTitleTextView;

        @Nullable
        @BindView(R.id.tv_movie_desc)
        TextView mDescriptionTextView;

        private View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                mMovieItemCallback.onPopularMovieSelected(mMovie);
            }
        };

        public PopularMovieViewHolder(final View itemView, final MovieItemCallback movieItemCallback) {
            super(itemView, movieItemCallback);
            itemView.setOnClickListener(mOnClickListener);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void doBind() {
            if (mTitleTextView != null) {
                mTitleTextView.setText(mMovie.getTitle());
            }

            if (mDescriptionTextView != null) {
                mDescriptionTextView.setText(mMovie.getOverview());
            }

            Picasso.with(itemView.getContext())
                    .load(Constants.getBackdropImageUrl(mMovie.getBackdropPath()))
                    .fit()
                    .centerInside()
                    .placeholder(R.drawable.placeholder_land)
                    .error(R.drawable.error_placeholder_land)
                    .transform(new RoundedCornersTransformation(Constants.ROUND_TRANSFORMATION_RADIUS, Constants.ROUND_TRANSFORMATION_MARGIN))
                    .into(mBackDropImageView);
        }
    }

    public static class RegularMovieViewHolder extends AbstractMovieViewHolder {

        @BindView(R.id.tv_movie_title)
        TextView mTitleTextView;

        @BindView(R.id.tv_movie_desc)
        TextView mDescriptionTextView;

        @BindView(R.id.iv_movie_poster)
        ImageView mPosterImageView;

        private View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                mMovieItemCallback.onMovieSelected(mMovie);
            }
        };

        public RegularMovieViewHolder(final View itemView, final MovieItemCallback movieItemCallback) {
            super(itemView, movieItemCallback);
            itemView.setOnClickListener(mOnClickListener);
            ButterKnife.bind(this, itemView);
        }

        public void doBind() {
            mTitleTextView.setText(mMovie.getTitle());
            mDescriptionTextView.setText(mMovie.getOverview());
            Picasso.with(itemView.getContext())
                    .load(Constants.getPosterImageUrl(mMovie.getPosterPath()))
                    .fit()
                    .centerInside()
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error_placeholder)
                    .transform(new RoundedCornersTransformation(Constants.ROUND_TRANSFORMATION_RADIUS, Constants.ROUND_TRANSFORMATION_MARGIN))
                    .into(mPosterImageView);
        }
    }

    /**
     * Interface to be implemented to be notified about actions on movie items.
     */
    public interface MovieItemCallback {

        /**
         * Callback invoked when a movie is tapped/selected.
         *
         * @param movie
         */
        void onMovieSelected(Movie movie);

        /**
         * Callback invoked when a popular movie is tapped/selected.
         *
         * @param movie
         */
        void onPopularMovieSelected(Movie movie);
    }
}