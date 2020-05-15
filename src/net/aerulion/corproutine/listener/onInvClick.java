package net.aerulion.corproutine.listener;

import java.sql.SQLException;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.conversations.ConversationPrefix;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

import net.aerulion.corproutine.Main;
import net.aerulion.corproutine.conversation.AdditionalHelperConversation;
import net.aerulion.corproutine.conversation.CommentConversation;
import net.aerulion.corproutine.utils.EditSession;
import net.aerulion.corproutine.utils.Inventories;
import net.aerulion.corproutine.utils.Routineaufgabe;
import net.aerulion.corproutine.utils.Util;

public class onInvClick implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (e.getView().getTitle().equals(Inventories.MainMenuName)) {
			e.setCancelled(true);
			if ((e.getView().getTopInventory().getSize() == 54) && (e.getRawSlot() >= 0 && e.getRawSlot() <= 53)) {
				if (Util.DyeItems.contains(e.getCurrentItem().getType())) {
					((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.UI_BUTTON_CLICK, 1F, 1F);
					e.getWhoClicked().openInventory(Inventories.RoutineMenu(Integer.parseInt(e.getCurrentItem().getItemMeta().getLore().get(0).substring(15))));
					Util.setPlayerHeadTexturesAsync((Player) e.getWhoClicked());
					return;
				}
			}
		}

		if (e.getView().getTitle().contains(Inventories.RoutineMenuNamePrefix)) {
			e.setCancelled(true);
			if ((e.getView().getTopInventory().getSize() == 54) && (e.getRawSlot() >= 0 && e.getRawSlot() <= 53)) {
				if (e.getRawSlot() == 13) {
					if (e.getCurrentItem().getType().equals(Material.GRAY_DYE)) {
						if (!e.getClick().equals(ClickType.MIDDLE))
							return;
					}
					Main.EditSessions.put(e.getWhoClicked().getUniqueId(), new EditSession(e.getWhoClicked().getUniqueId(), Integer.parseInt(e.getView().getTitle().substring(Inventories.RoutineMenuNamePrefix.length()))));
					e.getWhoClicked().openInventory(Inventories.EditMenu(e.getWhoClicked().getUniqueId()));
					Util.setPlayerHeadTexturesAsync((Player) e.getWhoClicked());
					return;
				}
				if (e.getRawSlot() == 53) {
					((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.UI_BUTTON_CLICK, 1F, 1F);
					e.getWhoClicked().openInventory(Inventories.MainMenu());
					return;
				}
			}
		}

		if (e.getView().getTitle().contains(Inventories.EditMenuNamePrefix)) {
			e.setCancelled(true);
			if ((e.getView().getTopInventory().getSize() == 54) && (e.getRawSlot() >= 0 && e.getRawSlot() <= 53)) {
				if (e.getCurrentItem().getType().equals(Material.PLAYER_HEAD) || e.getCurrentItem().getType().equals(Material.SKELETON_SKULL)) {
					EditSession ES = Main.EditSessions.get(e.getWhoClicked().getUniqueId());
					ES.toggleStaffler(e.getCurrentItem().getItemMeta().getDisplayName().substring(4));
					((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.UI_BUTTON_CLICK, 1F, 1F);
					if (e.getCurrentItem().getType().equals(Material.PLAYER_HEAD))
						((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.ENTITY_VILLAGER_NO, 1F, 1F);
					else
						((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.ENTITY_VILLAGER_YES, 1F, 1F);
					e.getWhoClicked().openInventory(Inventories.EditMenu(e.getWhoClicked().getUniqueId()));
					Util.setPlayerHeadTexturesAsync((Player) e.getWhoClicked());
					return;
				}
				if (e.getCurrentItem().getType().equals(Material.BARRIER)) {
					Main.EditSessions.remove(e.getWhoClicked().getUniqueId());
					((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.ENTITY_ITEM_BREAK, 1F, 1F);
					e.getWhoClicked().closeInventory();
					return;
				}
				if (e.getCurrentItem().getType().equals(Material.KNOWLEDGE_BOOK)) {
					EditSession ES = Main.EditSessions.get(e.getWhoClicked().getUniqueId());
					Routineaufgabe RA = Main.Routineaufgaben.get(ES.getRoutineID());
					e.getWhoClicked().closeInventory();
					try {
						Util.updateData(ES);
					} catch (SQLException e1) {
						e.getWhoClicked().sendMessage(Util.PluginChatPrefix + "§cDatenbankfehler.");
						e1.printStackTrace();
						return;
					}
					Main.EditSessions.remove(e.getWhoClicked().getUniqueId());
					Util.playDoneSound((Player) e.getWhoClicked());

					Util.doneMessage((Player) e.getWhoClicked(), RA.getName());
					return;
				}

				if (e.getCurrentItem().getType().equals(Material.NAME_TAG)) {
					e.getWhoClicked().closeInventory();
					ConversationFactory cf = new ConversationFactory(Main.plugin);
					ConversationPrefix cp = prefix -> Util.PluginChatPrefix;
					Conversation c = cf.withFirstPrompt(new AdditionalHelperConversation()).withEscapeSequence("stop").withModality(false).withLocalEcho(false).withPrefix(cp).buildConversation((Player) e.getWhoClicked());
					c.begin();
					return;
				}

				if (e.getCurrentItem().getType().equals(Material.PAPER)) {
					e.getWhoClicked().closeInventory();
					ConversationFactory cf = new ConversationFactory(Main.plugin);
					ConversationPrefix cp = prefix -> Util.PluginChatPrefix;
					Conversation c = cf.withFirstPrompt(new CommentConversation()).withEscapeSequence("stop").withModality(false).withLocalEcho(false).withPrefix(cp).buildConversation((Player) e.getWhoClicked());
					c.begin();
					return;
				}
			}
		}
	}
}