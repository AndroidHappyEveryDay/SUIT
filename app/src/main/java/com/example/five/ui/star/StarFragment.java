package com.example.five.ui.star;

import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.five.R;

public class StarFragment extends Fragment{

    private StarViewModel starViewModel;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private RecyclerAdapter mAdapter;
    public Application application;
    private View view;//定义view用来设置fragment的layout

    private int mLastVisibleItem;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        starViewModel = ViewModelProviders.of(this).get(StarViewModel.class);
        view = inflater.inflate(R.layout.fragment_star, container, false);
        application =(Application)getActivity().getApplication();

        mRecyclerView =  view.findViewById(R.id.mRecyclerView);
        mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecyclerAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                /**
                 * scrollState有三种状态，分别是SCROLL_STATE_IDLE、SCROLL_STATE_TOUCH_SCROLL、SCROLL_STATE_FLING
                 * SCROLL_STATE_IDLE是当屏幕停止滚动时
                 * SCROLL_STATE_TOUCH_SCROLL是当用户在以触屏方式滚动屏幕并且手指仍然还在屏幕上时
                 * SCROLL_STATE_FLING是当用户由于之前划动屏幕并抬起手指，屏幕产生惯性滑动时
                 */
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && mLastVisibleItem + 1 == mAdapter.getItemCount()
                        && mAdapter.isShowFooter()) {

                    // 上拉加载更多
                    loadMore();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // 给lastVisibleItem赋值
                // findLastVisibleItemPosition()是返回最后一个item的位置
                mLastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });
        loadMore();
        return view;

    }


    private void loadMore() {
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.addData(20);
                mAdapter.setShowFooter(true);
                mAdapter.notifyDataSetChanged();
            }
        }, 1000);
    }

}