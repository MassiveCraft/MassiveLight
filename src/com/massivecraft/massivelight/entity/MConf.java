package com.massivecraft.massivelight.entity;

import com.massivecraft.massivecore.command.editor.annotation.EditorName;
import com.massivecraft.massivecore.store.Entity;
import com.massivecraft.massivecore.util.MUtil;

import java.util.List;

@EditorName("config")
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
	public List<String> aliasesLightConfig = MUtil.list("config");
	public List<String> aliasesLightFix = MUtil.list("fix");
	public List<String> aliasesLightVersion = MUtil.list("v", "version");
	
	// Other
	public boolean genfix = true;
	public int maxradius = 5;

}
