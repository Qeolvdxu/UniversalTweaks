package mod.acgaming.universaltweaks.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.cleanroommc.configanytime.ConfigAnytime;
import mod.acgaming.universaltweaks.UniversalTweaks;
import mod.acgaming.universaltweaks.core.UTLoadingPlugin;
import mod.acgaming.universaltweaks.tweaks.blocks.breakablebedrock.UTBreakableBedrock;
import mod.acgaming.universaltweaks.tweaks.items.parry.UTParry;
import mod.acgaming.universaltweaks.tweaks.items.rarity.UTCustomRarity;
import mod.acgaming.universaltweaks.tweaks.items.useduration.UTCustomUseDuration;
import mod.acgaming.universaltweaks.tweaks.misc.armorcurve.UTArmorCurve;
import mod.acgaming.universaltweaks.tweaks.misc.incurablepotions.UTIncurablePotions;
import mod.acgaming.universaltweaks.tweaks.misc.loadsound.UTLoadSound;
import mod.acgaming.universaltweaks.tweaks.misc.swingthroughgrass.UTSwingThroughGrassLists;
import mod.acgaming.universaltweaks.tweaks.performance.autosave.UTAutoSaveOFCompat;

@Config(modid = UniversalTweaks.MODID, name = UniversalTweaks.NAME + " - Tweaks")
public class UTConfigTweaks
{
    @Config.LangKey("cfg.universaltweaks.config.blocks")
    @Config.Name("Blocks")
    public static final BlocksCategory BLOCKS = new BlocksCategory();

    @Config.LangKey("cfg.universaltweaks.config.entities")
    @Config.Name("Entities")
    public static final EntitiesCategory ENTITIES = new EntitiesCategory();

    @Config.LangKey("cfg.universaltweaks.config.items")
    @Config.Name("Items")
    public static final ItemsCategory ITEMS = new ItemsCategory();

    @Config.LangKey("cfg.universaltweaks.config.misc")
    @Config.Name("Misc")
    public static final MiscCategory MISC = new MiscCategory();

    @Config.LangKey("cfg.universaltweaks.config.performance")
    @Config.Name("Performance")
    public static final PerformanceCategory PERFORMANCE = new PerformanceCategory();

    @Config.LangKey("cfg.universaltweaks.config.world")
    @Config.Name("World")
    public static final WorldCategory WORLD = new WorldCategory();

    public enum EnumLists
    {
        WHITELIST,
        BLACKLIST
    }

    public enum EnumDifficulty
    {
        PEACEFUL,
        EASY,
        NORMAL,
        HARD
    }

    public static class BlocksCategory
    {
        @Config.LangKey("cfg.universaltweaks.tweaks.blocks.betterplacement")
        @Config.Name("Better Placement")
        public final BetterPlacementCategory BETTER_PLACEMENT = new BetterPlacementCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.blocks.blockdispenser")
        @Config.Name("Block Dispenser")
        public final BlockDispenserCategory BLOCK_DISPENSER = new BlockDispenserCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.blocks.breakablebedrock")
        @Config.Name("Breakable Bedrock")
        public final BreakableBedrockCategory BREAKABLE_BEDROCK = new BreakableBedrockCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.blocks.finitewater")
        @Config.Name("Finite Water")
        public final FiniteWaterCategory FINITE_WATER = new FiniteWaterCategory();

        @Config.RequiresMcRestart
        @Config.Name("Bed Obstruction Replacement")
        @Config.Comment("Replaces bed obstruction checks with an improved version")
        public boolean utBedObstructionToggle = true;

        @Config.Name("Better Harvest")
        @Config.Comment("Prevents breaking lower parts of sugar cane and cacti as well as unripe crops, unless sneaking")
        public boolean utBetterHarvestToggle = false;

        @Config.RequiresMcRestart
        @Config.Name("Block Hit Delay")
        @Config.Comment("Sets the delay in ticks between breaking blocks")
        public int utBlockHitDelay = 5;

        @Config.RequiresMcRestart
        @Config.Name("Cactus Size")
        @Config.Comment("Determines how tall cacti can grow")
        public int utCactusSize = 3;

        @Config.RequiresMcRestart
        @Config.Name("Fast Leaf Decay")
        @Config.Comment("Makes leaves decay faster when trees are chopped")
        public boolean utLeafDecayToggle = true;

        @Config.RequiresMcRestart
        @Config.Name("Fence/Wall Jump")
        @Config.Comment("Allows the player to jump over fences and walls")
        public boolean utFenceWallJumpToggle = true;

        @Config.RequiresMcRestart
        @Config.Name("Lenient Paths")
        @Config.Comment("Allows the creation of grass paths everywhere (beneath fence gates, trapdoors, ...)")
        public boolean utLenientPathsToggle = true;

        @Config.RequiresMcRestart
        @Config.Name("Sugar Cane Size")
        @Config.Comment("Determines how tall sugar cane can grow")
        public int utSugarCaneSize = 3;

        @Config.RequiresMcRestart
        @Config.Name("Vine Size")
        @Config.Comment
            ({
                "Determines how long vines can grow",
                "0 = Infinite (vanilla default)"
            })
        public int utVineSize = 0;

        public static class BetterPlacementCategory
        {
            @Config.RequiresMcRestart
            @Config.Name("[1] Better Placement Toggle")
            @Config.Comment("Removes the delay between placing blocks")
            public boolean utBetterPlacementToggle = false;

            @Config.Name("[2] Force New Location")
            @Config.Comment("If the cursor must be moved to a new location before placing another block")
            public boolean utBetterPlacementNewLoc = true;

            @Config.Name("[3] Creative Mode Only")
            @Config.Comment("Only affects block placement in creative mode")
            public boolean utBetterPlacementCreative = false;
        }

        public static class BlockDispenserCategory
        {
            @Config.RequiresMcRestart
            @Config.Name("[1] Block Dispenser Toggle")
            @Config.Comment("Allows dispensers to place blocks")
            public boolean utBlockDispenserToggle = true;

            @Config.Name("[2] Block List")
            @Config.Comment
                ({
                    "List of blocks concerning dispensing",
                    "Behavior depends on the list mode",
                    "Syntax: modid:block"
                })
            public String[] utBlockDispenserBlockList = new String[]
                {
                    "minecraft:water",
                    "minecraft:flowing_water",
                    "minecraft:lava",
                    "minecraft:flowing_lava",
                    "minecraft:fire",
                    "minecraft:web",
                    "botania:specialflower",
                    "thermalexpansion:strongbox"
                };

            @Config.Name("[3] List Mode")
            @Config.Comment
                ({
                    "Blacklist Mode: Blocks which can't be placed, others can",
                    "Whitelist Mode: Blocks which can be placed, others can't"
                })
            public EnumLists utBlockDispenserBlockListMode = EnumLists.BLACKLIST;
        }

        public static class BreakableBedrockCategory
        {
            @Config.RequiresMcRestart
            @Config.Name("[1] Breakable Bedrock Toggle")
            @Config.Comment("Allows customizable mining of bedrock")
            public boolean utBreakableBedrockToggle = false;

            @Config.Name("[2] Tool List")
            @Config.Comment
                ({
                    "List of tools concerning mining bedrock",
                    "Behavior depends on the list mode",
                    "Syntax: modid:tool"
                })
            public String[] utBreakableBedrockToolList = new String[] {};

