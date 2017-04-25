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
<#import "email_macros.ftl" as emailMacros />
<@emailMacros.msg "email.service_announcement.dear1" /> ${emailName},

<@emailMacros.msg "email.service_announcement.2017.body_1" />


<@emailMacros.msg "email.service_announcement.2017.body_2" />


<@emailMacros.msg "email.service_announcement.2017.body_3" />


	(1) <@emailMacros.msg "email.service_announcement.2017.body_3_option_1" /> ${verificationUrl}
	(2) <@emailMacros.msg "email.service_announcement.2017.body_3_option_2" />
	(3) <@emailMacros.msg "email.service_announcement.2017.body_3_option_3" />
	
<@emailMacros.msg "email.service_announcement.2017.body_4_plain_text" />


<@emailMacros.msg "email.service_announcement.2017.regards" />

<@emailMacros.msg "email.service_announcement.2017.orcid_team" />


<@emailMacros.msg "email.service_announcement.2017.footer_1" />

<@emailMacros.msg "email.service_announcement.2017.footer_2" />

<@emailMacros.msg "email.service_announcement.2017.footer_3" /> <@emailMacros.msg "email.service_announcement.2017.footer_3_unsubscribe_link_text" /> - ${emailFrequencyUrl}

----
<#include "email_footer.ftl"/>
