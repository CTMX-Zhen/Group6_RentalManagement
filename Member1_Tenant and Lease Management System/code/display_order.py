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

# ===== DISPLAY ORDERS =====
def inorder(node, collector=None):
    try:
        if node is not None:
            inorder(node.left, collector)

            if collector is not None:
                collector.append(node)

            logger.info(f"\033[\n96mDEBUG: !* Tenant details - inorder: {node}\033[0m")

            inorder(node.right, collector)

    except Exception as e:
        logger.error(f"\n\033[91mERROR: !* Exception in inorder: {e}\033[0m")
        logger.error(f"\n\033[91mERROR: !* Interrupted inorder\033[0m")
        raise

def preorder(node, collector=None):
    try:
        if node is not None:

            if collector is not None:
                collector.append(node)

            logger.info(f"\033[\n96mDEBUG: !* Tenant details - preorder: {node}\033[0m")

            preorder(node.left, collector)
            preorder(node.right, collector)

    except Exception as e:
        logger.error(f"\n\033[91mERROR: !* Exception in preorder: {e}\033[0m")
        raise

def postorder(node, collector=None):
    try:
        if node is not None:

            postorder(node.left, collector)
            postorder(node.right, collector)

            if collector is not None:
                collector.append(node)

            logger.info(f"\033[\n96mDEBUG: !* Tenant details - postorder: {node}\033[0m")

    except Exception as e:
        logger.error(f"\n\033[91mERROR: !* Exception in postorder: {e}\033[0m")
        raise
