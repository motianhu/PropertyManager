package com.smona.app.propertymanager;

import java.util.ArrayList;

import com.smona.app.propertymanager.baoxiu.PropertySelectedDialog;
import com.smona.app.propertymanager.common.PropertyTypeAdapter;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;
import com.smona.app.propertymanager.data.process.PropertyMessageProcess;
import com.smona.app.propertymanager.data.process.PropertyMessageProcess.IQuestCallback;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public abstract class PropertyBaseActivity extends PropertyDialogActivity implements IQuestCallback {

    private static final int MSG_LOAD_DATA = 1;
    private static final int MSG_NOTIFY_REFRESH_UI = 2;

    protected View mRoot = null;

    protected PropertyMessageProcess mProcess;

    @SuppressLint("HandlerLeak")
    private Handler mLoadDataHandler = new Handler() {
        public void handleMessage(Message msg) {
            int what = msg.what;
            switch (what) {
            case MSG_LOAD_DATA:
                loadData();
                break;
            case MSG_NOTIFY_REFRESH_UI:
                refreshUI();
                break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void initViews() {
        initRoot();
        initHeader();
        initBody();
    }

    private void initRoot() {
        mRoot = findViewById(R.id.root);
    }

    protected abstract void initHeader();

    protected abstract void initBody();

    protected void requestLoadData() {
        sendMessage(MSG_LOAD_DATA);
    }

    protected void requestRefreshUI() {
        sendMessage(MSG_NOTIFY_REFRESH_UI);
    }

    private void sendMessage(int msg) {
        mLoadDataHandler.sendEmptyMessage(msg);
    }
    
    protected void loadData() {

    }

    protected void refreshUI() {

    }
    
    protected void saveData(String content) {

    }
    
    protected void failedRequest() {

    }    
    
    
    public  void onResult(boolean success, String content) {
        if(success) {
            saveData(content);
        } else {
            failedRequest();
        }
    }

    private OnClickListener mClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            clickView(v);
        }
    };

    protected abstract void clickView(View v);

    // text
    protected void initText(int resId, int text) {
        TextView title = (TextView) mRoot.findViewById(resId);
        title.setText(text);
    }

    protected void initText(int resId, String text) {
        TextView title = (TextView) mRoot.findViewById(resId);
        title.setText(text);
    }

    // parent text
    protected void initText(View parent, int resId, int text) {
        TextView title = (TextView) parent.findViewById(resId);
        title.setText(text);
    }

    protected void initText(View parent, int resId, String text) {
        TextView title = (TextView) parent.findViewById(resId);
        title.setText(text);
    }

    // hint
    protected void initTextHint(int resId, int text) {
        TextView title = (TextView) mRoot.findViewById(resId);
        title.setHint(text);
    }

    protected void initTextHint(int resId, String text) {
        TextView title = (TextView) mRoot.findViewById(resId);
        title.setHint(text);
    }

    // parent hint
    protected void initTextHint(View parent, int resId, int text) {
        TextView title = (TextView) parent.findViewById(resId);
        title.setHint(text);
    }

    protected void initTextHint(View parent, int resId, String text) {
        TextView title = (TextView) parent.findViewById(resId);
        title.setHint(text);
    }

    protected void initView(int resId) {
        View view = mRoot.findViewById(resId);
        view.setOnClickListener(mClickListener);
    }

    protected void gotoSubActivity(Class<?> clazz) {
        Intent intent = new Intent();
        intent.setClass(this, clazz);
        startActivity(intent);
    }

    protected void showSingleChoiceType(ArrayList<PropertyItemInfo> datas,
            final IChoiceCallback callback) {
        PropertyTypeAdapter dapter = new PropertyTypeAdapter(this, datas);

        PropertySelectedDialog.Builder builder = new PropertySelectedDialog.Builder(
                this);
        builder.setSingleChoiceItems(dapter, 0,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        callback.onChoice(which);
                        dialog.dismiss();
                    }
                });
        builder.show();
    }

    public interface IChoiceCallback {
        void onChoice(int which);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
