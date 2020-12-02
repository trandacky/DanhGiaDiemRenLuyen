package com.qnu.cnttk40a.web.rest;

import com.qnu.cnttk40a.service.CauHoiService;
import com.qnu.cnttk40a.web.rest.errors.BadRequestAlertException;
import com.qnu.cnttk40a.service.dto.CauHoiDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link com.qnu.cnttk40a.domain.CauHoi}.
 */
@RestController
@RequestMapping("/api")
public class CauHoiResource {

    private final Logger log = LoggerFactory.getLogger(CauHoiResource.class);

    private static final String ENTITY_NAME = "cauHoi";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CauHoiService cauHoiService;

    public CauHoiResource(CauHoiService cauHoiService) {
        this.cauHoiService = cauHoiService;
    }

    /**
     * {@code POST  /cau-hois} : Create a new cauHoi.
     *
     * @param cauHoiDTO the cauHoiDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cauHoiDTO, or with status {@code 400 (Bad Request)} if the cauHoi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cau-hois")
    public ResponseEntity<CauHoiDTO> createCauHoi(@RequestBody CauHoiDTO cauHoiDTO) throws URISyntaxException {
        log.debug("REST request to save CauHoi : {}", cauHoiDTO);
        if (cauHoiDTO.getId() != null) {
            throw new BadRequestAlertException("A new cauHoi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CauHoiDTO result = cauHoiService.save(cauHoiDTO);
        return ResponseEntity.created(new URI("/api/cau-hois/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cau-hois} : Updates an existing cauHoi.
     *
     * @param cauHoiDTO the cauHoiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cauHoiDTO,
     * or with status {@code 400 (Bad Request)} if the cauHoiDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cauHoiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cau-hois")
    public ResponseEntity<CauHoiDTO> updateCauHoi(@RequestBody CauHoiDTO cauHoiDTO) throws URISyntaxException {
        log.debug("REST request to update CauHoi : {}", cauHoiDTO);
        if (cauHoiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CauHoiDTO result = cauHoiService.save(cauHoiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cauHoiDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cau-hois} : get all the cauHois.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cauHois in body.
     */
    @GetMapping("/cau-hois")
    public ResponseEntity<List<CauHoiDTO>> getAllCauHois(Pageable pageable) {
        log.debug("REST request to get a page of CauHois");
        Page<CauHoiDTO> page = cauHoiService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cau-hois/:id} : get the "id" cauHoi.
     *
     * @param id the id of the cauHoiDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cauHoiDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cau-hois/{id}")
    public ResponseEntity<CauHoiDTO> getCauHoi(@PathVariable Long id) {
        log.debug("REST request to get CauHoi : {}", id);
        Optional<CauHoiDTO> cauHoiDTO = cauHoiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cauHoiDTO);
    }

    /**
     * {@code DELETE  /cau-hois/:id} : delete the "id" cauHoi.
     *
     * @param id the id of the cauHoiDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cau-hois/{id}")
    public ResponseEntity<Void> deleteCauHoi(@PathVariable Long id) {
        log.debug("REST request to delete CauHoi : {}", id);
        cauHoiService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/cau-hois?query=:query} : search for the cauHoi corresponding
     * to the query.
     *
     * @param query the query of the cauHoi search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/cau-hois")
    public ResponseEntity<List<CauHoiDTO>> searchCauHois(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of CauHois for query {}", query);
        Page<CauHoiDTO> page = cauHoiService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
        }
}
