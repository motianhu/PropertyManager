package com.smona.app.propertymanager.common;

import java.util.ArrayList;

import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.source.listview.XListView;
import com.smona.app.propertymanager.source.listview.XListView.IXListViewListener;
import com.smona.app.propertymanager.zulin.PropertyZulinDetailAdapter;

public abstract class PropertyFetchListActivity extends PropertyBaseActivity implements
        IXListViewListener {

    protected static final int PAGE_SIZE = 10;
    private int mCurrPage = 1;
    private boolean mIsDataOver = false;

    protected XListView mList;
    private PropertyZulinDetailAdapter mAdapter;

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
        mList = (XListView) mRoot.findViewById(R.id.list_content);
        mAdapter = new PropertyZulinDetailAdapter(this, data);
        mList.setAdapter(mAdapter);
        mList.setPullRefreshEnable(true);
        mList.setPullLoadEnable(true);
        mList.setXListViewListener(this);
    }
    
    protected void notifyDataSetChanged() {
        mAdapter.notifyDataSetChanged();
    }

    private void stopRefresh() {
        mList.stopLoadMore();
        mList.stopRefresh();
    }
    
    protected void finishDialogAndRefresh() {
        stopRefresh();
        hideCustomProgressDialog();
    }

}
