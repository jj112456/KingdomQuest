package ai;

import main.GamePanel;

public class CombatSystemSkeletonBoss {

    GamePanel gp;

    public CombatSystemSkeletonBoss(GamePanel gp) {
        this.gp = gp;
    }

    public void handleAttack() {
        int skeletonBossHp = gp.skeletonBoss.getHp() - gp.player.getAttack();
        gp.skeletonBoss.setHp(skeletonBossHp);

        if (gp.skeletonBoss.getHp() <= 0) {

            gp.player.setExp(gp.player.getExp() + gp.skeletonBoss.getExpDrop());

            gp.skeletonBoss.setHp(gp.skeletonBoss.maxHp);
            gp.gameState = gp.playState;
        } else {
            int playerHp = gp.player.getHp() - gp.skeletonBoss.getAttack();
            gp.player.setHp(playerHp);

            gp.ui.battleMessage = "The Skeleton Boss attacks back and does " + gp.skeletonBoss.getAttack() + " damage.";
            gp.ui.messageActive = true;
        }
    }


    public void handleSpecialAttack() {
        int skeletonBossHp = gp.skeletonBoss.getHp() - gp.player.getSpecialAttack();
        gp.skeletonBoss.setHp(skeletonBossHp);

        if (gp.skeletonBoss.getHp() <= 0) {

            gp.player.setExp(gp.player.getExp() + gp.skeletonBoss.getExpDrop());

            gp.skeletonBoss.setHp(gp.skeletonBoss.maxHp); 
            gp.gameState = gp.playState;
        } else {
            int playerHp = gp.player.getHp() - gp.skeletonBoss.getAttack();
            gp.player.setHp(playerHp);

            gp.ui.battleMessage = "The Skeleton Boss attacks back and does " + gp.skeletonBoss.getAttack() + " damage.";
            gp.ui.messageActive = true;
        }
    }

    public void handleRun() {
            gp.ui.battleMessage = "Can not Run from a Boss Battle!";
            gp.ui.messageActive = true;
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

