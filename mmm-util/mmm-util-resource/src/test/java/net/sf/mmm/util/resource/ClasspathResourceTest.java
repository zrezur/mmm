/* $Id$ */
package net.sf.mmm.util.resource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * This is the test case for the class {@link ClasspathUtil}.
 *
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 */
@SuppressWarnings("all")
public class ClasspathResourceTest extends TestCase {

  /**
   * The constructor
   */
  public ClasspathResourceTest() {

    super();
  }
  
  public void verifyResource(Resource resource) throws Exception {
    assertTrue(resource.isAvailable());
    assertEquals(41, resource.getSize());
    InputStream in = in = resource.openStream();
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(in));
      String line = reader.readLine();
      assertEquals("This is a resource loaded from classpath.", line);
      line = reader.readLine();
      assertNull(line);
    } finally {
      in.close();
    }
  }
  
  @Test
  public void testClasspathResource() throws Exception {
    verifyResource(new ClasspathResource(ClasspathResource.class, ".txt"));
    verifyResource(new ClasspathResource(ClasspathResource.class.getPackage(), "ClasspathResource.txt"));
  }
  
}
