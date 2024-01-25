import requests
import multiprocessing
from concurrent.futures import ThreadPoolExecutor, wait
import concurrent
import time


def do(i):
    time.sleep(10-i)
    print(i)

futures = []
print(multiprocessing.cpu_count())
with concurrent.futures.ThreadPoolExecutor(max_workers=multiprocessing.cpu_count()) as executor:
    for i in range(10):
        futures.append(executor.submit(do, i))

wait(futures)