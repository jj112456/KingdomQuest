package ai;

import java.util.Random;

import main.GamePanel;

public class CombatSystemSlime {

    GamePanel gp;

    public CombatSystemSlime(GamePanel gp) {
        this.gp = gp;
    }

    public void handleAttack() {
        int slimeHp = gp.slime.getHp() - gp.player.getAttack();
        gp.slime.setHp(slimeHp);

        if (gp.slime.getHp() <= 0) {

            gp.player.setExp(gp.player.getExp() + gp.slime.getExpDrop());

            gp.slime.setHp(gp.slime.maxHp);
            gp.gameState = gp.playState;
        } else {
            int playerHp = gp.player.getHp() - gp.slime.getAttack();
            gp.player.setHp(playerHp);

            gp.ui.battleMessage = "The slime attacks back and does " + gp.slime.getAttack() + " damage.";
            gp.ui.messageActive = true;
        }
    }


    public void handleSpecialAttack() {
        int slimeHp = gp.slime.getHp() - gp.player.getSpecialAttack();
        gp.slime.setHp(slimeHp);

        if (gp.slime.getHp() <= 0) {

            gp.player.setExp(gp.player.getExp() + gp.slime.getExpDrop());

            gp.slime.setHp(gp.slime.maxHp); // Resetting slime for future fights?
            gp.gameState = gp.playState;
        } else {
            int playerHp = gp.player.getHp() - gp.slime.getAttack();
            gp.player.setHp(playerHp);

            gp.ui.battleMessage = "The slime attacks back and does " + gp.slime.getAttack() + " damage.";
            gp.ui.messageActive = true;
        }
    }

    public void handleRun() {
    	Random rand = new Random();
        
        if (rand.nextBoolean()) { 
            gp.ui.battleMessage = "Can not Run! The slime attacks back and does "+ gp.slime.getAttack() + " damage.";
            gp.ui.messageActive = true;
            int playerHp = gp.player.getHp() - gp.slime.getAttack();
            gp.player.setHp(playerHp);
        }else {
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

