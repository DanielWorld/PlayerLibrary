# PlayerLibrary
This is custom media player which it uses ExoPlayer

## Gradle settings
<pre>
dependencies {
    // library
    compile 'com.danielworld:player-library:1.0.0'
    compile 'com.android.support:appcompat-v7:25.3.0'
    // video player
    compile 'com.google.android.exoplayer:exoplayer:r2.2.0'
  
}
</pre>

## How to use
  1. Play video
  <pre>
        Intent intent = new Intent(this, BasePlayerActivity.class);
        intent.putExtra(BasePlayerActivity.PREFER_EXTENSION_DECODERS, false);
        intent.setData(Uri.parse(
                "http://www.youtube.com/api/manifest/dash/id/3aa39fa2cc27967f/source/youtube?as=fmp4_audio_clear,fmp4_sd_hd_clear&sparams=ip,ipbits,expire,source,id,as&ip=0.0.0.0&ipbits=0&expire=19000000000&signature=A2716F75795F5D2AF0E88962FFCD10DB79384F29.84308FF04844498CE6FBCE4731507882B8307798&key=ik0"
        ));
        intent.putExtra(BasePlayerActivity.EXTENSION_EXTRA, "mpd")
        .setAction(BasePlayerActivity.ACTION_VIEW);

        startActivity(intent);
  </pre>
  
  2. Use custom Player Activity
  <pre>
  public class YourCustomPlayerActivity extends BasePlayerActivity {
        @Override
        protected void initViews() {
            setContentView(R.layout.your_custom_player_activity); // (Required)

            mPlayerView = (PlayerView) findViewById(R.id.custom_player_view); // (Required)
            mPlayerView.setControllerVisibilityListener(this); // (Required) let the developer use controller event!
            // mPlayerView.setFullscreenListener(this);    // No need! until you manage fullscreen event by yourself!
            mPlayerView.requestFocus(); // (Required)

            // (Required) portrait vs landscape mode has different icons when activity executed at first.
            mPlayerView.setFullscreenIcon(false);
            
            // setEventLoggerEnable(true); // Recommend to use this only DEBUG mode
        }
  }
  </pre>
  
  your_custom_player_activity.xml (You must set PlayerView!)
  <pre>
  &lt;?xml version="1.0" encoding="utf-8"?>
  &lt;RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:app="http://schemas.android.com/apk/res-auto"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      android:id="@+id/root">

      &lt;com.danielpark.player.PlayerView
          android:layout_alignParentTop="true"
          android:id="@+id/player_view"
          android:layout_width="match_parent"
          android:layout_height="wrap_content"
          app:resize_mode="fit"
          app:show_timeout="5000"
          app:use_controller="true" />

      &lt;ScrollView
          android:layout_below="@+id/player_view"
          android:layout_width="match_parent"
          android:layout_height="wrap_content">

          &lt;LinearLayout
              android:layout_width="match_parent"
              android:layout_height="wrap_content"
              android:orientation="vertical">

              &lt;TextView
                  android:layout_width="match_parent"
                  android:layout_height="wrap_content"
                  android:text="dslkjfklsdjfklsjfkl\n
  dslkjfklsdjfklsjfkl\ndslkjfklsdjfklsjfkl\ndslkjfklsdjfklsjfkl\ndslkjfklsdjfklsjfkl\ndslkjfklsdjfklsjfkl\n
  dslkjfklsdjfklsjfkl\ndslkjfklsdjfklsjfkl\ndslkjfklsdjfklsjfkl\ndslkjfklsdjfklsjfkl\ndslkjfklsdjfklsjfkl\n
  dslkjfklsdjfklsjfkl\ndslkjfklsdjfklsjfkl\ndslkjfklsdjfklsjfkl\ndslkjfklsdjfklsjfkl\ndslkjfklsdjfklsjfkl\n
  dslkjfklsdjfklsjfkl\ndslkjfklsdjfklsjfkl\ndslkjfklsdjfklsjfkl\ndslkjfklsdjfklsjfkl\ndslkjfklsdjfklsjfkl\n
  dslkjfklsdjfklsjfkl\ndslkjfklsdjfklsjfkl\ndslkjfklsdjfklsjfkl\ndslkjfklsdjfklsjfkl\ndslkjfklsdjfklsjfkl\n"/>

         &lt;/LinearLayout>
      &lt;/ScrollView>


  &lt;/RelativeLayout>
  </pre>
