package com.gmail.nomad856.kamakarzy;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
public class Main extends JavaPlugin {
	public final Logger logger = Logger.getLogger("Minecraft");
	public static Main plugin;
	public HashMap<String, String> marriage = new HashMap<String, String>();
	public huggsconfigManager manager;

	public huggsconfig config;
	public huggsconfig messages;

	@Override
	public void onEnable() {
		getServer().getLogger();
		String[] header = { "HUGGS", "MARRIED", "PLAYERS!" };
		this.manager = new huggsconfigManager(this);
		this.config = manager.getNewConfig("marriges.yml");
		this.config.saveConfig();
		saveDefaultConfig();
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	@Override
	public void onDisable() {
		getServer().getLogger();
	}

	public void hearts(Player p) {
		Wolf o = p.getWorld().spawn(p.getLocation(), Wolf.class);
		o.playEffect(EntityEffect.WOLF_HEARTS);
		o.remove();
	}

	public void Villager(Player p) {
		org.bukkit.entity.Villager v = p.getWorld().spawn(p.getLocation(),
				org.bukkit.entity.Villager.class);
		v.setBaby();
	}

	public boolean onCommand(CommandSender sender, Command cmd,String commandLabel, String[] args) {

		if (cmd.getName().equalsIgnoreCase("hugs")) {
			if (sender instanceof Player) {
				if (args.length > 0) {
					
					Player target = Bukkit.getPlayer(args[0]);
					
				
					if (target != null) {
						final Player player = (Player) sender;
						Location playerLoc = player.getLocation();
						Location targetLoc = target.getLocation();
						
						if (playerLoc.getWorld().getUID()
								.equals(targetLoc.getWorld().getUID())) {
							if (playerLoc.distanceSquared(targetLoc) < 4)
								if (player.hasPermission(new Permisssions().hug)) {
									{
										BukkitRunnable run = new BukkitRunnable() {
											private int count = 0;

											@Override
											public void run() {
												if (count >= getConfig()
														.getInt("Hug_Time")) {
													this.cancel();
													return;
												}
												hearts(player);
												count++;

											}

										};
										run.runTaskTimer(this, 0L, 20L);

										target.sendMessage((ChatColor
												.translateAlternateColorCodes(
														'&',
														getConfig()
																.getString(
																		"Reciever_Message")
																.replace(
																		"%P",
																		target.getName())
																.replace(
																		"%S",
																		sender.getName()))));
										player.sendMessage((ChatColor.translateAlternateColorCodes(
														'&',
														getConfig()
																.getString(
																		"Sender_Message")
																.replace(
																		"%P",
																		target.getName())
																.replace(
																		"%S",
																		sender.getName()))));
										return true;
									}
								} else {
									player.sendMessage(ChatColor.RED
											+ "YOU DO NOT HAVE PERMISSION TO HUG");
									return true;
								}
							else {
								sender.sendMessage(target.getDisplayName()
										+ ChatColor.RED + " is too far away.");
								return true;
							}
						} else {
							sender.sendMessage(target.getDisplayName()
									+ ChatColor.RED
									+ " is not in the same world as you.");
							return true;
						}
					} else {
						sender.sendMessage(ChatColor.RED
								+ "Can't find player: " + args[0]);
						return true;
					}
				} else {
					return false; // print usage
				}
			} else {
				sender.sendMessage("This command can only be used by a player.");
				return true;
			}
		} else if (cmd.getName().equalsIgnoreCase("kiss")) {
			if (sender instanceof Player) {
				System.out.println(args.length);
				if (args.length > 0) {
					Player target = Bukkit.getPlayer(args[0]);
					
					if (target != null) {
						if(!args[0].equals(sender.getName())){
						
						
						final Player player = (Player) sender;
						Location playerLoc = player.getLocation();
						Location targetLoc = target.getLocation();

						if (playerLoc.getWorld().getUID()
								.equals(targetLoc.getWorld().getUID())) {
							if (playerLoc.distanceSquared(targetLoc) < 4)
								if (player
										.hasPermission(new Permisssions().kiss)) {
									{
										BukkitRunnable run = new BukkitRunnable() {

											private int count = 0;

											@Override
											public void run() {
												if (count >= getConfig()
														.getInt("Kiss_Time")) {
													this.cancel();
													return;
												}
												hearts(player);
												count++;

											}

										};
										run.runTaskTimer(this, 0L, 20L);

										if (getConfig().get("SPAWN_BABY")
												.equals(true)) {
											Villager(player);
										}
										target.sendMessage(ChatColor
												.translateAlternateColorCodes(
														'&',
														getConfig()
																.getString(
																		"Kiss_Message_Reciever")
																.replace(
																		"%P",
																		target.getName())
																.replace(
																		"%S",
																		sender.getName())));
										player.sendMessage((ChatColor
												.translateAlternateColorCodes(
														'&',
														getConfig()
																.getString(
																		"Kiss_Message_Sender")
																.replace(
																		"%P",
																		target.getName())
																.replace(
																		"%S",
																		sender.getName()))));
									}
								} else {
									player.sendMessage(ChatColor.RED
											+ "YOU DO NOT HAVE PERMISSION TO HUG");
									return true;
								}
							else {
								sender.sendMessage(target.getDisplayName()
										+ ChatColor.RED + " is too far away.");
								return true;
							}
						} else {
							sender.sendMessage(target.getDisplayName()
									+ ChatColor.RED
									+ " is not in the same world as you.");
							return true;
						}
					} else {
						sender.sendMessage(ChatColor.RED 
								+ "You can't kiss yourself :P");
						
					}
					} else {
						sender.sendMessage(ChatColor.RED
								+ "Can't find player: " + args[0]);
						return true;
					}
				} else {
					return false;
				}
			} else {
				sender.sendMessage("This command can only be used by a player.");
				return true;
			}
		} else if (cmd.getName().equalsIgnoreCase("willyoumarryme")) {
			if (sender instanceof Player) {
				System.out.println(args.length);
				if (args.length > 0) {
					Player target = Bukkit.getPlayer(args[0]);

					if (target != null) {
						final Player player = (Player) sender;
						Location playerLoc = player.getLocation();
						Location targetLoc = target.getLocation();

						if (playerLoc.getWorld().getUID()
								.equals(targetLoc.getWorld().getUID())) {
							if (playerLoc.distanceSquared(targetLoc) < 4)
								if (player
										.hasPermission(new Permisssions().marry)) {
									if (this.config.contains(player.getName())) {
										player.sendMessage("YOU CANNOT PERFORM BIGAMY ON THIS SERVER");
									} else {
										marriage.put(target.getName(),
												player.getName());
										target.sendMessage("will you marry me love "
												+ target.getDisplayName()+ "?");
									}

								} else {
									player.sendMessage(ChatColor.RED
											+ "YOU DO NOT HAVE PERMISSION TO MARRY");
									return true;
								}
							else {
								sender.sendMessage(target.getDisplayName()
										+ ChatColor.RED + " is too far away.");
								return true;
							}
						} else {
							sender.sendMessage(target.getDisplayName()
									+ ChatColor.RED
									+ " is not in the same world as you.");
							return true;
						}
					} else {
						sender.sendMessage(ChatColor.RED
								+ "Can't find player: " + args[0]);
						return true;
					}
				} else {
					return false;
				}
			} else {
				sender.sendMessage("This command can only be used by a player.");
				return true;
			}
		} else if (cmd.getName().equalsIgnoreCase("ido")) {
			Player player = (Player) sender;
			if (sender instanceof Player){
				if (args.length > 0) {
					player.sendMessage(ChatColor.RED + "Incorrect usage");
				} else {
					if (marriage.get(player.getName()) != null) {
						String target = marriage.get(player.getName());
						this.config.set(target, "is Married");
						marriage.remove(target);
					} else {
					}
				}
			}
			else {
				sender.sendMessage("Can only be used by player");
			}
		}
		else if (cmd.getName().equalsIgnoreCase("no")) {
			Player player = (Player) sender;
			if (sender instanceof Player) {
				if (args.length > 0) {
					player.sendMessage(ChatColor.RED + "Incorrect usage");
				} else {
					marriage.remove(player.getName());
				}
			} else {
				sender.sendMessage("Can only be used by player");
			}
		}
		return true;
	}
}