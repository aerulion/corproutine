package net.aerulion.corproutine.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Routineaufgabe {

    private final int AufgabenID;
    private final String Cycle;
    private final String Name;
    private final String ExpireDate;
    private final String Comment;
    private final String Category;
    private final List<String> DoneBy;

    private boolean IsExpired;

    public Routineaufgabe(int ID, String CYCLE, String NAME, String EXPIREDATE, String COMMENT, String CATEGORY, List<String> DONEBY) {
        this.AufgabenID = ID;
        this.Cycle = CYCLE;
        this.Name = NAME;
        this.ExpireDate = EXPIREDATE;
        this.Comment = COMMENT;
        this.Category = CATEGORY;
        this.DoneBy = DONEBY;
        checkIfExpired();
    }

    public int getID() {
        return this.AufgabenID;
    }

    public String getCycle() {
        return this.Cycle;
    }

    public String getName() {
        return this.Name;
    }

    public String getExpireDate() {
        return this.ExpireDate;
    }

    public String getComment() {
        return this.Comment;
    }

    public String getCategory() {
        return this.Category;
    }

    public List<String> getDoneBy() {
        return this.DoneBy;
    }

    public boolean getIsExpired() {
        return this.IsExpired;
    }

    private void checkIfExpired() {
        Date ExpireDate;
        try {
            ExpireDate = new SimpleDateFormat("dd.MM.yyyy").parse(this.ExpireDate);
        } catch (ParseException e) {
            return;
        }
        this.IsExpired = ExpireDate.before(new Date());
    }
}