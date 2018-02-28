/*MIT License

Copyright (c) 2016 ittianyu

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.*/
package com.loften.bottomnavigationviewsample.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.loften.bottomnavigationviewsample.R;
import com.loften.bottomnavigationviewsample.utils.SizeUtils;

import java.lang.reflect.Field;

/**
 * Created by lcw on 2016/12/5.
 */

public class DivBottomNavigationView extends BottomNavigationView {

    private static final int BADGE_MIN_WIDTH = 16;
    private static final int BADGE_MARGIN_TOP = 5;
    private static final int BADGE_MARGIN_LEFT = 15;

    private BottomNavigationMenuView bottomMenuView;

    public DivBottomNavigationView(Context context) {
        super(context);
    }

    public DivBottomNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initBadges(context, attrs);
    }

    public DivBottomNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //初始化badge
    private void initBadges(Context context, AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.advanced_bottom_navigation_bar);
        int badgeLayouId = typedArray.getResourceId(R.styleable.advanced_bottom_navigation_bar_badge_layout, -1);
        typedArray.recycle();
        bottomMenuView = getField(getClass().getSuperclass(), this, "mMenuView", BottomNavigationMenuView.class);
        BottomNavigationItemView[] mButtons = getField(bottomMenuView.getClass(), bottomMenuView, "mButtons", BottomNavigationItemView[].class);

        int marginTop = SizeUtils.dp2px(context, BADGE_MARGIN_TOP);
        int marginLeft = SizeUtils.dp2px(context, BADGE_MARGIN_LEFT);

        for (BottomNavigationItemView button : mButtons) {
            FrameLayout badge = (FrameLayout)View.inflate(context, badgeLayouId, null);
            badge.setVisibility(View.GONE);
            badge.setMinimumWidth(SizeUtils.dp2px(context, BADGE_MIN_WIDTH));

            LayoutParams layoutParam = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            layoutParam.gravity = Gravity.START;

            int oneItemAreaWidth = SizeUtils.dp2px(context, SizeUtils.getMeasuredWidth(button));

            layoutParam.setMargins(oneItemAreaWidth, marginTop, 0, 0);
            button.addView(badge, layoutParam);
        }
    }

    //设置底部导航栏消息数目
    public void setBadgeValue(int position, int count){
        BottomNavigationItemView menuItem = (BottomNavigationItemView)bottomMenuView.getChildAt(position);
        FrameLayout badge = (FrameLayout) menuItem.findViewById(R.id.bottom_bar_badge);
        TextView badgeText = (TextView)menuItem.findViewById(R.id.bottom_bar_badge_text);

        if(count > 0){
            badgeText.setText(count+"");
            badge.setVisibility(VISIBLE);
        }else{
            badge.setVisibility(GONE);
        }
    }

    public void SetNormalBottomNavigation() {

        BottomNavigationMenuView mMenuView = getField(getClass().getSuperclass(), this, "mMenuView", BottomNavigationMenuView.class);
        BottomNavigationItemView[] mButtons = getField(mMenuView.getClass(), mMenuView, "mButtons", BottomNavigationItemView[].class);
        for (BottomNavigationItemView button : mButtons) {
            TextView mLargeLabel = getField(button.getClass(), button, "mLargeLabel", TextView.class);
            TextView mSmallLabel = getField(button.getClass(), button, "mSmallLabel", TextView.class);

            setField(button.getClass(), button, "mShiftAmount", 0);
            setField(button.getClass(), button, "mScaleUpFactor", 1);
            setField(button.getClass(), button, "mScaleDownFactor", 1);

            mLargeLabel.setTextSize(TypedValue.COMPLEX_UNIT_PX, mSmallLabel.getTextSize());

            setField(button.getClass(), button, "mShiftingMode", false);
        }
        setField(mMenuView.getClass(), mMenuView, "mShiftingMode", false);

        mMenuView.updateMenuView();
    }


    public int getCurrentItem() {

        BottomNavigationMenuView mMenuView = getField(getClass().getSuperclass(), this, "mMenuView", BottomNavigationMenuView.class);
        BottomNavigationItemView[] mButtons = getField(mMenuView.getClass(), mMenuView, "mButtons", BottomNavigationItemView[].class);
        Menu menu = getMenu();
        for (int i = 0; i < mButtons.length; i++) {
            if (menu.getItem(i).isChecked()) {
                return i;
            }
        }
        return 0;
    }

    public void setCurrentItem(int item) {

        if (item < 0 || item >= getMaxItemCount()) {
            throw new ArrayIndexOutOfBoundsException("item is out of bounds, we expected 0 - "
                    + (getMaxItemCount() - 1) + ". Actually " + item);
        }

        BottomNavigationMenuView mMenuView = getField(getClass().getSuperclass(), this, "mMenuView", BottomNavigationMenuView.class);
        BottomNavigationItemView[] mButtons = getField(mMenuView.getClass(), mMenuView, "mButtons", BottomNavigationItemView[].class);
        View.OnClickListener mOnClickListener = getField(mMenuView.getClass(), mMenuView, "mOnClickListener", View.OnClickListener.class);
        mOnClickListener.onClick(mButtons[item]);
    }

    /**
     * get private filed in this specific object
     *
     * @param targetClass
     * @param instance    the filed owner
     * @param fieldName
     * @param fieldType   the field type
     * @param <T>
     * @return field if success, null otherwise.
     */
    private <T> T getField(Class targetClass, Object instance, String fieldName, Class<T> fieldType) {
        try {
            Field field = targetClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            return (T) field.get(instance);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * change the field value
     *
     * @param targetClass
     * @param instance      the filed owner
     * @param fieldName
     * @param value
     */
    private void setField(Class targetClass, Object instance, String fieldName, Object value) {
        try {
            Field field = targetClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(instance, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
