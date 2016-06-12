-- CREATE SCHEMA `Get-Together` DEFAULT CHARACTER SET utf8 ;
-- System user
INSERT INTO User (uuid,firstName,lastName,gender,dateOfBirth,address,postalCode,contactNo,email,fbUserId) values ("6cc4d834-10fb-4a32-9021-1721ab8e0ee0","System", "Admin", "Male", str_to_date('01,11,1991','%d,%m,%Y'),"Blk 213, #10-141, Bedok North Street 1", 460213, 98278559, "dev.minko@gmail.com", null);


-- User Credentails
INSERT INTO UserCredential (userId,username,password,salt,failedAttempts,accountNonExpired,accountNonLocked,credentialsNonExpired,enabled,lastAccessDate) values (1,'thetminko','/xURfw9oSoPVBeinNt7y0i8qsakDYwvvVza2T2aUwmM=','tuhfaOfW7N7mH+kG7O0DRm2ogCw0neu3YJasnzLuKDg=',0,1,1,1,1,now());

-- Permission
INSERT INTO Permission (permission) values ("SYS_ADMIN");
INSERT INTO Permission (permission) values ("AUTH_USER");

-- Set permission to user
INSERT INTO UserPermission values (1,1);
INSERT INTO UserPermission values (1,2);