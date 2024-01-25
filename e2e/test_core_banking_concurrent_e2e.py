import requests
import multiprocessing
from concurrent.futures import ThreadPoolExecutor, wait
import concurrent
import time

core_banking_url = "http://localhost:8080"
core_banking_url_accounts = f"{core_banking_url}/v1/accounts"
core_banking_url_execute_transactions = f"{core_banking_url}/v1/execute/transactions"
headers = { "Content-Type" : "application/json"}

def test_execute_concurrent_deposits_and_check_balance():
    create_account_body = {'type': 'CHECKING'}
    create_account_response = requests.post(core_banking_url_accounts, json=create_account_body, headers=headers)
    account_id = create_account_response.json()['id']
    execute_transaction_body = {"accountId": account_id, "type": "DEPOSIT", "amount": 20}
    futures = []
    with concurrent.futures.ThreadPoolExecutor(max_workers=multiprocessing.cpu_count()) as executor:
        for i in range(100):
            futures.append(executor.submit(requests.post, core_banking_url_execute_transactions, json=execute_transaction_body, headers=headers, timeout=20))
    wait(futures)
    get_account_response = requests.get(f"{core_banking_url_accounts}/{account_id}", headers=headers)
    assert get_account_response.status_code == 200
    assert get_account_response.json()['balance'] == 2000


def test_execute_concurrent_withdraws_and_check_balance():
    create_account_body = {'type': 'CHECKING'}
    create_account_response = requests.post(core_banking_url_accounts, json=create_account_body, headers=headers)
    account_id = create_account_response.json()['id']
    execute_transaction_body = {"accountId": account_id, "type": "WITHDRAW", "amount": 20}
    futures = []
    with concurrent.futures.ThreadPoolExecutor(max_workers=multiprocessing.cpu_count()) as executor:
        for i in range(100):
            futures.append(executor.submit(requests.post, core_banking_url_execute_transactions, json=execute_transaction_body, headers=headers, timeout=20))
    wait(futures)
    get_account_response = requests.get(f"{core_banking_url_accounts}/{account_id}", headers=headers)
    assert get_account_response.status_code == 200
    assert get_account_response.json()['balance'] == -2000

def test_execute_concurrent_deposits__and_withdraws_and_check_balance():
    create_account_body = {'type': 'CHECKING'}
    create_account_response = requests.post(core_banking_url_accounts, json=create_account_body, headers=headers)
    account_id = create_account_response.json()['id']
    execute_transaction_body = {"accountId": account_id, "type": "WITHDRAW", "amount": 20}
    executor = ThreadPoolExecutor(max_workers=16)
    futures = []
    with concurrent.futures.ThreadPoolExecutor(max_workers=multiprocessing.cpu_count()) as executor:
        for i in range(100):
            futures.append(executor.submit(requests.post, core_banking_url_execute_transactions, json=execute_transaction_body, headers=headers, timeout=20))
    execute_transaction_body = {"accountId": account_id, "type": "DEPOSIT", "amount": 20}
    executor = ThreadPoolExecutor(max_workers=16)
    futures = []
    with concurrent.futures.ThreadPoolExecutor(max_workers=multiprocessing.cpu_count()) as executor:
        for i in range(100):
            futures.append(executor.submit(requests.post, core_banking_url_execute_transactions, json=execute_transaction_body, headers=headers, timeout=20))
    wait(futures)
    get_account_response = requests.get(f"{core_banking_url_accounts}/{account_id}", headers=headers)
    assert get_account_response.status_code == 200
    assert get_account_response.json()['balance'] == 0
