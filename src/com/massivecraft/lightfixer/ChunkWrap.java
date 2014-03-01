package com.massivecraft.lightfixer;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Chunk;
import org.bukkit.World;

import com.massivecraft.mcore.util.LightUtil;

public class ChunkWrap
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public World world;
	public int x;
	public int z;
	
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
		List<ChunkWrap> ret = new ArrayList<ChunkWrap>();
		World world = this.world;
		int xfrom = this.x - 1;
		int xto = this.x + 1;
		int zfrom = this.z - 1;
		int zto = this.z + 1;
		for (int x = xfrom; x <= xto; x++)
		{
			for (int z = zfrom; z <= zto; z++)
			{
				ret.add(new ChunkWrap(world, x, z));
			}
		}
		
		if ( ! includeSelf)
		{
			ret.remove(this);
		}
		
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
			if ( ! othercw.world.loadChunk(othercw.x, othercw.z, false))
			{
				return false;
			}
		}
		return true;
	}
	
	//----------------------------------------------//
	// LIGHT LEVEL
	//----------------------------------------------//
	
	public boolean recalcLightLevel()
	{
		if ( ! this.loadSurrounding(true))
		{
			return false;
		}
		
		World world = this.world;
		int xfrom = this.x * 16;
		int xto = xfrom + 16;
		int yfrom = 0;
		int yto = this.world.getMaxHeight() - 1;
		int zfrom = this.z * 16;
		int zto = zfrom + 16;
		
		for (int x = xfrom; x <= xto; x++)
		{
			for (int y = yfrom; y <= yto; y++)
			{
				for (int z = zfrom; z <= zto; z++)
				{
					LightUtil.recalcLightLevelAt(world, x, y, z);
				}
			}
		}
		
		return true;
	}
	
	// -------------------------------------------- //
	// COMPARISON
	// -------------------------------------------- //
	
	@Override
	public int hashCode()
	{
		int hash = 3;
		hash = 19 * hash + (this.world != null ? this.world.hashCode() : 0);
		hash = 19 * hash + this.x;
		hash = 19 * hash + this.z;
		return hash;
	};
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj == this) return true;
		if (!(obj instanceof ChunkWrap)) return false;

		ChunkWrap that = (ChunkWrap) obj;
		return this.x == that.x && this.z == that.z && ( this.world==null ? that.world==null : this.world.equals(that.world) );
	}
	
}
