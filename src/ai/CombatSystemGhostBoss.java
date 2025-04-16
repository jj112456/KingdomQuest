package ai;

import main.GamePanel;

public class CombatSystemGhostBoss {

    GamePanel gp;

    public CombatSystemGhostBoss(GamePanel gp) {
        this.gp = gp;
    }

    public void handleAttack() {
        if (gp.ghostBoss.getHp() >= 0) {
            int ghostBossHp = gp.ghostBoss.getHp() - gp.player.getAttack();
            gp.ghostBoss.setHp(ghostBossHp);

            int playerHp = gp.player.getHp() - gp.ghostBoss.getSpecialAttack();
            gp.player.setHp(playerHp);

            gp.ui.battleMessage = "The player attacks and does " + gp.player.getAttack() + " damage";
            gp.ui.messageActive = true;

            gp.ui.battleMessage = "The ghostBoss attacks back and does " + gp.ghostBoss.getSpecialAttack() + " damage";
        } else {
            //gp.ui.battleMessage = "Enemy Defeated! EXP Gained: " + gp.ghostBoss.getExpDrop();
            //gp.ui.messageActive = true;

            gp.ghostBoss.setHp(50);
            gp.player.setExp(gp.player.getExp() + gp.ghostBoss.getExpDrop());

            gp.gameState = gp.playState;
        }
    }

    public void handleSpecialAttack() {
        if (gp.ghostBoss.getHp() >= 0) {
            int ghostBossHp = gp.ghostBoss.getHp() - gp.player.getSpecialAttack();
            gp.ghostBoss.setHp(ghostBossHp);

            int playerHp = gp.player.getHp() - gp.ghostBoss.getSpecialAttack();
            gp.player.setHp(playerHp);

            gp.ui.battleMessage = "The player uses a special attack and does " + gp.player.getSpecialAttack() + " damage";
            gp.ui.messageActive = true;

            gp.ui.battleMessage = "The ghostBoss attacks back and does " + gp.ghostBoss.getSpecialAttack() + " damage";
        } else {
            //gp.ui.battleMessage = "Enemy Defeated! EXP Gained: " + gp.ghostBoss.getExpDrop();
        	//gp.ui.messageActive = true;
        	
            gp.ghostBoss.setHp(50);
            gp.player.setExp(gp.player.getExp() + gp.ghostBoss.getExpDrop());

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

