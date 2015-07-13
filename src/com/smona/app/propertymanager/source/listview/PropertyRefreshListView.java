package com.smona.app.propertymanager.source.listview;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.util.PropertyLogUtil;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PropertyRefreshListView extends ListView implements
        OnScrollListener {
    private static final String TAG = PropertyRefreshListView.class
            .getSimpleName();
    private PropertyWorkMode mWorkMode = PropertyWorkMode.getDefault();
    private PropertyIRefreshListener mListener;

    private static final int ANIMATION_DURATION = 300;
    private int mDownY;
    private View mHeaderView;
    private int mHeaderViewHeight;
    private int mFirstVisibleItemPos;
    private DisplayMode mCurrentState = DisplayMode.Pull_Down;
    private Animation mUpAnim;
    private Animation mDownAnim;
    private ImageView mIvArrow;
    private TextView mTvState;
    private ProgressBar mProgressBar;
    private TextView mTvLastUpdateTime;

    private boolean mIsScroll2Bottom = false;
    private View mFooterView;
    private int mFooterViewHeight;
    private boolean mIsLoadMoving = false;

    public enum DisplayMode {
        Pull_Down, Release_Refresh, Refreshing
    }

    public PropertyRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAnimation();
        initHeader();
        initFooter();
        this.setOnScrollListener(this);
    }

    private void initFooter() {
        mFooterView = LayoutInflater.from(getContext()).inflate(
                R.layout.listview_refresh_footer, null);
        measureView(mFooterView);
        mFooterViewHeight = mFooterView.getMeasuredHeight();
        mFooterView.setPadding(0, -mFooterViewHeight, 0, 0);
        this.addFooterView(mFooterView);
    }

    private void initHeader() {
        mHeaderView = LayoutInflater.from(getContext()).inflate(
                R.layout.listview_refresh_header, null);
        mIvArrow = (ImageView) mHeaderView
                .findViewById(R.id.iv_listview_header_down_arrow);
        mProgressBar = (ProgressBar) mHeaderView
                .findViewById(R.id.pb_listview_header_progress);
        mTvState = (TextView) mHeaderView
                .findViewById(R.id.tv_listview_header_state);
        mTvLastUpdateTime = (TextView) mHeaderView
                .findViewById(R.id.tv_listview_header_last_update_time);

        int height = getContext().getResources().getDimensionPixelOffset(
                R.dimen.listview_refresh_height);
        mIvArrow.setMinimumWidth(height);

        mTvLastUpdateTime.setText(getFinallyUpdateTime());
        measureView(mHeaderView);
        mHeaderViewHeight = mHeaderView.getMeasuredHeight();
        PropertyLogUtil.i(TAG, "头布局的高度: " + mHeaderViewHeight);
        mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);
        this.addHeaderView(mHeaderView);
    }

    private String getLastUpdateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(new Date());
    }

    private String getFinallyUpdateTime() {
        return getContext().getResources()
                .getString(R.string.last_refresh_time)
                + " "
                + getLastUpdateTime();
    }

    private void initAnimation() {
        mUpAnim = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mUpAnim.setDuration(ANIMATION_DURATION);
        mUpAnim.setFillAfter(true);

        mDownAnim = new RotateAnimation(-180, -360, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mDownAnim.setDuration(ANIMATION_DURATION);
        mDownAnim.setFillAfter(true);
    }

    private void measureView(View child) {
        ViewGroup.LayoutParams lp = child.getLayoutParams();
        if (lp == null) {
            lp = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0, lp.width);

        int lpHeight = lp.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
                    MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0,
                    MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!mWorkMode.permitRefreshHeader()) {
            return super.onTouchEvent(ev);
        }

        switch (ev.getAction()) {
        case MotionEvent.ACTION_DOWN:
            mDownY = (int) ev.getY();
            break;
        case MotionEvent.ACTION_MOVE:
            if (mCurrentState == DisplayMode.Refreshing) {
                break;
            }

            int moveY = (int) ev.getY();
            int diffY = moveY - mDownY;
            int paddingTop = -mHeaderViewHeight + (diffY / 2);

            if (mFirstVisibleItemPos == 0 && paddingTop > -mHeaderViewHeight) {
                if (paddingTop > 0 && mCurrentState == DisplayMode.Pull_Down) {
                    mCurrentState = DisplayMode.Release_Refresh;
                    refreshHeaderViewState();
                } else if (paddingTop < 0
                        && mCurrentState == DisplayMode.Release_Refresh) {
                    PropertyLogUtil.d(TAG, "下拉刷新");
                    mCurrentState = DisplayMode.Pull_Down;
                    refreshHeaderViewState();
                }
                mHeaderView.setPadding(0, paddingTop, 0, 0);
                return true;
            }
            break;
        case MotionEvent.ACTION_UP:
            mDownY = -1;
            if (mCurrentState == DisplayMode.Pull_Down) {
                mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);
            } else if (mCurrentState == DisplayMode.Release_Refresh) {
                mHeaderView.setPadding(0, 0, 0, 0);
                mCurrentState = DisplayMode.Refreshing;
                refreshHeaderViewState();
                if (null != mListener) {
                    mListener.onPullDownToRefresh(this);
                }
            }
            break;
        default:
            break;
        }
        return super.onTouchEvent(ev);
    }

    public void onRefreshFinish() {
        if (mIsLoadMoving) {
            mIsLoadMoving = false;
            mIsScroll2Bottom = false;
            mFooterView.setPadding(0, -mFooterViewHeight, 0, 0);
        } else {
            mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);
            mProgressBar.setVisibility(View.GONE);
            mIvArrow.setVisibility(View.VISIBLE);
            mTvLastUpdateTime.setText(getFinallyUpdateTime());
            mCurrentState = DisplayMode.Pull_Down;
        }
    }

    private void refreshHeaderViewState() {
        if (mCurrentState == DisplayMode.Pull_Down) {
            mIvArrow.startAnimation(mDownAnim);
            mTvState.setText(R.string.drop_down_refresh);
        } else if (mCurrentState == DisplayMode.Release_Refresh) {
            mIvArrow.startAnimation(mUpAnim);
            mTvState.setText(R.string.release_refresh);
        } else if (mCurrentState == DisplayMode.Refreshing) {
            mIvArrow.clearAnimation();
            mIvArrow.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.VISIBLE);
            mTvState.setText(R.string.refreshing);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        PropertyLogUtil.i(TAG, "onScrollStateChanged: " + scrollState);
        if (scrollState == OnScrollListener.SCROLL_STATE_IDLE
                || scrollState == OnScrollListener.SCROLL_STATE_FLING) {
            if (mIsScroll2Bottom && !mIsLoadMoving) {
                mFooterView.setPadding(0, 0, 0, 0);
                this.setSelection(this.getCount());
                mIsLoadMoving = true;
                if (null != mListener) {
                    mListener.onPullUpToRefresh(this);
                }
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
            int visibleItemCount, int totalItemCount) {
        if (!mWorkMode.permitRefreshFooter()) {
            return;
        }

        mFirstVisibleItemPos = firstVisibleItem;
        PropertyLogUtil.i(TAG, "onScroll: " + firstVisibleItem + ", "
                + visibleItemCount + ", " + totalItemCount);
        if ((firstVisibleItem + visibleItemCount) >= totalItemCount
                && totalItemCount > 0) {
            mIsScroll2Bottom = true;
        } else {
            mIsScroll2Bottom = false;
        }
    }

    public void setRefreshListener(PropertyIRefreshListener mListener) {
        this.mListener = mListener;
    }
}
