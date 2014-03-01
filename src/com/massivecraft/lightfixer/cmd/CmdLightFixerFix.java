package com.massivecraft.lightfixer.cmd;

import org.bukkit.Chunk;
import org.bukkit.World;

import com.massivecraft.lightfixer.ChunkWrap;
import com.massivecraft.lightfixer.Perm;
import com.massivecraft.lightfixer.entity.MConf;
import com.massivecraft.mcore.cmd.MCommand;
import com.massivecraft.mcore.cmd.arg.ARInteger;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.cmd.req.ReqIsPlayer;

public class CmdLightFixerFix extends MCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdLightFixerFix()
	{
		// Aliases
		this.addAliases("fix");
		
		// Args
		this.addOptionalArg("radius", "0");
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.FIX.node));
		this.addRequirements(ReqIsPlayer.get());
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform()
	{
		// Args
		Integer radius = this.arg(0, ARInteger.get(), 0);
		if (radius == null) return;
		if (radius < 0)
		{
			msg("<b>Radius may not be a negative value.");
			return;
		}
		if (radius > MConf.get().maxradius)
		{
			msg("<b>The maxium radius allowed is <h>%d<b>.", MConf.get().maxradius);
			return;
		}
		
		Chunk origin = me.getLocation().getChunk();
		int originX = origin.getX();
		int originZ = origin.getZ();
		World world = me.getWorld();
		
		// Pre Inform
		int side = (1 + radius * 2);
		int target = side * side;
		msg("<i>Chunks around you will now be relighted.");
		msg("<k>Radius <v>%d <a>--> <k>Side <v>%d <a>--> <k>Chunks <v>%d", radius, side, target);
		
		// Apply
		for (int deltaX = -radius; deltaX <= radius; deltaX++)
		{
			for (int deltaZ = -radius; deltaZ <= radius; deltaZ++)
			{
				int x = originX + deltaX;
				int z = originZ + deltaZ;
				new ChunkWrap(world, x, z).recalcLightLevel();
			}	
		}
		
		// Post Inform
		msg("<g>Chunk relight complete.");
	}
	
}
