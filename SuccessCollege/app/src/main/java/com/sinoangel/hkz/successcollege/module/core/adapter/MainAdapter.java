package com.sinoangel.hkz.successcollege.module.core.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinoangel.hkz.successcollege.R;
import com.sinoangel.hkz.successcollege.base.MyApplication;
import com.sinoangel.hkz.successcollege.module.core.ShowActivity;
import com.sinoangel.hkz.successcollege.module.core.VedioActivity;
import com.sinoangel.hkz.successcollege.module.core.bean.AlbumInfo;
import com.sinoangel.hkz.successcollege.module.core.bean.Card;

import java.util.List;
import java.util.Map;

/**
 * Created by Z on 2016/9/14.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> implements View.OnClickListener {

    private List<Card.DataBean> dataList;
    int rad;
    private Context context;

    private AlbumInfo albumInfo;

    public Map map;

    public MainAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_card, null);
        view.setOnClickListener(this);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (rad++ % 2) {
            case 0:
                holder.iv.setLayoutParams(new FrameLayout.LayoutParams(MyApplication.getInstantiation().getDimension(R.dimen.sw600_200dp), MyApplication.getInstantiation().getDimension(R.dimen.sw600_200dp)));
                break;
            case 1:
                holder.iv.setLayoutParams(new FrameLayout.LayoutParams(MyApplication.getInstantiation().getDimension(R.dimen.sw600_410dp), MyApplication.getInstantiation().getDimension(R.dimen.sw600_200dp)));
                break;
        }

        MyApplication.getInstantiation().showImgUrl(dataList.get(position).getIcon(), holder.iv);
        holder.viewHolder_save.setTag(dataList.get(position));

        Log.e("dataList.getposition",dataList.get(position).toString());
//        holder.tv.setText(position + "");

    }

    @Override
    public int getItemCount() {
        if(dataList==null){
            return 0;
        }else{
            return dataList.size();
        }
    }


//    public void setDate(String[] dataList) {
//        this.dataList = dataList;
//        notifyDataSetChanged();
//    }

    public void setDate(List<Card.DataBean> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }


    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//
//        }

        Card.DataBean mData=(Card.DataBean)v.getTag();
        Log.e("dataList--v.getTag()",mData.toString());

        if(mData.getIsAlbumList()==0){
            //单个或没有专辑
            if(mData.getAlbums().length()==0){
                return;
            }
            if(mData.getAlbums().length()>0){
                Log.e("My----mData.getAlbums()",mData.getAlbums());

                /**
                 * to VedioActivity;
                 */

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

//        Intent i = new Intent(context, YKPlayerActivity.class);
//        i.putExtra("vid", "XNzg0NTczNDky");
////        i.putExtra("platform", "youku");
//        context.startActivity(i);
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;
        public View viewHolder_save;

        public ViewHolder(View itemView) {
            super(itemView);
            viewHolder_save=itemView;
            iv = (ImageView) itemView.findViewById(R.id.imageView111);
            tv = (TextView) itemView.findViewById(R.id.textView111);
        }
    }
}
