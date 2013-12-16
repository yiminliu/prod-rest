Account Service Requirements
==============================

---

Overview
--------
The account service manages Bedrosians customer accounts information. The service will be used for Bedrosians and Customer to manage the account information
Service Type
------------
The service is capable of performing the following actions
#### Create a new account
* **required:**  acount name, account number, account address, owner's info, account contact (admin), Credit Limit, Credit Status, Credit Ar Note, paper statement mailing frequency, email statement mailing frequency, paper invoice mailing frequency, email invoice mailing frequency, Payment term, PO Required, Pre Lien, Late Payment Charge, Price on Invoice.  
* **note:** 
	1. $Branch_Readonly only allows users with UC_ArCreateCustCd of "Y" status to edit.  These users can create ALL accounts (credit, cash and layaway). If UC_CreateCustCd is "C", users can only create CASH account.  If UC_ArCreateCustCd is "N", users cannot create any type of account.
	2. not available for customer 

#### Look up an account
* ** Criteria search **  
	* return the account infomaion in account number, account name, account type (cashe or credit), activity status, account address in city, account address in state, and account address postal code  
	
	* ** Resource URL **  
		http://hostname:port/BedOrderBillingService/accounts?{parameters}  

	* ** Parameters **  
	
		*** accountName *** (optional)  
		example value: tile
		
		---
		*** addressStreetLine1 *** (optional)  
		example value: state%20college
		
		---
		*** addressCity *** (optional)  
		example value: anaheim
		
		---
		*** addressState *** (optional)  
		example value: ca
		
		---
		*** addressZip *** (optional)  
		example value: 92806
		
		---
		*** caseNo *** (optional)  
		example value: c123456
		
		---
		*** ownerFirstName *** (optional)  
		example value: larry
		
		---
		*** ownerLastName *** (optional)  
		example value: hajali
		
		---
		*** ownerDriverLicenseNo *** (optional)  
		example value: A123456
		
		---
		*** phoneNo *** (optional)  
		example value: 7147784616
		
		---
		*** activityStatus *** (required)  
		possible values: all, active, inactive
		
		---
		 
	* ** Example request **  
		GET	http://hostname:port/BedOrderBillingService/accounts?accountName=tile&addressState=ca&activityStatus=active
		
			200 OK
			
			[
				{
					"accountId": "28315",
					"accountName": "TILE AND STONE CONCEPTS",
					"creditStatus": "CASH",
					"activityStatus": "Y",
					"addressCity": "SAN RAFAEL",
					"addressState": "CA",
					"addressZip": "94901",
				},
				}
					"accountId": "26818",
					"accountName": "STONE AGE TILE",
					"creditStatus": "CASH",
					"activityStatus": "Y",
					"addressCity": "ANAHEIM",
					"addressState": "CA",
					"addressZip": "92806",
				{
			]

	* ** Error messages **  
		no account was found with the provided input
		
			404 Not Found
		
			no content will be returned
			
		invalid parameter was enetered
		
			400 Bad Request
		
			{"errors": [{"message": "invalid paraeter addressZip"]}		

		
* ** Exact search **  
	* return exact one account with its relevant datas 
	
	* ** Resource URL **  
	http://hostname:port/BedOrderBillingService/accounts/{account_id}
	
	* ** Path Values **  
	
		*** account_id ***  
		example value: 26818
		
		---
	
	* ** Example Request **  
		GET http://hostname:port/BedOrderBillingService/accounts/26818  
	
			200 OK
			
			{
				"accountId": "26818",
				"accountName": "STONE AGE TILE",
				"dba": "STONE AGE TILE",
				"addressStreetLine1": "1701 SOUTH STATE COLLEGE",
				"addressStreetLine2": "BLVD",
				"addressCity": "ANAHEIM",
				"addressState": "CA",
				"addressPostalCode": "92806",
				"addressCountry": "USA",
				"phoneNumbers": [
					{
						"type": "office",
						"number": "7147049293",
						"extension": "123"
					},
					{
						"type": "fax",
						"number": "7147049294",
						"extension": ""
					}
				],
				"activityStatus": "Y",
				"vendorNumber": 0,
				"isSlabStore": "No",
				"judgementDate": "",
				"buyingGroup": "None",
				"vendorNumber": "123456789",
				"contractorNumber": "123456789",
				"ownerFirstName": "LARRY",
				"ownerLastName": "HAJALI",
				"ownerDriverLicenseNo": "A1234567",
				"treatAsStore": "No",
				"websiteURL": "www.stoneage.com",
				"notes": [
					{
						"content": "this is a note 003",
						"status": "Sent Demands",
						"isCreatedByBedrosians": "Y",
						"createdBY": "Alex",
						"creatorId": 2460,
						"createdTime": "2013-07-16 15:43:23"
					},
					{
						"content": "this is a note 002",
						"status": "Agency",
						"isCreatedByBedrosians": "N",
						"createdBY": "Eric",
						"creatorId": 1523,
						"createdTime": "2013-07-10 09:20:12"
					},
					{
						"content": "this is a note 001",
						"status": "FollowUp",
						"isCreatedByBedrosians": "Y",
						"createdBY": "Edith",
						"creatorId": 1335,
						"createdTime": "2013-07-09 17:20:56"
					},
				],
				"paperStatementMailingFrequency": "No",
				"emailStatementMailingFrequency": "EOM",
				"paperInvoiceMailingFrequency": "No",
				"emailInvoiceMailingFrequency": "D1",
				"noneSufficientChecks": [
					{
						"fileLocation": "path/to/file"
					},
					{
					"fileLocation": "path/to/file"
					}
				]
				"creditLimit": "20000",
				"lockHold": "No",
				"creditStatus": "OPEN",
				"creditARNote": "OK",
				"paymentTerm": "10TH",
				"bankruptcyCaseNumber": "",
				"PORequired": "No",
				"jointCheck": "No",
				"preLienRequired": "YES",
				"preLienLimit": "7000",
				"latePaymentCharge": "No",
				"latePaymentChargeRate": "2.00",
				"lastCreditApprovalDate": "2000-01-01",
				"isVoucherAccount": "No",
				"isConsolidatedPayment": "No",
				"priceOnInvoice": "Yes"
				"requireJobInfo": "Yes",
				"accountEstablishedDate": "2000-01-01",
				"accountSales": {
					"firstName": "Tyler",
					"lastName": "Morrow",
					"title": "Outside Sales Rep",
					"salesNumber": "2531",
					"storeNumber": "125",
					"phone": "714-333-55555",
					"email": "tyler.morrow@bedrosians.com"
				}
				"accountManager": "PATRICA"
				"Contacts": [
					{
						"firstName": "ARIANA",
						"lastName": "SMITH",
						"title": "AP",
						"email": "AP@SATILE.COM",
						"phoneNumbers": [
							{
								"type": "cell",
								"number": "7147049294",
								"extension": ""
							},
							{
								"type": "desktop",
								"number": "7141234567",
								"extension": "111"
							}
						]
					},
					{
						"firstName": "Raleigh",
						"lastName": "Becket",
						"title": "CFO",
						"email": "CFO@SATILE.COM",
						"phoneNumbers": [
							{
								"type": "cell",
								"number": "7141111234",
								"extension": ""
							},
							{
								"type": "office",
								"number": "7142325555",
								"extension": "123"
							}
						]
					},
					{
						"firstName": "Newton",
						"lastName": "Geiszler",
						"title": "PURCHASE",
						"email": "PURCHASE@SATILE.COM",
						"phoneNumbers": [
							{
								"type": "office",
								"number": "7143451123",
								"extension": "105"
							}
						]
					}
				],
				branches: [
					{
						"branchId": "1",
						"branchName" "STONE AGE TILE",
						"branchAddressCity": "ANAHEIM",
						"branchAddressState": "CA"
					},
					{
						"branchId": "2",
						"branchName" "STONE AGE TILE",
						"branchAddressCity": "SAN JOSE",
						"branchAddressState": "CA"
					}
				]
			}
	* ** Error messages ** 
		no account was found with the provided input
			
			404 Not Found
		
			no content will be returned
		invalid path value was entered
		
			400 Bad Request
		
			{"errors": [{"message": "invalid account id"]}

#### Update account information
* **required:** account number  
* **note:**
	1. credit user can update the credit account, cash user cannot; cash user can only update cash account
		* credit user editable fields
			* Arm_CoName.readOnly = false;
   			* Arm_CoAddr1.readOnly = false;
   			* Arm_CoAddr2.readOnly = false;
   			* Arm_City.readOnly = false;
   			* Arm_StateCd.readOnly = false;
   			* Arm_Zip.readOnly = false;
   			* Arm_CountryCd.readOnly = false;
   			* Arm_InactiveCd.readOnly = false;
   			* Arm_OwnerFirstName.readOnly = false;
   			* Arm_OwnerLastName.readOnly = false;
   			* Arm_OwnerDriverLicNbr.readOnly = false;
   			* Arm_ApContact.readOnly = false;
   			* Arm_ApPhone.readOnly = false;
   			* Arm_ApExt.readOnly = false;
   			* Arm_ApCellPhone.readOnly = false;
   			* Arm_ApFax.readOnly = false;
   			* Arm_ApEmail.readOnly = false;
   			* Arm_ApNotes.readOnly = false;
   			* Arm_CeoContact.readOnly = false;
   			* Arm_CeoPhone.readOnly = false;
   			* Arm_CeoExt.readOnly = false;
   			* Arm_CeoCellPhone.readOnly = false;
   			* Arm_CeoFax.readOnly = false;
   			* Arm_CeoEmail.readOnly = false;
   			* Arm_CeoNotes.readOnly = false;
   			* Arm_CfoContact.readOnly = false;
   			* Arm_CfoPhone.readOnly = false;
   			* Arm_CfoExt.readOnly = false;
   			* Arm_CG_Credit.readOnly = false;
   			* Arm_CfoCellPhone.readOnly = false;
   			* Arm_CfoFax.readOnly = false;
   			* Arm_CfoEmail.readOnly = false;
   			* Arm_CfoNotes.readOnly = false;
   			* Arm_PurContact.readOnly = false;
   			* Arm_PurPhone.readOnly = false;
   			* Arm_PurExt.readOnly = false;
   			* Arm_PurCellPhone.readOnly = false;
   			* Arm_PurFax.readOnly = false;
   			* Arm_PurEmail.readOnly = false;
   			* Arm_PurNotes.readOnly = false;
   			* Arm_Dba.readOnly = false;
   			* Arm_SlsPhone.readOnly = false;
   			* Arm_SlsExt.readOnly = false;
   			* Arm_SlsCellPhone.readOnly = false;
   			* Arm_SlsFax.readOnly = false;
   			* Arm_SlsNotes.readOnly = false;
   			* Arm_CreditLimit.readOnly = false;
   			* Arm_CredStatChgDate.readOnly = false;
   			* Arm_CredStatNote.readOnly = false;
   			* Arm_FinChgRate.readOnly = false;
   			* Arm_GuaranteeExpDate.readOnly = false;
   			* Arm_Judment_Date.readOnly = false;
   			* Arm_LastCredApRecvdDate.readOnly = false;
   			* Arm_OurArContact.readOnly = false;
   			* Arm_PreLienLimit.readOnly = false;
   			* Arm_PmtTermsCd.disabled = false;
   			* Arm_PoRequired.disabled = false;
   			* Arm_JointCheck.disabled = false;
   			* Arm_GuaranteeSigned.disabled = false;
   			* Arm_PreLienReq.disabled = false;
   			* Arm_ConsolidStmt.disabled = false;
   			* Arm_EmailServer.disabled = false;
   			* Arm_Slab.disabled = false;
   			* Arm_StmtType.disabled = false;
   			* Arm_InvType.disabled = false;
   			* Arm_Voucher.disabled = false;
   			* Arm_PrintPricingOnInv.disabled = false;
   			* Arm_RequireJobInfo.disabled = false;
   			* Arm_TreatAsStore.disabled = false;
   			* Arm_Vendor.disabled = false;
   			* Arm_FinChgCd.disabled = false;
   			* Arm_CreditStatus.disabled = false;
   			* Arm_StmtFreq_M.disabled = false;
   			* Arm_StmtFreq_E.disabled = false;
   			* Arm_InvFreq_M.disabled = false;
   			* Arm_InvFreq_E.disabled = false;
   			* Arm_BuyingGroup.disabled = false;
   		* cashe user editable fields
   			* Arm_CoName.readOnly = false;
   			* Arm_CoAddr1.readOnly = false;
   			* Arm_CoAddr2.readOnly = false;
   			* Arm_City.readOnly = false;
   			* Arm_StateCd.readOnly = false;
   			* Arm_Zip.readOnly = false;
   			* Arm_CountryCd.readOnly = false;
   			* Arm_OwnerFirstName.readOnly = false;
   			* Arm_OwnerLastName.readOnly = false;
   			* Arm_ApPhone.readOnly = false;
   			* Arm_ApExt.readOnly = false;
   			* Arm_ApFax.readOnly = false;
   	2. only limited fields are available to Customer
   	
#### Delete account
NOT allowed

Account Data
------------
* Account ID  
	* must be unique  
	* alphanumeric characters only  
	* 10 characters maximum  
	* cannot be modified once created
* Account Name
	* 35 characters maximum
	* The name of the account, typically the company name
* DBA
	* 35 characters maximum
	* The real company name, may be the same as account name
* Account Address (also the billing address)
	* address line 1 (required for CREATE action)
		* 30 characters maximum
	* address line 2
		* 30 characters maximum
	* city (required for CREATE action)
		* 25 characters maximum
	* country
		* 4 characters maximum
	* state (required for CREATE action)
		* 2 characters maximum
	* zip code
		* 10 characters maximum
	* phone number
		* type: work, mobile, fax or custom
		* 10 entries maximum
* Activity Status
	* Y: Yes
	* D: delete, after the account has not made purchase for 2 years
* isVendor
	* vendor number
		* 0 means NOT a vendor
* isSlab store
	* boolean
* Judgement Date
	* date
* Buying Group
	* options
		* None
		* CCA
		* Alliance
* Vendor Number
	* 10 characters maximum, numerical characters only
* Contractor Number
* Owner's Info
	* first name (required for CREATE action)
		* 15 characters maximum
	* last name (required for CREATE action)
		* 15 characters maximum
	* driver's license number
		* 15 characters maximum
* Treat as a Store
	* boolean
* web site URL
	* a web browser accessible address
* List of Audit Notes
	* date
	* note
	* the person who created the note
	* status
		* possbile values?
	* since day 1
* paper statement mailing frequency
	* options
		* No (default for cash account)
		* End of Month (default for credit account)
* email statement mailing frequency
	* options
		* No
		* End of Month
		* 3: 3 times a month
* paper invoice mailing frequency
	* options
		* No (default for cash account)
		* 3: 3 times a month (default for credit account)
* email invoice mailing frequency
	* options
		* B1 (will be removed)
		* B2 (will be removed)
		* B3 (will be removed)
		* B4 (will be removed)
		* B5 (will be removed)
		* B6 (will be removed)
		* B7 (will be removed)
		* No (default for cash account)
		* Eod of Month
		* 3: 3 times a month (will be removed)
		* W1 (will be removed)
		* W2 (will be removed)
		* W3 (will be removed)
		* W4 (will be removed)
		* W5 (will be removed)
		* W6 (will be removed)
		* W7 (will be removed)
		* D1
		* D2
		* D3
		* D4 (will beremoved)
		* D5 (will beremoved)
* List of Non-sufficient check
	* when count is >= 3, hold the account
	* since day 1
* Credit Limit
	* only editable by account manager
	* fixed value of 0 for cash account
* Lock Hold
	* boolean
	* highest access right required to change value, ex, Larry
* Credit Status
	* options
		* Open
		* Cash (fixed value value for cash account)
		* Hold
		* Tran (will be removed)
		* Warn (will be removed)
	* CASH is the only accetable payment type when Credit Account Receivable Note is not OK
* Credit Account Receivable Note
	* options
		* OK (fixed value for cash account)
		* Agency
		* Bankruptcy 7
		* Bankruptcy 11
		* Bankruptcy 13
		* W/O : write off
		* Close
	* Fields affected when non-OK option is slected
		* Credit Status is changed to CASH or remains unchanged if it's HOLD now.
		* Payment Term is changed to CASH
* Payment Term (some options are not finalized yet)
	* options
		* 10th
		* 2/10
		* C/M
		* Cash (fixed value for cash account)
		* LYWY
		* N30: net 30 days
		* N60: net 60 days
		* N90: net 90 days
		* N45: net 45 days
		* N120: net 120 days
		* 369: 30, 60, 90 days
		* PUT (will be removed)
* Bankruptcy Case Number
* PO Required
	* boolean
* Joint Checks
	* boolean
	* a file should be saved
* List of Guarantee Signed documents
	* boolean
	* expiration date
	* since day 1
* Pre Lien
	* boolean (default value of No for cash account; default value of Yes for credit accout)
	* amount limit
* Late Payment Charge
	* boolean
	* percentence
* Last Credit approved date
	* initial value of current date is automatically generated when account is created
* Voucher Account
	* boolean
* isConsolidated Payment
	* boolean
	* indicate a consolidated payments of multiple branches
* Price on Invoice
	* boolean (default value of Yes for both cash and credit account)
* Require Job Info
	* boolean
* Account Sales 
	* first name
		* 15 characters maximum
	* last name
		* 15 characters maximum
	* title
		* 16 characters maximum
	* sales number
		* numeric(4,0)
	* store number
		* numeric(3,0)
	* phone
	* email
		* 30 characters maximum
* Account Manager
	* firstname
	* lastname
* Account established date
	* initila value of current date is automatically generated when account is created
	* not editable once saved
* Account Contacts
	* must be at least one
	* one of them is admin to manage the account (update account info) and contacts (add/update/remove contact)
	* user
		* email
			* 64 characters maximum
		* first name
			* 32 characters maximum
		* last name
			* 32 characters maximum
		* password
			* no space allowed
			* 16 characters maximum
		* title
			* 16 characters maximum
		* user_role
		* phone
			* type: work, mobile, fax or custom
			* 10 entries maximum		
* Branches
	* branch ID
		* unique
		* alphanumeric characters only
		* 4 characters maximum
	* the branches that belong to this account
	
	



