package ai;

import main.GamePanel;

public class CombatSystemZombie {

    GamePanel gp;

    public CombatSystemZombie(GamePanel gp) {
        this.gp = gp;
    }

    public void handleAttack() {
        if (gp.zombie.getHp() >= 0) {
            int zombieHp = gp.zombie.getHp() - gp.player.getAttack();
            gp.zombie.setHp(zombieHp);

            int playerHp = gp.player.getHp() - gp.zombie.getAttack();
            gp.player.setHp(playerHp);

            gp.ui.battleMessage = "The player attacks and does " + gp.player.getAttack() + " damage";
            gp.ui.messageActive = true;

            gp.ui.battleMessage = "The zombie attacks back and does " + gp.zombie.getAttack() + " damage";
        } else {
            //gp.ui.battleMessage = "Enemy Defeated! EXP Gained: " + gp.zombie.getExpDrop();
            //gp.ui.messageActive = true;

            gp.zombie.setHp(50);
            gp.player.setExp(gp.player.getExp() + gp.zombie.getExpDrop());

            gp.gameState = gp.playState;
        }
    }

    public void handleSpecialAttack() {
        if (gp.zombie.getHp() >= 0) {
            int zombieHp = gp.zombie.getHp() - gp.player.getSpecialAttack();
            gp.zombie.setHp(zombieHp);

            int playerHp = gp.player.getHp() - gp.zombie.getAttack();
            gp.player.setHp(playerHp);

            gp.ui.battleMessage = "The player uses a special attack and does " + gp.player.getSpecialAttack() + " damage";
            gp.ui.messageActive = true;

            gp.ui.battleMessage = "The zombie attacks back and does " + gp.zombie.getAttack() + " damage";
        } else {
            //gp.ui.battleMessage = "Enemy Defeated! EXP Gained: " + gp.zombie.getExpDrop();
        	//gp.ui.messageActive = true;
        	
            gp.zombie.setHp(50);
            gp.player.setExp(gp.player.getExp() + gp.zombie.getExpDrop());

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

