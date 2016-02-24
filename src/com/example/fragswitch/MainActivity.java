package com.example.fragswitch;

import java.util.ArrayList;

import com.example.fragswitch.base.BaseActivity;
import com.example.fragswitch.base.BaseFragment;
import com.example.fragswitch.base.FragSwitch;
import com.example.fragswitch.test.Test1Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends BaseActivity {

	private Bundle bundle;
	private ArrayList<BaseFragment> fragments;
	private FragSwitch fragSwitch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.base);
		initFragment();
	}

	public void initFragment() {
		fragments = new ArrayList<BaseFragment>();
		Test1Fragment test0Fragment = new Test1Fragment();
		Bundle bundle0 = new Bundle();
		bundle0.putInt("index", 0);
		test0Fragment.setArguments(bundle0);
		
		
		Test1Fragment test1Fragment = new Test1Fragment();
		Bundle bundle1 = new Bundle();
		bundle1.putInt("index", 1);
		test0Fragment.setArguments(bundle0);
		
		
		Test1Fragment test2Fragment = new Test1Fragment();
		
		Bundle bundle2 = new Bundle();
		bundle2.putInt("index", 2);
		test0Fragment.setArguments(bundle0);
		
		Test1Fragment test3Fragment = new Test1Fragment();
		
		Bundle bundle3 = new Bundle();
		bundle3.putInt("index", 3);
		test0Fragment.setArguments(bundle0);
		
		Test1Fragment test4Fragment = new Test1Fragment();
		
		Bundle bundle4 = new Bundle();
		bundle4.putInt("index", 4);
		test0Fragment.setArguments(bundle0);
		
		
		fragments.add(test0Fragment);
		fragments.add(test1Fragment);
		fragments.add(test2Fragment);
		fragments.add(test3Fragment);
		fragments.add(test4Fragment);
		
		fragSwitch = new FragSwitch(this,
				R.id.fl_content, fragments);
	}
	
	public void setBundle(Bundle bundle){
		this.bundle = bundle;
	}
	public Bundle getBundle(){
		return this.bundle;
	}
	
	public void showTab(int index){
		BaseFragment baseFragment = fragments.get(index);
		baseFragment.setArguments(this.bundle);
		fragSwitch.onCheckedChanged(index);
	}
	
	@Override
	public void onBackPressed() {
		if(fragSwitch.getCurrentTab()>0){
			fragSwitch.onCheckedChanged(fragSwitch.getCurrentTab()-1);
		}else{
			super.onBackPressed();
		}

	}
}
