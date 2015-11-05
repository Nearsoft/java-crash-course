package com.nearsoft.domain;


import com.nearsoft.exceptions.WorkshopGroupIsFullException;
import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by ilopez on 10/17/15.
 */
public class WorkshopTest {

    @Test
    public void workshopGroupCanBeOpenedIfEnrolledStudentsAreMoreThanMin() {
        Workshop java = new Workshop("Java Advanced", 3, 1, new Date());

        assertFalse(java.canBeOpened());
        java.enroll(new Student("John", "Doe", "foo@doe.com"));
        assertTrue(java.canBeOpened());

    }














    @Test(expected = WorkshopGroupIsFullException.class)
    public void workshopCanNotHaveMoreThanMaxStudents() {

        Workshop java = new Workshop("Java Super Advanced", 1, 1, new Date());
        java.enroll(new Student("John", "Doe", "foo@doe.com"));
        java.enroll(new Student("Lisa", "Unlucky", "lisa@unlucky.com"));

    }
}