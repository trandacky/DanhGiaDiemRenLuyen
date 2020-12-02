package com.qnu.cnttk40a.repository.search;

import com.qnu.cnttk40a.domain.ChiTietPhieuRenLuyen;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link ChiTietPhieuRenLuyen} entity.
 */
public interface ChiTietPhieuRenLuyenSearchRepository extends ElasticsearchRepository<ChiTietPhieuRenLuyen, Long> {
}
