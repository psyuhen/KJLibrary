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
package org.kymjs.aframe.ui.fragment;

import org.kymjs.aframe.ui.AnnotateUtil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

/**
 * Fragment's framework,the developer shouldn't extends it
 * 
 * @author kymjs(kymjs123@gmail.com)
 * @version 1.5
 * @created 2014-3-1
 * @lastChange 2014-5-30
 */
public abstract class KJFrameFragment extends Fragment implements
        OnClickListener {

	private View view;
    protected abstract View inflaterView(LayoutInflater inflater,
            ViewGroup container, Bundle bundle);

    /**
     * initialization widget, you should look like parentView.findviewbyid(id);
     * call method
     * 
     * @param parentView
     */
    protected void initWidget(View parentView) {}

    /** initialization data */
    protected void initData() {}

    /** widget click method */
    protected void widgetClick(View v) {}

    @Override
    public void onClick(View v) {
        widgetClick(v);
    }

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (view == null) {
			view = inflaterView(inflater, container, savedInstanceState);
			AnnotateUtil.initBindView(this, view);
			initWidget(view);
			initData();
		}

		ViewGroup p = (ViewGroup) view.getParent();
		if (p != null) {
			p.removeAllViewsInLayout();
		}
		return view;
	}
}
