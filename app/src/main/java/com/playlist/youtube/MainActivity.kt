package com.playlist.youtube

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView


class MainActivity : YouTubeBaseActivity(),YouTubePlayer.OnInitializedListener{
    var videoId:String?= null
    lateinit var  youtubplayer:YouTubePlayerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        youtubplayer= findViewById(R.id.youtubplayer)
        youtubplayer.initialize(YoutubeAPI.youtubeAPI,this)
        // get the video id
        videoId= intent.getStringExtra("video_id")
    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?
                                         ,youTubePlayer: YouTubePlayer?
                                         ,wasRestored: Boolean) {
        Log.d(TAG, "onInitializationSuccess: provider is ${provider?.javaClass}")
        Log.d(TAG, "onInitializationSuccess: youTubePlayer is ${youTubePlayer?.javaClass}")
        Toast.makeText(this, "Initialized Youtube Player successfully", Toast.LENGTH_SHORT).show()
        youTubePlayer?.loadVideo(videoId)

    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider?,
        youTubeInitializationResult: YouTubeInitializationResult?
    ) {
        val REQUEST_CODE = 0

        if (youTubeInitializationResult?.isUserRecoverableError == true) {
            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show()
        } else {
            val errorMessage = "There was an error initializing the YoutubePlayer ($youTubeInitializationResult)"
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        }
    }
}
