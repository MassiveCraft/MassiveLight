package com.massivecraft.massivelight;

import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivelight.nms.NmsMassiveLight;
import org.bukkit.Chunk;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChunkWrap
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	private World world;
	public World getWorld() { return this.world; }
	
	private int x;
	public int getX() { return this.x; }
	
	private int z;
	public int getZ() { return this.z; }
	
	// -------------------------------------------- //
	// CONSTRUCTORS
	// -------------------------------------------- //
	
	public ChunkWrap(Chunk chunk)
	{
		this(chunk.getWorld(), chunk.getX(), chunk.getZ());
	}
	
	public ChunkWrap(World world, int x, int z)
	{
		this.world = world;
		this.x = x;
		this.z = z;
	}
	
	// -------------------------------------------- //
	// SURROUNDING
	// -------------------------------------------- //
	
	public List<ChunkWrap> getSurrounding(boolean includeSelf)
	{
		List<ChunkWrap> ret = new ArrayList<>();
		World world = this.getWorld();
		int xfrom = this.getX() - 1;
		int xto = this.getX() + 1;
		int zfrom = this.getZ() - 1;
		int zto = this.getZ() + 1;
		for (int x = xfrom; x <= xto; x++)
		{
			for (int z = zfrom; z <= zto; z++)
			{
				ret.add(new ChunkWrap(world, x, z));
			}
		}
		
		if (!includeSelf) ret.remove(this);
		
		return ret;
	}
	
	/**
	 * Try to load the chunks around a certain chunk.
	 * No attempts to generate chunks will be made. If one of the chunks doesnt exist
	 * the operation will be canceled and false will be returned.
	 */
	public boolean loadSurrounding(boolean includeSelf)
	{
		for (ChunkWrap othercw : this.getSurrounding(includeSelf))
		{
			if (!othercw.getWorld().loadChunk(othercw.getX(), othercw.getZ(), false)) return false;
		}
		return true;
	}
	
	//----------------------------------------------//
	// LIGHT LEVEL
	//----------------------------------------------//
	
	public boolean recalcLightLevel()
	{
		if (!this.loadSurrounding(true)) return false;
		
		World world = this.getWorld();
		int xfrom = this.getX() * 16;
		int xto = xfrom + 16;
		int yfrom = 0;
		int yto = this.getWorld().getMaxHeight() - 1;
		int zfrom = this.getZ() * 16;
		int zto = zfrom + 16;
		
		for (int x = xfrom; x <= xto; x++)
		{
			for (int y = yfrom; y <= yto; y++)
			{
				for (int z = zfrom; z <= zto; z++)
				{
					NmsMassiveLight.get().updateLightLevelAt(world, x, y, z);
				}
			}
		}
		
		// Send update data to nearby players.
		world.refreshChunk(this.getX(), this.getZ());
		
		return true;
	}
	
	// -------------------------------------------- //
	// COMPARISON
	// -------------------------------------------- //
	
	@Override
	public int hashCode()
	{
		return Objects.hash(
			this.getWorld(),
			this.getX(),
			this.getZ()
		);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj == this) return true;
		if (!(obj instanceof ChunkWrap)) return false;

		ChunkWrap that = (ChunkWrap) obj;
		
		return MUtil.equals(
			this.getX(), that.getX(),
			this.getZ(), that.getZ(),
			this.getWorld(), that.getWorld()
		);
	}
	
}
