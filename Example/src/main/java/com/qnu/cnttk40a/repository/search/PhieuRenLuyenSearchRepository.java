package com.qnu.cnttk40a.repository.search;

import com.qnu.cnttk40a.domain.PhieuRenLuyen;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link PhieuRenLuyen} entity.
 */
public interface PhieuRenLuyenSearchRepository extends ElasticsearchRepository<PhieuRenLuyen, Long> {
}
