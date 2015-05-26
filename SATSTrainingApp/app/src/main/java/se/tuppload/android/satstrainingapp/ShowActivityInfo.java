package se.tuppload.android.satstrainingapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import com.google.android.youtube.player.YouTubePlayerView;

import static com.google.android.youtube.player.YouTubePlayer.*;
import static se.tuppload.android.satstrainingapp.R.layout.class_view;

public class ShowActivityInfo extends YouTubeBaseActivity implements OnInitializedListener {
    public YouTubePlayer player;
    public static final String GOOGLE_API_KEY = "AIzaSyDOdUDNDMIYt1Br8g-T4_hzU2YMcNfPQok";

    //http://youtu.be/<VIDEO_ID>
    public static final String VIDEO_ID = "4GBrCy1Uolo";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(class_view);
        Bundle extras = getIntent().getExtras();

        YouTubePlayerView YouTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        YouTubePlayerView.initialize(GOOGLE_API_KEY, this);

        TextView className = (TextView) findViewById(R.id.class_name);
        TextView duration = (TextView) findViewById(R.id.class_duration_time);
        TextView center = (TextView) findViewById(R.id.center_name);
        TextView date = (TextView) findViewById(R.id.date);
        TextView instructor = (TextView) findViewById(R.id.class_view_instructor);
        TextView description = (TextView) findViewById(R.id.class_information);

        ProgressBar cardio = (ProgressBar) findViewById(R.id.fitness_bar);

        className.setText(extras.getString("CLASSTYPE"));
        duration.setText(extras.getString("DURATION"));
        center.setText(extras.getString("CENTER"));
        date.setText(extras.getString("DATE"));
        instructor.setText(extras.getString("INSTRUCTOR"));
        description.setText(extras.getString("DESCRIPTION"));

        cardio.setProgress(extras.getInt("CARDIO"));


    }

//    @Override
//    public void onInitializationFailure(YouTubePlayer.Provider provider,
//                                        YouTubeInitializationResult errorReason) {
//        if (errorReason.isUserRecoverableError()) {
//            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
//        } else {
//            String errorMessage = String.format(
//                    "There was an error initializing the YouTubePlayer",
//                    errorReason.toString());
//            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
//        }
//    }

//    public void onInitializationSuccess(Provider provider, YouTubePlayer player,boolean wasRestored)
//    {
//        if (!wasRestored)
//        {
//            player.loadVideo(VIDEO_ID);
//            player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
//            player.setShowFullscreenButton(false);
//            player.setManageAudioFocus(false);
//            player.setFullscreen(true);
//        }
//    }


//    @Override
//    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
//
//        /** add listeners to YouTubePlayer instance **/
//        player.setPlayerStateChangeListener(playerStateChangeListener);
//        player.setPlaybackEventListener(playbackEventListener);
//
//        player.loadVideo(VIDEO_ID);
//
//
//        /** Start buffering **/
//        if (!wasRestored) {
//            player.cueVideo(VIDEO_ID);
//        }
//    }

    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Failured to Initialize!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInitializationSuccess(Provider provider,
                                        YouTubePlayer player, boolean wasRestored) {
        this.player = player;
        if (!wasRestored) {
            player.cueVideo(VIDEO_ID);
        }
    }

    @Override //reconfigure display properties on screen rotation
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        player.setPlayerStateChangeListener(playerStateChangeListener);
        player.setPlaybackEventListener(playbackEventListener);

        //Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            player.setFullscreen(false);
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            player.setFullscreen(true);
        }
    }


    private PlaybackEventListener playbackEventListener = new PlaybackEventListener() {

        @Override
        public void onBuffering(boolean arg0) {
        }

        @Override
        public void onPaused() {
        }

        @Override
        public void onPlaying() {
        }

        @Override
        public void onSeekTo(int arg0) {
        }

        @Override
        public void onStopped() {
        }

    };

    private PlayerStateChangeListener playerStateChangeListener = new PlayerStateChangeListener() {

        @Override
        public void onAdStarted() {
        }

        @Override
        public void onError(ErrorReason arg0) {
        }

        @Override
        public void onLoaded(String arg0) {
        }

        @Override
        public void onLoading() {
        }

        @Override
        public void onVideoEnded() {
        }

        @Override
        public void onVideoStarted() {
        }
    };
}
