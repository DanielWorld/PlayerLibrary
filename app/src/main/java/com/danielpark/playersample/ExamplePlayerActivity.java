package com.danielpark.playersample;

import com.danielpark.player.BasePlayerActivity;
import com.danielpark.player.PlayerView;

/**
 * Copyright (c) 2014-2017 op7773hons@gmail.com
 * Created by Daniel Park on 2017-04-11.
 */

public class ExamplePlayerActivity extends BasePlayerActivity {

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
    }
}
