package com.qnu.cnttk40a.web.rest;

import com.qnu.cnttk40a.service.TongDiemService;
import com.qnu.cnttk40a.web.rest.errors.BadRequestAlertException;
import com.qnu.cnttk40a.service.dto.TongDiemDTO;

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
 * REST controller for managing {@link com.qnu.cnttk40a.domain.TongDiem}.
 */
@RestController
@RequestMapping("/api")
public class TongDiemResource {

    private final Logger log = LoggerFactory.getLogger(TongDiemResource.class);

    private static final String ENTITY_NAME = "tongDiem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TongDiemService tongDiemService;

    public TongDiemResource(TongDiemService tongDiemService) {
        this.tongDiemService = tongDiemService;
    }

    /**
     * {@code POST  /tong-diems} : Create a new tongDiem.
     *
     * @param tongDiemDTO the tongDiemDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tongDiemDTO, or with status {@code 400 (Bad Request)} if the tongDiem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tong-diems")
    public ResponseEntity<TongDiemDTO> createTongDiem(@RequestBody TongDiemDTO tongDiemDTO) throws URISyntaxException {
        log.debug("REST request to save TongDiem : {}", tongDiemDTO);
        if (tongDiemDTO.getId() != null) {
            throw new BadRequestAlertException("A new tongDiem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TongDiemDTO result = tongDiemService.save(tongDiemDTO);
        return ResponseEntity.created(new URI("/api/tong-diems/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tong-diems} : Updates an existing tongDiem.
     *
     * @param tongDiemDTO the tongDiemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tongDiemDTO,
     * or with status {@code 400 (Bad Request)} if the tongDiemDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tongDiemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tong-diems")
    public ResponseEntity<TongDiemDTO> updateTongDiem(@RequestBody TongDiemDTO tongDiemDTO) throws URISyntaxException {
        log.debug("REST request to update TongDiem : {}", tongDiemDTO);
        if (tongDiemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TongDiemDTO result = tongDiemService.save(tongDiemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tongDiemDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tong-diems} : get all the tongDiems.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tongDiems in body.
     */
    @GetMapping("/tong-diems")
    public ResponseEntity<List<TongDiemDTO>> getAllTongDiems(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("phieurenluyen-is-null".equals(filter)) {
            log.debug("REST request to get all TongDiems where phieuRenLuyen is null");
            return new ResponseEntity<>(tongDiemService.findAllWherePhieuRenLuyenIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of TongDiems");
        Page<TongDiemDTO> page = tongDiemService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /tong-diems/:id} : get the "id" tongDiem.
     *
     * @param id the id of the tongDiemDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tongDiemDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tong-diems/{id}")
    public ResponseEntity<TongDiemDTO> getTongDiem(@PathVariable Long id) {
        log.debug("REST request to get TongDiem : {}", id);
        Optional<TongDiemDTO> tongDiemDTO = tongDiemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tongDiemDTO);
    }

    /**
     * {@code DELETE  /tong-diems/:id} : delete the "id" tongDiem.
     *
     * @param id the id of the tongDiemDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tong-diems/{id}")
    public ResponseEntity<Void> deleteTongDiem(@PathVariable Long id) {
        log.debug("REST request to delete TongDiem : {}", id);
        tongDiemService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/tong-diems?query=:query} : search for the tongDiem corresponding
     * to the query.
     *
     * @param query the query of the tongDiem search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/tong-diems")
    public ResponseEntity<List<TongDiemDTO>> searchTongDiems(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of TongDiems for query {}", query);
        Page<TongDiemDTO> page = tongDiemService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
        }
}
