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
package eu.de4a.iem.slot;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.regrep.rim.SlotType;
import com.helger.regrep.slot.ISlotProvider;
import com.helger.regrep.slot.SlotBuilder;

import eu.de4a.iem.model.PersonPojo;
import eu.de4a.iem.xml.cv.PersonMarshaller;

/**
 * DataSubject "NaturalPerson" slot
 *
 * @author Philip Helger
 */
public class SlotDataSubjectNaturalPerson implements ISlotProvider
{
  public static final String NAME = "NaturalPerson";

  private final PersonPojo m_aNaturalPerson;

  public SlotDataSubjectNaturalPerson (@Nonnull final PersonPojo aNaturalPerson)
  {
    ValueEnforcer.notNull (aNaturalPerson, "NaturalPerson");
    m_aNaturalPerson = aNaturalPerson;
  }

  @Nonnull
  @Nonempty
  public String getName ()
  {
    return NAME;
  }

  @Nonnull
  public SlotType createSlot ()
  {
    return new SlotBuilder ().setName (NAME)
                             .setValue (new PersonMarshaller ().getAsDocument (m_aNaturalPerson.getAsCorePerson ()).getDocumentElement ())
                             .build ();
  }
}