            @Config.Name("[3] List Mode")
            @Config.Comment
                ({
                    "Blacklist Mode: Tools which can't mine bedrock, others can",
                    "Whitelist Mode: Tools which can mine bedrock, others can't"
                })
            public EnumLists utBreakableBedrockToolListMode = EnumLists.BLACKLIST;
        }

        public static class FiniteWaterCategory
        {
            @Config.RequiresMcRestart
            @Config.Name("[1] Finite Water Toggle")
            @Config.Comment("Prevents creation of infinite water sources")
            public boolean utFiniteWaterToggle = false;

            @Config.Name("[2] Allow Water Biomes")
            @Config.Comment("Allows creation of infinite water sources in ocean and river biomes")
            public boolean utFiniteWaterWaterBiomes = true;

            @Config.Name("[3] Minimum Altitude")
            @Config.Comment("Inclusive minimum altitude at which water is infinite")
            public int utFiniteWaterInfMin = 0;

            @Config.Name("[4] Maximum Altitude")
            @Config.Comment("Inclusive maximum altitude at which water is infinite")
            public int utFiniteWaterInfMax = 63;
        }
    }

    public static class EntitiesCategory
    {
        @Config.LangKey("cfg.universaltweaks.tweaks.entities.attributes")
        @Config.Name("Attributes")
        public final AttributesCategory ATTRIBUTES = new AttributesCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.entities.betterburning")
        @Config.Name("Better Burning")
        public final BetterBurningCategory BETTER_BURNING = new BetterBurningCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.entities.collisiondamage")
        @Config.Name("Collision Damage")
        public final CollisionDamageCategory COLLISION_DAMAGE = new CollisionDamageCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.entities.damagevelocity")
        @Config.Name("Damage Velocity")
        public final DamageVelocityCategory DAMAGE_VELOCITY = new DamageVelocityCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.entities.easybreeding")
        @Config.Name("Easy Breeding")
        public final EasyBreedingCategory EASY_BREEDING = new EasyBreedingCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.entities.nogolems")
        @Config.Name("No Golems")
        public final NoGolemsCategory NO_GOLEMS = new NoGolemsCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.entities.playerspeed")
        @Config.Name("Player Speed")
        public final PlayerSpeedCategory PLAYER_SPEED = new PlayerSpeedCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.entities.rallyhealth")
        @Config.Name("Rally Health")
        public final RallyHealthCategory RALLY_HEALTH = new RallyHealthCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.entities.sleeping")
        @Config.Name("Sleeping")
        public final SleepingCategory SLEEPING = new SleepingCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.entities.spawncaps")
        @Config.Name("Spawn Caps")
        public final SpawnCapsCategory SPAWN_CAPS = new SpawnCapsCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.entities.undeadhorses")
        @Config.Name("Undead Horses")
        public final UndeadHorsesCategory UNDEAD_HORSES = new UndeadHorsesCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.entities.waterfalldamage")
        @Config.Name("Water Fall Damage")
        public final WaterFallDamageCategory WATER_FALL_DAMAGE = new WaterFallDamageCategory();

        @Config.RequiresMcRestart
        @Config.Name("AI Replacement")
        @Config.Comment("Replaces entity AI for improved server performance")
        public boolean utAIReplacementToggle = true;

        @Config.Name("AI Removal")
        @Config.Comment("Removes entity AI for improved server performance")
        public boolean utAIRemovalToggle = false;

        @Config.RequiresMcRestart
        @Config.Name("Auto Jump Replacement")
        @Config.Comment("Replaces auto jump with an increased step height (singleplayer only)")
        public boolean utAutoJumpToggle = true;

        @Config.Name("Better Ignition")
        @Config.Comment("Enables ignition of entities by right-clicking instead of awkwardly lighting the block under them")
        public boolean utBetterIgnitionToggle = true;

        @Config.RequiresMcRestart
        @Config.Name("Boat Speed")
        @Config.Comment("Sets the acceleration value for controlling boats")
        public double utBoatSpeed = 0.04D;

        @Config.RequiresMcRestart
        @Config.Name("Burning Baby Zombies")
        @Config.Comment("Lets baby zombies burn in daylight as in Minecraft 1.13+")
        public boolean utBurningBabyZombiesToggle = true;

        @Config.Name("Creeper Charged Spawning Chance")
        @Config.Comment("Sets the chance for creepers to spawn charged")
        @Config.RangeDouble(min = 0.0D, max = 1.0D)
        public double utCreeperChargedChance = 0.0D;

        @Config.RequiresMcRestart
        @Config.Name("Creeper Confetti Spawning Chance")
        @Config.Comment("Sets the chance to replace deadly creeper explosions with delightful confetti")
        @Config.RangeDouble(min = 0.0D, max = 1.0D)
        public double utCreeperConfettiChance = 0.0D;

        @Config.RequiresMcRestart
        @Config.Name("Critical Arrow Damage")
        @Config.Comment
            ({
                "Sets the additional damage that critical arrows deal",
                "-1 for vanilla random default"
            })
        public int utCriticalArrowDamage = -1;

        @Config.RequiresMcRestart
        @Config.Name("Disable Creeper Music Discs")
        @Config.Comment("Disables creepers dropping music discs when slain by skeletons")
        public boolean utCreeperMusicDiscsToggle = false;

        @Config.RequiresMcRestart
        @Config.Name("Disable Villager Trade Leveling")
        @Config.Comment("Disables leveling of villager careers, only allowing base level trades")
        public boolean utVillagerTradeLevelingToggle = false;

        @Config.RequiresMcRestart
        @Config.Name("Disable Wither Targeting AI")
        @Config.Comment("Disables withers targeting animals")
        public boolean utWitherAIToggle = false;

        @Config.RequiresMcRestart
        @Config.Name("Husk & Stray Spawning")
        @Config.Comment("Lets husks and strays spawn underground like regular zombies and skeletons")
        public boolean utHuskStraySpawningToggle = true;

        @Config.RequiresMcRestart
        @Config.Name("Mob Despawn Improvement")
        @Config.Comment("Mobs carrying picked up items will drop their equipment and despawn properly")
        public boolean utMobDespawnToggle = true;

        @Config.RequiresMcRestart
        @Config.Name("No Saddled Wandering")
        @Config.Comment("Stops horses wandering around when saddled")
        public boolean utSaddledWanderingToggle = true;

        @Config.Name("Rabbit Killer Spawning Chance")
        @Config.Comment("Sets the chance for rabbits to spawn as the killer bunny variant")
        @Config.RangeDouble(min = 0.0D, max = 1.0D)
        public double utRabbitKillerChance = 0.0D;

        @Config.Name("Rabbit Toast Spawning Chance")
        @Config.Comment("Sets the chance for rabbits to spawn as the Toast variant")
        @Config.RangeDouble(min = 0.0D, max = 1.0D)
        public double utRabbitToastChance = 0.0D;

        public static class AttributesCategory
        {
            @Config.RequiresMcRestart
            @Config.Name("[01] Attributes Toggle")
            @Config.Comment("Sets custom ranges for entity attributes")
            public boolean utAttributesToggle = true;

            @Config.Name("[02] Max Health Min")
            public double utAttributeMaxHealthMin = -65536;

            @Config.Name("[03] Max Health Max")
            public double utAttributeMaxHealthMax = 65536;

            @Config.Name("[04] Follow Range Min")
            public double utAttributeFollowRangeMin = -65536;

