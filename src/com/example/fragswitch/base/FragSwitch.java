package com.example.fragswitch.base;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.example.fragswitch.R;

/**
 * Created with IntelliJ IDEA. Author: wangjie email:tiantian.china.2@gmail.com
 * Date: 13-10-10 Time: 上午9:25
 */
public class FragSwitch {
	private List<BaseFragment> fragments; // 一个tab页面对应一个Fragment
	private FragmentActivity fragmentActivity; // Fragment所属的Activity
	private int fragmentContentId; // Activity中所要被替换的区域的id

	private int currentTab = -1; // 当前Tab页面索引

	private OnRgsExtraCheckedChangedListener onRgsExtraCheckedChangedListener; // 用于让调用者在切换tab时候增加新的功能


	public FragSwitch(FragmentActivity fragmentActivity,
			ArrayList<BaseFragment> fragments, int fragmentContentId, int index) {
		this(fragmentActivity, fragmentContentId,fragments);
		currentTab = index;
		
		// 默认显示第一页
		BaseFragment baseFragment = fragments.get(index);
		if (baseFragment.isAdded()) {
			// fragment.onStart(); // 启动目标tab的onStart()
			baseFragment.onResume(); // 启动目标tab的onResume()
		} else {
			FragmentTransaction ft = fragmentActivity.getSupportFragmentManager()
					.beginTransaction();
			ft.add(fragmentContentId, fragments.get(index));
			ft.commit();
		}

	}

	public FragSwitch(FragmentActivity fragmentActivity,
			int fragmentContentId,ArrayList<BaseFragment> fragments) {
		this.fragments = fragments;
		this.fragmentActivity = fragmentActivity;
		this.fragmentContentId = fragmentContentId;

	}

	public void onCheckedChanged(int index) {
		if (index == currentTab) {
			return;
		}
		Fragment fragment = fragments.get(index);
		if(currentTab!=-1)
			getCurrentFragment().onPause(); // 暂停当前tab

		if (fragment.isAdded()) {
			fragment.onResume(); // 启动目标tab的onResume()
		} else {
			FragmentTransaction ft = obtainFragmentTransaction(index);
			ft.add(fragmentContentId, fragment);
			ft.commit();
		}

		showTab(index); // 显示目标tab

		// 如果设置了切换tab额外功能功能接口

	}

	/**
	 * 切换tab
	 * 
	 * @param idx
	 */
	private void showTab(int idx) {

		for (int i = 0; i < fragments.size(); i++) {
			Fragment fragment = fragments.get(i);
			FragmentTransaction ft = obtainFragmentTransaction(i);

			if (idx == i) {
				ft.show(fragment);
			} else {
				ft.hide(fragment);
			}
			ft.commit();
		}

		if (this.onRgsExtraCheckedChangedListener != null) {
			onRgsExtraCheckedChangedListener.OnRgsExtraCheckedChanged(idx,
					getCurrentFragment());
		}
		currentTab = idx; // 更新目标tab为当前tab
	}

	/**
	 * 获取一个带动画的FragmentTransaction
	 * 
	 * @param index
	 * @return
	 */
	private FragmentTransaction obtainFragmentTransaction(int index) {
		FragmentTransaction ft = fragmentActivity.getSupportFragmentManager()
				.beginTransaction();
		// 设置切换动画
		if (index > currentTab) {
			ft.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out);
		} else {
			ft.setCustomAnimations(R.anim.slide_right_in,
					R.anim.slide_right_out);
		}

		return ft;
	}

	public int getCurrentTab() {
		return currentTab;
	}

	public Fragment getCurrentFragment() {
		return fragments.get(currentTab);
	}

	public OnRgsExtraCheckedChangedListener getOnRgsExtraCheckedChangedListener() {
		return onRgsExtraCheckedChangedListener;
	}

	public void setOnRgsExtraCheckedChangedListener(
			OnRgsExtraCheckedChangedListener onRgsExtraCheckedChangedListener) {
		this.onRgsExtraCheckedChangedListener = onRgsExtraCheckedChangedListener;
	}

	/**
	 * 切换tab额外功能功能接口
	 */
	public interface OnRgsExtraCheckedChangedListener {
		public void OnRgsExtraCheckedChanged(int index, Fragment fragment);
	}

}