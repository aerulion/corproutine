package net.aerulion.corproutine.utils;

public enum Permissions {
  ALERT("alert"),
  CMD_ROUTINE("cmd.routine");

  private final String permission;

  Permissions(String permission) {
    this.permission = permission;
  }

  public String get() {
    return "corproutine." + permission;
  }
}