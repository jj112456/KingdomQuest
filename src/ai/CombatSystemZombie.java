package ai;

import main.GamePanel;
import java.util.Random;


public class CombatSystemZombie {

    GamePanel gp;

    public CombatSystemZombie(GamePanel gp) {
        this.gp = gp;
    }

    public void handleAttack() {
        int zombieHp = gp.zombie.getHp() - gp.player.getAttack();
        gp.zombie.setHp(zombieHp);

        if (gp.zombie.getHp() <= 0) {

            gp.player.setExp(gp.player.getExp() + gp.zombie.getExpDrop());

            gp.zombie.setHp(gp.zombie.maxHp);
            

            //gp.gameState = gp.dialogueStateObject;
			//gp.ui.showMessage("Enemy Defeated! EXP Gained: " + gp.zombie.getExpDrop());
            
			gp.gameState = gp.playState;
            
        } else {
            int playerHp = gp.player.getHp() - gp.zombie.getAttack();
            gp.player.setHp(playerHp);

            gp.ui.battleMessage = "The zombie attacks back and does " + gp.zombie.getAttack() + " damage.";
            gp.ui.messageActive = true;
        }
    }


    public void handleSpecialAttack() {
        int zombieHp = gp.zombie.getHp() - gp.player.getSpecialAttack();
        gp.zombie.setHp(zombieHp);

        if (gp.zombie.getHp() <= 0) {

            gp.player.setExp(gp.player.getExp() + gp.zombie.getExpDrop());

            gp.zombie.setHp(gp.zombie.maxHp);
            
            //gp.gameState = gp.dialogueStateObject;
			//gp.ui.showMessage("Enemy Defeated! EXP Gained: " + gp.zombie.getExpDrop());
            
			gp.gameState = gp.playState;
            
        } else {
            int playerHp = gp.player.getHp() - gp.zombie.getAttack();
            gp.player.setHp(playerHp);

            gp.ui.battleMessage = "The zombie attacks back and does " + gp.zombie.getAttack() + " damage.";
            gp.ui.messageActive = true;
        }
    }

    public void handleRun() {
    	Random rand = new Random();
        
        if (rand.nextBoolean()) { 
            gp.ui.battleMessage = "Can not Run! The zombie attacks back and does "+ gp.zombie.getAttack() + " damage.";
            gp.ui.messageActive = true;
            int playerHp = gp.player.getHp() - gp.zombie.getAttack();
            gp.player.setHp(playerHp);
        }else {
        	
        	//gp.gameState = gp.dialogueStateObject;
        	//gp.ui.showMessage("Successfully Ran from Battle");
        	
        	gp.gameState = gp.playState;
        	
        }
    }

    public void handleItem() {
    	if(gp.player.getPotion()>=1) {
    		gp.player.setHp((gp.player.getHp())+20);
    		gp.ui.battleMessage = "Used Potion and Gained 20HP";
            gp.ui.messageActive = true;
    	}
    	else {
    		gp.ui.battleMessage = "No potions available!";
            gp.ui.messageActive = true;
    	}
        
    }
}

