package com.sinoangel.hkz.successcollege.module.core.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.sinoangel.hkz.successcollege.R;
import com.sinoangel.hkz.successcollege.module.core.adapter.SecondAdapter;
import com.sinoangel.hkz.successcollege.module.core.bean.Card;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;

import java.util.List;

/**
 * Created by lenovo on 2017/1/22.
 */

public class SubjectFragment extends Fragment {

    private RecyclerView rv_list;
    private SecondAdapter seAp;
    private List<String> listData;
    private String[] Data;
    private Card card;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cartoon, null);
        rv_list = (RecyclerView) view.findViewById(R.id.rv_cartoon_list);

        GridLayoutManager Glm=new GridLayoutManager(this.getContext(),2, OrientationHelper.HORIZONTAL,false);
        rv_list.setLayoutManager(Glm);
        rv_list.addItemDecoration(new RecyclerView.ItemDecoration(){
            /**
             * Retrieve any offsets for the given item. Each field of <code>outRect</code> specifies
             * the number of pixels that the item view should be inset by, similar to padding or margin.
             * The default implementation sets the bounds of outRect to 0 and returns.
             * <p>
             * <p>
             * If this ItemDecoration does not affect the positioning of item views, it should set
             * all four fields of <code>outRect</code> (left, top, right, bottom) to zero
             * before returning.
             * <p>
             * <p>
             * If you need to access Adapter for additional data, you can call
             * {@link RecyclerView#getChildAdapterPosition(View)} to get the adapter position of the
             * View.
             *
             * @param outRect Rect to receive the output.
             * @param view    The child view to decorate
             * @param parent  RecyclerView this ItemDecoration is decorating
             * @param state   The current state of RecyclerView.
             */
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.right=0;
                outRect.left=0;
                outRect.bottom=0;
                outRect.top=0;
            }
        });

        seAp = new SecondAdapter(getActivity());
        rv_list.setAdapter(seAp);

        getNetData();
        return view;

    }


    /**
     * 把这里的图片换成json串里得到的图片
     */
    private void getNetData() {
        // 如果要指定并发值，传入数字即可：NoHttp.newRequestQueue(3);
        final RequestQueue requestQueue = NoHttp.newRequestQueue();

        Request<String> objRequest = NoHttp.createStringRequest("http://cn.api.sinoangel.cn/college/getContentByGroupId?groupId=3", RequestMethod.GET);
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

                //listData=card.getData();

//                for(Card.DataBean dataBean:listData){
//                    if(!bitmaps.contains(dataBean.getIcon())){
//                        bitmaps.add(dataBean.getIcon());
//                    }
//
//                }
                seAp.setDate(card.getData());
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        });
    }
}
