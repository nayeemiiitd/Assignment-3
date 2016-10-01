# Assignment-3

### Short Desciprtion about the App ("Notess")<br/>

1. App takes the login credentials of the user to login for adding notes
2. New User can signUp and also users can delete their account, update their account and retreive the password
3. Notes can be added and be seen



### Uses of Databases
1. Internal Storage<br/>
		After successfull login the user's timestamp is stored in internal storage and by reading by the internal file (dates.txt) it is shown to user using a toast

2. External Storage<br/>
		After creating a note (with heading and note) it is stored in external storage (add_notes.txt) and on clicking view notes button the file is read and shown to the user in a separate layout.

3. SQLite Database<br/>
		User Sign Up data is stored in the database. Forgot Password is retreiving the password from SQL. Deleting the account. Checking the correct credentials and editing the account.(notes.db)

4. SharedPreferenceState<br/>
		Username and password is stored in sharedpreference and when the user again opens the app it is automatically logged in. The user has to logout to delete its sharedpreference state data.
    
### Extra works
1. Making dialog boxes
2. Making Navigator drawer
3. Making clickable listview
4. Icon of the app
5. Simple and beautifull GUI


### Screenshots

