package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ai.CombatSystemZombie;
import ai.CombatSystemSlime;
import ai.CombatSystemGhost;
import ai.CombatSystemSkeleton;

import ai.CombatSystemSkeletonBoss;
import ai.CombatSystemGhostBoss;


public class KeyHandler implements KeyListener{
	
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	public boolean enterPressed;
	public boolean escapePressed;
	
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// Not using this
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		// TITLE STATE---------------------------------------------------
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
					// NEW GAME
					gp.gameState = gp.playState;
					gp.playMusic(0);
				}
				if(gp.ui.commandNum == 1) {
					// LOAD GAME
					gp.saveLoad.load();
					gp.gameState = gp.playState;
					//gp.gameState = gp.dialogueStateObject;
					//gp.ui.showMessage("Save Successfully Loaded");
					gp.playMusic(0);
				}
				if(gp.ui.commandNum == 2) {
					// EXIT GAME
					System.exit(0);
				}
			}
		}
		
		
		
		
		// GAME OVER STATE------------------------------------------
		if(gp.gameState == gp.gameOverState) {
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 1;
				}
			}
			
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 1) {
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				if(gp.ui.commandNum == 0) {
					gp.gameState = gp.titleState;
					gp.stopMusic();
				}
				if(gp.ui.commandNum == 1) {
					System.exit(0);
				}
				
				
			}
		}
		
		
		
		
		// BATTLE STATE----------------------------------------------------------
		if( (gp.gameState == gp.battleStateZombie) || (gp.gameState == gp.battleStateSlime)  || 
				(gp.gameState == gp.battleStateGhost) || (gp.gameState == gp.battleStateSkeleton) || 
				(gp.gameState == gp.battleStateSkeletonBoss) || (gp.gameState == gp.battleStateGhostBoss) ) {
			
			if (gp.ui.messageActive) return;

			
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
				
			    CombatSystemZombie combatZombie = new CombatSystemZombie(gp);
			    CombatSystemSlime combatSlime = new CombatSystemSlime(gp);
			    CombatSystemGhost combatGhost = new CombatSystemGhost(gp);
			    CombatSystemSkeleton combatSkeleton = new CombatSystemSkeleton(gp);
			    
			    CombatSystemSkeletonBoss combatSkeletonBoss = new CombatSystemSkeletonBoss(gp);
			    CombatSystemGhostBoss combatGhostBoss = new CombatSystemGhostBoss(gp);
			    
			    
			    
			    if(gp.ui.commandNum == 0) {
			    	if(gp.gameState == gp.battleStateZombie) {
			    		combatZombie.handleAttack();
			    	}
			    	if(gp.gameState == gp.battleStateSlime) {
			    		combatSlime.handleAttack();
			    	}
			    	if(gp.gameState == gp.battleStateGhost) {
			    		combatGhost.handleAttack();
			    	}
			    	if(gp.gameState == gp.battleStateSkeleton) {
			    		combatSkeleton.handleAttack();
			    	}
			    	if(gp.gameState == gp.battleStateSkeletonBoss) {
			    		combatSkeletonBoss.handleAttack();
			    	}
			    	if(gp.gameState == gp.battleStateGhostBoss) {
			    		combatGhostBoss.handleAttack();
			    	}
			    }
			    if(gp.ui.commandNum == 1) {
			    	if(gp.gameState == gp.battleStateZombie) {
			    		combatZombie.handleSpecialAttack();
			    	}
			    	if(gp.gameState == gp.battleStateSlime) {
			    		combatSlime.handleSpecialAttack();
			    	}
			    	if(gp.gameState == gp.battleStateGhost) {
			    		combatGhost.handleSpecialAttack();
			    	}
			    	if(gp.gameState == gp.battleStateSkeleton) {
			    		combatSkeleton.handleSpecialAttack();
			    	}
			    	if(gp.gameState == gp.battleStateSkeletonBoss) {
			    		combatSkeletonBoss.handleSpecialAttack();
			    	}
			    	if(gp.gameState == gp.battleStateGhostBoss) {
			    		combatGhostBoss.handleSpecialAttack();
			    	}
			    } 
			    
			    if(gp.ui.commandNum == 2) {
			    	if(gp.gameState == gp.battleStateZombie) {
			    		combatZombie.handleItem();
			    	}
			    	if(gp.gameState == gp.battleStateSlime) {
			    		combatSlime.handleItem();
			    	}
			    	if(gp.gameState == gp.battleStateGhost) {
			    		combatGhost.handleItem();
			    	}
			    	if(gp.gameState == gp.battleStateSkeleton) {
			    		combatSkeleton.handleItem();
			    	}
			    	if(gp.gameState == gp.battleStateSkeletonBoss) {
			    		combatSkeletonBoss.handleItem();
			    	}
			    	if(gp.gameState == gp.battleStateGhostBoss) {
			    		combatGhostBoss.handleItem();
			    	}
			    }
			    if(gp.ui.commandNum == 3) {
			    	if(gp.gameState == gp.battleStateZombie) {
			    		combatZombie.handleRun();
			    	}
			    	if(gp.gameState == gp.battleStateSlime) {
			    		combatSlime.handleRun();
			    	}
			    	if(gp.gameState == gp.battleStateGhost) {
			    		combatGhost.handleRun();
			    	}
			    	if(gp.gameState == gp.battleStateSkeleton) {
			    		combatSkeleton.handleRun();
			    	}
			    	if(gp.gameState == gp.battleStateSkeletonBoss) {
			    		combatSkeletonBoss.handleRun();
			    	}
			    	if(gp.gameState == gp.battleStateGhostBoss) {
			    		combatGhostBoss.handleRun();
			    	}
			    } 
			    
			    // GAME OVER if Player HP Reaches 0
				if(gp.player.getHp() <= 0) {
					gp.player.setHp(gp.player.getMaxHp());
					gp.ghost.setHp(gp.ghost.getMaxHp());
					gp.gameState = gp.gameOverState;
				}
					
				// LEVEL PLAYER UP IF EXP REQUIREMENT IS MET
				if(gp.player.getExp() >= gp.player.getNextLevelExp()) {
					
					gp.player.levelUpPlayer();
					
				}
			    
			}

				
		}
		
		// PLAY STATE----------------------------------------------------
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
				escapePressed = true;
				gp.gameState = gp.menuState;
			}
			
			
		}
		
		// HELP STATE-----------------------------------------------
		else if(gp.gameState == gp.helpState) {
			if(code == KeyEvent.VK_ENTER) {
				gp.gameState = gp.menuState;
			}
		}
		
		// MENU STATE-----------------------------------------------
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
					gp.gameState = gp.dialogueStateObject;
					gp.ui.showMessage("Keys: " + gp.player.hasKey +"   Potions: " + gp.player.getPotion());
				}
				if(gp.ui.commandNum == 1) {
					// help
					gp.gameState = gp.helpState;
				}
				if(gp.ui.commandNum == 2) {
					// stats
					gp.gameState = gp.dialogueStateObject;
					gp.ui.showMessage("ATK: " + gp.player.getAttack() + "   SP.ATK: " + gp.player.getSpecialAttack()
					+ "   DEF: " + gp.player.getDefense() + "   SP.DEF: " + gp.player.getSpecialDefense());
					
				}
				if(gp.ui.commandNum == 3) {
					// save
					// Update total time before saving
					long now = System.currentTimeMillis();
					gp.timePlayed += now - gp.sessionStartTime;
					gp.sessionStartTime = now;

					gp.saveLoad.save();
					gp.gameState = gp.dialogueStateObject;
					gp.ui.showMessage("Game Saved");
				}
				if(gp.ui.commandNum == 4) {
					// quit (Saves on exit)
					long now = System.currentTimeMillis();
					gp.timePlayed += now - gp.sessionStartTime;
					gp.sessionStartTime = now;

					gp.saveLoad.save();
					System.exit(0);
				}
			}
		}
		
		// DIALOGUE STATE-------------------------------------
		else if(gp.gameState == gp.dialogueState) {
			if(code == KeyEvent.VK_ENTER) {
				gp.gameState = gp.playState;
			}
		}
		
		// OBJECT STATE----------------------------------------------
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
