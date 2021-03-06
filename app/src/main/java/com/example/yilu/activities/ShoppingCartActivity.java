package com.example.yilu.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.TargetApi;
import android.app.StatusBarManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yilu.R;
import com.example.yilu.entities.Good;
import com.example.yilu.utils.GoodsRecyclerViewAdapter;
import com.google.android.material.button.MaterialButton;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {

    private List<Good> goodList = new ArrayList<>();
    private List<Integer> selectedGoodIdList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        applyKitKatTranslucency();
        setContentView(R.layout.activity_shopping_cart);

        /**商品列表的初始化和填充**/
        initGoods();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.sc_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        GoodsRecyclerViewAdapter adapter = new GoodsRecyclerViewAdapter(goodList);
        recyclerView.setAdapter(adapter);

        /**“购物车”初始化和填充**/
        TextView goodNumberTextView = (TextView)findViewById(R.id.sc_title_text2_2);
        goodNumberTextView.setText(""+goodList.size());

        /**设置底部栏的监听事件**/
        //CheckBox
        CheckBox allCheckedCheckBox = (CheckBox)findViewById(R.id.all_checked_checkbox);
        allCheckedCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean is_checked = allCheckedCheckBox.isChecked();
                int n=goodList.size();
                int position = 0;
                while(position<n){
                    goodList.get(position).setIs_checked(is_checked);
                    position = position+1;
                }
                adapter.notifyDataSetChanged();
            }
        });

        //MaterialButton
        MaterialButton startButton = (MaterialButton)findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1 向selectedGoodIdList中添加已选中的商品的Id
                int n=goodList.size();
                int position = 0;
                Good good;
                while(position<n){
                    good=goodList.get(position);
                    if (good.getIs_checked()){
                        selectedGoodIdList.add(good.getId());
                        good.setIs_checked(false);
                    }
                    position = position+1;
                }

                //2 跳转到地图页面
                if(selectedGoodIdList!=null && !selectedGoodIdList.isEmpty()){
                    Intent intent = new Intent(ShoppingCartActivity.this, MapActivity.class);
                    intent.putIntegerArrayListExtra("selectedGoodIdList", (ArrayList<Integer>) selectedGoodIdList);
                    startActivity(intent);

                    adapter.notifyDataSetChanged();
                    allCheckedCheckBox.setChecked(false);
                    selectedGoodIdList.clear();
                }
                else{
                    Toast.makeText(ShoppingCartActivity.this,"您尚未选择任何商品！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initGoods(){
        int i = 1;
        while(i<=12){
            Good good = new Good(i,"CONVERSE匡威官方 All Star Move低帮厚底帆布鞋女休闲鞋570256C",
                    "黑//白//570256C;37.5",469,1,R.drawable.converse,false);
            goodList.add(good);
            i = i+1;
        }
    }


    /**
     * Apply KitKat specific translucency.
     */
    private void applyKitKatTranslucency() {

        // KitKat translucent navigation/status bar.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager mTintManager = new SystemBarTintManager(this);
            mTintManager.setStatusBarTintEnabled(true);

            mTintManager.setStatusBarTintResource(R.color.transparent_light_gray);//通知栏所需颜色
        }

    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

}