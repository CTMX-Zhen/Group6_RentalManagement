import json
import logging

from tenant_bst_system import TenantBST
from random_data import generate_random_data, clear_tree

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

# sync data to JSON file
def sync_data_to_json(bst, filename="tenant_test_data.json"):
    try:
        tenants = bst.to_list()
        with open(filename, "w") as f:
            json.dump(tenants, f, indent=4)
        logger.info(f"\033[\n92mACTION: !* BST snapshot saved to {filename}\033[0m")
        return tenants
    
    except Exception as e:
        logger.error(f"\n\033[91mERROR: !* Failed to save BST to JSON: {e}\033[0m")
        raise

def main():
    try:
        bst = TenantBST()

        generate_random_data()

        with open("tenant_test_data.json", "r") as f:
            tenants = json.load(f)

        for tenant in tenants:
            bst.insert_tenant(tenant["tenant_id"], tenant["name"], tenant["phone"], tenant["unit"], tenant["lease"])

        while True:
            logger.info(f"\033[\n1;37;44m===== Tenant & Lease Management System (BST) =====\033[0m")
            logger.info(f"\033[\n94mINFO: !* 0. Generate new random tenants\033[0m")
            logger.info(f"\033[\n94mINFO: !* 1. Insert new tenant\033[0m")
            logger.info(f"\033[\n94mINFO: !* 2. Search tenant by ID\033[0m")
            logger.info(f"\033[\n94mINFO: !* 3. Update tenant\033[0m")
            logger.info(f"\033[\n94mINFO: !* 4. Delete tenant\033[0m")
            logger.info(f"\033[\n94mINFO: !* 5. Display all tenants\033[0m")
            logger.info(f"\033[\n94mINFO: !* 6. Exit\033[0m")

            choice = input("Enter your choice: ")

            if choice == "0":
                logger.info(f"\033[\n93mWARNING: !* Generating new random tenants will overwrite existing data in BST and tenant_test_data.json\033[0m")
                if tenants is not None:
                    logger.info(f"\033[\n93mWARNING: !* Overwriting existing tenants in BST and tenant_test_data.json\033[0m")

                    choice_confirm = input("Are you sure you want to proceed? (y/n): ")
                    if choice_confirm.lower() != 'y':
                        logger.info(f"\033[\n93mWARNING: !* Operation cancelled\033[0m")
                        continue

                # Clear existing BST
                clear_tree(bst)
                tenants = None

                logger.info(f"\033[\n93mWARNING: !* Proceeding will erase all current data in the BST\033[0m")

                generate_random_data()
                with open("tenant_test_data.json", "r") as f:
                    tenants = json.load(f)
                for tenant in tenants:
                    bst.insert_tenant(tenant["tenant_id"], tenant["name"], tenant["phone"], tenant["unit"], tenant["lease"])
                
                tenants = sync_data_to_json(bst)

                logger.info(f"\033[\n92mACTION: !* New random tenants generated and loaded into BST successfully.\033[0m")

                continue

            if choice == "1":
                try:
                    tenant_id = int(input("Enter Tenant ID (integer): "))
                except ValueError:
                    logger.error(f"\n\033[91mERROR: !* Invalid ID. Must be integer\033[0m")
                    continue

                name = input("Enter Name: ")
                phone = input("Enter Phone: ")
                unit = input("Enter Unit (e.g. A-12-03): ")

                try:
                    lease = int(input("Enter lease duration (months): "))
                except ValueError:
                    logger.error(f"\n\033[91mERROR: !* Invalid lease duration. Must be integer\033[0m")
                    continue

                bst.insert_tenant(tenant_id, name, phone, unit, lease)
                tenants = sync_data_to_json(bst)

                continue

            elif choice == "2":
                try:
                    tenant_id = int(input("Enter Tenant ID to search: "))
                except ValueError:
                    logger.error(f"\n\033[91mERROR: !* Invalid ID. Must be integer (numbers only)\033[0m")

                node = bst.search_tenant(tenant_id)

                if node:
                    logger.info(f"\033[\n92mACTION: !* Tenant found successfully\033[0m")
                    logger.info(f"\033[\n96mDEBUG: !* Tenant details: {node}\033[0m")
                else:
                    logger.error(f"\n\033[91mERROR: !* Tenant not found\033[0m")
                
                continue

            elif choice == "3":
                try:
                    tenant_id = int(input("Enter Tenant ID to update: "))
                except ValueError:
                    logger.error(f"\n\033[91mERROR: !* Invalid ID. Must be integer (numbers only)\033[0m")
                    continue

                node = bst.search_tenant(tenant_id)

                if not node:
                    logger.error(f"\n\033[91mERROR: !* Tenant not found. Cannot update\033[0m")
                    continue

                print("Leave field empty if no change.")
                new_name = input(f"New Name (current: {node.name}): ").strip()
                new_phone = input(f"New Phone (current: {node.phone}): ").strip()
                new_unit = input(f"New Unit (current: {node.unit}): ").strip()
                new_lease_str = input(f"New Lease Duration (months, current: {node.lease_duration}): ").strip()

                updated =bst.update_tenant(
                    tenant_id,
                    new_name if new_name else None,
                    new_phone if new_phone else None,
                    new_unit if new_unit else None,
                    int(new_lease_str) if new_lease_str else None
                )

                if updated:
                    tenants = sync_data_to_json(bst)
                
                continue

            elif choice == "4":
                try:
                    tenant_id = int(input("Enter Tenant ID to delete: "))
                except ValueError:
                    logger.error(f"\n\033[91mERROR: !* Invalid ID. Must be integer (numbers only)\033[0m")
                    continue

                bst.delete_tenant(tenant_id)
                tenants = sync_data_to_json(bst)

                continue

            elif choice == "5":
                logger.info(f"\033[\n1;37;44m===== Choose display order: =====\033[0m")
                logger.info(f"\033[\n94mINFO: !* 1. Inorder (sorted by Tenant ID)\033[0m")
                logger.info(f"\033[\n94mINFO: !* 2. Preorder (show tree structure)\033[0m")
                logger.info(f"\033[\n94mINFO: !* 3. Postorder (bottom-up)\033[0m")

                traversal = input("Enter choice (1-3): ")

                if traversal == "1":
                    bst.display_in_order(1)
                elif traversal == "2":
                    bst.display_in_order(2)
                elif traversal == "3":
                    bst.display_in_order(3)
                else:
                    logger.error(f"\n\033[91mERROR: !* Invalid display order '{traversal}'\033[0m")
                
                continue

            elif choice == "6":
                logger.info(f"\033[\n1;37;44m===== Exiting program =====\033[0m")
                break

            else:
                logger.warning(f"\n\033[93mWARNING: !* Invalid choice '{choice}'\033[0m")
                logger.info(f"\033[\n93mWARNING: !* Please enter a valid option (0-6)\033[0m")
                continue

    except Exception as e:
        logger.error(f"\n\033[91mERROR: !* Exception in main: {e}\033[0m")
        logger.error(f"\n\033[91mERROR: !* Interrupted program\033[0m")
        raise

if __name__ == "__main__":
    main()
