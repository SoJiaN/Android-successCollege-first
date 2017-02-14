package com.sinoangel.hkz.successcollege.module.core.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinoangel.hkz.successcollege.R;
import com.sinoangel.hkz.successcollege.base.MyApplication;
import com.sinoangel.hkz.successcollege.module.core.ShowActivity;
import com.sinoangel.hkz.successcollege.module.core.VedioActivity;
import com.sinoangel.hkz.successcollege.module.core.bean.AlbumInfo;
import com.sinoangel.hkz.successcollege.module.core.bean.Card;

import java.util.List;

/**
 * Created by lenovo on 2017/1/23.
 */

public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.ViewHolder> implements View.OnClickListener{

    private Context context;
    private List<Card.DataBean> dataList;
    private AlbumInfo albumInfo;

    public SecondAdapter(Context context){
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cartoon_card, null);
        view.setOnClickListener(this);
        SecondAdapter.ViewHolder vh = new SecondAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        MyApplication.getInstantiation().showImgUrl(dataList.get(position).getIcon(), holder.iv1);
//        holder.tv.setText(dataList.get(position).getId());

        holder.itemView.setTag(dataList.get(position));
    }

    @Override
    public void onClick(View view) {

        Card.DataBean mData=(Card.DataBean)view.getTag();

        if(mData.getIsAlbumList()==0){
            //单个或没有专辑
            if(mData.getAlbums().length()==0){
                return;
            }
            if(mData.getAlbums().length()>0){

//                // 如果要指定并发值，传入数字即可：NoHttp.newRequestQueue(3);
//                final RequestQueue requestQueue = NoHttp.newRequestQueue();
//
//                Request<String> objRequest = NoHttp.createStringRequest("http://cn.api.sinoangel.cn/college/getVideoListByAlbumId?albumId="+mData.getAlbums()+"&lang=1", RequestMethod.GET);
//                // 发起请求
//                requestQueue.add(0, objRequest, new OnResponseListener<String>() {
//                    @Override
//                    public void onStart(int what) {
//                        Log.e("songjian","&&&&&^I&^*&^*&^*&^*^*&^*&^*&^*^*^&*^*^*&");
//                    }
//
//                    public void onSucceed(int what, Response<String> response) {
//                        Log.e("Jian Song is Here", String.valueOf(what));
//                        Log.e("Jian Song's response", response.toString());
//
//                        albumInfo = JSON.parseObject(response.get(), AlbumInfo.class);
//
//                        for(int i=0;i<albumInfo.getData().size();i++){
//                            Log.e("albumInfo.getVid()",albumInfo.getData().get(i).getVid());
//                        }
//
//                        Intent toShow=new Intent(context, VedioActivity.class);
//                        toShow.putExtra("AlbumsVid",albumInfo.getData().get(0).getVid());
//                        context.startActivity(toShow);
//
//                    }
//
//                    @Override
//                    public void onFailed(int what, Response<String> response) {
//                        Log.e("songjian","&&&&&^I&^*&^*&^*&^*^*&^*&^*&^*^*^&*^*^*&");
//                    }
//
//                    @Override
//                    public void onFinish(int what) {
//                        Log.e("songjian","&&&&&^I&^*&^*&^*&^*^*&^*&^*&^*^*^&*^*^*&");
//                    }
//                });

                Intent toShow=new Intent(context, VedioActivity.class);
                toShow.putExtra("AlbumsId",mData.getAlbums());
                context.startActivity(toShow);

            }
        }
        if(mData.getIsAlbumList()==1){
            //多个专辑
            Intent toShow=new Intent(context, ShowActivity.class);
            toShow.putExtra("id",mData.getId());
            context.startActivity(toShow);
        }
    }

    @Override
    public int getItemCount() {
        if(dataList==null){
            return 0;
        }else{
            return dataList.size();
        }
    }

    public void setDate(List<Card.DataBean> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv1;
        TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            iv1 = (ImageView) itemView.findViewById(R.id.iv1);
            tv=(TextView) itemView.findViewById(R.id.tv);
        }
    }

}
