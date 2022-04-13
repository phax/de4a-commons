/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.de4a.iem.xml.de4a;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.wrapper.Wrapper;
import com.helger.jaxb.GenericJAXBMarshaller;

/**
 * Test class for class {@link DE4AMarshaller}.
 *
 * @author Philip Helger
 */
public final class DE4AMarshallerTest
{
  private static final Logger LOGGER = LoggerFactory.getLogger (DE4AMarshallerTest.class);
  private static final String BASE_PATH = "src/test/resources/de4a/";

  @SuppressWarnings ("unused")
  private static <T> void _receiveViaHttp (@Nonnull final GenericJAXBMarshaller <T> aMarshaller, @Nonnull final File aFile) throws Exception
  {
    final Wrapper <Exception> aExWrapper = new Wrapper <> ();
    aMarshaller.readExceptionCallbacks ().removeAll ();
    aMarshaller.readExceptionCallbacks ().add (ex -> aExWrapper.set (ex));
    aMarshaller.readExceptionCallbacks ().add (ex -> LOGGER.error ("Failed to parse XML", ex));

    final T aRead = aMarshaller.read (aFile);
    if (aRead == null)
    {
      if (aExWrapper.isSet ())
        throw aExWrapper.get ();
      throw new Exception ("HTTP 400");
    }
  }

  private static <T> void _testReadWrite (@Nonnull final GenericJAXBMarshaller <T> aMarshaller, @Nonnull final File aFile)
  {
    assertTrue ("Test file does not exists " + aFile.getAbsolutePath (), aFile.exists ());

    if (false)
    {
      aMarshaller.readExceptionCallbacks ().set (ex -> LOGGER.error ("Read error", ex));
      aMarshaller.writeExceptionCallbacks ().set (ex -> LOGGER.error ("Write error", ex));
    }

    final T aRead = aMarshaller.read (aFile);
    assertNotNull ("Failed to read " + aFile.getAbsolutePath (), aRead);

    final byte [] aBytes = aMarshaller.getAsBytes (aRead);
    assertNotNull ("Failed to re-write " + aFile.getAbsolutePath (), aBytes);

    if (false)
    {
      aMarshaller.setFormattedOutput (true);
      LOGGER.info (aMarshaller.getAsString (aRead));
    }
  }

  // TODO fix
  // @Test
  // public void testDE_USI ()
  // {
  // // Request
  // _testReadWrite (DE4ACoreMarshaller.deUsiRequestMarshaller
  // (EDE4ACanonicalEvidenceType.T42_COMPANY_INFO_V06),
  // new File (BASE_PATH + "core/t4.2/0.6/DE1-USI-request-T42.xml"));
  // }
  //
  // @Test
  // public void testDO_IM ()
  // {
  // // Response
  // _testReadWrite (DE4ACoreMarshaller.doImResponseMarshaller
  // (EDE4ACanonicalEvidenceType.T42_COMPANY_INFO_V06),
  // new File (BASE_PATH + "core/t4.2/0.6/DO1-IM-response-T42.xml"));
  // }
  //
  // @Test
  // public void testDR_IM ()
  // {
  // // Response
  // _testReadWrite (DE4ACoreMarshaller.drImResponseMarshaller
  // (EDE4ACanonicalEvidenceType.T42_COMPANY_INFO_V06),
  // new File (BASE_PATH + "core/t4.2/0.6/DR1-IM-response-T42.xml"));
  // }
  //
  // @Test
  // public void testDT_USI ()
  // {
  // // Request
  // _testReadWrite (DE4ACoreMarshaller.dtUsiRequestMarshaller
  // (EDE4ACanonicalEvidenceType.T42_COMPANY_INFO_V06),
  // new File (BASE_PATH + "core/t4.2/0.6/DT1-USI-request-T42.xml"));
  // }
}