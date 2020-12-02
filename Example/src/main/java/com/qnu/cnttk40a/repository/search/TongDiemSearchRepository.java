package com.qnu.cnttk40a.repository.search;

import com.qnu.cnttk40a.domain.TongDiem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link TongDiem} entity.
 */
public interface TongDiemSearchRepository extends ElasticsearchRepository<TongDiem, Long> {
}
