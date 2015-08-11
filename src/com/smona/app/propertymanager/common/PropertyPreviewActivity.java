package com.smona.app.propertymanager.common;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.smona.app.propertymanager.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class PropertyPreviewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_common_preview_picture);
        String url = getIntent().getStringExtra("url");
        ImageView image = (ImageView)findViewById(R.id.image);
        ImageLoader.getInstance().displayImage(url, image);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