            @Config.Name("[05] Follow Range Max")
            public double utAttributeFollowRangeMax = 65536;

            @Config.Name("[06] Knockback Resistance Min")
            public double utAttributeKnockbackResistanceMin = -65536;

            @Config.Name("[07] Knockback Resistance Max")
            public double utAttributeKnockbackResistanceMax = 65536;

            @Config.Name("[08] Movement Speed Min")
            public double utAttributeMovementSpeedMin = -65536;

            @Config.Name("[09] Movement Speed Max")
            public double utAttributeMovementSpeedMax = 65536;

            @Config.Name("[10] Flying Speed Min")
            public double utAttributeFlyingSpeedMin = -65536;

            @Config.Name("[11] Flying Speed Max")
            public double utAttributeFlyingSpeedMax = 65536;

            @Config.Name("[12] Attack Damage Min")
            public double utAttributeAttackDamageMin = -65536;

            @Config.Name("[13] Attack Damage Max")
            public double utAttributeAttackDamageMax = 65536;

            @Config.Name("[14] Attack Speed Min")
            public double utAttributeAttackSpeedMin = -65536;

            @Config.Name("[15] Attack Speed Max")
            public double utAttributeAttackSpeedMax = 65536;

            @Config.Name("[16] Armor Min")
            public double utAttributeArmorMin = -65536;

            @Config.Name("[17] Armor Max")
            public double utAttributeArmorMax = 65536;

            @Config.Name("[18] Armor Toughness Min")
            public double utAttributeArmorToughnessMin = -65536;

            @Config.Name("[19] Armor Toughness Max")
            public double utAttributeArmorToughnessMax = 65536;

            @Config.Name("[20] Luck Min")
            public double utAttributeLuckMin = -65536;

            @Config.Name("[21] Luck Max")
            public double utAttributeLuckMax = 65536;
        }

        public static class BetterBurningCategory
        {
            @Config.Name("[1] Cooked Items")
            @Config.Comment("Fixes some edge cases where fire damage sources won't cause mobs to drop their cooked items")
            public boolean utBBCookedToggle = true;

            @Config.Name("[2] Extinguishing")
            @Config.Comment("If entities have fire resistance, they get extinguished right away when on fire")
            public boolean utBBExtinguishToggle = true;

            @Config.Name("[3] Fire Overlay")
            @Config.Comment("Prevents the fire animation overlay from being displayed when the player is immune to fire")
            public boolean utBBOverlayToggle = true;

            @Config.Name("[4] Flaming Arrows")
            @Config.Comment("Allows skeletons to shoot flaming arrows when on fire (30% chance * regional difficulty)")
            public boolean utBBArrowsToggle = true;

            @Config.Name("[5] Spreading Fire")
            @Config.Comment("Allows fire to spread from entity to entity (30% chance * regional difficulty)")
            public boolean utBBSpreadingToggle = true;
        }

        public static class EasyBreedingCategory
        {
            @Config.Name("[1] Easy Breeding Toggle")
            @Config.Comment("Enables easy breeding of animals by tossing food on the ground")
            public boolean utEasyBreedingToggle = false;

            @Config.Name("[2] Search Distance")
            @Config.Comment("Determines the distance for animals to search for food")
            public double utEasyBreedingDistance = 10;
        }

        public static class CollisionDamageCategory
        {
            @Config.RequiresMcRestart
            @Config.Name("[1] Collision Damage Toggle")
            @Config.Comment("Applies horizontal collision damage to the player akin to elytra collision")
            public boolean utCollisionDamageToggle = false;

            @Config.Name("[2] Damage Factor")
            @Config.Comment
                ({
                    "The damage factor that gets multiplied with the player speed",
                    "Vanilla default for elytra damage is 10"
                })
            public int utCollisionDamageFactor = 10;
        }

        public static class DamageVelocityCategory
        {
            @Config.RequiresMcRestart
            @Config.Name("[1] Damage Velocity Toggle")
            @Config.Comment("Enables the modification of damage sources that change the entity's velocity")
            public boolean utDamageVelocityToggle = false;

            @Config.Name("[2] Damage Velocity List")
            @Config.Comment("Syntax: damagetype")
            public String[] utDamageVelocityList = new String[]
                {
                    "inFire",
                    "onFire"
                };

            @Config.Name("[3] List Mode")
            @Config.Comment
                ({
                    "Blacklist Mode: Damage sources that don't change velocity, others do",
                    "Whitelist Mode: Damage sources that change velocity, others don't"
                })
            public EnumLists utDamageVelocityListMode = EnumLists.BLACKLIST;
        }

        public static class NoGolemsCategory
        {
            @Config.RequiresMcRestart
            @Config.Name("[1] Iron Golem Toggle")
            @Config.Comment("Disables the manual creation of iron golems")
            public boolean utNGIronGolemToggle = false;

            @Config.RequiresMcRestart
            @Config.Name("[2] Snow Golem Toggle")
            @Config.Comment("Disables the manual creation of snow golems")
            public boolean utNGSnowGolemToggle = false;

            @Config.RequiresMcRestart
            @Config.Name("[3] Wither Toggle")
            @Config.Comment("Disables the manual creation of withers")
            public boolean utNGWitherToggle = false;
        }

        public static class PlayerSpeedCategory
        {
            @Config.RequiresMcRestart
            @Config.Name("[1] Player Speed Toggle")
            @Config.Comment("Enables the modification of base and maximum player speeds")
            public boolean utPlayerSpeedToggle = false;

            @Config.Name("[2] Walk Speed")
            @Config.Comment("Determines the player's base walk speed")
            public double utPlayerWalkSpeed = 0.1;

            @Config.Name("[3] Fly Speed")
            @Config.Comment("Determines the player's base fly speed")
            public double utPlayerFlySpeed = 0.05;

            @Config.Name("[4] Max Speed")
            @Config.Comment
                ({
                    "Determines the player's maximum speed",
                    "Increase if you get the infamous 'Player moved too quickly' messages"
                })
            public double utPlayerMaxSpeed = 100;

            @Config.Name("[5] Max Elytra Speed")
            @Config.Comment
                ({
                    "Determines the player's maximum speed when flying with elytra",
                    "Increase if you get the infamous 'Player moved too quickly' messages"
                })
            public double utPlayerMaxElytraSpeed = 300;

            @Config.Name("[6] Max Vehicle Speed")
            @Config.Comment
                ({
                    "Determines the player's maximum speed when riding a vehicle or mount",
                    "Increase if you get the infamous 'Player moved too quickly' messages"
                })
            public double utPlayerVehicleSpeed = 100;
        }

        public static class RallyHealthCategory
        {
            @Config.Name("[1] Rally Health Toggle")
            @Config.Comment
                ({
                    "Adds Bloodborne's Rally system to Minecraft",
                    "Regain lost health when attacking back within the risk time"
                })
            public boolean utRallyHealthToggle = false;

            @Config.Name("[2] Risk Time")
            @Config.Comment("Determines the risk time in ticks")
            public int utRallyHealthRiskTime = 60;

            @Config.Name("[3] Heal Chance")
            @Config.Comment("Determines the chance to regain health in percent")
            public int utRallyHealthHealChance = 80;

            @Config.Name("[4] Indication Sound")
            @Config.Comment("Plays an indication sound effect when health is regained")
            public boolean utRallyHealthSound = false;
        }

