package com.sinoangel.hkz.successcollege.module.core.fragment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.sinoangel.hkz.successcollege.R;
import com.sinoangel.hkz.successcollege.module.core.adapter.MainAdapter;
import com.sinoangel.hkz.successcollege.module.core.bean.Card;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;

import java.util.List;

/**
 * Created by Z on 2016/9/14.
 */

public class HomeFragment extends Fragment {
    private RecyclerView rv_list;
    private MainAdapter maup;
    private List<Card.DataBean> listData;
    private Card card;
    private List<String> bitmaps;
    private ImageView imageView;
    private RelativeLayout relativeLayoutj;
    private int id=1;
    private Bitmap b;

    @Nullable
    @Override

    //recyclerview没有绘制完成就调用个getcatch


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, null);

        relativeLayoutj=(RelativeLayout) view.findViewById(R.id.ReLa_xml);

        rv_list = (RecyclerView) view.findViewById(R.id.rv_main_list);
//            rv_list.setDrawingCacheEnabled(true);
        
//        //256       EXACTLY
//            rv_list.measure(View.MeasureSpec.makeMeasureSpec(600, View.MeasureSpec.EXACTLY),View.MeasureSpec.makeMeasureSpec(256, View.MeasureSpec.EXACTLY));
//            rv_list.layout(0,0,rv_list.getMeasuredWidth(),rv_list.getMeasuredHeight());
//            rv_list.buildDrawingCache();
//            b=rv_list.getDrawingCache();


        //=============================================================================================
//        imageView=new ImageView(this.getContext());
//        RelativeLayout.LayoutParams ReLa=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        ReLa.addRule(RelativeLayout.BELOW,R.id.reView);
//        ReLa.addRule(RelativeLayout.ALIGN_LEFT,R.id.reView);
//        ReLa.topMargin=10;
//        imageView.setLayoutParams(ReLa);



        StaggeredGridLayoutManager sglm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);

        rv_list.setLayoutManager(sglm);


        rv_list.setId(R.id.reView);


        //=======================================================
//        final int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//        rv_list.measure(measureSpec, measureSpec);
//        rv_list.layout(0,0,rv_list.getMeasuredWidth(),rv_list.getMeasuredHeight());
////        rv_list.measure(0,0);




        //==========================================
//        Log.e("@@@@2222",rv_list.getMeasuredHeight()+"");


//        b = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
//        final Canvas c = new Canvas(b);
//        rv_list.draw(c);

//          b = Bitmap.createBitmap(rv_list.getWidth(), rv_list.getHeight(), Bitmap.Config.ARGB_8888);
//        imageView.setImageBitmap(b);

//        imageView.setBackgroundResource(R.drawable.ic_launcher);
//        Bitmap zhe= BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);

//        reflectView.reflectImage(imageView, rv_list);
//
//        imageView.setImageBitmap(b);
//        imageView.setVisibility(View.VISIBLE);

//
//        //==================================================================
////        imageView.setImageBitmap(createReflection(b, 1, 10));
////        imageView.setBackgroundColor(Color.BLUE);
//        relativeLayoutj.addView(imageView);


//        view.post(new Runnable() {
//            @Override
//            public void run() {

//                reflectView.reflectImage(imageView,rv_list);
//                relativeLayoutj.addView(imageView);
//                b = Bitmap.createBitmap(rv_list.getMeasuredWidth(), rv_list.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
////                b = Bitmap.createBitmap(rv_list.getWidth(), rv_list.getHeight(), Bitmap.Config.ARGB_8888);
//                imageView.setImageBitmap(b);
//                relativeLayoutj.addView(imageView);
//                Log.e("songjian is here",b.toString());
//                Log.e("this is height",String.valueOf(b.getHeight()));
//                Log.e("this is height",String.valueOf(b.getWidth()));
////                final Canvas c = new Canvas(b);
////                rv_list.draw(c);
//                imageView.setBackgroundColor(Color.BLUE);

////                imageView.setBackground(R.drawable.ic_launcher);


//            }
//        });

//        rv_list.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                reflectView.reflectImage(imageView,rv_list);
//                relativeLayoutj.removeView(imageView);
//                relativeLayoutj.addView(imageView);
//                  Log.e("songjian songjian","songjiansongjiansongjain");
//                imageView.setBackgroundColor(Color.BLUE);
//            }
//        });

//        Log.e("view post After",b.toString());
//        Log.e("view post Agter 111", String.valueOf(b.getHeight()));
//        Log.e("view post After 222", String.valueOf(b.getWidth()));

//        imageView.setImageBitmap(createReflection(b, 0.2f, 101));

        maup = new MainAdapter(getActivity());
        rv_list.setAdapter(maup);

        getNetData();

//        relativeLayoutj.addView(imageView);

        return view;

    }

//    public static Bitmap convertViewToBitmap(View view)
//    {
//        view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
//                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
//        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
//        view.buildDrawingCache();
//        Bitmap bitmap = view.getDrawingCache();
//        return bitmap;
//    }


    public  void getNetData() {
        // 如果要指定并发值，传入数字即可：NoHttp.newRequestQueue(3);
        final RequestQueue requestQueue = NoHttp.newRequestQueue();

        Request<String> objRequest = NoHttp.createStringRequest("http://cn.api.sinoangel.cn/college/getContentByGroupId?groupId=1", RequestMethod.GET);
        // 发起请求
        requestQueue.add(0, objRequest, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                Log.e("int what",String.valueOf(what));
                Log.e("response",response.toString());

                card= JSON.parseObject(response.get(),Card.class);

                maup.setDate(card.getData());

            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        });

    }

    private Bitmap createReflection(Bitmap original, float percentage, int gap) {

        final int reflectionHeight = (int) (original.getHeight() * percentage);
        Bitmap bitmapWithReflection = Bitmap.createBitmap(original.getWidth(), (original.getHeight() + reflectionHeight + gap), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapWithReflection);

        Paint song=new Paint();

        // original image
        canvas.drawBitmap(original, 0, 0, song);
        // gap drawing
        final Paint transparentPaint = new Paint();
        transparentPaint.setARGB(0, 255, 255, 255);
//        canvas.drawRect(0, original.getHeight(), original.getWidth(), original.getHeight() + gap, transparentPaint);
        // reflection
        final Matrix matrix = new Matrix();
        matrix.preScale(1, -1);
        canvas.drawBitmap(Bitmap.createBitmap(original, 0, original.getHeight() - reflectionHeight, original.getWidth(), reflectionHeight, matrix, false), 0, original.getHeight() + gap, song);
        // reflection shading
        final Paint fadePaint = new Paint();
        fadePaint.setShader(new LinearGradient(0, original.getHeight(), 0, original.getHeight() + reflectionHeight + gap, 0x70ffffff, 0x00ffffff, Shader.TileMode.CLAMP));
        fadePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0, original.getHeight(), original.getWidth(), bitmapWithReflection.getHeight() + gap, fadePaint);

        original.recycle();
        return bitmapWithReflection;
    }



    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        RelativeLayout.LayoutParams ReLa=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        ReLa.addRule(RelativeLayout.BELOW,R.id.reView);
//        ReLa.addRule(RelativeLayout.ALIGN_LEFT,R.id.reView);
//        ReLa.topMargin=10;
//        imageView.setLayoutParams(ReLa);
//
//        reflectView.reflectImage(imageView, rv_list);
//
//        relativeLayoutj.addView(imageView);

    }
}
