package net.yrom.config;

import java.util.Objects;

public class EncodeConfig {
    public String mCodecName;
    public String mMIMEType;

    /**
     * @param codecName    selected codec name, maybe null
     * @param codeMIMEType video MIME type, cannot be null
     */
    public EncodeConfig(String codecName, String codeMIMEType) {
        mCodecName = codecName;
        mMIMEType = Objects.requireNonNull(codeMIMEType);
    }
}
