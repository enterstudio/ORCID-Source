<#--

    =============================================================================

    ORCID (R) Open Source
    http://orcid.org

    Copyright (c) 2012-2014 ORCID, Inc.
    Licensed under an MIT-Style License (MIT)
    http://orcid.org/open-source-license

    This copyright and license information (including a link to the full license)
    shall be included in its entirety in all copies or substantial portion of
    the software.

    =============================================================================

-->
<@base>
    <div class="container oauth-login top-green-border oneStepWidth">
        <div class="row">
            <div class="col-push-3 col-md-9">
                <div class="logo">
                    <h1 class="oauth_h1_margin"><a href="${aboutUri}"><img src="${staticCdn}/img/orcid-logo.png" alt="ORCID logo" /></a></h1>
                    <!-- <p>${springMacroRequestContext.getMessage("oauth_login.connectingresearch")}</p> -->
                </div>
            </div>
        </div>
        <div class="row">    
            <#if Session.SPRING_SECURITY_LAST_EXCEPTION?? && Session.SPRING_SECURITY_LAST_EXCEPTION.message?has_content>
                <div class="alert alert-error pagination-centered input-xxlarge">
                    <p><@spring.message "orcid.frontend.security.bad_credentials"/></p>
                </div>
            </#if>
        </div>
        <div class="row">
            <#include "sandbox_warning.ftl"/>
            <#include "oauth_login_and_sign_up.ftl"/>
        </div>
    </div>
</@base>