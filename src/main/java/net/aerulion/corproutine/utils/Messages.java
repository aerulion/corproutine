package net.aerulion.corproutine.utils;

public enum Messages {
    ERROR_DATABASE("§cFehler bei der Kommunikation mit der Datenbank."),
    ERROR_NO_PERMISSION("§cFehler: Du hast nicht die erforderlichen Rechte."),
    ERROR_NO_PLAYER("§cFehler: Dieser Befehl kann nur als Spieler ausgeführt werden."),

    MESSAGE_EXPIRED_TASKS("§7Es sind §e§l{COUNT}§7 Routineaufgaben überfällig."),
    MESSAGE_TASK_DONE("§e{PLAYER}§7 hat die Routineaufgabe §e{TASK}§7 abgeschlossen."),
    MESSAGE_TASK_DONE_SELF("§aDu hast die Routineaufgabe §e{TASK}§a abgeschlossen."),

    PREFIX("§7[§e§lRoutine§7]§7 ");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String get() {
        return PREFIX.getRaw() + message;
    }

    public String getRaw() {
        return message;
    }
}