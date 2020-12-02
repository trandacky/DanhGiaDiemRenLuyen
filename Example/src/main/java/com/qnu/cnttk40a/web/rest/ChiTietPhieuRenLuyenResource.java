package com.qnu.cnttk40a.web.rest;

import com.qnu.cnttk40a.service.ChiTietPhieuRenLuyenService;
import com.qnu.cnttk40a.web.rest.errors.BadRequestAlertException;
import com.qnu.cnttk40a.service.dto.ChiTietPhieuRenLuyenDTO;

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
 * REST controller for managing {@link com.qnu.cnttk40a.domain.ChiTietPhieuRenLuyen}.
 */
@RestController
@RequestMapping("/api")
public class ChiTietPhieuRenLuyenResource {

    private final Logger log = LoggerFactory.getLogger(ChiTietPhieuRenLuyenResource.class);

    private static final String ENTITY_NAME = "chiTietPhieuRenLuyen";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ChiTietPhieuRenLuyenService chiTietPhieuRenLuyenService;

    public ChiTietPhieuRenLuyenResource(ChiTietPhieuRenLuyenService chiTietPhieuRenLuyenService) {
        this.chiTietPhieuRenLuyenService = chiTietPhieuRenLuyenService;
    }

    /**
     * {@code POST  /chi-tiet-phieu-ren-luyens} : Create a new chiTietPhieuRenLuyen.
     *
     * @param chiTietPhieuRenLuyenDTO the chiTietPhieuRenLuyenDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new chiTietPhieuRenLuyenDTO, or with status {@code 400 (Bad Request)} if the chiTietPhieuRenLuyen has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/chi-tiet-phieu-ren-luyens")
    public ResponseEntity<ChiTietPhieuRenLuyenDTO> createChiTietPhieuRenLuyen(@RequestBody ChiTietPhieuRenLuyenDTO chiTietPhieuRenLuyenDTO) throws URISyntaxException {
        log.debug("REST request to save ChiTietPhieuRenLuyen : {}", chiTietPhieuRenLuyenDTO);
        if (chiTietPhieuRenLuyenDTO.getId() != null) {
            throw new BadRequestAlertException("A new chiTietPhieuRenLuyen cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ChiTietPhieuRenLuyenDTO result = chiTietPhieuRenLuyenService.save(chiTietPhieuRenLuyenDTO);
        return ResponseEntity.created(new URI("/api/chi-tiet-phieu-ren-luyens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /chi-tiet-phieu-ren-luyens} : Updates an existing chiTietPhieuRenLuyen.
     *
     * @param chiTietPhieuRenLuyenDTO the chiTietPhieuRenLuyenDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chiTietPhieuRenLuyenDTO,
     * or with status {@code 400 (Bad Request)} if the chiTietPhieuRenLuyenDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the chiTietPhieuRenLuyenDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/chi-tiet-phieu-ren-luyens")
    public ResponseEntity<ChiTietPhieuRenLuyenDTO> updateChiTietPhieuRenLuyen(@RequestBody ChiTietPhieuRenLuyenDTO chiTietPhieuRenLuyenDTO) throws URISyntaxException {
        log.debug("REST request to update ChiTietPhieuRenLuyen : {}", chiTietPhieuRenLuyenDTO);
        if (chiTietPhieuRenLuyenDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ChiTietPhieuRenLuyenDTO result = chiTietPhieuRenLuyenService.save(chiTietPhieuRenLuyenDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, chiTietPhieuRenLuyenDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /chi-tiet-phieu-ren-luyens} : get all the chiTietPhieuRenLuyens.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of chiTietPhieuRenLuyens in body.
     */
    @GetMapping("/chi-tiet-phieu-ren-luyens")
    public ResponseEntity<List<ChiTietPhieuRenLuyenDTO>> getAllChiTietPhieuRenLuyens(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("phieurenluyen-is-null".equals(filter)) {
            log.debug("REST request to get all ChiTietPhieuRenLuyens where phieuRenLuyen is null");
            return new ResponseEntity<>(chiTietPhieuRenLuyenService.findAllWherePhieuRenLuyenIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of ChiTietPhieuRenLuyens");
        Page<ChiTietPhieuRenLuyenDTO> page = chiTietPhieuRenLuyenService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /chi-tiet-phieu-ren-luyens/:id} : get the "id" chiTietPhieuRenLuyen.
     *
     * @param id the id of the chiTietPhieuRenLuyenDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the chiTietPhieuRenLuyenDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/chi-tiet-phieu-ren-luyens/{id}")
    public ResponseEntity<ChiTietPhieuRenLuyenDTO> getChiTietPhieuRenLuyen(@PathVariable Long id) {
        log.debug("REST request to get ChiTietPhieuRenLuyen : {}", id);
        Optional<ChiTietPhieuRenLuyenDTO> chiTietPhieuRenLuyenDTO = chiTietPhieuRenLuyenService.findOne(id);
        return ResponseUtil.wrapOrNotFound(chiTietPhieuRenLuyenDTO);
    }

    /**
     * {@code DELETE  /chi-tiet-phieu-ren-luyens/:id} : delete the "id" chiTietPhieuRenLuyen.
     *
     * @param id the id of the chiTietPhieuRenLuyenDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/chi-tiet-phieu-ren-luyens/{id}")
    public ResponseEntity<Void> deleteChiTietPhieuRenLuyen(@PathVariable Long id) {
        log.debug("REST request to delete ChiTietPhieuRenLuyen : {}", id);
        chiTietPhieuRenLuyenService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/chi-tiet-phieu-ren-luyens?query=:query} : search for the chiTietPhieuRenLuyen corresponding
     * to the query.
     *
     * @param query the query of the chiTietPhieuRenLuyen search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/chi-tiet-phieu-ren-luyens")
    public ResponseEntity<List<ChiTietPhieuRenLuyenDTO>> searchChiTietPhieuRenLuyens(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of ChiTietPhieuRenLuyens for query {}", query);
        Page<ChiTietPhieuRenLuyenDTO> page = chiTietPhieuRenLuyenService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
        }
}
