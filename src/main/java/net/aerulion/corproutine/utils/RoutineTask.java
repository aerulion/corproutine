package net.aerulion.corproutine.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RoutineTask {

    private final int ID;
    private final String CYCLE;
    private final String NAME;
    private final String EXPIRY_DATE;
    private final String COMMENT;
    private final String CATEGORY;
    private final List<String> DONE_BY;

    private boolean isExpired;

    public RoutineTask(int ID, String cycle, String name, String expiryDate, String comment, String category, List<String> doneBy) {
        this.ID = ID;
        this.CYCLE = cycle;
        this.NAME = name;
        this.EXPIRY_DATE = expiryDate;
        this.COMMENT = comment;
        this.CATEGORY = category;
        this.DONE_BY = doneBy;
        checkIfExpired();
    }

    public int getID() {
        return this.ID;
    }

    public String getCycle() {
        return this.CYCLE;
    }

    public String getName() {
        return this.NAME;
    }

    public String getExpiryDate() {
        return this.EXPIRY_DATE;
    }

    public String getComment() {
        return this.COMMENT;
    }

    public String getCategory() {
        return this.CATEGORY;
    }

    public List<String> getDoneBy() {
        return this.DONE_BY;
    }

    public boolean isExpired() {
        return this.isExpired;
    }

    private void checkIfExpired() {
        Date expiryDate;
        try {
            expiryDate = new SimpleDateFormat("dd.MM.yyyy").parse(this.EXPIRY_DATE);
        } catch (ParseException e) {
            return;
        }
        this.isExpired = expiryDate.before(new Date());
    }
}