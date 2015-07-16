package com.smona.app.propertymanager;

import java.util.ArrayList;

import com.smona.app.propertymanager.baoxiu.PropertySelectedDialog;
import com.smona.app.propertymanager.baoxiu.TypeAdapter;
import com.smona.app.propertymanager.data.model.PropertyItemInfo;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public abstract class PropertyBaseActivity extends Activity {

    protected View mRoot = null;

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

    private OnClickListener mClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            clickView(v);
        }
    };

    protected abstract void clickView(View v);

    protected void initText(int resId, int text) {
        TextView title = (TextView) mRoot.findViewById(resId);
        title.setText(text);
    }

    protected void initText(int resId, String text) {
        TextView title = (TextView) mRoot.findViewById(resId);
        title.setText(text);
    }

    protected void initText(View parent, int resId, int text) {
        TextView title = (TextView) parent.findViewById(resId);
        title.setText(text);
    }
    
    protected void initText(View parent, int resId, String text) {
        TextView title = (TextView) parent.findViewById(resId);
        title.setText(text);
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
        TypeAdapter dapter = new TypeAdapter(this, datas);

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
