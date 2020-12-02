package com.qnu.cnttk40a.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link CauHoiSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class CauHoiSearchRepositoryMockConfiguration {

    @MockBean
    private CauHoiSearchRepository mockCauHoiSearchRepository;

}
