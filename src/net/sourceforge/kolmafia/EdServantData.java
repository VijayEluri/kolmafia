/**
 * Copyright (c) 2005-2015, KoLmafia development team
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

import java.io.File;

import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;

import net.java.dev.spellcast.utilities.JComponentUtilities;
import net.java.dev.spellcast.utilities.SortedListModel;

import net.sourceforge.kolmafia.listener.NamedListenerRegistry;

import net.sourceforge.kolmafia.objectpool.IntegerPool;

import net.sourceforge.kolmafia.request.GenericRequest;

import net.sourceforge.kolmafia.utilities.FileUtilities;
import net.sourceforge.kolmafia.utilities.StringUtilities;

public class EdServantData
	implements Comparable<EdServantData>
{
	public static final Object[][] SERVANTS =
	{
		// Servant type
		// Servant ID
		// Image file name
		// Level 1 Power
		// Level 7 Power
		// Level 14 Power
		// Level 21 Power

		{
			"Cat",
			IntegerPool.get( 1 ),
			"edserv1.gif",
			"Gives unpleasant gifts",
			"Helps find items",
			"Lowers enemy stats",
			"Teaches you how to find items",
		},
		{
			"Belly-Dancer",
			IntegerPool.get( 2 ),
			"edserv2.gif",
			"Lowers enemy stats",
			"Restores MP",
			"Picks pockets",
			"Teaches you how to restore MP",
		},
		{
			"Maid",
			IntegerPool.get( 3 ),
			"edserv3.gif",
			"Helps find meat",
			"Attacks enemies",
			"Prevents enemy attacks",
			"Teaches you how to find meat",
		},
		{
			"Bodyguard",
			IntegerPool.get( 4 ),
			"edserv4.gif",
			"Prevents enemy attacks",
			"Attacks enemies",
			"Attacks enemies even when guarding",
			"Teaches you how to defend yourself",
		},
		{
			"Scribe",
			IntegerPool.get( 5 ),
			"edserv5.gif",
			"Improves stat gains",
			"Improves spell crit",
			"Improves spell damage",
			"Teaches you how to improve stat gains",
		},
		{
			"Priest",
			IntegerPool.get( 6 ),
			"edserv6.gif",
			"Attacks undead enemies",
			"Improves evocation spells",
			"Improves Ka drops",
			"Teaches you how to improve spell damage",
		},
		{
			"Assassin",
			IntegerPool.get( 7 ),
			"edserv7.gif",
			"Attacks enemies",
			"Lowers enemy stats",
			"Staggers enemies",
			"Teaches you how to improve physical attacks",
		},
	};

	public static String dataToType( Object[] data )
	{
		return data == null ? "" : (String)data[ 0 ];
	}

	public static int dataToId( Object[] data )
	{
		return data == null ? 0 : ((Integer)data[ 1 ]).intValue();
	}

	public static String dataToImage( Object[] data )
	{
		return data == null ? null : (String)data[ 2 ];
	}

	public static String dataToLevel1Ability( Object[] data )
	{
		return data == null ? null : (String)data[ 3 ];
	}

	public static String dataToLevel7Ability( Object[] data )
	{
		return data == null ? null : (String)data[ 4 ];
	}

	public static String dataToLevel14Ability( Object[] data )
	{
		return data == null ? null : (String)data[ 5 ];
	}

	public static String dataToLevel21Ability( Object[] data )
	{
		return data == null ? null : (String)data[ 6 ];
	}

	public static Object[] idToData( final int id )
	{
		for ( Object[] data : EdServantData.SERVANTS )
		{
			if ( EdServantData.dataToId( data ) == id )
			{
				return data;
			}
		}
		return null;
	}

	public static Object[] typeToData( final String type )
	{
		for ( Object[] data : EdServantData.SERVANTS )
		{
			if ( EdServantData.dataToType( data ).equals( type ) )
			{
				return data;
			}
		}
		return null;
	}

	public static String idToType( final int id )
	{
		return id == 0 ? "(none)" : EdServantData.dataToType( EdServantData.idToData( id ) );
	}

	public static final EdServantData NO_SERVANT = new EdServantData( 0 );

	private Object[] data;
	private final String type;
	private final int id;
	private String name;
	private int experience;
	private int level;

	public static final SortedListModel<EdServantData> edServants = new SortedListModel<EdServantData>();
	public static EdServantData currentEdServant = EdServantData.NO_SERVANT;

	public static void reset()
	{
		EdServantData.edServants.clear();
		EdServantData.edServants.add( EdServantData.NO_SERVANT );
		EdServantData.currentEdServant = EdServantData.NO_SERVANT;
	}

	private EdServantData( final Object [] data )
	{
		this.data = data;
		this.type = EdServantData.dataToType( data );
		this.id = EdServantData.dataToId( data );
		this.name = "";
		this.experience = 0;
		this.level = 0;
	}

	private EdServantData( final String type )
	{
		this( EdServantData.typeToData( type ) );
	}

	private EdServantData( final int id )
	{
		this( EdServantData.idToData( id ) );
	}

	public Object [] getData()
	{
		return this.data;
	}

	public String getType()
	{
		return this.type;
	}

	public int getId()
	{
		return this.id;
	}

	public String getName()
	{
		return this.name;
	}

	public final void setName( final String name )
	{
		this.name = name;
	}

	public int getLevel()
	{
		return this.level;
	}

	public int getExperience()
	{
		return this.experience;
	}

	public String getImage()
	{
		return this.data == null ? "" : EdServantData.dataToImage( this.data );
	}

	public static final ImageIcon getNoServantImage()
	{
		return JComponentUtilities.getImage( "debug.gif" );
	}

	public ImageIcon getServantImage()
	{
		if ( this.data == null )
		{
			return EdServantData.getNoServantImage();
		}

		String image = EdServantData.dataToImage( this.data );
		if ( image == null || image.equals( "" ) )
		{
			return EdServantData.getNoServantImage();
		}

		File file = FileUtilities.downloadImage( "http://images.kingdomofloathing.com/itemimages/" + image );
		if ( file == null )
		{
			return EdServantData.getNoServantImage();
		}
		ImageIcon icon = JComponentUtilities.getImage( "itemimages/" + image );
		return icon != null ? icon : EdServantData.getNoServantImage();
	}

	public String getLevel1Ability()
	{
		return this.data == null ? "" : EdServantData.dataToLevel1Ability( this.data );
	}

	public String getLevel7Ability()
	{
		return this.data == null ? "" : EdServantData.dataToLevel7Ability( this.data );
	}

	public String getLevel14Ability()
	{
		return this.data == null ? "" : EdServantData.dataToLevel14Ability( this.data );
	}

	public String getLevel21Ability()
	{
		return this.data == null ? "" : EdServantData.dataToLevel21Ability( this.data );
	}

	@Override
	public String toString()
	{
		return this.id == 0 ? "(none)" : this.name + ", the " + this.type + " (lvl. " + this.getLevel() + ", " + this.getExperience() + " xp)";
	}

	@Override
	public boolean equals( final Object o )
	{
		return o != null && o instanceof EdServantData && this.id == ( (EdServantData) o ).id;
	}

	@Override
	public int hashCode()
	{
		return this.id;
	}

	public int compareTo( final EdServantData td )
	{
		return this.id - td.id;
	}

	// <b>Busy Servant</b>: <img src=http://images.kingdomofloathing.com/itemimages/edserv2.gif>Hetekhemerit, the Belly-Dancer (lvl. 11, 142 XP) <small>[<a href=# id="ren">rename</a>]</small><br><span class=tiny>&nbsp;&nbsp;&nbsp;<font color=blue><B>Level 1:</b> Lowers enemy stats</font><br>&nbsp;&nbsp;&nbsp;<font color=blue><b>Level 7:</b> Restores MP</font><br>&nbsp;&nbsp;&nbsp;<font color=gray><b>Level 14:</b> Picks pockets</font><br>&nbsp;&nbsp;&nbsp;<font color=gray><b>Level 21:</b> Teaches you how to restore MP</font><br></span><div style="display: none" id="rename"><form method="post" action="choice.php" style="display:inline"><input type="hidden" name="whichchoice" value="1053" /><input type="hidden" name="option" value="2" /><input type="hidden" name="pwd" value="a4ce8559175f86f50b2d8b7626826e3e" /><input type="hidden" name="sid" value="2" /><input type="text" size="30" maxlen="30" name="name" value="Hetekhemerit" /><input type="submit" class="button" value="Rename" /></form></div>

	private static final Pattern BUSY_PATTERN =
		Pattern.compile( "<b>Busy Servant</b>: <img.*?/itemimages/(edserv\\d.gif)>(.*?), the (.*?) \\(lvl. (\\d+), ([\\d,]+) XP\\)", Pattern.DOTALL );

	// <b>Freed, but Lazy Servants</b><table>...</table>

	private static final Pattern FREED_TABLE_PATTERN =
		Pattern.compile( "<b>Freed, but Lazy Servants</b><table>(.*?)</table>", Pattern.DOTALL );

	// <tr><td valign=top><img src=http://images.kingdomofloathing.com/itemimages/edserv1.gif></td><td>Hethys, the Cat<br><span class=tiny>&nbsp;&nbsp;&nbsp;<font color=blue><B>Level 1:</b> Gives unpleasant gifts</font><br>&nbsp;&nbsp;&nbsp;<font color=blue><b>Level 7:</b> Helps find items</font><br>&nbsp;&nbsp;&nbsp;<font color=blue><b>Level 14:</b> Lowers enemy stats</font><br>&nbsp;&nbsp;&nbsp;<font color=gray><b>Level 21:</b> Teaches you how to find items</font><br></span></td><td valign=top>(level 14, 221 xp)</td><td valign=top><form method="post" action="choice.php" style="display:inline"><input type="hidden" name="whichchoice" value="1053" /><input type="hidden" name="option" value="1" /><input type="hidden" name="pwd" value="a4ce8559175f86f50b2d8b7626826e3e" /><input type="hidden" name="sid" value="1" /><input type="submit" class="button" value="Put to Work" /></form></td></tr>

	private static final Pattern FREED_PATTERN =
		Pattern.compile( "<img.*?/itemimages/(edserv\\d.gif)></td><td>(.*?), the (.*?)<br>.*?\\(level (\\d+), ([\\d,]+) xp\\)", Pattern.DOTALL );

	// <b>Entombed Servants</b> - You may release 0 more servants.<table>...</table>

	// <tr><td valign=top><img src=http://images.kingdomofloathing.com/itemimages/edserv4.gif></td><td>Bodyguard<br><span class=tiny>&nbsp;&nbsp;&nbsp;<font color=gray><B>Level 1:</b> Prevents enemy attacks</font><br>&nbsp;&nbsp;&nbsp;<font color=gray><b>Level 7:</b> Attacks enemies</font><br>&nbsp;&nbsp;&nbsp;<font color=gray><b>Level 14:</b> Attacks enemies even when guarding</font><br>&nbsp;&nbsp;&nbsp;<font color=gray><b>Level 21:</b> Teaches you how to defend yourself</font><br></span></td></tr>

	public static final void inspectServants( final String responseText )
	{
		// Assume you have no servant
		EdServantData current = EdServantData.NO_SERVANT;

		if ( responseText.contains( "Busy Servant" ) )
		{
			Matcher busyMatcher = EdServantData.BUSY_PATTERN.matcher( responseText );
			if ( busyMatcher.find() )
			{
				current = EdServantData.registerEdServant( busyMatcher );
			}
		}

		Matcher freedTableMatcher = EdServantData.FREED_TABLE_PATTERN.matcher( responseText );
		if ( freedTableMatcher.find() )
		{
			Matcher freedMatcher = EdServantData.FREED_PATTERN.matcher( freedTableMatcher.group( 1 ) );
			while ( freedMatcher.find() )
			{
				EdServantData servant = EdServantData.registerEdServant( freedMatcher );
			}
		}

		EdServantData.currentEdServant = current;
	}

	public static final EdServantData currentServant()
	{
		return EdServantData.currentEdServant;
	}

	public static final List<EdServantData> getServants()
	{
		return EdServantData.edServants;
	}

	public static final EdServantData findEdServant( final String type )
	{
		for ( EdServantData servant : EdServantData.edServants )
		{
			if ( servant.type.equals( type ) )
			{
				return servant;
			}
		}
		return null;
	}

	private static final EdServantData registerEdServant( final Matcher matcher )
	{
		String type = matcher.group( 3 );
		EdServantData servant = EdServantData.findEdServant( type );
		if ( servant == null )
		{
			// Add new familiar to list
			servant = new EdServantData( type );
			EdServantData.edServants.add( servant );
		}

		// String image = matcher.group( 1 );
		String name = matcher.group( 2 );
		int level = StringUtilities.parseInt( matcher.group( 4 ) );
		int experience = StringUtilities.parseInt( matcher.group( 5 ) );

		servant.name = new String( name );
		servant.level = level;
		servant.experience = experience;

		return servant;
	}

	public static final void manipulateServants( final GenericRequest request, final String responseText )
	{
		// This should be a response to choice #1053
		String urlString = request.getURLString();

		if ( !urlString.startsWith( "choice.php" ) || !urlString.contains( "whichchoice=1053" ) )
		{
			return;
		}

		String option = request.getFormField( "option" );
		if ( option == null )
		{
			return;
		}

		// We did ... something. Re-parse servants.
		EdServantData.inspectServants( responseText );
		NamedListenerRegistry.fireChange( "(edservants)" );
	}
}
