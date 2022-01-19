package net.aerulion.corproutine.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

public class ItemBuilder {

  public static @NotNull ItemStack createRoutineItemStack(final @NotNull RoutineTask RA) {
    final @NotNull ItemStack RoutineItem = new ItemStack(
        RA.isExpired() ? Material.RED_DYE : Material.LIME_DYE);
    final ItemMeta mRoutineItem = RoutineItem.getItemMeta();
    mRoutineItem.setDisplayName("§e§l" + RA.getName());
    final @NotNull List<String> Lore = new ArrayList<>();
    Lore.add("§8Aufgaben-ID: " + RA.getID());
    Lore.add("§7Kategorie: §a" + Util.formatCategory(RA.getCategory()));
    Lore.add("§7Intervall: " + Util.formatCycle(RA.getCycle()));
    Lore.add("§7Fällig am: §a" + RA.getExpiryDate());
    Lore.add("§7Zuletzt erledigt von: §a" + String.join("§7, §a", RA.getDoneBy()));
    Lore.add("§7Kommentar: §a");
    for (final String s : Util.WrapString(RA.getComment(), 30)) {
      Lore.add("   §a" + s);
    }
    mRoutineItem.setLore(Lore);
    RoutineItem.setItemMeta(mRoutineItem);
    return RoutineItem;
  }

  public static @NotNull ItemStack createSpacerGlassPane() {
    final @NotNull ItemStack GlassPane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
    final ItemMeta mGlassPane = GlassPane.getItemMeta();
    mGlassPane.setDisplayName("§8§l-");
    GlassPane.setItemMeta(mGlassPane);
    return GlassPane;
  }

  public static @NotNull ItemStack createDoneByBook() {
    final @NotNull ItemStack Book = new ItemStack(Material.BOOK);
    final ItemMeta mBook = Book.getItemMeta();
    mBook.setDisplayName("§e§lZuletzt erledigt von:");
    Book.setItemMeta(mBook);
    return Book;
  }

  public static @NotNull ItemStack createWhoHelpedBook() {
    final @NotNull ItemStack Book = new ItemStack(Material.BOOK);
    final ItemMeta mBook = Book.getItemMeta();
    mBook.setDisplayName("§e§lBeteiligt waren:");
    Book.setItemMeta(mBook);
    return Book;
  }

  public static @NotNull ItemStack createPlayerHead(final String PlayerName, final boolean Active) {
    final @NotNull ItemStack Skull;
    if (Active) {
      Skull = new ItemStack(Material.PLAYER_HEAD);
      final @NotNull SkullMeta mSkull = (SkullMeta) Skull.getItemMeta();
      mSkull.setDisplayName("§a§l" + PlayerName);
      Skull.setItemMeta(mSkull);
    } else {
      Skull = new ItemStack(Material.SKELETON_SKULL);
      final ItemMeta mSkull = Skull.getItemMeta();
      mSkull.setDisplayName("§c§l" + PlayerName);
      Skull.setItemMeta(mSkull);
    }
    return Skull;
  }

  public static @NotNull ItemStack createNoNameBarrier() {
    final @NotNull ItemStack Barrier = new ItemStack(Material.BARRIER);
    final ItemMeta mBarrier = Barrier.getItemMeta();
    mBarrier.setDisplayName("§cKein Name angegeben");
    Barrier.setItemMeta(mBarrier);
    return Barrier;
  }

  public static @NotNull ItemStack createExpireDateClock(final String ExpireDate) {
    final @NotNull ItemStack Clock = new ItemStack(Material.CLOCK);
    final ItemMeta mClock = Clock.getItemMeta();
    mClock.setDisplayName("§e§lFällig am:");
    mClock.setLore(Arrays.asList("§a" + ExpireDate));
    Clock.setItemMeta(mClock);
    return Clock;
  }

  public static @NotNull ItemStack createNextDateClock(final String NextDate) {
    final @NotNull ItemStack Clock = new ItemStack(Material.CLOCK);
    final ItemMeta mClock = Clock.getItemMeta();
    mClock.setDisplayName("§e§lWieder fällig am:");
    mClock.setLore(Arrays.asList("§a" + NextDate));
    Clock.setItemMeta(mClock);
    return Clock;
  }

  public static @NotNull ItemStack createButtonDye(final boolean IsExpired) {
    final @NotNull ItemStack Dye = new ItemStack(IsExpired ? Material.LIME_DYE : Material.GRAY_DYE);
    final ItemMeta mDye = Dye.getItemMeta();
    mDye.setDisplayName(
        IsExpired ? "§a§lRoutineaufgabe abschließen" : "§7§lRoutineaufgabe noch nicht notwendig");
    mDye.setLore(
        IsExpired ? null : Arrays.asList("§7§oMittelklick um die Aufgabe dennoch abzuschließen"));
    Dye.setItemMeta(mDye);
    return Dye;
  }

  public static @NotNull ItemStack createCommentPaper(final String Comment) {
    final @NotNull ItemStack Paper = new ItemStack(Material.PAPER);
    final ItemMeta mPaper = Paper.getItemMeta();
    mPaper.setDisplayName("§e§lKommentar:");
    final @NotNull List<String> Lore = new ArrayList<>();
    for (final String s : Util.WrapString(Comment, 30)) {
      Lore.add("   §a" + s);
    }
    mPaper.setLore(Lore);
    Paper.setItemMeta(mPaper);
    return Paper;
  }

  public static @NotNull ItemStack createMainMenuDoor() {
    final @NotNull ItemStack Door = new ItemStack(Material.DARK_OAK_DOOR, 1);
    final ItemMeta mDoor = Door.getItemMeta();
    mDoor.setDisplayName("§e§lZurück zum Hauptmenü");
    Door.setItemMeta(mDoor);
    return Door;
  }

  public static @NotNull ItemStack createDoneButtonKnowledgeBook() {
    final @NotNull ItemStack KnowledgeBook = new ItemStack(Material.KNOWLEDGE_BOOK);
    final ItemMeta mKnowledgeBook = KnowledgeBook.getItemMeta();
    mKnowledgeBook.setDisplayName("§a§lAufgabe abschließen");
    KnowledgeBook.setItemMeta(mKnowledgeBook);
    return KnowledgeBook;
  }

  public static @NotNull ItemStack createCancelButtonBarrier() {
    final @NotNull ItemStack Barrier = new ItemStack(Material.BARRIER);
    final ItemMeta mBarrier = Barrier.getItemMeta();
    mBarrier.setDisplayName("§c§lAbbrechen");
    Barrier.setItemMeta(mBarrier);
    return Barrier;
  }

  public static @NotNull ItemStack createAdditionalHelperNameTag() {
    final @NotNull ItemStack NameTag = new ItemStack(Material.NAME_TAG);
    final ItemMeta mNameTag = NameTag.getItemMeta();
    mNameTag.setDisplayName("§e§lWeitere Person eintragen");
    NameTag.setItemMeta(mNameTag);
    return NameTag;
  }
}