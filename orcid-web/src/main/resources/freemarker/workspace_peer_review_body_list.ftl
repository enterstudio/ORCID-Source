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
<#include "includes/peer_review/del_peer_review_inc.ftl"/>

<#include "includes/peer_review/add_peer_review_inc.ftl"/>
<div>
    <div class="workspace-accordion accordion-peer-review" id="workspace-accordion">
        <div id="workspace-peer-review" class="workspace-accordion-item workspace-accordion-active">
            <div class="workspace-accordion-header"><a name='workspace-peer-review'></a>
                <div class="row">
                    <div class="col-md-5 col-sm-2 col-xs-12">
                         <div class="workspace-title">                                          
                            <a ng-click="workspaceSrvc.togglePeerReview()" class="toggle-text">
                                <i class="glyphicon-chevron-down glyphicon x075" ng-class="{'glyphicon-chevron-right':workspaceSrvc.displayPeerReview==false}"></i> <@orcid.msg 'workspace_peer_review_body_list.peerReview'/> (<span ng-bind="peerReviewSrvc.peerReviewCount()"></span>)
                            </a>                                
                        </div>
                    </div>
                    <div class="col-md-7 col-sm-10 col-xs-12 action-button-bar">
                        <#include "includes/workspace/workspace_act_sort_menu.ftl"/>
                        <#if !(isPublicProfile??)>
                            <ul class="workspace-bar-menu">
                                <li class="hidden-xs" ng-if="noLinkFlag">
                                    <div class="menu-container" >
                                        <ul class="toggle-menu">
                                            <li> 
                                                <span class="glyphicon glyphicon-plus"></span> <@orcid.msg 'workspace_peer_review_body_list.addPeerReview'/>
                                                <ul class="menu-options works">                                                 
                                                    <!-- Search & Link -->
                                                    <li ng-if="noLinkFlag" ng-cloak>
                                                        <a class="action-option manage-button" ng-click="showPeerReviewImportWizard()">
                                                            <span class="glyphicon glyphicon-cloud-upload"></span> <@orcid.msg 'workspace_peer_review_body_list.searchAndLink'/> 
                                                        </a>
                                                     </li>                                                                 
                                                </ul>
                                            </li>                                           
                                        </ul>                                       
                                    </div>  
                                </li>                               
                            </ul>
                        </#if>
                    </div>  
                </div>          
            </div>
            <!--  Import Wizard -->
            <div ng-if="peerReviewImportWizard == true" class="funding-import-wizard" ng-cloak>
                <div class="ie7fix-inner">
                    <div class="row">   
                        <div class="col-md-12 col-sm-12 col-xs-12">
                            <h1 class="lightbox-title wizard-header">Link Peer Review</h1>
                            <span ng-click="showPeerReviewImportWizard()" class="close-wizard">Hide link Peer Review</span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 col-sm-12 col-xs-12">
                            <div class="justify">
                                <p class="wizard-content">ORCID works with our member organizations to make it easy to connect your ORCID iD and link to information in their records. Choose one of the link wizards to get started.</p>
                            </div>                                  
                        </div>
                    </div>
                    <div class="row wizards">
                        <div class="col-md-12 col-sm-12 col-xs-12">
                            <div ng-repeat="peerReview in peerReviewImportWizardList">
                                <strong><a ng-click="openImportWizardUrlFilter('<@orcid.rootPath '/oauth/authorize'/>', peerReview)">{{peerReview.displayName}}</a></strong><br />                                                                                                              
                                <div class="justify">                                               
                                    <p class="wizard-description" ng-class="{'ellipsis-on' : wizardDescExpanded[peerReview.clientId] == false || wizardDescExpanded[peerReview.clientId] == null}">
                                        {{peerReview.shortDescription}}                                                 
                                        <a ng-click="toggleWizardDesc(peerReview.clientId)" ng-if="wizardDescExpanded[peerReview.clientId]" ng-cloak><span class="glyphicon glyphicon-chevron-right wizard-chevron"></span></a>
                                    </p>                                                
                                    <a ng-click="toggleWizardDesc(peerReview.clientId)" ng-if="wizardDescExpanded[peerReview.clientId] == false || wizardDescExpanded[peerReview.clientId] == null" class="toggle-wizard-desc" ng-cloak><span class="glyphicon glyphicon-chevron-down wizard-chevron"></span></a>
                                </div>
                                <hr/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="workspace-accordion-content" ng-if="workspaceSrvc.displayPeerReview" >
                <#include "includes/peer_review/peer_review_body_inc.ftl" />
            </div>
        </div>
    </div>
</div>