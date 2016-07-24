package com.sharathp.flickster.views;

import android.databinding.BindingAdapter;
import android.widget.TextView;

import com.sharathp.flickster.models.Movie;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BindingUtil {
    private static final String DATE_FORMAT_RELEASE_DATE = "MMMM d, yyyy";
    private static final String FORMAT_POPULARITY = "#.00";

    @BindingAdapter({"bind:movieReleaseDate"})
    public static void formatMovieReleaseDate(final TextView tv, final String releaseDate) {
        final Date date = Movie.parseReleaseDate(releaseDate);
        final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_RELEASE_DATE);
        final String formattedDate = sdf.format(date);
        tv.setText(formattedDate);
    }

    @BindingAdapter({"bind:moviePopularity"})
    public static void formatMoviePopularity(final TextView tv, final float popularity) {
        final DecimalFormat decimalFormat = new DecimalFormat(FORMAT_POPULARITY);
        final String formattedPopularity = decimalFormat.format(popularity);
        tv.setText(formattedPopularity);
    }
}