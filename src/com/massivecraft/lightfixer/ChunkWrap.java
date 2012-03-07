package com.massivecraft.lightfixer;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Chunk;
import org.bukkit.World;

public class ChunkWrap
{
    //----------------------------------------------//
    // FIELDS
    //----------------------------------------------//
    public World world;
    public int x;
    public int z;
    
    //----------------------------------------------//
    // CONSTRUCTORS
    //----------------------------------------------//
    
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
    
    //----------------------------------------------//
    // UTIL
    //----------------------------------------------//
    
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
    
    //----------------------------------------------//
    // COMPARISON
    //----------------------------------------------//
    
    public int hashCode()
    {
        int hash = 3;
        hash = 19 * hash + (this.world != null ? this.world.hashCode() : 0);
        hash = 19 * hash + this.x;
        hash = 19 * hash + this.z;
        return hash;
    };
    
    public boolean equals(Object obj)
    {
        if (obj == this) return true;
        if (!(obj instanceof ChunkWrap)) return false;

        ChunkWrap that = (ChunkWrap) obj;
        return this.x == that.x && this.z == that.z && ( this.world==null ? that.world==null : this.world.equals(that.world) );
    }
}
