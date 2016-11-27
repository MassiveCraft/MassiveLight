package com.massivecraft.massivelight.nms;

import net.minecraft.server.v1_10_R1.BlockPosition;
import net.minecraft.server.v1_10_R1.WorldServer;

public class NmsMassiveLight110R1 extends NmsMassiveLight
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static NmsMassiveLight110R1 i = new NmsMassiveLight110R1();
	public static NmsMassiveLight110R1 get() { return i; }
	
	// -------------------------------------------- //
	// PROVOKE
	// -------------------------------------------- //
	
	@Override
	public Class<BlockPosition> provoke()
	{
		return BlockPosition.class;
	}
	
	// -------------------------------------------- //
	// UPDATE LIGHT LEVEL AT
	// -------------------------------------------- //
	
	public void updateLightLevelAtNms(Object nmsWorldServer, int x, int y, int z)
	{
		WorldServer worldServer = (WorldServer)nmsWorldServer;
		BlockPosition blockPosition = new BlockPosition(x, y, z);
		worldServer.w(blockPosition);
	}
	
}
