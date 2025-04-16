package ai;

import main.GamePanel;

public class CombatSystemSkeleton {

    GamePanel gp;

    public CombatSystemSkeleton(GamePanel gp) {
        this.gp = gp;
    }

    public void handleAttack() {
        if (gp.skeleton.getHp() >= 0) {
            int skeletonHp = gp.skeleton.getHp() - gp.player.getAttack();
            gp.skeleton.setHp(skeletonHp);

            int playerHp = gp.player.getHp() - gp.skeleton.getSpecialAttack();
            gp.player.setHp(playerHp);

            gp.ui.battleMessage = "The player attacks and does " + gp.player.getAttack() + " damage";
            gp.ui.messageActive = true;

            gp.ui.battleMessage = "The skeleton attacks back and does " + gp.skeleton.getSpecialAttack() + " damage";
        } else {
            //gp.ui.battleMessage = "Enemy Defeated! EXP Gained: " + gp.skeleton.getExpDrop();
            //gp.ui.messageActive = true;

            gp.skeleton.setHp(50);
            gp.player.setExp(gp.player.getExp() + gp.skeleton.getExpDrop());

            gp.gameState = gp.playState;
        }
    }

    public void handleSpecialAttack() {
        if (gp.skeleton.getHp() >= 0) {
            int skeletonHp = gp.skeleton.getHp() - gp.player.getSpecialAttack();
            gp.skeleton.setHp(skeletonHp);

            int playerHp = gp.player.getHp() - gp.skeleton.getSpecialAttack();
            gp.player.setHp(playerHp);

            gp.ui.battleMessage = "The player uses a special attack and does " + gp.player.getSpecialAttack() + " damage";
            gp.ui.messageActive = true;

            gp.ui.battleMessage = "The skeleton attacks back and does " + gp.skeleton.getSpecialAttack() + " damage";
        } else {
            //gp.ui.battleMessage = "Enemy Defeated! EXP Gained: " + gp.skeleton.getExpDrop();
        	//gp.ui.messageActive = true;
        	
            gp.skeleton.setHp(50);
            gp.player.setExp(gp.player.getExp() + gp.skeleton.getExpDrop());

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

