package com.namelessmc.NamelessBungee;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.config.Configuration;

public enum NamelessMessages {

    // Global
    NO_PERMISSION("NO_PERMISSION"),
	MUST_BE_INGAME("MUST_BE_INGAME"),
	PLAYER_NOT_VALID("PLAYER_NOT_VALID"),
	MUST_REGISTER("MUST_REGISTER"),
	
	// Incorrect usage
	INCORRECT_USAGE_MAIN("INCORRECT_USAGE_MAIN"),
	INCORRECT_USAGE_REGISTER("INCORRECT_USAGE_REGISTER"),
	INCORRECT_USAGE_GETUSER("INCORRECT_USAGE_GETUSER"),
	INCORRECT_USAGE_GETNOTIFICATIONS("INCORRECT_USAGE_GETNOTIFICATIONS"),
	INCORRECT_USAGE_REPORT("INCORRECT_USAGE_REPORT"),
	INCORRECT_USAGE_SETGROUP("INCORRECT_USAGE_SETGROUP"),
	
	// Help/Alone Help Messages
	HELP_DESCRIPTION_MAIN("HELP_DESCRIPTION_MAIN"),
	HELP_DESCRIPTION_REGISTER("HELP_DESCRIPTION_REGISTER"),
	HELP_DESCRIPTION_GETUSER("HELP_DESCRIPTION_GETUSER"),
	HELP_DESCRIPTION_GETNOTIFICATIONS("HELP_DESCRIPTION_GETNOTIFICATIONS"),
	HELP_DESCRIPTION_REPORT("HELP_DESCRIPTION_REPORT"),
	HELP_DESCRIPTION_SETGROUP("HELP_DESCRIPTION_SETGROUP"),
	
	// Register
	REGISTER_SUCCESS_MESSAGE("REGISTER_SUCCESS_MESSAGE"),
	REGISTER_USERNAME_EXISTS("REGISTER_USERNAME_EXISTS"),
	REGISTER_UUID_EXISTS("REGISTER_USERNAME_EXISTS"),
	REGISTER_EMAIL_EXISTS("REGISTER_EMAIL_EXISTS"),

	
	// Notification
	NO_NOTIFICATIONS("NO_NOTIFICATIONS"),
	PM_NOTIFICATIONS_MESSAGE("PM_NOTIFICATIONS_MESSAGE"),
	ALERTS_NOTIFICATIONS_MESSAGE("ALERTS_NOTIFICATIONS_MESSAGE"),

	// Report 
	REPORT_SUCCESS("REPORT_SUCCESS"),
	
	// SetGroup
	SETGROUP_SUCCESS("SETGROUP_SUCCESS"),
	
	
	// Get User
	GETUSER_USERNAME("GETUSER_USERNAME"),
	GETUSER_DISPLAYNAME("GETUSER_DISPLAYNAME"),
	GETUSER_UUID("GETUSER_UUID"),
	GETUSER_GROUP_ID("GETUSER_GROUP_ID"),
	GETUSER_REGISTERED("GETUSER_REGISTERED"),
	GETUSER_REPUTATION("GETUSER_REPUTATION"),

	GETUSER_VALIDATED("GETUSER_VALIDATED"),
	GETUSER_VALIDATED_YES("GETUSER_VALIDATED_YES"),
	GETUSER_VALIDATED_NO("GETUSER_VALIDATED_NO"),

	GETUSER_BANNED("GETUSER_BANNED"),
	GETUSER_BANNED_YES("GETUSER_BANNED_YES"),
	GETUSER_BANNED_NO("GETUSER_BANNED_NO");
	
	String name;
	
	private NamelessMessages(String name){
		this.name = name;
	}

	public String getMessage() {
		//Configuration messageConfig = NamelessPlugin.getInstance().getAPI().getConfigManager().getMessageConfig();
		Configuration messageConfig = Config.MESSAGES.getConfig();
		return NamelessChat.convertColorsString(messageConfig.getString(name));
	}
	
	public BaseComponent[] getComponents() {
		return TextComponent.fromLegacyText(this.getMessage());
	}

}