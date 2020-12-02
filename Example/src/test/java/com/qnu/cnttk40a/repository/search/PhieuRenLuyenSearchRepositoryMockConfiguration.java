package com.qnu.cnttk40a.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link PhieuRenLuyenSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class PhieuRenLuyenSearchRepositoryMockConfiguration {

    @MockBean
    private PhieuRenLuyenSearchRepository mockPhieuRenLuyenSearchRepository;

}
