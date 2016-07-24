package com.sharathp.flickster.util;

import com.sharathp.flickster.models.Video;

import java.util.List;

public class MovieUtil {

    public static String getVideoKeyToPlay(final List<Video> videos) {
        // get the first video
        return videos.get(0).getSource();
    }
}
