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
package org.orcid.core.manager.read_only.impl;

import javax.annotation.Resource;

import org.orcid.core.manager.ProfileEntityCacheManager;
import org.orcid.core.manager.impl.OrcidUrlManager;
import org.orcid.core.manager.read_only.ActivitiesSummaryManagerReadOnly;
import org.orcid.core.manager.read_only.EmailManagerReadOnly;
import org.orcid.core.manager.read_only.PersonDetailsManagerReadOnly;
import org.orcid.core.manager.read_only.RecordManagerReadOnly;
import org.orcid.jaxb.model.common_v2.LastModifiedDate;
import org.orcid.jaxb.model.common_v2.OrcidIdentifier;
import org.orcid.jaxb.model.common_v2.Source;
import org.orcid.jaxb.model.message.CreationMethod;
import org.orcid.jaxb.model.common_v2.OrcidType;
import org.orcid.jaxb.model.record_v2.CompletionDate;
import org.orcid.jaxb.model.record_v2.DeactivationDate;
import org.orcid.jaxb.model.record_v2.Email;
import org.orcid.jaxb.model.record_v2.Emails;
import org.orcid.jaxb.model.record_v2.History;
import org.orcid.jaxb.model.record_v2.Preferences;
import org.orcid.jaxb.model.record_v2.Record;
import org.orcid.jaxb.model.record_v2.SubmissionDate;
import org.orcid.persistence.jpa.entities.ProfileEntity;
import org.orcid.pojo.ajaxForm.PojoUtil;
import org.orcid.utils.DateUtils;

/**
 * 
 * @author Angel Montenegro
 * 
 */
public class RecordManagerReadOnlyImpl implements RecordManagerReadOnly {

    @Resource
    protected OrcidUrlManager orcidUrlManager;
    
    protected ProfileEntityCacheManager profileEntityCacheManager;
    
    protected EmailManagerReadOnly emailManager;
    
    protected ActivitiesSummaryManagerReadOnly activitiesSummaryManager;

    protected PersonDetailsManagerReadOnly personDetailsManager;
    
    public void setProfileEntityCacheManager(ProfileEntityCacheManager profileEntityCacheManager) {
        this.profileEntityCacheManager = profileEntityCacheManager;
    }

    public void setEmailManager(EmailManagerReadOnly emailManager) {
        this.emailManager = emailManager;
    }

    public void setActivitiesSummaryManager(ActivitiesSummaryManagerReadOnly activitiesSummaryManager) {
        this.activitiesSummaryManager = activitiesSummaryManager;
    }

    public void setPersonDetailsManager(PersonDetailsManagerReadOnly personDetailsManager) {
        this.personDetailsManager = personDetailsManager;
    }

    @Override
    public Record getPublicRecord(String orcid) {
        Record record = new Record();
        record.setOrcidType(getOrcidType(orcid));
        record.setHistory(getHistory(orcid));
        record.setOrcidIdentifier(getOrcidIdentifier(orcid));
        record.setPreferences(getPreferences(orcid));
        record.setActivitiesSummary(activitiesSummaryManager.getPublicActivitiesSummary(orcid));
        record.setPerson(personDetailsManager.getPublicPersonDetails(orcid));        
        return record;
    }

    @Override
    public Record getRecord(String orcid) {
        Record record = new Record();
        record.setOrcidType(getOrcidType(orcid));
        record.setHistory(getHistory(orcid));
        record.setOrcidIdentifier(getOrcidIdentifier(orcid));
        record.setPreferences(getPreferences(orcid));
        record.setActivitiesSummary(activitiesSummaryManager.getActivitiesSummary(orcid));
        record.setPerson(personDetailsManager.getPersonDetails(orcid));        
        return record;
    }
    
    @Override
    public OrcidIdentifier getOrcidIdentifier(String orcid) {
        OrcidIdentifier orcidIdentifier = new OrcidIdentifier();
        orcidIdentifier.setPath(orcid);
        orcidIdentifier.setHost(orcidUrlManager.getBaseHost());
        orcidIdentifier.setUri(orcidUrlManager.getBaseUriHttp() + "/" + orcid);
        return orcidIdentifier;
    }

    private OrcidType getOrcidType(String orcid) {
        ProfileEntity profile = profileEntityCacheManager.retrieve(orcid);
        return profile.getOrcidType();
    }
    
    private Preferences getPreferences(String orcid) {
        Preferences preferences = new Preferences();
        ProfileEntity profile = profileEntityCacheManager.retrieve(orcid);
        org.orcid.jaxb.model.common_v2.Locale profileEntityLocale = profile.getLocale();
        if (profileEntityLocale != null) {
            preferences.setLocale(profileEntityLocale);
        }
        return preferences;
    }
    
    private History getHistory(String orcid) {
        History history = new History();
        ProfileEntity profile = profileEntityCacheManager.retrieve(orcid);
        history.setClaimed(profile.getClaimed());
        if(profile.getCompletedDate() != null) {
            history.setCompletionDate(new CompletionDate(DateUtils.convertToXMLGregorianCalendar(profile.getCompletedDate())));           
        }
        
        if(!PojoUtil.isEmpty(profile.getCreationMethod())) {
            history.setCreationMethod(CreationMethod.fromValue(profile.getCreationMethod()));
        }
        
        if(profile.getDeactivationDate() != null) {
            history.setDeactivationDate(new DeactivationDate(DateUtils.convertToXMLGregorianCalendar(profile.getDeactivationDate())));
        }
        
        if(profile.getLastModified() != null) {            
            history.setLastModifiedDate(new LastModifiedDate(DateUtils.convertToXMLGregorianCalendar(profile.getLastModified())));
        }
        
        if(profile.getSubmissionDate() != null) {
            history.setSubmissionDate(new SubmissionDate(DateUtils.convertToXMLGregorianCalendar(profile.getSubmissionDate())));
        }                
        
        if(profile.getSource() != null) {
            history.setSource(new Source(profile.getSource().getSourceId()));                
        }
        
        boolean verfiedEmail = false;
        boolean verfiedPrimaryEmail = false;
        
        Emails emails = emailManager.getEmails(orcid, profile.getLastModified().getTime());
        if (emails != null) {
            for (Email email : emails.getEmails()) {
                if (email.isVerified()) {
                    verfiedEmail = true;
                    if (email.isPrimary()) {
                        verfiedPrimaryEmail = true;
                        break;
                    }
                }
            }
        }
        history.setVerifiedEmail(verfiedEmail);
        history.setVerifiedPrimaryEmail(verfiedPrimaryEmail);
        
        return history;        
    }

}
