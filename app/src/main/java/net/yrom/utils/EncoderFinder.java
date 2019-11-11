package net.yrom.utils;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

public class EncoderFinder extends AsyncTask<String, Void, MediaCodecInfo[]> {
    private final static String TAG = "EncoderFinder";

    public interface EncoderFinderCallback {
        void onResult(MediaCodecInfo[] infos);
    }

    private EncoderFinderCallback func;

    public EncoderFinder(EncoderFinderCallback func) {
        this.func = func;
    }

    @Override
    protected MediaCodecInfo[] doInBackground(String... mimeTypes) {
        return findEncodersByType(mimeTypes[0]);
    }

    @Override
    protected void onPostExecute(MediaCodecInfo[] mediaCodecInfos) {
        func.onResult(mediaCodecInfos);
    }

    /**
     * Find an encoder supported specified MIME type
     *
     * @return Returns empty array if not found any encoder supported specified MIME type
     */
    private MediaCodecInfo[] findEncodersByType(String mimeType) {
        final MediaCodecList codecList = new MediaCodecList(MediaCodecList.ALL_CODECS);
        final List<MediaCodecInfo> infos = new ArrayList<>();
        for (MediaCodecInfo info : codecList.getCodecInfos()) {
            if (!info.isEncoder()) {
                continue;
            }
            try {
                MediaCodecInfo.CodecCapabilities cap = info.getCapabilitiesForType(mimeType);
                if (cap == null) continue;
            } catch (IllegalArgumentException e) {
                // unsupported
                continue;
            }
            infos.add(info);
        }
        return infos.toArray(new MediaCodecInfo[infos.size()]);
    }
}

