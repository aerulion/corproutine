package net.aerulion.corproutine.utils;

import org.jetbrains.annotations.NotNull;

public enum Permissions {
  ALERT("alert"),
  CMD_ROUTINE("cmd.routine");

  private final String permission;

  Permissions(final String permission) {
    this.permission = permission;
  }

  public @NotNull String get() {
    return "corproutine." + permission;
  }
}