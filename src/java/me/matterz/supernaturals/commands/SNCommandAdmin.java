/*
 * Supernatural Players Plugin for Bukkit
 * Copyright (C) 2011  Matt Walker <mmw167@gmail.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package me.matterz.supernaturals.commands;

import me.matterz.supernaturals.SupernaturalsPlugin;
import me.matterz.supernaturals.io.SNConfigHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SNCommandAdmin extends SNCommand {
	private static final List<String> adminHelpMessages = new ArrayList<>();

	public SNCommandAdmin() {
		super();
		requiredParameters = new ArrayList<>();
		optionalParameters = new ArrayList<>();
		senderMustBePlayer = false;
		senderMustBeSupernatural = false;
		permissions = "supernatural.admin.command.adminhelp";
	}

	static {
		adminHelpMessages.add("*** " + ChatColor.WHITE
				+ "Supernatural Admin Help" + ChatColor.RED + " ***");
		adminHelpMessages.add("/sn cure <PlayerName> " + ChatColor.WHITE
				+ "- Cure self or player.");
		adminHelpMessages.add("/sn convert <PlayerName> [SupernaturalType] "
				+ ChatColor.WHITE
				+ "- Turn self or player into any supernatural.");
		adminHelpMessages.add("/sn reset <PlayerName>" + ChatColor.WHITE
				+ "- Reset self or player's power.");
		adminHelpMessages.add("/sn power <PlayerName> [Power] "
				+ ChatColor.WHITE + "- Give power to self or player.");
		adminHelpMessages.add("/sn rmtarget <PlayerName> " + ChatColor.WHITE
				+ "- Removes player from current WitchHunter kill list.");
		adminHelpMessages.add("/sn save " + ChatColor.WHITE
				+ "- Save data to disk.");
		adminHelpMessages.add("/sn reload " + ChatColor.WHITE
				+ "- Reload data from disk.");
		adminHelpMessages.add("/sn restartTask " + ChatColor.WHITE
				+ "- Restarts the Task Timer.");
		adminHelpMessages.add("/sn setchurch " + ChatColor.WHITE
				+ "- Sets your current location as the priests' church.");
		adminHelpMessages.add("/sn setbanish " + ChatColor.WHITE
				+ "- Sets your current location as the priests' banish spot.");
	}

	@Override
	public void perform() {
		if (!(sender instanceof Player player)) {
			this.sendMessage(adminHelpMessages);
			return;
		}
		if (SupernaturalsPlugin.hasPermissions(player, permissions)) {
			this.sendMessage(adminHelpMessages);
		} else {
			if (!SNConfigHandler.spanish) {
				this.sendMessage("You do not have permissions to use this command.");
			} else {
				this.sendMessage("No tienes permiso para usar este comando.");
			}
		}
	}
}
