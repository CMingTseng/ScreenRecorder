/*
 * Copyright (c) 2017 Yrom Wang <http://www.yrom.net>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.yrom.encoder.video;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.util.Log;
import android.view.Surface;

import net.yrom.config.VideoEncodeConfig;
import net.yrom.encoder.BaseEncoder;
import net.yrom.encoder.BuildConfig;

import java.util.Objects;

/**
 * @author yrom
 * @version 2017/12/3
 */
public class VideoEncoder extends BaseEncoder {
    private final static String TAG = "VideoEncoder";
    private VideoEncodeConfig mConfig;
    private Surface mSurface;

    public VideoEncoder(VideoEncodeConfig config) {
        super(config.mCodecName);
        this.mConfig = config;
    }

    @Override
    protected void onEncoderConfigured(MediaCodec encoder) {
        mSurface = encoder.createInputSurface();
        if (BuildConfig.DEBUG) Log.e(TAG, "VideoEncoder create input surface: " + mSurface);
    }

    @Override
    protected MediaFormat createMediaFormat() {
        return mConfig.toFormat();
    }

    /**
     * @throws NullPointerException if prepare() not call
     */
    public Surface getInputSurface() {
        return Objects.requireNonNull(mSurface, "doesn't prepare()");
    }

    @Override
    public void release() {
        if (mSurface != null) {
            mSurface.release();
            mSurface = null;
        }
        super.release();
    }
}