        public static class SleepingCategory
        {
            @Config.Name("Disable Sleeping")
            @Config.Comment("Disables skipping night by using a bed while making it still able to set spawn")
            public boolean utDisableSleepingToggle = false;

            @Config.Name("Sleeping Time")
            @Config.RangeInt(min = -1, max = 23999)
            @Config.Comment
                ({
                    "Determines at which time of day sleeping is allowed in ticks (0 - 23999)",
                    "-1 for vanilla default"
                })
            public int utSleepingTime = -1;
        }

        public static class SpawnCapsCategory
        {
            @Config.RequiresMcRestart
            @Config.Name("[1] Spawn Caps Toggle")
            @Config.Comment("Sets maximum spawning limits for different entity types")
            public boolean utSpawnCapsToggle = false;

            @Config.RequiresMcRestart
            @Config.Name("[2] Monster Cap")
            @Config.Comment("Maximum amount of monsters (IMob)")
            public int utSpawnCapsMonster = 70;

            @Config.RequiresMcRestart
            @Config.Name("[3] Creature Cap")
            @Config.Comment("Maximum amount of creatures (EntityAnimal)")
            public int utSpawnCapsCreature = 10;

            @Config.RequiresMcRestart
            @Config.Name("[4] Ambient Cap")
            @Config.Comment("Maximum amount of ambients (EntityAmbientCreature)")
            public int utSpawnCapsAmbient = 15;

            @Config.RequiresMcRestart
            @Config.Name("[5] Water Creature Cap")
            @Config.Comment("Maximum amount of water creatures (EntityWaterMob)")
            public int utSpawnCapsWaterCreature = 5;
        }

        public static class UndeadHorsesCategory
        {
            @Config.RequiresMcRestart
            @Config.Name("Burning Undead Horses")
            @Config.Comment("Lets untamed undead horses burn in daylight")
            public boolean utBurningUndeadHorsesToggle = true;

            @Config.RequiresMcRestart
            @Config.Name("Taming Undead Horses")
            @Config.Comment("Allows taming of undead horses")
            public boolean utTamingUndeadHorsesToggle = true;
        }

        public static class WaterFallDamageCategory
        {
            @Config.RequiresMcRestart
            @Config.Name("[1] Water Fall Damage Toggle")
            @Config.Comment("Re-implements an improved version of pre-1.4 fall damage in water")
            public boolean utFallDamageToggle = false;

            @Config.Name("[2] Damage Reduction")
            @Config.Comment("How much fall damage gets reduced by water per tick")
            public double utFallDamageValue = 2.0D;
        }
    }

    public static class ItemsCategory
    {
        @Config.LangKey("cfg.universaltweaks.tweaks.items.attackcooldown")
        @Config.Name("Attack Cooldown")
        public final AttackCooldownCategory ATTACK_COOLDOWN = new AttackCooldownCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.items.itementities")
        @Config.Name("Item Entities")
        public final ItemEntitiesCategory ITEM_ENTITIES = new ItemEntitiesCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.items.mending")
        @Config.Name("Mending")
        public final MendingCategory MENDING = new MendingCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.items.parry")
        @Config.Name("Shield Parry")
        public final ParryCategory PARRY = new ParryCategory();

        @Config.Name("Always Eat")
        @Config.Comment("Allows the consumption of food at any time, regardless of the hunger bar")
        public boolean utAlwaysEatToggle = false;

        @Config.Name("Auto Switch Tools")
        @Config.Comment("Switches the selected hotbar slot to a proper tool if required")
        public boolean utAutoSwitchToggle = false;

        @Config.RequiresMcRestart
        @Config.Name("No Crafting Repair")
        @Config.Comment("Disables crafting recipes for repairing tools")
        public boolean utCraftingRepairToggle = false;

        @Config.RequiresMcRestart
        @Config.Name("Hardcore Buckets")
        @Config.Comment("Prevents placing of liquid source blocks in the world")
        public boolean utHardcoreBucketsToggle = false;

        @Config.RequiresMcRestart
        @Config.Name("No Leftover Breath Bottles")
        @Config.Comment("Disables dragon's breath from being a container item and leaving off empty bottles when a stack is brewed with")
        public boolean utLeftoverBreathBottleToggle = true;

        @Config.Name("Bow Infinity")
        @Config.Comment("Bows enchanted with Infinity no longer require arrows")
        public boolean utBowInfinityToggle = true;

        @Config.Name("Custom Rarity")
        @Config.Comment
            ({
                "Sets custom rarities for items, affecting tooltip colors",
                "Syntax: modid:item:meta;rarity",
                "'meta' is optional and defaults to 0",
                "Available rarities: common, uncommon, rare, epic",
                "Example -> minecraft:diamond;rare"
            })
        public String[] utCustomRarities = new String[]
            {
                "minecraft:dragon_breath;uncommon",
                "minecraft:elytra;uncommon",
                "minecraft:experience_bottle;uncommon",
                "minecraft:nether_star;uncommon",
                "minecraft:skull:0;uncommon",
                "minecraft:skull:1;uncommon",
                "minecraft:skull:2;uncommon",
                "minecraft:skull:3;uncommon",
                "minecraft:skull:4;uncommon",
                "minecraft:skull:5;uncommon",
                "minecraft:totem_of_undying;uncommon",
                "minecraft:beacon;rare",
                "minecraft:end_crystal;rare",
                "minecraft:barrier;epic",
                "minecraft:chain_command_block;epic",
                "minecraft:command_block;epic",
                "minecraft:command_block_minecart;epic",
                "minecraft:dragon_egg;epic",
                "minecraft:knowledge_book;epic",
                "minecraft:repeating_command_block;epic",
                "minecraft:structure_block;epic",
                "minecraft:structure_void;epic",
                "thaumcraft:thaumium_axe;uncommon",
                "thaumcraft:thaumium_hoe;uncommon",
                "thaumcraft:thaumium_pick;uncommon",
                "thaumcraft:thaumium_shovel;uncommon",
                "thaumcraft:thaumium_sword;uncommon",
                "thaumcraft:void_axe;uncommon",
                "thaumcraft:void_hoe;uncommon",
                "thaumcraft:void_pick;uncommon",
                "thaumcraft:void_shovel;uncommon",
                "thaumcraft:void_sword;uncommon",
                "thaumcraft:primal_crusher;epic"
            };

        @Config.RequiresMcRestart
        @Config.Name("Custom Use Duration")
        @Config.Comment
            ({
                "Sets custom use durations for items like shields, affecting the maximum block time",
                "Syntax: modid:item:meta;duration;cooldown",
                "'meta' and 'cooldown' are optional and default to 0, 'duration' and 'cooldown' in ticks",
                "Examples -> minecraft:shield;69",
                "         -> custommod:customshield:1;42;69"
            })
        public String[] utCustomUseDurations = new String[] {};

        @Config.Name("Super Hot Torch")
        @Config.Comment("Enables one-time ignition of entities by hitting them with a torch")
        public boolean utSuperHotTorchToggle = false;

        @Config.RequiresMcRestart
        @Config.Name("XP Bottle Amount")
        @Config.Comment
            ({
                "Sets the amount of experience spawned by bottles o' enchanting",
                "-1 for vanilla default"
            })
        public int utXPBottleAmount = -1;

