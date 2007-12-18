/**
 * @(#)CalendarTableModel.java	0.1 28/06/2002
 *
 * Copyright (c) 2002 Arron Ferguson
 *
 */

package ca.bcit.geekkit;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

/**
 * The custom <code>TableModel</code> for dealing with the calendar and dealing with <code>Date</code>s
 * and the <code>GregorianCalendar</code>
 *
 * @version	0.1 28/06/2002
 * @author 	Arron Ferguson
 */

public class CalendarTableModel extends AbstractTableModel
{
	final String[] months = {"January", "February", "March", "April", "May",
			"June", "July", "August", "September", "October", "November", "December"};

	/**
	 * Column names for the calendar.
	 */
	final Vector columnNames = new Vector(7);

	{
		columnNames.add("Sun");
		columnNames.add("Mon");
		columnNames.add("Tue");
		columnNames.add("Wed");
		columnNames.add("Thu");
		columnNames.add("Fri");
		columnNames.add("Sat");
	};

	/**
	 * The actual data for the table. The data is in a dynamic list instead of the usual 2D array
	 */
	private Vector data;

	// initial 2D table data ... hard coded but since a dynamic collection we can
	// change this.
	{
		data = new Vector(6);

		for(int i = 0; i < 6; i++)
		{
			Vector v = new Vector(7);
			for(int j = 0; j < 7; j++)
				v.add("");
			data.add(v);
		}
	};

	/**
	 * Reference to the actual view and controller which is known as the delegate in MVC speak
	 */
	private JCalendar delegate;

	/**
	 * The calendar that keeps track of dates, months and leap years. This object is called upon
	 * many times to render the month.
	 */
	private GregorianCalendar calendar;

	/**
	 * The date at which the calendar is currently pointing at. This value will change as the calendar
	 * moves forward and backward through calendar months.
	 */
	private int currentDate;

	/**
	 * The month at which the calendar is currently pointing at. This value will change as the calendar
	 * moves forward and backward through calendar months.
	 */
	private int currentMonth;

	/**
	 * The year at which the calendar is currently pointing at. This value will change as the calendar
	 * moves forward and backward through calendar months.
	 */
	private int currentYear;

	/**
	 * The time stamp to indicate the actual date as determined by the system's clock
	 */
	private int date;

	/**
	 * The first day in the week at which the date starts at. For example, the first of the month may
	 * be Wednesday.
	 */
	private int startday;

	/**
	 * The time stamp to indicate the actual month as determined by the system's clock
	 */
	private int month;

	/**
	 * The time stamp to indicate the actual year as determined by the system's clock
	 */
	private int year;

	/**
	 * Constructor for referencing the <code>JCalendar</code>
	 */
	public CalendarTableModel(JCalendar caller)
	{
		delegate = caller;
		// set up the calendar
		calendar = new GregorianCalendar();
		date = calendar.get(Calendar.DAY_OF_MONTH);
		month = calendar.get(Calendar.MONTH);
		year = calendar.get(Calendar.YEAR);
		currentDate = date;
		currentMonth = month;
		currentYear = year;
	}

	/**
	 * Returns the column count of this <code>TableModel</code>
	 *
	 * @return the number of columns for this <code>TableModel</code>
	 */
	public int getColumnCount()
	{
		return columnNames.size();
	}

