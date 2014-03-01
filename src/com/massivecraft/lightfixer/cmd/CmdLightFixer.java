package com.massivecraft.lightfixer.cmd;

import java.util.List;

import com.massivecraft.lightfixer.LightFixer;
import com.massivecraft.lightfixer.Perm;
import com.massivecraft.lightfixer.entity.MConf;
import com.massivecraft.mcore.cmd.HelpCommand;
import com.massivecraft.mcore.cmd.MCommand;
import com.massivecraft.mcore.cmd.VersionCommand;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;

public class CmdLightFixer extends MCommand
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public CmdLightFixerFix cmdLightFixerFix = new CmdLightFixerFix();
	public VersionCommand cmdLightFixerVersion = new VersionCommand(LightFixer.get(), Perm.VERSION.node, "v", "version");
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdLightFixer()
	{
		// Add SubCommands
		this.addSubCommand(HelpCommand.get());
		this.addSubCommand(this.cmdLightFixerFix);
		this.addSubCommand(this.cmdLightFixerVersion);
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.BASECOMMAND.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesLightFixer;
	}

}
