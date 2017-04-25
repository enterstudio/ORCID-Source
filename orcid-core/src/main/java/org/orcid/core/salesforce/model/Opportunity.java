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
package org.orcid.core.salesforce.model;

import java.io.Serializable;

/**
 * 
 * @author Will Simpson
 *
 */
public class Opportunity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String targetAccountId;
    private String accountName;
    private String stageName;
    private String closeDate;
    private String type;
    private String memberType;
    private String membershipStartDate;
    private String membershipEndDate;
    private String consortiumLeadId;
    private String name;
    private String recordTypeId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTargetAccountId() {
        return targetAccountId;
    }

    public void setTargetAccountId(String targetAccountId) {
        this.targetAccountId = targetAccountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getMembershipStartDate() {
        return membershipStartDate;
    }

    public void setMembershipStartDate(String membershipStartDate) {
        this.membershipStartDate = membershipStartDate;
    }

    public String getMembershipEndDate() {
        return membershipEndDate;
    }

    public void setMembershipEndDate(String membershipEndDate) {
        this.membershipEndDate = membershipEndDate;
    }

    public String getConsortiumLeadId() {
        return consortiumLeadId;
    }

    public void setConsortiumLeadId(String consortiumLeadId) {
        this.consortiumLeadId = consortiumLeadId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecordTypeId() {
        return recordTypeId;
    }

    public void setRecordTypeId(String recordTypeId) {
        this.recordTypeId = recordTypeId;
    }

    @Override
    public String toString() {
        return "Opportunity [id=" + id + ", targetAccountId=" + targetAccountId + ", accountName=" + accountName + ", stageName=" + stageName + ", closeDate=" + closeDate
                + ", type=" + type + ", memberType=" + memberType + ", membershipStartDate=" + membershipStartDate + ", membershipEndDate=" + membershipEndDate
                + ", consortiumLeadId=" + consortiumLeadId + ", name=" + name + ", recordTypeId=" + recordTypeId + "]";
    }

}
