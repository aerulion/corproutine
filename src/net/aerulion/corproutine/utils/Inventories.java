package net.aerulion.corproutine.utils;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.aerulion.corproutine.Main;

public class Inventories {

	public static String MainMenuName = "§e§lRoutineaufgaben";
	public static String RoutineMenuNamePrefix = "§e§lRoutineaufgabe #";
	public static String EditMenuNamePrefix = "§e§lBearbeiten #";

	public static Inventory MainMenu() {
		Inventory MainMenu = Bukkit.createInventory(null, 54, MainMenuName);
		for (Routineaufgabe RA : Main.Routineaufgaben.values()) {
			MainMenu.addItem(ItemBuilder.createRoutineItemStack(RA));
		}
		return MainMenu;
	}

	public static Inventory RoutineMenu(int ID) {
		Routineaufgabe RA = Main.Routineaufgaben.get(ID);
		Inventory RoutineMenu = Bukkit.createInventory(null, 54, RoutineMenuNamePrefix + ID);

		ItemStack Spacer = ItemBuilder.createSpacerGlassPane();
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
		for (String PlayerName : RA.getDoneBy()) {
			RoutineMenu.setItem(FaceSlot, PlayerName == "" ? ItemBuilder.createNoNameBarrier() : ItemBuilder.createPlayerHead(PlayerName, true));
			FaceSlot++;
		}

		RoutineMenu.setItem(11, ItemBuilder.createExpireDateClock(RA.getExpireDate()));
		RoutineMenu.setItem(13, ItemBuilder.createButtonDye(RA.getIsExpired()));
		RoutineMenu.setItem(15, ItemBuilder.createCommentPaper(RA.getComment()));
		RoutineMenu.setItem(53, ItemBuilder.createMainMenuDoor());

		return RoutineMenu;
	}

	public static Inventory EditMenu(UUID EditSessionOwner) {
		EditSession ES = Main.EditSessions.get(EditSessionOwner);
		Routineaufgabe RA = Main.Routineaufgaben.get(ES.getRoutineID());
		Inventory EditMenu = Bukkit.createInventory(null, 54, EditMenuNamePrefix + RA.getID());

		EditMenu.setItem(11, ItemBuilder.createNextDateClock(Util.convertDate(ES.getNextDate())));
		EditMenu.setItem(15, ItemBuilder.createCommentPaper(ES.getComment().equals("-") ? "Klicke um ein Kommentar hinzuzufügen" : ES.getComment()));

		ItemStack Spacer = ItemBuilder.createSpacerGlassPane();
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
		for (String StafflerName : Main.Staffler) {
			EditMenu.setItem(FaceSlot, ItemBuilder.createPlayerHead(StafflerName, ES.getDoneBy().contains(StafflerName) ? true : false));
			FaceSlot++;
		}
		for (String Name : ES.getDoneBy()) {
			if (!Main.Staffler.contains(Name)) {
				if (FaceSlot > 50)
					break;
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