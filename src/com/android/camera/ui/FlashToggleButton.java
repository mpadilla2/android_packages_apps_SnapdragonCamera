/*
 * Copyright (c) 2016, The Linux Foundation. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above
 *       copyright notice, this list of conditions and the following
 *       disclaimer in the documentation and/or other materials provided
 *       with the distribution.
 *     * Neither the name of The Linux Foundation nor the names of its
 *       contributors may be used to endorse or promote products derived
 *       from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN
 * IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.android.camera.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.camera.SettingsManager;

import org.codeaurora.snapcam.R;

public class FlashToggleButton extends RotateImageView {
    private SettingsManager mSettingsManager;
    private int[] iconResId = {R.drawable.flash_off, R.drawable.flash};
    private int mIndex;

    public FlashToggleButton(Context context) {
        super(context);
    }

    public FlashToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void init() {
        mSettingsManager = SettingsManager.getInstance();
        mIndex = mSettingsManager.getValueIndex(SettingsManager.KEY_FLASH_MODE);
        if (mIndex == -1) {
            setVisibility(GONE);
            return;
        } else {
            setVisibility(VISIBLE);
        }

        update();
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mIndex = (mIndex + 1) % 2;
                mSettingsManager.setValueIndex(SettingsManager.KEY_FLASH_MODE, mIndex);
                update();
            }
        });
    }

    private void update() {
        setImageResource(iconResId[mIndex]);
    }
}
