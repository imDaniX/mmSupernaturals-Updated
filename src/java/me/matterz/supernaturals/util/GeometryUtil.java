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

package me.matterz.supernaturals.util;

import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;

public class GeometryUtil {

	public static ArrayList<Block> getBallBlocks(Block centerBlock, double radius) {
		return getRadiusBlocks(centerBlock, radius, true);
	}

	public static ArrayList<Block> getRadiusBlocks(Block centerBlock, double radius, boolean ball) {
		return getRadiusBlocks(centerBlock, radius, ball, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0);
	}

	// All those radius factors make it possible to select half balls etc.
	public static ArrayList<Block> getRadiusBlocks(Block centerBlock, double radius, boolean ball, double xFromRadiusFactor, double xToRadiusFactor, double yFromRadiusFactor, double yToRadiusFactor, double zFromRadiusFactor, double zToRadiusFactor) {
		ArrayList<Block> blocks = new ArrayList<>();
		int xFrom = (int) (-xFromRadiusFactor * radius);
		int xTo = (int) (xToRadiusFactor * radius);
		int yFrom = (int) (-yFromRadiusFactor * radius);
		int yTo = (int) (yToRadiusFactor * radius);
		int zFrom = (int) (-zFromRadiusFactor * radius);
		int zTo = (int) (zToRadiusFactor * radius);

		for (int y = yFrom; y <= yTo; y++) {
			for (int z = zFrom; z <= zTo; z++) {
				for (int x = xFrom; x <= xTo; x++) {
					if (!ball || x * x + y * y + z * z <= radius * radius) {
						blocks.add(centerBlock.getRelative(x, -y, z));
					}
				}
			}
		}
		return blocks;
	}

	public static int countNearby(Block centerBlock, Material material, double radius) {
		ArrayList<Block> ballBlocks = GeometryUtil.getBallBlocks(centerBlock, radius);
		int count = 0;
		for (Block block : ballBlocks) {
			if (block.getType() == material) {
				count += 1;
			}
		}
		return count;
	}
}
