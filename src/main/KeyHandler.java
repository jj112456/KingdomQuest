package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	public boolean enterPressed;
	
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// Not using this method
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		// TITLE
		if(gp.gameState == gp.titleState) {
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 2;
				}
			}
			
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 2) {
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				if(gp.ui.commandNum == 0) {
					gp.gameState = gp.playState;
					gp.playMusic(0);
				}
				if(gp.ui.commandNum == 1) {
					// ADD WHEN SAVE
				}
				if(gp.ui.commandNum == 2) {
					System.exit(0);
				}
			}
		}
		
		
		// BATTLE STATE
		if(gp.gameState == gp.battleState) {
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 3;
				}
			}
			
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 3) {
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				if(gp.ui.commandNum == 0) {
					//attack
					if(gp.ghost.getHp()>0) {
						int var = gp.ghost.getHp() - gp.player.getAttack();
						System.out.println("Ghost HP: " + var);
						gp.ghost.setHp(var);
					}
					else {
						System.out.println("Enemy Defeated: ");
						gp.gameState = gp.playState;
					}
				}
				if(gp.ui.commandNum == 1) {
					//special attack
					if(gp.ghost.getHp()>0) {
						int var = gp.ghost.getHp() - gp.player.getSpecialAttack();
						System.out.println("Ghost HP: " + var);
						gp.ghost.setHp(var);
					}
					else {
						System.out.println("Enemy Defeated: ");
						gp.gameState = gp.playState;
					}
				}
				if(gp.ui.commandNum == 2) {
					// ultimate
					System.out.println("Not ready!");
				}
				if(gp.ui.commandNum == 3) {
					// item
					System.out.println("No Items!");
				}
			}
		}
		
		
		
		// PLAY STATE
		if (gp.gameState == gp.playState){
			
			if(code == KeyEvent.VK_W) {
				upPressed = true;
				gp.checkForEncounter(gp);
			}
			
			if(code == KeyEvent.VK_S) {
				downPressed = true;
				gp.checkForEncounter(gp);
			}
			
			if(code == KeyEvent.VK_A) {
				leftPressed = true;
				gp.checkForEncounter(gp);
			}
			
			if(code == KeyEvent.VK_D) {
				rightPressed = true;
				gp.checkForEncounter(gp);
			}
			
			if(code == KeyEvent.VK_ENTER) {
				enterPressed = true;
			}
			
			if(code == KeyEvent.VK_ESCAPE) {
				gp.gameState = gp.menuState;
			}
			
			
		}
		
		
		// MENU STATE
		else if(gp.gameState == gp.menuState) {
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 4;
				}
			}
			
			if(code == KeyEvent.VK_ESCAPE) {
				gp.gameState = gp.playState;
			}
			
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 4) {
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				if(gp.ui.commandNum == 0) {
					// item
				}
				if(gp.ui.commandNum == 1) {
					// equip
				}
				if(gp.ui.commandNum == 2) {
					// stats
				}
				if(gp.ui.commandNum == 3) {
					// settings
				}
				if(gp.ui.commandNum == 4) {
					// save
				}
			}
		}
		
		
		
		// DIALOGUE STATE
		else if(gp.gameState == gp.dialogueState) {
			if(code == KeyEvent.VK_ENTER) {
				gp.gameState = gp.playState;
			}
		}
		
		// OBJECT STATE
		else if(gp.gameState == gp.dialogueStateObject) {
			if(code == KeyEvent.VK_ENTER) {
				gp.gameState = gp.playState;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode();
				
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = false;
		}
		
	}
	
}
