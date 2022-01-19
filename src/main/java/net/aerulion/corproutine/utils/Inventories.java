package net.aerulion.corproutine.utils;

import java.util.UUID;
import net.aerulion.corproutine.Main;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class Inventories {

  public static final String MainMenuName = "§e§lRoutineaufgaben";
  public static final String RoutineMenuNamePrefix = "§e§lRoutineaufgabe #";
  public static final String EditMenuNamePrefix = "§e§lBearbeiten #";

  public static @NotNull Inventory MainMenu() {
    final @NotNull Inventory MainMenu = Bukkit.createInventory(null, 54, MainMenuName);
    for (final @NotNull RoutineTask RA : Main.ROUTINE_TASKS.values()) {
      MainMenu.addItem(ItemBuilder.createRoutineItemStack(RA));
    }
    return MainMenu;
  }

  public static @NotNull Inventory RoutineMenu(final int ID) {
    final RoutineTask RA = Main.ROUTINE_TASKS.get(ID);
    final @NotNull Inventory RoutineMenu = Bukkit.createInventory(null, 54,
        RoutineMenuNamePrefix + ID);

    final @NotNull ItemStack Spacer = ItemBuilder.createSpacerGlassPane();
    RoutineMenu.setItem(27, Spacer);
    RoutineMenu.setItem(28, Spacer);
    RoutineMenu.setItem(29, Spacer);
    RoutineMenu.setItem(30, Spacer);
    RoutineMenu.setItem(32, Spacer);
    RoutineMenu.setItem(33, Spacer);
    RoutineMenu.setItem(34, Spacer);
    RoutineMenu.setItem(35, Spacer);

    RoutineMenu.setItem(31, ItemBuilder.createDoneByBook());

    int FaceSlot = 36;
    for (final @NotNull String PlayerName : RA.getDoneBy()) {
      RoutineMenu.setItem(FaceSlot, PlayerName.isEmpty() ? ItemBuilder.createNoNameBarrier()
          : ItemBuilder.createPlayerHead(PlayerName, true));
      FaceSlot++;
    }

    RoutineMenu.setItem(11, ItemBuilder.createExpireDateClock(RA.getExpiryDate()));
    RoutineMenu.setItem(13, ItemBuilder.createButtonDye(RA.isExpired()));
    RoutineMenu.setItem(15, ItemBuilder.createCommentPaper(RA.getComment()));
    RoutineMenu.setItem(53, ItemBuilder.createMainMenuDoor());

    return RoutineMenu;
  }

  public static @NotNull Inventory EditMenu(final UUID EditSessionOwner) {
    final EditSession ES = Main.EDIT_SESSIONS.get(EditSessionOwner);
    final RoutineTask RA = Main.ROUTINE_TASKS.get(ES.getRoutineID());
    final @NotNull Inventory EditMenu = Bukkit.createInventory(null, 54,
        EditMenuNamePrefix + RA.getID());

    EditMenu.setItem(11, ItemBuilder.createNextDateClock(Util.convertDate(ES.getNextDate())));
    EditMenu.setItem(15, ItemBuilder.createCommentPaper(
        ES.getComment().equals("-") ? "Klicke um ein Kommentar hinzuzufügen" : ES.getComment()));

    final @NotNull ItemStack Spacer = ItemBuilder.createSpacerGlassPane();
    EditMenu.setItem(27, Spacer);
    EditMenu.setItem(28, Spacer);
    EditMenu.setItem(29, Spacer);
    EditMenu.setItem(30, Spacer);
    EditMenu.setItem(32, Spacer);
    EditMenu.setItem(33, Spacer);
    EditMenu.setItem(34, Spacer);
    EditMenu.setItem(35, Spacer);
    EditMenu.setItem(31, ItemBuilder.createWhoHelpedBook());

    int FaceSlot = 36;
    for (final String StafflerName : Main.staffler) {
      EditMenu.setItem(FaceSlot,
          ItemBuilder.createPlayerHead(StafflerName, ES.getDoneBy().contains(StafflerName)));
      FaceSlot++;
    }
    for (final String Name : ES.getDoneBy()) {
      if (!Main.staffler.contains(Name)) {
        if (FaceSlot > 50) {
          break;
        }
        EditMenu.setItem(FaceSlot, ItemBuilder.createPlayerHead(Name, true));
        FaceSlot++;
      }
    }
    EditMenu.setItem(FaceSlot, ItemBuilder.createAdditionalHelperNameTag());

    EditMenu.setItem(53, ItemBuilder.createCancelButtonBarrier());
    EditMenu.setItem(52, ItemBuilder.createDoneButtonKnowledgeBook());

    return EditMenu;
  }
}