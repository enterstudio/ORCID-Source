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

import java.util.Map;

import javax.annotation.Resource;

import org.orcid.core.manager.StatisticsManager;
import org.orcid.statistics.dao.StatisticsDao;
import org.orcid.statistics.jpa.entities.StatisticKeyEntity;
import org.orcid.statistics.jpa.entities.StatisticValuesEntity;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class StatisticsManagerImpl implements StatisticsManager {

    @Resource
    private TransactionTemplate statisticsTransactionTemplate;
    
    @Resource
    StatisticsDao statisticsDao;

    /**
     * Save a set of statistics to the database
     * 
     * @param statistics
     *          List of statistics to store
     * */
    
    @Override    
    @Transactional
    public void saveStatistics(Map<String, Long> statistics) {        
        statisticsTransactionTemplate.execute(new TransactionCallbackWithoutResult() {            
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                StatisticKeyEntity key = statisticsDao.createKey();        
                // Store statistics on database
                for (Map.Entry<String, Long> entry : statistics.entrySet()) {            
                    StatisticValuesEntity newStat = new StatisticValuesEntity(key, entry.getKey(), entry.getValue());
                    statisticsDao.persist(newStat);
                }                
            }
        });
                        
    }   
}
