package com.massivecraft.massivelight.cmd;

import java.util.List;

import com.massivecraft.massivecore.cmd.MassiveCommand;
import com.massivecraft.massivecore.cmd.VersionCommand;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivelight.MassiveLight;
import com.massivecraft.massivelight.Perm;
import com.massivecraft.massivelight.entity.MConf;

public class CmdLight extends MassiveCommand
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public CmdLightFix cmdLightFix = new CmdLightFix();
	public VersionCommand cmdLightVersion = new VersionCommand(MassiveLight.get(), Perm.VERSION.node, "v", "version");
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdLight()
	{
		// Add SubCommands
		this.addSubCommand(this.cmdLightFix);
		this.addSubCommand(this.cmdLightVersion);
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.BASECOMMAND.node));
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
