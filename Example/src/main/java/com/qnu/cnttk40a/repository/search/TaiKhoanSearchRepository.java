package com.qnu.cnttk40a.repository.search;

import com.qnu.cnttk40a.domain.TaiKhoan;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link TaiKhoan} entity.
 */
public interface TaiKhoanSearchRepository extends ElasticsearchRepository<TaiKhoan, Long> {
}
