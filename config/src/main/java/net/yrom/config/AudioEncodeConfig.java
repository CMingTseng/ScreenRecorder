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

import android.media.MediaFormat;

/**
 * @author yrom
 * @version 2017/12/3
 */
public class AudioEncodeConfig extends EncodeConfig {
    private final static String TAG = "AudioEncodeConfig";

    final int bitRate;
    public final int sampleRate;
    public final int channelCount;
    final int profile;

    /**
     * @param codecName    selected codec name, maybe null
     * @param codeMIMEType video MIME type, cannot be null
     */
    public AudioEncodeConfig(String codecName, String codeMIMEType, int bitRate, int sampleRate, int channelCount, int profile) {
        super(codecName, codeMIMEType);
        this.bitRate = bitRate;
        this.sampleRate = sampleRate;
        this.channelCount = channelCount;
        this.profile = profile;
    }

    public MediaFormat toFormat() {
        MediaFormat format = MediaFormat.createAudioFormat(mMIMEType, sampleRate, channelCount);
        format.setInteger(MediaFormat.KEY_AAC_PROFILE, profile);
        format.setInteger(MediaFormat.KEY_BIT_RATE, bitRate);
        //format.setInteger(MediaFormat.KEY_MAX_INPUT_SIZE, 4096 * 4);
        return format;
    }

    @Override
    public String toString() {
        return "AudioEncodeConfig{" +
                "codecName='" + mCodecName + '\'' +
                ", mimeType='" + mMIMEType + '\'' +
                ", bitRate=" + bitRate +
                ", sampleRate=" + sampleRate +
                ", channelCount=" + channelCount +
                ", profile=" + profile +
                '}';
    }
}
