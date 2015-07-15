package com.massivecraft.massivelight;

import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.WorldServer;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;

/**
 * Find the method by going through CraftBlock.setTypeId()
 * It should be located in NMS.World and looks something like this:
 * The method should look something like this:
 *
  public boolean x(BlockPosition blockposition)
  {
    boolean flag = false;
    if (!this.worldProvider.o()) {
      flag |= c(EnumSkyBlock.SKY, blockposition);
    }
    flag |= c(EnumSkyBlock.BLOCK, blockposition);
    return flag;
  }
 * 
 */
public class LightUtil
{
	// -------------------------------------------- //
	// RECALC LIGHT LEVEL AT
	// -------------------------------------------- //
	// This method will update the light level for the block.
	// It will however only work properly if all chunks that are around the chunk the block is in are loaded.
	
	public static void recalcLightLevelAt(Block block)
	{
		recalcLightLevelAt(block.getWorld(), block.getX(), block.getY(), block.getZ());
	}
	
	public static void recalcLightLevelAt(World world, int x, int y, int z)
	{
		CraftWorld cworld = (CraftWorld)world;
		WorldServer worldServer = cworld.getHandle();
		BlockPosition blockPosition = new BlockPosition(x, y, z);
		worldServer.x(blockPosition);
	}
	
}
