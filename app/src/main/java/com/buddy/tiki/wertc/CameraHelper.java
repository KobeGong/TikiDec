package com.buddy.tiki.wertc;

import java.util.List;
import org.webrtc.CameraEnumerationAndroid.CaptureFormat.FramerateRange;

public class CameraHelper {
    public static FramerateRange getClosestSupportedFramerateRange(List<FramerateRange> supportedFramerates, int requestedFps) {
        int foundIndex = -1;
        for (int i = 0; i < supportedFramerates.size(); i++) {
            int min = ((FramerateRange) supportedFramerates.get(i)).min;
            int max = ((FramerateRange) supportedFramerates.get(i)).max;
            if (min <= requestedFps && max >= requestedFps) {
                foundIndex = i;
                break;
            }
        }
        if (foundIndex >= supportedFramerates.size() || foundIndex < 0) {
            return (FramerateRange) supportedFramerates.get(supportedFramerates.size() - 1);
        }
        return (FramerateRange) supportedFramerates.get(foundIndex);
    }
}
