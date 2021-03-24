package net.aerulion.corproutine.conversation;

import lombok.experimental.UtilityClass;
import net.aerulion.corproutine.Main;
import net.aerulion.corproutine.utils.Messages;
import net.aerulion.nucleus.api.sound.SoundType;
import net.aerulion.nucleus.api.sound.SoundUtils;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.conversations.ConversationPrefix;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public final class ConversationUtil {
    public static void startConversation(@NotNull Player player, @NotNull Prompt prompt) {
        player.closeInventory();
        if (player.isConversing()) {
            player.sendMessage(Messages.ERROR_ALREADY_CONVERSING.get());
            SoundUtils.playSound(player, SoundType.ERROR);
            return;
        }
        ConversationFactory conversationFactory = new ConversationFactory(Main.plugin);
        ConversationPrefix conversationPrefix = prefix -> LegacyComponentSerializer.legacySection().serialize(Messages.PREFIX.getRaw());
        Conversation c = conversationFactory.withFirstPrompt(prompt).withEscapeSequence("stop").withModality(false).withLocalEcho(false).withPrefix(conversationPrefix).buildConversation(player);
        c.begin();
    }
}