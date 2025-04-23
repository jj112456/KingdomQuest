package ai;

import main.GamePanel;

public class CombatSystemSlime {

    GamePanel gp;

    public CombatSystemSlime(GamePanel gp) {
        this.gp = gp;
    }

    public void handleAttack() {
        if (gp.slime.getHp() >= 0) {
            int slimeHp = gp.slime.getHp() - gp.player.getAttack();
            gp.slime.setHp(slimeHp);

            int playerHp = gp.player.getHp() - gp.slime.getAttack();
            gp.player.setHp(playerHp);

            gp.ui.battleMessage = "The player attacks and does " + gp.player.getAttack() + " damage";
            gp.ui.messageActive = true;

            gp.ui.battleMessage = "The slime attacks back and does " + gp.slime.getAttack() + " damage";
        } else {
            //gp.ui.battleMessage = "Enemy Defeated! EXP Gained: " + gp.slime.getExpDrop();
            //gp.ui.messageActive = true;

            gp.slime.setHp(50);
            gp.player.setExp(gp.player.getExp() + gp.slime.getExpDrop());

            gp.gameState = gp.playState;
        }
    }

    public void handleSpecialAttack() {
        if (gp.slime.getHp() >= 0) {
            int slimeHp = gp.slime.getHp() - gp.player.getSpecialAttack();
            gp.slime.setHp(slimeHp);

            int playerHp = gp.player.getHp() - gp.slime.getAttack();
            gp.player.setHp(playerHp);

            gp.ui.battleMessage = "The player uses a special attack and does " + gp.player.getSpecialAttack() + " damage";
            gp.ui.messageActive = true;

            gp.ui.battleMessage = "The slime attacks back and does " + gp.slime.getAttack() + " damage";
        } else {
            //gp.ui.battleMessage = "Enemy Defeated! EXP Gained: " + gp.slime.getExpDrop();
        	//gp.ui.messageActive = true;
        	
            gp.slime.setHp(50);
            gp.player.setExp(gp.player.getExp() + gp.slime.getExpDrop());

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

