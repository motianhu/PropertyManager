package com.smona.app.propertymanager.notify;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.common.PropertyBaseActivity;
import com.smona.app.propertymanager.data.model.PropertyWuyetongzhiContentItem;
import com.smona.app.propertymanager.notify.process.PropertyWuyetongzhiMessageProcessProxy;
import com.smona.app.propertymanager.notify.process.PropertyWuyetongzhiSubmitRequestInfo;
import com.smona.app.propertymanager.util.LogUtil;
import com.smona.app.propertymanager.util.PropertyConstants;

public class PropertyWuyetongzhiDetailActivity extends PropertyBaseActivity {

	private static final String FONT_START = "<font color='blue'>";
	private static final String FONT_END = "</font>";
	private static final String LINK_START = "<a href=\"http://";
	private static final String LINK_END = "</a>";
	private static final String LINK_CENTER = "\">";
	private static final String TEL_START = "<a href=\"tel:";
	private static final String TEL_END = "</a>";

	private PropertyWuyetongzhiContentItem mItem;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.property_wuyetongzhi_detail);
		acquireData();
		initViews();
		requestLoadData();
	}

	private void acquireData() {
		mItem = (PropertyWuyetongzhiContentItem) getIntent()
				.getParcelableExtra(PropertyConstants.DATA_ITEM_INFO);
	}

	protected void loadData() {
		requestData();
	}

	private void requestData() {
		mProcess = new PropertyWuyetongzhiMessageProcessProxy();

		mRequestInfo = new PropertyWuyetongzhiSubmitRequestInfo();
		((PropertyWuyetongzhiSubmitRequestInfo) mRequestInfo).publishid = mItem.publishid;

		((PropertyWuyetongzhiMessageProcessProxy) mProcess)
				.submitWuyetongzhiRead(this, mRequestInfo, this);
	}

	protected void saveData(String content) {
		requestRefreshUI();
	}

	protected void refreshUI() {
		hideCustomProgressDialog();
	}

	protected void failedRequest() {
		hideCustomProgressDialog();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	protected void initHeader() {
		initText(R.id.title, R.string.property_home_wuyetongzhi);
		initView(R.id.back);
	}

	@Override
	protected void initBody() {
		TextView text = (TextView) mRoot.findViewById(R.id.notify_time);
		text.setText(mItem.publishtime);

		text = (TextView) mRoot.findViewById(R.id.notify_title);
		text.setText(mItem.title);

		text = (TextView) mRoot.findViewById(R.id.notify_content);

		String content = mItem.notice;

		LogUtil.d("start motianhu", content);
		content = content.replace(" ", "&nbsp;");
		content = processKeys(content, "<A>", "</A>", LINK_START, LINK_END);
		content = processKeys(content, "<T>", "</T>", TEL_START, TEL_END);
		content = content.replace("\n", "<br/>");
		
		LogUtil.d("end motianhu", content);
		text.setText(Html.fromHtml(content));
		text.setMovementMethod(LinkMovementMethod.getInstance()); 
	}

	private String processKeys(String content, String keyStart, String keyEnd,
			String replaceStart, String replaceEnd) {
		StringBuffer buffer = new StringBuffer();
		String linkA = null;
		String[] links = content.split(keyStart + "|" + keyEnd);
		if (links != null) {
			int lenght = links.length;
			if (lenght < 2) {
				return content;
			}
			for (int index = 0; index < lenght; index++) {
				linkA = links[index];
				if (index % 2 == 1) {
					buffer.append(FONT_START + replaceStart + linkA
							+ LINK_CENTER + linkA + replaceEnd + FONT_END);
				} else {
					buffer.append(linkA);
				}
			}
		}

		return buffer.toString();
	}

	@Override
	protected void clickView(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.back:
			finish();
			break;
		}
	}

}
