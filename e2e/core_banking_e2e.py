import requests


core_banking_url = "http://localhost:8080"
core_banking_url_accounts = f"{core_banking_url}/v1/accounts"
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


