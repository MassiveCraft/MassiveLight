package com.massivecraft.massivelight.cmd;

import java.util.List;

import com.massivecraft.massivecore.command.MassiveCommandVersion;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivelight.MassiveLight;
import com.massivecraft.massivelight.Perm;
import com.massivecraft.massivelight.entity.MConf;

public class CmdLightVersion extends MassiveCommandVersion
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdLightVersion()
	{
		super(MassiveLight.get());
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.VERSION));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesLightVersion;
	}
	
}
