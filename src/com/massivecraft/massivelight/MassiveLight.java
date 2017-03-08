package com.massivecraft.massivelight;

import com.massivecraft.massivecore.MassivePlugin;
import com.massivecraft.massivelight.cmd.CmdLight;
import com.massivecraft.massivelight.engine.EngineGenfix;
import com.massivecraft.massivelight.entity.MConfColl;
import com.massivecraft.massivelight.nms.NmsMassiveLight;

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
			// NMS
			NmsMassiveLight.class,
			
			// Coll
			MConfColl.class,
		
			// Engine
			EngineGenfix.class,
			
			// Command
			CmdLight.class
		);
	}   
	
}