        public static class AttackCooldownCategory
        {
            @Config.RequiresMcRestart
            @Config.Name("[1] No Attack Cooldown Toggle")
            @Config.Comment("Disables the 1.9 combat update attack cooldown")
            public boolean utAttackCooldownToggle = false;

            @Config.Name("[2] Only Affect Swords")
            @Config.Comment("Only removes the attack cooldown of swords to balance other weapons like axes")
            public boolean utAttackCooldownSwords = false;

            @Config.Name("[3] Hide Attack Speed Tooltip")
            @Config.Comment("Hides attack speed tooltips of weapons")
            public boolean utAttackCooldownTooltips = true;
        }

        public static class ItemEntitiesCategory
        {
            @Config.RequiresMcRestart
            @Config.Name("[01] Item Entities Toggle")
            @Config.Comment("Enables the modification of item entity properties")
            public boolean utItemEntitiesToggle = true;

            @Config.Name("[02] Physics")
            @Config.Comment("Adds physical aspects such as collision boxes to item entities")
            public boolean utIEPhysicsToggle = false;

            @Config.Name("[03] Automatic Pickup")
            @Config.Comment
                ({
                    "Item entities can be picked up automatically",
                    "When disabled, item entities can be picked up by right-clicking (requires 'Physics' option)"
                })
            public boolean utIEAutomaticPickupToggle = true;

            @Config.Name("[04] Sneaking Pickup")
            @Config.Comment("Item entities can only be picked up when sneaking")
            public boolean utIESneakingPickupToggle = false;

            @Config.Name("[05] Collection Tool")
            @Config.Comment
                ({
                    "Tools which enable picking up items automatically",
                    "Example -> minecraft:bucket"
                })
            public String[] utIECollectionTools = new String[] {};

            @Config.Name("[06] Pickup Delay")
            @Config.Comment
                ({
                    "Determines the delay in ticks until item entities can be picked up",
                    "-1 for vanilla default"
                })
            public int utIEPickupDelay = -1;

            @Config.Name("[07] Lifespan")
            @Config.Comment
                ({
                    "Determines the time in ticks until item entities get despawned",
                    "-1 for vanilla default"
                })
            public int utIELifespan = -1;

            @Config.Name("[08] No Combination")
            @Config.Comment("Stops combination of item entities")
            public boolean utIENoCombinationToggle = false;

            @Config.Name("[09] Smart Combination")
            @Config.Comment("Stops combination of item entities if their maximum stack size is reached")
            public boolean utIESmartCombinationToggle = true;

            @Config.Name("[10] Smart Combination Radius")
            @Config.Comment
                ({
                    "The radius (in blocks) that dropped items should check around them for other dropped items to combine with",
                    "Depends on the Smart Combination toggle"
                })
            public double utIESmartCombinationRadius = 2;

            @Config.Name("[11] Smart Combination Y-Axis Check")
            @Config.Comment
                ({
                    "Allows dropped items to also check above and below them for combination",
                    "Depends on the Smart Combination toggle"
                })
            public boolean utIESmartCombinationYAxis = true;

            @Config.Name("[12] Rotation")
            @Config.Comment("Enables the rotation effect")
            public boolean utIERotationToggle = true;

            @Config.Name("[13] Bobbing")
            @Config.Comment("Enables the bobbing effect")
            public boolean utIEBobbingToggle = true;

            @Config.Name("[14] Clear Despawn")
            @Config.Comment("Makes item entities flash when they're about to despawn")
            public boolean utIEClearDespawnToggle = false;

            @Config.Name("[15] Clear Despawn: Flashing Time")
            @Config.Comment("Determines the time in seconds item entities have left before despawn to start flashing")
            public int utIEClearDespawnTime = 20;

            @Config.Name("[16] Clear Despawn: Urgent Flashing")
            @Config.Comment("Makes item entities flash faster as they get closer to despawning")
            public boolean utIEClearDespawnUrgentToggle = true;
        }

        public static class MendingCategory
        {
            @Config.Name("[1] Mending Toggle")
            @Config.Comment("Implements modern mending behavior to only repair damaged equipment with XP")
            public boolean utMendingToggle = true;

            @Config.Name("[2] Ratio")
            @Config.Comment("Determines the amount of durability mending will repair, on average, per point of experience")
            public double utMendingRatio = 2.0D;

            @Config.Name("[3] Overpowered")
            @Config.Comment("Repairs damaged items from the entire inventory with XP")
            public boolean utMendingOPToggle = false;
        }

        public static class ParryCategory
        {
            @Config.Name("[01] Shield Parry Toggle")
            @Config.Comment("Allows parrying of projectiles with shields")
            public boolean utParryToggle = false;

            @Config.Name("[02] Arrow Time Window")
            @Config.Comment
                ({
                    "Determines the amount of time an arrow can be parried after raising the shield",
                    "Measured in ticks"
                })
            public int utParryArrowTimeWindow = 40;

            @Config.Name("[03] Fireball Time Window")
            @Config.Comment
                ({
                    "Determines the amount of time a fireball can be parried after raising the shield",
                    "Measured in ticks"
                })
            public int utParryFireballTimeWindow = 40;

            @Config.Name("[04] Throwable Time Window")
            @Config.Comment
                ({
                    "Determines the amount of time a throwable can be parried after raising the shield",
                    "Measured in ticks"
                })
            public int utParryThrowableTimeWindow = 40;

            @Config.Name("[05] Projectile List")
            @Config.Comment
                ({
                    "Syntax:  modid:entity",
                    "Example: minecraft:arrow"
                })
            public String[] utParryProjectileList = new String[] {};

            @Config.Name("[06] List Mode")
            @Config.Comment
                ({
                    "Blacklist Mode: Projectiles which can't be parried, others can be parried",
                    "Whitelist Mode: Projectiles which can be parried, others can't be parried"
                })
            public EnumLists utParryProjectileListMode = EnumLists.BLACKLIST;

            @Config.Name("[07] Indication Sound")
            @Config.Comment("Plays an indication sound effect when projectiles are parried")
            public boolean utParrySound = false;

            @Config.Name("[08] Rebound Enchantment")
            @Config.Comment("Adds the Rebound enchantment for extended parry time windows")
            public boolean utParryReboundToggle = true;

            @Config.Name("[09] Rebound Treasure Enchantment")
            @Config.Comment("Makes the Rebound enchantment exclusive to enchanted books as loot")
            public boolean utParryReboundTreasure = false;

            @Config.Name("[10] Rebound Max Level")
            @Config.Comment("Maximum enchantment level for the Rebound enchantment")
            public int utParryReboundMaxLevel = 5;

            @Config.Name("[11] Rebound Multiplier")
            @Config.Comment("Multiplier for the parry time windows")
            public double utParryReboundMultiplier = 0.25D;

            @Config.Name("[12] Require Rebound Enchantment")
            @Config.Comment("Requires the rebound enchantment for parrying")
            public boolean utParryReboundRequire = false;
        }
    }

