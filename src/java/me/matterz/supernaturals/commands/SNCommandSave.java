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
import org.bukkit.entity.Player;

public class SNCommandSave extends SNCommandReload {

	public SNCommandSave() {
		senderMustBePlayer = false;
		permissions = "supernatural.admin.command.save";
		helpNameAndParams = "";
		helpDescription = "Save data from disk.";
	}

	@Override
	public void perform() {
		if (!(sender instanceof Player)) {
			SupernaturalsPlugin.saveAll();
			this.sendMessage("All config/player data has been saved!");
		}
		Player senderPlayer = (Player) sender;
		if (!SNConfigHandler.spanish) {
			if (!SupernaturalsPlugin.hasPermissions(senderPlayer, permissions)) {
				this.sendMessage("You do not have permissions to use this command.");
				return;
			}
			SupernaturalsPlugin.saveAll();
			this.sendMessage("All config/player data has been saved!");
		} else {
			if (!SupernaturalsPlugin.hasPermissions(senderPlayer, permissions)) {
				this.sendMessage("No tienes permiso para este comando.");
				return;
			}
			SupernaturalsPlugin.saveAll();
			this.sendMessage("Los datos y configuraciones han sido guardados!");
		}
	}
}
