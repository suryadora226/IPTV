package com.example.watchme2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    private String websiteUri = "https://simultv.s.llnwi.net/n4s4/2ANetwork/interlink.m3u8";
    private ProgressDialog progressDialog;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = findViewById(R.id.videoview);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Buffering");
        progressDialog.setCancelable(true);
        playVideo();
    }
    private void playVideo(){
        try {
            getWindow().setFormat(PixelFormat.TRANSLUCENT);
            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);

            videoView.setMediaController(mediaController);

            Uri videoUri = Uri.parse(websiteUri);
            videoView.setVideoURI(videoUri);
            videoView.requestFocus();
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    progressDialog.dismiss();
                    videoView.start();
                }
            });
        }
        catch (Exception e){
            progressDialog.dismiss();
        }
    }
}