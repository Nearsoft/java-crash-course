package com.nearsoft.academy.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.nearsoft.academy.domain.Workshop;
import com.nearsoft.academy.repository.WorkshopRepository;
import com.nearsoft.academy.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Workshop.
 */
@RestController
@RequestMapping("/api")
public class WorkshopResource {

    private final Logger log = LoggerFactory.getLogger(WorkshopResource.class);

    @Inject
    private WorkshopRepository workshopRepository;

    /**
     * POST  /workshops -> Create a new workshop.
     */
    @RequestMapping(value = "/workshops",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Workshop> createWorkshop(@RequestBody Workshop workshop) throws URISyntaxException {
        log.debug("REST request to save Workshop : {}", workshop);
        if (workshop.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new workshop cannot already have an ID").body(null);
        }
        Workshop result = workshopRepository.save(workshop);
        return ResponseEntity.created(new URI("/api/workshops/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("workshop", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /workshops -> Updates an existing workshop.
     */
    @RequestMapping(value = "/workshops",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Workshop> updateWorkshop(@RequestBody Workshop workshop) throws URISyntaxException {
        log.debug("REST request to update Workshop : {}", workshop);
        if (workshop.getId() == null) {
            return createWorkshop(workshop);
        }
        Workshop result = workshopRepository.save(workshop);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("workshop", workshop.getId().toString()))
            .body(result);
    }

    /**
     * GET  /workshops -> get all the workshops.
     */
    @RequestMapping(value = "/workshops",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Workshop> getAllWorkshops() {
        log.debug("REST request to get all Workshops");
        return workshopRepository.findAll();
    }

    /**
     * GET  /workshops/:id -> get the "id" workshop.
     */
    @RequestMapping(value = "/workshops/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Workshop> getWorkshop(@PathVariable Long id) {
        log.debug("REST request to get Workshop : {}", id);
        return Optional.ofNullable(workshopRepository.findOne(id))
            .map(workshop -> new ResponseEntity<>(
                workshop,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /workshops/:id -> delete the "id" workshop.
     */
    @RequestMapping(value = "/workshops/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteWorkshop(@PathVariable Long id) {
        log.debug("REST request to delete Workshop : {}", id);
        workshopRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("workshop", id.toString())).build();
    }
}
