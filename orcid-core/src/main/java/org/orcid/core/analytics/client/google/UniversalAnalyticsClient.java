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
package org.orcid.core.analytics.client.google;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.HttpMethod;

import org.apache.commons.lang3.StringUtils;
import org.orcid.core.analytics.AnalyticsData;
import org.orcid.core.analytics.client.AnalyticsClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.dataformat.yaml.snakeyaml.util.UriEncoder;

public class UniversalAnalyticsClient implements AnalyticsClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(UniversalAnalyticsClient.class);

    protected static final String PROTOCOL_VERSION_PARAM = "v";

    protected static final String PROTOCOL_VERSION_VALUE = "1";

    protected static final String TRACKING_ID_PARAM = "tid";

    protected static final String CLIENT_ID_PARAM = "cid";

    protected static final String IP_ADDRESS_PARAM = "uip";

    protected static final String USER_AGENT_PARAM = "ua";

    protected static final String HIT_TYPE_PARAM = "t";

    protected static final String HIT_TYPE_VALUE_EVENT = "event";

    protected static final String EVENT_CATEGORY_PARAM = "ec";

    protected static final String EVENT_ACTION_PARAM = "ea";

    protected static final String EVENT_LABEL_PARAM = "el";

    protected static final String API_VERSION_PARAM = "cd1";

    protected static final String CONTENT_TYPE_PARAM = "cd2";

    protected static final String RESPONSE_CODE_PARAM = "cd3";
    
    protected static final String CLIENT_PARAM = "cd4";

    @Value("${org.orcid.core.api.analytics.trackingCode:}")
    private String analyticsTrackingCode;

    @Value("${org.orcid.core.api.analytics.endpoint:}")
    private String analyticsEndpoint;

    @Override
    public void sendAnalyticsData(AnalyticsData data) {
        if (!StringUtils.isBlank(analyticsTrackingCode)) {
            recordEvent(data);
        }
    }

    private void recordEvent(AnalyticsData data) {
        String payload = getEventPayload(data);
        postData(payload);
    }

    protected void postData(String payload) {
        try {
            URL url = new URL(analyticsEndpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(HttpMethod.POST);
            connection.setDoOutput(true);

            OutputStreamWriter outputStream = new OutputStreamWriter(connection.getOutputStream());
            outputStream.write(payload);
            outputStream.flush();
            outputStream.close();
            int response = connection.getResponseCode();

            if (response != 200) {
                LOGGER.warn("Analytics: received response code " + response);
                LOGGER.warn("Payload was: " + payload);
            }
            connection.disconnect();
        } catch (IOException e) {
            LOGGER.warn("Error sending analytics data", e);
        }

    }

    private String getEventPayload(AnalyticsData data) {
        StringBuilder payload = new StringBuilder(PROTOCOL_VERSION_PARAM).append("=").append(PROTOCOL_VERSION_VALUE);
        payload.append("&").append(TRACKING_ID_PARAM).append("=").append(analyticsTrackingCode);
        payload.append("&").append(CLIENT_ID_PARAM).append("=").append(data.getClientId());
        payload.append("&").append(IP_ADDRESS_PARAM).append("=").append(data.getIpAddress());
        payload.append("&").append(USER_AGENT_PARAM).append("=").append(data.getUserAgent());
        payload.append("&").append(HIT_TYPE_PARAM).append("=").append(HIT_TYPE_VALUE_EVENT);
        payload.append("&").append(EVENT_ACTION_PARAM).append("=").append(data.getMethod());
        payload.append("&").append(EVENT_CATEGORY_PARAM).append("=").append(data.getCategory());
        payload.append("&").append(EVENT_LABEL_PARAM).append("=").append(data.getUrl());
        payload.append("&").append(API_VERSION_PARAM).append("=").append(data.getApiVersion());
        payload.append("&").append(CONTENT_TYPE_PARAM).append("=").append(data.getContentType());
        payload.append("&").append(RESPONSE_CODE_PARAM).append("=").append(data.getResponseCode());
        payload.append("&").append(CLIENT_PARAM).append("=").append(data.getClientDetailsString());
        return UriEncoder.encode(payload.toString());
    }
}
