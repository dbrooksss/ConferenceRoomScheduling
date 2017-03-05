package ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.optionalusertools.DateVetoPolicy;
import data.RoomRepository;
import model.Schedule;
import model.User;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class AdminAssistant extends BaseJFrame implements DateVetoPolicy{

	private static final long serialVersionUID = 1L;
	private User user;
	private RoomRepository repository = new RoomRepository();
	
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				try{
					
					AdminAssistant a = new AdminAssistant(new User("blahhhhh", "lastname", "username", "me@home.com", 1));
					a.setVisible(true);
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	}

	public AdminAssistant(User user){
		this.user = user; 
		initialize();
	}
	
//		a.	An administrative assistant should be able to view everyone’s schedule;

	private void initialize(){
		setTitle("Conference Room Date Picker");
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		design();
	}

	public void design(){ // rl, ud, w, h
		
		String name = user.getFirstName().substring(0, 1).toUpperCase() + user.getFirstName().substring(1);

		JPanel panel0 = new JPanel();
		panel0.setBounds(10, 15, 414, 47);
		panel0.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		getContentPane().add(panel0);
		
		JLabel label = new JLabel("Hello, " + name);
		label.setFont(font14);
		panel0.add(label);

		createLabel("Pick a Conference Room: ", 141, 60, 173, 14, font12);

		String[] rooms = repository.getRoomNames(); 
		JComboBox<?> roomChoice = new JComboBox<Object>(rooms);

		roomChoice.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			}
		});
		roomChoice.setBounds(182, 87, 76, 28);
		getContentPane().add(roomChoice);

		// date time menu components
		createLabel("Pick a Date and Time: ", 153, 134, 140, 14, font12);

		JPanel panel1 = new JPanel();
		panel1.setBounds(10, 160, 414, 47);
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		getContentPane().add(panel1);

		DatePickerSettings dateSettings = new DatePickerSettings();
		DatePicker datePicker1 = new DatePicker(dateSettings);
		dateSettings.setVetoPolicy(this);
		panel1.add(datePicker1);

		TimePicker timePicker1 = new TimePicker();
		panel1.add(timePicker1);

		JButton submitButton = new JButton("Submit");
		getContentPane().add(submitButton);
		submitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				Schedule room;

				try{
					String name = roomChoice.getSelectedItem().toString();
					Date date = java.sql.Date.valueOf(datePicker1.getDate());
					Time time = java.sql.Time.valueOf(timePicker1.getTime());

					room = new Schedule(name, date, time);
					System.out.println(room.getName() + " " + room.getDate() + " " + room.getTime());

				}catch(Exception ex){
					System.out.println("Missing information.");
				}
			}
		});
		submitButton.setBounds(242, 213, 99, 23);
	}

	/**
		 * isDateAllowed, Return true if a date should be allowed, or false if a
		 * date should be vetoed.
		 */
		@Override
		public boolean isDateAllowed(LocalDate date){

			// Disallow Saturdays and Sundays.
			if((date.getDayOfWeek() == DayOfWeek.SATURDAY) || (date.getDayOfWeek() == DayOfWeek.SUNDAY)){
				return false;
			}
			// Allow all other days.
			return true;
		}
}

// 1. Open your Java source code document and navigate to the JTable object you
// have created inside of your Swing class.
//
// 2. Create a new TableModel object that holds a DatePickerTable. You must
// create the DatePickerTable with a range of date values in MMDDYYYY format.
// The first value is the begin date and the last is the end date. In code, this
// looks like:
// TableModel datePicker = new DatePickerTable("01011999","12302000");
//
// 3. Set the display interval in the datePicker object. By default each day is
// displayed, but you may set a regular interval. To set a 15-day interval
// between date options, use this code:
// datePicker.interval = 15;
//
// 4. Attach your table model into your JTable:
// JTable newtable = new JTable (datePicker);
// Your Java application now has a drop-down date selection dialog.

// Conference Room Scheduling Software
// Drake Systems, Inc. is in need of an automated resource scheduling software
// to handle the booking of the three conference rooms (C1, C2, and C3)
// available at the company which are shared by 85 employees. The following
// requirements are provided and you requested to come up with a system solution
// providing the desired functionality.
//
// ■ The booking system should allow the meeting initiator to reserve a
// conference room by hour and day. Meetings can only be scheduled during week
// days;
//
// ■ Rules the system should comply with when allowing employee calendar viewing
// are:
// a. An administrative assistant should be able to view everyone’s schedule;
// b. A manager should be able to view the calendar of all those who report to
// him;
// c. Every individual should have the ability to grant/revoke permission to
// others to view their calendars;
// d. An administrator should have additional system maintenance functions;
//
// ■ When a meeting is booked successfully, the scheduled event should be
// updated in the calendars of each of the meeting participants;
//
// ■ Anyone who has a user id and password to the system can create a schedule.
// When creating a schedule, one should be able to see the available slots of
// each resource (each conference room or team member) to decide which time-slot
// works best for everyone at the time the resource is available;
//
// ■ A meeting organizer should have the ability to cancel or reschedule a
// meeting. If a meeting is cancelled, the calendar of each participant should
// be updated and an email notification should be sent to each participant. If a
// meeting is scheduled an available slot must be researched both for a resource
// (conference room) and a participant and a new email notification must be sent
// to all attendees;
//
// ■ The system should allow viewing of each of the resources (conference rooms)
// to determine their availability;
//
// ■ The system should not book more than 10 participants to a conference room
// at any one time;
//
// ■ Date and time entered must only be picked from a pop up calendar, not keyed
// in manually by the user;
//
// ■ System should not allow booking of a conference room if at least three
// participants are not added to the meeting;
//
// ■ Each participant should be able to accept, decline, or propose a new time
// for a meeting upon receiving an email notification of the event;
//
// ■ System should not allow scheduling of conference room outside hours 8am –
// 5Pm, on weekends, and holidays;
//
// You are expected to review the above requirements and come up with a formal
// requirement and design document as well as a test plan document. Your design
// document should include a class diagram and a database design for the
// application as well as user interface design of each of the screens that will
// be part of the application. The design document must include an object
// oriented design of the application using UML diagrams (e.g. use case,
// sequence, activity diagram, class diagrams depicting how the system is
// supposed to function. Use the IEEE template for SRS, SDS, and STS.

// DatePickerSettings dateSettings = new DatePickerSettings();
// DatePicker datePicker1 = new DatePicker(dateSettings);
// dateSettings.setVetoPolicy(new SampleDateVetoPolicy());
// dateSettings.setAllowEmptyDates(false);
// panel1.add(datePicker1);
//
// TimePickerSettings timeSettings = new TimePickerSettings();
// TimePicker timePicker1 = new TimePicker(timeSettings);
// timeSettings.setAllowEmptyTimes(false);
// panel1.add(timePicker1);
