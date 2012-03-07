package com.massivecraft.lightfixer;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkPopulateEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class P extends JavaPlugin implements Listener
{
    protected Set<ChunkWrap> fixed = new HashSet<ChunkWrap>(); 
    
    @Override
    public void onEnable()
    {
        getServer().getPluginManager().registerEvents(this, this);
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if ( ! (sender instanceof Player))
        {
            sender.sendMessage(ChatColor.RED.toString()+"Can only be used by ingame players");
            return true;
        }
        
        Player me = (Player)sender;
        Chunk chunk = me.getLocation().getChunk();
        
        if (LightUtil.updateChunkLight(new ChunkWrap(chunk)))
        {
            sender.sendMessage(ChatColor.GREEN.toString()+"Successfully updated all light in this chunk (:");
        }
        else
        {
            sender.sendMessage(ChatColor.RED.toString()+"Lightupdate failed. All surrounding chunks must exist. ):");
        }
        
        return true;
    }
    
    /**
     * Each time a chunk is generated we try to fix light there and in all surrounding chunks.
     */
    @EventHandler(priority = EventPriority.MONITOR)
    public void monitorChunkPopulateEvent(ChunkPopulateEvent event)
    {
        ChunkWrap populatedcw = new ChunkWrap(event.getChunk());
        for (ChunkWrap cw : populatedcw.getSurrounding(true))
        {
            if (this.fixed.contains(cw))
            {
                continue;
            }
            
            if (LightUtil.updateChunkLight(cw))
            {
                this.fixed.add(cw);
            }
        }
    }
}