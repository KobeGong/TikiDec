package com.buddy.tiki.model.constant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public interface RoomMessageType {
    public static final String FACE_DETECTION = "FaceDetection";

    @Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
    @Retention(RetentionPolicy.SOURCE)
    public @interface RoomMessageTypeProtocol {
    }
}
