# tenant_test_data.py

import json
import random
import logging

# ===== LOGGING CONFIGURATION =====
logger = logging.getLogger("tenant_bst_logger")
logger.setLevel(logging.DEBUG)
formatter = logging.Formatter('%(asctime)s - %(levelname)s - %(message)s')

console_handler = logging.StreamHandler()
console_handler.setLevel(logging.DEBUG)
console_handler.setFormatter(formatter)

file_handler = logging.FileHandler('tenant_bst.log')
file_handler.setLevel(logging.DEBUG)
file_handler.setFormatter(formatter)

if not logger.hasHandlers():
    logger.addHandler(console_handler)
    logger.addHandler(file_handler)
else:
    handler_types = [type(h) for h in logger.handlers]
    if logging.FileHandler not in handler_types:
        logger.addHandler(file_handler)

def generate_random_data(n=5, seed=42):
    try:
        random.seed(seed)

        tenants = []

        for i in range(n):
            tenant_id = 1000 + i
            name = f"Tenant_{i+1:03d}"
            phone = f"01{random.randint(0,9)}-{random.randint(1000000, 9999999)}"

            block = random.choice(['A', 'B', 'C', 'D'])
            floor = random.randint(1, 20)
            unit_no = random.randint(1, 15)
            unit = f"{block}-{floor:02d}-{unit_no:02d}"

            lease_duration = random.choice([6, 12, 18, 24, 36])

            tenants.append({
                "tenant_id": tenant_id,
                "name": name,
                "phone": phone,
                "unit": unit,
                "lease": lease_duration
            })
        
        with open("tenant_test_data.json", "w") as f:
            json.dump(tenants, f, indent=4)

        logger.info(f"\033[\n92mACTION: !* tenant_test_data.json generated successfully.\033[0m")

        return "tenant_test_data.json"

    except Exception as e:
        logger.error(f"\n\033[91mERROR: !* Exception in generating random data: {e}\033[0m")
        return []
    
def clear_tree(bst):
    try:
        if bst is None:
            logger.error(f"\n\033[91mERROR: !* No BST instance provided to clear\033[0m")
            return

        bst.root = None
        logger.info("\033[\n93mWARNING: !* BST cleared (all tenants removed)\033[0m")
    except Exception as e:
        logger.error(f"\n\033[91mERROR: !* Exception in clearing BST: {e}\033[0m")
        raise
