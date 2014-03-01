package com.massivecraft.lightfixer;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.world.ChunkPopulateEvent;
import org.bukkit.plugin.Plugin;

import com.massivecraft.lightfixer.entity.MConf;
import com.massivecraft.mcore.EngineAbstract;

public class EngineGenfix extends EngineAbstract
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static EngineGenfix i = new EngineGenfix();
	public static EngineGenfix get() { return i; }
	public EngineGenfix() {}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public Plugin getPlugin()
	{
		return LightFixer.get();
	}
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public Set<ChunkWrap> fixed = new HashSet<ChunkWrap>(); 
	
	// -------------------------------------------- //
	// LISTENER
	// -------------------------------------------- //
	// Each time a chunk is generated we try to fix light there and in all surrounding chunks.
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void genfix(ChunkPopulateEvent event)
	{
		// If genfix is enabled ...
		if (!MConf.get().genfix) return;
		
		// ... and a chunk is being populated ...
		Chunk chunk = event.getChunk();
		ChunkWrap populatedcw = new ChunkWrap(chunk);
		
		// ... then for each surrounding chunk and the chunk itself ...
		for (ChunkWrap cw : populatedcw.getSurrounding(true))
		{
			// ... if the chunk isn't already fixed ...
			if (this.fixed.contains(cw)) continue;
			
			// ... try recalculating the light level ...
			if (!cw.recalcLightLevel()) continue;
			
			// ... and remember the success on success.
			this.fixed.add(cw);
		}
	}

}
