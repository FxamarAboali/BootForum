--
-- Create the 'admin' user with role 'ADMIN' if not already exists
--

-- Insert into PERSON_T table
INSERT INTO PERSON_T (ID, CREATE_BY, CREATE_DATE, EMAIL, FIRST_NAME, LAST_NAME, UPDATE_BY, UPDATE_DATE)
	SELECT 1, 'SYSTEM', CURRENT_TIMESTAMP, 'updateme@somehost.net', 'System', 'Administrator', 'SYSTEM', CURRENT_TIMESTAMP 
 	WHERE NOT EXISTS(SELECT 1 FROM PERSON_T WHERE ID = 1);

-- Insert into USER_T table. Hashed password is 'secret'
INSERT INTO USER_T (ID, ACCOUNT_STATUS, CREATE_BY, CREATE_DATE, PASSWORD, UPDATE_BY, UPDATE_DATE, USER_NAME, USER_ROLE, PERSON_ID)
	SELECT 1, 0, 'SYSTEM', CURRENT_TIMESTAMP, '$2a$10$AvJZ09HChLj.qPlKZNu6ue4iyAHPKYbIKTqJsJ/3S3nIQCU56dxYK', 'SYSTEM', CURRENT_TIMESTAMP, 'admin', 'ADMIN', 1 
 	WHERE NOT EXISTS(SELECT 1 FROM USER_T WHERE ID = 1);