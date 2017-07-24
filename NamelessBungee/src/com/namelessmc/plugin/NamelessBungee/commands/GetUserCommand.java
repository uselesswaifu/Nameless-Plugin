package com.namelessmc.plugin.NamelessBungee.commands;

import java.util.UUID;

import com.namelessmc.NamelessAPI.NamelessPlayer;
import com.namelessmc.plugin.NamelessBungee.Message;
import com.namelessmc.plugin.NamelessBungee.Nameless;
import com.namelessmc.plugin.NamelessBungee.util.UUIDFetcher;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class GetUserCommand extends Command {

	private String commandName;

	public GetUserCommand(String commandName) {
		super(commandName);
		this.commandName = commandName;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (!sender.hasPermission(Nameless.PERMISSION_ADMIN + ".getUser")) {
			sender.sendMessage(Message.NO_PERMISSION.getComponents());
			return;
		}
		
		if (args.length != 1) {
			sender.sendMessage(TextComponent.fromLegacyText(
					Message.INCORRECT_USAGE_GETUSER.getMessage().replace("%command%", commandName)));
			return;
		}
		
		ProxyServer.getInstance().getScheduler().runAsync(Nameless.getInstance(), new Runnable() {
			@Override
			public void run() {
				final String targetName = args[0];
				final UUID targetUuid;
				
				try {
					targetUuid = UUIDFetcher.getUUID(targetName);
				} catch (IllegalArgumentException e) {
					sender.sendMessage(new ComponentBuilder("This player could not be found").color(ChatColor.RED).create()); // TODO Use messages.yml
					return;
				}
				
				NamelessPlayer target = new NamelessPlayer(targetUuid, Nameless.baseApiURL);

				BaseComponent[] separator = new ComponentBuilder("--------------------------------").color(ChatColor.DARK_AQUA).italic(true).create();
				
				sender.sendMessage(separator);
				
				sender.sendMessage(TextComponent.fromLegacyText(
						Message.GETUSER_USERNAME.getMessage().replace("%username%", target.getUsername())));
				
				sender.sendMessage(TextComponent.fromLegacyText(
						Message.GETUSER_DISPLAYNAME.getMessage().replace("%displayname%", target.getDisplayName())));
				
				sender.sendMessage(TextComponent.fromLegacyText(
						Message.GETUSER_UUID.getMessage().replace("%uuid%", targetUuid.toString())));
				
				sender.sendMessage(TextComponent.fromLegacyText(
						Message.GETUSER_GROUP_ID.getMessage().replace("%groupid%", "" + target.getGroupID())));
		
				sender.sendMessage(TextComponent.fromLegacyText(
						Message.GETUSER_REGISTERED.getMessage().replace("%registereddate%", target.getRegisteredDate().toString())));
				
				sender.sendMessage(TextComponent.fromLegacyText(
						Message.GETUSER_REPUTATION.getMessage().replace("%reputation%", "" + target.getReputations())));

				/*if (namelessPlayer.isValidated()) { TODO Check if verified
						sender.sendMessage(NamelessChat.convertColors(
								NamelessChat.getMessage(NamelessMessages.GETUSER_VALIDATED) + "&a: "
												+ NamelessChat.getMessage(NamelessMessages.GETUSER_VALIDATED_YES)));
							} else {
								sender.sendMessage(NamelessChat.convertColors(
										NamelessChat.getMessage(NamelessMessages.GETUSER_VALIDATED) + "&c: "
												+ NamelessChat.getMessage(NamelessMessages.GETUSER_VALIDATED_NO)));
							}*/
				
				if (target.isBanned()) {
					sender.sendMessage(TextComponent.fromLegacyText(
							Message.GETUSER_BANNED.getMessage() +
							"&c: " +
							Message.GETUSER_BANNED_YES.getMessage()));
				} else {
					sender.sendMessage(TextComponent.fromLegacyText(
							Message.GETUSER_BANNED.getMessage() +
							"&a: " +
							Message.GETUSER_BANNED_NO.getMessage()));
				}
				
				sender.sendMessage(separator);
			}
		});

	}

}