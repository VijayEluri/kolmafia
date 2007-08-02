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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StarChartRequest extends ItemCreationRequest
{
	public static final int STAR = 654;
	public static final int LINE = 655;

	public static final AdventureResult CHART = new AdventureResult( "star chart", -1 );
	public static final AdventureResult STARS = new AdventureResult( "star", -1 );
	public static final AdventureResult LINES = new AdventureResult( "line", -1 );

	private static final Pattern STAR_PATTERN = Pattern.compile( "numstars=(\\d+)" );
	private static final Pattern LINE_PATTERN = Pattern.compile( "numlines=(\\d+)" );

	private int stars, lines;

	public StarChartRequest( int itemId )
	{
		super( "starchart.php", itemId );

		AdventureResult [] ingredients = ConcoctionsDatabase.getIngredients( itemId );
		if ( ingredients != null )
			for ( int i = 0; i < ingredients.length; ++i )
			{
				if ( ingredients[i].getItemId() == STAR )
					this.stars = ingredients[i].getCount();
				else if ( ingredients[i].getItemId() == LINE)
					this.lines = ingredients[i].getCount();
			}

		this.addFormField( "action", "makesomething" );
		this.addFormField( "numstars", String.valueOf( this.stars ) );
		this.addFormField( "numlines", String.valueOf( this.lines ) );
	}

	public void reconstructFields()
	{
	}

	public void run()
	{
		// Attempting to make the ingredients will pull the
		// needed items from the closet if they are missing.

		if ( !this.makeIngredients() )
			return;

		for ( int i = 1; i <= this.getQuantityNeeded(); ++i )
		{
			KoLmafia.updateDisplay( "Creating " + this.getName() + " (" + i + " of " + this.getQuantityNeeded() + ")..." );
			super.run();
		}
	}

	public void processResults()
	{
		// It's possible to fail. For example, you can't make a
		// shirt without the Torso Awaregness skill.

		// "You can't seem to make a reasonable picture out of
		// that number of stars and lines."

		if ( this.responseText.indexOf( "reasonable picture" ) != -1 )
		{
			KoLmafia.updateDisplay( ERROR_STATE, "You can't make that item." );
			return;
		}
	}

	public static final boolean registerRequest( String urlString )
	{
		Matcher starMatcher = STAR_PATTERN.matcher( urlString );
		Matcher lineMatcher = LINE_PATTERN.matcher( urlString );

		if ( !starMatcher.find() || !lineMatcher.find() )
			return true;

		int stars = StaticEntity.parseInt( starMatcher.group(1) );
		int lines = StaticEntity.parseInt( lineMatcher.group(1) );

		RequestLogger.updateSessionLog();
		RequestLogger.updateSessionLog( "Draw " + stars + " stars with " + lines + " lines" );

		StaticEntity.getClient().processResult( new AdventureResult( STAR, 0 - stars ) );
		StaticEntity.getClient().processResult( new AdventureResult( LINE, 0 - lines ) );
		StaticEntity.getClient().processResult( CHART );

		return true;
	}
}

