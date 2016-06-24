package org.telegram.bot.structure;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 01 of April of 2016
 */
public class Chat {
    // Channel Flags
    private static final int FLAG_CREATOR = 0x00000001; // 0
    private static final int FLAG_KICKED = 0x00000002; // 1
    private static final int FLAG_LEFT = 0x00000004; // 2
    private static final int FLAG_EDITOR = 0x00000008; // 3
    private static final int FLAG_MODERATOR = 0x00000010; // 4
    private static final int FLAG_BROADCAST = 0x00000020; // 5
    private static final int FLAG_USERNAME = 0x00000040; // 6
    private static final int FLAG_VERIFIED = 0x00000080; // 7
    private static final int FLAG_MEGAGROUP = 0x00000100; // 8
    private static final int FLAG_RESTRICTED = 0x00000200; // 9
    private static final int FLAG_INVITES_ENABLED = 0x00000400; // 10
    private static final int FLAG_SIGNATURES = 0x00000800; // 11
    private static final int FLAG_MIN = 0x00001000; // 12
    private static final int FLAG_ACCESS_HASH = 0x00002000; // 13

    // Chat Flags
    private static final int FLAG_USER_CREATOR     = 0x00000001; // 0
    private static final int FLAG_USER_KICKED      = 0x00000002; // 1
    private static final int FLAG_USER_LEFT        = 0x00000004; // 2
    private static final int FLAG_ADMINS_ENABLED   = 0x00000008; // 3
    private static final int FLAG_USER_ADMIN       = 0x00000010; // 4
    private static final int FLAG_DEACTIVATED      = 0x00000020; // 5
    private static final int FLAG_MIGRATED_TO      = 0x00000040; // 6

    private int id;
    private boolean isChannel;
    private int flags;
    private Long accessHash;
    private String title;
    private String username;
    private String restrictionReason;
    private boolean forbidden;
    private Integer migratedTo;

    public int getId() {
        return id;
    }

    public Chat(int id) {
        this.id = id;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public Long getAccessHash() {
        return accessHash;
    }

    public void setAccessHash(Long accessHash) {
        this.accessHash = accessHash;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRestrictionReason() {
        return restrictionReason;
    }

    public boolean isChannel() {
        return isChannel;
    }

    public void setChannel(boolean channel) {
        isChannel = channel;
    }

    public void setRestrictionReason(String restrictionReason) {
        this.restrictionReason = restrictionReason;
    }

    public Integer getMigratedTo() {
        return migratedTo;
    }

    public void setMigratedTo(Integer migratedTo) {
        this.migratedTo = migratedTo;
    }

    public void setForbidden(boolean forbidden) {
        this.forbidden = forbidden;
    }

    public boolean isForbidden() {
        return forbidden;
    }

    public boolean hasUsername() {
        return (flags & FLAG_USERNAME) != 0;
    }

    public boolean hasRestrictionReason() {
        return (flags & FLAG_RESTRICTED) != 0;
    }

    public boolean isSupergroup() {
        return isChannel && ((flags & FLAG_MEGAGROUP) != 0);
    }

    public boolean isMigratedTo() {
        return !isChannel && ((flags & FLAG_MIGRATED_TO) != 0);
    }

    public boolean hasAccess() {
        return !(((flags & FLAG_USER_KICKED) != 0) || ((flags & FLAG_USER_LEFT) != 0));
    }

    @Override
    public String toString() {
        return title;
    }
}

