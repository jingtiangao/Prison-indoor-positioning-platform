package com.grain.service;

import com.grain.entity.TInsectsInbinOnDepot;

/**
 * Service - 储备库害虫采集
 * @author Mark
 * 
 */
public interface DepotCollectionService extends BaseService<TInsectsInbinOnDepot, String> {

	boolean depotCollectionExists(String smCollection);

	TInsectsInbinOnDepot findBySMCollection(String smCollection);

}