    public static class MiscCategory
    {
        @Config.LangKey("cfg.universaltweaks.tweaks.misc.armorcurve")
        @Config.Name("Armor Curve")
        public final ArmorCurveCategory ARMOR_CURVE = new ArmorCurveCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.misc.incurablepotions")
        @Config.Name("Incurable Potions")
        public final IncurablePotionsCategory INCURABLE_POTIONS = new IncurablePotionsCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.misc.lightning")
        @Config.Name("Lightning")
        public final LightningCategory LIGHTNING = new LightningCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.misc.loadsounds")
        @Config.Name("Load Sounds")
        public final LoadSoundsCategory LOAD_SOUNDS = new LoadSoundsCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.misc.pickupnotification")
        @Config.Name("Pickup Notification")
        public final PickupNotificationCategory PICKUP_NOTIFICATION = new PickupNotificationCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.misc.smoothscrolling")
        @Config.Name("Smooth Scrolling")
        public final SmoothScrollingCategory SMOOTH_SCROLLING = new SmoothScrollingCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.misc.stg")
        @Config.Name("Swing Through Grass")
        public final SwingThroughGrassCategory SWING_THROUGH_GRASS = new SwingThroughGrassCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.misc.toastcontrol")
        @Config.Name("Toast Control")
        public final ToastControlCategory TOAST_CONTROL = new ToastControlCategory();

        @Config.RequiresMcRestart
        @Config.Name("Copy World Seed")
        @Config.Comment("Enables clicking of `/seed` world seed in chat to copy to clipboard")
        public boolean utCopyWorldSeedToggle = false;

        @Config.Name("Damage Tilt")
        @Config.Comment("Restores feature to tilt the camera when damaged")
        public boolean utDamageTiltToggle = true;

        @Config.Name("Default Difficulty")
        @Config.Comment("Sets the default difficulty for newly generated worlds")
        public EnumDifficulty utDefaultDifficulty = EnumDifficulty.NORMAL;

        @Config.RequiresMcRestart
        @Config.Name("Disable Narrator")
        @Config.Comment("Disables the narrator functionality entirely")
        public boolean utDisableNarratorToggle = true;

        @Config.RequiresMcRestart
        @Config.Name("End Portal Parallax")
        @Config.Comment("Re-implements parallax rendering of the end portal from 1.10 and older")
        public boolean utEndPortalParallaxToggle = true;

        @Config.RequiresMcRestart
        @Config.Name("Infinite Music")
        @Config.Comment("Lets background music play continuously without delays")
        public boolean utInfiniteMusicToggle = false;

        @Config.RequiresMcRestart
        @Config.Name("Linear XP Amount")
        @Config.Comment
            ({
                "Sets the amount of XP needed for each level, effectively removing the increasing level scaling",
                "0 for vanilla default"
            })
        public int utLinearXP = 0;

        @Config.RequiresMcRestart
        @Config.Name("No Night Vision Flash")
        @Config.Comment("Disables the flashing effect when the night vision potion effect is about to run out")
        public boolean utNightVisionFlashToggle = false;

        @Config.Name("No Potion Shift")
        @Config.Comment("Disables the inventory shift when potion effects are active")
        public boolean utPotionShiftToggle = true;

        @Config.RequiresMcRestart
        @Config.Name("No Smelting XP")
        @Config.Comment("Disables the experience reward when smelting items in furnaces")
        public boolean utSmeltingXPToggle = false;

        @Config.Name("Offhand Improvement")
        @Config.Comment("Prevents placing offhand blocks when blocks or food are held in the mainhand")
        public boolean utOffhandToggle = true;

        @Config.RequiresMcRestart
        @Config.Name("Overlay Message Height")
        @Config.Comment
            ({
                "Sets the Y value of the overlay message (action bar), displayed for playing records etc.",
                "-4 for vanilla default"
            })
        public int utOverlayMessageHeight = -4;

        @Config.RequiresMcRestart
        @Config.Name("Remove Realms Button")
        @Config.Comment
            ({
                "Removes the redundant Minecraft Realms button from the main menu",
                "Incompatible with RandomPatches"
            })
        public boolean utRealmsButtonToggle = true;

        @Config.RequiresMcRestart
        @Config.Name("Remove Recipe Book")
        @Config.Comment("Removes the recipe book button from GUIs")
        public boolean utRecipeBookToggle = false;

        @Config.RequiresMcRestart
        @Config.Name("Remove Snooper")
        @Config.Comment("Forcefully turns off the snooper and hides the snooper settings button from the options menu")
        public boolean utSnooperToggle = true;

        @Config.RequiresMcRestart
        @Config.Name("Selected Item Tooltip Height")
        @Config.Comment
            ({
                "Sets the Y value of the selected item tooltip, displayed when held items are changed",
                "59 for vanilla default"
            })
        public int utSelectedItemTooltipHeight = 59;

        @Config.RequiresMcRestart
        @Config.Name("Skip Credits")
        @Config.Comment("Skips the credits screen after the player goes through the end podium portal")
        public boolean utSkipCreditsToggle = false;

        @Config.RequiresMcRestart
        @Config.Name("Skip Missing Registry Entries Screen")
        @Config.Comment
            ({
                "Automatically confirms the 'Missing Registry Entries' screen on world load",
                "Identical to the launch parameter `-Dfml.queryResult=confirm`"
            })
        public boolean utSkipRegistryScreenToggle = false;

        @Config.Name("Toggle Cheats Button")
        @Config.Comment("Adds a button to the pause menu to toggle cheats")
        public boolean utToggleCheatsToggle = true;

        public static class ArmorCurveCategory
        {
            @Config.RequiresMcRestart
            @Config.Name("[1] Armor Curve Toggle")
            @Config.Comment("Adjusts the armor scaling and degradation formulae for mobs and players")
            public boolean utArmorCurveToggle = false;

            @Config.Name("[2] Armor Damage Reduction Formula")
            @Config.Comment
                ({
                    "Configure how much armor does against damage",
                    "Valid values are 'armor', 'damage', and 'toughness'",
                    "Set to 1 to not modify damage at this step"
                })
            public String utArmorCurveArmor = "damage-(damage>(40/(toughness+1)))*((40/(toughness+1)))/2";

            @Config.Name("[3] Armor Toughness Damage Reduction Formula")
            @Config.Comment
                ({
                    "Configure sudden death protection for armor toughness",
                    "Valid values are 'armor', 'damage', and 'toughness'",
                    "Set to 1 to not modify damage at this step"
                })
            public String utArmorCurveArmorToughness = "damage*MAX(10/(10+armor),0.2)";

            @Config.Name("[4] Enchantment Damage Reduction Formula")
            @Config.Comment
                ({
                    "Configure the efficiency of protection enchantments",
                    "Valid values are 'enchant' and 'damage'",
                    "Set to 1 to not modify damage at this step"
                })
            public String utArmorCurveEnchantments = "damage*10/(10+enchant)";

            @Config.Name("[5] Armor Degradation Formula")
            @Config.Comment
                ({
                    "Configure how armor degrades",
                    "Valid values are 'remaining' and 'max'",
                    "Set to 1 to disable"
                })
            public String utArmorCurveDegradation = "remaining/MAX(max,1)";

            @Config.Name("[6] Debug Logging")
            @Config.Comment("Enables debug logging for easier config validation")
            public boolean utArmorCurveLogging = false;
        }

        public static class IncurablePotionsCategory
        {
            @Config.RequiresMcRestart
            @Config.Name("[1] Incurable Potions Toggle")
            @Config.Comment("Determines if potion effects are curable with curative items like buckets of milk")
            public boolean utIncurablePotionsToggle = true;

            @Config.Name("[2] Potion Effect List")
            @Config.Comment("Syntax: modid:potioneffect")
            public String[] utIncurablePotionsList = new String[] {};

