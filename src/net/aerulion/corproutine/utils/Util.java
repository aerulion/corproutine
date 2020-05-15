package net.aerulion.corproutine.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import net.aerulion.corproutine.Main;

public class Util {

	public static String PluginChatPrefix = "§7[§e§lRoutine§7]§7 ";
	public final static List<Material> DyeItems = Arrays.asList(Material.INK_SAC, Material.LAPIS_LAZULI, Material.COCOA, Material.CYAN_DYE, Material.GREEN_DYE, Material.GRAY_DYE, Material.LIGHT_BLUE_DYE, Material.LIGHT_GRAY_DYE, Material.LIME_DYE, Material.MAGENTA_DYE, Material.ORANGE_DYE, Material.PINK_DYE, Material.PURPLE_DYE, Material.RED_DYE, Material.BONE_MEAL, Material.YELLOW_DYE);

	public static int countExpired() {
		try {
			loadAllData();
		} catch (SQLException e) {
		}
		int Expired = 0;
		for (Routineaufgabe RA : Main.Routineaufgaben.values()) {
			if (RA.getIsExpired())
				Expired++;
		}
		return Expired;
	}

	public static List<String> convertNames(String Names) {
		List<String> NamesList = new ArrayList<String>();
		for (String s : Names.replaceAll(" ", "").split(",")) {
			NamesList.add(s);
		}
		return NamesList;
	}

	public static String convertDate(String time) {
		long unixSeconds = Long.parseLong(time);
		Date date = new Date(unixSeconds * 1000L);
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		return sdf.format(date);
	}

	@SuppressWarnings("deprecation")
	public static void setPlayerHeadTexturesAsync(Player player) {
		Bukkit.getServer().getScheduler().runTaskAsynchronously(Main.plugin, () -> {
			for (ItemStack is : player.getOpenInventory().getTopInventory()) {
				if (is != null) {
					if (is.getType().equals(Material.PLAYER_HEAD)) {
						if (is.getItemMeta().getDisplayName().startsWith("§a§l")) {
							SkullMeta skullMeta = (SkullMeta) is.getItemMeta();
							skullMeta.setOwner(skullMeta.getDisplayName().substring(4));
							is.setItemMeta(skullMeta);
						}
					}
				}
			}
		});
	}

	public static String formatCycle(String Cycle) {
		try {
			int CycleInt = Integer.parseInt(Cycle);
			if (CycleInt % 365 == 0) {
				return CycleInt / 365 == 1 ? "§ajährlich" : "§a" + CycleInt / 365 + "-jährlich";
			}
			if (CycleInt % 30 == 0) {
				return CycleInt / 30 == 1 ? "§amonatlich" : "§a" + CycleInt / 30 + "-monatlich";
			}
			if (CycleInt % 7 == 0) {
				return CycleInt / 7 == 1 ? "§awöchentlich" : "§a" + CycleInt / 7 + "-wöchentlich";
			}
			return "§a" + CycleInt + " Tage";
		} catch (NumberFormatException e) {
			if (Cycle.equalsIgnoreCase("@lastdayofmonth")) {
				return "§aLetzter Monatstag";
			}
			if (Cycle.equalsIgnoreCase("@mondaybefore_thirdsundayofmonth")) {
				return "§aMontag vor 3. Sonntag";
			}
			if (Cycle.equalsIgnoreCase("@mondaybefore_firstsundayofmonth")) {
				return "§aMontag vor 1. Sonntag";
			}
			return "§a" + Cycle;
		}

	}

	public static String formatCategory(String Category) {
		if (Category.equalsIgnoreCase("gs-delete"))
			return "Grundstücke löschen";
		if (Category.equalsIgnoreCase("gs-check"))
			return "Grundstücke kontrollieren";
		if (Category.equalsIgnoreCase("misc"))
			return "Sonstiges";
		if (Category.equalsIgnoreCase("tech"))
			return "Technik";
		return Category;
	}

	public static List<String> WrapString(String Comment, int width) {
		List<String> WrappedString = new ArrayList<String>();
		String wrapped = WordUtils.wrap(Comment, width, "\n", true);
		for (String s : wrapped.split("\n")) {
			WrappedString.add(s);
		}
		return WrappedString;
	}

	public static void loadAllData() throws SQLException {
		Main.Routineaufgaben.clear();
		ResultSet rs = Main.MySQL.query("SELECT * FROM tool_routine");
		while (rs.next()) {
			Main.Routineaufgaben.put(rs.getInt(1), new Routineaufgabe(rs.getInt(1), rs.getString(4), rs.getString(3), convertDate(rs.getString(5)), rs.getString(7), rs.getString(2), convertNames(rs.getString(6))));
		}
	}

	public static void updateData(EditSession ES) throws SQLException {
		Routineaufgabe RA = Main.Routineaufgaben.get(ES.getRoutineID());
		Main.MySQL.update(ES.getNextDate(), String.join(", ", ES.getDoneBy()), ES.getComment(), ES.getRoutineID(), Bukkit.getOfflinePlayer(ES.getSessionOwner()).getName(), RA.getName());
	}

	public static void playDoneSound(Player player) {
		player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1F, 0.529F);
		Bukkit.getScheduler().runTaskLater(Main.plugin, () -> {
			player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1F, 0.595F);
		}, 2L);
		Bukkit.getScheduler().runTaskLater(Main.plugin, () -> {
			player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1F, 0.667F);
		}, 4L);
		Bukkit.getScheduler().runTaskLater(Main.plugin, () -> {
			player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1F, 0.707F);
		}, 6L);
		Bukkit.getScheduler().runTaskLater(Main.plugin, () -> {
			player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1F, 0.794F);
		}, 8L);
		Bukkit.getScheduler().runTaskLater(Main.plugin, () -> {
			player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1F, 0.89F);
		}, 10L);
		Bukkit.getScheduler().runTaskLater(Main.plugin, () -> {
			player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1F, 0.944F);
		}, 12L);
		Bukkit.getScheduler().runTaskLater(Main.plugin, () -> {
			player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1F, 1.059F);
		}, 14L);
	}

	public static void playAlert(Player player) {
		Bukkit.getScheduler().runTaskLater(Main.plugin, () -> {
			player.sendMessage(Util.PluginChatPrefix + "Es sind §e§l" + Util.countExpired() + "§7 Routineaufgaben überfällig.");
			player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1F, 0.707F);
			player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1F, 0.891F);
			player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1F, 1.059F);
		}, 100L);
		Bukkit.getScheduler().runTaskLater(Main.plugin, () -> {
			player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1F, 1.414F);
			player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1F, 1.782F);
			player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1F, 2F);
		}, 105L);
	}

	public static void doneMessage(Player player, String name) {
		for (Player staffler : Bukkit.getOnlinePlayers()) {
			if (staffler.hasPermission("corproutine.alert")) {
				if (!staffler.getName().equalsIgnoreCase(player.getName())) {
					staffler.sendMessage(Util.PluginChatPrefix + "§e" + player.getName() + "§7 hat die Routineaufgabe §e" + name + " §7abgeschlossen.");
				}
			}
		}
		player.sendMessage(Util.PluginChatPrefix + "§aDu hast die Routineaufgabe §e" + name + " §aabgeschlossen.");
	}
}