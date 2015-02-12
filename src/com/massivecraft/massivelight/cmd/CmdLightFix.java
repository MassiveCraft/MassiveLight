package com.massivecraft.massivelight.cmd;

import org.bukkit.Chunk;
import org.bukkit.World;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.MassiveCommand;
import com.massivecraft.massivecore.cmd.arg.ARInteger;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.cmd.req.ReqIsPlayer;
import com.massivecraft.massivelight.ChunkWrap;
import com.massivecraft.massivelight.Perm;
import com.massivecraft.massivelight.entity.MConf;

public class CmdLightFix extends MassiveCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdLightFix()
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
	public void perform() throws MassiveException
	{
		// Args
		Integer radius = this.arg(0, ARInteger.get(), 0);
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
