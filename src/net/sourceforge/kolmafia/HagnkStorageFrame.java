/**
 * Copyright (c) 2005, KoLmafia development team
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
 *  [3] Neither the name "KoLmafia development team" nor the names of
 *      its contributors may be used to endorse or promote products
 *      derived from this software without specific prior written
 *      permission.
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

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.ListSelectionModel;

import net.java.dev.spellcast.utilities.LockableListModel;
import net.java.dev.spellcast.utilities.JComponentUtilities;

/**
 * An extension of <code>KoLFrame</code> which handles all the clan
 * management functionality of Kingdom of Loathing.
 */

public class HagnkStorageFrame extends KoLFrame
{
	private static HagnkStorageFrame INSTANCE = null;

	private static int pullsRemaining = 0;

	public HagnkStorageFrame()
	{
		super( "Hagnk's Storage" );

		INSTANCE = this;
		setPullsRemaining( pullsRemaining );
		framePanel.add( new HagnkStoragePanel(), BorderLayout.CENTER );
	}

	public static int getPullsRemaining()
	{	return pullsRemaining;
	}

	public static void setPullsRemaining( int pullsRemaining )
	{
		HagnkStorageFrame.pullsRemaining = pullsRemaining;
		if ( INSTANCE == null )
			return;

		if ( KoLCharacter.isHardcore() )
		{
			INSTANCE.setTitle( "No Pulls Left" );
			return;
		}
		else
		{
			switch ( pullsRemaining )
			{
			case 0:
				INSTANCE.setTitle( "No Pulls Left" );
				break;
			case -1:
				INSTANCE.setTitle( "Hagnk's Storage" );
				break;
			case 1:
				INSTANCE.setTitle( "1 Pull Left" );
				break;
			default:
				INSTANCE.setTitle( pullsRemaining + " Pulls Left" );
			}
		}
	}

	private class HagnkStoragePanel extends ItemManagePanel
	{
		private FilterCheckBox [] consumeFilters;

		public HagnkStoragePanel()
		{
			super( "Inside Storage", storage, true );

			setButtons( new String [] { "put in bag", "put in closet", "take it all" },
				new ActionListener [] { new PullFromStorageListener( false ), new PullFromStorageListener( true ), new EmptyStorageListener() } );

			movers[2].setSelected( true );

			consumeFilters = new FilterCheckBox[3];
			consumeFilters[0] = new FilterCheckBox( consumeFilters, elementList, "Show food", true );
			consumeFilters[1] = new FilterCheckBox( consumeFilters, elementList, "Show drink", true );
			consumeFilters[2] = new FilterCheckBox( consumeFilters, elementList, "Show others", true );

			for ( int i = 0; i < consumeFilters.length; ++i )
				optionPanel.add( consumeFilters[i] );

			elementList.setCellRenderer(
				AdventureResult.getConsumableCellRenderer( true, true, true ) );
		}

		protected AdventureResult [] getDesiredItems( String message )
		{
			// Ensure that the selection interval does not include
			// anything that was filtered out by the checkboxes.

			filterSelection( consumeFilters[0].isSelected(), consumeFilters[1].isSelected(), consumeFilters[2].isSelected() );
			return super.getDesiredItems( message );
		}

		private class PullFromStorageListener implements ActionListener
		{
			private boolean isCloset;

			public PullFromStorageListener( boolean withdraw )
			{	this.isCloset = isCloset;
			}

			public void actionPerformed( ActionEvent e )
			{
				Object [] items = getDesiredItems( "Pulling" );
				if ( items == null )
					return;

				Runnable [] requests = isCloset ? new Runnable[2] : new Runnable[1];
				requests[0] = new ItemStorageRequest( ItemStorageRequest.STORAGE_TO_INVENTORY, items );

				if ( isCloset )
					requests[1] = new ItemStorageRequest( ItemStorageRequest.INVENTORY_TO_CLOSET, items );

				(new RequestThread( requests )).start();
			}
		}

		private class EmptyStorageListener implements ActionListener
		{
			public void actionPerformed( ActionEvent e )
			{
				if ( !KoLCharacter.canInteract() )
				{
					KoLmafia.updateDisplay( ERROR_STATE, "You are not yet out of ronin." );
					return;
				}

				(new RequestThread( new ItemStorageRequest( ItemStorageRequest.EMPTY_STORAGE ) )).start();
			}
		}
	}

	public void dispose()
	{
		INSTANCE = null;
		super.dispose();
	}
}
