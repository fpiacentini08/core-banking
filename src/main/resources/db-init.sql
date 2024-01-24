CREATE TABLE core_banking.account (
	id varchar(100) NOT NULL,
	`type` varchar(100) NOT NULL,
	status varchar(100) NOT NULL,
	balance decimal(19,2) NOT NULL,
	CONSTRAINT account_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=latin1
COLLATE=latin1_swedish_ci;