	/**
	 * Return the current date which is where the calendar is pointing at. This current date
	 * may or may not be the actual real date as determined by the system clock.
	 *
	 * @return the current date being pointed to
	 */
	public int getCurrentDate()
	{
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Return the current month which is where the calendar is pointing at. This current date
	 * may or may not be the actual real date as determined by the system clock.
	 *
	 * @return the current month being pointed to
	 */
	public int getCurrentMonth()
	{
		return calendar.get(Calendar.MONTH);
	}

	/**
	 * Return the current year which is where the calendar is pointing at. This current date
	 * may or may not be the actual real date as determined by the system clock.
	 *
	 * @return the current year being pointed to
	 */
	public int getCurrentYear()
	{
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * Return the real date based on the system clock
	 * @return the actual date based on the system clock
	 */
	public int getDate()
	{
		return date;
	}

	/**
	 * Return the real month based on the system clock
	 * @return the actual month based on the system clock
	 */
	public int getMonth()
	{
		return month;
	}

	/**
	 * Return the real year based on the system clock
	 * @return the actual year based on the system clock
	 */
	public int getYear()
	{
		return year;
	}

	/**
	 * Return the number of rows in this table
	 * @return the number of rows as an int
	 */
	public int getRowCount()
	{
		return data.size();
	}

	/**
	 * Returns the column name based on the index <code>col</code>
	 *
	 * @param col the column at which a name is being requested for
	 * @return the <code>String</code> name of column <code>col</code>
	 */
	public String getColumnName(int col)
	{
		String s = (String)columnNames.get(col);
		return s;
	}

	/**
	 * Return the <code>Object</code> at a particular cell
	 *
	 * @param row the row to index
	 * @param col the column to index
	 * @return the <code>Object</code> returned from a particular cell
	 */
	public Object getValueAt(int row, int col)
	{
		return ((Vector)data.elementAt(row)).elementAt(col);
	}

	/*
	* JTable uses this method to determine the default renderer/editor for each cell
	*
	* @param col the column to index
	* @return the class of the object that is being used to render this column
	*/
	public Class getColumnClass(int col)
	{
		return getValueAt(0, col).getClass();
	}

	/*
	* Allow for the ability to edit information in each cell of this table
	*
	* @param value the actual value being passed int at a particular cell in the table
	* @param row the row at which to add the <code>Object</code>
	* @param row the column at which to add the <code>Object</code>
	*/
	public void setValueAt(Object value, int row, int col)
	{
		((Vector)data.elementAt(row)).set(col, value);
		fireTableCellUpdated(row, col);
	}

	/**
	 * Format a date and return it as a <code>String</code>
	 * @return the date as a <code>String</code>
	 */
	public String simpleDate()
	{
		Locale locale = new Locale("en", "CANADA");
		DateFormat formatter = DateFormat.getDateInstance(DateFormat.FULL, locale);
		Date signoutDate = new Date();
		String now = formatter.format(signoutDate);
		return now;
	}

	/**
	 * Generates an entire month and populates the table/cell model with the values. This method
	 * will start off with the present date which is based on the system clock. In order to change
	 * the month that is being displayed, a roll value is given. If the roll value is 1, then the
	 * calendar moves ahead one month. If the roll value is -1, then the calendar moves back one
	 * month. If 0 is given as a roll value, then the current date that is in the calendar is used.
	 *
	 * @param rollValue the value to move the calendar forwards or backwards
	 */
	public void generateCalendarMonth(int rollValue)
	{
		if(rollValue == 0 || rollValue < -1 || rollValue > 1)
			; // don't do anything since that value is non-valid
		else
			calendar.set(Calendar.MONTH, (calendar.get(Calendar.MONTH) + rollValue));

		currentYear = calendar.get(Calendar.YEAR);
		currentMonth = calendar.get(Calendar.MONTH);

		// going to go to the first of the month to get where it falls upon within the week.
		// example: the 1st of the month is Monday?
		int tempDate = calendar.get(Calendar.DATE);

		calendar.set(Calendar.DATE, 1);
		startday = calendar.get(Calendar.DAY_OF_WEEK);
		// arrays start at 0 so decrement by 1
		startday--;

		// now put it back
		calendar.set(Calendar.DATE, tempDate);

		// populate the label in the JCalendar
		delegate.label.setText(months[currentMonth] + " " + currentYear);


		// precalculate vector sizes. This assumes that all vectors
		// within the main vector will be of equal size.
		int columnMax = ((Vector)data.elementAt(0)).size();
		// all months start at 1 so this can be hard-coded
		currentDate = 1;
		// precalculate the maximum date number for the current month. February may have
		// 28 or 29 days if it's a leap year, July has 31 days, etc.
		int maxMonthDate = calendar.getActualMaximum(Calendar.DATE);
		// increment because we are dealing with arrays
		maxMonthDate++;

		// populate the cells in the table model
		for(int i = 0; i < data.size(); i++)
		{
			for(int k = 0; k < columnMax; k++)
			{
				// we need to check if a it's the 1st row. If it is, the 1st of the month
				// may not start on the first column (column 0). We need to check for this.
				if(i > 0)
				{
					setValueAt((currentDate + ""), i, k);
					currentDate++;
				} else
				{
					if(k >= startday)
					{
						setValueAt((currentDate + ""), i, k);
						currentDate++;
					}
					else
						setValueAt("", i, k);
				}
				if(currentDate > maxMonthDate)
					setValueAt("", i, k);
				if(currentDate == date)
				{
					;
				}
			}
		}
	}
}
