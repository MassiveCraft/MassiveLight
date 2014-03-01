package com.massivecraft.lightfixer;

import com.massivecraft.lightfixer.cmd.CmdLightFixer;
import com.massivecraft.lightfixer.entity.MConfColl;
import com.massivecraft.mcore.MPlugin;

public class LightFixer extends MPlugin
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static LightFixer i;
	public static LightFixer get() { return i; }
	public LightFixer() { LightFixer.i = this; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	// Commands
	private CmdLightFixer cmdLightFixer;
	public CmdLightFixer getCmdLightFixer() { return this.cmdLightFixer; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void onEnable()
	{
		if ( ! preEnable()) return;
		
		// Initialize Database
		MConfColl.get().init();
		
		// Commands
		this.cmdLightFixer = new CmdLightFixer();
		this.cmdLightFixer.register();
		
		// Engines
		EngineGenfix.get().activate();
				
		postEnable();
	}   
	
}