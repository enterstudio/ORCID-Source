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
<@protected>
<div id="claim">
   <div class="row">        
        <div class="col-md-offset-3 col-md-9 col-sm-offset-3 col-sm-9 col-xs-12"> 
		<h2>${springMacroRequestContext.getMessage("claim.claimyourrecord")}</h2>
				<#include "/common/browser-checks.ftl" />
				<div ng-controller="ClaimCtrl">
					<div>
						<!-- span class="orcid-error" ng-show="register.errors.length > 0">
							<div ng-repeat='error in register.errors' ng-bind-html="error"></div>
					   	</span -->
					   	<h4>${springMacroRequestContext.getMessage("claim.almostthere")}</h4>
					   	<p>${springMacroRequestContext.getMessage("claim.completefields")}</p>
										
	                    <div class="control-group">
	                        <label class="control-label">${springMacroRequestContext.getMessage("claim.password")}</label>
	                        <div class="relative">
	                            <input type="password" name="password" class="input-xlarge" ng-model="register.password.value" ng-change="serverValidate('Password')"/>
	                            <span class="required" ng-class="isValidClass(register.password)">*</span>
					   			<div class="popover-help-container" style="display: inline; position: relative;">
	                                <a href="javascript:void(0);"><i class="glyphicon glyphicon-question-sign"></i></a>
	                                <div id="name-help" class="popover bottom">
								        <div class="arrow"></div>
								        <div class="popover-content">
									    	<p>${springMacroRequestContext.getMessage("password_one_time_reset.labelmust8more")}</p>
									        <ul>
												<li>${springMacroRequestContext.getMessage("password_one_time_reset.labelatleast09")}</li>
												<li>${springMacroRequestContext.getMessage("password_one_time_reset.labelatleast1following")}
													<ul>
														<li>${springMacroRequestContext.getMessage("password_one_time_reset.labelalphacharacter")}</li>
														<li>${springMacroRequestContext.getMessage("password_one_time_reset.labelanyoffollow")}<br /> ! @ # $ % ^ * ( ) ~ `{ } [ ] | \ &amp; _</li>
													</ul>
												</li>
												<li>${springMacroRequestContext.getMessage("password_one_time_reset.labeloptionallyspace")}</li>
											</ul>                         
											<p>${springMacroRequestContext.getMessage("password_one_time_reset.commonpasswords")}<a href="https://github.com/danielmiessler/SecLists/blob/master/Passwords/10_million_password_list_top_1000.txt" target="_blank">${springMacroRequestContext.getMessage("password_one_time_reset.commonpasswordslink")}</a></p>             
											<p><strong>${springMacroRequestContext.getMessage("password_one_time_reset.examplesunmoon")}</strong></p>
										</div>                
								    </div>
	                            </div>
	                            <span class="orcid-error" ng-show="register.password.errors.length > 0">
									<div ng-repeat='error in register.password.errors' ng-bind-html="error"></div>
					   			</span>
	                        </div>
	                    </div>
	                    <div>
	                        <label class="control-label">${springMacroRequestContext.getMessage("password_one_time_reset.labelconfirmpassword")}</label>
	                        <div class="relative">
	                            <input type="password" name="confirmPassword" class="input-xlarge" ng-model="register.passwordConfirm.value" ng-change="serverValidate('PasswordConfirm')"/>
	                            <span class="required" ng-class="isValidClass(register.passwordConfirm)">*</span>
	                            <span class="orcid-error" ng-show="register.passwordConfirm.errors.length > 0">
									<div ng-repeat='error in register.passwordConfirm.errors' ng-bind-html="error"></div>
					   			</span>
	                        </div>
	                    </div>
	     				<div class="margin-top-box privacy">
	                        <label class="privacy-toggle-lbl">${springMacroRequestContext.getMessage("privacy_preferences.activitiesVisibilityDefault")}</label> 
        					<label class="privacy-toggle-lbl">${springMacroRequestContext.getMessage("privacy_preferences.activitiesVisibilityDefault.who_can_see_this")}</label>
    						<@orcid.privacyToggle "register.activitiesVisibilityDefault.visibility" "updateActivitiesVisibilityDefault('PUBLIC', $event)"
	                        	"updateActivitiesVisibilityDefault('LIMITED', $event)" "updateActivitiesVisibilityDefault('PRIVATE', $event)" /> 
	                    </div>                    
		                <div class="margin-top-box">
		                    <div class="relative">
		                        <label></strong>${springMacroRequestContext.getMessage("claim.notificationemail")}</label>
		                        <label class="checkbox">
		                            <input type="checkbox" name="sendOrcidChangeNotifications" ng-model="register.sendChangeNotifications.value"/>
		                            ${springMacroRequestContext.getMessage("register.labelsendmenotifications")}
		                        </label>		                        
		                     </div>
						</div>
	                    <div class="margin-top-box">
		                    <div class="relative">
		                        <label>${springMacroRequestContext.getMessage("register.labelTermsofUse")} <span class="required"  ng-class="{'text-error':register.termsOfUse.value == false}">*</span></label>
		                        <label class="checkbox">
		                        	<input type="checkbox" name="acceptTermsAndConditions" ng-model="register.termsOfUse.value" ng-change="serverValidate('TermsOfUse')"/>
		                        	${springMacroRequestContext.getMessage("register.labelconsent")} <a href="${aboutUri}/footer/privacy-policy" target="_blank">${springMacroRequestContext.getMessage("register.labelprivacypolicy")}</a> ${springMacroRequestContext.getMessage("register.labeland")} ${springMacroRequestContext.getMessage("common.termsandconditions1")}<a href="${aboutUri}/content/orcid-terms-use" target="_blank">${springMacroRequestContext.getMessage("common.termsandconditions2")}</a> ${springMacroRequestContext.getMessage("common.termsandconditions3")}</p>
		                        </label>
		                        <span class="orcid-error" ng-show="register.termsOfUse.errors.length > 0">
									<div ng-repeat='error in register.termsOfUse.errors' ng-bind-html="error"></div>
					   			</span>
		                    </div>
	                	</div>   
		                <div class="relative centered-mobile">
		                      <button type="submit" class="btn btn-primary" ng-click="postClaim()">${springMacroRequestContext.getMessage("claim.btnClaim")}</button>
		                      <span ng-show="postingClaim" ng-cloak>
		                      	<i class="glyphicon glyphicon-refresh spin x2 green"></i>
		                      </span>
		                </div>  
	                </div> 
				</div>
		</div>
	</div>
</div>
</@protected>
