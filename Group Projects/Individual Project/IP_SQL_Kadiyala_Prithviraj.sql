Create Table Person( 
    SSN int,
    Person_Name varchar(40),
    Birthdate date,
    Race varchar(40),
    Gender varchar(20),
    Profession varchar(20),
    Mailing_Address varchar(100),
    Email varchar(30),
    Home_number number,
    Work_Number number,
    Phone_Number number,
    Subscription varchar(5),
    primary key (SSN)
    );
    
Create Table Emergency( 
    SSN int,
    E_Name varchar(40),
    Relationship varchar(40),
    E_Mailing_Address varchar(100),
    E_Email varchar(30),
    E_Home_number number,
    E_Work_Number number,
    E_Phone_Number number,
    primary key (SSN, E_Phone_Number),
    foreign key (SSN) references Person(SSN) on delete cascade
    );

Create Table Client( 
    SSN int,
    Doc_Name varchar(40),
    Doc_Phone number,
    Attorney_Name varchar(40),
    Attorney_Phone number,
    date_assigned date,
    primary key (SSN),
    foreign key (SSN) references Person(SSN) on delete cascade
    );

Create Table Needs( 
    SSN int,
    Needs varchar(20),
    Importance int,
    primary key (SSN,Needs),
    foreign key (SSN) references Client(SSN) on delete cascade
    );

Create Table Insurance_Policy( 
    Policy_ID varchar(20),
    Provider_ID varchar(20),
    Provider_Address varchar(100),
    Insurance_Type varchar(20),
    SSN int,
    foreign key (SSN) references Client(SSN) on delete cascade,
    primary key (Policy_ID)
    );
    
Create Table Team( 
    Team_Name varchar(40),
    Team_Type varchar(40),
    Date_Formed date,
    primary key (Team_Name)
    );
    
Create Table Cares( 
    Team_Name varchar(40),
    SSN int,
    Status varchar(10),
    primary key (SSN, Team_Name),
    foreign key (SSN) references Client(SSN) on delete cascade,
    foreign key (Team_Name) references Team(Team_Name) on delete cascade
    );

Create Table Volunteers( 
    SSN int,
    Date_Joined date,
    Recent_Date date,
    Recent_Location varchar(20),
    primary key (SSN),
    foreign key (SSN) references Person(SSN) on delete cascade
    );

Create Table Employee( 
    SSN int,
    salary int,
    marital_Status varchar(20),
    Hire_Date date,
    primary key (SSN),
    foreign key (SSN) references Person(SSN) on delete cascade
    );
    
Create Table Donors( 
    SSN int,
    Anonymous varchar(10),
    primary key (SSN),
    foreign key (SSN) references Person(SSN) on delete cascade
    );
    
Create Table Serves( 
    SSN int,
    Team_Name varchar(40),
    Month_record date,
    hours_week int,
    primary key (SSN, Team_Name,Month_record),
    foreign key (SSN) references Person(SSN) on delete cascade,
    foreign key (Team_Name) references Team(Team_Name) on delete cascade
    );
    
Create Table Leads( 
    SSN int,
    Team_Name varchar(40),
    primary key (SSN),
    foreign key (SSN) references Person(SSN) on delete cascade,
    foreign key (Team_Name) references Team(Team_Name) on delete cascade
    );
    
Create Table Active( 
    SSN int,
    Team_Name varchar(40),
    Active varchar(10),
    primary key (SSN),
    foreign key (SSN) references Person(SSN) on delete cascade,
    foreign key (Team_Name) references Team(Team_Name) on delete cascade
    );
    
Create Table Expenses( 
    SSN int,
    date_of_expense date,
    amount int,
    expense_description varchar(100),
    primary key (SSN, date_of_expense, amount, expense_description),
    foreign key (SSN) references Employee(SSN) on delete cascade
    );
    
Create Table Reporting( 
    SSN int,
    Team_Name varchar(40),
    report_date date,
    report_description varchar(100),
    primary key (Team_Name,SSN),
    foreign key (SSN) references Employee(SSN) on delete cascade,
    foreign key (Team_Name) references Team(Team_Name) on delete cascade
    );
    
Create Table Donors_Donate( 
    SSN int,
    donors_donate_date date,
    donors_donate_amount int,
    donors_donate_type varchar(20),
    donors_donate_fcampaign varchar(30),
    primary key (SSN,donors_donate_date ,donors_donate_amount),
    foreign key (SSN) references Donors(SSN) on delete cascade
    );
    
