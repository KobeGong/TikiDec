package com.buddy.tiki.request.base;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

public class ProgressRequestBody extends RequestBody {
    protected RequestBody f896a;
    protected Listener f897b;
    protected CountingSink f898c;

    protected final class CountingSink extends ForwardingSink {
        final /* synthetic */ ProgressRequestBody f891a;
        private long f892b = 0;
        private long f893c = 0;
        private long f894d;
        private long f895e;

        public CountingSink(ProgressRequestBody this$0, Sink delegate) {
            this.f891a = this$0;
            super(delegate);
        }

        public void write(Buffer source, long byteCount) throws IOException {
            super.write(source, byteCount);
            if (this.f893c <= 0) {
                this.f893c = this.f891a.contentLength();
            }
            this.f892b += byteCount;
            long curTime = System.currentTimeMillis();
            if (curTime - this.f894d >= 100 || this.f892b == this.f893c) {
                long diffTime = (curTime - this.f894d) / 1000;
                if (diffTime == 0) {
                    diffTime++;
                }
                long networkSpeed = (this.f892b - this.f895e) / diffTime;
                if (this.f891a.f897b != null) {
                    this.f891a.f897b.onRequestProgress(this.f892b, this.f893c, networkSpeed);
                }
                this.f894d = System.currentTimeMillis();
                this.f895e = this.f892b;
            }
        }
    }

    public interface Listener {
        void onRequestProgress(long j, long j2, long j3);
    }

    public ProgressRequestBody(RequestBody delegate) {
        this.f896a = delegate;
    }

    public ProgressRequestBody(RequestBody delegate, Listener listener) {
        this.f896a = delegate;
        this.f897b = listener;
    }

    public void setListener(Listener listener) {
        this.f897b = listener;
    }

    public MediaType contentType() {
        return this.f896a.contentType();
    }

    public long contentLength() {
        try {
            return this.f896a.contentLength();
        } catch (IOException e) {
            return -1;
        }
    }

    public void writeTo(BufferedSink sink) throws IOException {
        this.f898c = new CountingSink(this, sink);
        BufferedSink bufferedSink = Okio.buffer(this.f898c);
        this.f896a.writeTo(bufferedSink);
        bufferedSink.flush();
    }
}
