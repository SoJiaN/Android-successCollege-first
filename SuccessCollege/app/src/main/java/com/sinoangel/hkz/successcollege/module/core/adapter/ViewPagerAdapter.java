package com.sinoangel.hkz.successcollege.module.core.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.sinoangel.hkz.successcollege.R;
import com.sinoangel.hkz.successcollege.base.MyApplication;
import com.sinoangel.hkz.successcollege.module.core.VedioActivity;
import com.sinoangel.hkz.successcollege.module.core.bean.AlbumInfo;
import com.sinoangel.hkz.successcollege.module.core.bean.AlbumInfoList;
import com.sinoangel.hkz.successcollege.module.core.fragment.reflectView;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.List;

import static com.sinoangel.hkz.successcollege.R.id.imageView;

/**
 * Created by lenovo on 2017/1/23.
 */

public class ViewPagerAdapter extends PagerAdapter {

    public ArrayList<View> viewContainter;
    protected  List<AlbumInfoList.DataBean> dataList;
    private Context context;
    private AlbumInfo albumInfo;
    private ImageView image_reflect;
    private LinearLayout total_LL,part_LL;

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    public void setDate(List<AlbumInfoList.DataBean> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    /**
     * Determines whether a page View is associated with a specific key object
     * as returned by {@link #instantiateItem(ViewGroup, int)}. This method is
     * required for a PagerAdapter to function properly.
     *
     * @param view   Page View to check for association with <code>object</code>
     * @param object Object to check for association with <code>view</code>
     * @return true if <code>view</code> is associated with the key object <code>object</code>
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public int getCount() {
        return viewContainter.size();
    }

    /**
     * Create the page for the given position.  The adapter is responsible
     * for adding the view to the container given here, although it only
     * must ensure this is done by the time it returns from
     * {@link #finishUpdate(ViewGroup)}.
     *
     * @param container The containing View in which the page will be shown.
     * @par  @Override
    public int getItemPosition(Object object) {

    return super.getItemPosition(object);
    }am position  The page position to be instantiated.
     * @return Returns an Object representing the new page.  This does not
     * need to be a View, but can be some other container of the page.
     */
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view=viewContainter.get(position);

        total_LL=(LinearLayout)view.findViewById(R.id.total_viewpager_card_lila);
        part_LL=(LinearLayout)view.findViewById(R.id.part_viewpager_card_lila);

        image_reflect=(ImageView)view.findViewById(R.id.viewpager_reflct_imagev);

//        LinearLayout.LayoutParams ReLa=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        LinearLayout.LayoutParams ReLa=new LinearLayout.LayoutParams(600, 300);
//
//        image_reflect.setLayoutParams(ReLa);
//
//        image_reflect.setBackgroundColor(Color.BLUE);
//
//
//        total_LL.addView(image_reflect);
//
//        image_reflect.setVisibility(View.VISIBLE);



        TextView textview5= (TextView) view.findViewById(R.id.textView5);
        textview5.setText(dataList.get(position).getName());

        TextView textview= (TextView) view.findViewById(R.id.textView);
        textview.setText(dataList.get(position).getInfo());

        String urlImage="http://api2.sinoangel.cn";
        ImageView imageView3= (ImageView) view.findViewById(R.id.imageView3);
        ImageView imageView2= (ImageView) view.findViewById(R.id.imageView2);
        ImageView imageView1= (ImageView) view.findViewById(imageView);

        MyApplication.getInstantiation().showImgUrl(urlImage+dataList.get(position).getImgs().get(position).getImg(), imageView3);
        MyApplication.getInstantiation().showImgUrl(urlImage+dataList.get(position).getImgs().get(position+1).getImg(), imageView2);
        MyApplication.getInstantiation().showImgUrl(urlImage+dataList.get(position).getImgs().get(position+2).getImg(), imageView1);

        Log.e("dataList_imageUrl",urlImage+dataList.get(position).getImgs().get(position).getImg());

/**
 * 这里加入view的点击事件，播放视频
 */

        View.OnClickListener clickListener=new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                // 如果要指定并发值，传入数字即可：NoHttp.newRequestQueue(3);
                final RequestQueue requestQueue = NoHttp.newRequestQueue();

                Request<String> objRequest = NoHttp.createStringRequest("http://cn.api.sinoangel.cn/college/getVideoListByAlbumId?albumId="+dataList.get(position).getId()+"&lang=1", RequestMethod.GET);
                // 发起请求
                requestQueue.add(0, objRequest, new OnResponseListener<String>() {
                    @Override
                    public void onStart(int what) {
                        Log.e("songjian","&&&&&^I&^*&^*&^*&^*^*&^*&^*&^*^*^&*^*^*&");
                    }

                    public void onSucceed(int what, Response<String> response) {
                        Log.e("Jian Song is Here", String.valueOf(what));
                        Log.e("Jian Song's response", response.toString());

                        albumInfo = JSON.parseObject(response.get(), AlbumInfo.class);

                        for(int i=0;i<albumInfo.getData().size();i++){
                            Log.e("albumInfo.getVid()",albumInfo.getData().get(i).getVid());
                        }

//                        Intent toShow=new Intent(context, YKPlayerActivity.class);
//                        toShow.putExtra("AlbumsVid",albumInfo.getData().get(0).getVid());
//                        toShow.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(toShow);

                        Intent toShow=new Intent(context, VedioActivity.class);
                        toShow.putExtra("AlbumsId",dataList.get(position).getId());
                        toShow.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(toShow);

                    }

                    @Override
                    public void onFailed(int what, Response<String> response) {
                        Log.e("songjian","&&&&&^I&^*&^*&^*&^*^*&^*&^*&^*^*^&*^*^*&");
                    }

                    @Override
                    public void onFinish(int what) {
                        Log.e("songjian","&&&&&^I&^*&^*&^*&^*^*&^*&^*&^*^*^&*^*^*&");
                    }
                });
            }
        };

        view.setOnClickListener(clickListener);


        //设置page间距
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        p.setMargins(100,0,100,0);

        part_LL.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                reflectView.reflectImage(image_reflect,part_LL);

                total_LL.removeView(image_reflect);
                total_LL.addView(image_reflect);
                Log.e("songjian songjian","songjiansongjiansongjain");
//                image_reflect.setBackgroundColor(Color.BLUE);
            }
        });


        container.addView(view,p);

        Log.d("tag",String.valueOf(position));
        return viewContainter.get(position);
    }

    /**
     * Remove a page for the given position.  The adapter is responsible
     * for removing the view from its container, although it only must ensure
     * this is done by the time it returns from {@link #finishUpdate(ViewGroup)}.
     *
     * @param container The containing View from which the page will be removed.
     * @param position  The page position to be removed.
     * @param object    The same object that was returned by
     *                  {@link #instantiateItem(View, int)}.
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewContainter.get(position));
    }
}
