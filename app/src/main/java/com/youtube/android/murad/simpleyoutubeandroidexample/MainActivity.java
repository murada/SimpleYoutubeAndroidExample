package com.youtube.android.murad.simpleyoutubeandroidexample;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MainActivity extends YouTubeBaseActivity  implements YouTubePlayer.OnInitializedListener {


    public static final String YOUTUBE_VIDEO_CODE = "U2bKzQt1VPI"; // you can find this value in the link of the youtube video
    public static final String YOUTUBE_KEY = "YOUR_YOUTUBE_API_KEY";
    private YouTubePlayerView youTubePlayerView ;
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    private YouTubePlayer youTubePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubePlayerView.initialize(YOUTUBE_KEY,this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        if (!wasRestored) {
            this.youTubePlayer = youTubePlayer;
            youTubePlayer.loadVideo(YOUTUBE_VIDEO_CODE);
            youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format("damn", youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (youTubePlayer != null) {
            youTubePlayer.loadVideo(YOUTUBE_VIDEO_CODE);
        }
    }
}