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
package org.orcid.core.exception;

public class ExceedMaxNumberOfPutCodesException extends ApplicationException {

    private static final long serialVersionUID = 1L;
    
    public ExceedMaxNumberOfPutCodesException(Integer maxNumber) {
        super("Too many put codes specified: maximum is " + maxNumber);
    }
    
}
