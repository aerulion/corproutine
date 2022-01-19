package net.aerulion.corproutine.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import net.aerulion.corproutine.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Util {

  public static final List<Material> DyeItems = Arrays.asList(Material.INK_SAC,
      Material.LAPIS_LAZULI, Material.COCOA, Material.CYAN_DYE, Material.GREEN_DYE,
      Material.GRAY_DYE, Material.LIGHT_BLUE_DYE, Material.LIGHT_GRAY_DYE, Material.LIME_DYE,
      Material.MAGENTA_DYE, Material.ORANGE_DYE, Material.PINK_DYE, Material.PURPLE_DYE,
      Material.RED_DYE, Material.BONE_MEAL, Material.YELLOW_DYE);

  public static int countExpired() {
    int Expired = 0;
    for (final @NotNull RoutineTask routineTask : Main.ROUTINE_TASKS.values()) {
      if (routineTask.isExpired()) {
        Expired++;
      }
    }
    return Expired;
  }

  public static @NotNull List<String> convertNames(final @NotNull String Names) {
    final @NotNull List<String> NamesList = new ArrayList<>();
    Collections.addAll(NamesList, Names.replaceAll(" ", "").split(","));
    return NamesList;
  }

  public static @NotNull String convertDate(final @NotNull String time) {
    final long unixSeconds = Long.parseLong(time);
    final @NotNull Date date = new Date(unixSeconds * 1000L);
    final @NotNull SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    return sdf.format(date);
  }

  public static void setPlayerHeadTexturesAsync(final @NotNull Player player) {
    Bukkit.getServer().getScheduler().runTaskAsynchronously(Main.plugin, () -> {
      for (final @Nullable ItemStack is : player.getOpenInventory().getTopInventory()) {
        if (is != null) {
          if (is.getType() == Material.PLAYER_HEAD) {
            if (is.getItemMeta().getDisplayName().startsWith("§a§l")) {
              final @NotNull SkullMeta skullMeta = (SkullMeta) is.getItemMeta();
              skullMeta.setOwner(skullMeta.getDisplayName().substring(4));
              is.setItemMeta(skullMeta);
            }
          }
        }
      }
    });
  }

  public static @NotNull String formatCycle(final @NotNull String Cycle) {
    try {
      final int CycleInt = Integer.parseInt(Cycle);
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
    } catch (final NumberFormatException e) {
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

  public static @NotNull String formatCategory(final @NotNull String Category) {
    if (Category.equalsIgnoreCase("gs-delete")) {
      return "Grundstücke löschen";
    }
    if (Category.equalsIgnoreCase("gs-check")) {
      return "Grundstücke kontrollieren";
    }
    if (Category.equalsIgnoreCase("misc")) {
      return "Sonstiges";
    }
    if (Category.equalsIgnoreCase("tech")) {
      return "Technik";
    }
    return Category;
  }

  public static @NotNull List<String> WrapString(final String Comment, final int width) {
    final @NotNull List<String> WrappedString = new ArrayList<>();
    final String wrapped = WordUtils.wrap(Comment, width, "\n", true);
    Collections.addAll(WrappedString, wrapped.split("\n"));
    return WrappedString;
  }

  public static void playDoneSound(final @NotNull Player player) {
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

  public static void playAlert(final @NotNull Player player) {
    Bukkit.getScheduler().runTaskLaterAsynchronously(Main.plugin, () -> {
      if (countExpired() > 0) {
        player.sendMessage(Messages.MESSAGE_EXPIRED_TASKS.get().replaceText(
            TextReplacementConfig.builder().replacement(String.valueOf(countExpired()))
                .match("%count%").build()));
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1F, 0.707F);
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1F, 0.891F);
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1F, 1.059F);
        Bukkit.getScheduler().runTaskLaterAsynchronously(Main.plugin, () -> {
          player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1F, 1.414F);
          player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1F, 1.782F);
          player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1F, 2F);
        }, 5L);
      }
    }, 100L);
  }

  public static void doneMessage(final @NotNull Player staffler, final @NotNull String taskName) {
    final @NotNull Component message = Messages.MESSAGE_TASK_DONE.get().replaceText(
            TextReplacementConfig.builder().replacement(staffler.getName()).match("%player%").build())
        .replaceText(TextReplacementConfig.builder().replacement(taskName).match("%task%").build());
    Bukkit.getOnlinePlayers().stream().filter(
        player -> player.hasPermission(Permissions.ALERT.get()) && !player.getName()
            .equalsIgnoreCase(staffler.getName())).forEach(player -> player.sendMessage(message));
    staffler.sendMessage(Messages.MESSAGE_TASK_DONE_SELF.get().replaceText(
        TextReplacementConfig.builder().replacement(taskName).match("%task%").build()));
  }
}