/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.util.value.api;

/**
 * This is the interface for a converter that {@link #convert(Object, Object, Class) converts} a value of the
 * type &lt;SOURCE&gt; to the type &lt;TARGET&gt;.<br>
 * <b>ATTENTION:</b><br>
 * An implementation of this interface should be stateless and thread-safe.
 * 
 * @param <SOURCE> is the generic type of the object to be converted.
 * @param <TARGET> is the generic type of the result of the conversion.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public interface SimpleValueConverter<SOURCE, TARGET> {

  /**
   * This method converts the given <code>pojo</code> to the &lt;TARGET&gt;-type.
   * 
   * @param <T> is the generic type of <code>targetClass</code>.
   * @param value is the value to convert.
   * @param valueSource describes the source of the value. This may be the filename where the value was read
   *        from, an XPath where the value was located in an XML document, etc. It is used in exceptions
   *        thrown if something goes wrong. This will help to find the problem easier.
   * @param targetClass is the type to convert the <code>value</code> to.
   * @return the converted <code>value</code> or <code>null</code> if the conversion is NOT possible. The
   *         returned value has to be an {@link Class#isInstance(Object) instance} of the given
   *         <code>targetType</code>.
   * @throws ValueException if the conversion failed (e.g. the given <code>value</code> is illegal for the
   *         given <code>targetClass</code>).
   */
  <T extends TARGET> T convert(SOURCE value, Object valueSource, Class<T> targetClass) throws ValueException;

}
