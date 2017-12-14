package com.kuruvatech.dgshonnali.adapter;

/**
 * Created by dayas on 29-11-2017.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.kuruvatech.dgshonnali.R;
import com.kuruvatech.dgshonnali.utils.ImageLoader;

import java.util.ArrayList;

public class FullScreenImageAdapter extends PagerAdapter {

    private Activity _activity;
    private ArrayList<String> _imagePaths;
    private LayoutInflater inflater;
    public ImageLoader imageLoader;
    // constructor
    public FullScreenImageAdapter(Activity activity,
                                  ArrayList<String> imagePaths) {
        this._activity = activity;
        this._imagePaths = imagePaths;
        imageLoader = new ImageLoader(activity,500,500);
    }

    @Override
    public int getCount() {
        return this._imagePaths.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imgDisplay;
        Button btnClose;

        inflater = (LayoutInflater) _activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.activity_single_view, container,
                false);

        imgDisplay = (ImageView) viewLayout.findViewById(R.id.SingleView);
        ImageView imageShareView = (ImageView) viewLayout.findViewById(R.id.imagesharebutton2);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        imageLoader.DisplayImage(_imagePaths.get(position), imgDisplay);
        final String imageurl = _imagePaths.get(position);

        ((ViewPager) container).addView(viewLayout);
        imageShareView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent shareIntent = new Intent();

                ArrayList<Uri> imageUris = new ArrayList<Uri>();
                imageUris.add(Uri.parse(imageLoader.getFilePath(imageurl)));
                shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
                shareIntent.setType("image/*");
                shareIntent.setAction(Intent.ACTION_SEND);
                _activity.startActivity(Intent.createChooser(shareIntent, "Share it ...."));
            }



        });
        return viewLayout;
    }
    private void setToolBar(String areaClicked) {

//        tb.setTitleTextColor(Color.rgb(Constants.TITLE_TEXT_COLOR_RED,
//                Constants.TITLE_TEXT_COLOR_GREEN, Constants.TITLE_TEXT_COLOR_BLUE));
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);

    }
}