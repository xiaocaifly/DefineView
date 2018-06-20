package com.rqhua.demo.defineview;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.internal.NavigationMenuView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.rqhua.demo.defineview.demo2.CustomeViewGroup1;
import com.rqhua.demo.defineview.demo2.StatusCallback;

import java.util.ArrayList;
import java.util.List;

public class ScrollerDemo31Activity extends Activity implements View.OnClickListener, StatusCallback {
    private RecyclerView content;
    private RecyclerView menu;
    private AbsAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.main31);
        findViewById(R.id.st_header).setOnClickListener(this);
        findViewById(R.id.st_sticky).setOnClickListener(this);
        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        initRv();
    }

    private void initRv() {
        content = (RecyclerView) findViewById(R.id.st_content);
        menu = (RecyclerView) findViewById(R.id.menu);
        CustomeViewGroup1 customeViewGroup = (CustomeViewGroup1) findViewById(R.id.cvg_layout);
        customeViewGroup.setStatusCallback(this);
        adapter = new AbsAdapter<String>(this) {
            @Override
            protected void onBindDataToView(CommonHolder holder, String bean, final int position) {
                holder.setText(R.id.text, bean + " " + position);
                holder.setOnItemClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ScrollerDemo31Activity.this, "Click position " + position, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public int getItemLayoutID(int viewType) {
                return R.layout.item_list_layout;
            }
        };
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView.LayoutManager layoutManagerMenu = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        content.setLayoutManager(layoutManager);
        content.setAdapter(adapter);
        menu.setLayoutManager(layoutManagerMenu);
        menu.setAdapter(adapter);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("item " + i);
        }
        adapter.addAll(data, true);
    }

    private static final String TAG = "MainActivity";

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.st_header:
                Log.d(TAG, "onClick st_header");
                Toast.makeText(ScrollerDemo31Activity.this, "Click st_header", Toast.LENGTH_SHORT).show();
                break;
            case R.id.st_sticky:
                Log.d(TAG, "onClick st_sticky");
                Toast.makeText(ScrollerDemo31Activity.this, "Click st_sticky", Toast.LENGTH_SHORT).show();
                break;
            case R.id.st_content:
                Log.d(TAG, "onClick st_content");
                Toast.makeText(ScrollerDemo31Activity.this, "Click st_content", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_1:
                Log.d(TAG, "onClick TEXT-1");
                Toast.makeText(ScrollerDemo31Activity.this, "Click TEXT-1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_2:
                Log.d(TAG, "onClick TEXT-2");
                Toast.makeText(ScrollerDemo31Activity.this, "Click TEXT-2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_3:
                Log.d(TAG, "onClick TEXT-3");
                Toast.makeText(ScrollerDemo31Activity.this, "Click TEXT-3", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean isContentTop(float touchSlop) {
        /*Rect rect = new Rect();
        content.getDrawingRect(rect);
        boolean b = rect.top <= touchSlop ? true : false;
        return b;*/

        if (content != null && content instanceof RecyclerView) {
            RecyclerView recyclerView = ((RecyclerView) content);
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {
                int firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                View childAt = recyclerView.getChildAt(0);
                if (childAt == null || (firstVisibleItemPosition == 0 && childAt.getTop() == 0)) {
                    return true;
                }
            }
        }
        return false;
    }
}