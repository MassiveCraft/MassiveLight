package com.massivecraft.massivelight.cmd;

import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivelight.Perm;
import com.massivecraft.massivelight.entity.MConf;

import java.util.List;

public class CmdLight extends MassiveCommand
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static CmdLight i = new CmdLight();
	public static CmdLight get() { return i; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public CmdLightFix cmdLightFix = new CmdLightFix();
	public CmdLightConfig cmdLightConfig = new CmdLightConfig();
	public CmdLightVersion cmdLightVersion = new CmdLightVersion();
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdLight()
	{
		// Children
		this.addChild(this.cmdLightFix);
		this.addChild(this.cmdLightConfig);
		this.addChild(this.cmdLightVersion);
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.BASECOMMAND));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesLight;
	}

}
