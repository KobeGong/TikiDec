package com.buddy.tiki.view.video;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import tv.danmaku.ijk.media.player.misc.IMediaDataSource;

public class FileMediaDataSource implements IMediaDataSource {
    private RandomAccessFile f3356a;
    private long f3357b = this.f3356a.length();

    public FileMediaDataSource(File file) throws IOException {
        this.f3356a = new RandomAccessFile(file, "r");
    }

    public int readAt(long position, byte[] buffer, int offset, int size) throws IOException {
        if (this.f3356a.getFilePointer() != position) {
            this.f3356a.seek(position);
        }
        if (size == 0) {
            return 0;
        }
        return this.f3356a.read(buffer, 0, size);
    }

    public long getSize() throws IOException {
        return this.f3357b;
    }

    public void close() throws IOException {
        this.f3357b = 0;
        this.f3356a.close();
        this.f3356a = null;
    }
}
