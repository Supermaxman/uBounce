package me.supermaxman.ubounce;

import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Jukebox;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

@SuppressWarnings("unused")
public class uBounce extends JavaPlugin implements Listener{
	
	//Required
	public static uBounce plugin;
	public final Logger logger = Logger.getLogger("Minecraft");
	@Override
	public void onDisable() {this.logger.info("uBounce Disabled, No More Bouncing!");}
	@Override
	public void onEnable() {
        getServer().getPluginManager().registerEvents(new uBounce(), this);
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled! Sorry It Took So Long To Update!");
		
	}
	
	//Events
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		Player player = event.getPlayer();
		Action action = event.getAction();
		if (action == Action.valueOf("PHYSICAL")){
		//Location loc = player.getLocation();
		Block block = event.getClickedBlock();
		Block block2 = block.getRelative(0,-1,0);
		if ((block.getType() == Material.STONE_PLATE)&&(block2.getType() == Material.MOSSY_COBBLESTONE)&&(player.isSneaking()!=true)){
			block.setType(Material.STONE_PLATE);
			if(block2.getRelative(0, -1, 0).getType()==Material.MOSSY_COBBLESTONE){
				if(block2.getRelative(0, -2, 0).getType()==Material.MOSSY_COBBLESTONE){
					Vector vector = new Vector(0, 4.5, 0);
					player.setVelocity(vector);
				}else{
					Vector vector = new Vector(0, 3, 0);
					player.setVelocity(vector);
				}
			}else{
				Vector vector = new Vector(0, 1.5, 0);
				player.setVelocity(vector);
			}
		}else if ((block.getType() == Material.WOOD_PLATE)&&(block2.getType() == Material.SANDSTONE)&&(block2.getData()==(byte) 1)&&(player.isSneaking()!=true)){
			block.setType(Material.WOOD_PLATE);
			//0.5 = 1 block
			int bh = 0;
			int i = 1;
			while(i<255){
			Block b = block.getRelative(0,-i,0);
			if ((b.getType()==Material.SANDSTONE)&&(b.getData()==(byte) 1)){
				bh++;
				i++;
			}else{
				i=255;
			}
			}
			/*
			 * 4b = 1v
			 * 18b = 2v
			 * 36b = 3v
			 * 60b = 4v
			 * quadratic fit{1, 4},{2, 18},{3, 36},{4, 60} = 2.5(x^2) + 6.1x - 4.5
			 * quadratic fit{4, 1},{18, 2},{36, 3},{60, 4} = -0.000387672x^2 + 0.0781934x + 0.700756
			 * 
			 * -0.000387672x^2 + 0.0781934x + 0.700756
			 * 0.5 = 1 
			 * 1 = 4
			 * 2 = 18
			 * 3 = 36
			 */
			int x = bh;
			if (x>100){x=100;}
			if (x<0){x=100;}
			double v = (-0.000387672*(x*x)) + (0.0781934*x) + (0.700756);
			Vector vector = new Vector(0, v, 0);
			player.setVelocity(vector);
			}
		}
	}
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event){
			Entity player = event.getEntity();
			Location loc = player.getLocation();
			Block block = loc.getBlock();
		    Block block2 = block.getRelative(0,-1,0);
		if ((block2.getType() == Material.MOSSY_COBBLESTONE)&&(block.getType() == Material.STONE_PLATE)){
			event.setCancelled(true);
			
		}else if ((block2.getType() == Material.SANDSTONE)&&(block.getType() == Material.WOOD_PLATE)&&(block2.getData()==(byte) 1)){
			event.setCancelled(true);
			
		}
		}
	@EventHandler
	public void onEntityInteract(EntityInteractEvent event){
		Entity player = event.getEntity();
		if (!(player instanceof Player)){
		//Location loc = player.getLocation();
		Block block = event.getBlock();
		Block block2 = block.getRelative(0,-1,0);
		if ((block.getType() == Material.STONE_PLATE)&&(block2.getType() == Material.MOSSY_COBBLESTONE)){
			block.setType(Material.STONE_PLATE);
			if(block2.getRelative(0, -1, 0).getType()==Material.MOSSY_COBBLESTONE){
				if(block2.getRelative(0, -2, 0).getType()==Material.MOSSY_COBBLESTONE){
					Vector vector = new Vector(0, 4.5, 0);
					player.setVelocity(vector);
				
				
				}else{
					Vector vector = new Vector(0, 3, 0);
					player.setVelocity(vector);
				}
			}else{
				Vector vector = new Vector(0, 1.5, 0);
				player.setVelocity(vector);
			}
		}else if ((block.getType() == Material.WOOD_PLATE)&&(block2.getType() == Material.SANDSTONE)&&(block2.getData()==(byte) 1)){
			block.setType(Material.WOOD_PLATE);
			//0.5 = 1 block
			int bh = 0;
			int i = 1;
			while(i<255){
			Block b = block.getRelative(0,-i,0);
			if ((b.getType()==Material.SANDSTONE)&&(b.getData()==(byte) 1)){
				bh++;
				i++;
			}else{
				i=255;
			}
			}
			/*
			 * 4b = 1v
			 * 18b = 2v
			 * 36b = 3v
			 * 60b = 4v
			 * quadratic fit{1, 4},{2, 18},{3, 36},{4, 60} = 2.5(x^2) + 6.1x - 4.5
			 * quadratic fit{4, 1},{18, 2},{36, 3},{60, 4} = -0.000387672x^2 + 0.0781934x + 0.700756
			 * 
			 * -0.000387672x^2 + 0.0781934x + 0.700756
			 * 0.5 = 1 
			 * 1 = 4
			 * 2 = 18
			 * 3 = 36
			 */
			int x = bh;
			if (x>100){x=100;}
			if (x<0){x=100;}
			double v = (-0.000387672*(x*x)) + (0.0781934*x) + (0.700756);
			Vector vector = new Vector(0, v, 0);
			player.setVelocity(vector);
			}
		}
	}
	@EventHandler
	public void onVehicleMove(VehicleMoveEvent event){
		Entity player = event.getVehicle();
		if ((player instanceof Minecart)){
			Minecart cart = (Minecart) player;
		Location loc = player.getLocation();
		Block block = loc.getBlock().getRelative(0,0,0);
		Block block2 = loc.getBlock().getRelative(0,-1,0);
		if ((block.getType() == Material.DETECTOR_RAIL)&&(block2.getType() == Material.MOSSY_COBBLESTONE)){
		
		Vector vector = new Vector(cart.getVelocity().getX()*2, 1.5, cart.getVelocity().getZ()*2);
		cart.teleport(cart.getLocation().add(0, 1.5, 0));
		cart.setVelocity(vector);

		}	
	}
	}	
	
	
	
	/*TODO Improve and Implement Piston Launchers
	
	@EventHandler
	public void onBlockPistonExtend(BlockPistonExtendEvent event){
		Block block = event.getBlock();
		Block mossy = block.getRelative(BlockFace.UP, 1);
		if (block.getRelative(BlockFace.UP).getType()==Material.PISTON_EXTENSION){
			mossy = block.getRelative(BlockFace.UP, 1);
		}else if (block.getRelative(BlockFace.DOWN).getType()==Material.PISTON_EXTENSION){
			mossy = block.getRelative(BlockFace.DOWN, 1);
		}else if (block.getRelative(BlockFace.NORTH).getType()==Material.PISTON_EXTENSION){
			mossy = block.getRelative(BlockFace.NORTH, 1);
		}else if (block.getRelative(BlockFace.SOUTH).getType()==Material.PISTON_EXTENSION){
			mossy = block.getRelative(BlockFace.SOUTH, 1);
		}else if (block.getRelative(BlockFace.EAST).getType()==Material.PISTON_EXTENSION){
			mossy = block.getRelative(BlockFace.EAST, 1);
		}else if (block.getRelative(BlockFace.WEST).getType()==Material.PISTON_EXTENSION){
			mossy = block.getRelative(BlockFace.WEST, 1);
		}
		
		
		if ((block.getType() == Material.PISTON_STICKY_BASE)&&
			(mossy.getType() == Material.MOSSY_COBBLESTONE)){
			for (Player p:mossy.getWorld().getPlayers()){
				System.out.println(p.getLocation().add(0, -1, 0).toString());
				System.out.println(mossy.getLocation().toString());
				if ((p.getLocation().add(0, 0, 0).getX()==mossy.getLocation().getX())&&
					(p.getLocation().add(0, -1, 0).getY()==mossy.getLocation().getY())&&
					(p.getLocation().add(0, 0, 0).getZ()==mossy.getLocation().getZ())){
					
					Vector vector = new Vector(0, 1.5, 0);
					p.setVelocity(vector);
				}
			}
			
		}
		
	}
	
	*/
	//Msc
	
	
}