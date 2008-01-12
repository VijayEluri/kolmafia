/**
 * Copyright (c) 2005-2007, KoLmafia development team
 * http://kolmafia.sourceforge.net/
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  [1] Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *  [2] Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in
 *      the documentation and/or other materials provided with the
 *      distribution.
 *  [3] Neither the name "KoLmafia" nor the names of its contributors may
 *      be used to endorse or promote products derived from this software
 *      without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package net.sourceforge.kolmafia;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.java.dev.spellcast.utilities.LockableListModel;
import net.java.dev.spellcast.utilities.SortedListModel;

/**
 * A container class representing the <code>KoLCharacter</code>. This class also allows for data listeners that are
 * updated whenever the character changes; ultimately, the purpose of this class is to shift away from the
 * centralized-notification paradigm (inefficient) towards a listener paradigm, which is both cleaner and easier to
 * manage with regards to extensions. In addition, it loosens the coupling between the various aspects of
 * <code>KoLmafia</code>, leading to extensibility.
 */

public abstract class KoLCharacter
	extends StaticEntity
{
	private static final Pattern STILLS_PATTERN = Pattern.compile( "with (\\d+) bright" );

	public static final String SEAL_CLUBBER = "Seal Clubber";
	private static final List SEAL_CLUBBER_RANKS = new ArrayList();
	static
	{
		KoLCharacter.SEAL_CLUBBER_RANKS.add( "Lemming Trampler" );
		KoLCharacter.SEAL_CLUBBER_RANKS.add( "Tern Slapper" );
		KoLCharacter.SEAL_CLUBBER_RANKS.add( "Puffin Intimidator" );
		KoLCharacter.SEAL_CLUBBER_RANKS.add( "Ermine Thumper" );
		KoLCharacter.SEAL_CLUBBER_RANKS.add( "Penguin Frightener" );
		KoLCharacter.SEAL_CLUBBER_RANKS.add( "Malamute Basher" );
		KoLCharacter.SEAL_CLUBBER_RANKS.add( "Narwhal Pummeler" );
		KoLCharacter.SEAL_CLUBBER_RANKS.add( "Otter Crusher" );
		KoLCharacter.SEAL_CLUBBER_RANKS.add( "Caribou Smacker" );
		KoLCharacter.SEAL_CLUBBER_RANKS.add( "Moose Harasser" );
		KoLCharacter.SEAL_CLUBBER_RANKS.add( "Reindeer Threatener" );
		KoLCharacter.SEAL_CLUBBER_RANKS.add( "Ox Wrestler" );
		KoLCharacter.SEAL_CLUBBER_RANKS.add( "Walrus Bludgeoner" );
		KoLCharacter.SEAL_CLUBBER_RANKS.add( "Whale Boxer" );
		KoLCharacter.SEAL_CLUBBER_RANKS.add( "Seal Clubber" );
	}

	public static final String TURTLE_TAMER = "Turtle Tamer";
	private static final List TURTLE_TAMER_RANKS = new ArrayList();
	static
	{
		KoLCharacter.TURTLE_TAMER_RANKS.add( "Toad Coach" );
		KoLCharacter.TURTLE_TAMER_RANKS.add( "Skink Trainer" );
		KoLCharacter.TURTLE_TAMER_RANKS.add( "Frog Director" );
		KoLCharacter.TURTLE_TAMER_RANKS.add( "Gecko Supervisor" );
		KoLCharacter.TURTLE_TAMER_RANKS.add( "Newt Herder" );
		KoLCharacter.TURTLE_TAMER_RANKS.add( "Frog Boss" );
		KoLCharacter.TURTLE_TAMER_RANKS.add( "Iguana Driver" );
		KoLCharacter.TURTLE_TAMER_RANKS.add( "Salamander Subduer" );
		KoLCharacter.TURTLE_TAMER_RANKS.add( "Bullfrog Overseer" );
		KoLCharacter.TURTLE_TAMER_RANKS.add( "Rattlesnake Chief" );
		KoLCharacter.TURTLE_TAMER_RANKS.add( "Crocodile Lord" );
		KoLCharacter.TURTLE_TAMER_RANKS.add( "Cobra Commander" );
		KoLCharacter.TURTLE_TAMER_RANKS.add( "Alligator Subjugator" );
		KoLCharacter.TURTLE_TAMER_RANKS.add( "Asp Master" );
		KoLCharacter.TURTLE_TAMER_RANKS.add( "Turtle Tamer" );
	}

	public static final String PASTAMANCER = "Pastamancer";
	private static final List PASTAMANCER_RANKS = new ArrayList();
	static
	{
		KoLCharacter.PASTAMANCER_RANKS.add( "Dough Acolyte" );
		KoLCharacter.PASTAMANCER_RANKS.add( "Yeast Scholar" );
		KoLCharacter.PASTAMANCER_RANKS.add( "Noodle Neophyte" );
		KoLCharacter.PASTAMANCER_RANKS.add( "Starch Savant" );
		KoLCharacter.PASTAMANCER_RANKS.add( "Carbohydrate Cognoscenti" );
		KoLCharacter.PASTAMANCER_RANKS.add( "Spaghetti Sage" );
		KoLCharacter.PASTAMANCER_RANKS.add( "Macaroni Magician" );
		KoLCharacter.PASTAMANCER_RANKS.add( "Vermicelli Enchanter" );
		KoLCharacter.PASTAMANCER_RANKS.add( "Linguini Thaumaturge" );
		KoLCharacter.PASTAMANCER_RANKS.add( "Ravioli Sorcerer" );
		KoLCharacter.PASTAMANCER_RANKS.add( "Manicotti Magus" );
		KoLCharacter.PASTAMANCER_RANKS.add( "Spaghetti Spellbinder" );
		KoLCharacter.PASTAMANCER_RANKS.add( "Cannelloni Conjurer" );
		KoLCharacter.PASTAMANCER_RANKS.add( "Angel-Hair Archmage" );
		KoLCharacter.PASTAMANCER_RANKS.add( "Pastamancer" );
	}

	public static final String SAUCEROR = "Sauceror";
	private static final List SAUCEROR_RANKS = new ArrayList();
	static
	{
		KoLCharacter.SAUCEROR_RANKS.add( "Allspice Acolyte" );
		KoLCharacter.SAUCEROR_RANKS.add( "Cilantro Seer" );
		KoLCharacter.SAUCEROR_RANKS.add( "Parsley Enchanter" );
		KoLCharacter.SAUCEROR_RANKS.add( "Sage Sage" );
		KoLCharacter.SAUCEROR_RANKS.add( "Rosemary Diviner" );
		KoLCharacter.SAUCEROR_RANKS.add( "Thyme Wizard" );
		KoLCharacter.SAUCEROR_RANKS.add( "Tarragon Thaumaturge" );
		KoLCharacter.SAUCEROR_RANKS.add( "Oreganoccultist" );
		KoLCharacter.SAUCEROR_RANKS.add( "Basillusionist" );
		KoLCharacter.SAUCEROR_RANKS.add( "Coriander Conjurer" );
		KoLCharacter.SAUCEROR_RANKS.add( "Bay Leaf Brujo" );
		KoLCharacter.SAUCEROR_RANKS.add( "Sesame Soothsayer" );
		KoLCharacter.SAUCEROR_RANKS.add( "Marinara Mage" );
		KoLCharacter.SAUCEROR_RANKS.add( "Alfredo Archmage" );
		KoLCharacter.SAUCEROR_RANKS.add( "Sauceror" );
	}

	public static final String DISCO_BANDIT = "Disco Bandit";
	private static final List DISCO_BANDIT_RANKS = new ArrayList();
	static
	{
		KoLCharacter.DISCO_BANDIT_RANKS.add( "Funk Footpad" );
		KoLCharacter.DISCO_BANDIT_RANKS.add( "Rhythm Rogue" );
		KoLCharacter.DISCO_BANDIT_RANKS.add( "Chill Crook" );
		KoLCharacter.DISCO_BANDIT_RANKS.add( "Jiggy Grifter" );
		KoLCharacter.DISCO_BANDIT_RANKS.add( "Beat Snatcher" );
		KoLCharacter.DISCO_BANDIT_RANKS.add( "Sample Swindler" );
		KoLCharacter.DISCO_BANDIT_RANKS.add( "Move Buster" );
		KoLCharacter.DISCO_BANDIT_RANKS.add( "Jam Horker" );
		KoLCharacter.DISCO_BANDIT_RANKS.add( "Groove Filcher" );
		KoLCharacter.DISCO_BANDIT_RANKS.add( "Vibe Robber" );
		KoLCharacter.DISCO_BANDIT_RANKS.add( "Boogie Brigand" );
		KoLCharacter.DISCO_BANDIT_RANKS.add( "Flow Purloiner" );
		KoLCharacter.DISCO_BANDIT_RANKS.add( "Jive Pillager" );
		KoLCharacter.DISCO_BANDIT_RANKS.add( "Rhymer and Stealer" );
		KoLCharacter.DISCO_BANDIT_RANKS.add( "Disco Bandit" );
	}

	public static final String ACCORDION_THIEF = "Accordion Thief";
	private static final List ACCORDION_THIEF_RANKS = new ArrayList();
	static
	{
		KoLCharacter.ACCORDION_THIEF_RANKS.add( "Polka Criminal" );
		KoLCharacter.ACCORDION_THIEF_RANKS.add( "Mariachi Larcenist" );
		KoLCharacter.ACCORDION_THIEF_RANKS.add( "Zydeco Rogue" );
		KoLCharacter.ACCORDION_THIEF_RANKS.add( "Chord Horker" );
		KoLCharacter.ACCORDION_THIEF_RANKS.add( "Chromatic Crook" );
		KoLCharacter.ACCORDION_THIEF_RANKS.add( "Squeezebox Scoundrel" );
		KoLCharacter.ACCORDION_THIEF_RANKS.add( "Concertina Con Artist" );
		KoLCharacter.ACCORDION_THIEF_RANKS.add( "Button Box Burglar" );
		KoLCharacter.ACCORDION_THIEF_RANKS.add( "Hurdy-Gurdy Hooligan" );
		KoLCharacter.ACCORDION_THIEF_RANKS.add( "Sub-Sub-Apprentice Accordion Thief" );
		KoLCharacter.ACCORDION_THIEF_RANKS.add( "Sub-Apprentice Accordion Thief" );
		KoLCharacter.ACCORDION_THIEF_RANKS.add( "Pseudo-Apprentice Accordion Thief" );
		KoLCharacter.ACCORDION_THIEF_RANKS.add( "Hemi-Apprentice Accordion Thief" );
		KoLCharacter.ACCORDION_THIEF_RANKS.add( "Apprentice Accordion Thief" );
		KoLCharacter.ACCORDION_THIEF_RANKS.add( "Accordion Thief" );
	}

	private static final int BAKULA = 1519;
	private static final int BOTTLE_ROCKET = 2834;
	private static final int WIZARD_HAT = 1653;
	public static final int HAT = 0;
	public static final int WEAPON = 1;
	public static final int OFFHAND = 2;
	public static final int SHIRT = 3;
	public static final int PANTS = 4;
	public static final int ACCESSORY1 = 5;
	public static final int ACCESSORY2 = 6;
	public static final int ACCESSORY3 = 7;
	public static final int FAMILIAR = 8;
	public static final int FAKEHAND = 9;

	// General static final variables

	private static String username = "";
	private static String avatar = "otherimages/discobandit_f.gif";
	private static int userId = 0;
	private static String playerId = "0";
	private static String classname = "";
	private static String classtype = "";
	private static int currentLevel = 1;
	private static int decrementPrime = 0;
	private static int incrementPrime = 0;

	private static int currentHP, maximumHP, baseMaxHP;
	private static int currentMP, maximumMP, baseMaxMP;

	private static int[] adjustedStats = new int[ 3 ];
	private static int[] totalSubpoints = new int[ 3 ];

	private static LockableListModel equipment = new LockableListModel();
	private static int fakeHands = 0;
	private static LockableListModel customOutfits = new LockableListModel();
	private static LockableListModel outfits = new LockableListModel();

	static
	{
		for ( int i = 0; i < 9; ++i )
		{
			KoLCharacter.equipment.add( EquipmentRequest.UNEQUIP );
		}
	}

	public static final SortedListModel battleSkillNames = new SortedListModel();

	private static final LockableListModel accessories = new SortedListModel();
	private static final LockableListModel[] equipmentLists = new LockableListModel[ 9 ];

	static
	{
		for ( int i = 0; i < 9; ++i )
		{
			switch ( i )
			{
			case ACCESSORY1:
			case ACCESSORY2:
			case ACCESSORY3:
				KoLCharacter.equipmentLists[ i ] = KoLCharacter.accessories.getMirrorImage();
				break;

			default:
				KoLCharacter.equipmentLists[ i ] = new SortedListModel();
				break;
			}
		}
	}

	// Status pane data which is rendered whenever
	// the user issues a "status" type command.

	private static int pvpRank = 0;
	private static int attacksLeft = 0;
	private static int availableMeat = 0;
	private static int storageMeat = 0;
	private static int closetMeat = 0;
	private static int inebriety = 0;
	private static int adventuresLeft = 0;
	private static int currentRun = 0;
	private static boolean isFullnessIncreased = false;
	private static int holidayManaCostReduction = 0;

	// Status pane data which is rendered whenever
	// the user changes equipment, effects, and familiar

	private static Modifiers currentModifiers = new Modifiers();

	// Travel information

	private static boolean hasStore = true;
	private static boolean hasDisplayCase = true;
	private static boolean hasClan = true;

	// Campground information

	private static boolean hasToaster = false;
	private static boolean hasArches = false;
	private static boolean hasChef = false;
	private static boolean hasBartender = false;
	private static boolean hasBookshelf = false;
	private static int telescopeUpgrades = 0;

	// Familiar data for reference

	private static final SortedListModel familiars = new SortedListModel();
	private static boolean isUsingStabBat = false;
	private static FamiliarData currentFamiliar = FamiliarData.NO_FAMILIAR;

	private static int arenaWins = 0;
	private static int stillsAvailable = 0;

	// Listener-driven container items

	private static final List listenerList = new ArrayList();
	private static boolean beanstalkArmed = false;

	// Ascension-related variables

	private static boolean isHardcore = false;
	private static boolean inRonin = true;

	private static int ascensions = 0;
	private static String ascensionSign = "None";
	private static int ascensionSignType = KoLConstants.NONE;
	private static int consumptionRestriction = AscensionSnapshotTable.NOPATH;
	private static int mindControlLevel = 0;
	private static int detunedRadioVolume = 0;
	private static int annoyotronLevel = 0;

	private static String autosellMode = "";

	public static final void resetInventory()
	{
		KoLConstants.inventory.clear();

		// Initialize the equipment lists inside
		// of the character data

		for ( int i = 0; i < KoLCharacter.equipmentLists.length; ++i )
		{
			KoLCharacter.equipmentLists[ i ].clear();
		}

		KoLCharacter.accessories.clear();
		GearChangeFrame.clearWeaponLists();
	}

	/**
	 * Constructs a new <code>KoLCharacter</code> with the given name. All fields are initialized to their default
	 * values (nothing), and it is the responsibility of other methods to initialize the fields with their real values.
	 *
	 * @param newUsername The name of the character this <code>KoLCharacter</code> represents
	 */

	public static final void reset( final String newUserName )
	{
		if ( newUserName.equals( KoLCharacter.username ) )
		{
			return;
		}

		KoLCharacter.username = newUserName;

		KoLCharacter.reset();
		KoLSettings.reset( KoLCharacter.username );
	}

	public static final void reset()
	{
		KoLCharacter.classname = "";
		KoLCharacter.classtype = "";

		KoLCharacter.currentLevel = 1;
		KoLCharacter.decrementPrime = 0;
		KoLCharacter.incrementPrime = 0;

		KoLCharacter.pvpRank = 0;
		KoLCharacter.attacksLeft = 0;
		KoLCharacter.adjustedStats = new int[ 3 ];
		KoLCharacter.totalSubpoints = new int[ 3 ];

		KoLCharacter.currentModifiers.reset();

		KoLCharacter.equipment.clear();
		for ( int i = 0; i < 9; ++i )
		{
			KoLCharacter.equipment.add( EquipmentRequest.UNEQUIP );
		}

		KoLCharacter.fakeHands = 0;
		KoLCharacter.customOutfits.clear();
		KoLCharacter.outfits.clear();

		KoLConstants.closet.clear();
		KoLConstants.storage.clear();
		KoLConstants.collection.clear();

		KoLConstants.usableSkills.clear();
		KoLConstants.summoningSkills.clear();
		KoLConstants.remedySkills.clear();
		KoLConstants.selfOnlySkills.clear();
		KoLConstants.buffSkills.clear();
		KoLConstants.availableSkills.clear();
		KoLCharacter.battleSkillNames.clear();

		// All characters get the option to
		// attack something.

		KoLCharacter.battleSkillNames.add( "attack with weapon" );
		KoLCharacter.battleSkillNames.add( "custom combat script" );
		KoLCharacter.battleSkillNames.add( "delevel and plink" );

		KoLCharacter.battleSkillNames.add( "item dictionary" );
		KoLCharacter.battleSkillNames.add( "item toy mercenary" );

		KoLCharacter.battleSkillNames.add( "item seal tooth" );
		KoLCharacter.battleSkillNames.add( "item turtle totem" );
		KoLCharacter.battleSkillNames.add( "item spices" );

		KoLCharacter.battleSkillNames.add( "try to run away" );

		KoLCharacter.isHardcore = false;
		KoLCharacter.inRonin = true;
		KoLCharacter.hasStore = false;
		KoLCharacter.hasDisplayCase = false;
		KoLCharacter.hasClan = false;

		KoLCharacter.hasToaster = false;
		KoLCharacter.hasArches = false;
		KoLCharacter.hasChef = false;
		KoLCharacter.hasBartender = false;
		KoLCharacter.hasBookshelf = false;
		KoLCharacter.telescopeUpgrades = 0;

		KoLCharacter.familiars.clear();
		KoLCharacter.familiars.add( FamiliarData.NO_FAMILIAR );

		KoLCharacter.arenaWins = 0;
		KoLCharacter.isUsingStabBat = false;

		KoLCharacter.stillsAvailable = -1;
		KoLCharacter.beanstalkArmed = false;

		KoLCharacter.ascensions = 0;
		KoLCharacter.ascensionSign = "None";
		KoLCharacter.ascensionSignType = KoLConstants.NONE;

		KoLCharacter.mindControlLevel = 0;
		KoLCharacter.detunedRadioVolume = 0;
		KoLCharacter.annoyotronLevel = 0;

		KoLCharacter.autosellMode = "";

		// Clear some of the standard lists so they don't
		// carry over from player to player.

		KoLConstants.conditions.clear();
		KoLConstants.eventHistory.clear();
		KoLConstants.recentEffects.clear();
		KoLConstants.activeEffects.clear();

		// Don't reuse NPC food & drink from a previous login
		RestaurantRequest.reset();
		MicrobreweryRequest.reset();
		KitchenRequest.reset();

		KoLCharacter.resetInventory();

		int battleIndex = KoLCharacter.battleSkillNames.indexOf( KoLSettings.getUserProperty( "battleAction" ) );
		KoLCharacter.battleSkillNames.setSelectedIndex( battleIndex == -1 ? 0 : battleIndex );
	}

	public static final void setHoliday( final String holiday )
	{
		KoLCharacter.isFullnessIncreased = holiday.equals( "Feast of Boris" );
		KoLCharacter.holidayManaCostReduction = holiday.equals( "Festival of Jarlsberg" ) ? 3 : 0;
	}

	public static final int getFullness()
	{
		return KoLSettings.getIntegerProperty( "currentFullness" );
	}

	public static final int getFullnessLimit()
	{
		int baseFullness = KoLCharacter.hasSkill( "Stomach of Steel" ) ? 20 : KoLCharacter.canEat() ? 15 : 0;
		return baseFullness == 0 ? 0 : KoLCharacter.isFullnessIncreased ? baseFullness + 15 : baseFullness;
	}

	public static final void setInebriety( final int inebriety )
	{
		KoLCharacter.inebriety = inebriety;
	}

	public static final int getInebriety()
	{
		return KoLCharacter.inebriety;
	}

	public static final int getInebrietyLimit()
	{
		return KoLCharacter.hasSkill( "Liver of Steel" ) ? 19 : KoLCharacter.canDrink() ? 14 : 0;
	}

	public static final boolean isFallingDown()
	{
		return KoLCharacter.getInebriety() > KoLCharacter.getInebrietyLimit();
	}

	public static final int getSpleenUse()
	{
		return KoLSettings.getIntegerProperty( "currentSpleenUse" );
	}

	public static final int getSpleenLimit()
	{
		return KoLCharacter.hasSkill( "Spleen of Steel" ) ? 20 : 15;
	}

	/**
	 * Accessor method to retrieve the name of this character.
	 *
	 * @return The name of this character
	 */

	public static final String getUserName()
	{
		return KoLCharacter.username;
	}

	public static final String baseUserName()
	{
		return KoLSettings.baseUserName( KoLCharacter.username );
	}

	/**
	 * Accessor method to set the user Id associated with this character.
	 *
	 * @param userId The user Id associated with this character
	 */

	public static final void setUserId( final int userId )
	{
		KoLCharacter.userId = userId;
		KoLCharacter.playerId = String.valueOf( userId );
	}

	/**
	 * Accessor method to retrieve the user Id associated with this character.
	 *
	 * @return The user Id associated with this character
	 */

	public static final String getPlayerId()
	{
		return KoLCharacter.playerId;
	}

	/**
	 * Accessor method to retrieve the user Id associated with this character.
	 *
	 * @return The user Id associated with this character
	 */

	public static final int getUserId()
	{
		return KoLCharacter.userId;
	}

	/**
	 * Accessor method to get the avatar associated with this character.
	 *
	 * @param avatar The avatar for this character
	 */

	public static final void setAvatar( final String avatar )
	{
		KoLCharacter.avatar = avatar;
	}

	/**
	 * Accessor method to get the avatar associated with this character.
	 *
	 * @return The avatar for this character
	 */

	public static final String getAvatar()
	{
		RequestEditorKit.downloadImage( "http://images.kingdomofloathing.com/" + KoLCharacter.avatar );
		return KoLCharacter.avatar;
	}

	/**
	 * Accessor method to retrieve the index of the prime stat.
	 *
	 * @return The index of the prime stat
	 */

	public static final int getPrimeIndex()
	{
		return KoLCharacter.classtype.startsWith( "Se" ) || KoLCharacter.classtype.startsWith( "Tu" ) ? 0 : KoLCharacter.classtype.startsWith( "Sa" ) || KoLCharacter.classtype.startsWith( "Pa" ) ? 1 : 2;
	}

	/**
	 * Accessor method to retrieve the level of this character.
	 *
	 * @return The level of this character
	 */

	public static final int getLevel()
	{
		int totalPrime = KoLCharacter.getTotalPrime();

		if ( totalPrime <= KoLCharacter.decrementPrime || totalPrime >= KoLCharacter.incrementPrime )
		{
			KoLCharacter.currentLevel =
				(int) Math.sqrt( KoLCharacter.calculateBasePoints( KoLCharacter.getTotalPrime() ) - 4 ) + 1;
			KoLCharacter.decrementPrime = KoLCharacter.calculateLastLevel();
			KoLCharacter.incrementPrime = KoLCharacter.calculateNextLevel();
		}

		return KoLCharacter.currentLevel;
	}

	public static final int getPvpRank()
	{
		return KoLCharacter.pvpRank;
	}

	public static final void setPvpRank( final int pvpRank )
	{
		KoLCharacter.pvpRank = pvpRank;
	}

	public static final int getAttacksLeft()
	{
		return KoLCharacter.attacksLeft;
	}

	public static final void setAttacksLeft( final int attacksLeft )
	{
		KoLCharacter.attacksLeft = attacksLeft;
	}

	/**
	 * Accessor method to set the character's class.
	 *
	 * @param classname The name of the character's class
	 */

	public static final void setClassName( final String classname )
	{
		KoLCharacter.classname = classname;
		KoLCharacter.classtype = null;
		KoLCharacter.classtype = KoLCharacter.getClassType();
	}

	/**
	 * Accessor method to retrieve the name of the character's class.
	 *
	 * @return The name of the character's class
	 */

	public static final String getClassName()
	{
		return KoLCharacter.classname;
	}

	/**
	 * Accessor method to retrieve the type of the character's class.
	 *
	 * @return The type of the character's class
	 */

	public static final String getClassType()
	{
		if ( KoLCharacter.classtype == null )
		{
			KoLCharacter.classtype =
				KoLCharacter.SEAL_CLUBBER_RANKS.contains( KoLCharacter.classname ) ? KoLCharacter.SEAL_CLUBBER : KoLCharacter.TURTLE_TAMER_RANKS.contains( KoLCharacter.classname ) ? KoLCharacter.TURTLE_TAMER : KoLCharacter.PASTAMANCER_RANKS.contains( KoLCharacter.classname ) ? KoLCharacter.PASTAMANCER : KoLCharacter.SAUCEROR_RANKS.contains( KoLCharacter.classname ) ? KoLCharacter.SAUCEROR : KoLCharacter.DISCO_BANDIT_RANKS.contains( KoLCharacter.classname ) ? KoLCharacter.DISCO_BANDIT : KoLCharacter.ACCORDION_THIEF_RANKS.contains( KoLCharacter.classname ) ? KoLCharacter.ACCORDION_THIEF : KoLCharacter.SAUCEROR;
		}

		return KoLCharacter.classtype;
	}

	/**
	 * Accessor method to retrieve the type of the character's class.
	 *
	 * @return The type of the character's class
	 */

	public static final String getClassType( final String classname )
	{
		return KoLCharacter.SEAL_CLUBBER_RANKS.contains( classname ) ? KoLCharacter.SEAL_CLUBBER : KoLCharacter.TURTLE_TAMER_RANKS.contains( classname ) ? KoLCharacter.TURTLE_TAMER : KoLCharacter.PASTAMANCER_RANKS.contains( classname ) ? KoLCharacter.PASTAMANCER : KoLCharacter.SAUCEROR_RANKS.contains( classname ) ? KoLCharacter.SAUCEROR : KoLCharacter.DISCO_BANDIT_RANKS.contains( classname ) ? KoLCharacter.DISCO_BANDIT : KoLCharacter.ACCORDION_THIEF_RANKS.contains( classname ) ? KoLCharacter.ACCORDION_THIEF : KoLCharacter.SAUCEROR;
	}

	public static final boolean isMuscleClass()
	{
		return KoLCharacter.classtype.equals( KoLCharacter.SEAL_CLUBBER ) || KoLCharacter.classtype.equals( KoLCharacter.TURTLE_TAMER );
	}

	public static final boolean isMysticalityClass()
	{
		return KoLCharacter.classtype.equals( KoLCharacter.PASTAMANCER ) || KoLCharacter.classtype.equals( KoLCharacter.SAUCEROR );
	}

	public static final boolean isMoxieClass()
	{
		return KoLCharacter.classtype.equals( KoLCharacter.DISCO_BANDIT ) || KoLCharacter.classtype.equals( KoLCharacter.ACCORDION_THIEF );
	}

	/**
	 * Accessor method to set the character's current health state.
	 *
	 * @param currentHP The character's current HP value
	 * @param maximumHP The character's maximum HP value
	 * @param baseMaxHP The base value for the character's maximum HP
	 */

	public static final void setHP( final int currentHP, final int maximumHP, final int baseMaxHP )
	{
		KoLCharacter.currentHP = currentHP < 0 ? 0 : currentHP > maximumHP ? maximumHP : currentHP;
		KoLCharacter.maximumHP = maximumHP;
		KoLCharacter.baseMaxHP = baseMaxHP;
	}

	/**
	 * Accessor method to retrieve the character's current HP.
	 *
	 * @return The character's current HP
	 */

	public static final int getCurrentHP()
	{
		return KoLCharacter.currentHP;
	}

	/**
	 * Accessor method to retrieve the character's maximum HP.
	 *
	 * @return The character's maximum HP
	 */

	public static final int getMaximumHP()
	{
		return KoLCharacter.maximumHP;
	}

	/**
	 * Accessor method to retrieve the base value for the character's maximum HP.
	 *
	 * @return The base value for the character's maximum HP
	 */

	public static final int getBaseMaxHP()
	{
		return KoLCharacter.baseMaxHP;
	}

	/**
	 * Accessor method to set the character's current mana limits.
	 *
	 * @param currentMP The character's current MP value
	 * @param maximumMP The character's maximum MP value
	 * @param baseMaxMP The base value for the character's maximum MP
	 */

	public static final void setMP( final int currentMP, final int maximumMP, final int baseMaxMP )
	{
		KoLCharacter.currentMP = currentMP < 0 ? 0 : currentMP > maximumMP ? maximumMP : currentMP;
		KoLCharacter.maximumMP = maximumMP;
		KoLCharacter.baseMaxMP = baseMaxMP;
	}

	/**
	 * Accessor method to retrieve the character's current MP.
	 *
	 * @return The character's current MP
	 */

	public static final int getCurrentMP()
	{
		return KoLCharacter.currentMP;
	}

	/**
	 * Accessor method to retrieve the character's maximum MP.
	 *
	 * @return The character's maximum MP
	 */

	public static final int getMaximumMP()
	{
		return KoLCharacter.maximumMP;
	}

	/**
	 * Accessor method to retrieve the base value for the character's maximum MP.
	 *
	 * @return The base value for the character's maximum MP
	 */

	public static final int getBaseMaxMP()
	{
		return KoLCharacter.baseMaxMP;
	}

	public static final void setStorageMeat( final int storageMeat )
	{
		KoLCharacter.storageMeat = storageMeat;
	}

	public static final int getStorageMeat()
	{
		return KoLCharacter.storageMeat;
	}

	/**
	 * Accessor method to set the amount of meat in the character's closet.
	 *
	 * @param closetMeat The amount of meat in the character's closet.
	 */

	public static final void setClosetMeat( final int closetMeat )
	{
		KoLCharacter.closetMeat = closetMeat;
	}

	/**
	 * Accessor method to retrieve the amount of meat in the character's closet.
	 *
	 * @return The amount of meat in the character's closet.
	 */

	public static final int getClosetMeat()
	{
		return KoLCharacter.closetMeat;
	}

	/**
	 * Accessor method to set the character's current available meat for spending (IE: meat that isn't currently in the
	 * character's closet).
	 *
	 * @param availableMeat The character's available meat for spending
	 */

	public static final void setAvailableMeat( final int availableMeat )
	{
		if ( KoLCharacter.availableMeat != availableMeat )
		{
			KoLCharacter.availableMeat = availableMeat;
		}
	}

	/**
	 * Accessor method to retrieve the character's current available meat for spending (IE: meat that isn't currently in
	 * the character's closet).
	 *
	 * @return The character's available meat for spending
	 */

	public static final int getAvailableMeat()
	{
		return KoLCharacter.availableMeat;
	}

	/**
	 * Sets the character's current stat values. Each parameter in the list comes in pairs: the adjusted value (based on
	 * equipment and spell effects) and the total number of subpoints acquired through adventuring for that statistic.
	 * This is preferred over the character's current base and/or distance from base as it allows for more accurate
	 * reporting of statistic gains and losses, as statistic losses are not reported by KoL.
	 *
	 * @param adjustedMuscle The adjusted value for the character's muscle
	 * @param totalMuscle The total number of muscle subpoints acquired thus far
	 * @param adjustedMysticality The adjusted value for the character's mysticality
	 * @param totalMysticality The total number of mysticality subpoints acquired thus far
	 * @param adjustedMoxie The adjusted value for the character's moxie
	 * @param totalMoxie The total number of moxie subpoints acquired thus far
	 */

	public static final void setStatPoints( final int adjustedMuscle, final int totalMuscle,
		final int adjustedMysticality, final int totalMysticality, final int adjustedMoxie, final int totalMoxie )
	{
		KoLCharacter.adjustedStats[ 0 ] = adjustedMuscle;
		KoLCharacter.adjustedStats[ 1 ] = adjustedMysticality;
		KoLCharacter.adjustedStats[ 2 ] = adjustedMoxie;

		KoLCharacter.totalSubpoints[ 0 ] = totalMuscle;
		KoLCharacter.totalSubpoints[ 1 ] = totalMysticality;
		KoLCharacter.totalSubpoints[ 2 ] = totalMoxie;
	}

	/**
	 * Utility method for calculating how many subpoints have been accumulated thus far, given the current base point
	 * value of the statistic and how many have been accumulate since the last gain.
	 *
	 * @param baseValue The current base point value
	 * @param sinceLastBase Number of subpoints accumulate since the last base point gain
	 * @return The total number of subpoints acquired since creation
	 */

	public static final int calculateSubpoints( final int baseValue, final int sinceLastBase )
	{
		return baseValue * baseValue - 1 + sinceLastBase;
	}

	/**
	 * Utility method for calculating how many actual points are associated with the given number of subpoints.
	 *
	 * @param totalSubpoints The total number of subpoints accumulated
	 * @return The base points associated with the subpoint value
	 */

	public static final int calculateBasePoints( final int totalSubpoints )
	{
		return (int) Math.floor( Math.sqrt( totalSubpoints + 1 ) );
	}

	/**
	 * Returns the total number of subpoints to the current level.
	 *
	 * @return The total subpoints to the current level
	 */

	public static final int calculateLastLevel()
	{
		int level = KoLCharacter.currentLevel - 1;
		int basePointsNeeded = level * level + 4;
		return basePointsNeeded * basePointsNeeded - 1;
	}

	/**
	 * Returns the total number of subpoints to the next level.
	 *
	 * @return The total subpoints to the next level
	 */

	public static final int calculateNextLevel()
	{
		int level = KoLCharacter.currentLevel;
		int basePointsNeeded = level * level + 4;
		return basePointsNeeded * basePointsNeeded - 1;
	}

	/**
	 * Returns the total number of subpoints acquired in the prime stat.
	 *
	 * @return The total subpoints in the prime stat
	 */

	public static final int getTotalPrime()
	{
		return KoLCharacter.totalSubpoints[ KoLCharacter.getPrimeIndex() ];
	}

	/**
	 * Utility method to calculate the "till next point" value, given the total number of subpoints accumulated.
	 */

	private static final int calculateTillNextPoint( final int totalSubpoints )
	{
		int basePoints = KoLCharacter.calculateBasePoints( totalSubpoints ) + 1;
		return basePoints * basePoints - totalSubpoints - 1;
	}

	/**
	 * Accessor method to retrieve the character's base value for muscle.
	 *
	 * @return The character's base value for muscle
	 */

	public static final int getBaseMuscle()
	{
		return KoLCharacter.calculateBasePoints( KoLCharacter.totalSubpoints[ 0 ] );
	}

	/**
	 * Accessor method to retrieve the total subpoints accumulted so far in muscle.
	 *
	 * @return The total muscle subpoints so far
	 */

	public static final int getTotalMuscle()
	{
		return KoLCharacter.totalSubpoints[ 0 ];
	}

	/**
	 * Accessor method to retrieve the number of subpoints required before the character gains another full point of
	 * muscle.
	 */

	public static final int getMuscleTNP()
	{
		return KoLCharacter.calculateTillNextPoint( KoLCharacter.totalSubpoints[ 0 ] );
	}

	/**
	 * Accessor method to retrieve the character's adjusted value for muscle.
	 *
	 * @return The character's adjusted value for muscle
	 */

	public static final int getAdjustedMuscle()
	{
		return KoLCharacter.adjustedStats[ 0 ];
	}

	/**
	 * Accessor method to retrieve the character's base value for mysticality.
	 *
	 * @return The character's base value for muscle
	 */

	public static final int getBaseMysticality()
	{
		return KoLCharacter.calculateBasePoints( KoLCharacter.totalSubpoints[ 1 ] );
	}

	/**
	 * Accessor method to retrieve the total subpoints accumulted so far in mysticality.
	 *
	 * @return The total mysticality subpoints so far
	 */

	public static final int getTotalMysticality()
	{
		return KoLCharacter.totalSubpoints[ 1 ];
	}

	/**
	 * Accessor method to retrieve the number of subpoints required before the character gains another full point of
	 * mysticality.
	 */

	public static final int getMysticalityTNP()
	{
		return KoLCharacter.calculateTillNextPoint( KoLCharacter.totalSubpoints[ 1 ] );
	}

	/**
	 * Accessor method to retrieve the character's adjusted value for mysticality.
	 *
	 * @return The character's adjusted value for mysticality
	 */

	public static final int getAdjustedMysticality()
	{
		return KoLCharacter.adjustedStats[ 1 ];
	}

	/**
	 * Accessor method to retrieve the character's base value for moxie.
	 *
	 * @return The character's base value for moxie
	 */

	public static final int getBaseMoxie()
	{
		return KoLCharacter.calculateBasePoints( KoLCharacter.totalSubpoints[ 2 ] );
	}

	/**
	 * Accessor method to retrieve the total subpoints accumulted so far in moxie.
	 *
	 * @return The total moxie subpoints so far
	 */

	public static final int getTotalMoxie()
	{
		return KoLCharacter.totalSubpoints[ 2 ];
	}

	/**
	 * Accessor method to retrieve the number of subpoints required before the character gains another full point of
	 * moxie.
	 */

	public static final int getMoxieTNP()
	{
		return KoLCharacter.calculateTillNextPoint( KoLCharacter.totalSubpoints[ 2 ] );
	}

	/**
	 * Accessor method to retrieve the character's adjusted value for moxie.
	 *
	 * @return The character's adjusted value for moxie
	 */

	public static final int getAdjustedMoxie()
	{
		return KoLCharacter.adjustedStats[ 2 ];
	}

	/**
	 * Accessor method to set the number of adventures the character has left to spend in this session.
	 *
	 * @param adventuresLeft The number of adventures the character has left
	 */

	public static final void setAdventuresLeft( final int adventuresLeft )
	{
		if ( adventuresLeft != KoLCharacter.adventuresLeft )
		{
			KoLCharacter.adventuresLeft = adventuresLeft;
			if ( KoLCharacter.canEat() && !KoLCharacter.hasChef() || KoLCharacter.canDrink() && !KoLCharacter.hasBartender() )
			{
				ConcoctionsDatabase.refreshConcoctions();
			}
		}
	}

	/**
	 * Accessor method to retrieve the number of adventures the character has left to spend in this session.
	 *
	 * @return The number of adventures the character has left
	 */

	public static final int getAdventuresLeft()
	{
		return KoLCharacter.adventuresLeft;
	}

	public static final void setCurrentRun( final int currentRun )
	{
		KoLCharacter.currentRun = currentRun;
	}

	/**
	 * Accessor method to retrieve the total number of turns the character
	 * has used this run.
	 */

	public static final int getCurrentRun()
	{
		return KoLCharacter.currentRun;
	}

	/**
	 * Accessor method to record the turn count when a semirare was found.
	 */

	public static final void registerSemirare()
	{
		KoLCharacter.ensureUpdatedSemirareCounter();
		KoLSettings.setUserProperty( "semirareCounter", String.valueOf( KoLCharacter.currentRun + 1 ) );
		StaticEntity.stopCounting( "Fortune Cookie" );
	}

	/**
	 * Accessor method to return how many turns have passed since the last
	 * semirare was found.
	 */

	public static final int turnsSinceLastSemirare()
	{
                KoLCharacter.ensureUpdatedSemirareCounter();
                int last = KoLSettings.getIntegerProperty( "semirareCounter" );
                return KoLCharacter.currentRun - last;
	}

	/**
	 * Accessor method to retrieve the current value of a named modifier
	 */

	public static final float currentNumericModifier( final String name )
	{
		return KoLCharacter.currentModifiers.get( name );
	}

	public static final float currentNumericModifier( final int index )
	{
		return KoLCharacter.currentModifiers.get( index );
	}

	public static final boolean currentBooleanModifier( final String name )
	{
		return KoLCharacter.currentModifiers.getBoolean( name );
	}

	public static final boolean currentBooleanModifier( final int index )
	{
		return KoLCharacter.currentModifiers.getBoolean( index );
	}

	/**
	 * Accessor method to retrieve the total current monster level adjustment
	 */

	public static final int getMonsterLevelAdjustment()
	{
		return (int) KoLCharacter.currentModifiers.get( Modifiers.MONSTER_LEVEL );
	}

	/**
	 * Accessor method to retrieve the total current familiar weight adjustment
	 */

	public static final int getFamiliarWeightAdjustment()
	{
		return (int) KoLCharacter.currentModifiers.get( Modifiers.FAMILIAR_WEIGHT );
	}

	public static final int getFamiliarWeightPercentAdjustment()
	{
		return (int) KoLCharacter.currentModifiers.get( Modifiers.FAMILIAR_WEIGHT_PCT );
	}

	public static final int getManaCostAdjustment()
	{
		return (int) KoLCharacter.currentModifiers.get( Modifiers.MANA_COST ) - KoLCharacter.holidayManaCostReduction;
	}

	/**
	 * Accessor method to retrieve the total current combat percent adjustment
	 */

	public static final float getCombatRateAdjustment()
	{
		return KoLCharacter.currentModifiers.get( Modifiers.COMBAT_RATE );
	}

	/**
	 * Accessor method to retrieve the total current initiative adjustment
	 */

	public static final float getInitiativeAdjustment()
	{
		return KoLCharacter.currentModifiers.get( Modifiers.INITIATIVE );
	}

	/**
	 * Accessor method to retrieve the total current fixed experience adjustment
	 */

	public static final float getExperienceAdjustment()
	{
		return KoLCharacter.currentModifiers.get( Modifiers.EXPERIENCE );
	}

	/**
	 * Accessor method to retrieve the total current meat drop percent adjustment
	 *
	 * @return Total Current Meat Drop Percent Adjustment
	 */

	public static final float getMeatDropPercentAdjustment()
	{
		return KoLCharacter.currentModifiers.get( Modifiers.MEATDROP );
	}

	/**
	 * Accessor method to retrieve the total current item drop percent adjustment
	 *
	 * @return Total Current Item Drop Percent Adjustment
	 */

	public static final float getItemDropPercentAdjustment()
	{
		return KoLCharacter.currentModifiers.get( Modifiers.ITEMDROP );
	}

	public static final void setEquipment( final int slot, AdventureResult item )
	{
		// Accessories are special in terms of testing for existence
		// in equipment lists -- they are all mirrors of accessories.

		switch ( slot )
		{
		case ACCESSORY1:
		case ACCESSORY2:
		case ACCESSORY3:
			int index = KoLCharacter.accessories.indexOf( item );
			if ( index == -1 )
			{
				KoLCharacter.accessories.add( item );
			}
			else
			{
				item = (AdventureResult) KoLCharacter.accessories.get( index );
			}
			break;

		default:
			if ( !KoLCharacter.equipmentLists[ slot ].contains( item ) )
			{
				KoLCharacter.equipmentLists[ slot ].add( item );
			}
			break;
		}

		KoLCharacter.equipment.set( slot, item );
		KoLCharacter.equipmentLists[ slot ].setSelectedItem( item );

		// Certain equipment slots require special update handling
		// in addition to the above code.

		switch ( slot )
		{
		case WEAPON:
		case OFFHAND:
			GearChangeFrame.updateWeapons();
			break;
		case FAMILIAR:
			if ( KoLCharacter.currentFamiliar.getId() > 0 )
			{
				KoLCharacter.currentFamiliar.setItem( item );
			}
			break;
		}

		// Certain items provide additional skills when equipped.
		// Handle the addition of those skills here.

		switch ( item.getItemId() )
		{
		case BOTTLE_ROCKET:
			KoLCharacter.addAvailableSkill( "Fire red bottle-rocket" );
			KoLCharacter.addAvailableSkill( "Fire blue bottle-rocket" );
			KoLCharacter.addAvailableSkill( "Fire orange bottle-rocket" );
			KoLCharacter.addAvailableSkill( "Fire purple bottle-rocket" );
			KoLCharacter.addAvailableSkill( "Fire black bottle-rocket" );
			break;
		case WIZARD_HAT:
			KoLCharacter.addAvailableSkill( "Magic Missile" );
			break;
		case BAKULA:
			KoLCharacter.addAvailableSkill( "Give In To Your Vampiric Urges" );
			break;
		}
	}

	/**
	 * Accessor method to set the equipment the character is currently using. This does not take into account the power
	 * of the item or anything of that nature; only the item's name is stored. Note that if no item is equipped, the
	 * value should be <code>none</code>, not <code>null</code> or the empty string.
	 *
	 * @param equipment All of the available equipment, stored in an array index by the constants
	 */

	public static final void setEquipment( final AdventureResult[] equipment )
	{
		for ( int i = KoLCharacter.HAT; i <= KoLCharacter.FAMILIAR; ++i )
		{
			if ( equipment[ i ] == null || equipment[ i ].equals( EquipmentRequest.UNEQUIP ) )
			{
				KoLCharacter.setEquipment( i, EquipmentRequest.UNEQUIP );
			}
			else
			{
				KoLCharacter.setEquipment( i, equipment[ i ] );
			}
		}
	}

	public static final void setOutfits( final List newOutfits )
	{
		// Rebuild outfits if given a new list
		if ( newOutfits != null )
		{
			KoLCharacter.customOutfits.clear();
			KoLCharacter.customOutfits.addAll( newOutfits );
		}

		EquipmentDatabase.updateOutfits();
	}

	/**
	 * Accessor method to retrieve the name of the item equipped on the character's familiar.
	 *
	 * @return The name of the item equipped on the character's familiar, <code>none</code> if no such item exists
	 */

	public static final AdventureResult getFamiliarItem()
	{
		return KoLCharacter.currentFamiliar == null ? EquipmentRequest.UNEQUIP : KoLCharacter.currentFamiliar.getItem();
	}

	/**
	 * Accessor method to retrieve the name of a piece of equipment
	 *
	 * @param type the type of equipment
	 * @return The name of the equipment, <code>none</code> if no such item exists
	 */

	public static final AdventureResult getEquipment( final int type )
	{
		if ( type >= 0 && type < KoLCharacter.equipment.size() )
		{
			return (AdventureResult) KoLCharacter.equipment.get( type );
		}

		if ( type == KoLCharacter.FAMILIAR )
		{
			return KoLCharacter.getFamiliarItem();
		}

		return EquipmentRequest.UNEQUIP;
	}

	public static final int getFakeHands()
	{
		return KoLCharacter.fakeHands;
	}

	public static final void setFakeHands( final int hands )
	{
		KoLCharacter.fakeHands = hands;
	}

	/**
	 * Accessor method to retrieve # of hands character's weapon uses
	 *
	 * @return int number of hands needed
	 */

	public static final int weaponHandedness()
	{
		return EquipmentDatabase.getHands( KoLCharacter.getEquipment( KoLCharacter.WEAPON ).getName() );
	}

	/**
	 * Accessor method to determine character's hit stat
	 *
	 * @return int MUSCLE, MYSTICALITY, MOXIE
	 */

	public static final int equipStat()
	{
		return EquipmentDatabase.equipStat( KoLCharacter.getEquipment( KoLCharacter.WEAPON ).getName() );
	}

	public static final int hitStat()
	{
		return KoLCharacter.rangedWeapon() ? KoLConstants.MOXIE : KoLConstants.MUSCLE;
	}

	/**
	 * Accessor method to determine character's adjusted hit stat
	 *
	 * @return int adjusted muscle, mysticality, or moxie
	 */

	public static final int getAdjustedHitStat()
	{
		switch ( KoLCharacter.hitStat() )
		{
		case MOXIE:
			return KoLCharacter.getAdjustedMoxie();
		case MYSTICALITY:
			return KoLCharacter.getAdjustedMysticality();
		default:
			return KoLCharacter.getAdjustedMuscle();
		}
	}

	/**
	 * Accessor method to determine if character's weapon is ranged
	 *
	 * @return boolean true if weapon is ranged
	 */

	public static final boolean rangedWeapon()
	{
		return EquipmentDatabase.isRanged( KoLCharacter.getEquipment( KoLCharacter.WEAPON ).getName() );
	}

	/**
	 * Accessor method to determine if character's weapon is a chefstaff
	 *
	 * @return boolean true if weapon is a chefstaff
	 */

	public static final boolean wieldingChefstaff()
	{
		return EquipmentDatabase.getType( KoLCharacter.getEquipment( KoLCharacter.WEAPON ).getName() ).equals(
			"chefstaff" );
	}

	/**
	 * Accessor method to retrieve the total current damage absorption
	 *
	 * @return Total Current Damage Absorption
	 */

	public static final int getDamageAbsorption()
	{
		return (int) KoLCharacter.currentModifiers.get( Modifiers.DAMAGE_ABSORPTION );
	}

	/**
	 * Accessor method to retrieve the total current damage reduction
	 *
	 * @return Total Current Damage Reduction
	 */

	public static final int getDamageReduction()
	{
		return (int) KoLCharacter.currentModifiers.get( Modifiers.DAMAGE_REDUCTION );
	}

	/**
	 * Accessor method to retrieve the current elemental resistance
	 *
	 * @return Total Current Resistance to specified element
	 */

	public static final float getElementalResistance( final int element )
	{
		switch ( element )
		{
		case MonsterDatabase.COLD:
			return KoLCharacter.currentModifiers.get( Modifiers.COLD_RESISTANCE );
		case MonsterDatabase.HEAT:
			return KoLCharacter.currentModifiers.get( Modifiers.HOT_RESISTANCE );
		case MonsterDatabase.SLEAZE:
			return KoLCharacter.currentModifiers.get( Modifiers.SLEAZE_RESISTANCE );
		case MonsterDatabase.SPOOKY:
			return KoLCharacter.currentModifiers.get( Modifiers.SPOOKY_RESISTANCE );
		case MonsterDatabase.STENCH:
			return KoLCharacter.currentModifiers.get( Modifiers.STENCH_RESISTANCE );
		}

		return 0.0f;
	}

	/**
	 * Accessor method to determine if character is currently dual-wielding
	 *
	 * @return boolean true if character has two weapons equipped
	 */

	public static final boolean dualWielding()
	{
		return EquipmentDatabase.getHands( KoLCharacter.getEquipment( KoLCharacter.OFFHAND ).getName() ) == 1;
	}

	/**
	 * Accessor method to retrieve a list of all available items which can be equipped by familiars. Note this lists
	 * items which the current familiar cannot equip.
	 */

	public static final LockableListModel[] getEquipmentLists()
	{
		return KoLCharacter.equipmentLists;
	}

	public static final int consumeFilterToEquipmentType( final int consumeFilter )
	{
		switch ( consumeFilter )
		{
		case EQUIP_HAT:
			return KoLCharacter.HAT;
		case EQUIP_WEAPON:
			return KoLCharacter.WEAPON;
		case EQUIP_OFFHAND:
			return KoLCharacter.OFFHAND;
		case EQUIP_SHIRT:
			return KoLCharacter.SHIRT;
		case EQUIP_PANTS:
			return KoLCharacter.PANTS;
		case EQUIP_ACCESSORY:
			return KoLCharacter.ACCESSORY1;
		case EQUIP_FAMILIAR:
			return KoLCharacter.FAMILIAR;
		default:
			return -1;
		}
	}

	public static final int equipmentTypeToConsumeFilter( final int equipmentType )
	{
		switch ( equipmentType )
		{
		case HAT:
			return KoLConstants.EQUIP_HAT;
		case WEAPON:
			return KoLConstants.EQUIP_WEAPON;
		case OFFHAND:
			return KoLConstants.EQUIP_OFFHAND;
		case SHIRT:
			return KoLConstants.EQUIP_SHIRT;
		case PANTS:
			return KoLConstants.EQUIP_PANTS;
		case ACCESSORY1:
		case ACCESSORY2:
		case ACCESSORY3:
			return KoLConstants.EQUIP_ACCESSORY;
		case FAMILIAR:
			return KoLConstants.EQUIP_FAMILIAR;
		default:
			return -1;
		}
	}

	public static final void updateEquipmentLists()
	{
		EquipmentDatabase.updateOutfits();
		for ( int i = 0; i <= KoLCharacter.FAMILIAR; ++i )
		{
			KoLCharacter.updateEquipmentList( i );
		}
	}

	public static final void updateEquipmentList( final int listIndex )
	{
		int consumeFilter = KoLCharacter.equipmentTypeToConsumeFilter( listIndex );
		if ( consumeFilter == -1 )
		{
			return;
		}

		AdventureResult equippedItem = KoLCharacter.getEquipment( listIndex );

		switch ( listIndex )
		{
		case ACCESSORY1:
		case ACCESSORY2:
		case ACCESSORY3:

			KoLCharacter.updateEquipmentList( consumeFilter, KoLCharacter.accessories );
			break;

		case FAMILIAR:

			// If we are looking at familiar items, include those which can
			// be universally equipped, but are currently on another
			// familiar.

			KoLCharacter.updateEquipmentList( consumeFilter, KoLCharacter.equipmentLists[ listIndex ] );

			FamiliarData[] familiarList = new FamiliarData[ KoLCharacter.familiars.size() ];
			KoLCharacter.familiars.toArray( familiarList );

			for ( int i = 0; i < familiarList.length; ++i )
			{
				if ( !familiarList[ i ].getItem().equals( EquipmentRequest.UNEQUIP ) )
				{
					AdventureResult.addResultToList(
						KoLCharacter.equipmentLists[ KoLCharacter.FAMILIAR ], familiarList[ i ].getItem() );
				}
			}

			break;

		default:

			KoLCharacter.updateEquipmentList( consumeFilter, KoLCharacter.equipmentLists[ listIndex ] );
			if ( !KoLCharacter.equipmentLists[ listIndex ].contains( equippedItem ) )
			{
				KoLCharacter.equipmentLists[ listIndex ].add( equippedItem );
			}

			break;
		}

		KoLCharacter.equipmentLists[ listIndex ].setSelectedItem( equippedItem );
	}

	private static final void updateEquipmentList( final int filterId, final List currentList )
	{
		ArrayList temporary = new ArrayList();
		temporary.add( EquipmentRequest.UNEQUIP );

		// If the character is currently equipped with a one-handed
		// weapon and the character has the ability to dual-wield
		// weapons, then also allow one-handed weapons in the off-hand.

		boolean dual = KoLCharacter.weaponHandedness() == 1 && KoLCharacter.hasSkill( "Double-Fisted Skull Smashing" );
		int equipStat = KoLCharacter.equipStat();

		for ( int i = 0; i < KoLConstants.inventory.size(); ++i )
		{
			AdventureResult currentItem = (AdventureResult) KoLConstants.inventory.get( i );
			String currentItemName = currentItem.getName();

			int type = TradeableItemDatabase.getConsumptionType( currentItem.getItemId() );

			// If we are equipping familiar items, make sure
			// current familiar can use this one

			if ( filterId == KoLConstants.EQUIP_FAMILIAR && type == KoLConstants.EQUIP_FAMILIAR )
			{
				temporary.add( currentItem );
				continue;
			}

			// If we want off-hand items and we can dual wield,
			// allow one-handed weapons of same type

			if ( filterId == KoLConstants.EQUIP_OFFHAND && type == KoLConstants.EQUIP_WEAPON && dual )
			{
				if ( EquipmentDatabase.getHands( currentItemName ) != 1 || EquipmentDatabase.equipStat( currentItemName ) != equipStat )
				{
					continue;
				}
			}

			// Otherwise, slot and item type must match

			else if ( filterId != type )
			{
				continue;
			}
			else if ( filterId == KoLConstants.EQUIP_WEAPON && dual )
			{
				if ( EquipmentDatabase.getHands( currentItemName ) == 1 && EquipmentDatabase.equipStat( currentItemName ) != equipStat )
				{
					continue;
				}
			}

			temporary.add( currentItem );
		}

		if ( currentList == KoLCharacter.accessories )
		{
			if ( !currentList.contains( KoLCharacter.getEquipment( KoLCharacter.ACCESSORY1 ) ) )
			{
				currentList.add( KoLCharacter.getEquipment( KoLCharacter.ACCESSORY1 ) );
			}
			if ( !currentList.contains( KoLCharacter.getEquipment( KoLCharacter.ACCESSORY2 ) ) )
			{
				currentList.add( KoLCharacter.getEquipment( KoLCharacter.ACCESSORY2 ) );
			}
			if ( !currentList.contains( KoLCharacter.getEquipment( KoLCharacter.ACCESSORY3 ) ) )
			{
				currentList.add( KoLCharacter.getEquipment( KoLCharacter.ACCESSORY3 ) );
			}
		}

		currentList.retainAll( temporary );
		temporary.removeAll( currentList );
		currentList.addAll( temporary );
	}

	private static final int getCount( final AdventureResult accessory )
	{
		int available = accessory.getCount( KoLConstants.inventory );
		if ( KoLCharacter.getEquipment( KoLCharacter.ACCESSORY1 ).equals( accessory ) )
		{
			++available;
		}
		if ( KoLCharacter.getEquipment( KoLCharacter.ACCESSORY2 ).equals( accessory ) )
		{
			++available;
		}
		if ( KoLCharacter.getEquipment( KoLCharacter.ACCESSORY3 ).equals( accessory ) )
		{
			++available;
		}

		return available;
	}

	/**
	 * Accessor method to retrieve a list of the custom outfits available to this character, based on the last time the
	 * equipment screen was requested.
	 *
	 * @return A <code>LockableListModel</code> of the available outfits
	 */

	public static final LockableListModel getCustomOutfits()
	{
		return KoLCharacter.customOutfits;
	}

	/**
	 * Accessor method to retrieve a list of the all the outfits available to this character, based on the last time the
	 * equipment screen was requested.
	 *
	 * @return A <code>LockableListModel</code> of the available outfits
	 */

	public static final LockableListModel getOutfits()
	{
		return KoLCharacter.outfits;
	}

	/**
	 * Accessor method which indicates whether or not the the beanstalk has been armed this session.
	 *
	 * @return <code>true</code> if the beanstalk has been armed
	 */

	public static final boolean beanstalkArmed()
	{
		return KoLCharacter.beanstalkArmed;
	}

	/**
	 * Accessor method to indicate a change in state of the beanstalk
	 */

	public static final void armBeanstalk()
	{
		KoLCharacter.beanstalkArmed = true;
	}

	/**
	 * Accessor method which indicates whether or not the character has store in the mall
	 *
	 * @return <code>true</code> if the character has a store
	 */

	public static final boolean hasStore()
	{
		return KoLCharacter.hasStore;
	}

	/**
	 * Accessor method to indicate a change in state of the mall store.
	 *
	 * @param hasStore Whether or not the character currently has a store
	 */

	public static final void setStore( final boolean hasStore )
	{
		KoLCharacter.hasStore = hasStore;
	}

	/**
	 * Accessor method which indicates whether or not the character has display case
	 *
	 * @return <code>true</code> if the character has a display case
	 */

	public static final boolean hasDisplayCase()
	{
		return KoLCharacter.hasDisplayCase;
	}

	/**
	 * Accessor method to indicate a change in state of the museum display case
	 *
	 * @param hasDisplayCase Whether or not the character currently has display case
	 */

	public static final void setDisplayCase( final boolean hasDisplayCase )
	{
		KoLCharacter.hasDisplayCase = hasDisplayCase;
	}

	/**
	 * Accessor method which indicates whether or not the character is in a clan
	 *
	 * @return <code>true</code> if the character is in a clan
	 */

	public static final boolean hasClan()
	{
		return KoLCharacter.hasClan;
	}

	/**
	 * Accessor method to indicate a change in state of the character's clan membership
	 *
	 * @param hasClan Whether or not the character currently is in a clan
	 */

	public static final void setClan( final boolean hasClan )
	{
		KoLCharacter.hasClan = hasClan;
	}

	/**
	 * Accessor method which indicates whether or not the character has a toaster
	 *
	 * @return <code>true</code> if the character has a toaster
	 */

	public static final boolean hasToaster()
	{
		return KoLCharacter.hasToaster;
	}

	/**
	 * Accessor method to indicate a change in state of the toaster.
	 *
	 * @param hasToaster Whether or not the character currently has a toaster
	 */

	public static final void setToaster( final boolean hasToaster )
	{
		KoLCharacter.hasToaster = hasToaster;
	}

	/**
	 * Accessor method which indicates whether or not the character has golden arches
	 *
	 * @return <code>true</code> if the character has golden arches
	 */

	public static final boolean hasArches()
	{
		return KoLCharacter.hasArches;
	}

	/**
	 * Accessor method to indicate a change in state of the golden arches.
	 *
	 * @param hasArches Whether or not the character currently has golden arches
	 */

	public static final void setArches( final boolean hasArches )
	{
		KoLCharacter.hasArches = hasArches;
	}

	/**
	 * Accessor method which indicates whether or not the character has a bartender-in-the-box.
	 *
	 * @return <code>true</code> if the character has a bartender-in-the-box
	 */

	public static final boolean hasBartender()
	{
		return KoLCharacter.hasBartender;
	}

	/**
	 * Accessor method to indicate a change in state of the bartender-in-the-box.
	 *
	 * @param hasBartender Whether or not the character currently has a bartender
	 */

	public static final void setBartender( final boolean hasBartender )
	{
		if ( KoLCharacter.hasBartender != hasBartender )
		{
			KoLCharacter.hasBartender = hasBartender;
			ConcoctionsDatabase.refreshConcoctions();
		}
	}

	/**
	 * Accessor method which indicates whether or not the character has a chef-in-the-box.
	 *
	 * @return <code>true</code> if the character has a chef-in-the-box
	 */

	public static final boolean hasChef()
	{
		return KoLCharacter.hasChef;
	}

	/**
	 * Accessor method to indicate a change in state of the chef-in-the-box.
	 *
	 * @param hasChef Whether or not the character currently has a chef
	 */

	public static final void setChef( final boolean hasChef )
	{
		if ( KoLCharacter.hasChef != hasChef )
		{
			KoLCharacter.hasChef = hasChef;
			ConcoctionsDatabase.refreshConcoctions();
		}
	}

	/**
	 * Accessor method which indicates whether or not the character has a mystical bookshelf
	 *
	 * @return <code>true</code> if the character has a mystical bookshelf
	 */

	public static final boolean hasBookshelf()
	{
		return KoLCharacter.hasBookshelf;
	}

	/**
	 * Accessor method to indicate a change in state of the mystical bookshelf
	 *
	 * @param hasBookshelf Whether or not the character currently has a bookshelf
	 */

	public static final void setBookshelf( final boolean hasBookshelf )
	{
		KoLCharacter.hasBookshelf = hasBookshelf;
		if ( hasBookshelf && KoLCharacter.hasBookshelf != hasBookshelf)
		{
			RequestThread.postRequest( new CampgroundRequest( "bookshelf" ) );
		}
	}

	/**
	 * Accessor method which indicates how many times the character has upgraded their telescope
	 *
	 * @return <code>int/code> power of telescope
	 */

	public static final int getTelescopeUpgrades()
	{
		return KoLCharacter.telescopeUpgrades;
	}

	/**
	 * Accessor method to indicate a change in state of the telescope
	 */

	public static final void setTelescopeUpgrades( final int upgrades )
	{
		KoLCharacter.telescopeUpgrades = upgrades;
	}

	/**
	 * Accessor method to indicate a change in state of the telescope
	 */

	public static final void setTelescope( final boolean present )
	{
		KoLCharacter.telescopeUpgrades = KoLSettings.getIntegerProperty( "telescopeUpgrades" );
		// Assume newly detected telescope is basic. We'll look through
		// it when checkTelescope is called.
		if ( present && KoLCharacter.telescopeUpgrades == 0 )
		{
			KoLCharacter.telescopeUpgrades = 1;
		}
	}

	/**
	 * Method to look through the telescope if it hasn't been done yet
	 */

	public static final void checkTelescope()
	{
		if ( KoLCharacter.telescopeUpgrades == 0 )
		{
			return;
		}

		if ( KoLCharacter.ascensionSignType == KoLConstants.BAD_MOON )
		{
			return;
		}

		int lastAscension = KoLSettings.getIntegerProperty( "lastTelescopeReset" );
		if ( lastAscension < KoLCharacter.ascensions )
		{
			RequestThread.postRequest( new TelescopeRequest( TelescopeRequest.LOW ) );
		}
	}

	/**
	 * Accessor method which indicates whether or not the character has freed King Ralph
	 *
	 * @return <code>true</code> if the character has freed King Ralph
	 */

	public static final boolean kingLiberated()
	{
		int lastAscension = KoLSettings.getIntegerProperty( "lastKingLiberation" );
		if ( lastAscension < KoLCharacter.ascensions )
		{
			KoLSettings.setUserProperty( "lastKingLiberation", String.valueOf( KoLCharacter.getAscensions() ) );
			KoLSettings.setUserProperty( "kingLiberated", "false" );
			return false;
		}
		return KoLSettings.getBooleanProperty( "kingLiberated" );
	}

	public static final void liberateKing()
	{
		KoLSettings.setUserProperty( "lastKingLiberation", String.valueOf( KoLCharacter.getAscensions() ) );
		KoLSettings.setUserProperty( "kingLiberated", "true" );
		CharpaneRequest.setInteraction();
	}

	/**
	 * Accessor method which tells you if the character can interact with other players (Ronin or Hardcore players
	 * cannot).
	 */

	public static final boolean canInteract()
	{
		return CharpaneRequest.canInteract();
	}

	/**
	 * Returns whether or not the character is currently in hardcore.
	 */

	public static final boolean isHardcore()
	{
		return KoLCharacter.isHardcore;
	}

	/**
	 * Accessor method which sets whether or not the player is currently in hardcore.
	 */

	public static final void setHardcore( final boolean isHardcore )
	{
		KoLCharacter.isHardcore = isHardcore;
	}

	/**
	 * Returns whether or not the character is currently in roin.
	 */

	public static final boolean inRonin()
	{
		return KoLCharacter.inRonin;
	}

	/**
	 * Accessor method which sets whether or not the player is currently in ronin.
	 */

	public static final void setRonin( final boolean inRonin )
	{
		KoLCharacter.inRonin = inRonin;
	}

	/**
	 * Accessor method for the character's ascension count
	 *
	 * @return String
	 */

	public static final int getAscensions()
	{
		return KoLCharacter.ascensions;
	}

	/**
	 * Accessor method for the character's zodiac sign
	 *
	 * @return String
	 */

	public static final String getSign()
	{
		return KoLCharacter.ascensionSign;
	}

	/**
	 * Accessor method for the character's zodiac sign stat
	 *
	 * @return int
	 */

	public static final int getSignStat()
	{
		return KoLCharacter.ascensionSignType;
	}

	/**
	 * Accessor method to set a character's ascension count
	 *
	 * @param ascensions the new ascension count
	 */

	public static final void setAscensions( final int ascensions )
	{
		KoLCharacter.ascensions = ascensions;
	}

	/**
	 * Accessor method to set a character's zodiac sign
	 *
	 * @param ascensionSign the new sign
	 */

	public static final void setSign( String ascensionSign )
	{
		if ( ascensionSign.startsWith( "The " ) )
		{
			ascensionSign = ascensionSign.substring( 4 );
		}

		KoLCharacter.ascensionSign = ascensionSign;

		if ( ascensionSign.equals( "Wallaby" ) || ascensionSign.equals( "Mongoose" ) || ascensionSign.equals( "Vole" ) )
		{
			KoLCharacter.ascensionSignType = KoLConstants.MUSCLE;
		}
		else if ( ascensionSign.equals( "Platypus" ) || ascensionSign.equals( "Opossum" ) || ascensionSign.equals( "Marmot" ) )
		{
			KoLCharacter.ascensionSignType = KoLConstants.MYSTICALITY;
		}
		else if ( ascensionSign.equals( "Wombat" ) || ascensionSign.equals( "Blender" ) || ascensionSign.equals( "Packrat" ) )
		{
			KoLCharacter.ascensionSignType = KoLConstants.MOXIE;
		}
		else if ( ascensionSign.equals( "Bad Moon" ) )
		{
			KoLCharacter.ascensionSignType = KoLConstants.BAD_MOON;
		}
		else
		{
			KoLCharacter.ascensionSignType = KoLConstants.NONE;
		}
	}

	public static final int getConsumptionRestriction()
	{
		return KoLCharacter.consumptionRestriction;
	}

	public static final void setConsumptionRestriction( final int consumptionRestriction )
	{
		KoLCharacter.consumptionRestriction = consumptionRestriction;
	}

	public static final boolean canEat()
	{
		return KoLCharacter.consumptionRestriction == AscensionSnapshotTable.NOPATH || KoLCharacter.consumptionRestriction == AscensionSnapshotTable.TEETOTALER;
	}

	public static final boolean canDrink()
	{
		return KoLCharacter.consumptionRestriction == AscensionSnapshotTable.NOPATH || KoLCharacter.consumptionRestriction == AscensionSnapshotTable.BOOZETAFARIAN;
	}

	/**
	 * Accessor method for the current mind control setting
	 *
	 * @return int
	 */

	public static final int getMindControlLevel()
	{
		return KoLCharacter.mindControlLevel;
	}

	/**
	 * Accessor method to set the current mind control level
	 *
	 * @param level the new level
	 */

	public static final void setMindControlLevel( final int level )
	{
		KoLCharacter.mindControlLevel = level;
		KoLCharacter.recalculateAdjustments();
		KoLCharacter.updateStatus();
	}

	/**
	 * Accessor method for the current detuned radio volume
	 *
	 * @return int
	 */

	public static final int getDetunedRadioVolume()
	{
		return KoLCharacter.detunedRadioVolume;
	}

	/**
	 * Accessor method to set the current detuned radio volume
	 *
	 * @param volume the new level
	 */

	public static final void setDetunedRadioVolume( final int volume )
	{
		KoLCharacter.detunedRadioVolume = volume;
		KoLCharacter.recalculateAdjustments();
		KoLCharacter.updateStatus();
	}

	/**
	 * Accessor method for the current Annoyotron level
	 *
	 * @return int
	 */

	public static final int getAnnoyotronLevel()
	{
		return KoLCharacter.annoyotronLevel;
	}

	/**
	 * Accessor method to set the current Annoyotron level
	 *
	 * @param volume the new level
	 */

	public static final void setAnnoyotronLevel( final int level )
	{
		KoLCharacter.annoyotronLevel = level;
		KoLCharacter.recalculateAdjustments();
		KoLCharacter.updateStatus();
	}

	/**
	 * Accessor method for the current sign-specific monster level modifier
	 *
	 * @return int
	 */

	public static final int getSignedMLAdjustment()
	{
		if ( KoLCharacter.mindControlLevel > 0 )
		{
			return KoLCharacter.mindControlLevel;
		}
		if ( KoLCharacter.detunedRadioVolume > 0 )
		{
			return KoLCharacter.detunedRadioVolume;
		}
		if ( KoLCharacter.annoyotronLevel > 0 )
		{
			return KoLCharacter.annoyotronLevel;
		}
		return 0;
	}

	/**
	 * Accessor method for the current autosell mode
	 *
	 * @return String
	 */

	public static final String getAutosellMode()
	{
		return KoLCharacter.autosellMode;
	}

	/**
	 * Accessor method to set the autosellmode
	 *
	 * @param mode the new mode
	 */

	public static final void setAutosellMode( final String mode )
	{
		KoLCharacter.autosellMode = mode;
	}

	/**
	 * Accessor method which indicates whether the character is in a Muscle sign KoLmafia could/should use this to: -
	 * Allow adventuring in The Bugbear Pens - Provide access to npcstore #4: The Degrassi Knoll Bakery - Provide access
	 * to npcstore #5: The Degrassi Knoll General Store - Train Muscle in The Gym - Smith non-advanced things using
	 * Innabox (no hammer/adventure) - Combine anything using The Plunger (no meat paste)
	 *
	 * @return <code>true</code> if the character is in a Muscle sign
	 */

	public static final boolean inMuscleSign()
	{
		return KoLCharacter.ascensionSignType == KoLConstants.MUSCLE;
	}

	/**
	 * Accessor method which indicates whether the character is in a Mysticality sign KoLmafia could/should use this to: -
	 * Allow adventuring in Outskirts of Camp Logging Camp - Allow adventuring in Camp Logging Camp - Provide access to
	 * npcstore #j: Little Canadia Jewelers - Train Mysticality in The Institute for Canadian Studies
	 *
	 * @return <code>true</code> if the character is in a Mysticality sign
	 */

	public static final boolean inMysticalitySign()
	{
		return KoLCharacter.ascensionSignType == KoLConstants.MYSTICALITY;
	}

	/**
	 * Accessor method which indicates whether the character is in a Moxie sign KoLmafia could/should use this to: -
	 * Allow adventuring in Thugnderdome - Provide access to TINKER recipes - Train Moxie with Gnirf
	 *
	 * @return <code>true</code> if the character is in a Moxie sign
	 */

	public static final boolean inMoxieSign()
	{
		return KoLCharacter.ascensionSignType == KoLConstants.MOXIE;
	}

	/**
	 * Accessor method which indicates whether the character is in Bad Moon KoLmafia could/should use this to: -
	 * Eliminate access to Hagnks - Provide access to Hell's Kitchen - Provide access to Nervewrecker's Store
	 *
	 * @return <code>true</code> if the character is in a Moxie sign
	 */

	public static final boolean inBadMoon()
	{
		return KoLCharacter.ascensionSignType == KoLConstants.BAD_MOON;
	}

	/**
	 * Accessor method to set the list of available skills.
	 *
	 * @param newSkillSet The list of the names of available skills
	 */

	public static final void setAvailableSkills( final List newSkillSet )
	{
		if ( KoLCharacter.isMoxieClass() )
		{
			KoLCharacter.addAvailableSkill( "Moxious Maneuver" );
		}

		// Check all available skills to see if they
		// qualify to be added as combat or usables.

		for ( int i = 0; i < newSkillSet.size(); ++i )
		{
			KoLCharacter.addAvailableSkill( (UseSkillRequest) newSkillSet.get( i ) );
		}

		// Add derived skills based on base skills

		KoLCharacter.addDerivedSkills();
		KoLConstants.usableSkills.sort();
		KoLConstants.summoningSkills.sort();
		KoLConstants.remedySkills.sort();
		KoLConstants.selfOnlySkills.sort();
		KoLConstants.buffSkills.sort();

		KoLCharacter.battleSkillNames.setSelectedItem( KoLSettings.getUserProperty( "battleAction" ) );
	}

	/**
	 * Adds a single skill to the list of known skills possessed by this character.
	 */

	public static final void addAvailableSkill( final UseSkillRequest skill )
	{
		if ( skill == null )
		{
			return;
		}

		if ( KoLConstants.availableSkills.contains( skill ) )
		{
			return;
		}

		KoLConstants.availableSkills.add( skill );

		switch ( ClassSkillsDatabase.getSkillType( skill.getSkillId() ) )
		{
		case ClassSkillsDatabase.PASSIVE:

			// Flavour of Magic gives you access to five other
			// castable skills

			if ( skill.getSkillName().equals( "Flavour of Magic" ) )
			{
				UseSkillRequest use = UseSkillRequest.getInstance( "Spirit of Cayenne" );
				KoLConstants.usableSkills.add( use );
				KoLConstants.selfOnlySkills.add( use );
				use = UseSkillRequest.getInstance( "Spirit of Peppermint" );
				KoLConstants.usableSkills.add( use );
				KoLConstants.selfOnlySkills.add( use );
				use = UseSkillRequest.getInstance( "Spirit of Garlic" );
				KoLConstants.usableSkills.add( use );
				KoLConstants.selfOnlySkills.add( use );
				use = UseSkillRequest.getInstance( "Spirit of Wormwood" );
				KoLConstants.usableSkills.add( use );
				KoLConstants.selfOnlySkills.add( use );
				use = UseSkillRequest.getInstance( "Spirit of Bacon Grease" );
				KoLConstants.usableSkills.add( use );
				KoLConstants.selfOnlySkills.add( use );
				KoLConstants.usableSkills.sort();
				KoLConstants.selfOnlySkills.sort();
			}

			break;

		case ClassSkillsDatabase.SUMMON:
			KoLConstants.usableSkills.add( skill );
			KoLConstants.usableSkills.sort();
			KoLConstants.summoningSkills.add( skill );
			KoLConstants.summoningSkills.sort();
			break;

		case ClassSkillsDatabase.REMEDY:
			KoLConstants.usableSkills.add( skill );
			KoLConstants.usableSkills.sort();
			KoLConstants.remedySkills.add( skill );
			KoLConstants.remedySkills.sort();
			break;

		case ClassSkillsDatabase.SELF_ONLY:
			KoLConstants.usableSkills.add( skill );
			KoLConstants.usableSkills.sort();
			KoLConstants.selfOnlySkills.add( skill );
			KoLConstants.selfOnlySkills.sort();
			break;

		case ClassSkillsDatabase.BUFF:

			KoLConstants.usableSkills.add( skill );
			KoLConstants.usableSkills.sort();
			KoLConstants.buffSkills.add( skill );
			KoLConstants.buffSkills.sort();
			break;

		case ClassSkillsDatabase.COMBAT:

			KoLCharacter.addCombatSkill( skill.getSkillName() );
			break;
		}
	}

	public static final void addAvailableSkill( final String name )
	{
		KoLCharacter.addAvailableSkill( UseSkillRequest.getInstance( name ) );
	}

	/**
	 * Adds derived skills to appropriate lists
	 */

	public static final void addDerivedSkills()
	{
		if ( KoLCharacter.classtype.startsWith( "Tu" ) )
		{
			boolean head = KoLCharacter.hasSkill( "Headbutt" );
			boolean knee = KoLCharacter.hasSkill( "Kneebutt" );
			boolean shield = KoLCharacter.hasSkill( "Shieldbutt" );

			if ( head && knee )
			{
				KoLCharacter.addCombatSkill( "Head + Knee Combo" );
			}
			if ( head && shield )
			{
				KoLCharacter.addCombatSkill( "Head + Shield Combo" );
			}
			if ( knee && shield )
			{
				KoLCharacter.addCombatSkill( "Knee + Shield Combo" );
			}
			if ( head && knee && shield )
			{
				KoLCharacter.addCombatSkill( "Head + Knee + Shield Combo" );
			}
		}
	}

	private static final void addCombatSkill( final String name )
	{
		String skillname = "skill " + name.toLowerCase();
		if ( !KoLCharacter.battleSkillNames.contains( skillname ) )
		{
			KoLCharacter.battleSkillNames.add( skillname );
		}
	}

	/**
	 * Returns a list of the names of all available combat skills. The selected index in this list should match the
	 * selected index in the battle skills list.
	 */

	public static final LockableListModel getBattleSkillNames()
	{
		return KoLCharacter.battleSkillNames;
	}

	/**
	 * Accessor method to look up whether or not the character can summon noodles.
	 *
	 * @return <code>true</code> if noodles can be summoned by this character
	 */

	public static final boolean canSummonNoodles()
	{
		return KoLCharacter.hasSkill( "Pastamastery" );
	}

	/**
	 * Accessor method to look up whether or not the character can summon reagent.
	 *
	 * @return <code>true</code> if reagent can be summoned by this character
	 */

	public static final boolean canSummonReagent()
	{
		return KoLCharacter.hasSkill( "Advanced Saucecrafting" );
	}

	/**
	 * Accessor method to look up whether or not the character can summon shore-based items.
	 *
	 * @return <code>true</code> if shore-based items can be summoned by this character
	 */

	public static final boolean canSummonShore()
	{
		return KoLCharacter.hasSkill( "Advanced Cocktailcrafting" );
	}

	/**
	 * Accessor method to look up whether or not the character can summon snowcones
	 *
	 * @return <code>true</code> if snowcones can be summoned by this character
	 */

	public static final boolean canSummonSnowcones()
	{
		return KoLCharacter.hasSkill( "Summon Snowcone" );
	}

	/**
	 * Accessor method to look up whether or not the character can smith weapons.
	 *
	 * @return <code>true</code> if this character can smith advanced weapons
	 */

	public static final boolean canSmithWeapons()
	{
		return KoLCharacter.hasSkill( "Super-Advanced Meatsmithing" );
	}

	/**
	 * Accessor method to look up whether or not the character can smith armor.
	 *
	 * @return <code>true</code> if this character can smith advanced armor
	 */

	public static final boolean canSmithArmor()
	{
		return KoLCharacter.hasSkill( "Armorcraftiness" );
	}

	/**
	 * Accessor method to look up whether or not the character can craft expensive jewelry
	 *
	 * @return <code>true</code> if this character can smith advanced weapons
	 */

	public static final boolean canCraftExpensiveJewelry()
	{
		return KoLCharacter.hasSkill( "Really Expensive Jewelrycrafting" );
	}

	/**
	 * Accessor method to look up whether or not the character has Amphibian Sympathy
	 *
	 * @return <code>true</code> if this character has Amphibian Sympathy
	 */

	public static final boolean hasAmphibianSympathy()
	{
		return KoLCharacter.hasSkill( "Amphibian Sympathy" );
	}

	/**
	 * Utility method which looks up whether or not the character has a skill of the given name.
	 */

	public static final boolean hasSkill( final int skillId )
	{
		return KoLCharacter.hasSkill( ClassSkillsDatabase.getSkillName( skillId ) );
	}

	public static final boolean hasSkill( final String skillName )
	{
		return KoLCharacter.hasSkill( skillName, KoLConstants.availableSkills ) || KoLCharacter.hasSkill(
			skillName, KoLConstants.usableSkills );
	}

	public static final boolean hasSkill( final String skillName, final LockableListModel list )
	{
		for ( int i = 0; i < list.size(); ++i )
		{
			if ( ( (UseSkillRequest) list.get( i ) ).getSkillName().equalsIgnoreCase( skillName ) )
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Accessor method to get the current familiar.
	 *
	 * @return familiar The current familiar
	 */

	public static final FamiliarData getFamiliar()
	{
		return KoLCharacter.currentFamiliar == null ? FamiliarData.NO_FAMILIAR : KoLCharacter.currentFamiliar;
	}

	public static final boolean isUsingStabBat()
	{
		return KoLCharacter.isUsingStabBat;
	}

	/**
	 * Accessor method to get arena wins
	 *
	 * @return The number of arena wins
	 */

	public static final int getArenaWins()
	{
		// Ensure that the arena opponent list is
		// initialized.

		CakeArenaManager.getOpponentList();
		return KoLCharacter.arenaWins;
	}

	public static final int getStillsAvailable()
	{
		if ( !KoLCharacter.hasSkill( "Superhuman Cocktailcrafting" ) || !KoLCharacter.isMoxieClass() )
		{
			return 0;
		}

		if ( KoLCharacter.stillsAvailable == -1 )
		{
			KoLRequest stillChecker = new KoLRequest( "guild.php?place=still" );
			RequestThread.postRequest( stillChecker );

			Matcher stillMatcher = KoLCharacter.STILLS_PATTERN.matcher( stillChecker.responseText );
			if ( stillMatcher.find() )
			{
				KoLCharacter.stillsAvailable = StaticEntity.parseInt( stillMatcher.group( 1 ) );
			}
			else
			{
				KoLCharacter.stillsAvailable = 0;
			}
		}

		return KoLCharacter.stillsAvailable;
	}

	public static final void decrementStillsAvailable( final int decrementAmount )
	{
		KoLCharacter.stillsAvailable -= decrementAmount;
		ConcoctionsDatabase.refreshConcoctions();
	}

	public static final boolean canUseWok()
	{
		return KoLCharacter.hasSkill( "Transcendental Noodlecraft" ) && KoLCharacter.isMysticalityClass();
	}

	public static final boolean canUseMalus()
	{
		return KoLCharacter.hasSkill( "Pulverize" ) && KoLCharacter.isMuscleClass();
	}

	/**
	 * Accessor method to set arena wins
	 *
	 * @param wins The number of arena wins
	 */

	public static final void setArenaWins( final int wins )
	{
		KoLCharacter.arenaWins = wins;
	}

	/**
	 * Accessor method to find the specified familiar.
	 *
	 * @param race The race of the familiar to find
	 * @return familiar The first familiar matching this race
	 */

	public static final FamiliarData findFamiliar( final String race )
	{
		FamiliarData[] familiarArray = new FamiliarData[ KoLCharacter.familiars.size() ];
		KoLCharacter.familiars.toArray( familiarArray );

		for ( int i = 0; i < familiarArray.length; ++i )
		{
			if ( race.equals( familiarArray[ i ].getRace() ) )
			{
				return familiarArray[ i ];
			}
		}

		return null;
	}

	/**
	 * Accessor method to set the data for the current familiar.
	 *
	 * @param familiar The new current familiar
	 */

	public static final void setFamiliar( final FamiliarData familiar )
	{
		KoLCharacter.currentFamiliar = KoLCharacter.addFamiliar( familiar );

		KoLCharacter.familiars.setSelectedItem( KoLCharacter.currentFamiliar );
		KoLCharacter.equipmentLists[ KoLCharacter.FAMILIAR ].setSelectedItem( KoLCharacter.currentFamiliar.getItem() );

		KoLCharacter.isUsingStabBat =
			KoLCharacter.currentFamiliar.getRace().equals( "Stab Bat" ) || KoLCharacter.currentFamiliar.getRace().equals(
				"Scary Death Orb" );

		KoLCharacter.recalculateAdjustments();
		KoLCharacter.updateStatus();
	}

	/**
	 * Accessor method to increment the weight of the current familiar by one.
	 */

	public static final void incrementFamilarWeight()
	{
		if ( KoLCharacter.currentFamiliar != null )
		{
			KoLCharacter.currentFamiliar.setWeight( KoLCharacter.currentFamiliar.getWeight() + 1 );
		}
	}

	/**
	 * Adds the given familiar to the list of available familiars.
	 *
	 * @param familiar The Id of the familiar to be added
	 */

	public static final FamiliarData addFamiliar( final FamiliarData familiar )
	{
		if ( familiar == null )
		{
			return null;
		}

		int index = KoLCharacter.familiars.indexOf( familiar );
		if ( index >= 0 )
		{
			return (FamiliarData) KoLCharacter.familiars.get( index );
		}

		KoLCharacter.familiars.add( familiar );
		if ( !familiar.getItem().equals( EquipmentRequest.UNEQUIP ) )
		{
			AdventureResult.addResultToList( KoLCharacter.equipmentLists[ KoLCharacter.FAMILIAR ], familiar.getItem() );
		}

		return familiar;
	}

	/**
	 * Remove the given familiar from the list of available familiars.
	 *
	 * @param familiar The Id of the familiar to be added
	 */

	public static final void removeFamiliar( final FamiliarData familiar )
	{
		if ( familiar == null )
		{
			return;
		}

		int index = KoLCharacter.familiars.indexOf( familiar );
		if ( index < 0 )
		{
			return;
		}

		if ( KoLCharacter.currentFamiliar == familiar )
		{
			KoLCharacter.currentFamiliar = FamiliarData.NO_FAMILIAR;
			KoLCharacter.setEquipment( KoLCharacter.FAMILIAR, EquipmentRequest.UNEQUIP );
		}

		KoLCharacter.familiars.remove( familiar );
	}

	/**
	 * Returns the list of familiars available to the character.
	 *
	 * @return The list of familiars available to the character
	 */

	public static final LockableListModel getFamiliarList()
	{
		return KoLCharacter.familiars;
	}

	/**
	 * Returns the string used on the character pane to detrmine how many points remain until the character's next
	 * level.
	 *
	 * @return The string indicating the TNP advancement
	 */

	public static final String getAdvancement()
	{
		int level = KoLCharacter.getLevel();
		return KoLConstants.COMMA_FORMAT.format( level * level + 4 - KoLCharacter.calculateBasePoints( KoLCharacter.getTotalPrime() ) ) + " " + AdventureResult.STAT_NAMES[ KoLCharacter.getPrimeIndex() ] + " until level " + ( level + 1 );
	}

	/**
	 * Adds a new <code>KoLCharacterListener</code> to the list of listeners listening to this
	 * <code>KoLCharacter</code>.
	 *
	 * @param listener The listener to be added to the listener list
	 */

	public static final void addCharacterListener( final KoLCharacterListener listener )
	{
		if ( listener != null && !KoLCharacter.listenerList.contains( listener ) )
		{
			KoLCharacter.listenerList.add( listener );
		}
	}

	/**
	 * Removes an existing <code>KoLCharacterListener</code> from the list of listeners listening to this
	 * <code>KoLCharacter</code>.
	 *
	 * @param listener The listener to be removed from the listener list
	 */

	public static final void removeCharacterListener( final KoLCharacterListener listener )
	{
		if ( listener != null )
		{
			KoLCharacter.listenerList.remove( listener );
		}
	}

	public static final void processResult( final AdventureResult result )
	{
		KoLCharacter.processResult( result, true );
	}

	/**
	 * Processes a result received through adventuring. This places items inside of inventories and lots of other good
	 * stuff.
	 */

	public static final void processResult( final AdventureResult result, final boolean updateCalculatedLists )
	{
		// Treat the result as normal from this point forward.
		// Figure out which list the skill should be added to
		// and add it to that list.

		String resultName = result.getName();
		if ( resultName == null )
		{
			return;
		}

		if ( result.isItem() )
		{
			AdventureResult.addResultToList( KoLConstants.inventory, result );

			if ( updateCalculatedLists )
			{
				int consumeType = TradeableItemDatabase.getConsumptionType( result.getItemId() );

				if ( consumeType == KoLConstants.EQUIP_ACCESSORY )
				{
					AdventureResult.addResultToList( KoLCharacter.accessories, result );
				}
				else
				{
					int equipmentType = KoLCharacter.consumeFilterToEquipmentType( consumeType );
					if ( equipmentType != -1 )
					{
						AdventureResult.addResultToList( KoLCharacter.equipmentLists[ equipmentType ], result );
					}

					if ( equipmentType == KoLCharacter.WEAPON || equipmentType == KoLCharacter.OFFHAND )
					{
						GearChangeFrame.updateWeapons();
					}
				}

				if ( EquipmentDatabase.getOutfitWithItem( result.getItemId() ) != -1 )
				{
					EquipmentDatabase.updateOutfits();
				}

				boolean shouldRefresh = false;
				List uses = ConcoctionsDatabase.getKnownUses( result );

				for ( int i = 0; i < uses.size() && !shouldRefresh; ++i )
				{
					shouldRefresh =
						ConcoctionsDatabase.isPermittedMethod( ConcoctionsDatabase.getMixingMethod( ( (AdventureResult) uses.get( i ) ).getItemId() ) );
				}

				if ( shouldRefresh )
				{
					ConcoctionsDatabase.refreshConcoctions();
				}
				else if ( consumeType == KoLConstants.CONSUME_EAT || consumeType == KoLConstants.CONSUME_DRINK )
				{
					ConcoctionsDatabase.refreshConcoctions();
				}
			}
		}
		else if ( resultName.equals( AdventureResult.HP ) )
		{
			KoLCharacter.setHP(
				KoLCharacter.getCurrentHP() + result.getCount(), KoLCharacter.getMaximumHP(),
				KoLCharacter.getBaseMaxHP() );
		}
		else if ( resultName.equals( AdventureResult.MP ) )
		{
			KoLCharacter.setMP(
				KoLCharacter.getCurrentMP() + result.getCount(), KoLCharacter.getMaximumMP(),
				KoLCharacter.getBaseMaxMP() );
		}
		else if ( resultName.equals( AdventureResult.MEAT ) )
		{
			KoLCharacter.setAvailableMeat( KoLCharacter.getAvailableMeat() + result.getCount() );
		}
		else if ( resultName.equals( AdventureResult.ADV ) )
		{
			KoLCharacter.setAdventuresLeft( KoLCharacter.getAdventuresLeft() + result.getCount() );
			if ( result.getCount() < 0 )
			{
				AdventureResult[] effectsArray = new AdventureResult[ KoLConstants.activeEffects.size() ];
				KoLConstants.activeEffects.toArray( effectsArray );

				for ( int i = effectsArray.length - 1; i >= 0; --i )
				{
					AdventureResult effect = effectsArray[ i ];
					if ( effect.getCount() + result.getCount() <= 0 )
					{
						KoLConstants.activeEffects.remove( i );
					}
					else
					{
						KoLConstants.activeEffects.set( i, effect.getInstance( effect.getCount() + result.getCount() ) );
					}
				}

				KoLCharacter.setCurrentRun( KoLCharacter.currentRun - result.getCount() );
			}
		}
		else if ( resultName.equals( AdventureResult.DRUNK ) )
		{
			KoLCharacter.setInebriety( KoLCharacter.getInebriety() + result.getCount() );
		}
		else if ( resultName.equals( AdventureResult.SUBSTATS ) )
		{
			if ( result.isMuscleGain() )
			{
				KoLCharacter.totalSubpoints[ 0 ] += result.getCount();
			}
			else if ( result.isMysticalityGain() )
			{
				KoLCharacter.totalSubpoints[ 1 ] += result.getCount();
			}
			else if ( result.isMoxieGain() )
			{
				KoLCharacter.totalSubpoints[ 2 ] += result.getCount();
			}
		}
	}

	private static final AdventureResult DEAD_MIMIC = new AdventureResult( 1267, 1 );
	private static final AdventureResult[] WANDS = new AdventureResult[]
	{
		new AdventureResult( 1268, 1 ),	// pine wand
		new AdventureResult( 1269, 1 ),	// ebony wand
		new AdventureResult( 1270, 1 ),	// hexagonal wand
		new AdventureResult( 1271, 1 ),	// aluminum wand
		new AdventureResult( 1272, 1 )	// marble wand
	};

	/**
	 * Returns the character's zapping wand, if any
	 */

	public static final AdventureResult getZapper()
	{
		if ( KoLConstants.inventory.contains( KoLCharacter.DEAD_MIMIC ) )
		{
			RequestThread.postRequest( new ConsumeItemRequest( KoLCharacter.DEAD_MIMIC ) );
		}

		for ( int i = 0; i < KoLCharacter.WANDS.length; ++i )
		{
			if ( KoLConstants.inventory.contains( KoLCharacter.WANDS[ i ] ) )
			{
				return KoLCharacter.WANDS[ i ];
			}
		}

		return null;
	}

	public static final boolean hasItem( final AdventureResult item )
	{
		return KoLCharacter.hasItem( item, false );
	}

	public static final boolean hasItem( final AdventureResult item, final boolean shouldCreate )
	{
		if ( item == null )
		{
			return false;
		}

		int count = item.getCount( KoLConstants.inventory ) + item.getCount( KoLConstants.closet );

		if ( KoLCharacter.canInteract() )
		{
			if ( !KoLCharacter.inBadMoon() )
			{
				count += item.getCount( KoLConstants.storage );
			}

			if ( KoLCharacter.hasClan() && KoLSettings.getBooleanProperty( "autoSatisfyWithStash" ) )
			{
				count += item.getCount( ClanManager.getStash() );
			}
		}

		switch ( TradeableItemDatabase.getConsumptionType( item.getItemId() ) )
		{
		case EQUIP_HAT:
		case EQUIP_PANTS:
		case EQUIP_FAMILIAR:
		case EQUIP_OFFHAND:
			if ( KoLCharacter.hasEquipped( item ) )
			{
				++count;
			}
			break;

		case EQUIP_WEAPON:
			if ( KoLCharacter.hasEquipped( item, KoLCharacter.WEAPON ) )
			{
				++count;
			}
			if ( KoLCharacter.hasEquipped( item, KoLCharacter.OFFHAND ) )
			{
				++count;
			}
			break;

		case EQUIP_ACCESSORY:
			if ( KoLCharacter.hasEquipped( item, KoLCharacter.ACCESSORY1 ) )
			{
				++count;
			}
			if ( KoLCharacter.hasEquipped( item, KoLCharacter.ACCESSORY2 ) )
			{
				++count;
			}
			if ( KoLCharacter.hasEquipped( item, KoLCharacter.ACCESSORY3 ) )
			{
				++count;
			}
			break;
		}

		if ( shouldCreate )
		{
			ItemCreationRequest creation = ItemCreationRequest.getInstance( item.getItemId() );
			if ( creation != null )
			{
				count += creation.getQuantityPossible();
			}
		}

		return count > 0 && count >= item.getCount();
	}

	public static final boolean hasEquipped( final AdventureResult item, final int equipmentSlot )
	{
		return KoLCharacter.getEquipment( equipmentSlot ).getItemId() == item.getItemId();
	}

	public static final boolean hasEquipped( final AdventureResult item )
	{
		switch ( TradeableItemDatabase.getConsumptionType( item.getItemId() ) )
		{
		case EQUIP_WEAPON:
			return KoLCharacter.hasEquipped( item, KoLCharacter.WEAPON ) || KoLCharacter.hasEquipped(
				item, KoLCharacter.OFFHAND );

		case EQUIP_OFFHAND:
			return KoLCharacter.hasEquipped( item, KoLCharacter.OFFHAND );

		case EQUIP_HAT:
			return KoLCharacter.hasEquipped( item, KoLCharacter.HAT );

		case EQUIP_SHIRT:
			return KoLCharacter.hasEquipped( item, KoLCharacter.SHIRT );

		case EQUIP_PANTS:
			return KoLCharacter.hasEquipped( item, KoLCharacter.PANTS );

		case EQUIP_ACCESSORY:
			return KoLCharacter.hasEquipped( item, KoLCharacter.ACCESSORY1 ) || KoLCharacter.hasEquipped(
				item, KoLCharacter.ACCESSORY2 ) || KoLCharacter.hasEquipped( item, KoLCharacter.ACCESSORY3 );

		case EQUIP_FAMILIAR:
			return KoLCharacter.hasEquipped( item, KoLCharacter.FAMILIAR );
		}

		return false;
	}

	public static final void updateStatus()
	{
		KoLCharacterListener[] listenerArray = new KoLCharacterListener[ KoLCharacter.listenerList.size() ];
		KoLCharacter.listenerList.toArray( listenerArray );

		for ( int i = 0; i < listenerArray.length; ++i )
		{
			listenerArray[ i ].updateStatus();
		}
	}

	public static final boolean recalculateAdjustments()
	{
		int taoFactor = KoLCharacter.hasSkill( "Tao of the Terrapin" ) ? 2 : 1;
		int brimstoneMonsterLevel = 1;

		Modifiers newModifiers = new Modifiers();

		// Look at sign-specific adjustments
		newModifiers.add( Modifiers.MONSTER_LEVEL, KoLCharacter.getSignedMLAdjustment() );

		// Look at items
		for ( int slot = KoLCharacter.HAT; slot <= KoLCharacter.FAMILIAR; ++slot )
		{
			AdventureResult item = KoLCharacter.getEquipment( slot );
			if ( item == null )
			{
				continue;
			}

			newModifiers.add( Modifiers.getModifiers( item.getName() ) );

			// Wearing multiple brimstone items has a secret effect
			// on Monster Level, according to this thread:
			// http://forums.kingdomofloathing.com:8080/vb/showthread.php?t=144250
			if ( item.getItemId() >= 2813 && item.getItemId() <= 2818 )
			{
				brimstoneMonsterLevel *= 2;
			}

			switch ( slot )
			{
			case WEAPON:
			case FAMILIAR:
			case OFFHAND:
				break;

			case HAT:
			case PANTS:
				newModifiers.add(
					Modifiers.DAMAGE_ABSORPTION, taoFactor * EquipmentDatabase.getPower( item.getItemId() ) );
				break;

			case SHIRT:
				newModifiers.add( Modifiers.DAMAGE_ABSORPTION, EquipmentDatabase.getPower( item.getItemId() ) );
				break;
			}
		}

		// Brimstone only affects monster level if more than one is worn
		if ( brimstoneMonsterLevel > 2 )
		{
			newModifiers.add( Modifiers.MONSTER_LEVEL, brimstoneMonsterLevel );
		}

		// Certain outfits give benefits to the character
		SpecialOutfit outfit = EquipmentDatabase.currentOutfit();
		if ( outfit != null )
		{
			newModifiers.add( Modifiers.getModifiers( outfit.getName() ) );
		}

		// Wearing a serpentine sword and a serpentine shield doubles
		// the effect of the sword.

		if ( KoLCharacter.getEquipment( KoLCharacter.WEAPON ).getName().equals( "serpentine sword" ) && KoLCharacter.getEquipment(
			KoLCharacter.OFFHAND ).getName().equals( "snake shield" ) )
		{
			newModifiers.add( Modifiers.MONSTER_LEVEL, 10 );
		}

		// Because there are a limited number of passive skills,
		// it is much more efficient to execute one check for
		// each of the known skills.

		newModifiers.applyPassiveModifiers();

		// For the sake of easier maintenance, execute a lot of extra
		// extra string comparisons when looking at status effects.

		for ( int i = 0; i < KoLConstants.activeEffects.size(); ++i )
		{
			newModifiers.add( Modifiers.getModifiers( ( (AdventureResult) KoLConstants.activeEffects.get( i ) ).getName() ) );
		}

		// Add familiar effects based on calculated weight adjustment,

		newModifiers.applyFamiliarModifiers( KoLCharacter.currentFamiliar );

		// Add in strung-up quartet.

		if ( KoLCharacter.getAscensions() == KoLSettings.getIntegerProperty( "lastQuartetAscension" ) )
		{
			switch ( KoLSettings.getIntegerProperty( "lastQuartetRequest" ) )
			{
			case 1:
				newModifiers.add( Modifiers.MONSTER_LEVEL, 5 );
				break;
			case 2:
				newModifiers.add( Modifiers.COMBAT_RATE, -5 );
				break;
			case 3:
				newModifiers.add( Modifiers.ITEMDROP, 5 );
				break;
			}
		}

		// Lastly, experience adjustment also implicitly depends on
		// monster level.  Add that information.

		float monsterLevel = newModifiers.get( Modifiers.MONSTER_LEVEL );
		newModifiers.add( Modifiers.EXPERIENCE, monsterLevel / 4.0f );

		float combatRate = newModifiers.get( Modifiers.COMBAT_RATE );
		if ( combatRate < 0.0f )
		{
			newModifiers.add( Modifiers.COMBAT_RATE, Math.min(
				( monsterLevel - KoLCharacter.getSignedMLAdjustment() ) / 5.0f, 0 - combatRate ) );
		}

		// Determine whether or not data has changed

		boolean changed = KoLCharacter.currentModifiers.set( newModifiers );
		return changed;
	}

        // Per-character settings that change each ascension

	public static final void ensureUpdatedGuyMadeOfBees()
	{
		int lastAscension = KoLSettings.getIntegerProperty( "lastGuyMadeOfBeesReset" );
		if ( lastAscension < KoLCharacter.getAscensions() )
		{
			KoLSettings.setUserProperty( "lastGuyMadeOfBeesReset", String.valueOf( KoLCharacter.getAscensions() ) );
                        KoLSettings.setUserProperty( "guyMadeOfBeesCount", "0" );
                        KoLSettings.setUserProperty( "guyMadeOfBeesDefeated", "false" );
                }
	}

	public static final void ensureUpdatedSemirareCounter()
	{
		int lastAscension = KoLSettings.getIntegerProperty( "lastSemirareReset" );
		if ( lastAscension < KoLCharacter.getAscensions() )
		{
			KoLSettings.setUserProperty( "lastSemirareReset", String.valueOf( KoLCharacter.getAscensions() ) );
                        KoLSettings.setUserProperty( "semirareCounter", "0" );
                }
	}

	public static final void ensureUpdatedPotionEffects()
	{
		int lastAscension = KoLSettings.getIntegerProperty( "lastBangPotionReset" );
		if ( lastAscension < KoLCharacter.getAscensions() )
		{
			KoLSettings.setUserProperty( "lastBangPotionReset", String.valueOf( KoLCharacter.getAscensions() ) );
			for ( int i = 819; i <= 827; ++i )
			{
				KoLSettings.setUserProperty( "lastBangPotion" + i, "" );
			}
		}
	}

	public static final void ensureUpdatedSphereEffects()
	{
		int lastAscension = KoLSettings.getIntegerProperty( "lastStoneSphereReset" );
		if ( lastAscension < KoLCharacter.getAscensions() )
		{
			KoLSettings.setUserProperty( "lastStoneSphereReset", String.valueOf( KoLCharacter.getAscensions() ) );
			for ( int i = 2174; i <= 2177; ++i )
			{
				KoLSettings.setUserProperty( "lastStoneSphere" + i, "" );
			}
		}
	}
}
