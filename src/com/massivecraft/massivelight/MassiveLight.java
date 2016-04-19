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
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void onEnableInner()
	{
		// Active
		this.activate(
			// Coll
			MConfColl.class,
		
			// Engine
			EngineGenfix.class,
			
			// Command
			CmdLight.class
		);
	}   
	
}