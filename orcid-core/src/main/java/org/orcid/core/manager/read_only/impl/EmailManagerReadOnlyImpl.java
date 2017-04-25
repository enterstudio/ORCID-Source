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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.orcid.core.adapter.JpaJaxbEmailAdapter;
import org.orcid.core.manager.read_only.EmailManagerReadOnly;
import org.orcid.jaxb.model.common_v2.Visibility;
import org.orcid.jaxb.model.record_v2.Email;
import org.orcid.jaxb.model.record_v2.Emails;
import org.orcid.persistence.dao.EmailDao;
import org.orcid.persistence.jpa.entities.EmailEntity;
import org.orcid.pojo.ajaxForm.PojoUtil;
import org.springframework.cache.annotation.Cacheable;

/**
 * 
 * @author Will Simpson
 * 
 */
public class EmailManagerReadOnlyImpl extends ManagerReadOnlyBaseImpl implements EmailManagerReadOnly {
    @Resource
    protected JpaJaxbEmailAdapter jpaJaxbEmailAdapter;
    
    protected EmailDao emailDao;
    
    public void setEmailDao(EmailDao emailDao) {
        this.emailDao = emailDao;
    }

    @Override
    public boolean emailExists(String email) {
        return emailDao.emailExists(email);
    }

    @Override
    public String findOrcidIdByEmail(String email) {
        return emailDao.findOrcidIdByCaseInsenitiveEmail(email);
    }
    
    @Override
    public boolean isPrimaryEmail(String orcid, String email) {
        return emailDao.isPrimaryEmail(orcid, email);
    }
    
    @Override
    @SuppressWarnings("rawtypes")
    public Map<String, String> findOricdIdsByCommaSeparatedEmails(String csvEmail) {
        Map<String, String> emailIds = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
        List<String> emailList = new ArrayList<String>();
        String [] emails = csvEmail.split(",");
        for(String email : emails) {
            if(StringUtils.isNotBlank(email.trim()))
                emailList.add(email.trim());
        }
        List ids = emailDao.findIdByCaseInsensitiveEmail(emailList);
        for (Iterator it = ids.iterator(); it.hasNext(); ) {
            Object[] orcidEmail = (Object[]) it.next();
            String orcid = (String) orcidEmail[0];
            String email = (String) orcidEmail[1];
            emailIds.put(email, orcid);
        }
        return emailIds;
    }
    
    @Override
    @SuppressWarnings("rawtypes")
    public Map<String, String> findIdsByEmails(List<String> emailList) {
        Map<String, String> emailIds = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
        List ids = emailDao.findIdByCaseInsensitiveEmail(emailList);
        for (Iterator it = ids.iterator(); it.hasNext();) {
            Object[] orcidEmail = (Object[]) it.next();
            String orcid = (String) orcidEmail[0];
            String email = (String) orcidEmail[1];
            emailIds.put(email, orcid);
        }
        return emailIds;
    }
    
    @Override
    public boolean isPrimaryEmailVerified(String orcid) {
        return emailDao.isPrimaryEmailVerified(orcid);
    }
    
    
    @Override
    @Cacheable(value = "emails", key = "#orcid.concat('-').concat(#lastModified)")
    public Emails getEmails(String orcid, long lastModified) {
        return getEmails(orcid, null);
    }
    
    @Override
    @Cacheable(value = "public-emails", key = "#orcid.concat('-').concat(#lastModified)")
    public Emails getPublicEmails(String orcid, long lastModified) {
        return getEmails(orcid, Visibility.PUBLIC);
    }
    
    private Emails getEmails(String orcid, Visibility visibility) {
        List<EmailEntity> entities = new ArrayList<EmailEntity>();
        if(visibility == null) {
            entities = emailDao.findByOrcid(orcid);
        } else {
            entities = emailDao.findByOrcid(orcid, Visibility.PUBLIC);
        }
        List<org.orcid.jaxb.model.record_v2.Email> emailList = jpaJaxbEmailAdapter.toEmailList(entities);
        Emails emails = new Emails();
        emails.setEmails(emailList);        
        return emails;
    }
    
    @Override
    public boolean haveAnyEmailVerified(String orcid) {
        List<EmailEntity> entities = emailDao.findByOrcid(orcid);
        if(entities != null) {
            for(EmailEntity email : entities) {
                if(email.getVerified()) {
                    return true;
                }
            }
        }
        return false;
    }     
    
    @Override
    public EmailEntity findCaseInsensitive(String email) {
        return emailDao.findCaseInsensitive(email);
    }

    @Override
    public EmailEntity find(String email) {
        return emailDao.find(email);
    }

    @Override
    public Email findPrimaryEmail(String orcid) {
        if(PojoUtil.isEmpty(orcid)) {
            return null;
        }
        return jpaJaxbEmailAdapter.toEmail(emailDao.findPrimaryEmail(orcid));
    }
}
