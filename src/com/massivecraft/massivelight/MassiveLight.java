package com.massivecraft.massivelight;

import com.massivecraft.massivecore.MassivePlugin;
import com.massivecraft.massivelight.cmd.CmdLight;
import com.massivecraft.massivelight.entity.MConfColl;

public class MassiveLight extends MassivePlugin
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static MassiveLight i;
	public static MassiveLight get() { return i; }
	public MassiveLight() { MassiveLight.i = this; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	// Commands
	private CmdLight cmdLight;
	public CmdLight getCmdLight() { return this.cmdLight; }
	
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
		this.cmdLight = new CmdLight();
		this.cmdLight.register();
		
		// Engines
		EngineGenfix.get().activate();
				
		postEnable();
	}   
	
}