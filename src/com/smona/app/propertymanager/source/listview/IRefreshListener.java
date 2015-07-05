package com.smona.app.propertymanager.source.listview;

import android.view.View;

public interface IRefreshListener {
    /**
     * 下拉刷新执行的刷新任务回调：当刷新完毕之后, 需要手动的调用onRefreshFinish(), 去隐藏头布局
     */
    public void onPullDownToRefresh(final View view);

    /**
     * 当加载更多时回调：当加载更多完毕之后, 需要手动的调用onRefreshFinish(), 去隐藏脚布局
     */
    public void onPullUpToRefresh(final View view);
}
