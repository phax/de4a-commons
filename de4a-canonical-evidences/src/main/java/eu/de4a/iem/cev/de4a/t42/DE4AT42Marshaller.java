package eu.de4a.iem.cev.de4a.t42;

import java.util.List;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.NamespaceContext;

import com.helger.commons.io.resource.ClassPathResource;
import com.helger.jaxb.GenericJAXBMarshaller;

import eu.de4a.iem.jaxb.t42.v0_6.LegalEntityType;

/**
 * Special marshaller for canonical evidences of the DE4A T4.2 v0.5 pilot. This
 * class can ONLY ready T4.2 stuff without the surrounding core document.
 *
 * @author Philip Helger
 * @param <JAXBTYPE>
 *        JAXB type to be read
 */
public class DE4AT42Marshaller <JAXBTYPE> extends GenericJAXBMarshaller <JAXBTYPE>
{
  public DE4AT42Marshaller (@Nonnull final Class <JAXBTYPE> aType,
                            @Nullable final List <? extends ClassPathResource> aXSDs,
                            @Nonnull final Function <? super JAXBTYPE, ? extends JAXBElement <JAXBTYPE>> aJAXBElementWrapper,
                            @Nullable final NamespaceContext aNSContext)
  {
    super (aType, aXSDs, aJAXBElementWrapper);
    setNamespaceContext (aNSContext);
  }

  @Nonnull
  public static DE4AT42Marshaller <LegalEntityType> legalEntity ()
  {
    return new DE4AT42Marshaller <> (LegalEntityType.class,
                                     CT42.getAllXSDs (),
                                     new eu.de4a.iem.jaxb.t42.v0_6.ObjectFactory ()::createLegalEntity,
                                     DE4AT42NamespaceContext.getInstance ());
  }
}
