package pw.vexus.core.commands;

import net.cogzmc.core.Core;
import net.cogzmc.core.modular.command.ArgumentRequirementException;
import net.cogzmc.core.modular.command.CommandException;
import net.cogzmc.core.modular.command.ModuleCommand;
import net.cogzmc.core.player.CPlayer;
import pw.vexus.core.TeleMan;
import pw.vexus.core.VexusCore;

public final class TpaCommand extends ModuleCommand {
    public TpaCommand() {
        super("tpa");
    }

    @Override
    protected void handleCommand(final CPlayer player, String[] args) throws CommandException {
        if (args.length < 1) throw new ArgumentRequirementException("You have not specified a player!");
        final CPlayer target = Core.getPlayerManager().getFirstOnlineCPlayerForStartOfName(args[0]);
        if (target == null) throw new ArgumentRequirementException("The player you have specified does not exist!");
        Confirmer.confirm("Do you want to allow " + player.getDisplayName() + " to teleport to you?", target, new Confirmer.ConfrimerCallback() {
            @Override
            public void call(boolean result, CPlayer pl) {
                if (!player.isOnline()) return;
                if (!result) {
                    player.sendMessage(VexusCore.getInstance().getFormat("tpa-denied"));
                    pl.sendMessage(VexusCore.getInstance().getFormat("tpa-deny"));
                    return;
                }
                TeleMan.teleportPlayer(player, target.getBukkitPlayer().getLocation());
                player.sendMessage(VexusCore.getInstance().getFormat("tpa-begin", new String[]{"<player>", target.getDisplayName()}));
                pl.sendMessage(VexusCore.getInstance().getFormat("tpa-accept"));
            }
        });
        player.sendMessage(VexusCore.getInstance().getFormat("tpa-sent", new String[]{"<player>", target.getDisplayName()}));
    }
}