package com.sinoangel.hkz.successcollege.module.core;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sinoangel.hkz.successcollege.R;
import com.sinoangel.hkz.successcollege.module.core.bean.Card;
import com.sinoangel.hkz.successcollege.module.core.fragment.ArtFragment;
import com.sinoangel.hkz.successcollege.module.core.fragment.HomeFragment;
import com.sinoangel.hkz.successcollege.module.core.fragment.JobFragment;
import com.sinoangel.hkz.successcollege.module.core.fragment.LiftFragment;
import com.sinoangel.hkz.successcollege.module.core.fragment.SubjectFragment;
import com.sinoangel.hkz.successcollege.module.core.fragment.reflectView;

import java.util.List;

public class MainActivity extends FragmentActivity {

    private FragmentManager fm;
    private FragmentTransaction ft;
    private Fragment hf, jf, sf,lif,af;
    private RadioGroup rg_btn;
    private Card card;
    private List<Card.DataBean> Databeans;
    private FrameLayout frameLayout;
    private ImageView imageView;
    private LinearLayout lishow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        Window window = getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        //透明状态栏
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //隐藏底部导航栏
        final View main=getLayoutInflater().inflate(R.layout.activity_main,null);
        frameLayout=(FrameLayout)main.findViewById(R.id.fl_box);

        lishow=(LinearLayout)main.findViewById(R.id.linear_show_MainAc);

        imageView=new ImageView(this);
        LinearLayout.LayoutParams LeLa_main=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 250);
        LeLa_main.topMargin=10;

        imageView.setLayoutParams(LeLa_main);


        lishow.addView(imageView);

        imageView.setVisibility(View.VISIBLE);


        main.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i=main.getSystemUiVisibility();
                if(i==View.SYSTEM_UI_FLAG_HIDE_NAVIGATION){
                    main.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                }else{
                    if(i==View.SYSTEM_UI_FLAG_VISIBLE){
                        main.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
                    }
                    if(i==View.SYSTEM_UI_FLAG_LOW_PROFILE){
                        main.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                    }
                }
            }
        });
        setContentView(main);

        fm = getSupportFragmentManager();//布局管理器

        hf = new HomeFragment();
        jf = new JobFragment();
        sf = new SubjectFragment();
        lif = new LiftFragment();
        af = new ArtFragment();

        initView(savedInstanceState);


        ft = fm.beginTransaction();

        ((RadioButton) rg_btn.getChildAt(0)).setChecked(true);

        frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                reflectView.reflectImage(imageView,frameLayout);
                lishow.removeView(imageView);
                lishow.addView(imageView);
                Log.e("songjian songjian","songjiansongjiansongjain");
//                imageView.setBackgroundColor(Color.BLUE);
//                imageView.setBackground(getResources().getDrawable(R.drawable.ic_launcher));
            }
        });


    }

    private void initView(final Bundle savedInstanceState) {
        rg_btn = (RadioGroup) findViewById(R.id.rg_btn);

        rg_btn.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                ft = fm.beginTransaction();
                List<Fragment> lf = fm.getFragments();

                switch (checkedId) {
                    case R.id.rb_main_1:
                        if (lf == null || !lf.contains(hf)) {
                            ft.add(R.id.fl_box, hf);
                        }

                        if(savedInstanceState==null){
                            ft.hide(jf);
                            ft.hide(sf);
                            ft.hide(af);
                            ft.hide(lif);
                            ft.show(hf);
                            ft.commit();
                        }
                        break;
                    case R.id.rb_main_2:
                        if (lf == null || !lf.contains(jf)) {
                            ft.add(R.id.fl_box, jf);
                        }
                        if(savedInstanceState==null){
                            ft.hide(hf);
                            ft.hide(sf);
                            ft.hide(af);
                            ft.hide(lif);
                            ft.show(jf);
                            ft.commit();
                        }
                        break;
                    case R.id.rb_main_3:
                        if (lf == null || !lf.contains(sf)) {
                            ft.add(R.id.fl_box, sf);
                        }

                        if(savedInstanceState==null){
                            ft.hide(jf);
                            ft.hide(hf);
                            ft.hide(af);
                            ft.hide(lif);
                            ft.show(sf);
                            ft.commit();
                        }
                        break;
                    case R.id.rb_main_4:
                        if (lf == null || !lf.contains(lif)) {
                            ft.add(R.id.fl_box, lif);
                        }

                        if(savedInstanceState==null){
                            ft.hide(jf);
                            ft.hide(af);
                            ft.hide(sf);
                            ft.hide(hf);
                            ft.show(lif);
                            ft.commit();
                        }
                        break;
                    case R.id.rb_main_5:
                        if (lf == null || !lf.contains(af)) {
                            ft.add(R.id.fl_box, af);
                        }

                        if(savedInstanceState==null){
                            ft.hide(jf);
                            ft.hide(hf);
                            ft.hide(sf);
                            ft.hide(lif);
                            ft.show(af);
                            ft.commit();
                        }
                        break;
                }

            }
        });
    }

}
