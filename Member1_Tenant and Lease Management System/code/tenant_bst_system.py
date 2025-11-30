import logging

from node import Node
from display_order import inorder, preorder, postorder

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

class TenantBST:
    def __init__(self):
        self.root = None

    # ---------- INSERT ----------
    def insert_tenant(self, tenant_id, name, phone, unit, lease_duration_months):
        try:
            new_node = Node(tenant_id, name, phone, unit, lease_duration_months)

            if self.root is None:
                self.root = new_node
                logger.info(f"\033[\n94mINFO: !* Tenant inserted as root\033[0m")
                return

            current = self.root
            
            for _ in range(1000):
                if tenant_id == current.tenant_id:
                    logger.error(f"\n\033[91mERROR: !* Insert failed: Tenant ID already exists\033[0m")
                    return
                elif tenant_id < current.tenant_id:
                    if current.left is None:
                        current.left = new_node
                        logger.info(f"\033[\n92mACTION: !* Tenant inserted on the LEFT\033[0m")
                        return
                    else:
                        current = current.left
                # tenant_id > current.tenant_id and tenant_id != current.tenant_id
                else:
                    if current.right is None:
                        current.right = new_node
                        logger.info(f"\033[\n92mACTION: !* Tenant inserted on the RIGHT\033[0m")
                        return
                    else:
                        current = current.right

        except Exception as e:
            logger.error(f"\n\033[91mERROR: !* Exception in inserting Tenant ID: {e}\033[0m")
            logger.error(f"\n\033[91mERROR: !* Interrupted insert\033[0m")
            raise

    # ---------- SEARCH ----------
    def search_tenant(self, tenant_id):
        try:
            current = self.root

            while current is not None:
                if tenant_id == current.tenant_id:
                    return current
                elif tenant_id < current.tenant_id:
                    current = current.left
                else:
                    current = current.right

            return None
        
        except Exception as e:
            logger.error(f"\n\033[91mERROR: !* Exception in searching Tenant ID: {e}\033[0m")
            logger.error(f"\n\033[91mERROR: !* Interrupted search\033[0m")
            raise

    # ---------- UPDATE ----------
    def update_tenant(self, tenant_id, new_name=None, new_phone=None, new_unit=None, new_lease_duration=None):
        try:
            node = self.search_tenant(tenant_id)
            if node is None:
                logger.error(f"\n\033[91mERROR: !* Tenant not found. Cannot update\033[0m")
                return False

            if new_name is not None:
                node.name = new_name
            if new_phone is not None:
                node.phone = new_phone
            if new_unit is not None:
                node.unit = new_unit
            if new_lease_duration is not None:
                node.lease_duration = new_lease_duration

            logger.info(f"\033[\n92mACTION: !* Tenant updated successfully\033[0m")
            return True
        
        except Exception as e:
            logger.error(f"\n\033[91mERROR: !* Exception in updating Tenant ID: {e}\033[0m")
            logger.error(f"\n\033[91mERROR: !* Interrupted update\033[0m")
            raise

    # ---------- DELETE ----------
    def delete_tenant(self, tenant_id):
        try:
            self.root, deleted = self.delete_recursive(self.root, tenant_id)

            if deleted:
                logger.info(f"\033[\n92mACTION: !* Tenant deleted successfully\033[0m")
            else:
                logger.error(f"\n\033[91mERROR: !* Tenant ID not found. Cannot delete\033[0m")
        
        except Exception as e:
            logger.error(f"\n\033[91mERROR: !* Exception in deleting Tenant ID: {e}\033[0m")
            logger.error(f"\n\033[91mERROR: !* Interrupted delete\033[0m")
            raise

    def delete_recursive(self, node, tenant_id):
        try: 
            if node is None:
                return node, False

            deleted = False

            if tenant_id < node.tenant_id:
                node.left, deleted = self.delete_recursive(node.left, tenant_id)
            elif tenant_id > node.tenant_id:
                node.right, deleted = self.delete_recursive(node.right, tenant_id)
            # tenant_id == node.tenant_id
            else:
                deleted = True
                # Case 1: if no child
                if node.left is None and node.right is None:
                    return None, True
                # Case 2: else if one child
                elif node.left is None:
                    return node.right, True
                elif node.right is None:
                    return node.left, True
                # Case 3:  else if two children
                else:
                    # find inorder successor (smallest in right subtree)
                    successor = self.min_value_node(node.right)
                    node.tenant_id = successor.tenant_id
                    node.name = successor.name
                    node.phone = successor.phone
                    node.unit = successor.unit
                    node.lease_duration = successor.lease_duration

                    # delete successor node from right subtree
                    node.right, _ = self.delete_recursive(node.right, successor.tenant_id)

            return node, deleted

        except Exception as e:
            logger.error(f"\n\033[91mERROR: !* Exception in deleting Tenant ID: {e}\033[0m")
            logger.error(f"\n\033[91mERROR: !* Interrupted delete\033[0m")
            raise

    def min_value_node(self, node):
        try:
            current = node
            while current.left is not None:
                current = current.left
            return current
        
        except Exception as e:
            logger.error(f"\n\033[91mERROR: !* Exception in finding min value node: {e}\033[0m")
            logger.error(f"\n\033[91mERROR: !* Interrupted min value search\033[0m")
    
    # ---------- TO LIST ----------
    def to_list(self):
        nodes = []
        inorder(self.root, nodes)

        tenants = []
        for tenant_node in nodes:
            tenants.append({
                "tenant_id": tenant_node.tenant_id,
                "name": tenant_node.name,
                "phone": tenant_node.phone,
                "unit": tenant_node.unit,
                "lease": tenant_node.lease_duration,
            })
        return tenants

    # ---------- DISPLAY ----------
    def display_in_order(self, order_type=1):
        # simple alias to match menu options
        return self.display_result_found(order_type)

    def display_result_found(self, order_type=1):
        try:
            # inorder to display tenants
            if self.root is None:
                logger.warning(f"\033[\n93mWARNING: !* No tenants in the system\033[0m")
            elif order_type == 1:
                logger.info(f"\033[\n94mINFO: !* Displaying tenants in inorder...\033[0m")
                inorder(self.root)
            elif order_type == 2:
                logger.info(f"\033[\n94mINFO: !* Displaying tenants in preorder...\033[0m")
                preorder(self.root)
            elif order_type == 3:
                logger.info(f"\033[\n94mINFO: !* Displaying tenants in postorder...\033[0m")
                postorder(self.root)
            else:
                logger.error(f"\033[91mERROR: !* Invalid display order '{order_type}'\033[0m")

        except Exception as e:
            logger.error(f"\n\033[91mERROR: !* Exception in displaying tenants: {e}\033[0m")
            logger.error(f"\n\033[91mERROR: !* Interrupted display\033[0m")
            raise
        
        
