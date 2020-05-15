package net.aerulion.corproutine.conversation;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.ValidatingPrompt;
import org.bukkit.entity.Player;

import net.aerulion.corproutine.Main;
import net.aerulion.corproutine.utils.EditSession;
import net.aerulion.corproutine.utils.Inventories;
import net.aerulion.corproutine.utils.Util;

public class CommentConversation extends ValidatingPrompt {

	@Override
	public String getPromptText(ConversationContext con) {
		return "Tippe den Kommentar in den Chat. Schreibe 'stop' um den Vorgang abzubrechen.";
	}

	@Override
	public String getFailedValidationText(ConversationContext con, String InvalidInput) {
		return "Fehler.";
	}

	@Override
	protected Prompt acceptValidatedInput(ConversationContext con, String input) {
		EditSession ES = Main.EditSessions.get(((Player) con.getForWhom()).getUniqueId());
		ES.setComment(input);
		((Player) con.getForWhom()).openInventory(Inventories.EditMenu(((Player) con.getForWhom()).getUniqueId()));
		Util.setPlayerHeadTexturesAsync((Player) con.getForWhom());
		return null;
	}

	@Override
	protected boolean isInputValid(ConversationContext context, String input) {
		return true;
	}
}