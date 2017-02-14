package com.sinoangel.hkz.successcollege.module.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.sinoangel.hkz.successcollege.R;
import com.sinoangel.hkz.successcollege.module.core.adapter.ViewPagerAdapter;
import com.sinoangel.hkz.successcollege.module.core.bean.AlbumInfoList;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/1/23.
 */

public class ShowActivity extends Activity{

    private AlbumInfoList albumInfo;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    public ArrayList<View> viewContainter;
    private List<AlbumInfoList.DataBean> pageData;
    private LinearLayout linear_show;
    private RelativeLayout relativeLayout;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutInflater().inflate(R.layout.activity_show, null));

        linear_show = (LinearLayout)findViewById(R.id.linear_show);
        linear_show.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return viewPager.dispatchTouchEvent(motionEvent);
            }
        });


//        imageView=new ImageView(this);
//
////        LinearLayout.LayoutParams ReLa=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        LinearLayout.LayoutParams ReLa=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
//
//        imageView.setLayoutParams(ReLa);
//
//        imageView.setBackground(getResources().getDrawable(R.color.text_color_blue_1));
//
//
//        linear_show.addView(imageView);
//
//        imageView.setVisibility(View.VISIBLE);


        viewPager = (ViewPager) findViewById(R.id.viewpager_show);
        viewPager.setPageMargin(100);

        viewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            final float MIN_SCALE1 = 0.85f;
            final float MIN_SCALE2 = 0.7f;
            final float MIN_SCALE3 = 0.6f;

            final float MIN_ALPHA1 = 0.5f;
            final float MIN_ALPHA2 = 0.5f;
            final float MIN_ALPHA3 = 0.5f;

            @Override
            public void transformPage(View view, float position) {

                int hei = view.getHeight();
                float scaleFactor;
                float tranYFactor;
                float apFactor;
                if (position <= -2) {
                    float cha = MIN_SCALE2 - MIN_SCALE3;
                    float baifenbi = (-position) - (int) (-position);
                    scaleFactor = MIN_SCALE2 - cha * baifenbi;

                    float apcha = MIN_ALPHA2 - MIN_ALPHA3;
                    apFactor = MIN_ALPHA2 - apcha * baifenbi;

                    tranYFactor = (hei - scaleFactor * hei) / 4;
                } else if (position < -1) {
                    float cha = MIN_SCALE1 - MIN_SCALE2;
                    float baifenbi = (-position) - (int) (-position);
                    scaleFactor = MIN_SCALE1 - cha * baifenbi;

                    float apcha = MIN_ALPHA1 - MIN_ALPHA2;
                    apFactor = MIN_ALPHA1 - apcha * baifenbi;

                    tranYFactor = (hei - scaleFactor * hei) / 4;
                } else if (position >= -1f && position <= 1f) {//[-1,1]
                    float cha = 1 - MIN_SCALE1;
                    scaleFactor = 1 - cha * Math.abs(position);

                    float apcha = 1 - MIN_ALPHA1;
                    apFactor = 1 - apcha * Math.abs(position);

                    tranYFactor = (hei - scaleFactor * hei) / 4;
                } else if (position > 1 && position < 2) {
                    float cha = MIN_SCALE1 - MIN_SCALE2;
                    float baifenbi = position - (int) position;
                    scaleFactor = MIN_SCALE1 - cha * baifenbi;

                    float apcha = MIN_ALPHA1 - MIN_ALPHA2;
                    apFactor = MIN_ALPHA1 - apcha * baifenbi;

                    tranYFactor = (hei - scaleFactor * hei) / 4;
                } else {
                    float cha = MIN_SCALE2 - MIN_SCALE3;
                    float baifenbi = position - (int) position;
                    scaleFactor = MIN_SCALE2 - cha * baifenbi;

                    float apcha = MIN_ALPHA2 - MIN_ALPHA3;
                    apFactor = MIN_ALPHA2 - apcha * baifenbi;

                    tranYFactor = (hei - scaleFactor * hei) / 4;
                }
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                view.setTranslationY(tranYFactor);
                view.setAlpha(apFactor);

            }
        });

//        viewPager.setOffscreenPageLimit(50);
//        viewPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.sw600_246dp));

        Intent intent = getIntent();
        String Albumsid = intent.getStringExtra("id");

//        http://cn.api.sinoangel.cn/college/getAlbumList?collegeId=4
        // 如果要指定并发值，传入数字即可：NoHttp.newRequestQueue(3);
        final RequestQueue requestQueue = NoHttp.newRequestQueue();

        Request<String> objRequest = NoHttp.createStringRequest("http://cn.api.sinoangel.cn/college/getAlbumList?collegeId=" + Albumsid, RequestMethod.GET);
        // 发起请求
        requestQueue.add(0, objRequest, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            public void onSucceed(int what, Response<String> response) {
                Log.e("int what", String.valueOf(what));
                Log.e("response", response.toString());

                viewPagerAdapter = new ViewPagerAdapter(getApplicationContext());

                albumInfo = JSON.parseObject(response.get(), AlbumInfoList.class);
                viewPagerAdapter.setDate(albumInfo.getData());

                View[] view = new View[albumInfo.getData().size()];
                LayoutInflater lf = getLayoutInflater().from(ShowActivity.this);
                // 将要分页显示的View装入数组中
                viewContainter = new ArrayList<View>();
                for (int i = 0; i < albumInfo.getData().size(); i++) {
                    view[i] = lf.inflate(R.layout.viewpager_card, null);

//                    reflectView.reflectImage(imageView,view[i]);
//                    linear_show.removeView(imageView);
//                    linear_show.addView(imageView);
//                    Log.e("songjian songjian","songjiansongjiansongjain");


                    viewContainter.add(view[i]);
                }

                //将viewContainter传给viewPagerAdapter类
                if (viewContainter != null) {
                    viewPagerAdapter.viewContainter = viewContainter;
                }

                viewPager.setAdapter(viewPagerAdapter);

            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        });


//        viewPager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                reflectView.reflectImage(imageView,viewPager);
//                linear_show.removeView(imageView);
//                linear_show.addView(imageView);
//                Log.e("songjian songjian","songjiansongjiansongjain");
////                imageView.setBackgroundColor(Color.BLUE);
//            }
//        });


    }


    /**
     * 将dp转换为Px
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
