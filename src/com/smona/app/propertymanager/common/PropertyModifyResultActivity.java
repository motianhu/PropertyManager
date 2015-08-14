package com.smona.app.propertymanager.common;

import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.util.LogUtil;
import com.smona.app.propertymanager.util.PropertyConstants;

import android.content.Intent;
import android.view.View;

public abstract class PropertyModifyResultActivity extends
        PropertyPictureZoomActivity {

    private static final String TAG = "PropertyModifyResultActivity";

    protected static final int ACTION_MODIFY = 3;
    protected static final int ACTION_DELETE = 4;

    protected PropertyItemInfo mModifyItem = null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            if (ACTION_MODIFY == requestCode) {
                mModifyItem = data
                        .getParcelableExtra(PropertyConstants.DATA_MODIFY_ITEM);
                LogUtil.d(TAG, "mModifyItem: " + mModifyItem);
                modifySuccess();
            } else if (ACTION_DELETE == requestCode) {

            }
        }
    }

    @Override
    public void onBackPressed() {
        LogUtil.d(TAG,
                "onBackPressed: " + mModifyItem + ", " + this.getIntent());
        setListResult();
        finish();
    }

    private void setListResult() {
        if (mModifyItem != null) {
            Intent intent = new Intent();
            intent.putExtra(PropertyConstants.DATA_MODIFY_ITEM, mModifyItem);
            setResult(RESULT_OK, intent);
        }
    }

    @Override
    protected void clickView(View v) {
        int id = v.getId();
        switch (id) {
        case R.id.back:
            setListResult();
            finish();
            break;
        }
    }

    protected abstract void modifySuccess();
}
