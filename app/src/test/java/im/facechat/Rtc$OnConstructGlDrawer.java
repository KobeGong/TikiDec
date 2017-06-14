package im.facechat;

import org.webrtc.RendererCommon.GlDrawer;

public interface Rtc$OnConstructGlDrawer<G extends GlDrawer> {
    G newInstance();
}
