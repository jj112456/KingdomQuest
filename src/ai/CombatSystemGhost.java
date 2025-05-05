package ai;

import java.util.Random;

import main.GamePanel;

public class CombatSystemGhost {

    GamePanel gp;

    public CombatSystemGhost(GamePanel gp) {
        this.gp = gp;
    }

    public void handleAttack() {
        int ghostHp = gp.ghost.getHp() - gp.player.getAttack();
        gp.ghost.setHp(ghostHp);

        if (gp.ghost.getHp() <= 0) {

            gp.player.setExp(gp.player.getExp() + gp.ghost.getExpDrop());

            gp.ghost.setHp(gp.ghost.maxHp);
            gp.gameState = gp.playState;
        } else {
            int playerHp = gp.player.getHp() - gp.ghost.getSpecialAttack();
            gp.player.setHp(playerHp);

            gp.ui.battleMessage = "The ghost attacks back and does " + gp.ghost.getSpecialAttack() + " damage.";
            gp.ui.messageActive = true;
        }
    }


    public void handleSpecialAttack() {
        int ghostHp = gp.ghost.getHp() - gp.player.getSpecialAttack();
        gp.ghost.setHp(ghostHp);

        if (gp.ghost.getHp() <= 0) {

            gp.player.setExp(gp.player.getExp() + gp.ghost.getExpDrop());

            gp.ghost.setHp(gp.ghost.maxHp);
            gp.gameState = gp.playState;
        } else {
            int playerHp = gp.player.getHp() - gp.ghost.getSpecialAttack();
            gp.player.setHp(playerHp);

            gp.ui.battleMessage = "The ghost attacks back and does " + gp.ghost.getSpecialAttack() + " damage.";
            gp.ui.messageActive = true;
        }
    }

    public void handleRun() {
    	Random rand = new Random();
        
        if (rand.nextBoolean()) { 
            gp.ui.battleMessage = "Can not Run! The ghost attacks back and does "+ gp.ghost.getSpecialAttack() + " damage.";
            gp.ui.messageActive = true;
            int playerHp = gp.player.getHp() - gp.ghost.getSpecialAttack();
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