            @Config.Name("[3] List Mode")
            @Config.Comment
                ({
                    "Blacklist Mode: Potion effects incurable by curative items, others are curable",
                    "Whitelist Mode: Potion effects curable by curative items, others are incurable"
                })
            public EnumLists utIncurablePotionsListMode = EnumLists.BLACKLIST;
        }

        public static class LightningCategory
        {
            @Config.RequiresMcRestart
            @Config.Name("Lightning Damage")
            @Config.Comment("Sets the damage lightning bolts deal to entities")
            public double utLightningDamage = 5.0D;

            @Config.RequiresMcRestart
            @Config.Name("Lightning Fire Ticks")
            @Config.Comment("Sets the duration in ticks lightning bolts set entities on fire")
            public int utLightningFireTicks = 8;

            @Config.RequiresMcRestart
            @Config.Name("No Lightning Fire")
            @Config.Comment("Disables the creation of fire around lightning strikes")
            public boolean utLightningFireToggle = false;

            @Config.RequiresMcRestart
            @Config.Name("No Lightning Flash")
            @Config.Comment("Disables the flashing of skybox and ground brightness on lightning bolt strikes")
            public boolean utLightningFlashToggle = false;
        }

        public static class LoadSoundsCategory
        {
            @Config.RequiresMcRestart
            @Config.Name("[1] Mode")
            @Config.Comment("Play load sound on...")
            public EnumSoundModes utLoadSoundMode = EnumSoundModes.NOTHING;

            @Config.Name("[2] Minecraft Loaded Sounds")
            @Config.Comment({"Sounds to play when Minecraft is loaded", "Syntax: eventname;pitch"})
            public String[] utLoadSoundMC = new String[]
                {
                    "entity.experience_orb.pickup;1.0",
                    "entity.player.levelup;1.0"
                };

            @Config.Name("[3] World Loaded Sounds")
            @Config.Comment({"Sounds to play when the world is loaded", "Syntax: eventname;pitch"})
            public String[] utLoadSoundWorld = new String[]
                {
                    "entity.experience_orb.pickup;1.0",
                    "entity.player.levelup;1.0"
                };

            public enum EnumSoundModes
            {
                NOTHING,
                MINECRAFT,
                WORLD,
                MINECRAFT_AND_WORLD
            }
        }

        public static class PickupNotificationCategory
        {
            @Config.RequiresMcRestart
            @Config.Name("[01] Pickup Notification Toggle")
            @Config.Comment("Displays notifications when the player obtains or loses items")
            public boolean utPickupNotificationToggle = false;

            @Config.Name("[02] Display Item Additions")
            @Config.Comment("Displays item additions when a player obtains an item")
            public boolean utPUNItemAdditions = true;

            @Config.Name("[03] Display Item Removals")
            @Config.Comment("Displays item removals when a player loses an item")
            public boolean utPUNItemRemovals = true;

            @Config.Name("[04] Display Experience")
            @Config.Comment("Displays changes in player experience")
            public boolean utPUNExperience = true;

            @Config.Name("[05] Display Icon")
            @Config.Comment("Displays the icon of the respective item")
            public boolean utPUNDisplayIcon = true;

            @Config.Name("[06] Display Name")
            @Config.Comment("Displays the name of the respective item")
            public boolean utPUNDisplayName = true;

            @Config.Name("[07] Display Background")
            @Config.Comment("Displays a dark rectangle behind changed items")
            public boolean utPUNDisplayBackground = false;

            @Config.Name("[08] Display Offset Horizontal")
            @Config.Comment("Sets the horizontal offset of the notification")
            public int utPUNOffsetHorizontal = 0;

            @Config.Name("[09] Display Offset Vertical")
            @Config.Comment("Sets the vertical offset of the notification")
            public int utPUNOffsetVertical = 18;

            @Config.Name("[10] Snap Position")
            @Config.Comment("Sets the edge/corner of the screen to use as the base location")
            public EnumDrawPosition utPUNSnapPosition = EnumDrawPosition.BOTTOM_RIGHT;

            @Config.Name("[11] Name Scale")
            @Config.Comment("Sets the scaling of item names")
            public double utPUNScaleName = 0.8;

            @Config.Name("[12] Icon Scale")
            @Config.Comment("Sets the scaling of item icons")
            public double utPUNScaleIcon = 0.8;

            @Config.Name("[13] Soft Limit")
            @Config.Comment("Sets the maximum number of items in the queue before they start fading out artificially")
            public int utPUNSoftLimit = 6;

            @Config.Name("[14] Fade Limit")
            @Config.Comment("Sets the number of items that will be faded out after the soft limit is reached")
            public int utPUNFadeLimit = 3;

            @Config.Name("[15] Display Duration")
            @Config.Comment("Sets the duration in ticks how long the notification will be displayed")
            public int utPUNDisplayDuration = 120;

            @Config.Name("[16] Fade Duration")
            @Config.Comment("Sets the duration in ticks how long the notification fades out")
            public int utPUNFadeDuration = 20;

            @Config.Name("[17] Blacklist: Ignore Item Changes")
            @Config.Comment
                ({
                    "List of item registry names to ignore when displaying changes",
                    "Syntax: modid:item"
                })
            public String[] utPUNBlacklistItem = new String[] {};

            @Config.Name("[18] Blacklist: Ignore Subitem Changes")
            @Config.Comment
                ({
                    "List of item registry names for which to ignore subitem changes",
                    "Syntax: modid:item"
                })
            public String[] utPUNBlacklistSubitem = new String[] {};

            public enum EnumDrawPosition
            {
                BOTTOM_RIGHT,
                BOTTOM,
                BOTTOM_LEFT,
                LEFT,
                TOP_LEFT,
                TOP,
                TOP_RIGHT,
                RIGHT,
                CENTER
            }
        }

        public static class SmoothScrollingCategory
        {
            @Config.RequiresMcRestart
            @Config.Name("[1] Smooth Scrolling Toggle")
            @Config.Comment("Adds smooth scrolling to in-game lists")
            public boolean utSmoothScrollingToggle = true;

            @Config.Name("[2] Bounce Back Multiplier")
            public double utSmoothScrollingBounce = 0.24;

            @Config.Name("[3] Scroll Duration")
            public int utSmoothScrollingDuration = 600;

            @Config.Name("[4] Scroll Step")
            public double utSmoothScrollingStep = 19.0;
        }

        public static class SwingThroughGrassCategory
        {
            @Config.Name("[1] Swing Through Grass Toggle")
            @Config.Comment("Allows hitting entities through grass instead of breaking it")
            public boolean utSwingThroughGrassToggle = true;

            @Config.Name("[2] Blacklist")
            @Config.Comment
                ({
                    "Excludes blocks from the swing through grass tweak",
                    "Syntax: modid:block"
                })
            public String[] utSwingThroughGrassBlacklist = new String[] {};

            @Config.Name("[3] Whitelist")
            @Config.Comment
                ({
                    "Includes blocks in the swing through grass tweak",
                    "Syntax: modid:block"
                })
            public String[] utSwingThroughGrassWhitelist = new String[] {};
        }

        public static class ToastControlCategory
        {
            @Config.RequiresMcRestart
            @Config.Name("[1] Toast Control Toggle")
            @Config.Comment("Enables the control of toasts (pop-up text boxes)")
            public boolean utToastControlToggle = true;

            @Config.Name("[2] Disable Advancement Toasts")
            @Config.Comment("Determines if advancement toasts are blocked. Enabling will block ALL advancements.")
            public boolean utToastControlAdvancementsToggle = false;

