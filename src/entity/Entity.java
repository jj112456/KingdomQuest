package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Entity {
	
	
	public int worldX, worldY;
	public int speed;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle solidArea;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	
	
	// CHARACTER STATS
	public int type; // 0 = player, 1 = enemy
	public String name;
	
	public int lvl;
	public int hp;
	public int attack;
	public int specialAttack;
	public int defense;
	public int specialDefense;
	public int attackSpeed;
	public int luck;
	
	public int exp;
	public int nextLevelExp;
	public int currency;
	
	public Entity currentWeapon;
	public Entity currentArmor;
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLvl() {
		return lvl;
	}
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getSpecialAttack() {
		return specialAttack;
	}
	public void setSpecialAttack(int specialAttack) {
		this.specialAttack = specialAttack;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	public int getSpecialDefense() {
		return specialDefense;
	}
	public void setSpecialDefense(int specialDefense) {
		this.specialDefense = specialDefense;
	}
	public int getAttackSpeed() {
		return attackSpeed;
	}
	public void setAttackSpeed(int attackSpeed) {
		this.attackSpeed = attackSpeed;
	}
	public int getLuck() {
		return luck;
	}
	public void setLuck(int luck) {
		this.luck = luck;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getNextLevelExp() {
		return nextLevelExp;
	}
	public void setNextLevelExp(int nextLevelExp) {
		this.nextLevelExp = nextLevelExp;
	}
	public int getCurrency() {
		return currency;
	}
	public void setCurrency(int currency) {
		this.currency = currency;
	}
	public Entity getCurrentWeapon() {
		return currentWeapon;
	}
	public void setCurrentWeapon(Entity currentWeapon) {
		this.currentWeapon = currentWeapon;
	}
	public Entity getCurrentArmor() {
		return currentArmor;
	}
	public void setCurrentArmor(Entity currentArmor) {
		this.currentArmor = currentArmor;
	}
	
	
	
	
}
