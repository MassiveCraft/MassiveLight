package com.massivecraft.massivelight.entity;

import java.util.List;

import com.massivecraft.massivecore.store.Entity;
import com.massivecraft.massivecore.util.MUtil;

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
	public List<String> aliasesLight = MUtil.list("light", "massivelight");
	
	// Other
	public boolean genfix = true;
	public int maxradius = 5;

}
