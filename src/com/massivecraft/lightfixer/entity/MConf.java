package com.massivecraft.lightfixer.entity;

import java.util.List;

import com.massivecraft.mcore.store.Entity;
import com.massivecraft.mcore.util.MUtil;

public class MConf extends Entity<MConf>
{
	// -------------------------------------------- //
	// META
	// -------------------------------------------- //
	
	protected static transient MConf i;
	public static MConf get() { return i; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	// Aliases
	public List<String> aliasesLightFixer = MUtil.list("lightfixer", "lightfix", "lf");
	
	// Other
	public boolean genfix = true;
	public int maxradius = 5;

}
