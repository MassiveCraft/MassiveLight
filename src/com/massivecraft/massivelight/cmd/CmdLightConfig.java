package com.massivecraft.massivelight.cmd;

import java.util.List;

import com.massivecraft.massivecore.command.editor.CommandEditSingleton;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivelight.Perm;
import com.massivecraft.massivelight.entity.MConf;

public class CmdLightConfig extends CommandEditSingleton<MConf>
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdLightConfig()
	{
		super(MConf.get());
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.CONFIG));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesLightConfig;
	}
	
}
