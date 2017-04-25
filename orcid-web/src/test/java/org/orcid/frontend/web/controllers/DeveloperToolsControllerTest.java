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
package org.orcid.frontend.web.controllers;

/**
 * @author Angel Montenegro (amontenegro) Date: 29/08/2013
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.orcid.core.manager.EncryptionManager;
import org.orcid.core.manager.OrcidProfileManager;
import org.orcid.core.oauth.OrcidProfileUserDetails;
import org.orcid.core.security.OrcidWebRole;
import org.orcid.frontend.web.util.BaseControllerTest;
import org.orcid.persistence.dao.ClientDetailsDao;
import org.orcid.persistence.dao.ProfileDao;
import org.orcid.persistence.jpa.entities.ClientDetailsEntity;
import org.orcid.persistence.jpa.entities.ClientSecretEntity;
import org.orcid.pojo.ajaxForm.PojoUtil;
import org.orcid.pojo.ajaxForm.RedirectUri;
import org.orcid.pojo.ajaxForm.SSOCredentials;
import org.orcid.pojo.ajaxForm.Text;
import org.orcid.test.OrcidJUnit4ClassRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(OrcidJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:orcid-core-context.xml", "classpath:orcid-frontend-web-servlet.xml", "classpath:statistics-core-context.xml" })
public class DeveloperToolsControllerTest extends BaseControllerTest {

    @Resource
    DeveloperToolsController developerToolsController;

    @Resource
    private ProfileDao profileDao;

    @Resource
    protected OrcidProfileManager orcidProfileManager;
    
    @Resource
    private EncryptionManager encryptionManager;
    
    @Resource
    private ClientDetailsDao clientDetailsDao;
    
    @Before
    public void init() {
        assertNotNull(developerToolsController);
        assertNotNull(profileDao);
    }
    
    @Override
    protected Authentication getAuthentication() {
        orcidProfile = orcidProfileManager.retrieveOrcidProfile("4444-4444-4444-4442");

        OrcidProfileUserDetails details = null;
        if(orcidProfile.getType() != null){             
                details = new OrcidProfileUserDetails(orcidProfile.getOrcidIdentifier().getPath(), orcidProfile.getOrcidBio().getContactDetails().getEmail()
                    .get(0).getValue(), orcidProfile.getOrcidInternal().getSecurityDetails().getEncryptedPassword().getContent(), orcidProfile.getType(), orcidProfile.getGroupType());
        } else {
                details = new OrcidProfileUserDetails(orcidProfile.getOrcidIdentifier().getPath(), orcidProfile.getOrcidBio().getContactDetails().getEmail()
                    .get(0).getValue(), orcidProfile.getOrcidInternal().getSecurityDetails().getEncryptedPassword().getContent());
        }
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(details, "4444-4444-4444-4442", getRole());
        return auth;
    }
    
    protected List<OrcidWebRole> getRole() {
        return Arrays.asList(OrcidWebRole.ROLE_USER);
    }
    
    
    @Test
    @Transactional("transactionManager")
    public void testCrossSiteScriptingOnClientName() throws Exception {
        SSOCredentials ssoCredentials = new SSOCredentials();
        ssoCredentials.setClientName(Text.valueOf("<script>alert('name')</script>"));
        ssoCredentials.setClientDescription(Text.valueOf("This is a short description"));
        ssoCredentials.setClientWebsite(Text.valueOf("http://client.com"));
        Set<RedirectUri> redirectUris = new HashSet<RedirectUri>();
        RedirectUri rUri = new RedirectUri();
        rUri.setType(Text.valueOf("default"));
        rUri.setValue(Text.valueOf("http://test.com"));
        redirectUris.add(rUri);
        ssoCredentials.setRedirectUris(redirectUris);
        SSOCredentials result = developerToolsController.generateSSOCredentialsJson(ssoCredentials);
        assertNotNull(result);
        assertEquals(1, result.getErrors().size());
        assertEquals(developerToolsController.getMessage("manage.developer_tools.name.html"), result.getErrors().get(0));        
    }

    
    @Test
    @Transactional("transactionManager")
    public void testCrossSiteScriptingOnClientDescription() throws Exception {
        SSOCredentials ssoCredentials = new SSOCredentials();
        ssoCredentials.setClientName(Text.valueOf("Client Name"));
        ssoCredentials.setClientDescription(Text.valueOf("This is a test to show that html is <script>alert('name')</script> throws an error"));
        ssoCredentials.setClientWebsite(Text.valueOf("http://client.com"));
        Set<RedirectUri> redirectUris = new HashSet<RedirectUri>();
        RedirectUri rUri = new RedirectUri();
        rUri.setType(Text.valueOf("default"));
        rUri.setValue(Text.valueOf("http://test.com"));
        redirectUris.add(rUri);
        ssoCredentials.setRedirectUris(redirectUris);
        SSOCredentials result = developerToolsController.generateSSOCredentialsJson(ssoCredentials);
        assertNotNull(result);
        assertEquals(1, result.getErrors().size());
        assertEquals(developerToolsController.getMessage("manage.developer_tools.description.html"), result.getErrors().get(0));
    }
    
    @Test
    @Transactional("transactionManager")
    public void testSSOCredentialsValidation() throws Exception{
        //Test empty title
        SSOCredentials ssoCredentials = new SSOCredentials();        
        ssoCredentials.setClientDescription(Text.valueOf("This is a description"));
        ssoCredentials.setClientWebsite(Text.valueOf("http://client.com"));
        Set<RedirectUri> redirectUris = new HashSet<RedirectUri>();
        RedirectUri rUri = new RedirectUri();
        rUri.setType(Text.valueOf("default"));
        rUri.setValue(Text.valueOf("http://test.com"));
        redirectUris.add(rUri);
        ssoCredentials.setRedirectUris(redirectUris);
        SSOCredentials result = developerToolsController.generateSSOCredentialsJson(ssoCredentials);
        assertNotNull(result.getErrors());
        assertEquals(result.getErrors().size(), 1);
        assertEquals(result.getErrors().get(0), developerToolsController.getMessage("manage.developer_tools.name_not_empty"));

        //Test empty description
        ssoCredentials = new SSOCredentials();
        ssoCredentials.setClientName(Text.valueOf("Client Name"));        
        ssoCredentials.setClientWebsite(Text.valueOf("http://client.com"));
        redirectUris = new HashSet<RedirectUri>();
        rUri = new RedirectUri();
        rUri.setType(Text.valueOf("default"));
        rUri.setValue(Text.valueOf("http://test.com"));
        redirectUris.add(rUri);
        ssoCredentials.setRedirectUris(redirectUris);
        result = developerToolsController.generateSSOCredentialsJson(ssoCredentials);
        assertNotNull(result.getErrors());
        assertEquals(result.getErrors().size(), 1);
        assertEquals(result.getErrors().get(0), developerToolsController.getMessage("manage.developer_tools.description_not_empty"));
                
        //Test empty website
        ssoCredentials = new SSOCredentials();
        ssoCredentials.setClientName(Text.valueOf("Client Name"));   
        ssoCredentials.setClientDescription(Text.valueOf("This is a description"));        
        redirectUris = new HashSet<RedirectUri>();
        rUri = new RedirectUri();
        rUri.setType(Text.valueOf("default"));
        rUri.setValue(Text.valueOf("http://test.com"));
        redirectUris.add(rUri);
        ssoCredentials.setRedirectUris(redirectUris);
        result = developerToolsController.generateSSOCredentialsJson(ssoCredentials);
        assertNotNull(result.getErrors());
        assertEquals(result.getErrors().size(), 1);
        assertEquals(result.getErrors().get(0), developerToolsController.getMessage("manage.developer_tools.website_not_empty"));
        
        //Test empty redirect uris
        ssoCredentials = new SSOCredentials();
        ssoCredentials.setClientName(Text.valueOf("Client Name"));   
        ssoCredentials.setClientDescription(Text.valueOf("This is a description"));  
        ssoCredentials.setClientWebsite(Text.valueOf("http://client.com"));        
        result = developerToolsController.generateSSOCredentialsJson(ssoCredentials);
        assertNotNull(result.getErrors());
        assertEquals(result.getErrors().size(), 1);
        assertEquals(result.getErrors().get(0), developerToolsController.getMessage("manage.developer_tools.at_least_one"));
    }
    
    @Test
    @Transactional("transactionManager")
    public void testGenerateSSOCredentials() throws Exception {
        SSOCredentials ssoCredentials = new SSOCredentials();
        ssoCredentials.setClientName(Text.valueOf("Client Name"));
        ssoCredentials.setClientDescription(Text.valueOf("This is a test"));
        ssoCredentials.setClientWebsite(Text.valueOf("http://client.com"));
        Set<RedirectUri> redirectUris = new HashSet<RedirectUri>();
        RedirectUri rUri = new RedirectUri();
        rUri.setType(Text.valueOf("default"));
        rUri.setValue(Text.valueOf("http://test.com"));
        redirectUris.add(rUri);
        ssoCredentials.setRedirectUris(redirectUris);
        SSOCredentials result = developerToolsController.generateSSOCredentialsJson(ssoCredentials);
        assertNotNull(result);
        assertNotNull(result.getErrors());
        assertEquals(result.getErrors().size(), 0);
        assertNotNull(result.getClientSecret());
        assertFalse(PojoUtil.isEmpty(result.getClientSecret()));
        assertFalse(PojoUtil.isEmpty(result.getClientOrcid()));
    }
    
    @Test
    @Transactional("transactionManager")
    public void testUpdateSSOCredentials() throws Exception {
        SSOCredentials ssoCredentials = new SSOCredentials();
        ssoCredentials.setClientName(Text.valueOf("Client Name"));
        ssoCredentials.setClientDescription(Text.valueOf("This is a test"));
        ssoCredentials.setClientWebsite(Text.valueOf("http://client.com"));
        Set<RedirectUri> redirectUris = new HashSet<RedirectUri>();
        RedirectUri rUri = new RedirectUri();
        rUri.setType(Text.valueOf("default"));
        rUri.setValue(Text.valueOf("http://test.com"));
        redirectUris.add(rUri);
        ssoCredentials.setRedirectUris(redirectUris);
        SSOCredentials result = developerToolsController.generateSSOCredentialsJson(ssoCredentials);
        assertNotNull(result);
        assertNotNull(result.getErrors());
        assertEquals(result.getErrors().size(), 0);
        Text clientSecret = result.getClientSecret();                        
                
        //Update values
        ssoCredentials.setClientName(Text.valueOf("Updated client name"));
        ssoCredentials.setClientDescription(Text.valueOf("Updated client description"));
        ssoCredentials.setClientWebsite(Text.valueOf("http://updated.com"));
        RedirectUri rUri2 = new RedirectUri();
        rUri2.setType(Text.valueOf("default"));
        rUri2.setValue(Text.valueOf("http://test2.com"));
        redirectUris.add(rUri2);
        ssoCredentials.setRedirectUris(redirectUris);
        SSOCredentials updatedResult = developerToolsController.updateUserCredentials(ssoCredentials);
        assertNotNull(updatedResult);
        assertNotNull(updatedResult.getErrors());
        assertEquals(updatedResult.getErrors().size(), 0);
        
        Text updatedClientSecret = updatedResult.getClientSecret();
        
        assertEquals(updatedClientSecret.toString(), clientSecret.toString());
        assertEquals(updatedResult.getClientName().getValue(), "Updated client name");
        assertEquals(updatedResult.getClientDescription().getValue(), "Updated client description");
        assertEquals(updatedResult.getClientWebsite().getValue(), "http://updated.com");
        assertNotNull(updatedResult.getRedirectUris());
        assertEquals(updatedResult.getRedirectUris().size(), 2);
    }    
    
    @Test 
    public void testResetClientSecret() throws Exception {
        SSOCredentials ssoCredentials = new SSOCredentials();
        ssoCredentials.setClientName(Text.valueOf("Client Name"));
        ssoCredentials.setClientDescription(Text.valueOf("This is a test"));
        ssoCredentials.setClientWebsite(Text.valueOf("http://client.com"));
        Set<RedirectUri> redirectUris = new HashSet<RedirectUri>();
        RedirectUri rUri = new RedirectUri();
        rUri.setType(Text.valueOf("default"));
        rUri.setValue(Text.valueOf("http://test.com"));
        redirectUris.add(rUri);
        ssoCredentials.setRedirectUris(redirectUris);
        SSOCredentials result = developerToolsController.generateSSOCredentialsJson(ssoCredentials);
        assertNotNull(result);
        assertNotNull(result.getErrors());
        assertEquals(result.getErrors().size(), 0);
        Text clientSecret = result.getClientSecret();
        
        assertTrue(developerToolsController.resetClientSecret(result.getClientOrcid().getValue()));
        
        ClientDetailsEntity clientDetails = clientDetailsDao.findByClientId(result.getClientOrcid().getValue(), System.currentTimeMillis());
        
        assertEquals(result.getClientName().getValue(), clientDetails.getClientName());
        assertEquals(result.getClientDescription().getValue(), clientDetails.getClientDescription());
        assertEquals(result.getClientOrcid().getValue(), clientDetails.getClientId());
        assertEquals(result.getClientWebsite().getValue(), clientDetails.getClientWebsite());        
        Set<ClientSecretEntity> clientSecrets = clientDetails.getClientSecrets();
        assertNotNull(clientSecrets);
        assertEquals(2, clientSecrets.size());
        for(ClientSecretEntity clientSecretEntity : clientSecrets) {
            String secret = encryptionManager.decryptForInternalUse(clientSecretEntity.getClientSecret());            
            if(!clientSecretEntity.isPrimary())
                assertEquals(clientSecret.getValue(), secret);
            else
                assertFalse(clientSecret.getValue().equals(secret));
        }            
    }
}