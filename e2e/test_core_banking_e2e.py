import requests


core_banking_url = "http://localhost:8080"
core_banking_url_accounts = f"{core_banking_url}/v1/accounts"
core_banking_url_transactions = f"{core_banking_url}/v1/transactions"
core_banking_url_execute_transactions = f"{core_banking_url}/v1/execute/transactions"
headers = { "Content-Type" : "application/json"}


def test_create_account():
    create_account_body = {'type': 'CHECKING'}
    create_account_response = requests.post(core_banking_url_accounts, json=create_account_body, headers=headers)
    assert create_account_response.status_code == 201
    assert create_account_response.json()['id']
    assert create_account_response.json()['type'] == 'CHECKING'
    assert create_account_response.json()['status'] == 'OPEN'
    assert create_account_response.json()['balance'] == 0

def test_get_account_by_id():
    create_account_body = {'type': 'CHECKING'}
    create_account_response = requests.post(core_banking_url_accounts, json=create_account_body, headers=headers)
    account_id = create_account_response.json()['id']
    get_account_response = requests.get(f"{core_banking_url_accounts}/{account_id}", headers=headers)
    assert get_account_response.status_code == 200
    assert get_account_response.json()['id'] == account_id
    assert get_account_response.json()['type'] == 'CHECKING'
    assert get_account_response.json()['status'] == 'OPEN'
    assert get_account_response.json()['balance'] == 0

def test_get_account_by_id_does_not_exist():
    get_account_response = requests.get(f"{core_banking_url_accounts}/123456", headers=headers)
    assert get_account_response.status_code == 404
    assert get_account_response.json()['code'] == "01"
    assert get_account_response.json()['message'] == "Account not found"

def test_get_transaction_by_id_does_not_exist():
    get_transaction_response = requests.get(f"{core_banking_url_transactions}/123456", headers=headers)
    assert get_transaction_response.status_code == 404
    assert get_transaction_response.json()['code'] == "02"
    assert get_transaction_response.json()['message'] == "Transaction not found"


def test_execute_deposit_and_get_transaction_by_id():
    create_account_body = {'type': 'CHECKING'}
    create_account_response = requests.post(core_banking_url_accounts, json=create_account_body, headers=headers)
    account_id = create_account_response.json()['id']
    execute_transaction_body = {"accountId": account_id, "type": "DEPOSIT", "amount": 20}
    execute_transaction_response = requests.post(core_banking_url_execute_transactions, json=execute_transaction_body, headers=headers)
    assert execute_transaction_response.status_code == 201
    assert execute_transaction_response.json()['id'] 
    assert execute_transaction_response.json()['accountId']  == account_id
    assert execute_transaction_response.json()['amount']  == 20
    assert execute_transaction_response.json()['type']  == "DEPOSIT"
    assert execute_transaction_response.json()['status']  == "APPROVED"
    assert execute_transaction_response.json()['createdAt'] 
    assert execute_transaction_response.json()['updatedAt'] 
    assert execute_transaction_response.json()['createdAt'] == execute_transaction_response.json()['updatedAt'] 
    transaction_id = execute_transaction_response.json()['id'] 
    get_account_response = requests.get(f"{core_banking_url_accounts}/{account_id}", headers=headers)
    assert get_account_response.status_code == 200
    assert get_account_response.json()['balance'] == 20
    get_transaction_response = requests.get(f"{core_banking_url_transactions}/{transaction_id}", headers=headers)
    assert get_transaction_response.status_code == 200
    assert get_transaction_response.json()['id'] == transaction_id
    assert get_transaction_response.json()['accountId']  == account_id
    assert get_transaction_response.json()['amount']  == 20
    assert get_transaction_response.json()['type']  == "DEPOSIT"
    assert get_transaction_response.json()['status']  == "APPROVED"
    assert get_transaction_response.json()['createdAt'] 
    assert get_transaction_response.json()['updatedAt'] 
    assert get_transaction_response.json()['createdAt'] == get_transaction_response.json()['updatedAt'] 


