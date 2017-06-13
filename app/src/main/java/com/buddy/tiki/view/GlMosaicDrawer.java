package com.buddy.tiki.view;

import android.opengl.GLES20;
import com.buddy.tiki.log.TikiLog;
import java.nio.FloatBuffer;
import java.util.IdentityHashMap;
import java.util.Map;
import org.webrtc.GlShader;
import org.webrtc.GlUtil;
import org.webrtc.RendererCommon.GlDrawer;

public class GlMosaicDrawer implements GlDrawer {
    private static final TikiLog f2660a = TikiLog.getInstance(GlMosaicDrawer.class.getSimpleName());
    private static final FloatBuffer f2661b = GlUtil.createFloatBuffer(new float[]{-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f});
    private static final FloatBuffer f2662c = GlUtil.createFloatBuffer(new float[]{0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f});
    private final Map<String, Shader> f2663d = new IdentityHashMap();
    private int f2664e = 0;
    private int f2665f = 0;
    private int f2666g = 0;
    private int f2667h = 0;
    private int f2668i = 0;
    private int f2669j = 0;
    private float f2670k = 0.0234375f;
    private float f2671l = 0.041666668f;

    private static class Shader {
        public final GlShader f2658a;
        public final int f2659b = this.f2658a.getUniformLocation("texMatrix");

        public Shader(String fragmentShader) {
            this.f2658a = new GlShader("varying vec2 interp_tc;\nattribute vec4 in_pos;\nattribute vec4 in_tc;\n\nuniform mat4 texMatrix;\n\nvoid main() {\n    gl_Position = in_pos;\n    interp_tc = (texMatrix * in_tc).xy;\n}\n", fragmentShader);
        }
    }

    public void drawOes(int oesTextureId, float[] texMatrix, int frameWidth, int frameHeight, int viewportX, int viewportY, int viewportWidth, int viewportHeight) {
        m1703a("#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 interp_tc;\nuniform samplerExternalOES oes_tex;\n\nuniform float pixel_width;\nuniform float pixel_height;\n\nvec2 getMappedXY(vec2 xy) {\n    if(pixel_width > 0.0 && pixel_height > 0.0) {\n        float x = xy.x;\n        float y = xy.y;\n        x = floor(x / pixel_width) * pixel_width;\n        y = floor(y / pixel_height) * pixel_height;\n        return vec2(x, y);\n    }\n    return xy;\n}\n\nvoid main() {\n  vec2 newTc = getMappedXY(interp_tc);\n  gl_FragColor = texture2D(oes_tex, newTc);\n}\n", texMatrix);
        GLES20.glUniform1f(this.f2666g, this.f2670k);
        GLES20.glUniform1f(this.f2667h, this.f2671l);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(36197, oesTextureId);
        m1702a(viewportX, viewportY, viewportWidth, viewportHeight);
        GLES20.glBindTexture(36197, 0);
    }

