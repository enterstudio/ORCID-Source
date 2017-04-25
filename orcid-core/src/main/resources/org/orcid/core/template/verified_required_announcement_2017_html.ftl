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
<#escape x as x?html>
<!DOCTYPE html>
<html>
	<head>
	<title><@emailMacros.msg "email.service_announcement.2017.verify_email.subject" /></title>
	</head>
	<body>
		<div style="padding: 20px; padding-top: 0px;">
			<img src="https://orcid.org/sites/all/themes/orcid/img/orcid-logo.png" alt="ORCID.org"/>
		    <hr />
		  	<span style="font-family: arial, helvetica, sans-serif; font-size: 15px; color: #666666; font-weight: bold;">
		    <@emailMacros.msg "email.service_announcement.2017.verify_email.dear" />&nbsp;${emailName},
		    </span>
		    <p style="font-family: arial, helvetica, sans-serif; font-size: 15px; color: #666666;">
		    	<@emailMacros.msg "email.service_announcement.2017.body_1" />
		    </p>
		    <p style="font-family: arial, helvetica, sans-serif; font-size: 15px; color: #666666;">
		    	<@emailMacros.msg "email.service_announcement.2017.body_2" />
		    </p>
		    <p style="font-family: arial, helvetica, sans-serif; font-size: 15px; color: #666666;">
		    	<@emailMacros.msg "email.service_announcement.2017.body_3" />
		    </p>
		    <ol style="font-family: arial,  helvetica, sans-serif;font-size: 15px;color: #666666;">
		    	<li><@emailMacros.msg "email.service_announcement.2017.body_3_option_1" />&nbsp;<a href="${verificationUrl}">${verificationUrl}</a></li>
		    	<li><@emailMacros.msg "email.service_announcement.2017.body_3_option_2" /></li>
		   		<li><@emailMacros.msg "email.service_announcement.2017.body_3_option_3" /></li>
		    </ol>
		    <p style="font-family: arial, helvetica, sans-serif; font-size: 15px; color: #666666;">
		    	<@emailMacros.msg "email.service_announcement.2017.body_4_html" />
		    </p>
		    <p style="font-family: arial,  helvetica, sans-serif;font-size: 15px;color: #666666;">
				<@emailMacros.msg "email.service_announcement.2017.regards" />
				<br><@emailMacros.msg "email.service_announcement.2017.orcid_team" />
			</p>
			<p style="font-family: arial,  helvetica, sans-serif;font-size: 15px;color: #666666;">
				<@emailMacros.msg "email.service_announcement.2017.footer_1" />
				<br>
				<br><@emailMacros.msg "email.service_announcement.2017.footer_2" />
				<br>
				<br><@emailMacros.msg "email.service_announcement.2017.footer_3" />&nbsp;<a href="${emailFrequencyUrl}" target="_blank"><@emailMacros.msg "email.service_announcement.2017.footer_3_unsubscribe_link_text" /></a>
			</p>
			<p style="font-family: arial,  helvetica, sans-serif;font-size: 15px;color: #666666;">
				<#include "email_footer_html.ftl"/>
			</p>
		 </div>
	 </body>
 </html>
 </#escape>
