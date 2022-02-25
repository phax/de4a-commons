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

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;

import eu.de4a.iem.jaxb.common.types.ErrorType;
import eu.de4a.iem.jaxb.common.types.ResponseErrorType;

/**
 * Helper class to create response messages, eventually based on request
 * messages.
 *
 * @author Philip Helger
 */
public final class DE4AResponseDocumentHelper
{
  private DE4AResponseDocumentHelper ()
  {}

  /**
   * Create a single {@link ErrorType} instance. Must have code and text.
   *
   * @param sCode
   *        Error code. May neither be <code>null</code> nor empty.
   * @param sText
   *        Error text. May neither be <code>null</code> nor empty.
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static ErrorType createError (@Nonnull @Nonempty final String sCode, @Nonnull @Nonempty final String sText)
  {
    ValueEnforcer.notEmpty (sCode, "Code");
    ValueEnforcer.notEmpty (sText, "Text");

    final ErrorType ret = new ErrorType ();
    ret.setCode (sCode);
    ret.setText (sText);
    return ret;
  }

  /**
   * Create a {@link ResponseErrorType} with the "Ack" state set only.
   * Eventually present errors must be added by the caller.
   *
   * @param bSuccess
   *        <code>true</code> in case of success, <code>false</code> in case of
   *        error.
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static ResponseErrorType createResponseError (final boolean bSuccess)
  {
    final ResponseErrorType ret = new ResponseErrorType ();
    ret.setAck (bSuccess);
    return ret;
  }
}
