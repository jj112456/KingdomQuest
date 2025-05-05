package ai;

import java.util.Random;

import main.GamePanel;

public class CombatSystemSkeleton {

    GamePanel gp;

    public CombatSystemSkeleton(GamePanel gp) {
        this.gp = gp;
    }

    public void handleAttack() {
        int skeletonHp = gp.skeleton.getHp() - gp.player.getAttack();
        gp.skeleton.setHp(skeletonHp);

        if (gp.skeleton.getHp() <= 0) {

            gp.player.setExp(gp.player.getExp() + gp.skeleton.getExpDrop());

            gp.skeleton.setHp(gp.skeleton.maxHp);
            gp.gameState = gp.playState;
        } else {
            int playerHp = gp.player.getHp() - gp.skeleton.getAttack();
            gp.player.setHp(playerHp);

            gp.ui.battleMessage = "The skeleton attacks back and does " + gp.skeleton.getAttack() + " damage.";
            gp.ui.messageActive = true;
        }
    }


    public void handleSpecialAttack() {
        int skeletonHp = gp.skeleton.getHp() - gp.player.getSpecialAttack();
        gp.skeleton.setHp(skeletonHp);

        if (gp.skeleton.getHp() <= 0) {

            gp.player.setExp(gp.player.getExp() + gp.skeleton.getExpDrop());

            gp.skeleton.setHp(gp.skeleton.maxHp);
            gp.gameState = gp.playState;
        } else {
            int playerHp = gp.player.getHp() - gp.skeleton.getAttack();
            gp.player.setHp(playerHp);

            gp.ui.battleMessage = "The skeleton attacks back and does " + gp.skeleton.getAttack() + " damage.";
            gp.ui.messageActive = true;
        }
    }

    public void handleRun() {
    	Random rand = new Random();
        
        if (rand.nextBoolean()) { 
            gp.ui.battleMessage = "Can not Run! The skeleton attacks back and does "+ gp.skeleton.getAttack() + " damage.";
            gp.ui.messageActive = true;
            int playerHp = gp.player.getHp() - gp.skeleton.getAttack();
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

