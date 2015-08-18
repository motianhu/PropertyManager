package com.smona.app.propertymanager.common;

import java.util.ArrayList;

import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.source.listview.PropertyXListView;
import com.smona.app.propertymanager.source.listview.PropertyXListView.IXListViewListener;

public abstract class PropertyFetchListActivity extends PropertyBaseActivity
        implements IXListViewListener {

    protected static final int PAGE_SIZE = 10;
    private int mCurrPage = 1;
    private boolean mIsDataOver = false;

    protected PropertyXListView mList;
    private PropertyBaseDataAdapter mAdapter;

    @Override
    public void onRefresh() {
        stopRefresh();
    }

    @Override
    public void onLoadMore() {
        if (mIsDataOver) {
            stopRefresh();
            showMessage("数据到达终点");
            return;
        }
        loadMore();
    }

    protected abstract void loadMore();

    protected void setDataPos(int size) {
        mCurrPage += 1;
        mIsDataOver = PAGE_SIZE > size;
    }

    protected int getCurrentPage() {
        return mCurrPage;
    }

    protected void setFetchListener(ArrayList<PropertyItemInfo> data) {
        mList = (PropertyXListView) mRoot.findViewById(R.id.list_content);
        mAdapter = createAdapter(data);
        mList.setAdapter(mAdapter);
        mList.setPullRefreshEnable(false);
        mList.setPullLoadEnable(true);
        mList.setXListViewListener(this);
    }

    public abstract PropertyBaseDataAdapter createAdapter(
            ArrayList<PropertyItemInfo> data);

    protected void notifyDataSetChanged() {
        mAdapter.notifyDataSetChanged();
    }

    private void stopRefresh() {
        mList.stopLoadMore();
        mList.stopRefresh();
    }

    protected void finishDialogAndRefresh() {
        runOnUiThread(new Runnable() {
            public void run() {
                stopRefresh();
                hideCustomProgressDialog();
            }
        });
    }

}