def test_execute_withdraw_and_get_transaction_by_id():
    create_account_body = {'type': 'CHECKING'}
    create_account_response = requests.post(core_banking_url_accounts, json=create_account_body, headers=headers)
    account_id = create_account_response.json()['id']
    execute_transaction_body = {"accountId": account_id, "type": "WITHDRAW", "amount": 20}
    execute_transaction_response = requests.post(core_banking_url_execute_transactions, json=execute_transaction_body, headers=headers)
    assert execute_transaction_response.status_code == 201
    assert execute_transaction_response.json()['id'] 
    assert execute_transaction_response.json()['accountId']  == account_id
    assert execute_transaction_response.json()['amount']  == 20
    assert execute_transaction_response.json()['type']  == "WITHDRAW"
    assert execute_transaction_response.json()['status']  == "APPROVED"
    assert execute_transaction_response.json()['createdAt'] 
    assert execute_transaction_response.json()['updatedAt'] 
    assert execute_transaction_response.json()['createdAt'] == execute_transaction_response.json()['updatedAt'] 
    transaction_id = execute_transaction_response.json()['id'] 
    get_account_response = requests.get(f"{core_banking_url_accounts}/{account_id}", headers=headers)
    assert get_account_response.status_code == 200
    assert get_account_response.json()['balance'] == -20
    get_transaction_response = requests.get(f"{core_banking_url_transactions}/{transaction_id}", headers=headers)
    assert get_transaction_response.status_code == 200
    assert get_transaction_response.json()['id'] == transaction_id
    assert get_transaction_response.json()['accountId']  == account_id
    assert get_transaction_response.json()['amount']  == 20
    assert get_transaction_response.json()['type']  == "WITHDRAW"
    assert get_transaction_response.json()['status']  == "APPROVED"
    assert get_transaction_response.json()['createdAt'] 
    assert get_transaction_response.json()['updatedAt'] 
    assert get_transaction_response.json()['createdAt'] == get_transaction_response.json()['updatedAt'] 

def test_execute_deposit_and_then_withdraw():
    create_account_body = {'type': 'CHECKING'}
    create_account_response = requests.post(core_banking_url_accounts, json=create_account_body, headers=headers)
    account_id = create_account_response.json()['id']
    execute_transaction_body = {"accountId": account_id, "type": "DEPOSIT", "amount": 20}
    execute_transaction_response = requests.post(core_banking_url_execute_transactions, json=execute_transaction_body, headers=headers)
    assert execute_transaction_response.status_code == 201
    execute_transaction_body = {"accountId": account_id, "type": "WITHDRAW", "amount": 5}
    execute_transaction_response = requests.post(core_banking_url_execute_transactions, json=execute_transaction_body, headers=headers)
    assert execute_transaction_response.status_code == 201
    get_account_response = requests.get(f"{core_banking_url_accounts}/{account_id}", headers=headers)
    assert get_account_response.status_code == 200
    assert get_account_response.json()['balance'] == 15

def test_execute_withdraw_and_then_deposit():
    create_account_body = {'type': 'CHECKING'}
    create_account_response = requests.post(core_banking_url_accounts, json=create_account_body, headers=headers)
    account_id = create_account_response.json()['id']
    execute_transaction_body = {"accountId": account_id, "type": "WITHDRAW", "amount": 20}
    execute_transaction_response = requests.post(core_banking_url_execute_transactions, json=execute_transaction_body, headers=headers)
    assert execute_transaction_response.status_code == 201
    execute_transaction_body = {"accountId": account_id, "type": "DEPOSIT", "amount": 50}
    execute_transaction_response = requests.post(core_banking_url_execute_transactions, json=execute_transaction_body, headers=headers)
    assert execute_transaction_response.status_code == 201
    get_account_response = requests.get(f"{core_banking_url_accounts}/{account_id}", headers=headers)
    assert get_account_response.status_code == 200
    assert get_account_response.json()['balance'] == 30

def test_execute_deposit_twice():
    create_account_body = {'type': 'CHECKING'}
    create_account_response = requests.post(core_banking_url_accounts, json=create_account_body, headers=headers)
    account_id = create_account_response.json()['id']
    execute_transaction_body = {"accountId": account_id, "type": "DEPOSIT", "amount": 20}
    execute_transaction_response = requests.post(core_banking_url_execute_transactions, json=execute_transaction_body, headers=headers)
    assert execute_transaction_response.status_code == 201
    execute_transaction_body = {"accountId": account_id, "type": "DEPOSIT", "amount": 60}
    execute_transaction_response = requests.post(core_banking_url_execute_transactions, json=execute_transaction_body, headers=headers)
    assert execute_transaction_response.status_code == 201
    get_account_response = requests.get(f"{core_banking_url_accounts}/{account_id}", headers=headers)
    assert get_account_response.status_code == 200
    assert get_account_response.json()['balance'] == 80

def test_execute_withdraw_twice():
    create_account_body = {'type': 'CHECKING'}
    create_account_response = requests.post(core_banking_url_accounts, json=create_account_body, headers=headers)
    account_id = create_account_response.json()['id']
    execute_transaction_body = {"accountId": account_id, "type": "WITHDRAW", "amount": 20}
    execute_transaction_response = requests.post(core_banking_url_execute_transactions, json=execute_transaction_body, headers=headers)
    assert execute_transaction_response.status_code == 201
    execute_transaction_body = {"accountId": account_id, "type": "WITHDRAW", "amount": 30}
    execute_transaction_response = requests.post(core_banking_url_execute_transactions, json=execute_transaction_body, headers=headers)
    assert execute_transaction_response.status_code == 201
    get_account_response = requests.get(f"{core_banking_url_accounts}/{account_id}", headers=headers)
    assert get_account_response.status_code == 200
    assert get_account_response.json()['balance'] == -50