    public void drawRgb(int textureId, float[] texMatrix, int frameWidth, int frameHeight, int viewportX, int viewportY, int viewportWidth, int viewportHeight) {
        m1703a("precision mediump float;\nvarying vec2 interp_tc;\n\nuniform sampler2D rgb_tex;\n\nuniform float pixel_width;\nuniform float pixel_height;\n\nvec2 getMappedXY(vec2 xy) {\n    if(pixel_width > 0.0 && pixel_height > 0.0) {\n        float x = xy.x;\n        float y = xy.y;\n        x = floor(x / pixel_width) * pixel_width;\n        y = floor(y / pixel_height) * pixel_height;\n        return vec2(x, y);\n    }\n    return xy;\n}\n\nvoid main() {\n  vec2 newTc = getMappedXY(interp_tc);\n  gl_FragColor = texture2D(rgb_tex, newTc);\n}\n", texMatrix);
        GLES20.glUniform1f(this.f2668i, this.f2670k);
        GLES20.glUniform1f(this.f2669j, this.f2671l);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, textureId);
        m1702a(viewportX, viewportY, viewportWidth, viewportHeight);
        GLES20.glBindTexture(3553, 0);
    }

    public void drawYuv(int[] yuvTextures, float[] texMatrix, int frameWidth, int frameHeight, int viewportX, int viewportY, int viewportWidth, int viewportHeight) {
        int i;
        m1703a("precision mediump float;\nvarying vec2 interp_tc;\n\nuniform sampler2D y_tex;\nuniform sampler2D u_tex;\nuniform sampler2D v_tex;\nuniform float pixel_width;\nuniform float pixel_height;\n\nvec2 getMappedXY(vec2 xy) {\n    if(pixel_width > 0.0 && pixel_height > 0.0) {\n        float x = xy.x;\n        float y = xy.y;\n        x = floor(x / pixel_width) * pixel_width;\n        y = floor(y / pixel_height) * pixel_height;\n        return vec2(x, y);\n    }\n    return xy;\n}\n\nvoid main() {\n  vec2 newTc = getMappedXY(interp_tc);\n  float y = texture2D(y_tex, newTc).r;\n  float u = texture2D(u_tex, newTc).r - 0.5;\n  float v = texture2D(v_tex, newTc).r - 0.5;\n  gl_FragColor = vec4(y + 1.403 * v, y - 0.344 * u - 0.714 * v, y + 1.77 * u, 1);\n}", texMatrix);
        GLES20.glUniform1f(this.f2664e, this.f2670k);
        GLES20.glUniform1f(this.f2665f, this.f2671l);
        for (i = 0; i < 3; i++) {
            GLES20.glActiveTexture(33984 + i);
            GLES20.glBindTexture(3553, yuvTextures[i]);
        }
        m1702a(viewportX, viewportY, viewportWidth, viewportHeight);
        for (i = 0; i < 3; i++) {
            GLES20.glActiveTexture(33984 + i);
            GLES20.glBindTexture(3553, 0);
        }
    }

    private void m1702a(int x, int y, int width, int height) {
        GLES20.glViewport(x, y, width, height);
        GLES20.glDrawArrays(5, 0, 4);
    }

    private void m1703a(String fragmentShader, float[] texMatrix) {
        Shader shader;
        if (this.f2663d.containsKey(fragmentShader)) {
            shader = (Shader) this.f2663d.get(fragmentShader);
        } else {
            shader = new Shader(fragmentShader);
            this.f2663d.put(fragmentShader, shader);
            shader.f2658a.useProgram();
            if (fragmentShader == "precision mediump float;\nvarying vec2 interp_tc;\n\nuniform sampler2D y_tex;\nuniform sampler2D u_tex;\nuniform sampler2D v_tex;\nuniform float pixel_width;\nuniform float pixel_height;\n\nvec2 getMappedXY(vec2 xy) {\n    if(pixel_width > 0.0 && pixel_height > 0.0) {\n        float x = xy.x;\n        float y = xy.y;\n        x = floor(x / pixel_width) * pixel_width;\n        y = floor(y / pixel_height) * pixel_height;\n        return vec2(x, y);\n    }\n    return xy;\n}\n\nvoid main() {\n  vec2 newTc = getMappedXY(interp_tc);\n  float y = texture2D(y_tex, newTc).r;\n  float u = texture2D(u_tex, newTc).r - 0.5;\n  float v = texture2D(v_tex, newTc).r - 0.5;\n  gl_FragColor = vec4(y + 1.403 * v, y - 0.344 * u - 0.714 * v, y + 1.77 * u, 1);\n}") {
                GLES20.glUniform1i(shader.f2658a.getUniformLocation("y_tex"), 0);
                GLES20.glUniform1i(shader.f2658a.getUniformLocation("u_tex"), 1);
                GLES20.glUniform1i(shader.f2658a.getUniformLocation("v_tex"), 2);
                this.f2664e = shader.f2658a.getUniformLocation("pixel_width");
                this.f2665f = shader.f2658a.getUniformLocation("pixel_height");
                GLES20.glUniform1f(this.f2664e, 0.0234375f);
                GLES20.glUniform1f(this.f2665f, 0.041666668f);
            } else if (fragmentShader == "precision mediump float;\nvarying vec2 interp_tc;\n\nuniform sampler2D rgb_tex;\n\nuniform float pixel_width;\nuniform float pixel_height;\n\nvec2 getMappedXY(vec2 xy) {\n    if(pixel_width > 0.0 && pixel_height > 0.0) {\n        float x = xy.x;\n        float y = xy.y;\n        x = floor(x / pixel_width) * pixel_width;\n        y = floor(y / pixel_height) * pixel_height;\n        return vec2(x, y);\n    }\n    return xy;\n}\n\nvoid main() {\n  vec2 newTc = getMappedXY(interp_tc);\n  gl_FragColor = texture2D(rgb_tex, newTc);\n}\n") {
                GLES20.glUniform1i(shader.f2658a.getUniformLocation("rgb_tex"), 0);
                this.f2668i = shader.f2658a.getUniformLocation("pixel_width");
                this.f2669j = shader.f2658a.getUniformLocation("pixel_height");
                GLES20.glUniform1f(this.f2668i, 0.0234375f);
                GLES20.glUniform1f(this.f2669j, 0.041666668f);
            } else if (fragmentShader != "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 interp_tc;\nuniform samplerExternalOES oes_tex;\n\nuniform float pixel_width;\nuniform float pixel_height;\n\nvec2 getMappedXY(vec2 xy) {\n    if(pixel_width > 0.0 && pixel_height > 0.0) {\n        float x = xy.x;\n        float y = xy.y;\n        x = floor(x / pixel_width) * pixel_width;\n        y = floor(y / pixel_height) * pixel_height;\n        return vec2(x, y);\n    }\n    return xy;\n}\n\nvoid main() {\n  vec2 newTc = getMappedXY(interp_tc);\n  gl_FragColor = texture2D(oes_tex, newTc);\n}\n") {
                throw new IllegalStateException("Unknown fragment shader: " + fragmentShader);
            } else {
                GLES20.glUniform1i(shader.f2658a.getUniformLocation("oes_tex"), 0);
                this.f2666g = shader.f2658a.getUniformLocation("pixel_width");
                this.f2667h = shader.f2658a.getUniformLocation("pixel_height");
                GLES20.glUniform1f(this.f2666g, 0.0234375f);
                GLES20.glUniform1f(this.f2667h, 0.041666668f);
            }
            GlUtil.checkNoGLES2Error("Initialize fragment shader uniform values.");
            shader.f2658a.setVertexAttribArray("in_pos", 2, f2661b);
            shader.f2658a.setVertexAttribArray("in_tc", 2, f2662c);
        }
        shader.f2658a.useProgram();
        GLES20.glUniformMatrix4fv(shader.f2659b, 1, false, texMatrix, 0);
    }

    public void release() {
        for (Shader shader : this.f2663d.values()) {
            shader.f2658a.release();
        }
        this.f2663d.clear();
    }

    public void setMosaicSize(float width, float height) {
        f2660a.m261d("setMosaicSize:w:" + width + " h:" + height);
        this.f2670k = width;
        this.f2671l = height;
    }
}
