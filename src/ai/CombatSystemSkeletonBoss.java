package ai;

import main.GamePanel;

public class CombatSystemSkeletonBoss {

    GamePanel gp;

    public CombatSystemSkeletonBoss(GamePanel gp) {
        this.gp = gp;
    }

    public void handleAttack() {
        if (gp.skeletonBoss.getHp() >= 0) {
            int skeletonBossHp = gp.skeletonBoss.getHp() - gp.player.getAttack();
            gp.skeletonBoss.setHp(skeletonBossHp);

            int playerHp = gp.player.getHp() - gp.skeletonBoss.getSpecialAttack();
            gp.player.setHp(playerHp);

            gp.ui.battleMessage = "The player attacks and does " + gp.player.getAttack() + " damage";
            gp.ui.messageActive = true;

            gp.ui.battleMessage = "The skeletonBoss attacks back and does " + gp.skeletonBoss.getSpecialAttack() + " damage";
        } else {
            //gp.ui.battleMessage = "Enemy Defeated! EXP Gained: " + gp.skeletonBoss.getExpDrop();
            //gp.ui.messageActive = true;

            gp.skeletonBoss.setHp(50);
            gp.player.setExp(gp.player.getExp() + gp.skeletonBoss.getExpDrop());

            gp.gameState = gp.playState;
        }
    }

    public void handleSpecialAttack() {
        if (gp.skeletonBoss.getHp() >= 0) {
            int skeletonBossHp = gp.skeletonBoss.getHp() - gp.player.getSpecialAttack();
            gp.skeletonBoss.setHp(skeletonBossHp);

            int playerHp = gp.player.getHp() - gp.skeletonBoss.getSpecialAttack();
            gp.player.setHp(playerHp);

            gp.ui.battleMessage = "The player uses a special attack and does " + gp.player.getSpecialAttack() + " damage";
            gp.ui.messageActive = true;

            gp.ui.battleMessage = "The skeletonBoss attacks back and does " + gp.skeletonBoss.getSpecialAttack() + " damage";
        } else {
            //gp.ui.battleMessage = "Enemy Defeated! EXP Gained: " + gp.skeletonBoss.getExpDrop();
        	//gp.ui.messageActive = true;
        	
            gp.skeletonBoss.setHp(50);
            gp.player.setExp(gp.player.getExp() + gp.skeletonBoss.getExpDrop());

            gp.gameState = gp.playState;
        }
    }

    public void handleUltimate() {
        System.out.println("Not ready!");
    }

    public void handleItem() {
        System.out.println("No Items!");
    }
}

