package net.aerulion.corproutine.conversation;

import net.aerulion.corproutine.Main;
import net.aerulion.corproutine.utils.EditSession;
import net.aerulion.corproutine.utils.Inventories;
import net.aerulion.corproutine.utils.Util;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.ValidatingPrompt;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommentConversation extends ValidatingPrompt {

  @Override
  public @NotNull String getPromptText(final @NotNull ConversationContext con) {
    return "Tippe den Kommentar in den Chat. Schreibe 'stop' um den Vorgang abzubrechen.";
  }

  @Override
  protected boolean isInputValid(final @NotNull ConversationContext context,
      final @NotNull String input) {
    return true;
  }

  @Override
  protected Prompt acceptValidatedInput(final @NotNull ConversationContext con,
      final @NotNull String input) {
    final EditSession ES = Main.EDIT_SESSIONS.get(((Player) con.getForWhom()).getUniqueId());
    ES.setComment(input);
    ((Player) con.getForWhom()).openInventory(
        Inventories.EditMenu(((Player) con.getForWhom()).getUniqueId()));
    Util.setPlayerHeadTexturesAsync((Player) con.getForWhom());
    return null;
  }

  @Override
  public String getFailedValidationText(final @NotNull ConversationContext con,
      final @NotNull String InvalidInput) {
    return "Fehler.";
  }
}