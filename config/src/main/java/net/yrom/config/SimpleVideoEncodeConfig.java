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

package net.yrom.config;

import android.media.MediaCodecInfo;
import android.media.MediaFormat;

/**
 * @author yrom
 * @version 2017/12/3
 */
public class SimpleVideoEncodeConfig extends EncodeConfig {
    private final static String TAG = "SimpleVideoEncodeConfig";
    public int width;
    public int height;
    protected int bitrate;
    protected int framerate;
    protected int iframeInterval;
    private static final int FPS = 30;

    public SimpleVideoEncodeConfig(String codecName, String codeMIMEType, int width, int height, int bitrate) {
        super(codecName, codeMIMEType);
        this.width = width;
        this.height = height;
        this.bitrate = bitrate;
        this.framerate = FPS;
        this.iframeInterval = 1;
    }

    public MediaFormat toMediaFormat() {
        final MediaFormat mediaformat = MediaFormat.createVideoFormat(mMIMEType, width, height);
        switch (mMIMEType) {
            case MediaFormat.MIMETYPE_VIDEO_AVC:
                mediaformat.setInteger(MediaFormat.KEY_COLOR_FORMAT, MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface);
                break;
            case MediaFormat.MIMETYPE_VIDEO_VP8:
                mediaformat.setInteger(MediaFormat.KEY_COLOR_FORMAT, MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV420Flexible);
                break;
        }
        mediaformat.setInteger(MediaFormat.KEY_BIT_RATE, bitrate);
        mediaformat.setInteger(MediaFormat.KEY_FRAME_RATE, framerate);
        mediaformat.setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, iframeInterval);
        mediaformat.setInteger(MediaFormat.KEY_CHANNEL_COUNT, 0);
        // maybe useful
        // format.setInteger(MediaFormat.KEY_REPEAT_PREVIOUS_FRAME_AFTER, 10_000_000);
        return mediaformat;
    }

    @Override
    public String toString() {
        return "SimpleVideoEncodeConfig{" +
                "width=" + width +
                ", height=" + height +
                ", bitrate=" + bitrate +
                ", framerate=" + framerate +
                ", iframeInterval=" + iframeInterval +
                ", codecName='" + mCodecName + '\'' +
                ", mimeType='" + mMIMEType +
                '}';
    }
}
