package com.danielpark.playersample;

import android.content.pm.ActivityInfo;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.danielpark.player.BasePlayerActivity;
import com.danielpark.player.PlayerView;

/**
 * Copyright (c) 2014-2017 op7773hons@gmail.com
 * Created by Daniel Park on 2017-04-11.
 */

public class ExamplePlayerActivity extends BasePlayerActivity {

    private View playerTheme;
    private ScrollView contentsScrollView;

    @Override
    protected void initViews() {
//        super.initViews(getIntent);
        setContentView(R.layout.activity_example_player);

        mPlayerView = (PlayerView) findViewById(R.id.player_view);
        mPlayerView.setControllerVisibilityListener(this);
        mPlayerView.setFullscreenListener(this);
        mPlayerView.requestFocus();

        mPlayerView.setFullscreenIcon(false);

        mPlayerView.setPlayerTitle("This is Example title");

        setEventLoggerEnable(false);

        playerTheme = findViewById(R.id.playerTheme);
        contentsScrollView = (ScrollView) findViewById(R.id.contents);
    }

    @Override
    public void onFullscreenButtonClick() {
//        super.onFullscreenItemClick();

        switch (getRequestedOrientation()) {
            case ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED:
            case ActivityInfo.SCREEN_ORIENTATION_PORTRAIT:
            case ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT:
                mPlayerView.setFullscreenIcon(true);

                RelativeLayout.LayoutParams rl = (RelativeLayout.LayoutParams) mPlayerView.getLayoutParams();
                rl.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
                rl.addRule(RelativeLayout.CENTER_IN_PARENT);

                playerTheme.setVisibility(View.VISIBLE);
                contentsScrollView.setVisibility(View.GONE);

                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                break;
            case ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE:
            case ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE:
                mPlayerView.setFullscreenIcon(false);

                RelativeLayout.LayoutParams rl2 = (RelativeLayout.LayoutParams) mPlayerView.getLayoutParams();
                rl2.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                rl2.addRule(RelativeLayout.CENTER_IN_PARENT, 0);

                playerTheme.setVisibility(View.GONE);
                contentsScrollView.setVisibility(View.VISIBLE);

                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                break;
        }
    }
}
