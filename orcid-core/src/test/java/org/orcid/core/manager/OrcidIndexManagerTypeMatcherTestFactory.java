/**
 * =============================================================================
 *
 * ORCID (R) Open Source
 * http://orcid.org
 *
 * Copyright (c) 2012-2014 ORCID, Inc.
 * Licensed under an MIT-Style License (MIT)
 * http://orcid.org/open-source-license
 *
 * This copyright and license information (including a link to the full license)
 * shall be included in its entirety in all copies or substantial portion of
 * the software.
 *
 * =============================================================================
 */
package org.orcid.core.manager;

import org.mockito.ArgumentMatcher;
import org.orcid.jaxb.model.message.OrcidProfile;
import org.orcid.jaxb.model.message.PersonalDetails;

/**
 * Test factory class for Hamcrest matchers. For use by the
 * OrcidIndexManagerImplTest class to assert that following calls on the
 * OrcidIndexManagerImpl, calls to solr are also made with Orcids populated as
 * we expect. The matchers below can be made more specific as test criteria
 * evolves.
 *
 * @author jamesb
 */
class OrcidIndexManagerTypeMatcherTestFactory {

    private OrcidIndexManagerTypeMatcherTestFactory() {
    }

    public static ArgumentMatcher<OrcidProfile> orcidBasicProfileCreate() {
        return new ArgumentMatcher<OrcidProfile>() {

            @Override
            public boolean matches(OrcidProfile orcidProfile) {
                if (!"4444-4444-4444-0001".equals(orcidProfile.getOrcidIdentifier().getPath())) {
                    return false;
                }

                PersonalDetails personDetails = orcidProfile.getOrcidBio().getPersonalDetails();
                if (personDetails.getCreditName() != null || personDetails.getFamilyName() != null) {

                    return false;
                }

                boolean matchesGivenNameCorrectly = "Reserved For Claim".equals(personDetails.getGivenNames().getContent());

                return matchesGivenNameCorrectly;

            }

        };
    }

}
