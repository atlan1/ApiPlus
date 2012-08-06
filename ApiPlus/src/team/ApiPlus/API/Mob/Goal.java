package team.ApiPlus.API.Mob;

import org.bukkit.entity.HumanEntity;

import net.minecraft.server.Entity;
import net.minecraft.server.EntityAnimal;
import net.minecraft.server.EntityCreature;
import net.minecraft.server.EntityCreeper;
import net.minecraft.server.EntityIronGolem;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.EntityOcelot;
import net.minecraft.server.EntityTameableAnimal;
import net.minecraft.server.EntityVillager;
import net.minecraft.server.EntityWolf;
import net.minecraft.server.PathfinderGoal;
import net.minecraft.server.PathfinderGoalArrowAttack;
import net.minecraft.server.PathfinderGoalAvoidPlayer;
import net.minecraft.server.PathfinderGoalBeg;
import net.minecraft.server.PathfinderGoalBreakDoor;
import net.minecraft.server.PathfinderGoalBreed;
import net.minecraft.server.PathfinderGoalDefendVillage;
import net.minecraft.server.PathfinderGoalEatTile;
import net.minecraft.server.PathfinderGoalFleeSun;
import net.minecraft.server.PathfinderGoalFloat;
import net.minecraft.server.PathfinderGoalFollowOwner;
import net.minecraft.server.PathfinderGoalFollowParent;
import net.minecraft.server.PathfinderGoalHurtByTarget;
import net.minecraft.server.PathfinderGoalInteract;
import net.minecraft.server.PathfinderGoalJumpOnBlock;
import net.minecraft.server.PathfinderGoalLeapAtTarget;
import net.minecraft.server.PathfinderGoalLookAtPlayer;
import net.minecraft.server.PathfinderGoalMeleeAttack;
import net.minecraft.server.PathfinderGoalMoveIndoors;
import net.minecraft.server.PathfinderGoalMoveThroughVillage;
import net.minecraft.server.PathfinderGoalMoveTowardsRestriction;
import net.minecraft.server.PathfinderGoalMoveTowardsTarget;
import net.minecraft.server.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.PathfinderGoalOfferFlower;
import net.minecraft.server.PathfinderGoalOpenDoor;
import net.minecraft.server.PathfinderGoalOwnerHurtByTarget;
import net.minecraft.server.PathfinderGoalPanic;
import net.minecraft.server.PathfinderGoalRandomLookaround;
import net.minecraft.server.PathfinderGoalRandomStroll;
import net.minecraft.server.PathfinderGoalRandomTargetNonTamed;
import net.minecraft.server.PathfinderGoalRestrictOpenDoor;
import net.minecraft.server.PathfinderGoalRestrictSun;
import net.minecraft.server.PathfinderGoalSit;
import net.minecraft.server.PathfinderGoalSwell;
import net.minecraft.server.PathfinderGoalTakeFlower;
import net.minecraft.server.PathfinderGoalTempt;

/**
 * Enum for Minecraft Mob AI
 * @author SirTyler
 * @version 1.0
 */
public enum Goal {
	ArrowAttack(0),AvoidPlayer(1),Beg(2),BreakDoor(3),Breed(4),DefendVillage(5),
	DoorInteract(6),EatTile(7),FleeSun(8),Float(9),FollowOwner(10),FollowParent(11),
	HurtByTarget(12),Interact(13),JumpOnBlock(14),LeapAtTarget(15),LookAtPlayer(16),MakeLove(17),
	MeleeAttack(18),MoveIndoors(19),MoveThroughVillage(20),MoveTowardsRestriction(21),
	MoveTowardsTarget(22),NearestAttackableTarget(23),OfferFlower(24),OpenDoor(25),
	OwnerHurtByTarget(26),OwnerHurtTarget(27),OzelotAttack(28),Panic(29),Play(30),
	RandomLookAround(31),RandomStroll(32),RandomTargetNonTamed(33),RestrictOpenDoor(34),
	RestrictSun(35),Sit(36),Swell(37),TakeFlower(38),Target(39),Tempt(40);
	
	private PathfinderGoal use;
	private Entity ent;
	@SuppressWarnings("rawtypes")
	private Class c=HumanEntity.class;
	private float b = 23.0F;
	private float range = 16.0F;
	@SuppressWarnings("unused")
	private int i=1;
	private int index = 0;
	public int goalID;
	
	private Goal(int i) {
		goalID = i;
	}
	
	/**
	 * Method used for setting Entity used by PathfinderGoal creation.
	 * @param ent Entity to be set.
	 * @return Goal
	 */
	public Goal setEntity(Entity ent) {
		this.ent = ent;
		return this;
	}
	
	/**
	 * Method used for setting int used by PathfinderGoal creation.
	 * @param i int to be set.
	 * @return Goal
	 */
	public Goal setInt(int i) {
		this.i = i;
		return this;
	}
	
	/**
	 * Method used for setting float used by PathfinderGoal creation.
	 * @param f float to be set.
	 * @return Goal
	 */
	public Goal setFloat(float f) {
		this.b = f;
		return this;
	}
	
