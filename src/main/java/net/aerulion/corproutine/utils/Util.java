package net.aerulion.corproutine.utils;

import net.aerulion.corproutine.Main;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.text.SimpleDateFormat;
import java.util.*;

public class Util {
    public final static List<Material> DyeItems = Arrays.asList(Material.INK_SAC, Material.LAPIS_LAZULI, Material.COCOA, Material.CYAN_DYE, Material.GREEN_DYE, Material.GRAY_DYE, Material.LIGHT_BLUE_DYE, Material.LIGHT_GRAY_DYE, Material.LIME_DYE, Material.MAGENTA_DYE, Material.ORANGE_DYE, Material.PINK_DYE, Material.PURPLE_DYE, Material.RED_DYE, Material.BONE_MEAL, Material.YELLOW_DYE);

    public static int countExpired() {
        int Expired = 0;
        for (RoutineTask routineTask : Main.ROUTINE_TASKS.values()) {
            if (routineTask.isExpired())
                Expired++;
        }
        return Expired;
    }

    public static List<String> convertNames(String Names) {
        List<String> NamesList = new ArrayList<>();
        Collections.addAll(NamesList, Names.replaceAll(" ", "").split(","));
        return NamesList;
    }

    public static String convertDate(String time) {
        long unixSeconds = Long.parseLong(time);
        Date date = new Date(unixSeconds * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(date);
    }

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
            if (Cycle.equalsIgnoreCase("@sunday")) {
                return "§aSonntags";
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
        List<String> WrappedString = new ArrayList<>();
        String wrapped = WordUtils.wrap(Comment, width, "\n", true);
        Collections.addAll(WrappedString, wrapped.split("\n"));
        return WrappedString;
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
            player.sendMessage(Messages.MESSAGE_EXPIRED_TASKS.get().replaceAll("\\{COUNT}", String.valueOf(Util.countExpired())));
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

    public static void doneMessage(Player staffler, String name) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission(Permissions.ALERT.get())) {
                if (!player.getName().equalsIgnoreCase(staffler.getName())) {
                    player.sendMessage(Messages.MESSAGE_TASK_DONE.get().replaceAll("\\{PLAYER}", staffler.getName()).replaceAll("\\{TASK}", name));
                }
            }
        }
        staffler.sendMessage(Messages.MESSAGE_TASK_DONE_SELF.get().replaceAll("\\{TASK}", name));
    }
}