package net.aerulion.corproutine.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;

import net.aerulion.corproutine.Main;

public class EditSession {

	private UUID SessionOwner;
	private int RoutineID;
	private String Comment;
	private List<String> DoneBy;
	private String NextDate;

	public EditSession(UUID SessionOwner, int RoutineID) {
		this.SessionOwner = SessionOwner;
		this.RoutineID = RoutineID;
		this.Comment = "-";
		this.DoneBy = new ArrayList<String>();
		this.DoneBy.add(Bukkit.getOfflinePlayer(this.SessionOwner).getName());
		calculateNextDate();
	}

	private void calculateNextDate() {
		Routineaufgabe RA = Main.Routineaufgaben.get(RoutineID);
		try {
			long CycleLong = Long.parseLong(RA.getCycle());
			LocalDate localdate = LocalDate.now(ZoneId.systemDefault()).plusDays(CycleLong);
			this.NextDate = "" + localdate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
		} catch (NumberFormatException e) {
			if (RA.getCycle().equalsIgnoreCase("@lastdayofmonth")) {
				LocalDate localDate = LocalDate.now(ZoneId.systemDefault()).with(TemporalAdjusters.lastDayOfMonth());
				if (localDate.equals(LocalDate.now(ZoneId.systemDefault())))
					localDate = YearMonth.from(localDate.plusMonths(1L)).atEndOfMonth();
				this.NextDate = "" + localDate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
			}
			if (RA.getCycle().equalsIgnoreCase("@mondaybefore_thirdsundayofmonth")) {
				LocalDate localdate = LocalDate.now(ZoneId.systemDefault()).with(TemporalAdjusters.firstDayOfNextMonth()).with(TemporalAdjusters.next(DayOfWeek.SUNDAY)).with(TemporalAdjusters.next(DayOfWeek.SUNDAY)).with(TemporalAdjusters.next(DayOfWeek.SUNDAY)).with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
				this.NextDate = String.valueOf(localdate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond());
			}
			if (RA.getCycle().equalsIgnoreCase("@mondaybefore_firstsundayofmonth")) {
				LocalDate localdate = LocalDate.now(ZoneId.systemDefault()).with(TemporalAdjusters.firstDayOfNextMonth()).with(TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY)).with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
				this.NextDate = String.valueOf(localdate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond());
			}
		}
	}

	public UUID getSessionOwner() {
		return this.SessionOwner;
	}

	public int getRoutineID() {
		return this.RoutineID;
	}

	public String getComment() {
		return this.Comment;
	}

	public void setComment(String Comment) {
		this.Comment = Comment;
	}

	public List<String> getDoneBy() {
		return this.DoneBy;
	}

	public void toggleStaffler(String Name) {
		if (DoneBy.contains(Name))
			DoneBy.remove(Name);
		else
			DoneBy.add(Name);
	}

	public String getNextDate() {
		return this.NextDate;
	}
}