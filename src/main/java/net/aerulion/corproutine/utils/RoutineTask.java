package net.aerulion.corproutine.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import lombok.Getter;

@Getter
public class RoutineTask {

  private final int ID;
  private final String cycle;
  private final String name;
  private final String expiryDate;
  private final String comment;
  private final String category;
  private final List<String> doneBy;

  private boolean isExpired;

  public RoutineTask(int ID, String cycle, String name, String expiryDate, String comment,
      String category, List<String> doneBy) {
    this.ID = ID;
    this.cycle = cycle;
    this.name = name;
    this.expiryDate = expiryDate;
    this.comment = comment;
    this.category = category;
    this.doneBy = doneBy;
    checkIfExpired();
  }

  private void checkIfExpired() {
    Date expiryDate;
    try {
      expiryDate = new SimpleDateFormat("dd.MM.yyyy").parse(this.expiryDate);
    } catch (ParseException e) {
      return;
    }
    this.isExpired = expiryDate.before(new Date());
  }
}