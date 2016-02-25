package com.example.fragswitch.test;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.example.fragswitch.MainActivity;
import com.example.fragswitch.R;
import com.example.fragswitch.base.BaseFragment;
import com.example.fragswitch.base.FragSwitch;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class Test2Fragment extends BaseFragment implements OnClickListener{

	@ViewInject(R.id.iv_show)
	ImageView ivShow;
	
	@ViewInject(R.id.btn_pre)
	Button btnPre;
	@ViewInject(R.id.btn_next)
	Button btnNext;
	@Override
	public View initView() {
		view  = View.inflate(getActivity(), R.layout.test, null);
		ViewUtils.inject(this,view);
		
		btnPre.setOnClickListener(this);
		btnNext.setOnClickListener(this);
		return view;
	}

	@Override
	public void initData() {
		int backgroundResId = getArguments().getInt("backgroundResId");
		ivShow.setImageResource(backgroundResId);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btn_pre) {
			onPre();
		} else {
			onNext();
		}
	}

	private void onNext() {
		getArguments().putInt("backgroundResId", R.drawable.test_3);
		MainActivity activity = (MainActivity) getActivity();
		FragSwitch fragSwitch = activity.getSwitch();
		if (fragSwitch != null) {
			fragSwitch.onCheckedChanged(fragSwitch.getCurrentTab() + 1);
		}
	}

	private void onPre() {
		
	}

}
