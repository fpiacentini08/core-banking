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



CREATE TABLE core_banking.transaction (
	id varchar(100) NOT NULL,
	account_id varchar(100) NOT NULL,
	`type` varchar(100) NOT NULL,
	`status` varchar(100) NOT NULL,
	amount decimal(19,2) NOT NULL,
	created_at datetime NOT NULL,
	updated_at datetime NOT NULL,
	CONSTRAINT transaction_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=latin1
COLLATE=latin1_swedish_ci;
