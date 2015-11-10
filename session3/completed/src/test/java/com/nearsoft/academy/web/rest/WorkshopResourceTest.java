package com.nearsoft.academy.web.rest;

import com.nearsoft.academy.Application;
import com.nearsoft.academy.domain.Workshop;
import com.nearsoft.academy.repository.WorkshopRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the WorkshopResource REST controller.
 *
 * @see WorkshopResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class WorkshopResourceTest {

    private static final String DEFAULT_TITLE = "AAAAA";
    private static final String UPDATED_TITLE = "BBBBB";

    private static final Integer DEFAULT_MIN = 1;
    private static final Integer UPDATED_MIN = 2;

    private static final Integer DEFAULT_MAX = 1;
    private static final Integer UPDATED_MAX = 2;

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    @Inject
    private WorkshopRepository workshopRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restWorkshopMockMvc;

    private Workshop workshop;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        WorkshopResource workshopResource = new WorkshopResource();
        ReflectionTestUtils.setField(workshopResource, "workshopRepository", workshopRepository);
        this.restWorkshopMockMvc = MockMvcBuilders.standaloneSetup(workshopResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        workshop = new Workshop();
        workshop.setTitle(DEFAULT_TITLE);
        workshop.setMin(DEFAULT_MIN);
        workshop.setMax(DEFAULT_MAX);
        workshop.setStartDate(DEFAULT_START_DATE);
    }

    @Test
    @Transactional
    public void createWorkshop() throws Exception {
        int databaseSizeBeforeCreate = workshopRepository.findAll().size();

        // Create the Workshop

        restWorkshopMockMvc.perform(post("/api/workshops")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(workshop)))
                .andExpect(status().isCreated());

        // Validate the Workshop in the database
        List<Workshop> workshops = workshopRepository.findAll();
        assertThat(workshops).hasSize(databaseSizeBeforeCreate + 1);
        Workshop testWorkshop = workshops.get(workshops.size() - 1);
        assertThat(testWorkshop.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testWorkshop.getMin()).isEqualTo(DEFAULT_MIN);
        assertThat(testWorkshop.getMax()).isEqualTo(DEFAULT_MAX);
        assertThat(testWorkshop.getStartDate()).isEqualTo(DEFAULT_START_DATE);
    }

    @Test
    @Transactional
    public void getAllWorkshops() throws Exception {
        // Initialize the database
        workshopRepository.saveAndFlush(workshop);

        // Get all the workshops
        restWorkshopMockMvc.perform(get("/api/workshops"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(workshop.getId().intValue())))
                .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
                .andExpect(jsonPath("$.[*].min").value(hasItem(DEFAULT_MIN)))
                .andExpect(jsonPath("$.[*].max").value(hasItem(DEFAULT_MAX)))
                .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())));
    }

    @Test
    @Transactional
    public void getWorkshop() throws Exception {
        // Initialize the database
        workshopRepository.saveAndFlush(workshop);

        // Get the workshop
        restWorkshopMockMvc.perform(get("/api/workshops/{id}", workshop.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(workshop.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.min").value(DEFAULT_MIN))
            .andExpect(jsonPath("$.max").value(DEFAULT_MAX))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingWorkshop() throws Exception {
        // Get the workshop
        restWorkshopMockMvc.perform(get("/api/workshops/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWorkshop() throws Exception {
        // Initialize the database
        workshopRepository.saveAndFlush(workshop);

		int databaseSizeBeforeUpdate = workshopRepository.findAll().size();

        // Update the workshop
        workshop.setTitle(UPDATED_TITLE);
        workshop.setMin(UPDATED_MIN);
        workshop.setMax(UPDATED_MAX);
        workshop.setStartDate(UPDATED_START_DATE);

        restWorkshopMockMvc.perform(put("/api/workshops")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(workshop)))
                .andExpect(status().isOk());

        // Validate the Workshop in the database
        List<Workshop> workshops = workshopRepository.findAll();
        assertThat(workshops).hasSize(databaseSizeBeforeUpdate);
        Workshop testWorkshop = workshops.get(workshops.size() - 1);
        assertThat(testWorkshop.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testWorkshop.getMin()).isEqualTo(UPDATED_MIN);
        assertThat(testWorkshop.getMax()).isEqualTo(UPDATED_MAX);
        assertThat(testWorkshop.getStartDate()).isEqualTo(UPDATED_START_DATE);
    }

    @Test
    @Transactional
    public void deleteWorkshop() throws Exception {
        // Initialize the database
        workshopRepository.saveAndFlush(workshop);

		int databaseSizeBeforeDelete = workshopRepository.findAll().size();

        // Get the workshop
        restWorkshopMockMvc.perform(delete("/api/workshops/{id}", workshop.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Workshop> workshops = workshopRepository.findAll();
        assertThat(workshops).hasSize(databaseSizeBeforeDelete - 1);
    }
}