Create Table Donors_Donate_Check( 
    SSN int,
    donors_donate_date date,
    donors_donate_amount int,
    donors_donate_checkno varchar(20),
    PRIMARY KEY(SSN,donors_donate_date, donors_donate_amount, donors_donate_checkno),
    foreign key (SSN,donors_donate_date, donors_donate_amount) references Donors_Donate(SSN,donors_donate_date ,donors_donate_amount) on delete cascade
    );

Create Table Donors_Donate_Card( 
    SSN int,
    donors_donate_date date,
    donors_donate_amount int,
    donors_donate_cardno int,
    donors_donate_cardtype varchar(30),
    donors_donate_expirydate date,
    PRIMARY KEY(SSN, donors_donate_date, donors_donate_amount, donors_donate_cardno),
    foreign key (SSN,donors_donate_date, donors_donate_amount) references Donors_Donate(SSN,donors_donate_date ,donors_donate_amount) on delete cascade
    );

Create table External_Organization(
    Organization_Name varchar(40),
    Mailing_Address varchar(100),
    Phone_number number,
    Contact_Person Varchar(40),
    primary key(Organization_Name)
);    
create table Affiliates(
    SSN int,
    Organization_Name varchar(40),
    primary key(SSN),
    foreign key (SSN) references Person(SSN) on delete cascade,
    foreign key(Organization_Name) references External_Organization(Organization_Name) on delete cascade
);

create table church(
    Organization_Name varchar(40),
    Religious_Affiliation varchar(20),
    primary key(Organization_Name),
    foreign key (Organization_Name) references External_Organization(Organization_Name) on delete cascade
);

create table Business(
    Organization_Name varchar(40),
    Business_type varchar(20),
    Business_size varchar(20),
    Company_Website varchar(30),
    primary key(Organization_Name),
    foreign key (Organization_Name) references External_Organization(Organization_Name) on delete cascade
);

create table sponsor(
    Organization_Name varchar(40),
    Team_Name varchar(40),
    primary key(Organization_Name, Team_Name),
    foreign key (Organization_Name) references External_Organization(Organization_Name)on delete cascade,
    foreign key (Team_Name) references Team(Team_Name)on delete cascade
);

create table Organization_Donor(
    Organization_Name varchar(40),
    Anonymous varchar(10),
    primary key(Organization_Name),
    foreign key (Organization_Name) references External_Organization(Organization_Name)on delete cascade
);

Create Table Organization_Donate( 
    Organization_Name varchar(40),
    Org_donate_date date,
    Org_donate_amount int,
    Org_donate_type varchar(20),
    Org_donate_fcampaign varchar(30),
    primary key (Organization_Name,Org_donate_date ,Org_donate_amount),
    foreign key (Organization_Name) references Organization_Donor(Organization_Name) on delete cascade
    );
    
Create Table Organization_Donate_Check( 
    Organization_Name varchar(40),
    Org_donate_date date,
    Org_donate_amount int,
    Org_donate_checkno varchar(20),
    PRIMARY KEY(Organization_Name,Org_donate_date, Org_donate_amount, Org_donate_checkno),
    foreign key (Organization_Name,Org_donate_date, Org_donate_amount) references Organization_Donate(Organization_Name,Org_donate_date ,Org_donate_amount) on delete cascade
    );

Create Table Organization_Donate_Card( 
    Organization_Name varchar(40),
    Org_donate_date date,
    Org_donate_amount int,
    Org_donate_cardno int,
    Org_donate_cardtype varchar(30),
    Org_donate_expirydate date,
    PRIMARY KEY(Organization_Name, Org_donate_date, Org_donate_amount, Org_donate_cardno),
    foreign key (Organization_Name,Org_donate_date, Org_donate_amount) references Organization_Donate(Organization_Name,Org_donate_date ,Org_donate_amount) on delete cascade
    );

create index secondary_Insurance On Insurance_Policy(Insurance_Type);

create index Secondary_Serves_Month On Serves(Month_Record);

create index Secondary_Cares_Team_Name On Cares(Team_Name);

create index Secondary_Donors_Donate_SSN On Donors_Donate(SSN);

create index Secondary_Reporting_SSN On Reporting(SSN);

create index Secondary_Sponsor_Org_Name On Sponsor(Organization_Name);

create index Secondary_Expenses_Date On Expenses(DATE_OF_EXPENSE);