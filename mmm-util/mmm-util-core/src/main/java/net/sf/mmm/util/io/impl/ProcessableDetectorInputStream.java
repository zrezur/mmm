/* $Id$
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.util.io.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import net.sf.mmm.util.io.api.DetectorInputStream;
import net.sf.mmm.util.io.api.spi.DetectorStreamBuffer;
import net.sf.mmm.util.io.api.spi.DetectorStreamProcessor;
import net.sf.mmm.util.io.base.AbstractDetectorStreamProvider;
import net.sf.mmm.util.nls.api.NlsIllegalStateException;

/**
 * This is the implementation of the
 * {@link net.sf.mmm.util.io.api.DetectorOutputStream}.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.3
 */
public class ProcessableDetectorInputStream extends ProcessableDetectorStream implements
    DetectorInputStream {

  /** @see #getStream() */
  private final WrapperInputStream wrapperInputStream;

  /**
   * The constructor.
   * 
   * @param inputStream is the raw {@link InputStream} to {@link #getStream()
   *        warp}.
   * @param mutableMetadata is the initial {@link #getMutableMetadata() mutable
   *        metadata}.
   * @param provider is the
   *        {@link net.sf.mmm.util.io.api.DetectorStreamProvider} creating this
   *        instance.
   */
  public ProcessableDetectorInputStream(InputStream inputStream,
      Map<String, Object> mutableMetadata, AbstractDetectorStreamProvider provider) {

    super(mutableMetadata, null);
    this.wrapperInputStream = new WrapperInputStream(inputStream);
    initialize(provider, this.wrapperInputStream);
  }

  /**
   * {@inheritDoc}
   */
  public InputStream getStream() {

    return this.wrapperInputStream;
  }

  /**
   * This inner class is the actual wrapper stream.
   * 
   * @see ProcessableDetectorInputStream#getStream()
   */
  protected class WrapperInputStream extends InputStream implements DetectorStreamProcessor {

    /** The delegate adapted by this wrapper. */
    private final InputStream delegate;

    /** The destination buffer (end of chain) */
    private DetectorStreamBuffer targetBuffer;

    /**
     * <code>true</code> if end of detection-stream, <code>false</code>
     * otherwise.
     */
    private boolean endOfStream;

    /**
     * The constructor.
     * 
     * @param inputStream is the {@link InputStream} to adapt.
     */
    public WrapperInputStream(InputStream inputStream) {

      super();
      this.delegate = inputStream;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws IOException {

      this.delegate.close();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int read() throws IOException {

      fill(1);
      if (this.targetBuffer.hasNext()) {
        return this.targetBuffer.next();
      } else {
        return -1;
      }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int read(byte[] buffer, int offset, int length) throws IOException {

      fill(length);
      return this.targetBuffer.fill(buffer, offset, length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long skip(long n) throws IOException {

      if (n <= 0) {
        return 0;
      }
      if (this.targetBuffer == null) {
        fill(1);
      }
      long rest = n;
      while (rest > 0) {
        int bufferSize = this.targetBuffer.getBytesAvailable();
        if (bufferSize == 0) {
          // end of stream...
          // assert eos == true
          break;
        } else if (bufferSize < rest) {
          this.targetBuffer.skip(bufferSize);
          rest = rest - bufferSize;
        } else {
          this.targetBuffer.skip((int) rest);
          rest = 0;
          break;
        }
        fill(1);
      }
      return (n - rest);
    }

    /**
     * TODO: javadoc
     * 
     * @param requiredBufferLength
     * @throws IOException
     */
    protected void fill(int requiredBufferLength) throws IOException {

      int bufferLength = 0;
      if (this.targetBuffer != null) {
        bufferLength = this.targetBuffer.getBytesAvailable();
      }
      int readCount = 0;
      while (bufferLength < requiredBufferLength) {
        if (readCount > 5) {
          throw new NlsIllegalStateException();
        }
        byte[] buffer = getByteArrayPool().borrow();
        int length = this.delegate.read(buffer);
        readCount++;
        if (length == 0) {
          // strange stream API...
          length = this.delegate.read(buffer);
          if (length == 0) {
            throw new NlsIllegalStateException();
          }
        }
        if (length == -1) {
          processInternal(buffer, 0, 0, true);
          return;
        } else {
          processInternal(buffer, 0, length, false);
        }
        bufferLength = this.targetBuffer.getBytesAvailable();
      }
    }

    /**
     * {@inheritDoc}
     */
    public void process(DetectorStreamBuffer buffer, Map<String, Object> metadata, boolean eos)
        throws IOException {

      if (this.endOfStream) {
        throw new NlsIllegalStateException();
      }
      this.targetBuffer = buffer;
      this.endOfStream = eos;
    }

  }

}
