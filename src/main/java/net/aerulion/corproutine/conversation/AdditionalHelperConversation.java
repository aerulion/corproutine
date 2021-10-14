package net.aerulion.corproutine.conversation;

import net.aerulion.corproutine.Main;
import net.aerulion.corproutine.utils.EditSession;
import net.aerulion.corproutine.utils.Inventories;
import net.aerulion.corproutine.utils.Util;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.ValidatingPrompt;
import org.bukkit.entity.Player;

public class AdditionalHelperConversation extends ValidatingPrompt {

  @Override
  public String getPromptText(ConversationContext con) {
    return "Tippe die Namen der zusätzlichen Helfer in den Chat. Trenne einzelne Namen durch ein Leerzeichen. Schreibe 'stop' um den Vorgang abzubrechen.";
  }

  @Override
  protected boolean isInputValid(ConversationContext context, String input) {
    return true;
  }

  @Override
  protected Prompt acceptValidatedInput(ConversationContext con, String input) {
    EditSession ES = Main.EDIT_SESSIONS.get(((Player) con.getForWhom()).getUniqueId());
    for (String Staffler : input.split(" ")) {
      ES.toggleStaffler(Staffler);
    }
    ((Player) con.getForWhom()).openInventory(
        Inventories.EditMenu(((Player) con.getForWhom()).getUniqueId()));
    Util.setPlayerHeadTexturesAsync((Player) con.getForWhom());
    return null;
  }

  @Override
  public String getFailedValidationText(ConversationContext con, String InvalidInput) {
    return "Fehler.";
  }
}