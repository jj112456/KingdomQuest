package ai;

import main.GamePanel;

public class CombatSystemGhost {

    GamePanel gp;

    public CombatSystemGhost(GamePanel gp) {
        this.gp = gp;
    }

    public void handleAttack() {
        if (gp.ghost.getHp() >= 0) {
            int ghostHp = gp.ghost.getHp() - gp.player.getAttack();
            gp.ghost.setHp(ghostHp);

            int playerHp = gp.player.getHp() - gp.ghost.getSpecialAttack();
            gp.player.setHp(playerHp);

            gp.ui.battleMessage = "The player attacks and does " + gp.player.getAttack() + " damage";
            gp.ui.messageActive = true;

            gp.ui.battleMessage = "The ghost attacks back and does " + gp.ghost.getSpecialAttack() + " damage";
        } else {
            //gp.ui.battleMessage = "Enemy Defeated! EXP Gained: " + gp.ghost.getExpDrop();
            //gp.ui.messageActive = true;

            gp.ghost.setHp(50);
            gp.player.setExp(gp.player.getExp() + gp.ghost.getExpDrop());

            gp.gameState = gp.playState;
        }
    }

    public void handleSpecialAttack() {
        if (gp.ghost.getHp() >= 0) {
            int ghostHp = gp.ghost.getHp() - gp.player.getSpecialAttack();
            gp.ghost.setHp(ghostHp);

            int playerHp = gp.player.getHp() - gp.ghost.getSpecialAttack();
            gp.player.setHp(playerHp);

            gp.ui.battleMessage = "The player uses a special attack and does " + gp.player.getSpecialAttack() + " damage";
            gp.ui.messageActive = true;

            gp.ui.battleMessage = "The ghost attacks back and does " + gp.ghost.getSpecialAttack() + " damage";
        } else {
            //gp.ui.battleMessage = "Enemy Defeated! EXP Gained: " + gp.ghost.getExpDrop();
        	//gp.ui.messageActive = true;
        	
            gp.ghost.setHp(50);
            gp.player.setExp(gp.player.getExp() + gp.ghost.getExpDrop());

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

