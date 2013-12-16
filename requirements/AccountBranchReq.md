Account Branch Service Requirements
====================================
---
Overview
--------
The branches associated to an account
Service Type
------------
The service is capable of performing the following actions
#### Create a new branch
* **requried:** account number, branch id, branch name, branch address, Contact of Account Payable, Check Acceptable, Billing Address, Resale Number, Tax Class, Bedrosians Store, Bedrosians Sales
* **note:**

#### Look up a branch
* **required:** none
* **note:**
	1. list all branches within an account
	2. only limited fields are available for customer

#### Update a branch
* **required:** account number and branche id
* **note:**
	1. only limited fields are available for customer

#### Delete a branch
* **required:**
* **note:** is this action allowed?

Account Data
------------
- account number
	- the account this branch belongs to
- branch id
	- must be unique
	- alphabet and numeric characters only
	- 4 characters maximum
- branch name
	- 35 characters maximum
- branch address
	- address line 1 (required for CREATE action)
		- 30 characters maximum
	- address line 2
		- 30 characters maximum
	- city (required for CREATE action)
		- 25 characters maximum
	- country
		- 4 characters maximum
	- state (required for CREATE action)
		- 2 characters maximum
	- zip code
		- 10 characters maximum
	- phone number
		- type: work, mobile, fax or custom
		- 10 entries maximum
- Check Acceptable (NotBlockCheck)
	- boolean
- Billing Address (B2FromCorp)
	- boolean
		- yes: use the corporate address for billing
		- no: use the branch address for billing
- Shipping Address
- Account Receivable Rep
	- Bedrosians employee
- Contract Licence Number
	- 25 characters maximum
- List of Resale Info
	- resalse number (required for CREATE action)
	- mantra number
	- expireation date
	- certificate (pdf file uploaded)
	- since day 1
- Customer Type
	- Type Code `select * from armbrgrp;`
		- AI: ARCHITECT/INT. DESIGNER 
		- GC: ENERAL CONTRACTOR
		- SC: TILE/SPECIALTY CONTRACTOR
		- BD: BUILDER/DEVELOPER
		- DC: DESIGN CENTER
		- HM: HOME STORE
		- IR: INTERNET RETAILER
		- FB: FABRICATOR
		- DT: DISTRIBUTOR
		- RT: RETAIL
		- PC: POOL CONTRACTOR
		- LC: LANDSCAPE CONTRACTOR
		- AD: AEC DEALER
		- DL: FLOOR COVERING/TILE/STONE DEALER
		- AC: AEC DEALER CLOSED
		- Other
- Shipping method
	- options
		- AAA: AAA Cooper
		- ATLS: Altas Freight
		- ATRX: Action Trans EX
		- BAX: Bax Global
		- BBS: Bedrosian Truck
		- BENT: Benton Express
		- BEST: Bestway Freight
		- BOAT: Boat on Ocean
		- BOB: Papazian Express
		- BULL: Bullocks
		- CENT: Central Trans
		- CHOR: Eddie's Express
		- CROB: C.H. Robinson
		- CSI: CSI
		- C/T: Company Truck
		- CWX: Conway Express
		- DIA: Diamond Line
		- DISA: Di Salvo
		- DRCK: Dry Creek Trans
		- EFS: Eastern Freight
		- EPX: EPX
		- ESTE: Estes Freight
		- FAR: Farwest
		- FEDX: Federal Express
		- FFDT: FFDT
		- FSTN: Fastenal
		- GARR: Garret Freight
		- GEBE: GEBE Freight
		- GREY: Greyhound Bus
		- HERC: Hercules Frt
		- ICX: Icx Freight
		- IND: Industrial
		- KEY: Key Trucking
		- KEYS: Keys Courier
		- LBRT: Liberty Frieght
		- LTL: Less Than Truck
		- MATS: Matson
		- MIKE: Mikes Trucking
		- MOL: Molerway
		- MOOR: Moore Trucking
		- MWM: Midwest Motor
		- NOTE: See Notes
		- NPAR: Northpark
		- NWST: Northwest Ship
		- OLD: Old Dominion
		- OREG: Oregon Frt Ways
		- PENN: Peninsula Frt
		- PGA: Pga Trucking
		- PMT: PMT
		- RAC: RAC
		- RITC: Ritchie Truckin
		- R&L: R & L
		- RME: Rogers Mot. Exp
		- ROAD: Roadway Exp
		- ROS: Rosales
		- RWAY: Riteway
		- SAIA: Saia Trucking
		- SMIS: Smiser Freight
		- SMIT: Smith Transport
		- SOAK: So Alaska Frwrd
		- SON: Soniq
		- SOUT: South Eastern E
		- SPEN: Spencer
		- STER: Sterling
		- TRCK: Trucking Co.
		- UPS: United Parcel
		- UPS2: 2nd day Air
		- UPS3: 3 Day Select
		- UPSN: Ups Nxt Day Air
		- USF: USF Dugan
		- USPS: US Postal
		- VIK: Viking Freight
		- WILS: Wilson Trucking
		- YELL: Yellow Freight
- AuthOnly
	- boolean
- Tax Class
	- options
		- Exempt
		- Resale
		- Tax
- Statement Delivery Method
	- options
		- Email
		- Mail
- Invoice Delivery Method
	- options
		- Email
		- FAX
		- Mail
- Statement Number
	- numeric(4,0)
- Bedrosians Store
	- store number
	- numeric(3,0)
- Bedrosians Sales
	- Bedrosians employee (look up by using employee id)
- Statemnt Record
	- statement amount
	- statement delivery date
	- statement delivery method
	- since day 1
- Invoice Record
	- invoice delivery date
	- invoice delivery method
	- since day 1
- Batch Invoice
	- boolean
- Print Statment
	- boolean
- Cust Group (need to finialize its function)
- Branch Contacts
	- must have Account Payable
	- pull data from Account Contacts from Account service
- Customer Type
	- n entries, 1 < n <= 5
- Branch establised date
	- initila value of current date is automatically generated when account is created
	- not editable once saved
- First Sold Date
- List of Notes
	- Date
	- Notes
	- the person who created the note
	- Status
		- possbile values?
	- since day 1
- Activity Status
	- Y: Yes
	- D: delete, after the account has not made purchase for 2 years 

