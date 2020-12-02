package com.qnu.cnttk40a.repository.search;

import com.qnu.cnttk40a.domain.BoCauHoi;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link BoCauHoi} entity.
 */
public interface BoCauHoiSearchRepository extends ElasticsearchRepository<BoCauHoi, Long> {
}