            @Config.Name("[3] Disable Recipe Toasts")
            @Config.Comment("Determines if recipe unlock toasts are blocked. Blocks \"you have unlocked a new recipe\" toasts.")
            public boolean utToastControlRecipesToggle = true;

            @Config.Name("[4] Disable System Toasts")
            @Config.Comment("Determines if system toasts are blocked. This is used only for the narrator toggle notification right now.")
            public boolean utToastControlSystemToggle = true;

            @Config.RequiresMcRestart
            @Config.Name("[5] Disable Tutorial Toasts")
            @Config.Comment("Determines if tutorial toasts are blocked. Blocks useless things like use WASD to move.")
            public boolean utToastControlTutorialToggle = true;
        }
    }

    public static class PerformanceCategory
    {
        @Config.RequiresMcRestart
        @Config.Name("Auto Save Interval")
        @Config.Comment("Determines the interval in ticks between world auto saves")
        public int utAutoSaveInterval = 900;

        @Config.RequiresMcRestart
        @Config.Name("Check Animated Models")
        @Config.Comment("Improves model load times by checking if an animated model exists before trying to load it")
        public boolean utCheckAnimatedModelsToggle = true;

        @Config.RequiresMcRestart
        @Config.Name("Crafting Cache")
        @Config.Comment("Adds an IRecipe cache to improve recipe performance in larger modpacks")
        public boolean utCraftingCacheToggle = true;

        @Config.RequiresMcRestart
        @Config.Name("Disable Audio Debug")
        @Config.Comment("Improves loading times by removing debug code for missing sounds and subtitles")
        public boolean utDisableAudioDebugToggle = true;

        @Config.RequiresMcRestart
        @Config.Name("Disable Fancy Missing Model")
        @Config.Comment("Improves rendering performance by removing the resource location text on missing models")
        public boolean utDisableFancyMissingModelToggle = true;

        @Config.RequiresMcRestart
        @Config.Name("Faster Background Startup")
        @Config.Comment("Fixes slow background startup edge case caused by checking tooltips during the loading process")
        public boolean utFasterBackgroundStartupToggle = true;

        @Config.RequiresMcRestart
        @Config.Name("Fast Dye Blending")
        @Config.Comment("Replaces color lookup for sheep to check a predefined table rather than querying the recipe registry")
        public boolean utDyeBlendingToggle = true;

        @Config.RequiresMcRestart
        @Config.Name("Fast Prefix Checking")
        @Config.Comment("Optimizes Forge's ID prefix checking and removes prefix warnings impacting load time")
        public boolean utPrefixCheckToggle = true;

        @Config.RequiresMcRestart
        @Config.Name("Fast World Loading")
        @Config.Comment
            ({
                "Skips initial world chunk loading & garbage collection to speed up world loading",
                "May have side effects such as slower chunk generation"
            })
        public boolean utWorldLoadingToggle = false;

        @Config.RequiresMcRestart
        @Config.Name("No Redstone Lighting")
        @Config.Comment("Disables lighting of active redstone, repeaters, and comparators to improve performance")
        public boolean utRedstoneLightingToggle = false;

        @Config.RequiresMcRestart
        @Config.Name("Uncap FPS")
        @Config.Comment("Removes the hardcoded 30 FPS limit in screens like the main menu")
        public boolean utUncapFPSToggle = true;
    }

    public static class WorldCategory
    {
        @Config.LangKey("cfg.universaltweaks.tweaks.world.chunkgenlimit")
        @Config.Name("Chunk Gen Limit")
        public final ChunkGenLimitCategory CHUNK_GEN_LIMIT = new ChunkGenLimitCategory();

        @Config.LangKey("cfg.universaltweaks.tweaks.world.dimensionunload")
        @Config.Name("Dimension Unload")
        public final DimensionUnloadCategory DIMENSION_UNLOAD = new DimensionUnloadCategory();

        @Config.RequiresMcRestart
        @Config.Name("Sea Level")
        @Config.Comment
            ({
                "Sets the default height of the overworld's sea level",
                "Vanilla default is 63"
            })
        public int utSeaLevel = 63;

        @Config.RequiresMcRestart
        @Config.Name("Stronghold Replacement")
        @Config.Comment("Replaces stronghold generation with a safer variant")
        public boolean utStrongholdToggle = true;

        @Config.Name("Tidy Chunk")
        @Config.Comment("Tidies newly generated chunks by removing scattered item entities")
        public boolean utTidyChunkToggle = false;

        public static class ChunkGenLimitCategory
        {
            @Config.RequiresMcRestart
            @Config.Name("[1] Chunk Gen Limit Toggle")
            @Config.Comment("Limits maximum chunk generation per tick for improved server performance")
            public boolean utChunkGenLimitToggle = false;

            @Config.Name("[2] Ticks")
            @Config.Comment("Maximum chunks to generate per tick per dimension")
            public int utChunkGenLimitTicks = 2;

            @Config.Name("[3] Time")
            @Config.Comment("Maximum time in ms to spend generating chunks per tick per dimension")
            public int utChunkGenLimitTime = 5;
        }

        public static class DimensionUnloadCategory
        {
            @Config.Name("[1] Dimension Unload Toggle")
            @Config.Comment("Unloads dimensions not in use to free up resources")
            public boolean utUnloaderToggle = true;

            @Config.Name("[2] Interval")
            @Config.Comment("Time (in ticks) to wait before checking dimensions")
            public int utUnloaderInterval = 600;

            @Config.Name("[3] Blacklist")
            @Config.Comment
                ({
                    "List of dimensions which should not be unloaded",
                    "Can be dimension name or ID",
                    "Uses regular expressions"
                })
            public String[] utUnloaderBlacklist = new String[]
                {
                    "0",
                    "overworld"
                };
        }
    }

    static
    {
        ConfigAnytime.register(UTConfigTweaks.class);
    }

    @Mod.EventBusSubscriber(modid = UniversalTweaks.MODID)
    public static class EventHandler
    {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
        {
            if (event.getModID().equals(UniversalTweaks.MODID))
            {
                ConfigManager.sync(UniversalTweaks.MODID, Config.Type.INSTANCE);
                if (BLOCKS.BREAKABLE_BEDROCK.utBreakableBedrockToggle) UTBreakableBedrock.initToolList();
                if (MISC.ARMOR_CURVE.utArmorCurveToggle) UTArmorCurve.initExpressions();
                if (MISC.SWING_THROUGH_GRASS.utSwingThroughGrassToggle) UTSwingThroughGrassLists.initLists();
                if (MISC.INCURABLE_POTIONS.utIncurablePotionsToggle) UTIncurablePotions.initPotionList();
                if (ITEMS.utCustomRarities.length > 0) UTCustomRarity.initItemRarityMap();
                if (ITEMS.utCustomUseDurations.length > 0) UTCustomUseDuration.initItemUseMaps();
                if (ITEMS.PARRY.utParryToggle) UTParry.initProjectileList();
                if (UTLoadingPlugin.isClient)
                {
                    if (MISC.LOAD_SOUNDS.utLoadSoundMode != MiscCategory.LoadSoundsCategory.EnumSoundModes.NOTHING) UTLoadSound.initLists();
                    UTAutoSaveOFCompat.updateOFConfig();
                }
            }
        }
    }
}