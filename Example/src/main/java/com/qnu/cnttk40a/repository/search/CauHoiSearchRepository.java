package com.qnu.cnttk40a.repository.search;

import com.qnu.cnttk40a.domain.CauHoi;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link CauHoi} entity.
 */
public interface CauHoiSearchRepository extends ElasticsearchRepository<CauHoi, Long> {
}
