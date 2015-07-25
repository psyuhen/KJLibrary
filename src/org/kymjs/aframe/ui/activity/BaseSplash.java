/*
 * Copyright (c) 2014-2015, kymjs 张涛 (kymjs123@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kymjs.aframe.ui.activity;

import org.kymjs.kjlibrary.R;

import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * 应用启动的欢迎界面模板
 * 
 * @author kymjs(kymjs123@gmail.com)
 */
public class BaseSplash extends BaseActivity {

	private FrameLayout splashView;

	/**
	 * 背景图
	 */
	protected ImageView backImgView;

	/**
	 * 广告图
	 */
	protected ImageView adImgView;

	/**
	 * logo图
	 */
	protected ImageView logoImgView;

	@Override
	protected void setContent() {
		setAllowFullScreen(true);
		setHiddenActionBar(true);
		setScreenOrientation(ScreenOrientation.VERTICAL);
		setContentView(R.layout.base_splash);

		splashView = (FrameLayout) findViewById(R.id.splash);
		backImgView = (ImageView) findViewById(R.id.splash_back);
		adImgView = (ImageView) findViewById(R.id.splash_ad);
		logoImgView = (ImageView) findViewById(R.id.splash_logo);
	}

	@Override
	protected void initWidget() {
		super.initWidget();
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
		// 监听动画过程
		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
				checkVersion();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				redirectTo();
			}
		});
		splashView.setAnimation(animation);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return true;
	}

	/**
	 * 跳转到...
	 */
	protected void redirectTo() {
	    finish();
	}

	/**
	 * 判断首次使用
	 */
	protected boolean firstsInstall() {
		return true;
	}

	/**
	 * 检查更新
	 */
	protected void checkVersion() {
	}
}
