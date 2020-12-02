package com.qnu.cnttk40a.web.rest;

import com.qnu.cnttk40a.service.TaiKhoanService;
import com.qnu.cnttk40a.web.rest.errors.BadRequestAlertException;
import com.qnu.cnttk40a.service.dto.TaiKhoanDTO;

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
 * REST controller for managing {@link com.qnu.cnttk40a.domain.TaiKhoan}.
 */
@RestController
@RequestMapping("/api")
public class TaiKhoanResource {

    private final Logger log = LoggerFactory.getLogger(TaiKhoanResource.class);

    private static final String ENTITY_NAME = "taiKhoan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TaiKhoanService taiKhoanService;

    public TaiKhoanResource(TaiKhoanService taiKhoanService) {
        this.taiKhoanService = taiKhoanService;
    }

    /**
     * {@code POST  /tai-khoans} : Create a new taiKhoan.
     *
     * @param taiKhoanDTO the taiKhoanDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new taiKhoanDTO, or with status {@code 400 (Bad Request)} if the taiKhoan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tai-khoans")
    public ResponseEntity<TaiKhoanDTO> createTaiKhoan(@RequestBody TaiKhoanDTO taiKhoanDTO) throws URISyntaxException {
        log.debug("REST request to save TaiKhoan : {}", taiKhoanDTO);
        if (taiKhoanDTO.getId() != null) {
            throw new BadRequestAlertException("A new taiKhoan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TaiKhoanDTO result = taiKhoanService.save(taiKhoanDTO);
        return ResponseEntity.created(new URI("/api/tai-khoans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tai-khoans} : Updates an existing taiKhoan.
     *
     * @param taiKhoanDTO the taiKhoanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taiKhoanDTO,
     * or with status {@code 400 (Bad Request)} if the taiKhoanDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the taiKhoanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tai-khoans")
    public ResponseEntity<TaiKhoanDTO> updateTaiKhoan(@RequestBody TaiKhoanDTO taiKhoanDTO) throws URISyntaxException {
        log.debug("REST request to update TaiKhoan : {}", taiKhoanDTO);
        if (taiKhoanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TaiKhoanDTO result = taiKhoanService.save(taiKhoanDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, taiKhoanDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tai-khoans} : get all the taiKhoans.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of taiKhoans in body.
     */
    @GetMapping("/tai-khoans")
    public ResponseEntity<List<TaiKhoanDTO>> getAllTaiKhoans(Pageable pageable) {
        log.debug("REST request to get a page of TaiKhoans");
        Page<TaiKhoanDTO> page = taiKhoanService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /tai-khoans/:id} : get the "id" taiKhoan.
     *
     * @param id the id of the taiKhoanDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the taiKhoanDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tai-khoans/{id}")
    public ResponseEntity<TaiKhoanDTO> getTaiKhoan(@PathVariable Long id) {
        log.debug("REST request to get TaiKhoan : {}", id);
        Optional<TaiKhoanDTO> taiKhoanDTO = taiKhoanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(taiKhoanDTO);
    }

    /**
     * {@code DELETE  /tai-khoans/:id} : delete the "id" taiKhoan.
     *
     * @param id the id of the taiKhoanDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tai-khoans/{id}")
    public ResponseEntity<Void> deleteTaiKhoan(@PathVariable Long id) {
        log.debug("REST request to delete TaiKhoan : {}", id);
        taiKhoanService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/tai-khoans?query=:query} : search for the taiKhoan corresponding
     * to the query.
     *
     * @param query the query of the taiKhoan search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/tai-khoans")
    public ResponseEntity<List<TaiKhoanDTO>> searchTaiKhoans(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of TaiKhoans for query {}", query);
        Page<TaiKhoanDTO> page = taiKhoanService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
        }
}
