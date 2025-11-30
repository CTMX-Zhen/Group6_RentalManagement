# **Tenant and Lease Management System (BST)**

**Course:** MCSS1023 – Advanced Data Structures & Algorithms

**Student:** Connie Tang Ming Xin (MEC245051)

**Group:** 6 – Rental Management

**Solution 1 – Binary Search Tree Implementation**

---

## 📌 **Project Overview**

This project implements a **Tenant and Lease Management System** using a **Binary Search Tree (BST)**.
The system supports:

* Insert new tenants
* Search tenant by ID
* Update tenant details
* Delete tenant
* Display tenants using:

  * Inorder Traversal
  * Preorder Traversal
  * Postorder Traversal
* Persistent data storage using `tenant_test_data.json`

The program runs through a **console-based menu interface**.

---

## 🚀 **How to Run (EXE Version)**

**No Python environment is required.**

1. Navigate to the folder:

```
code\dist\
```

2. Run the executable file:

```
TenantBST.exe
```

3. The program will open in a console window and display the interactive menu.

> The EXE includes all required Python dependencies bundled using PyInstaller, so it runs on any Windows system without installing Python or managing environments.

---

## 📁 **Included Files**

### **Source Code (`code/`)**

* `main.py`
* `tenant_bst_system.py`
* `node.py`
* `display_order.py`
* `random_data.py`
* `tenant_test_data.json` *(auto-updated)*
* `tenant_bst.log` *(generated during execution)*

### **Executable (`code/dist/`)**

* `TenantBST.exe`

  * This is the standalone version that runs without Python.

---

## 🛠 **Features Implemented**

### **Core BST Functions**

* `insert_tenant()`
* `search_tenant()`
* `update_tenant()`
* `delete_tenant()`
* Traversals: `inorder`, `preorder`, `postorder`

### **Data Persistence**

After each insert/update/delete operation, the system automatically:

1. Converts BST → list using inorder traversal
2. Saves to `tenant_test_data.json`

---

## 🎥 **Video Demonstration**

A separate demo video (less than 20 minutes) explains:

1. Functions implemented
2. Live demo of each function
3. Planned improvements (search features, validation, GUI, etc.)

---

## 📌 **Notes**

* Ensure `tenant_test_data.json` is in the same folder as `TenantBST.exe` when running.
* Color output is supported through ANSI codes and `colorama` initialization.