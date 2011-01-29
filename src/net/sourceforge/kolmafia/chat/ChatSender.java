/**
 * Copyright (c) 2005-2010, KoLmafia development team
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

package net.sourceforge.kolmafia.chat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.kolmafia.AdventureResult;
import net.sourceforge.kolmafia.RequestThread;
import net.sourceforge.kolmafia.StaticEntity;
import net.sourceforge.kolmafia.persistence.ItemFinder;
import net.sourceforge.kolmafia.request.ChatRequest;
import net.sourceforge.kolmafia.request.GenericRequest;
import net.sourceforge.kolmafia.request.QuestLogRequest;
import net.sourceforge.kolmafia.session.ContactManager;
import net.sourceforge.kolmafia.swingui.CommandDisplayFrame;
import net.sourceforge.kolmafia.swingui.widget.ShowDescriptionList;
import net.sourceforge.kolmafia.utilities.StringUtilities;

public class ChatSender
{
	private static final Pattern DOJAX_PATTERN =
		Pattern.compile( "<!--js\\(\\s*dojax\\(\\s*['\"](.*?)['\"]\\s*\\)\\s*;?\\s*\\)-->" );

	private static final GenericRequest DOJAX_VISITOR = new GenericRequest( "" );

	private static final Pattern PRIVATE_MESSAGE_PATTERN =
		Pattern.compile( "/(?:msg|whisper|w|tell)\\s+(\\S+)\\s+", Pattern.CASE_INSENSITIVE );

	private static boolean scriptedMessagesEnabled = true;

	private static final ArrayList CHANNEL_COMMANDS = new ArrayList();

	static
	{
		ChatSender.CHANNEL_COMMANDS.add( "/em" );
		ChatSender.CHANNEL_COMMANDS.add( "/me" );
		ChatSender.CHANNEL_COMMANDS.add( "/ann" );
	}

	public static final void executeMacro( String macro )
	{
		if ( !ChatSender.scriptedMessagesEnabled || !QuestLogRequest.isChatAvailable() )
		{
			return;
		}

		ChatRequest request = new ChatRequest( macro );

		List accumulatedMessages = new ArrayList();
		
		sendRequest( accumulatedMessages, request );

		ChatSender.executeAjaxCommand( request.responseText );

		for ( int i = 0; ChatSender.scriptedMessagesEnabled && i < accumulatedMessages.size(); ++i )
		{
			ChatMessage message = (ChatMessage) accumulatedMessages.get( i );

			String recipient = message.getRecipient();

			ChatSender.scriptedMessagesEnabled = recipient == null || recipient.equals( "" ) || recipient.equals( "/clan" ) || recipient.equals( "/hobopolis" ) || recipient.equals( "/slimetube" );
		}
	}

	public static final void sendMessage( String contact, String message )
	{
		if ( !QuestLogRequest.isChatAvailable() )
		{
			return;
		}

		ChatSender.sendMessage( contact, message, false );
	}

	public static final void sendMessage( String contact, String message, boolean channelRestricted )
	{
		if ( !QuestLogRequest.isChatAvailable() )
		{
			return;
		}

		List grafs = getGrafs( contact, message );

		if ( grafs == null )
		{
			return;
		}
		
		List accumulatedMessages = new ArrayList();

		for ( int i = 0; i < grafs.size(); ++i )
		{
			String graf = (String) grafs.get( i );

			String responseText = ChatSender.sendMessage( accumulatedMessages, graf, channelRestricted );
			ChatSender.executeAjaxCommand( responseText );
		}
	}

	public static final String sendMessage( String graf )
	{
		return sendMessage( new ArrayList(), graf );
	}
	
	public static final String sendMessage( List accumulatedMessages, String graf )
	{
		if ( !QuestLogRequest.isChatAvailable() )
		{
			return "";
		}

		return ChatSender.sendMessage( accumulatedMessages, graf, false );
	}

	public static final String sendMessage( List accumulatedMessages, String graf, boolean channelRestricted )
	{
		if ( !QuestLogRequest.isChatAvailable() )
		{
			return "";
		}

		if ( channelRestricted && !ChatSender.scriptedMessagesEnabled )
		{
			return "";
		}

		if ( ChatSender.executeCommand( graf ) )
		{
			return "";
		}

		if ( graf.startsWith( "/examine" ) )
		{
			String item = graf.substring( graf.indexOf( " " ) ).trim();

			AdventureResult result = ItemFinder.getFirstMatchingItem( item, ItemFinder.ANY_MATCH, false );

			if ( result != null )
			{
				ShowDescriptionList.showGameDescription( result );
			}
			else
			{
				EventMessage message = new EventMessage( "Unable to find a unique match", "green" );
				ChatManager.broadcastEvent( message );
			}

			return "";
		}

		ChatRequest request = new ChatRequest( graf );

		sendRequest( accumulatedMessages, request );

		if ( channelRestricted )
		{
			for ( int i = 0; ChatSender.scriptedMessagesEnabled && i < accumulatedMessages.size(); ++i )
			{
				ChatMessage message = (ChatMessage) accumulatedMessages.get( i );

				String recipient = message.getRecipient();

				ChatSender.scriptedMessagesEnabled = recipient == null || recipient.equals( "/clan" ) || recipient.equals( "/hobopolis" ) || recipient.equals( "/slimetube" );
			}
		}

		return request.responseText;
	}

	public static final void sendRequest( List accumulatedMessages, ChatRequest request )
	{
		if ( !QuestLogRequest.isChatAvailable() )
		{
			return;
		}

		RequestThread.postRequest( request );

		if ( request.responseText == null )
		{
			return;
		}

		String graf = request.getGraf();

		if ( graf.equals( "/listen" ) )
		{
			ChatParser.parseChannelList( accumulatedMessages, request.responseText );
		}
		else if ( graf.startsWith( "/l " ) || graf.startsWith( "/listen " ) )
		{
			ChatParser.parseListen( accumulatedMessages, request.responseText );
		}
		else if ( graf.startsWith( "/c " ) || graf.startsWith( "/channel " ) )
		{
			ChatParser.parseChannel( accumulatedMessages, request.responseText );
		}
		else if ( graf.startsWith( "/s " ) || graf.startsWith( "/switch " ) )
		{
			ChatParser.parseSwitch( accumulatedMessages, request.responseText );
		}
		else if ( graf.startsWith( "/who " ) || graf.equals( "/f" ) || graf.equals( "/friends" ) || graf.equals( "/romans" ) || graf.equals( "/clannies" ) )
		{
			ChatParser.parseContacts( accumulatedMessages, request.responseText );
		}
		else
		{
			ChatParser.parseLines( accumulatedMessages, request.responseText );
		}

		ChatManager.processMessages( accumulatedMessages );
	}

	private static final List getGrafs( String contact, String message )
	{
		List grafs = new ArrayList();

		if ( message.startsWith( "/do " ) || message.startsWith( "/run " ) || message.startsWith( "/cli " ) )
		{
			grafs.add( message );

			return grafs;
		}

		Matcher privateMessageMatcher = ChatSender.PRIVATE_MESSAGE_PATTERN.matcher( message );

		if ( privateMessageMatcher.find() )
		{
			contact = privateMessageMatcher.group( 1 ).trim();
			message = message.substring( privateMessageMatcher.end() ).trim();
		}

		if ( message.length() > 256 && contact != null && !contact.equals( "/clan" ) )
		{
			// If the message is too long for one message, then
			// divide it into its component pieces.

			String command = "";
			String splitter = " ";
			String prefix = "... ";
			String suffix = " ...";

			if ( message.indexOf( " && " ) != -1 )
			{
				// Assume chained commands, must handle differently

				splitter = " && ";
				prefix = "";
				suffix = "";
			}
			else if ( message.startsWith( "/" ) )
			{
				int spaceIndex = message.indexOf( " " );

				if ( spaceIndex != -1 )
				{
					command = message.substring( 0, spaceIndex ).trim();
					message = message.substring( spaceIndex ).trim();
				}
				else
				{
					command = message.trim();
					message = "";
				}
			}

			int maxPiece = 255 - command.length() - suffix.length();

			while ( message.length() > maxPiece )
			{
				int splitPos = message.lastIndexOf( splitter, maxPiece );
				if ( splitPos <= prefix.length() )
				{
					splitPos = maxPiece;
				}

				grafs.addAll( ChatSender.getGrafs( contact, command + " " + message.substring( 0, splitPos ) + suffix ));

				message = prefix + message.substring( splitPos + splitter.length() );
			}

			grafs.addAll( ChatSender.getGrafs( contact, command + " " + message ) );

			return grafs;
		}

		String contactId = "[none]";

		if ( contact != null )
		{
			contactId = ContactManager.getPlayerId( contact );
			contactId = StringUtilities.globalStringReplace( contactId, " ", "_" ).trim();
		}

		String graf = message == null ? "" : message.trim();

		if ( graf.startsWith( "/exit" ) )
		{
			// Exiting chat should dispose.  KoLmafia should send the
			// message to be server-friendly.

			ChatManager.dispose();

			return grafs;
		}

		if ( contactId.startsWith( "[" ) )
		{
			// This is a message coming from an aggregated window, so
			// leave it as is.
		}
		else if ( !contact.startsWith( "/" ) && !graf.startsWith( "/" ) )
		{
			// Implied requests for a private message should be wrapped
			// in a /msg block.

			graf = "/msg " + contactId + " " + graf;
		}
		else if ( !graf.startsWith( "/" ) )
		{
			// All non-command messages are directed to a channel.  Append the
			// name of the channel to the beginning of the message so you
			// ensure the message gets there.

			graf = contact + " " + graf;
		}
		else
		{
			int spaceIndex = graf.indexOf( " " );
			String baseCommand = spaceIndex == -1 ? graf.toLowerCase() : graf.substring( 0, spaceIndex ).toLowerCase();

			if ( graf.equals( "/w" ) || graf.equals( "/who" ) )
			{
				// Attempts to view the /who list use the name of the channel
				// when the user doesn't specify the channel.

				graf = "/who " + contact.substring( 1 );
			}
			else if ( ChatSender.CHANNEL_COMMANDS.contains( baseCommand ) )
			{
				if ( contact.startsWith( "/" ) )
				{
					// Direct the message to a channel by appending the name
					// of the channel to the beginning of the message.
					graf = contact + " " + graf;
				}
				else
				{
					// And if it's a private message
					graf = "/msg " + contact + " " + graf;
				}
			}
		}

		if ( graf.startsWith( "/l " ) || graf.startsWith( "/listen " ) )
		{
			String currentChannel = ChatManager.getCurrentChannel();

			if ( currentChannel != null && graf.endsWith( currentChannel ) )
			{
				return null;
			}
		}

		grafs.add( graf );

		return grafs;
	}

	private static final boolean executeCommand( String graf )
	{
		if ( graf == null )
		{
			return false;
		}

		if ( !graf.startsWith( "/do " ) && !graf.startsWith( "/run " ) && !graf.startsWith( "/cli " ) )
		{
			return false;
		}

		int spaceIndex = graf.indexOf( " " );

		String command = graf.substring( spaceIndex ).trim();
		CommandDisplayFrame.executeCommand( command );
		return true;
	}

	private static void executeAjaxCommand( String responseText )
	{
		if ( responseText == null )
		{
			return;
		}

		Matcher dojax = ChatSender.DOJAX_PATTERN.matcher( responseText );

		while ( dojax.find() )
		{
			ChatSender.DOJAX_VISITOR.constructURLString( dojax.group( 1 ) );
			RequestThread.postRequest( ChatSender.DOJAX_VISITOR );

			if ( ChatSender.DOJAX_VISITOR.responseText == null )
			{
				continue;
			}

			StaticEntity.externalUpdate( ChatSender.DOJAX_VISITOR.getURLString(), ChatSender.DOJAX_VISITOR.responseText );
		}
	}
}
