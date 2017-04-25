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
package org.orcid.core.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.orcid.core.manager.NotificationManager;
import org.orcid.core.manager.UserConnectionManager;
import org.orcid.jaxb.model.notification_v2.Notification;
import org.orcid.model.notification.institutional_sign_in_v2.NotificationInstitutionalConnection;
import org.orcid.persistence.dao.UserConnectionDao;
import org.orcid.persistence.jpa.entities.UserconnectionEntity;
import org.orcid.persistence.jpa.entities.UserconnectionPK;

/**
 * 
 * @author Will Simpson
 *
 */
public class UserConnectionManagerImpl implements UserConnectionManager {

    @Resource
    private UserConnectionDao userConnectionDao;

    @Resource
    private NotificationManager notificationManager;

    @Override
    public List<UserconnectionEntity> findByOrcid(String orcid) {
        return userConnectionDao.findByOrcid(orcid);
    }

    @Override
    public void remove(String orcid, UserconnectionPK userConnectionPK) {
        List<Notification> notifications = notificationManager.findNotificationAlertsByOrcid(orcid);
        notifications.forEach(n -> {
            if (n instanceof NotificationInstitutionalConnection) {
                NotificationInstitutionalConnection nic = (NotificationInstitutionalConnection) n;
                if (userConnectionPK.getProviderid().equals(nic.getAuthenticationProviderId())) {
                    notificationManager.flagAsArchived(orcid, n.getPutCode(), false);
                }
            }
        });
        userConnectionDao.remove(userConnectionPK);
    }

    @Override
    public UserconnectionEntity findByProviderIdAndProviderUserId(String providerUserId, String providerId) {        
        return userConnectionDao.findByProviderIdAndProviderUserId(providerUserId, providerId);
    }

    @Override
    public void updateLoginInformation(UserconnectionPK pk) {
        userConnectionDao.updateLoginInformation(pk);
    }

    @Override
    public UserconnectionEntity findByProviderIdAndProviderUserIdAndIdType(String userId, String shibIdentityProvider, String idType) {
        return userConnectionDao.findByProviderIdAndProviderUserIdAndIdType(userId, shibIdentityProvider, idType);
    }

    @Override
    public void update(UserconnectionEntity userConnectionEntity) {
        userConnectionDao.merge( userConnectionEntity);
    }

}
