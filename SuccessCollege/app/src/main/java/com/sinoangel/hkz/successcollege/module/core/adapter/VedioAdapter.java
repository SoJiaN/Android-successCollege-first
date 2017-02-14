package com.sinoangel.hkz.successcollege.module.core.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinoangel.hkz.successcollege.R;
import com.sinoangel.hkz.successcollege.base.MyApplication;
import com.sinoangel.hkz.successcollege.module.core.VedioActivity;
import com.sinoangel.hkz.successcollege.module.core.bean.AlbumInfo;

import java.util.List;

/**
 * Created by lenovo on 2017/2/6.
 */

public class VedioAdapter extends RecyclerView.Adapter<VedioAdapter.ViewHolder> implements View.OnClickListener{
    private Context context;
    private List<AlbumInfo.DataBean> dataList;
    private String urlImage;
    private ImageView imageView;

    private VedioActivity veac;

    private TextView top_left,top_right,bottom_bottom;

    public  static String toShowImage;
    public  static String vid;

    public VedioAdapter(Context context){
        this.context=context;
    }


    @Override
    public VedioAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vedio_card, null);
        view.setOnClickListener(this);

        VedioAdapter.ViewHolder vh = new VedioAdapter.ViewHolder(view);
        return vh;
    }


    @Override
    public void onBindViewHolder(VedioAdapter.ViewHolder holder, int position) {

        Log.e("onBindViewHolder","因为我对recyclerview控件的大小进行了一些适配大小的工作，而导致这个问题。");

        urlImage="http://api2.sinoangel.cn";
        MyApplication.getInstantiation().showImgUrl(urlImage+dataList.get(position).getApp_icon(), holder.iv_recy_vedio);

        holder.view_holder.setTag(position);

        toShowImage=urlImage+dataList.get(position).getApp_icon();
//        vid=dataList.get(position).getVid();

        holder.tv_recy_vedio_name.setText(dataList.get(position).getAppName());
//        holder.tv_recy_vedio_duration.setText(dataList.get(position).getDuration());
        holder.tv_recy_vedio_duration.setText("00:15:30");
    }

    @Override
    public long getItemId(int position) {
        notifyItemChanged(position);
        return super.getItemId(position);
    }

    @Override
    public void onClick(View view) {
        int id = (int) view.getTag();

        Log.e("VedioAdapter.Listener",urlImage+dataList.get(id).getApp_icon());
        Log.e("vedioAdapter.Listener",id+"");

        MyApplication.getInstantiation().showImgUrl(urlImage+dataList.get(id).getApp_icon(),imageView);
        this.top_left.setText(dataList.get(id).getAppName());
        this.top_right.setText(dataList.get(id).getId());
        this.bottom_bottom.setText(dataList.get(id).getVid());

        veac=new VedioActivity();
        veac.getVid(dataList.get(id).getVid());

//        this.bottom_bottom.setText("顾名思义，android:layout_alignTop就表示目标控件和引用控件的上边缘对齐，android:layout_alignLeft则表示目标控件与引用控件的左边缘对齐，android:layout_alignBaseLine是基于基准线对其，基准线就是我们写英文字母那4行线的第三条,,,这组属性的值是 true 或者 false，因为每个控件的直接父控件只有一个，所以用true/false来表示是否与父控件的边缘对齐");

    }

    @Override
    public int getItemCount() {
        if(dataList==null){
            return 0;
        }else{
            return dataList.size();
        }
    }


    public void setDate(List<AlbumInfo.DataBean> dataList, ImageView imageView,TextView textView1,TextView textView2,TextView textView3) {
        this.dataList = dataList;

        this.imageView=imageView;
        this.top_left=textView1;
        this.top_right=textView2;
        this.bottom_bottom=textView3;

        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View view_holder;
        ImageView iv_recy_vedio;
        TextView tv_recy_vedio_name,tv_recy_vedio_duration;
        public ViewHolder(View view) {
            super(view);
            view_holder=view;
            iv_recy_vedio=(ImageView) view.findViewById(R.id.iv_recy_vedio);
            tv_recy_vedio_name=(TextView) view.findViewById(R.id.tv_recy_vedio_name);
            tv_recy_vedio_duration=(TextView) view.findViewById(R.id.tv_recy_vedio_duration);
        }
    }
}
