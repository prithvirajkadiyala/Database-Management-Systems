//Written by PK
package individual_Project;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class IP_Java_Kadiyala_Prithviraj {
	public static Scanner scan = new Scanner(System.in);
	private static Connection dbConnection;
	private static Statement stmt;
	//Connection Function
	public static void initdb() {
		try { 
			Class.forName("oracle.jdbc.OracleDriver"); 
	} catch(Exception x){ 
			System.out.println( "Unable to load the driver class!" );
			
		}
		try{
			dbConnection = DriverManager.getConnection ("jdbc:oracle:thin:@//oracle.cs.ou.edu:1521/pdborcl.cs.ou.edu"," kadi0000 ", " XWza6Tc8 "); 
			stmt = dbConnection.createStatement(); 
			} 
		catch(Exception e) { 
			System.out.println (e.getMessage()); 
			System.out.println ("Exception occurred in executing the statement");
		} 
	}
	//Helps get Inputs from the user
	public static String getInput(String prompt) {
		System.out.println(prompt);
		return scan.nextLine();
	}
	//Main Function
	public static void main(String[] args) throws SQLException { 
		initdb();
		boolean shouldQuit = false;
		String bigPrompt = "------------------------------------------------------------------------------------------------\n" + 
		                               "   Welcome to the Database of Individual Project of Prithviraj Kadiyala\n" + 
				"------------------------------------------------------------------------------------------------\n" +
              "Please Enter you option(1-17):\n" + 
				"1. Enter a new team into the database\n" +
				"2. Enter a new client into the database and associate him or her with one or more teams\n" +
				"3. Enter a new volunteer into the database and associate him or her with one or more teams\n" +
				"4. Enter the number of hours a volunteer worked this month for a particular team\n" +
				"5. Enter a new employee into the database and associate him or her with one or more teams\n" +
				"6. Enter an expense charged by an employee\n" +
				"7. Enter a new organization and associate it to one or more PAN teams\n" +
				"8. Enter a new donor and associate him or her with several donations.\n" +
				"9. Enter a new organization and associate it with several donations\n" +
				"10. Retrieve the name and phone number of the doctor of a particular client\n" +
				"11. Retrieve the total amount of expenses charged by each employee for a particular period of time.\r\n" +
				"The list should be sorted by the total amount of expenses\n" +
				"12. Retrieve the list of volunteers that are members of teams that support a particular client\n" +
				"13. Retrieve the names and contact information of the clients that are supported by teams sponsored by\r\n" + 
				"an organization whose name starts with a letter between B and K. The client list should be sorted by name\n" +
				"14. Retrieve the name and total amount donated by donors that are also employees. The list should be\r\n" + 
				"sorted by the total amount of the donations, and indicate if each donor wishes to remain anonymous\n" +
				"15. For each team, retrieve the name and associated contact information of the volunteer that has worked\r\n" + 
				"the most total hours between March and June\n" +
				"16. Increase the salary by 10% of all employees to whom more than one team must report\n"+
				"17. Delete all clients who do not have health insurance and whose value of importance for transportation\r\n" + 
				"is less than 5\n"+
				"18.Import\n"+
				"19.Export\n" +
				"20. Quit\n";
		while(!shouldQuit)
			{
				String inp = getInput(bigPrompt);
				int input = -1;
				try {
					input = Integer.parseInt(inp.trim());
				}
				catch(Exception e) {}
					switch(input) {
					case 1:
						option1();
						break;
					case 2:
						option2();
						break;
					case 3:
						option3();
						break;
					case 4:
						option4();
						break;
					case 5:
						option5();
						break;
					case 6:
						option6();
						break;
					case 7:
						option7();
						break;
					case 8:
						option8();
						break;
					case 9:
						option9();
						break;
					case 10:
						option10();
						break;
					case 11:
						option11();
						break;
					case 12:
						option12();
						break;
					case 13:
						option13();
						break;
					case 14:
						option14();
						break;
					case 15:
						option15();
						break;
					case 16:
						option16();
						break;
					case 17:
						option17();
						break;
					case 18:
						option18();
						break;
					case 19:
						option19();
						break;
					case 20:
						shouldQuit = true;
						break;
					default:
						System.out.println("Sorry, Unrecognized input");
						break;
					}
				}
				System.out.println("Thank You for using the Program");
		}
	//Helps get TO_DATE function to send to SQL
		public static String getDateSQL() {
			String month = getInput("month(mm):");
			String day = getInput("day(dd):");
			String year = getInput("year(yyyy):");
			String dateString = "TO_DATE('" + month +"-"+day+"-"+year+"', 'MM-DD-YYYY')";
			return dateString;
			
		}
		//Enter a new team into the database
		public static void option1() {
			System.out.println("Executing Query 1:");
			String team_name = getInput("Team Name:");
			String team_type= "";
			boolean option1Quit = false;
			while(!option1Quit)
			{
				String team_type_select = getInput("Team Type Select: \n1. Premium Assistance \n"+
													"2. Copayment Assistance\n3. Ancillary Assistance\n"+
													"4. Nursing Assistance\n5. Travel Assistance");
				
				int team_select = -1;
				try {
					team_select = Integer.parseInt(team_type_select.trim());
				}
				catch(Exception e) {}
					switch(team_select) {
					case 1:
						team_type += "Premium Assistance";
						option1Quit = true;
						break;
					case 2:
						team_type+="Copayment Assistance";
						option1Quit = true;
						break;
					case 3:
						team_type += "Ancillary Assistance";
						option1Quit = true;
						break;
					case 4:
						team_type+="Nursing Assistance";
						option1Quit = true;
						break;
					case 5:
						team_type += "Travel Assistance";
						option1Quit = true;
						break;
					default:
						System.out.println("Invalid Option. Please choose a correct option");
						break;
					}
			}
			System.out.println("\nPlease enter the date the team was formed:");
			String date_formed = getDateSQL();
			//Inserting Teams in to the Database
			String sql="INSERT INTO Team(Team_Name, Team_Type, Date_Formed) VALUES ('"+team_name+"','"+team_type+"', "+date_formed+")";
			System.out.println(sql);
			try {
				stmt.executeQuery(sql);
				TeamSelect();
			}catch(SQLException e) {
				e.printStackTrace();
			}	
		}

		//Enter a new client into the database and associate him or her with one or more teams
		public static void option2() {
			boolean option2Quit = false;
			//Choose options between Adding new client and Associate teams with clients or Go back to Main Menu 
			while(!option2Quit)
			{
				String option2subquery_select = getInput("What do you want to do ?\n1. Add new Client\n2. Associate Client with a team\n3. Add new Insurances for Clients\n4.Add needs for the Client\n5. Main Menu");
				int option2subquery = String_Int_Convert(option2subquery_select);
					switch(option2subquery) {
					case 1:
						PersonSelect();
						ClientSelect();
						boolean correctclient = false;
						//Choose Whether to Add new client or Person because of the Parent-Child Key
						while(!correctclient)
						{
							String client_select =getInput("What do you want to do?\n1.Add new Client\n2.Add new Person\n3.Go Back");
							int client_sel = String_Int_Convert(client_select);
								switch(client_sel) {
								case 1:
									System.out.println("\nYou are entering Info for a Client");
									String sSSN = getInput("Please input one of the SSNs from the Persons Table and not already present in Clients Table");
									int SSN = String_Int_Convert(sSSN);
									String Doc_Name = getInput("Please enter the name of the Doctor");
									String sDoc_Phone = getInput("Please enter Doc Phone Number");
									long doc_phone = String_Long_Convert(sDoc_Phone);
									String Att_Name = getInput("Please enter Attorney Name");
									String sAtt_Phone = getInput("Please enter Attorney Phone Number");
									long att_phone = String_Long_Convert(sAtt_Phone);
									System.out.println("\nPlease enter the date client is assigned to PAN");
									String date_Assigned = getDateSQL();
									//Inserting Data into Clients Table
									String sql="INSERT INTO Client(SSN, Doc_Name, Doc_Phone, Attorney_Name, Attorney_Phone, Date_Assigned) VALUES ('"+SSN+"','"+Doc_Name+"', "+doc_phone+",'"+Att_Name+"',"+att_phone+","+date_Assigned+")";
									//Loop through result set and extract results of the query
									try {
										stmt.executeQuery(sql);
										ClientSelect();
									}catch(SQLException e) {
										e.printStackTrace();
									}
									//Adding Associations the the Current Client to teams
									TeamSelect();
									String Team_Name = getInput("Please enter the Team Name from the above Team List you want to assocaite Client with");
									String Status = "";
									boolean correctstatus = false;
									//Choose options for Status 1.Active or 2.Inactive
									while(!correctstatus) {
										String statusinput = getInput("Please enter Status of the client\n1.Active\n2.Inactive");
										int statusinputint = String_Int_Convert(statusinput);
										switch(statusinputint) {
										case 1:
											Status += "Active";
											correctstatus = true;
											break;
										case 2:
											Status += "Inactive";
											correctstatus = true;
											break;
										default:
											System.out.println("Invalid Input! Please enter the correct option");
											break;
										}
									}
									//Inserting Data into Cares Table
									String sql1="INSERT INTO Cares(SSN, Team_Name, Status) VALUES ('"+SSN+"','"+Team_Name+"', '"+Status+"')";
									//Loop through result set and extract results of the query
									try {
											stmt.executeQuery(sql1);
											CaresSelect();
									}catch(SQLException e) {
										e.printStackTrace();
									}
									System.out.println("\nYou are Entering Insurance details for the Client");
									String Policy_ID = getInput("Please enter the Policy ID not present in the above list");
									String Policy_Provider = getInput("Please enter policy Provider for the client");
									String Policy_Provider_address = getInput("Please provide the Address for the Policy Provider");
									String policy_type = "";
									boolean correctpolicy = false;
									while(!correctpolicy) {
										String policy_type_select = getInput("Please type of Policy from the following:\n1.Auto\n2.Life\n3.Health\n4.Home");
										switch(policy_type_select) {
										case "1":
											policy_type = "Auto";
											correctpolicy = true;
											break;
										case "2":
											policy_type = "Life";
											correctpolicy = true;
											break;
										case "3":
											policy_type = "Health";
											correctpolicy = true;
											break;
										case "4":
											policy_type = "Home";
											correctpolicy = true;
											break;
										default:
											System.out.println("Invalid Option. Please choose a correct option");
											break;
										}
									}
									//Inserting Data into Insurance Table
									String sqlInsurance_Submit = "Insert into Insurance_Policy(Policy_ID,Provider_ID,Provider_Address,Insurance_Type,SSN) values ('"+Policy_ID+"','"+Policy_Provider+"','"+Policy_Provider_address+"','"+policy_type+"',"+SSN+")";
									//Reading Data from Insurance Table
									String read_Insurance = "Select * from Insurance_Policy";
									try {
										stmt.executeQuery(sqlInsurance_Submit);
										ResultSet rs2 = stmt.executeQuery(read_Insurance);
										//Loop through result set and extract results of the query
										System.out.println("\nThis is the List of Insurance Policies");
										System.out.println("Policy_ID || Provider_ID || Provider_Address || Insurance_Type || SSN");
										while(rs2.next()) {
											System.out.println(rs2.getString(1) + "||" + rs2.getString(2) + "||" + rs2.getString(3) + "||" + rs2.getString(4) + "||" +rs2.getInt(5));
										}
									}catch(SQLException e) {
										e.printStackTrace();
									}
									NeedsSelect();
									System.out.println("You're entering the needs of the client");
									NeedsSelect();
									String Need = "";
									int Importance = 0;
									boolean optionneed = false;
									//Inserting Data into Needs Table for each Option
									while(!optionneed) {
										String inputneed = getInput("Please select one of the following\n1.Visiting\n2.Shopping\n3.HouseKeeping\n4.Transportation\n5.Yard Work\n6.Food\n7.Anything else\n8.Done Putting needs");
										switch(inputneed) {
										case "1":
											Need = "Visiting";
											String importance = getInput("Please enter the importance of Visiting(1-10):");
											Importance = String_Int_Convert(importance);
											String InsertNeed = "Insert into Needs(SSN, Needs, Importance) values ("+SSN+",'"+Need+"',"+Importance+")";
											try {
												stmt.executeQuery(InsertNeed);
												NeedsSelect();
											}catch(SQLException e) {
												e.printStackTrace();
											}
											break;
										case "2":
											Need = "Shopping";
											String importance1 = getInput("Please enter the importance of Shopping(1-10):");
											Importance = String_Int_Convert(importance1);
											String InsertNeed1 = "Insert into Needs(SSN, Needs, Importance) values ("+SSN+",'"+Need+"',"+Importance+")";
											try {
												stmt.executeQuery(InsertNeed1);
												NeedsSelect();
											}catch(SQLException e) {
												e.printStackTrace();
											}
											break;
										case "3":
											Need = "Housekeeping";
											String importance2 = getInput("Please enter the importance of Housekeeping(1-10):");
											Importance = String_Int_Convert(importance2);
											String InsertNeed2 = "Insert into Needs(SSN, Needs, Importance) values ("+SSN+",'"+Need+"',"+Importance+")";
											try {
												stmt.executeQuery(InsertNeed2);
												NeedsSelect();
											}catch(SQLException e) {
												e.printStackTrace();
											}
											break;
										case "4":
											Need = "Transportation";
											String importance3 = getInput("Please enter the importance of Transportation(1-10):");
											Importance = String_Int_Convert(importance3);
											String InsertNeed3 = "Insert into Needs(SSN, Needs, Importance) values ("+SSN+",'"+Need+"',"+Importance+")";
											try {
												stmt.executeQuery(InsertNeed3);
												NeedsSelect();
											}catch(SQLException e) {
												e.printStackTrace();
											}
											break;
										case "5":
											Need = "Yard Work";
											String importance4 = getInput("Please enter the importance of Yard Work(1-10):");
											Importance = String_Int_Convert(importance4);
											String InsertNeed4 = "Insert into Needs(SSN, Needs, Importance) values ("+SSN+",'"+Need+"',"+Importance+")";
											try {
												stmt.executeQuery(InsertNeed4);
												NeedsSelect();
											}catch(SQLException e) {
												e.printStackTrace();
											}
											break;
										case "6":
											Need = "Food";
											String importance5 = getInput("Please enter the importance of Food(1-10):");
											Importance = String_Int_Convert(importance5);
											String InsertNeed5 = "Insert into Needs(SSN, Needs, Importance) values ("+SSN+",'"+Need+"',"+Importance+")";
											try {
												stmt.executeQuery(InsertNeed5);
												NeedsSelect();
											}catch(SQLException e) {
												e.printStackTrace();
											}
											break;
										case "7":
											String userneed = getInput("Please enter any need apart from the listed");
											String importance6 = getInput("Please enter the importance of "+userneed+"(1-10):");
											Importance = String_Int_Convert(importance6);
											String InsertNeed6 = "Insert into Needs(SSN, Needs, Importance) values ("+SSN+",'"+userneed+"',"+Importance+")";
											try {
												stmt.executeQuery(InsertNeed6);
												NeedsSelect();
											}catch(SQLException e) {
												e.printStackTrace();
											}
											break;
										case "8":
											optionneed = true;
											break;
										}
									}
									break;
								case 2:
									getPersonInput();
									break;
								case 3:
									correctclient = true;
									break;
								default:
									System.out.println("Invalid Option. Please choose a correct option");
									break;
								}
								
						}
						break;
					case 2:
						boolean correctassociate = false;
						//Check if all the Teams are visible and associate them with a Client
						while(!correctassociate)
						{
							ClientSelect();
							String associate_select = getInput("Do you see the Client you want to associate Teams with?\n1.Yes\n2.No");
							int associate_sel = String_Int_Convert(associate_select);
								switch(associate_sel) {
								//Go back to previous Menu
								case 2:
									System.out.println("\nSelect 'Add new Client' in the next menu :)");
									correctassociate = true;
									break;
								//Add a new association between Team and Client
								case 1:
									CaresSelect();
									TeamSelect();
									boolean correctTeamAssociation = false;
									while(!correctTeamAssociation)
									{
										//Choose if you want to add another association or go back to main menu
										String teamassociate_select =getInput("Do you want to add another Association to the list above?\n1.Yes\n2.No");
										int teamassociate_sel = String_Int_Convert(teamassociate_select);
											switch(teamassociate_sel) {
											//Go back to Previous Menu
											case 2:
												System.out.println("\nSelect 'Associate with a team' in the next menu :)");
												correctTeamAssociation = true;
												break;
												//Enter Association
											case 1:
												String sSSN = getInput("Please input one of the SSNs from the Clients Table and not already present in Cares Table");
												int SSN = String_Int_Convert(sSSN);
												String Team_Name = getInput("Please enter the Team Name from the above Team List");
												String Status = "";
												boolean correctstatus = false;
												//Choose options for Status 1.Active or 2.Inactive
												while(!correctstatus) {
													String statusinput = getInput("Please enter Status of the client\n1.Active\n2.Inactive");
													int statusinputint = String_Int_Convert(statusinput);
													switch(statusinputint) {
													case 1:
														Status += "Active";
														correctstatus = true;
														break;
													case 2:
														Status += "Inactive";
														correctstatus = true;
														break;
													default:
														System.out.println("Invalid Input! Please enter the correct option");
														break;
													}
												}
												//Inserting Data into Cares Table
												String sql1="INSERT INTO Cares(SSN, Team_Name, Status) VALUES ('"+SSN+"','"+Team_Name+"', '"+Status+"')";
												//Loop through result set and extract results of the query
												try {
														stmt.executeQuery(sql1);
														CaresSelect();												
												}catch(SQLException e) {
													e.printStackTrace();
												}	
												break;
											default:
												System.out.println("Invalid Option. Please choose a correct option");
												break;
											}
									}	
									break;
								default:
									System.out.println("Invalid Option. Please choose a correct option");
									break;
								}
						}
						break;
					case 3:
						ClientSelect();
						System.out.println("\nYou are Entering Insurance details for the Client");
						String sSSN = getInput("Please select a SSN from above Client Table");
						int InsuranceSSN = String_Int_Convert(sSSN);
						String Policy_ID = getInput("Please enter the Policy ID not present in the above list");
						String Policy_Provider = getInput("Please enter policy Provider for the client");
						String Policy_Provider_address = getInput("Please provide the Address for the Policy Provider");
						String policy_type = "";
						boolean correctpolicy = false;
						while(!correctpolicy) {
							String policy_type_select = getInput("Please type of Policy from the following:\n1.Auto\n2.Life\n3.Health\n4.Home");
							switch(policy_type_select) {
							case "1":
								policy_type = "Auto";
								correctpolicy = true;
								break;
							case "2":
								policy_type = "Life";
								correctpolicy = true;
								break;
							case "3":
								policy_type = "Health";
								correctpolicy = true;
								break;
							case "4":
								policy_type = "Home";
								correctpolicy = true;
								break;
							default:
								System.out.println("Invalid Option. Please choose a correct option");
								break;
							}
						}
						//Inserting Data into Insurance Table
						String sqlInsurance_Submit = "Insert into Insurance_Policy(Policy_ID,Provider_ID,Provider_Address,Insurance_Type,SSN) values ('"+Policy_ID+"','"+Policy_Provider+"','"+Policy_Provider_address+"','"+policy_type+"',"+InsuranceSSN+")";
						//Reading Data from Insurance Table
						String read_Insurance = "Select * from Insurance_Policy";
						try {
							stmt.executeQuery(sqlInsurance_Submit);
							ResultSet rs2 = stmt.executeQuery(read_Insurance);
							//Loop through result set and extract results of the query
							System.out.println("\nThis is the List of Insurances");
							System.out.println("Policy_ID || Provider_ID || Provider_Address || Insurance Type || SSN");
							while(rs2.next()) {
								System.out.println(rs2.getString(1) + "|" + rs2.getString(2) + "|" + rs2.getString(3) + "|" + rs2.getString(4)+ "|" +rs2.getInt(5));
							}
						}catch(SQLException e) {
							e.printStackTrace();
						}
						break;
					case 4:
						NeedsSelect();
						String sSSN1 = getInput("Please enter the SSN of the client you want to input needs");
						int SSN = String_Int_Convert(sSSN1);
						System.out.println("You're entering the needs of the client");
						NeedsSelect();
						String Need = "";
						int Importance = 0;
						boolean optionneed = false;
						//Inserting Data into Needs Table for Each Option
						while(!optionneed) {
							String inputneed = getInput("Please select one of the following\n1.Visiting\n2.Shopping\n3.HouseKeeping\n4.Transportation\n5.Yard Work\n6.Food\n7.Anything else\n8.Done Putting needs");
							switch(inputneed) {
							case "1":
								Need = "Visiting";
								String importance = getInput("Please enter the importance of Visiting(1-10):");
								Importance = String_Int_Convert(importance);
								String InsertNeed = "Insert into Needs(SSN, Needs, Importance) values ("+SSN+",'"+Need+"',"+Importance+")";
								try {
									stmt.executeQuery(InsertNeed);
									NeedsSelect();
								}catch(SQLException e) {
									e.printStackTrace();
								}
								break;
							case "2":
								Need = "Shopping";
								String importance1 = getInput("Please enter the importance of Shopping(1-10):");
								Importance = String_Int_Convert(importance1);
								String InsertNeed1 = "Insert into Needs(SSN, Needs, Importance) values ("+SSN+",'"+Need+"',"+Importance+")";
								try {
									stmt.executeQuery(InsertNeed1);
									NeedsSelect();
								}catch(SQLException e) {
									e.printStackTrace();
								}
								break;
							case "3":
								Need = "Housekeeping";
								String importance2 = getInput("Please enter the importance of Housekeeping(1-10):");
								Importance = String_Int_Convert(importance2);
								String InsertNeed2 = "Insert into Needs(SSN, Needs, Importance) values ("+SSN+",'"+Need+"',"+Importance+")";
								try {
									stmt.executeQuery(InsertNeed2);
									NeedsSelect();
								}catch(SQLException e) {
									e.printStackTrace();
								}
								break;
							case "4":
								Need = "Transportation";
								String importance3 = getInput("Please enter the importance of Transportation(1-10):");
								Importance = String_Int_Convert(importance3);
								String InsertNeed3 = "Insert into Needs(SSN, Needs, Importance) values ("+SSN+",'"+Need+"',"+Importance+")";
								try {
									stmt.executeQuery(InsertNeed3);
									NeedsSelect();
								}catch(SQLException e) {
									e.printStackTrace();
								}
								break;
							case "5":
								Need = "Yard Work";
								String importance4 = getInput("Please enter the importance of Yard Work(1-10):");
								Importance = String_Int_Convert(importance4);
								String InsertNeed4 = "Insert into Needs(SSN, Needs, Importance) values ("+SSN+",'"+Need+"',"+Importance+")";
								try {
									stmt.executeQuery(InsertNeed4);
									NeedsSelect();
								}catch(SQLException e) {
									e.printStackTrace();
								}
								break;
							case "6":
								Need = "Food";
								String importance5 = getInput("Please enter the importance of Food(1-10):");
								Importance = String_Int_Convert(importance5);
								String InsertNeed5 = "Insert into Needs(SSN, Needs, Importance) values ("+SSN+",'"+Need+"',"+Importance+")";
								try {
									stmt.executeQuery(InsertNeed5);
									NeedsSelect();
								}catch(SQLException e) {
									e.printStackTrace();
								}
								break;
							case "7":
								String userneed = getInput("Please enter any need apart from the listed");
								String importance6 = getInput("Please enter the importance of "+userneed+"(1-10):");
								Importance = String_Int_Convert(importance6);
								String InsertNeed6 = "Insert into Needs(SSN, Needs, Importance) values ("+SSN+",'"+userneed+"',"+Importance+")";
								try {
									stmt.executeQuery(InsertNeed6);
									NeedsSelect();
								}catch(SQLException e) {
									e.printStackTrace();
								}
								break;
							case "8":
								optionneed = true;
								break;
							}
						}
						break;
					case 5:
						option2Quit = true;
						break;
					default:
						System.out.println("Invalid Option. Please choose a correct option");
						break;
					}
			}
		}
		//Entering new Volunteers into the system and associating them with a team 
		public static void option3() {		
			boolean correctoption = false;
			while(!correctoption) {
				PersonSelect();
				String inputselect = getInput("Please Choose\n(Please Exit and select option 4 for Entering Month and hours for a volunteer)"+
											"\n1.Enter a new Person\n2.Enter a new Volunteer\n3.Enter Volunteer Status\n4.Exit");
				switch(inputselect) {
				case "1":
					getPersonInput();
					break;
				case "2":
					System.out.println("\nYou are now entering Volunteer Data");
					String sSSN = getInput("Please enter volunteer SSN");
					int SSN = String_Int_Convert(sSSN);
					System.out.println("Please enter the date volunteer joined");
					String Date_joined = getDateSQL();
					System.out.println("Please enter the date Volunteer's Recent Training Course Date");
					String recent_date = getDateSQL();
					String Recent_Location = getInput("Please input the Recent Training Course Recent Training Course Location");
					//Inserting Data into Volunteer Table
					String InsertVolunteer = "Insert into volunteers(SSN, Date_Joined, Recent_Date, Recent_Location) Values ("+SSN+","+Date_joined+","+recent_date+",'"+Recent_Location+"')";
					//Reading Data from Volunteer Table
					String Read_Volunteer = "Select * from volunteers";
					try {
						stmt.executeQuery(InsertVolunteer);
						ResultSet rs2 = stmt.executeQuery(Read_Volunteer);
						//Loop through result set and extract results of the query
						System.out.println("\nThis is the List of Volunteers");
						System.out.println("SSN || Date_Joined || Recent_Date || Recent_Location");
						while(rs2.next()) {
							System.out.println(rs2.getInt(1) + "|" + rs2.getDate(2).toString() + "|" + rs2.getDate(3).toString() + "|" + rs2.getString(4));
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}
					TeamSelect();
					String Team_Name = getInput("Please Enter the team name you want to associate with");
					boolean correctmonth = false;
					int Month=0;
					while(!correctmonth) {
						String Month_choice = getInput("Please enter a month he has worked for:\n1.January\n2.February\n3.March\n4.April\n5.May\n6.June\n7.July\n8.August\n9.September\n10.October\n11.November\n12.December");
						
						switch(Month_choice) {
						case "1":
							Month = 1;
							correctmonth = true;
							break;
						case "2":
							Month = 2;
							correctmonth = true;
							break;
						case "3":
							Month = 3;
							correctmonth = true;
							break;
						case "4":
							Month = 4;
							correctmonth = true;
							break;
						case "5":
							Month = 5;
							correctmonth = true;
							break;
						case "6":
							Month = 6;
							correctmonth = true;
							break;
						case "7":
							Month = 7;
							correctmonth = true;
							break;
						case "8":
							Month = 8;
							correctmonth = true;
							break;
						case "9":
							Month = 9;
							correctmonth = true;
							break;
						case "10":
							Month = 10;
							correctmonth = true;
							break;
						case "11":
							Month = 11;
							correctmonth = true;
							break;
						case "12":
							Month = 12;
							correctmonth = true;
							break;
						default:
							System.out.println("Invalid Option. Please enter a valid option");
							break;
						}
					}
					String hours_per_week = getInput("Please enter the number of hours volunteer has worked for that month");
					int hrs = String_Int_Convert(hours_per_week);
					//Inserting Data into Serves Table
					String Date = "TO_DATE('2016-"+Month+"-01','YYYY-MM-DD')";
					String InsertServes = "Insert into serves(SSN, Team_Name, Month_record, Hours_week) values("+SSN+",'"+Team_Name+"','"+Date+"',"+hrs+")";
					//Reading Data from Serves Table
					String Serves_Select = "Select * from Serves";
					try {
						stmt.executeQuery(InsertServes);
						ResultSet rs2 = stmt.executeQuery(Serves_Select);
						//Loop through result set and extract results of the query
						System.out.println("\nThis is the List of Volunteers Serving on Teams");
						System.out.println("SSN || Team_Name || Month || Hours_per_Week");
						while(rs2.next()) {
							System.out.println(rs2.getInt(1) + "|" + rs2.getString(2) + "|" + rs2.getDate(3).toString() + "|" + rs2.getInt(4));
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}
					boolean correctstatus = false;
					String Status = "";
					while(!correctstatus) {
						String status = getInput("Is the volunter Active or not for that team?\n1.Yes\n2.No");
						switch(status) {
						case "1":
							Status = "Yes";
							correctstatus = true;
							break;
						case "2":
							Status = "No";
							correctstatus = true;
							break;
						default:
							System.out.println("Invalid Input. Please provide Correct input");
							break;
							
						}
					}
					//Inserting Data into Active Table
					String InsertActive = "Insert into Active(SSN, Team_Name, Active) values("+SSN+",'"+Team_Name+"','"+Status+"')";
					//Reading Data from Active Table
					String Active_Select = "Select * from Active";
					try {
						stmt.executeQuery(InsertActive);
						ResultSet rs2 = stmt.executeQuery(Active_Select);
						//Loop through result set and extract results of the query
						System.out.println("\nThis is the List of Volunteers Statuses");
						System.out.println("SSN || Team_Name || Active");
						while(rs2.next()) {
							System.out.println(rs2.getInt(1) + "|" + rs2.getString(2) + "|" + rs2.getString(3));
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}
					break;
				case "3":
					TeamSelect();
					String Read_Volunteer1 = "Select * from volunteers";
					try {
						ResultSet rs2 = stmt.executeQuery(Read_Volunteer1);
						//Loop through result set and extract results of the query
						System.out.println("\nThis is the List of Volunteers");
						System.out.println("SSN || Date_Joined || Recent_Date || Recent_Location");
						while(rs2.next()) {
							System.out.println(rs2.getInt(1) + "|" + rs2.getDate(2).toString() + "|" + rs2.getDate(3).toString() + "|" + rs2.getString(4));
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}
					String sSSN1 = getInput("Please enter SSN of a volunteer");
					int SSN1 = String_Int_Convert(sSSN1);
					String Team_Name1 = getInput("Please enter a Team name");
					boolean correctstatus1 = false;
					String Status1 = "";
					while(!correctstatus1) {
						String status = getInput("Is the volunter Active or not for that team?\n1.Yes\n.No");
						switch(status) {
						case "1":
							Status1 = "Yes";
							correctstatus1 = true;
							break;
						case "2":
							Status1 = "No";
							correctstatus1 = true;
							break;
						default:
							System.out.println("Invalid Input. Please provide Correct input");
							break;
							
						}
					}
					//Inserting Data into Active Table
					String InsertActive1 = "Insert into Active(SSN, Team_Name, Active) values("+SSN1+",'"+Team_Name1+"','"+Status1+"')";
					String Active_Select1 = "Select * from Serves";
					try {
						stmt.executeQuery(InsertActive1);
						ResultSet rs2 = stmt.executeQuery(Active_Select1);
						//Loop through result set and extract results of the query
						System.out.println("\nThis is the List of Volunteers Statuses");
						System.out.println("SSN || Team_Name || Active");
						while(rs2.next()) {
							System.out.println(rs2.getInt(1) + "|" + rs2.getString(2) + "|" + rs2.getString(3));
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}
					boolean teamlead = false;
					while(!teamlead) {
						String options = getInput("\nIs the volunteer also the lead of the team?\n1.Yes\n1.No");
						switch(options) {
						case "1":
							//Inserting Data into Leads Table
							String InsertLeads = "Insert into Leads(SSN, Team_Name) values("+SSN1+",'"+Team_Name1+"')";
							String selectLeads = "Select * from Leads";
							try {
								stmt.executeQuery(InsertLeads);
								ResultSet rs2 = stmt.executeQuery(selectLeads);
								//Loop through result set and extract results of the query
								System.out.println("\nThis is the List of Team Leads");
								System.out.println("SSN || Team_Name");
								while(rs2.next()) {
									System.out.println(rs2.getInt(1) + "|" + rs2.getString(2));
								}
							}catch(SQLException e) {
								e.printStackTrace();
							}
							teamlead = true;
							break;
						case "2":
							teamlead = true;
							break;
						default:
							System.out.println("\nInvalid option. Please enter a correct option");
							break;
						}
					}
					break;
				case "4":
					correctoption = true;
					break;
				}
			}
			
		}
		//Enter Month and Hrs worked by a volunteer for a Team
		public static void option4() {
			TeamSelect();
			String Read_Volunteer = "Select * from volunteers";
			try {
				ResultSet rs2 = stmt.executeQuery(Read_Volunteer);
				//Loop through result set and extract results of the query
				System.out.println("\nThis is the List of Volunteers");
				System.out.println("SSN || Date_Joined || Recent_Date || Recent_Location");
				while(rs2.next()) {
					System.out.println(rs2.getInt(1) + "|" + rs2.getDate(2).toString() + "|" + rs2.getDate(3).toString() + "|" + rs2.getString(4));
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			String Team_Name = getInput("Please Enter the team name you want to associate with");
			boolean correctmonth = false;
			String sSSN = getInput("Please enter the SSN of the Volunteer you want  to associate Team with");
			int SSN = String_Int_Convert(sSSN);
			int Month = 0;
			while(!correctmonth) {
				String Month_choice = getInput("Please enter a month he has worked for:\n1.January\n2.February\n3.March\n4.April\n5.May\n6.June\n7.July\n8.August\n9.September\n10.October\n11.November\n12.December");
				
				switch(Month_choice) {
				case "1":
					Month = 1;
					correctmonth = true;
					break;
				case "2":
					Month = 2;
					correctmonth = true;
					break;
				case "3":
					Month = 3;
					correctmonth = true;
					break;
				case "4":
					Month = 4;
					correctmonth = true;
					break;
				case "5":
					Month = 5;
					correctmonth = true;
					break;
				case "6":
					Month = 6;
					correctmonth = true;
					break;
				case "7":
					Month = 7;
					correctmonth = true;
					break;
				case "8":
					Month = 8;
					correctmonth = true;
					break;
				case "9":
					Month = 9;
					correctmonth = true;
					break;
				case "10":
					Month = 10;
					correctmonth = true;
					break;
				case "11":
					Month = 11;
					correctmonth = true;
					break;
				case "12":
					Month = 12;
					correctmonth = true;
					break;
				default:
					System.out.println("Invalid Option. Please enter a valid option");
					break;
				}
			}
			String hours_per_week = getInput("Please enter the number of hours volunteer has worked for that month");
			int hrs = String_Int_Convert(hours_per_week);
			//Inserting Data into Serves Table
			String Date = "TO_DATE('2016-"+Month+"-01', 'YYYY-MM-DD')";
			String InsertServes = "Insert into serves(SSN, Team_Name, Month_record, Hours_week) values("+SSN+",'"+Team_Name+"',"+Date+","+hrs+")";
			String Serves_Select = "Select * from Serves";
			try {
				stmt.executeQuery(InsertServes);
				ResultSet rs2 = stmt.executeQuery(Serves_Select);
				//Loop through result set and extract results of the query
				System.out.println("\nThis is the List of Volunteers Serving on Teams");
				System.out.println("SSN || Team_Name || Month || Hours_per_Week");
				while(rs2.next()) {
					System.out.println(rs2.getInt(1) + "|" + rs2.getString(2) + "|" + rs2.getDate(3).toString() + "|" + rs2.getInt(4));
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}	
		}
		
		
		
		
		
		//Enter Employee and associate him with Team(s)
		public static void option5() {
			boolean correctoption = false;
			while(!correctoption) {
				PersonSelect();
				String inputselect = getInput("Please Choose\n1.Enter a new Person\n2.Enter a new Employee\n3.Associate an Employee with a team\n4.Exit");
				switch(inputselect) {
				case "1":
					getPersonInput();
					break;
				case "2":
					System.out.println("You are now entering Employee Data");
					String sSSN = getInput("Please enter Employee SSN");
					int SSN = String_Int_Convert(sSSN);
					String sSalary = getInput("Please enter the salary of the employee");
					int salary = String_Int_Convert(sSalary);
					String Marital_Status = getInput("Please enter the Marital Status of the Employee");
					System.out.println("Please enter the hire date of the Employee");
					String hire_date = getDateSQL();
					//Inserting Data into Employee Table
					String InsertEmployee = "Insert into Employee(SSN, Hire_Date, salary, Marital_Status) Values ("+SSN+","+hire_date+","+salary+",'"+Marital_Status+"')";
					String Read_Employee = "Select * from Employee";
					try {
						stmt.executeQuery(InsertEmployee);
						ResultSet rs52 = stmt.executeQuery(Read_Employee);
						//Loop through result set and extract results of the query
						System.out.println("\nThis is the List of Employees");
						System.out.println("SSN || salary || Marital_Status || Hire_Date");
						while(rs52.next()) {
							System.out.println(rs52.getInt(1) + "|" + rs52.getInt(2) + "|" + rs52.getString(3) + "|" + rs52.getDate(4).toString());
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}
					TeamSelect();
					String Team_Name = getInput("Please Enter the team name you want to associate with");
					String descripiton = getInput("Please enter the description of the report");
					System.out.println("Please enter the date of the report");
					String date_report = getDateSQL();
					//Inserting Data into Reporting Table
					String InsertReport = "Insert into Reporting(SSN, Team_Name, Report_Date, Report_Description) values("+SSN+",'"+Team_Name+"',"+date_report+",'"+descripiton+"')";
					String Report_Select = "Select * from Reporting";
					try {
						stmt.executeQuery(InsertReport);
						ResultSet rs521 = stmt.executeQuery(Report_Select);
						//Loop through result set and extract results of the query
						System.out.println("\nThis is the List of Reports");
						System.out.println("SSN || Team_Name || Report_Date || Report_Description");
						while(rs521.next()) {
							System.out.println(rs521.getInt(1) + "|" + rs521.getString(2) + "|" + rs521.getDate(3).toString() + "|" + rs521.getString(4));
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}
					break;
				case "3":
					String Read_Employee1 = "Select * from Employee";
					try {
						ResultSet rs53 = stmt.executeQuery(Read_Employee1);
						//Loop through result set and extract results of the query
						System.out.println("\nThis is the List of Employees");
						System.out.println("SSN || salary || Marital_Status || Hire_Date");
						while(rs53.next()) {
							System.out.println(rs53.getInt(1) + "|" + rs53.getInt(2) + "|" + rs53.getString(3) + "|" + rs53.getDate(4).toString());
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}
					TeamSelect();
					String sSSN1 = getInput("Select one SSN from Employee");
					int SSN1 = String_Int_Convert(sSSN1);
					String Team_Name1 = getInput("Please Enter the team name you want to associate with");
					String descripiton1 = getInput("Please enter the number of hours volunteer has worked for that month");
					System.out.println("Please enter the date of the report");
					String date_report1 = getDateSQL();
					//Inserting Data into Reporting Table
					String InsertReport1 = "Insert into Reporting(SSN, Team_Name, Report_Date, Report_Description) values("+SSN1+",'"+Team_Name1+"',"+date_report1+",'"+descripiton1+"')";
					String Report_Select1 = "Select * from Reporting";
					try {
						stmt.executeQuery(InsertReport1);
						ResultSet rs531 = stmt.executeQuery(Report_Select1);
						//Loop through result set and extract results of the query
						System.out.println("\nThis is the List of Reports");
						System.out.println("SSN || Team_Name || Report_Date || Report_Description");
						while(rs531.next()) {
							System.out.println(rs531.getInt(1) + "|" + rs531.getString(2) + "|" + rs531.getDate(3).toString() + "|" + rs531.getString(4));
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}
					break;
				case "4":
					correctoption = true;
					break;
				}
			}
		}
		
		
		//Enter the Charges by the Employee
		public static void option6() {
			boolean option6 = false;
			while(!option6) {
				String Read_Employee1 = "Select * from Employee";
				try {
					ResultSet rs6 = stmt.executeQuery(Read_Employee1);
					//Loop through result set and extract results of the query
					System.out.println("\nThis is the List of Employees");
					System.out.println("SSN || salary || Marital_Status || Hire_Date");
					while(rs6.next()) {
						System.out.println(rs6.getInt(1) + "|" + rs6.getInt(2) + "|" + rs6.getString(3) + "|" + rs6.getDate(4).toString());
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}
				String optionselect = getInput("Please Choose from the options\n1.Enter new Expense\n2.Exit");
				switch(optionselect) {
				case "1":
					String sSSN = getInput("Select one SSN from Employee");
					int SSN = String_Int_Convert(sSSN);
					System.out.println("Please enter the date of Expense");
					String date_ofExpense = getDateSQL();
					String sAmount = getInput("Please the amount of Expense");
					int amount = String_Int_Convert(sAmount);
					String Expense_Description = getInput("Please enter the description of the expense");
					//Inserting Data into Expenses Table
					String Expense_Submit = "Insert into Expenses(SSN, date_of_expense, amount, expense_description) values("+SSN+","+date_ofExpense+", "+amount+",'"+Expense_Description+"')";
					String Read_Expenses = "Select * from Expenses";
					try {
						stmt.executeQuery(Expense_Submit);
						ResultSet rs61 = stmt.executeQuery(Read_Expenses);
						//Loop through result set and extract results of the query
						System.out.println("\nThis is the List of Employee's Expenses");
						System.out.println("SSN || Expense_date || Amount || Description");
						while(rs61.next()) {
							System.out.println(rs61.getInt(1) + "|" + rs61.getDate(2).toString() + "|" + rs61.getInt(3) + "|" + rs61.getString(4));
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}
					break;
				case "2":
					option6 = true;
					break;
				default:
					System.out.println("Invalid input. Please provide correct input");
				}
			}
		}
		
		
		//Display All Tables
		public static void option7() {
			boolean option7Quit = false;
			while(!option7Quit) {
				String Read_Expenses = "Select * from External_Organization";
				try {
					ResultSet rs7 = stmt.executeQuery(Read_Expenses);
					//Loop through result set and extract results of the query
					System.out.println("\nThis is the List of Organizations");
					System.out.println("Organization Name || Mailing_Address || Phone_Number || Contact_Person");
					while(rs7.next()) {
						System.out.println(rs7.getString(1) + "|" + rs7.getString(2) + "|" + rs7.getLong(3) + "|" + rs7.getString(4));
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}
				String option7select = getInput("\nPlease enter your option below\n1.Enter a new Organization.\n2.Associate Organization with a Team.\n3.Main Menu");
				switch(option7select) {
				case "1":
					String Org_Name = getInput("Please enter Organization Name");
					String Mailing_Address = getInput("Please enter Address of the Organization");
					String lphone = getInput("Please enter phone number of the Organization");
					long phone = String_Long_Convert(lphone);
					String personContact = getInput("Please enter the contact person in the organization");
					//Inserting Data into External_Organization Table
					String sql = "Insert into External_Organization(Organization_Name, Mailing_Address, Phone_Number, Contact_Person) values('"+Org_Name+"', '"+Mailing_Address+"',"+phone+",'"+personContact+"')";
					try {
						stmt.executeQuery(sql);
						ResultSet rs7 = stmt.executeQuery(Read_Expenses);
						System.out.println("\nThis is the List of Organizations");
						System.out.println("Organization Name || Mailing_Address || Phone_Number || Contact_Person");
						while(rs7.next()) {
							System.out.println(rs7.getString(1) + "|" + rs7.getString(2) + "|" + rs7.getLong(3) + "|" + rs7.getString(4));
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}
					break;
				case "2":
					TeamSelect();
					String team_name = getInput("Please enter the Team_Name from the list above");
					String Orga_Name = getInput("Please enter the Organization_Name from the list above");
					//Inserting Data into Sponsor Table
					String InsertSponsor = "Insert into Sponsor(Organization_Name, Team_Name) values('"+Orga_Name+"','"+team_name+"')";
					String ReadSponsor = "Select * from sponsor";
					try {
						stmt.executeQuery(InsertSponsor);
						ResultSet rs7 = stmt.executeQuery(ReadSponsor);
						System.out.println("\nThis is the List of Sponsors");
						System.out.println("Organization_Name || Team_Name");
						while(rs7.next()) {
							System.out.println(rs7.getString(1) + "|" + rs7.getString(2));
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}
					break;
				case "3":
					option7Quit = true;
					break;
				default :
					System.out.println("\nInvalid Input. Please provide correct input");
					break;
				}
			}
		}
		//Enter New Donors into the system
		public static void option8() {
			PersonSelect();
			boolean option8select = false;
			while(!option8select) {
				String option8input = getInput("Please select one from the following\n1.Enter New Person\n2.Enter New Donor\n3.Associate donor with Donations\n4.Main Menu");
				switch(option8input) {
				case "1":
					getPersonInput();
					break;
				case "2":
					String sSSN= getInput("Please enter the SSN of the person who wishes to be a Donor");
					int SSN = String_Int_Convert(sSSN);
					String Anonymous = "";
					boolean correctoption = false;
					while(!correctoption) {
						String input = getInput("Does the Donor wishes to be anonymous?\n1.Yes\n2.No");
						switch(input) {
						case "1":
							Anonymous = "Yes";
							correctoption = true;
							break;
						case "2":
							Anonymous = "No";
							correctoption = true;
							break;
						default :
							System.out.println("Invalid Input. Please provide correct input");
						}
					}
					//Inserting Data into Donors Table
					String sql = "Insert into Donors(SSN, Anonymous) values("+SSN+",'"+Anonymous+"')";
					String sql1 = "Select * from Donors";
					//Loop through result set and extract results of the query
					try {
						stmt.executeQuery(sql);
						ResultSet rs = stmt.executeQuery(sql1);
						System.out.println("\nThis is the Donors Table\n SSN || Anonymous");
						while(rs.next()) {
							System.out.println(rs.getInt(1) + "|" + rs.getString(2));
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}
					String donate_amount = getInput("Please enter the amount Donor wants to donate");
					int amount = String_Int_Convert(donate_amount);
					System.out.println("Please enter the Date of the donation");
					String donate_date = getDateSQL();
					String fund_campaign = getInput("Enter the fundraising Campaign towards which amount is donated");
					String donation_type = "";
					boolean donateoption = false;
					while(!donateoption) {
						String input = getInput("Please enter donation type\n1.Card\n2.Check");
						switch(input) {
						case "1":
							donation_type = "Card";
							String card_number = getInput("Please provide the Card Number");
							int card = String_Int_Convert(card_number);
							String Card_type = getInput("Please enter the card type");
							System.out.println("Please enter the expiry date of the card");
							String expiry_date = getDateSQL();
							//Inserting Data into Donors_Donate Table
							String InsertDonate = "Insert into Donors_Donate(SSN, Donors_Donate_Date, Donors_Donate_Amount, Donors_Donate_Type, Donors_Donate_FCampaign) values("+SSN+","+donate_date+","+amount+",'"+donation_type+"','"+fund_campaign+"')";
							//Inserting Data into Donors_Donate_Card Table
							String InsertDonate_Card = "Insert into donors_donate_card(SSN, Donors_Donate_Date, Donors_Donate_Amount,Donors_Donate_cardno, Donors_Donate_cardtype, Donors_Donate_Expirydate) values("+SSN+","+donate_date+","+amount+","+card+",'"+Card_type+"',"+expiry_date+")";
							String SelectDonors= "Select * from Donors_Donate";
							String SelectDonorsCard = "Select * from Donors_Donate_Card";
							//Loop through result set and extract results of the query
							try {
								stmt.executeQuery(InsertDonate);
								stmt.executeQuery(InsertDonate_Card);
								ResultSet rs81 = stmt.executeQuery(SelectDonors);
								System.out.println("\nThis is the Donors Table\n SSN || Donation Date || Donation Amount || Donation Type || Fundraising Campaign");
								while(rs81.next()) {
									System.out.println(rs81.getInt(1) + "|" + rs81.getDate(2).toString() + "|" +rs81.getInt(3) + "|" + rs81.getString(4) + "|" + rs81.getString(5));
								}
								ResultSet rs82 = stmt.executeQuery(SelectDonorsCard);
								System.out.println("\nThis is the Card Donors Table\n SSN || Donation Date || Donation Amount || Card number || Card Type || Expiry Date");
								while(rs82.next()) {
									System.out.println(rs82.getInt(1) + "|" + rs82.getDate(2).toString() + "|" + rs82.getInt(3) + "|" + rs82.getInt(4) + "|" + rs82.getString(5) + "|" + rs82.getDate(6).toString());
								}
							}catch(SQLException e) {
								e.printStackTrace();
							}
							donateoption = true;
							break;
						case "2":
							donation_type = "Check";
							String check_number = getInput("Please provide the Check Number");
							int check = String_Int_Convert(check_number);
							//Inserting Data into Donors_Donate Table
							String InsertDonates = "Insert into Donors_Donate(SSN, Donors_Donate_Date, Donors_Donate_Amount, Donors_Donate_Type, Donors_Donate_FCampaign) values("+SSN+","+donate_date+","+amount+",'"+donation_type+"','"+fund_campaign+"')";
							//Inserting Data into Donors_Donate_Check Table
							String InsertDonate_Check = "Insert into donors_donate_check(SSN, Donors_Donate_Date, Donors_Donate_Amount,Donors_Donate_checkno) values("+SSN+","+donate_date+","+amount+","+check+")";
							String Select_Donors = "Select * from Donors_donate";
							String SelectDonorsCheck = "Select * from Donors_Donate_Check";
							//Loop through result set and extract results of the query
							try {
								stmt.executeQuery(InsertDonates);
								stmt.executeQuery(InsertDonate_Check);
								ResultSet rs81 = stmt.executeQuery(Select_Donors);
								System.out.println("\nThis is the Donors Table\n SSN || Donation Date || Donation Amount || Donation Type || Fundraising Campaign");
								while(rs81.next()) {
									System.out.println(rs81.getInt(1) + "|" + rs81.getDate(2).toString() + "|" +rs81.getInt(3) + "|" + rs81.getString(4) + "|" + rs81.getString(5));
								}
								ResultSet rs82 = stmt.executeQuery(SelectDonorsCheck);
								System.out.println("\nThis is the Check Donors Table\n SSN || Donation Date || Donation Amount || Check number");
								while(rs82.next()) {
									System.out.println(rs82.getInt(1) + "|" + rs82.getDate(2).toString() + "|" + rs82.getInt(3) + "|" + rs82.getInt(4));
								}
							}catch(SQLException e) {
								e.printStackTrace();
							}
							donateoption = true;
							break;
						default :
							System.out.println("Invalid Input. Please provide correct input");
							break;
						}
					}
					
					break;
				case "3":
					String sSSN1 = getInput("Enter the SSN of the Donor who wants to donate");
					int SSN1 = String_Int_Convert(sSSN1);
					String donate_amount1 = getInput("Please enter the amount Donor wants to donate");
					int amount1 = String_Int_Convert(donate_amount1);
					System.out.println("Pleae enter the Date of the donation");
					String donate_date1 = getDateSQL();
					String fund_campaign1 = getInput("Enter the fundraising Campaign towards which amount is donated");
					String donation_type1 = "";
					boolean donateoption1 = false;
					while(!donateoption1) {
						String input = getInput("Please enter donation type\n1.Card\n2.Check");
						switch(input) {
						case "1":
							donation_type1 = "Card";
							String card_number = getInput("Please provide the Card Number");
							int card = String_Int_Convert(card_number);
							String Card_type = getInput("Please enter the card type");
							System.out.println("Please enter the expiry date of the card");
							String expiry_date = getDateSQL();
							//Inserting Data into Donors_Donate Table
							String InsertDonate = "Insert into Donors_Donate(SSN, Donors_Donate_Date, Donors_Donate_Amount, Donors_Donate_Type, Donors_Donate_FCampaign) values("+SSN1+","+donate_date1+","+amount1+",'"+donation_type1+"','"+fund_campaign1+"')";
							//Inserting Data into Donors_Donate_Card Table
							String InsertDonate_Card = "Insert into donors_donate_card(SSN, Donors_Donate_Date, Donors_Donate_Amount,Donors_Donate_cardno, Donors_Donate_cardtype, Donors_Donate_Expirydate) values("+SSN1+","+donate_date1+","+amount1+","+card+",'"+Card_type+"',"+expiry_date+")";
							String SelectDonors= "Select * from Donors_Donate";
							String SelectDonorsCard = "Select * from Donors_Donate_Card";
							//Loop through result set and extract results of the query
							try {
								stmt.executeQuery(InsertDonate);
								stmt.executeQuery(InsertDonate_Card);
								ResultSet rs81 = stmt.executeQuery(SelectDonors);
								System.out.println("\nThis is the Donors Table\n SSN || Donation Date || Donation Amount || Donation Type || Fundraising Campaign");
								while(rs81.next()) {
									System.out.println(rs81.getInt(1) + "|" + rs81.getDate(2).toString() + "|" +rs81.getInt(3) + "|" + rs81.getString(4) + "|" + rs81.getString(5));
								}
								ResultSet rs82 = stmt.executeQuery(SelectDonorsCard);
								System.out.println("\nThis is the Card Donors Table\n SSN || Donation Date || Donation Amount || Card number || Card Type || Expiry Date");
								while(rs82.next()) {
									System.out.println(rs82.getInt(1) + "|" + rs82.getDate(2).toString() + "|" + rs82.getInt(3) + "|" + rs82.getInt(4) + "|" + rs82.getString(5) + "|" + rs82.getDate(6).toString());
								}
							}catch(SQLException e) {
								e.printStackTrace();
							}
							donateoption1 = true;
							break;
						case "2":
							donation_type1 = "Check";
							String check_number = getInput("Please provide the Check Number");
							int check = String_Int_Convert(check_number);
							//Inserting Data into Donors_Donate Table
							String InsertDonates = "Insert into Donors_Donate(SSN, Donors_Donate_Date, Donors_Donate_Amount, Donors_Donate_Type, Donors_Donate_FCampaign) values("+SSN1+","+donate_date1+","+amount1+",'"+donation_type1+"','"+fund_campaign1+"')";
							//Inserting Data into Donors_Donate_Check Table
							String InsertDonate_Check = "Insert into donors_donate_check(SSN, Donors_Donate_Date, Donors_Donate_Amount,Donors_Donate_checkno) values("+SSN1+","+donate_date1+","+amount1+","+check+")";
							String Select_Donors = "Select * from Donors_Donate";
							String SelectDonorsCheck = "Select * from Donors_Donate_Card";
							//Loop through result set and extract results of the query
							try {
								stmt.executeQuery(InsertDonates);
								stmt.executeQuery(InsertDonate_Check);
								ResultSet rs81 = stmt.executeQuery(Select_Donors);
								System.out.println("\nThis is the Donors Table\n SSN || Donation Date || Donation Amount || Donation Type || Fundraising Campaign");
								while(rs81.next()) {
									System.out.println(rs81.getInt(1) + "|" + rs81.getDate(2).toString() + "|" +rs81.getInt(3) + "|" + rs81.getString(4) + "|" + rs81.getString(5));
								}
								ResultSet rs82 = stmt.executeQuery(SelectDonorsCheck);
								System.out.println("\nThis is the Check Donors Table\n SSN || Donation Date || Donation Amount || Check number");
								while(rs82.next()) {
									System.out.println(rs82.getInt(1) + "|" + rs82.getDate(2).toString() + "|" + rs82.getInt(3) + "|" + rs82.getInt(4));
								}
							}catch(SQLException e) {
								e.printStackTrace();
							}
							donateoption1 = true;
							break;
						default :
							System.out.println("Invalid Input. Please provide correct input");
						}
					}
					break;
				case "4":
					option8select = true;
					break;
				}
			}
		}
		//Find the category and name of the oldest book that has more than 10 editions and was translated by 'Mark'.
		public static void option9() {
			String Select_Organization = "Select * from External_organization";
			boolean option8select = false;
			while(!option8select) {
				try {
					ResultSet rs9 = stmt.executeQuery(Select_Organization);
					System.out.println("\nThis is the Organizations Table\n Organization_Name || Mailing Address || Phone Number || Contact Person");
					while(rs9.next()) {
						System.out.println(rs9.getString(1) + "|" + rs9.getString(2) + "|" + rs9.getLong(3) + "|" + rs9.getString(4));
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}
				String option8input = getInput("Please select one from the following\n1.Enter New Organization into the System\n2.Associate Organization with Donations\n3.Main Menu");
				switch(option8input) {
				case "1":
					String Organization_Name= getInput("Please enter the Organization Name");
					String Mailing_Address = getInput("Please enter the mailing address of the Organization");
					String lPhone = getInput("Please enter the phone number of the Organization");
					long phone = String_Long_Convert(lPhone);
					String Contact_Person = getInput("Please enter the name of the contact person in the Organization");
					String Anonymous = "";
					boolean correctoption = false;
					while(!correctoption) {
						String input = getInput("Does the Donor wish to be anonymous?\n1.Yes\n2.No");
						switch(input) {
						case "1":
							Anonymous = "Yes";
							correctoption = true;
							break;
						case "2":
							Anonymous = "No";
							correctoption = true;
							break;
						default :
							System.out.println("Invalid Input. Please provide correct input");
							break;
						}
					}
					//Inserting Data into External Organization Table
					String Insert_Organization = "Insert into External_Organization(Organization_Name, Mailing_Address, Phone_Number, Contact_Person) values('"+Organization_Name+"','"+Mailing_Address+"',"+phone+",'"+Contact_Person+"')";
					//Inserting Data into Donor_Organization Table
					String Insert_Organization_Donor = "Insert into Organization_Donor(Organization_Name, Anonymous) values('"+Organization_Name+"','"+Anonymous+"')";
					String Select_Organization_Donor = "Select * from Organization_Donor";
					//Loop through result set and extract results of the query
					try {
						stmt.executeQuery(Insert_Organization);
						stmt.executeQuery(Insert_Organization_Donor);
						ResultSet rs91 = stmt.executeQuery(Select_Organization);
						System.out.println("\nThis is the Organizations Table\n Organization_Name || Mailing Address || Phone Number || Contact Person");
						while(rs91.next()) {
							System.out.println(rs91.getString(1) + "|" + rs91.getString(2) + "|" + rs91.getLong(3) + "|" + rs91.getString(4));
						}
						ResultSet rs92 = stmt.executeQuery(Select_Organization_Donor);
						System.out.println("\nThis is the Organizations Donors Table\n Organization_Name || Anonymous");
						while(rs92.next()) {
							System.out.println(rs92.getString(1) + "|" + rs92.getString(2));
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}
					String donate_amount = getInput("Please enter the amount Organization wants to donate");
					int amount = String_Int_Convert(donate_amount);
					System.out.println("Please enter the Date of the donation");
					String donate_date = getDateSQL();
					String fund_campaign = getInput("Enter the fundraising Campaign towards which amount is donated");
					String donation_type = "";
					boolean donateoption = false;
					while(!donateoption) {
						String input = getInput("Please enter donation type\n1.Card\n2.Check");
						switch(input) {
						case "1":
							donation_type = "Card";
							String card_number = getInput("Please provide the Card Number");
							int card = String_Int_Convert(card_number);
							String Card_type = getInput("Please enter the card type");
							System.out.println("Please enter the expiry date of the card");
							String expiry_date = getDateSQL();
							//Inserting Data into Organization_Donate Table
							String InsertDonate = "Insert into Organization_Donate(Organization_Name, Org_Donate_Date, Org_Donate_Amount, Org_Donate_Type, Org_Donate_FCampaign) values('"+Organization_Name+"',"+donate_date+","+amount+",'"+donation_type+"','"+fund_campaign+"')";
							//Inserting Data into Organization_Donate_Card Table
							String InsertDonate_Card = "Insert into Organization_donate_card(Organization_Name, Org_Donate_Date, Org_Donate_Amount,Org_Donate_cardno, Org_Donate_cardtype, Org_Donate_Expirydate) values('"+Organization_Name+"',"+donate_date+","+amount+","+card+",'"+Card_type+"',"+expiry_date+")";
							String SelectOrganization= "Select * from Organization_Donate";
							String SelectOrganizationCard = "Select * from Organization_Donate_Card";
							//Loop through result set and extract results of the query
							try {
								stmt.executeQuery(InsertDonate);
								stmt.executeQuery(InsertDonate_Card);
								ResultSet rs93 = stmt.executeQuery(SelectOrganization);
								System.out.println("\nThis is the Organization Donate Table\n Organization_Name || Donation Date || Donation Amount || Donation Type || Fundraising Campaign");
								while(rs93.next()) {
									System.out.println(rs93.getString(1) + "|" + rs93.getDate(2).toString() + "|" +rs93.getInt(3) + "|" + rs93.getString(4) + "|" + rs93.getString(5));
								}
								ResultSet rs94 = stmt.executeQuery(SelectOrganizationCard);
								System.out.println("\nThis is the Card Organization Table\n Organization_Name || Donation Date || Donation Amount || Card number || Card Type || Expiry Date");
								while(rs94.next()) {
									System.out.println(rs94.getString(1) + "|" + rs94.getDate(2).toString() + "|" + rs94.getInt(3) + "|" + rs94.getInt(4) + "|" + rs94.getString(5) + "|" + rs94.getDate(6).toString());
								}
							}catch(SQLException e) {
								e.printStackTrace();
							}
							donateoption = true;
							break;
						case "2":
							donation_type = "Check";
							String check_number = getInput("Please provide the Check Number");
							int check = String_Int_Convert(check_number);
							//Inserting Data into Organization_Donate Table
							String InsertDonates = "Insert into Organization_Donate(Organization_Name, Org_Donate_Date, Org_Donate_Amount, Org_Donate_Type, Org_Donate_FCampaign) values('"+Organization_Name+"',"+donate_date+","+amount+",'"+donation_type+"','"+fund_campaign+"')";
							//Inserting Data into Organization_Donate_Check Table
							String InsertDonate_Check = "Insert into Organization_donate_check(Organization_Name, Org_Donate_Date, Org_Donate_Amount,Org_Donate_checkno) values('"+Organization_Name+"',"+donate_date+","+amount+","+check+")";
							String SelectOrganizationCheck = "Select * from Organization_Donate_Check";
							String SelectOrganizationDonate = "Select * from Organization_Donate";
							//Loop through result set and extract results of the query
							try {
								stmt.executeQuery(InsertDonates);
								stmt.executeQuery(InsertDonate_Check);
								ResultSet rs95 = stmt.executeQuery(SelectOrganizationDonate);
								System.out.println("\nThis is the Organization Donate Table\n Organization_Name || Donation Date || Donation Amount || Donation Type || Fundraising Campaign");
								while(rs95.next()) {
									System.out.println(rs95.getString(1) + "|" + rs95.getDate(2).toString() + "|" +rs95.getInt(3) + "|" + rs95.getString(4) + "|" + rs95.getString(5));
								}
								ResultSet rs96 = stmt.executeQuery(SelectOrganizationCheck);
								System.out.println("\nThis is the Check Organization Table\n Organization_Name || Donation Date || Donation Amount || Check number");
								while(rs96.next()) {
									System.out.println(rs96.getString(1) + "|" + rs96.getDate(2).toString() + "|" + rs96.getInt(3) + "|" + rs96.getInt(4));
								}
							}catch(SQLException e) {
								e.printStackTrace();
							}
							donateoption = true;
							break;
						default :
							System.out.println("Invalid Input. Please provide correct input");
						}
					}
					break;
				case "2":
					String Organization_Name1 = getInput("Please enter Organization Name from the above list to associate it with a donation");
					String donate_amount1 = getInput("Please enter the amount Organization wants to donate");
					int amount1 = String_Int_Convert(donate_amount1);
					System.out.println("Please enter the Date of the donation");
					String donate_date1 = getDateSQL();
					String fund_campaign1 = getInput("Enter the fundraising Campaign towards which amount is donated");
					String donation_type1 = "";
					boolean donateoption1 = false;
					while(!donateoption1) {
						String input = getInput("Please enter donation type\n1.Card\n2.Check");
						switch(input) {
						case "1":
							donation_type1 = "Card";
							String card_number = getInput("Please provide the Card Number");
							int card = String_Int_Convert(card_number);
							String Card_type = getInput("Please enter the card type");
							System.out.println("Please enter the expiry date of the card");
							String expiry_date = getDateSQL();
							//Inserting Data into Organization_Donate Table
							String InsertDonate = "Insert into Organization_Donate(Organization_Name, Org_Donate_Date, Org_Donate_Amount, Org_Donate_Type, Org_Donate_FCampaign) values('"+Organization_Name1+"',"+donate_date1+","+amount1+",'"+donation_type1+"','"+fund_campaign1+"')";
							//Inserting Data into Organization_Donate_Card Table
							String InsertDonate_Card = "Insert into Organization_donate_card(Organization_Name, Org_Donate_Date, Org_Donate_Amount,Org_Donate_cardno, Org_Donate_cardtype, Org_Donate_Expirydate) values('"+Organization_Name1+"',"+donate_date1+","+amount1+","+card+",'"+Card_type+"',"+expiry_date+")";
							String SelectOrganization= "Select * from Organization_Donate";
							String SelectOrganizationCard = "Select * from Organization_Donate_Card";
							//Loop through result set and extract results of the query
							try {
								stmt.executeQuery(InsertDonate);
								stmt.executeQuery(InsertDonate_Card);
								ResultSet rs93 = stmt.executeQuery(SelectOrganization);
								System.out.println("\nThis is the Organization Donate Table\n Organization_Name || Donation Date || Donation Amount || Donation Type || Fundraising Campaign");
								while(rs93.next()) {
									System.out.println(rs93.getString(1) + "|" + rs93.getDate(2).toString() + "|" +rs93.getInt(3) + "|" + rs93.getString(4) + "|" + rs93.getString(5));
								}
								ResultSet rs94 = stmt.executeQuery(SelectOrganizationCard);
								System.out.println("\nThis is the Card Organization Table\n Organization_Name || Donation Date || Donation Amount || Card number || Card Type || Expiry Date");
								while(rs94.next()) {
									System.out.println(rs94.getString(1) + "|" + rs94.getDate(2).toString() + "|" + rs94.getInt(3) + "|" + rs94.getInt(4) + "|" + rs94.getString(5) + "|" + rs94.getDate(6).toString());
								}
							}catch(SQLException e) {
								e.printStackTrace();
							}
							donateoption1 = true;
							break;
						case "2":
							donation_type1 = "Check";
							String check_number = getInput("Please provide the Check Number");
							int check = String_Int_Convert(check_number);
							//Inserting Data into Organization_Donate Table
							String InsertDonates = "Insert into Organization_Donate(Organization_Name, Org_Donate_Date, Org_Donate_Amount, Org_Donate_Type, Org_Donate_FCampaign) values('"+Organization_Name1+"',"+donate_date1+","+amount1+",'"+donation_type1+"','"+fund_campaign1+"')";
							//Inserting Data into Organization_Donate_Check Table
							String InsertDonate_Check = "Insert into Organization_donate_check(Organization_Name, Org_Donate_Date, Org_Donate_Amount,Org_Donate_checkno) values('"+Organization_Name1+"',"+donate_date1+","+amount1+","+check+")";
							String SelectOrganizationCheck = "Select * from Organization_Donate_Check";
							String Select_Organization_Donor1 = "Select * from Organization_Donor";
							//Loop through result set and extract results of the query
							try {
								stmt.executeQuery(InsertDonates);
								stmt.executeQuery(InsertDonate_Check);
								ResultSet rs95 = stmt.executeQuery(Select_Organization_Donor1);
								System.out.println("\nThis is the Organization Donate Table\n Organization_Name || Donation Date || Donation Amount || Donation Type || Fundraising Campaign");
								while(rs95.next()) {
									System.out.println(rs95.getString(1) + "|" + rs95.getDate(2).toString() + "|" +rs95.getInt(3) + "|" + rs95.getString(4) + "|" + rs95.getString(5));
								}
								ResultSet rs96 = stmt.executeQuery(SelectOrganizationCheck);
								System.out.println("\nThis is the Check Organization Table\n Organization_Name || Donation Date || Donation Amount || Check number");
								while(rs96.next()) {
									System.out.println(rs96.getString(1) + "|" + rs96.getDate(2).toString() + "|" + rs96.getInt(3) + "|" + rs96.getInt(4));
								}
							}catch(SQLException e) {
								e.printStackTrace();
							}
							donateoption1 = true;
							break;
						default :
							System.out.println("Invalid Input. Please provide correct input");
							break;
						}
					}
					break;
				case "3":
					option8select = true;
					break;
				default : 
					System.out.println("Please Enter a valid input");
					break;
				}
			}
		}
		//Retrieve Client's Doctor's Name and Phone number.
		public static void option10() {
			ClientSelect();
			String Required_Item = getInput("Please enter the SSN of the client's Info you need");
			int SSN = String_Int_Convert(Required_Item);
			String sql = "SELECT SSN,DOC_NAME,DOC_PHONE FROM CLIENT WHERE SSN="+SSN+"";
			//Loop through result set and extract results of the query
			try {
				ResultSet rs = stmt.executeQuery(sql);
				System.out.println("Details of the selected Client\nSSN || Doc_Name || Doc_Phone");
				while(rs.next()) {
					System.out.println(rs.getInt(1)+ "|" + rs.getString(2) + "|" + rs.getLong(3));
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}	
		}
		//
		public static void option11() {
			System.out.println("Please enter the start date of the expenses");
			String start_date = getDateSQL();
			System.out.println(start_date);
			System.out.println("Please enter the end date of the expenses");
			String end_date = getDateSQL();
			System.out.println(end_date);
			String sql = "SELECT SSN, SUM(amount) AS Total FROM Expenses where date_of_expense between "+start_date+" and "+end_date+" GROUP BY SSN ORDER BY Total";
			//Loop through result set and extract results of the query
			try {
				ResultSet rs = stmt.executeQuery(sql);
				System.out.println("\nSSN || Total Amount");
				while(rs.next()) {
					System.out.println(rs.getInt(1) + "||" + rs.getInt(2));
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}	
		}
		//Gets the Volunteers serving in Teams caring for a specific Client
		public static void option12() {
			ClientSelect();
			String Required_Item = getInput("Please enter the SSN of the client's Info you need");
			int SSN = String_Int_Convert(Required_Item);
			String sql = "Select Distinct(SSN) from Serves where Team_Name in(Select Team_Name from cares where SSN in(Select SSN from Client where SSN="+SSN+"))";
			//Loop through result set and extract results of the query
			try {
				ResultSet rs = stmt.executeQuery(sql);
				System.out.println("SSN");
				while(rs.next()) {
					System.out.println(rs.getInt(1));
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}	
		}
		//Get Name and Contact info of a client who is cared by the team sponsored by the Organization having names starting between B and K
		public static void option13() {
			String sql = "Select Person_Name, Mailing_Address, Email, Home_Number, Work_Number, Phone_Number from Person where SSN in(Select SSN from Cares where Team_Name in(Select Team_Name from Sponsor where Organization_Name in(Select Organization_Name from External_Organization WHERE substr(Organization_Name,1,1) between 'B' and 'K'))) order by Person_Name";
			//Loop through result set and extract results of the query
			try {
				ResultSet rs = stmt.executeQuery(sql);
				System.out.println("Name || Mailing_Address || Email || Home_Number || Work_Number || Phone_Number");
				while(rs.next()) {
					System.out.println(rs.getString(1) + "||" + rs.getString(2)+ "||" + rs.getString(3)+ "|" + rs.getLong(4)+ "|" + rs.getLong(5)+ "|" + rs.getLong(6));
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}	
		}
		//For each category, display the category and the average number of editions for that category.
		public static void option14() {
			String sql = "SELECT Person.Person_Name, SUM(Donors_Donate.Donors_Donate_Amount) as Total, Donors.Anonymous FROM ((Donors INNER JOIN Donors_Donate ON Donors.SSN = Donors_Donate.SSN) INNER JOIN Person ON Donors.SSN = Person.SSN) where Donors.SSN in(Select SSN from Employee) Group by Donors.Anonymous, Person.Person_Name Order by Total";
			//Loop through result set and extract results of the query
			try {
				ResultSet rs = stmt.executeQuery(sql);
				System.out.println("Person Name || Total Donations ||Anonymous");
				while(rs.next()) {
					System.out.println(rs.getString(1) + "|" + rs.getInt(2) + "|" + rs.getString(3));
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}	
		}
		//Increase the salary of all translators who translated the book titled �Beautiful Mind� by 10%.
		public static void option15() {
			String sql = "Select SSN,Person_Name,Mailing_address, Email, Home_Number, Work_Number, Phone_Number from Person where SSN in (select Distinct(SSN) from(select a.*,rank() over (order by Total desc) r from(select Team_Name,SSN,sum(Hours_Week) as Total from Serves where to_char(Month_Record,'mm') between 3 and 6 group by Team_Name,SSN) a))";
			//Loop through result set and extract results of the query
			try {
				ResultSet rs = stmt.executeQuery(sql);
				System.out.println("\nBelow is the Name and Contact Information of volunteers that have worked most for each team for months between March and June");
				System.out.println("\nSSN || Person Name || Mailing_Address || Email || Home_Number || Work_Number || Phone_Number");
				while(rs.next()) {
					System.out.println(rs.getInt(1) + "|" + rs.getString(2) + "|" + rs.getString(3) + "|" + rs.getString(4) + "|" + rs.getLong(5) + "|" + rs.getLong(6) + "|" + rs.getLong(7));
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}	
		}
		//Delete all customers with the children level.
		public static void option16() {
			String Select_Employee = "Select * from Employee";
			try {
				ResultSet rs161 = stmt.executeQuery(Select_Employee);
				System.out.println("\nSSN || Salary || Marital_Status || Hire_Date");
				while(rs161.next()) {
					System.out.println(rs161.getInt(1) + "|" + rs161.getInt(2) + "|" + rs161.getString(3) + "|" + rs161.getDate(4).toString());
				}
			}catch(SQLException e) {e.printStackTrace();}
			String sql = "Update Employee set Salary=Salary*1.10 where SSN in(Select SSN from Reporting group by SSN having count(Team_Name)>1)";
			//Loop through result set and extract results of the query
			try {
				stmt.executeQuery(sql);
				System.out.println("Salaries Updated");
				try {
					ResultSet rs162 = stmt.executeQuery(Select_Employee);
					System.out.println("\nSSN || Salary || Marital_Status || Hire_Date");
					while(rs162.next()) {
						System.out.println(rs162.getInt(1) + "|" + rs162.getInt(2) + "|" + rs162.getString(3) + "|" + rs162.getDate(4).toString());
					}
				}catch(SQLException e) {e.printStackTrace();}
			}catch(SQLException e) {
				e.printStackTrace();
			}	
		}
		//Delete all the clients who dont have Health Insurance and Transportation Importance is less than 5.
		public static void option17() {
			ClientSelect();
			String sql = "Delete from Client where SSN in(Select SSN from Needs where SSN in (Select SSN from Insurance_Policy where Insurance_Type='Health') and (Needs='Transportation' and Importance<5))";
			//Loop through result set and extract results of the query
			try {
				stmt.executeQuery(sql);
				System.out.println("Query Complete");
				ClientSelect();
			}catch(SQLException e) {
				e.printStackTrace();
			}	
		}
		//Import
		public static void option18() throws SQLException {
			//C:\Users\prith\Desktop\input.txt
			String input = getInput("Please Enter the location of the File");
			String Team_Name;
		    String Team_Type;
		    String Date_Formed;
		    try {
		    	BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(input)));
		        String line = null;
		    	while ((line = bufferedReader.readLine()) != null) {
		    		   	String tmp[]=line.split(":");
					    Team_Name=tmp[0];
					    Team_Type=tmp[1];
					    Date_Formed=tmp[2];
						System.out.println("This is the Read Line | "+Team_Name+"|"+Team_Type+"|"+Date_Formed);
						//Inserting Data into Team Table
						String sql = "INSERT INTO Team (Team_Name,Team_Type,Date_Formed) values ('" + Team_Name + "','" + Team_Type + "',"+Date_Formed+")";
						System.out.println(sql);
						try {
							stmt.executeQuery(sql);
							TeamSelect();
						}catch(SQLException e) {
							e.printStackTrace();
						}	
		    	   }
		    	   bufferedReader.close();
		    	  } catch (IOException e) {
		    	   e.printStackTrace();
		    	  } 
		}
		public static String quote(String s) {
			return "'" + s + "'";
		}
		//Export
		//C:\Users\prith\Desktop\output.txt
		public static void option19() {
			String input = getInput("Please Enter the location of the File");
			File file = new File(input);
			FileWriter fw = null;
			try {
				fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				String sql = "Select Person_Name, Mailing_Address from Person where SUBSCRIPTION='Yes'";
				try {
					
					ResultSet rs = stmt.executeQuery(sql);
					while(rs.next()) {
						bw.write(quote(rs.getString(1)) + "|" +quote(rs.getString(2)) +"\n");
						System.out.println("Printing Line into File "+ rs.getString(1) +" | " +rs.getString(2));
					}
					
				}catch(IOException | SQLException e ) {
					//bw.close();
					e.printStackTrace();
				}finally {
					try {bw.close();} catch (Exception ex) {}
				 }
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		public static int String_Int_Convert(String input) {
			int variable = -1;
			try {
				variable = Integer.parseInt(input.trim());
			}
			catch(Exception e) {}
			return variable;
		}
		public static long String_Long_Convert(String input) {
			long variable = -1;
			try {
				variable = Long.parseLong(input.trim());
			}
			catch(Exception e) {}
			return variable;
		}
		//A function to select all from Persons Table
		public static void PersonSelect() {
			String sql = "SELECT * FROM Person";
			//Loop through result set and extract results of the query
			try {
				ResultSet rs = stmt.executeQuery(sql);
				System.out.println("\nThis is the Persons Table");
				System.out.println("\nSSN||Person_Name || Birthdate date || Race || Gender || Profession || Mailing_Address || Email "+
						"|| Home_number || Work_Number || Phone_Number || Subscription ");
				while(rs.next()) {
					System.out.println(rs.getInt(1) + "||" + rs.getString(2) + "|" + rs.getDate(3).toString() + "|" + rs.getString(4) + "|" + rs.getString(5)+ "" +
							"|" +rs.getString(6) + "|" + rs.getString(7) + "|" + rs.getString(8) + "|" + rs.getLong(9) + "" +
							"|"+ rs.getLong(10) + "|" + rs.getLong(11) + "|" + rs.getString(12));
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}	
		}
		public static void ClientSelect() {
			String sql = "Select * from Client";
			System.out.println(sql);
			try {
				ResultSet rs = stmt.executeQuery(sql);
				System.out.println("\nThis is the Clients Table");
				//Loop through result set and extract results of the query
				System.out.println("\nSSN || Doc_Name || Doc_Number || Att_Name || Att_Phone || Date_Assigned");
				while(rs.next()) {
					System.out.println(rs.getInt(1) + "||" + rs.getString(2) + "|" + rs.getLong(3) + "|" + rs.getString(4)+ "|"+ rs.getLong(3) + "|" + rs.getDate(6).toString());
				}

			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		public static void CaresSelect() {
			String sql = "Select * from Cares";
			try {
				ResultSet rs2 = stmt.executeQuery(sql);
				//Loop through result set and extract results of the query
				System.out.println("\nThis is the Cares Table");
				System.out.println("\nTeam_Name || SSN || Status");
				while(rs2.next()) {
					System.out.println(rs2.getString(1) + "|" + rs2.getInt(2) + "|" + rs2.getString(3));
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}		
		}
		public static void TeamSelect() {
			String sql = "Select * from Team";
			try {
				ResultSet rs2 = stmt.executeQuery(sql);
				//Loop through result set and extract results of the query
				System.out.println("\nThis is the Team List");
				System.out.println("\nTeam_Name || Team_Type || Date_Formed");
				while(rs2.next()) {
					System.out.println(rs2.getString(1) + "|" + rs2.getString(2) + "|" + rs2.getDate(3).toString());
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}		
		}
		public static void NeedsSelect() {
			String sql = "Select * from Needs";
			System.out.println(sql);
			try {
				String sql2 = "Select * from Needs";
				System.out.println("\nThis is the Needs Table");
				ResultSet rs = stmt.executeQuery(sql2);
				//Loop through result set and extract results of the query
				System.out.println("\nSSN || Needs || Importance\n");
				while(rs.next()) {
					System.out.println(rs.getInt(1) + "|" + rs.getString(2) + "|" + rs.getInt(3));
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		//A Function to get all the inputs for the persons table.
		public static void getPersonInput() {
			System.out.println("You are entering details for Person");
			String sSSN = getInput("Please Enter SSN:");
			int SSN = String_Int_Convert(sSSN);
			String Person_Name = getInput("Enter Person's Name");
			System.out.println("Please enter the birthdate of the Person");
			String BirthDate = getDateSQL();
			boolean correctrace = false;
			String race= "";
			while(!correctrace) {
				String sRace = getInput("Please select race of the person:\n1.American\n2.Asian\n3.Middle East\n4.Europe\n5.Africa");
				int race_select = String_Int_Convert(sRace);
				switch(race_select) {
					case 1:
						race = "American";
						correctrace= true;
						break;
					case 2:
						race="Asian";
						correctrace=true;
						break;
					case 3:
						race = "Middle East";
						correctrace= true;
						break;
					case 4:
						race="Europe";
						correctrace=true;
						break;
					case 5:
						race = "Africa";
						correctrace= true;
						break;
					default:
						System.out.println("Please enter a correct choice");
						break;		
				}
			}
			boolean correctgender = false;
			String gender = "";
			while(!correctgender) {
				String sGender = getInput("Please enter the gender of the person\n1. Male\n2.Female");
				int Gender_select = String_Int_Convert(sGender);
				switch(Gender_select) {
					case 1:
						gender = "Male";
						correctgender= true;
						break;
					case 2:
						gender="Female";
						correctgender=true;
						break;
					default:
						System.out.println("Please enter a correct choice");
						break;		
				}
			}
			String Profession= getInput("Please Enter Profession of the person");
			String Mailing_Address = getInput("Please enter the mailing address of the person");
			String Email = getInput("Please Enter the Email ID of the person");
			String Home_Number = getInput("Enter the Home number of the person");
			long home_number = String_Long_Convert(Home_Number);
			String Work_Number = getInput("Enter the Work number of the person");
			long work_number = String_Long_Convert(Work_Number);
			String Phone_Number = getInput("Enter the Phone number of the person");
			long phone_number = String_Long_Convert(Phone_Number);
			boolean correctsub = false;
			String subsription = "";
			while(!correctsub) {
				String sSubscription = getInput("Is the person subscribed to the mailing list?\n1.Yes\n2.No");
				int sub_select = String_Int_Convert(sSubscription);
				switch(sub_select) {
					case 1:
						subsription = "Yes";
						correctsub= true;
						break;
					case 2:
						subsription="No";
						correctsub=true;
						break;
					default:
						System.out.println("Please enter a correct choice");
						break;		
				}
			}
			//Inserting Data into Persons Table
			String sql="INSERT INTO Person(SSN,Person_Name,Birthdate,Race,Gender,Profession,Mailing_Address,Email,Home_number,Work_Number,Phone_Number,Subscription) VALUES("+SSN+",'"+Person_Name+"', "+BirthDate+",'"+race+"', '"+gender+"', '"+Profession+"','"+Mailing_Address+"', '"+Email+"',"+home_number+","+work_number+","+phone_number+" ,'"+subsription+"')";
			System.out.println(sql);
			try {
				stmt.executeQuery(sql);
				System.out.println("\nInformation has been added to the persons table");
				PersonSelect();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		
} 

