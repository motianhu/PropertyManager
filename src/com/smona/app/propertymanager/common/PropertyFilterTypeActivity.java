package com.smona.app.propertymanager.common;

import java.util.ArrayList;

import android.content.Intent;

import com.smona.app.propertymanager.data.model.PropertyErshouwupinContentItem;
import com.smona.app.propertymanager.data.model.PropertyFangwuzulinContentItem;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.util.LogUtil;
import com.smona.app.propertymanager.util.PropertyConstants;

public abstract class PropertyFilterTypeActivity extends
        PropertyFetchListActivity {

    private static final String TAG = "PropertyFilterTypeActivity";

    protected ArrayList<PropertyItemInfo> mAllDatas = new ArrayList<PropertyItemInfo>();
    protected ArrayList<PropertyItemInfo> mShowDatas = new ArrayList<PropertyItemInfo>();

    protected static final int ACTION_DELETE_LIST = 6;
    protected PropertyItemInfo mModifyItem = null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            if (PropertyConstants.ACTION_MODIFY_LIST == requestCode) {
                processOnResult(data);
            }
        }
    }

    private void processOnResult(Intent data) {
        PropertyItemInfo cancelItem = data
                .getParcelableExtra(PropertyConstants.DATA_CANCEL_ITEM);
        if (cancelItem != null) {
            LogUtil.d(TAG, "cancelItem: " + cancelItem);
            modifySuccess(cancelItem, true);
            return;
        }
        PropertyItemInfo modifyItem = data
                .getParcelableExtra(PropertyConstants.DATA_MODIFY_ITEM);
        if (modifyItem != null) {
            LogUtil.d(TAG, "mModifyItem: " + mModifyItem);
            modifySuccess(modifyItem, false);
        }
    }

    protected void modifySuccess(PropertyItemInfo modifyItem, boolean isRemove) {
        boolean modifyAll = modifyDatas(modifyItem, mAllDatas, isRemove);
        boolean modifyShow = modifyDatas(modifyItem, mShowDatas, isRemove);
        LogUtil.d(TAG, "modifyAll: " + modifyAll + ", modifyShow :"
                + modifyShow);
        if (modifyAll || modifyShow) {
            notifyDataSetChanged();
        }
    }

    private boolean modifyDatas(PropertyItemInfo modifyItem,
            ArrayList<PropertyItemInfo> datas, boolean isRemove) {
        if (modifyItem instanceof PropertyErshouwupinContentItem) {
            return modifyWupin(datas,
                    (PropertyErshouwupinContentItem) modifyItem, isRemove);
        } else if (modifyItem instanceof PropertyFangwuzulinContentItem) {
            return modifyZulin(datas,
                    (PropertyFangwuzulinContentItem) modifyItem, isRemove);
        }
        return false;
    }

    private boolean modifyWupin(ArrayList<PropertyItemInfo> datas,
            PropertyErshouwupinContentItem modifyItem, boolean isRemove) {
        for (PropertyItemInfo item : datas) {
            if (((PropertyErshouwupinContentItem) item).publishid
                    .equals(modifyItem.publishid)) {
                if (isRemove) {
                    datas.remove(item);
                } else {
                    ((PropertyErshouwupinContentItem) item)
                            .cloneData(modifyItem);
                }
                return true;
            }
        }
        return false;
    }

    private boolean modifyZulin(ArrayList<PropertyItemInfo> datas,
            PropertyFangwuzulinContentItem modifyItem, boolean isRemove) {
        for (PropertyItemInfo item : datas) {
            if (((PropertyFangwuzulinContentItem) item).publishid
                    .equals(modifyItem.publishid)) {
                if (isRemove) {
                    datas.remove(item);
                } else {
                    ((PropertyFangwuzulinContentItem) item)
                            .cloneData(modifyItem);
                }
                return true;
            }
        }
        return false;
    }
}
