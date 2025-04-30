package data;

import java.io.FileOutputStream;
import java.io.FileInputStream;

import main.GamePanel;
import java.io.File;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;


public class SaveLoad {
	
	GamePanel gp;
	
	public SaveLoad(GamePanel gp) {
		this.gp = gp;
	}
	
	public void save() {
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
		    DataStorage ds = new DataStorage();
		    
		    ds.lvl = gp.player.lvl;
		    ds.hp = gp.player.hp;
		    ds.maxHp = gp.player.maxHp;
		    
		    ds.attack = gp.player.attack;
		    ds.specialAttack = gp.player.specialAttack;
		    ds.defense = gp.player.defense;
		    
		    ds.specialDefense = gp.player.specialDefense;
		    ds.attackSpeed = gp.player.attackSpeed;
		    ds.luck = gp.player.luck;
		    
		    ds.exp = gp.player.exp;
		    ds.nextLevelExp = gp.player.nextLevelExp;
		    ds.currency = gp.player.currency;
		    
		    ds.potion = gp.player.potion;
		    
		    oos.writeObject(ds);
		    
		}
		catch(Exception e){
			System.out.println("Error: Could Not Save!");
		}
	}
	
	public void load() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));
			
			DataStorage ds = (DataStorage)ois.readObject();
			
			gp.player.lvl = ds.lvl;
			gp.player.hp = ds.hp;
			gp.player.maxHp = ds.maxHp;

			gp.player.attack = ds.attack;
			gp.player.specialAttack = ds.specialAttack;
			gp.player.defense = ds.defense;

			gp.player.specialDefense = ds.specialDefense;
			gp.player.attackSpeed = ds.attackSpeed;
			gp.player.luck = ds.luck;

			gp.player.exp = ds.exp;
			gp.player.nextLevelExp = ds.nextLevelExp;
			gp.player.currency = ds.currency;

			gp.player.potion = ds.potion;

			
			
		}
		catch(Exception e){
			System.out.println("Error: Could Not Load Save!");
		}
	}
	
	
	
	
	
}
