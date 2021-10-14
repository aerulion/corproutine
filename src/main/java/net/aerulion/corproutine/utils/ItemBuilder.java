package net.aerulion.corproutine.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemBuilder {

  public static ItemStack createRoutineItemStack(RoutineTask RA) {
    ItemStack RoutineItem = new ItemStack(RA.isExpired() ? Material.RED_DYE : Material.LIME_DYE);
    ItemMeta mRoutineItem = RoutineItem.getItemMeta();
    mRoutineItem.setDisplayName("§e§l" + RA.getName());
    List<String> Lore = new ArrayList<>();
    Lore.add("§8Aufgaben-ID: " + RA.getID());
    Lore.add("§7Kategorie: §a" + Util.formatCategory(RA.getCategory()));
    Lore.add("§7Intervall: " + Util.formatCycle(RA.getCycle()));
    Lore.add("§7Fällig am: §a" + RA.getExpiryDate());
    Lore.add("§7Zuletzt erledigt von: §a" + String.join("§7, §a", RA.getDoneBy()));
    Lore.add("§7Kommentar: §a");
    for (String s : Util.WrapString(RA.getComment(), 30)) {
      Lore.add("   §a" + s);
    }
    mRoutineItem.setLore(Lore);
    RoutineItem.setItemMeta(mRoutineItem);
    return RoutineItem;
  }

  public static ItemStack createSpacerGlassPane() {
    ItemStack GlassPane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
    ItemMeta mGlassPane = GlassPane.getItemMeta();
    mGlassPane.setDisplayName("§8§l-");
    GlassPane.setItemMeta(mGlassPane);
    return GlassPane;
  }

  public static ItemStack createDoneByBook() {
    ItemStack Book = new ItemStack(Material.BOOK);
    ItemMeta mBook = Book.getItemMeta();
    mBook.setDisplayName("§e§lZuletzt erledigt von:");
    Book.setItemMeta(mBook);
    return Book;
  }

  public static ItemStack createWhoHelpedBook() {
    ItemStack Book = new ItemStack(Material.BOOK);
    ItemMeta mBook = Book.getItemMeta();
    mBook.setDisplayName("§e§lBeteiligt waren:");
    Book.setItemMeta(mBook);
    return Book;
  }

  public static ItemStack createPlayerHead(String PlayerName, boolean Active) {
    ItemStack Skull;
    if (Active) {
      Skull = new ItemStack(Material.PLAYER_HEAD);
      SkullMeta mSkull = (SkullMeta) Skull.getItemMeta();
      mSkull.setDisplayName("§a§l" + PlayerName);
      Skull.setItemMeta(mSkull);
    } else {
      Skull = new ItemStack(Material.SKELETON_SKULL);
      ItemMeta mSkull = Skull.getItemMeta();
      mSkull.setDisplayName("§c§l" + PlayerName);
      Skull.setItemMeta(mSkull);
    }
    return Skull;
  }

  public static ItemStack createNoNameBarrier() {
    ItemStack Barrier = new ItemStack(Material.BARRIER);
    ItemMeta mBarrier = Barrier.getItemMeta();
    mBarrier.setDisplayName("§cKein Name angegeben");
    Barrier.setItemMeta(mBarrier);
    return Barrier;
  }

  public static ItemStack createExpireDateClock(String ExpireDate) {
    ItemStack Clock = new ItemStack(Material.CLOCK);
    ItemMeta mClock = Clock.getItemMeta();
    mClock.setDisplayName("§e§lFällig am:");
    mClock.setLore(Arrays.asList("§a" + ExpireDate));
    Clock.setItemMeta(mClock);
    return Clock;
  }

  public static ItemStack createNextDateClock(String NextDate) {
    ItemStack Clock = new ItemStack(Material.CLOCK);
    ItemMeta mClock = Clock.getItemMeta();
    mClock.setDisplayName("§e§lWieder fällig am:");
    mClock.setLore(Arrays.asList("§a" + NextDate));
    Clock.setItemMeta(mClock);
    return Clock;
  }

  public static ItemStack createButtonDye(boolean IsExpired) {
    ItemStack Dye = new ItemStack(IsExpired ? Material.LIME_DYE : Material.GRAY_DYE);
    ItemMeta mDye = Dye.getItemMeta();
    mDye.setDisplayName(
        IsExpired ? "§a§lRoutineaufgabe abschließen" : "§7§lRoutineaufgabe noch nicht notwendig");
    mDye.setLore(
        IsExpired ? null : Arrays.asList("§7§oMittelklick um die Aufgabe dennoch abzuschließen"));
    Dye.setItemMeta(mDye);
    return Dye;
  }

  public static ItemStack createCommentPaper(String Comment) {
    ItemStack Paper = new ItemStack(Material.PAPER);
    ItemMeta mPaper = Paper.getItemMeta();
    mPaper.setDisplayName("§e§lKommentar:");
    List<String> Lore = new ArrayList<>();
    for (String s : Util.WrapString(Comment, 30)) {
      Lore.add("   §a" + s);
    }
    mPaper.setLore(Lore);
    Paper.setItemMeta(mPaper);
    return Paper;
  }

  public static ItemStack createMainMenuDoor() {
    ItemStack Door = new ItemStack(Material.DARK_OAK_DOOR, 1);
    ItemMeta mDoor = Door.getItemMeta();
    mDoor.setDisplayName("§e§lZurück zum Hauptmenü");
    Door.setItemMeta(mDoor);
    return Door;
  }

  public static ItemStack createDoneButtonKnowledgeBook() {
    ItemStack KnowledgeBook = new ItemStack(Material.KNOWLEDGE_BOOK);
    ItemMeta mKnowledgeBook = KnowledgeBook.getItemMeta();
    mKnowledgeBook.setDisplayName("§a§lAufgabe abschließen");
    KnowledgeBook.setItemMeta(mKnowledgeBook);
    return KnowledgeBook;
  }

  public static ItemStack createCancelButtonBarrier() {
    ItemStack Barrier = new ItemStack(Material.BARRIER);
    ItemMeta mBarrier = Barrier.getItemMeta();
    mBarrier.setDisplayName("§c§lAbbrechen");
    Barrier.setItemMeta(mBarrier);
    return Barrier;
  }

  public static ItemStack createAdditionalHelperNameTag() {
    ItemStack NameTag = new ItemStack(Material.NAME_TAG);
    ItemMeta mNameTag = NameTag.getItemMeta();
    mNameTag.setDisplayName("§e§lWeitere Person eintragen");
    NameTag.setItemMeta(mNameTag);
    return NameTag;
  }
}