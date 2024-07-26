package com.bigoat.android.view.sample.my;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import com.bigoat.android.arch.BaseActivity;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.KeyboardUtils;

public abstract class MyActivity<Binding extends ViewDataBinding, ViewModel extends MyViewModel> extends BaseActivity<Binding, ViewModel>{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        BarUtils.setNavBarVisibility(this, false);
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT);
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                KeyboardUtils.hideSoftInput(this);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if ((v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationOnScreen(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            return !(event.getRawX() > left && event.getRawX() < right
                    && event.getRawY() > top && event.getRawY() < bottom);
        }
        return false;
    }

    public void finish(View view) {
        finish();
    }
}
