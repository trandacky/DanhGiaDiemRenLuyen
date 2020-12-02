package com.qnu.cnttk40a.repository.search;

import com.qnu.cnttk40a.domain.Lop;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link Lop} entity.
 */
public interface LopSearchRepository extends ElasticsearchRepository<Lop, Long> {
}
