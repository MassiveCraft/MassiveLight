package com.massivecraft.lightfixer;

import net.minecraft.server.WorldServer;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.CraftWorld;

public class LightUtil
{
    /**
     * This method will perform a full light update in the chunk.
     * It will only work if the surrounding chunks exist.
     */
    public static boolean updateChunkLight(ChunkWrap chunk)
    {
        if ( ! loadSurroundingChunks(chunk, true))
        {
            return false;
        }
        
        World world = chunk.world;
        int xfrom = chunk.x * 16;
        int xto = xfrom + 16;
        int yfrom = 0;
        int yto = chunk.world.getMaxHeight() - 1;
        int zfrom = chunk.z * 16;
        int zto = zfrom + 16;
        
        for (int x = xfrom; x <= xto; x++)
        {
            for (int y = yfrom; y <= yto; y++)
            {
                for (int z = zfrom; z <= zto; z++)
                {
                    updateBlockLight(world, x, y, z);
                }
            }
        }
        
        return true;
    }
    
    /**
     * This method will update the light level for the block.
     * It will however only work properly if all chunks that are around the chunk the block is in are loaded.
     */
    public static void updateBlockLight(Block block)
    {
        updateBlockLight(block.getWorld(), block.getX(), block.getY(), block.getZ());
    }
    public static void updateBlockLight(World world, int x, int y, int z)
    {
        CraftWorld cworld = (CraftWorld)world;
        WorldServer worldServer = cworld.getHandle();
        worldServer.v(x, y, z);
    }
    
    /**
     * Try to load the chunks around a certain chunk.
     * No attempts to generate chunks will be made. If one of the chunks doesnt exist
     * the operation will be canceled and false will be returned.
     */
    public static boolean loadSurroundingChunks(ChunkWrap cw, boolean includeSelf)
    {
        for (ChunkWrap othercw : cw.getSurrounding(includeSelf))
        {
            if ( ! othercw.world.loadChunk(othercw.x, othercw.z, false))
            {
                return false;
            }
        }
        return true;
    }
}
