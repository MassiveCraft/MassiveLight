package com.massivecraft.massivelight.nms;

import com.massivecraft.massivecore.mixin.Mixin;
import com.massivecraft.massivecore.nms.NmsBasics;
import org.bukkit.World;
import org.bukkit.block.Block;

public class NmsMassiveLight extends Mixin
{
	// -------------------------------------------- //
	// DEFAULT
	// -------------------------------------------- //
	
	private static NmsMassiveLight d = new NmsMassiveLight().setAlternatives(
		NmsMassiveLight111R1.class,
		NmsMassiveLight110R1.class
	);
	
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static NmsMassiveLight i = d;
	public static NmsMassiveLight get() { return i; }
	
	// -------------------------------------------- //
	// UPDATE LIGHT LEVEL AT
	// -------------------------------------------- //
	// Find the method by going through CraftBlock.setTypeId()
	// It should be located in NMS.World and looks something like this:
	// The method should look something like this:
	//
	// public boolean w(BlockPosition blockposition)
	// {
	//     boolean flag = false;
	//     if (!this.worldProvider.o()) {
	//         flag |= c(EnumSkyBlock.SKY, blockposition);
	//     }
	//     flag |= c(EnumSkyBlock.BLOCK, blockposition);
	//     return flag;
	// }
	
	public void updateLightLevelAt(Block block)
	{
		this.updateLightLevelAt(block.getWorld(), block.getX(), block.getY(), block.getZ());
	}
	
	public void updateLightLevelAt(World world, int x, int y, int z)
	{
		Object nmsWorldServer = NmsBasics.get().getHandle(world);
		this.updateLightLevelAtNms(nmsWorldServer, x, y, z);
	}
	
	public void updateLightLevelAtNms(Object nmsWorldServer, int x, int y, int z)
	{
		throw new UnsupportedOperationException();
	}
	
}
