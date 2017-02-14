package com.sinoangel.hkz.successcollege.base;

import android.app.Activity;
import android.app.Application;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;
import com.youku.player.YoukuPlayerBaseConfiguration;

/**
 * Created by Z on 2016/9/14.
 */
public class MyApplication extends Application {

    private static MyApplication myApplication;
    private Picasso pic;
    public static YoukuPlayerBaseConfiguration configuration;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        pic = Picasso.with(this);
     //   LeakCanary.install(this);

        super.onCreate();

        //初始化NoHttp,超时配置，默认10s
        NoHttp.initialize(this, new NoHttp.Config()
                // 设置全局连接超时时间，单位毫秒
                .setConnectTimeout(30 * 1000)
                // 设置全局服务器响应超时时间，单位毫秒
                .setReadTimeout(30 * 1000)
        );

        Logger.setDebug(true); // 开启NoHttp调试模式。
        Logger.setTag("NoHttpSample"); // 设置NoHttp打印Log的TAG。

        initYuKu();
    }


    public static MyApplication getInstantiation() {
        return myApplication;
    }

    public void showImgUrl(String url, ImageView iv) {
        pic.load(url).into(iv);
    }

    public int getDimension(int id) {
        return (int) getResources().getDimension(id);
    }

    private void initYuKu() {
        configuration = new YoukuPlayerBaseConfiguration(this) {


            /**
             * 通过覆写该方法，返回“正在缓存视频信息的界面”，
             * 则在状态栏点击下载信息时可以自动跳转到所设定的界面.
             * 用户需要定义自己的缓存界面
             */
            @Override
            public Class<? extends Activity> getCachingActivityClass() {
                // TODO Auto-generated method stub
                return Activity.class;
            }

            /**
             * 通过覆写该方法，返回“已经缓存视频信息的界面”，
             * 则在状态栏点击下载信息时可以自动跳转到所设定的界面.
             * 用户需要定义自己的已缓存界面
             */

            @Override
            public Class<? extends Activity> getCachedActivityClass() {
                // TODO Auto-generated method stub
                return Activity.class;
            }

            /**
             * 配置视频的缓存路径，格式举例： /appname/videocache/
             * 如果返回空，则视频默认缓存路径为： /应用程序包名/videocache/
             *
             */
            @Override
            public String configDownloadPath() {
                // TODO Auto-generated method stub
                return null;
            }
        };
    }
}
