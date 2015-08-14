package com.smona.app.propertymanager.common;

import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.util.LogUtil;

import android.content.Intent;

public abstract class PropertyModifyResultActivity extends
        PropertyPictureZoomActivity {

    protected static final int ACTION_MODIFY = 3;
    protected static final int ACTION_DELETE = 4;
    protected PropertyItemInfo mModifyItem = null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            if (ACTION_MODIFY == requestCode) {
                mModifyItem = data.getParcelableExtra("modify_item");
                LogUtil.d("PropertyModifyResultActivity", "mModifyItem: "
                        + mModifyItem);
                modifySuccess();
            } else if (ACTION_DELETE == requestCode) {
                
            }
        }
    }
    
    protected abstract void  modifySuccess(); 
}
