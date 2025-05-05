package ai;

import main.GamePanel;

public class CombatSystemGhostBoss {

    GamePanel gp;

    public CombatSystemGhostBoss(GamePanel gp) {
        this.gp = gp;
    }

    public void handleAttack() {
        int ghostBossHp = gp.ghostBoss.getHp() - gp.player.getAttack();
        gp.ghostBoss.setHp(ghostBossHp);

        if (gp.ghostBoss.getHp() <= 0) {

            gp.player.setExp(gp.player.getExp() + gp.ghostBoss.getExpDrop());

            gp.ghostBoss.setHp(gp.ghostBoss.maxHp);
            gp.gameState = gp.playState;
        } else {
            int playerHp = gp.player.getHp() - gp.ghostBoss.getSpecialAttack();
            gp.player.setHp(playerHp);

            gp.ui.battleMessage = "The Ghost Boss attacks back and does " + gp.ghostBoss.getSpecialAttack() + " damage.";
            gp.ui.messageActive = true;
        }
    }


    public void handleSpecialAttack() {
        int ghostBossHp = gp.ghostBoss.getHp() - gp.player.getSpecialAttack();
        gp.ghostBoss.setHp(ghostBossHp);

        if (gp.ghostBoss.getHp() <= 0) {

            gp.player.setExp(gp.player.getExp() + gp.ghostBoss.getExpDrop());

            gp.ghostBoss.setHp(gp.ghostBoss.maxHp);
            gp.gameState = gp.playState;
        } else {
            int playerHp = gp.player.getHp() - gp.ghostBoss.getSpecialAttack();
            gp.player.setHp(playerHp);

            gp.ui.battleMessage = "The Ghost Boss attacks back and does " + gp.ghostBoss.getSpecialAttack() + " damage.";
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

