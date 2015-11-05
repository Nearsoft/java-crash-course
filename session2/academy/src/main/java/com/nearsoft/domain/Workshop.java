package com.nearsoft.domain;

import com.nearsoft.exceptions.WorkshopGroupIsFullException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ilopez on 10/16/15.
 */
public class Workshop {
    private String title;
    private int maxGroupSize;
    private int minGroupSize;
    private Date startDate;
    private List<Student> students = new ArrayList<Student>();

    public Workshop(String title, int maxGroupSize,
                    int minGroupSize, Date startDate) {
        this.title = title;
        this.maxGroupSize = maxGroupSize;
        this.minGroupSize = minGroupSize;
        this.startDate = startDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMaxGroupSize() {
        return maxGroupSize;
    }

    public void setMaxGroupSize(int maxGroupSize) {
        this.maxGroupSize = maxGroupSize;
    }

    public int getMinGroupSize() {
        return minGroupSize;
    }

    public void setMinGroupSize(int minGroupSize) {
        this.minGroupSize = minGroupSize;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


    public boolean canBeOpened() {
        return students.size() >= getMinGroupSize();
    }

    public void enroll(Student student)  {
        if (students.size() < getMaxGroupSize()){
            students.add(student);
        }else{
            throw new WorkshopGroupIsFullException();
        }

    }
}
