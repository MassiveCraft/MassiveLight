package com.massivecraft.massivelight;

import com.massivecraft.massivecore.MassivePlugin;

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
		this.activateAuto();
	}
	
}