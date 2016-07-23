package com.sharathp.flickster.views;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.IntDef;
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
    private Context mContext;

    public MovieListAdapter(final List<Movie> movies, final Context context) {
        mMovies = movies;
        mContext = context;
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
                final View movieView = inflater.inflate(R.layout.item_movie, parent, false);
                return new RegularMovieViewHolder(movieView);
            }
            case TYPE_REGULAR_MOVIE: {
                final View movieView = inflater.inflate(R.layout.item_movie_popular, parent, false);
                return new PopularMovieViewHolder(movieView);
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

        public AbstractMovieViewHolder(final View itemView) {
            super(itemView);
        }

        protected abstract void bind(final Movie movie);
    }

    public static class PopularMovieViewHolder extends AbstractMovieViewHolder {

        @BindView(R.id.iv_movie_backdrop)
        ImageView mBackDropImageView;

        @BindView(R.id.iv_movie_play)
        ImageView mPlayImageView;

        public PopularMovieViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bind(final Movie movie) {
            Picasso.with(itemView.getContext())
                    .load(Constants.getBackdropImageUrl(movie.getBackdropPath()))
                    .fit()
                    .centerInside()
                    .placeholder(R.drawable.placeholder_land)
                    .error(R.drawable.error_placeholder_land)
                    .transform(new RoundedCornersTransformation(Constants.ROUND_TRANSFORMATION_RADIUS, Constants.ROUND_TRANSFORMATION_MARGIN))
                    .into(mBackDropImageView,
                            new com.squareup.picasso.Callback() {
                                @Override
                                public void onSuccess() {
                                    mPlayImageView.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onError() {
                                    mPlayImageView.setVisibility(View.GONE);
                                }
                            }
                    );
        }
    }

    public static class RegularMovieViewHolder extends AbstractMovieViewHolder {

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

        public RegularMovieViewHolder(final View itemView) {
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
                    .transform(new RoundedCornersTransformation(Constants.ROUND_TRANSFORMATION_RADIUS, Constants.ROUND_TRANSFORMATION_MARGIN))
                    .into(imageView);
        }
    }


    @IntDef({TYPE_REGULAR_MOVIE, TYPE_POPULAR_MOVIE})
    public @interface ViewType {
        // no-op
    }
}