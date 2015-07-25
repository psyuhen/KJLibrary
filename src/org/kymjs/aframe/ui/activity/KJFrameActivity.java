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

import org.kymjs.aframe.ui.AnnotateUtil;
import org.kymjs.aframe.ui.I_BroadcastReg;
import org.kymjs.aframe.ui.KJActivityManager;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * Activity's framework,the developer shouldn't extends it
 * 
 * @author kymjs(kymjs123@gmail.com)
 * @version 1.5
 * @created 2014-3-1
 * @lastChange 2014-5-30
 */
public abstract class KJFrameActivity extends FragmentActivity implements
        OnClickListener, I_BroadcastReg, I_KJActivity {
    protected abstract void setContent();

    /** setContentView() */
    @Override
    public void setRootView() {
        setContent();
    }

    /** initialization widget */
    protected void initWidget() {}

    /** initialization data */
    protected void initData() {}

    /** initialization */
    @Override
    public void initialize() {
        setRootView();
        initWidget();
    }

    /** listened widget's click method */
    @Override
    public void widgetClick(View v) {}

    @Override
    public void onClick(View v) {
        widgetClick(v);
    }

    @Override
    public void registerBroadcast() {}
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KJActivityManager.create().addActivity(this);
        initialize();
        AnnotateUtil.initBindView(this);
        registerBroadcast();
        initData();
    }

    @Override
    public void unRegisterBroadcast() {}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterBroadcast();
        KJActivityManager.create().popupActivity(this);
    }
}
