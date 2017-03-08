package com.massivecraft.massivelight.cmd;

import java.util.List;

import org.bukkit.Chunk;
import org.bukkit.World;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;
import com.massivecraft.massivecore.command.type.primitive.TypeInteger;
import com.massivecraft.massivelight.ChunkWrap;
import com.massivecraft.massivelight.Perm;
import com.massivecraft.massivelight.entity.MConf;
import com.massivecraft.massivelight.Lang;

public class CmdLightFix extends MassiveCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdLightFix()
	{
		// Parameters
		this.addParameter(TypeInteger.get(), "radius", "0");
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.FIX));
		this.addRequirements(RequirementIsPlayer.get());
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesLightFix;
	}
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		int radius = this.readArg(0);
		if (radius < 0)
		{
			msg(Lang.RADIUS_NEGATIVE_ERROR);
			return;
		}
		if (radius > MConf.get().maxradius)
		{
			msg(Lang.MAX_RADIUS_ALLOWED_IS_X, MConf.get().maxradius);
			return;
		}
		
		Chunk origin = this.me.getLocation().getChunk();
		int originX = origin.getX();
		int originZ = origin.getZ();
		World world = this.me.getWorld();
		
		// Pre Inform
		int side = (1 + radius * 2);
		int target = side * side;
		msg(Lang.CHUNKS_RELIGHT_PREINFORM_GENERAL);
		msg(Lang.CHUNK_RELIGHT_PREINFORM_DETAILS, radius, side, target);
		
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
		msg(Lang.CHUNK_RELIGHT_DONE);
	}
	
}