	/**
	 * Method used for setting Mobs visual range, used in creation of Zombie Entity.
	 * @param f float to be set.
	 * @return Goal
	 */
	public Goal setRange(float f) {
		this.range = f;
		return this;
	}
	
	/**
	 * Method used for setting Class used by PathfinderGoal creation.
	 * @param c Class to be set.
	 * @return Goal
	 */
	public Goal setClass(Class<?> c) {
		this.c = c;
		return this;
	}
	
	/**
	 * Method used for setting Index used by PathfinderGoal creation.
	 * @param i int to be set.
	 * @return Goal
	 */
	public Goal setIndex(int i) {
		index = i;
		return this;
	}
	
	/**
	 * Method used for getting Index.
	 * @return int Index.
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Method used for getting the PathfinderGoal created.
	 * @return PathfinderGoal
	 */
	public PathfinderGoal getUse() {
		return use;
	}
	
	/**
	 * Method used for setting the PathfinderGoal.
	 * @param use PathfinderGoal 
	 * @return Goal
	 */
	public Goal setUse(PathfinderGoal use) {
		this.use = use;
		return this;
	}
	
	/**
	 * Method used for building the PathfinderGoal object based on GoalID.
	 * @param i GoalID to use.
	 */
	public void build(int i) {
		try {
            if(i==0)setUse(new PathfinderGoalArrowAttack((EntityLiving)ent, b, 1, 60));
			if(i==1)setUse(new PathfinderGoalAvoidPlayer((EntityCreature)ent,c,16.0F,b,0.4F));
			if(i==2)setUse(new PathfinderGoalBeg((EntityWolf)ent,b));
			if(i==3)setUse(new PathfinderGoalBreakDoor((EntityLiving)ent));
			if(i==4)setUse(new PathfinderGoalBreed((EntityAnimal)ent,b));
			if(i==5)setUse(new PathfinderGoalDefendVillage((EntityIronGolem)ent));
			if(i==6)setUse(null);
			if(i==7)setUse(new PathfinderGoalEatTile((EntityLiving)ent));
			if(i==8)setUse(new PathfinderGoalFleeSun((EntityCreature)ent, b));
			if(i==9)setUse(new PathfinderGoalFloat((EntityLiving)ent));
			if(i==10)setUse(new PathfinderGoalFollowOwner((EntityTameableAnimal)ent, b, 10.0F, 2.0F));
			if(i==11)setUse(new PathfinderGoalFollowParent((EntityAnimal)ent, b));
			if(i==12)setUse(new PathfinderGoalHurtByTarget((EntityLiving)ent, false));
			if(i==13)setUse(new PathfinderGoalInteract((EntityLiving)ent, c, b));
			if(i==14)setUse(new PathfinderGoalJumpOnBlock((EntityOcelot)ent, b));
			if(i==15)setUse(new PathfinderGoalLeapAtTarget((EntityLiving)ent, b));
			if(i==16)setUse(new PathfinderGoalLookAtPlayer((EntityLiving)ent, c, range));
			if(i==17)setUse(null);
			if(i==18)setUse(new PathfinderGoalMeleeAttack((EntityLiving)ent, c, b, false));
			if(i==19)setUse(new PathfinderGoalMoveIndoors((EntityCreature)ent));
			if(i==20)setUse(new PathfinderGoalMoveThroughVillage((EntityCreature)ent, b, false));
			if(i==21)setUse(new PathfinderGoalMoveTowardsRestriction((EntityCreature)ent, b));
			if(i==22)setUse(new PathfinderGoalMoveTowardsTarget((EntityCreature)ent, b, b));
			if(i==23)setUse(new PathfinderGoalNearestAttackableTarget((EntityCreature)ent, c, range, 0, false));
			if(i==24)setUse(new PathfinderGoalOfferFlower((EntityIronGolem)ent));
			if(i==25)setUse(new PathfinderGoalOpenDoor((EntityLiving)ent, false));
			if(i==26)setUse(new PathfinderGoalOwnerHurtByTarget((EntityTameableAnimal)ent));
			if(i==27)setUse(null);
			if(i==28)setUse(null);
			if(i==29)setUse(new PathfinderGoalPanic((EntityCreature)ent, b));
			if(i==30)setUse(null);
			if(i==31)setUse(new PathfinderGoalRandomLookaround((EntityLiving)ent));
			if(i==32)setUse(new PathfinderGoalRandomStroll((EntityCreature)ent, b));
			if(i==33)setUse(new PathfinderGoalRandomTargetNonTamed((EntityTameableAnimal)ent, c, b, 200, false));
			if(i==34)setUse(new PathfinderGoalRestrictOpenDoor((EntityCreature)ent));
			if(i==35)setUse(new PathfinderGoalRestrictSun((EntityCreature)ent));
			if(i==36)setUse(new PathfinderGoalSit((EntityTameableAnimal)ent));
			if(i==37)setUse(new PathfinderGoalSwell((EntityCreeper)ent));
			if(i==38)setUse(new PathfinderGoalTakeFlower((EntityVillager)ent));
			if(i==39)setUse(null);
			if(i==40)setUse(new PathfinderGoalTempt((EntityCreature)ent, b, i, false));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
