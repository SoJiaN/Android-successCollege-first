package com.sinoangel.hkz.successcollege.module.core;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.sinoangel.hkz.successcollege.R;
import com.sinoangel.hkz.successcollege.base.MyApplication;
import com.sinoangel.hkz.successcollege.module.core.adapter.VedioAdapter;
import com.sinoangel.hkz.successcollege.module.core.bean.AlbumInfo;
import com.sinoangel.hkz.successcollege.widget.YKPlayerActivity;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;

import static com.yolanda.nohttp.NoHttp.getContext;

/**
 * Created by lenovo on 2017/2/6.
 */

public class VedioActivity extends Activity {

    private LinearLayout l_left,l_left_top;
    private RelativeLayout l_left_bottom;
    private TextView top_left,top_right,bottom_bottom;
    private ImageView iv_bottom;
    private RecyclerView recyView;
    private AlbumInfo albumInfo;
    private VedioAdapter vedioAdapter;
    private static String vid;
    private static String app_icon;

    @Override
    protected void onCreate(Bundle instance){
        super.onCreate(instance);
        setContentView(getLayoutInflater().inflate(R.layout.activity_vedio,null));
        initVeiw();

        GridLayoutManager Glm=new GridLayoutManager(getApplicationContext(),1, OrientationHelper.VERTICAL,false);
        recyView.setLayoutManager(Glm);

        final Intent intent = getIntent();
        String AlbumsId = intent.getStringExtra("AlbumsId");

        /**
         * to YK
         */

        // 如果要指定并发值，传入数字即可：NoHttp.newRequestQueue(3);
        final RequestQueue requestQueue = NoHttp.newRequestQueue();

        Request<String> objRequest = NoHttp.createStringRequest("http://cn.api.sinoangel.cn/college/getVideoListByAlbumId?albumId="+AlbumsId+"&lang=1", RequestMethod.GET);
        // 发起请求
        requestQueue.add(0, objRequest, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            public void onSucceed(int what, Response<String> response) {

                albumInfo = JSON.parseObject(response.get(), AlbumInfo.class);

                vedioAdapter=new VedioAdapter(getContext());
                recyView.setAdapter(vedioAdapter);

                vedioAdapter.setDate(albumInfo.getData(), iv_bottom,top_left,top_right,bottom_bottom);

                top_left.setText(albumInfo.getData().get(0).getAppName());
                top_right.setText(albumInfo.getData().get(0).getAppName());

                bottom_bottom.setText(albumInfo.getData().get(0).getAppName());

                MyApplication.getInstantiation().showImgUrl("http://api2.sinoangel.cn"+albumInfo.getData().get(0).getApp_icon(),iv_bottom);

                iv_bottom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Log.e("iv_bottom.Listener","1111111111111111111111111111");

//                for(int i=0;i<albumInfo.getData().size();i++){
//                    Log.e("albumInfo.getVid()",albumInfo.getData().get(i).getVid());
//                }

//                增加VedioActivity后;
                        //=======================================================================================
//                        if(VedioActivity.vid!=null&&VedioActivity.app_icon!=null){
//                            Intent toShow=new Intent(getApplicationContext(), YKPlayerActivity.class);
//                            toShow.putExtra("AlbumsVid",vid);
//                            toShow.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            getApplicationContext().startActivity(toShow);
//                        }else{
                            Intent toShow=new Intent(getApplicationContext(), YKPlayerActivity.class);
//                            toShow.putExtra("AlbumsVid",albumInfo.getData().get(0).getVid());
                        toShow.putExtra("AlbumsVid",vid);
                            toShow.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getApplicationContext().startActivity(toShow);
//                        }

                    }
                });


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

    public String getVid(String vid){
        this.vid=vid;
        return vid;
    }


    private void initVeiw() {
        l_left=(LinearLayout) findViewById(R.id.l_left);
        l_left_top=(LinearLayout) findViewById(R.id.l_left_top);
        l_left_bottom=(RelativeLayout) findViewById(R.id.l_left_bottom);

        top_left=(TextView)findViewById(R.id.textView_top_left);
        top_right=(TextView)findViewById(R.id.textView_top_right);
        bottom_bottom=(TextView)findViewById(R.id.textView_bottom_bottom);

        iv_bottom=(ImageView)findViewById(R.id.iv_toYK);

        recyView=(RecyclerView)findViewById(R.id.recyV);
    }
}
