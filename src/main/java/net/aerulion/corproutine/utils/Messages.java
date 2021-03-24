package net.aerulion.corproutine.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public enum Messages {
    ERROR_ALREADY_CONVERSING(Component.text("Fehler: Es ist bereits eine Chat-Eingabe aktiv.")
            .color(NamedTextColor.RED)),
    ERROR_DATABASE(Component.text("Fehler bei der Kommunikation mit der Datenbank.")
            .color(NamedTextColor.RED)),
    ERROR_NO_PERMISSION(Component.text("Fehler: Du hast nicht die erforderlichen Rechte.")
            .color(NamedTextColor.RED)),
    ERROR_NO_PLAYER(Component.text("Fehler: Dieser Befehl kann nur als Spieler ausgeführt werden.")
            .color(NamedTextColor.RED)),
    MESSAGE_EXPIRED_TASKS(Component.text("Es sind ")
            .color(NamedTextColor.GRAY)
            .append(Component.text("%count%")
                    .color(NamedTextColor.YELLOW)
                    .decorate(TextDecoration.BOLD))
            .append(Component.text(" Routineaufgaben überfällig.")
                    .color(NamedTextColor.GRAY)
                    .decoration(TextDecoration.BOLD, TextDecoration.State.FALSE))),
    MESSAGE_TASK_DONE(Component.text("%player%")
            .color(NamedTextColor.YELLOW)
            .decorate(TextDecoration.BOLD)
            .append(Component.text(" hat die Routineaufgabe ")
                    .color(NamedTextColor.GRAY)
                    .decoration(TextDecoration.BOLD, TextDecoration.State.FALSE))
            .append(Component.text("%task%")
                    .color(NamedTextColor.YELLOW)
                    .decorate(TextDecoration.BOLD))
            .append(Component.text(" abgeschlossen.")
                    .color(NamedTextColor.GRAY)
                    .decoration(TextDecoration.BOLD, TextDecoration.State.FALSE))),
    MESSAGE_TASK_DONE_SELF(Component.text("Du hast die Routineaufgabe ")
            .color(NamedTextColor.GREEN)
            .append(Component.text("%task%")
                    .color(NamedTextColor.YELLOW)
                    .decorate(TextDecoration.BOLD))
            .append(Component.text(" abgeschlossen.")
                    .color(NamedTextColor.GREEN)
                    .decoration(TextDecoration.BOLD, TextDecoration.State.FALSE))),
    PREFIX(Component.text("[")
            .color(NamedTextColor.GRAY)
            .append(Component.text("Routine")
                    .color(NamedTextColor.YELLOW)
                    .decorate(TextDecoration.BOLD))
            .append(Component.text("] ")
                    .color(NamedTextColor.GRAY)
                    .decoration(TextDecoration.BOLD, TextDecoration.State.FALSE)));

    private final Component message;

    Messages(Component message) {
        this.message = message;
    }

    public Component get() {
        return PREFIX.getRaw().append(message);
    }

    public Component getRaw() {
        return message;
    }
}