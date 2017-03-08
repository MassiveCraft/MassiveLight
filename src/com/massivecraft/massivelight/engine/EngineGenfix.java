package com.massivecraft.massivelight.engine;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.world.ChunkPopulateEvent;

import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivelight.ChunkWrap;
import com.massivecraft.massivelight.entity.MConf;

public class EngineGenfix extends Engine
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static EngineGenfix i = new EngineGenfix();
	public static EngineGenfix get() { return i; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	private Set<ChunkWrap> fixed = new HashSet<ChunkWrap>();
	public Set<ChunkWrap> getFixed() { return this.fixed; }
	
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
			if (this.getFixed().contains(cw)) continue;
			
			// ... try recalculating the light level ...
			if (!cw.recalcLightLevel()) continue;
			
			// ... and remember the success on success.
			this.getFixed().add(cw);
		}
	}

}
