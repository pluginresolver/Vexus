package pw.vexus.core.commands;

import net.cogzmc.core.modular.command.CommandException;
import net.cogzmc.core.modular.command.ModuleCommand;
import net.cogzmc.core.player.CPlayer;
import net.cogzmc.core.util.Point;

public final class SetspawnCommand extends ModuleCommand {
    public SetspawnCommand() {
        super("setspawn");
    }

    @Override
    protected void handleCommand(CPlayer player, String[] args) throws CommandException {
        Point point = player.getPoint();
        player.getBukkitPlayer().getWorld().setSpawnLocation(point.getX().intValue(), point.getY().intValue(), point.getZ().intValue());
    }
}
