# 📘 Group 6 – Rental Management Systems

This repository contains two independent Rental Management System implementations developed by **Group 6**:

* **Member 1 – Tenant & Lease Management System (BST, Python/EXE)**
* **Member 2 – Property Rental Management System (Java JAR Application)**

Each system resides in its own directory with separate build outputs and documentation.

---

# 📁 Repository Structure

```text
Group6_RentalManagement/
├── Member1_Tenant and Lease Management System/
│   └── code/
│       └── dist/
│           └── TenantBST.exe
└── Member2_Property Rental System/
    └── RentalManagementSystem/
        └── dist/
            ├── RentalManagementSystem.jar
            └── lib/   (dependency JARs)
```

---

# ⭐ Member 1 – Tenant & Lease Management System (BST)

**Course:** MCSS1023 – Advanced Data Structures & Algorithms
**Student:** Connie Tang Ming Xin
**Implementation:** Binary Search Tree (Solution 1)



---

## 📌 Overview

This project implements a **Tenant & Lease Management System** using a **Binary Search Tree (BST)** to store and manage tenant records efficiently.
The system runs through a **console-based menu interface** and uses **JSON** for persistent local storage.

**Features include:**

* Insert new tenants
* Search tenant by ID
* Update tenant details
* Delete tenant
* Display tenants using:

  * Inorder traversal
  * Preorder traversal
  * Postorder traversal
* Automatic JSON saving (`tenant_test_data.json`)

---

## 🚀 How to Run (EXE Version — No Python Needed)

1. Navigate to:

   ```text
   Member1_Tenant and Lease Management System\code\dist\
   ```

2. Run:

   ```text
   TenantBST.exe
   ```

The executable includes all dependencies via PyInstaller — **no Python installation required**.

---

## 📁 Files Included

### Source Code (`code/`)

* `main.py`
* `tenant_bst_system.py`
* `node.py`
* `display_order.py`
* `random_data.py`
* `tenant_test_data.json` *(auto-updated at runtime)*
* `tenant_bst.log` *(generated during execution)*

### Executable (`code/dist/`)

* `TenantBST.exe`

---

## 🛠 BST Features

* `insert_tenant()`
* `search_tenant()`
* `update_tenant()`
* `delete_tenant()`
* BFS + DFS visual traversal ordering
* Data persistence after every modification

---

## 📌 Notes

* Ensure `tenant_test_data.json` remains in the **same folder** as `TenantBST.exe`.
* Uses ANSI color codes with `colorama` for enhanced CLI visuals.

---

# ⭐ Member 2 – Property Rental Management System (Java)



---

## 📌 Overview

Java console application that loads property listings from `src/csv/mudah-apartment-kl-selangor.csv` into a Binary Search Tree keyed by `ads_id`. The menu lets you list, search, add, update, and delete properties while keeping them sorted by ID.

---

## 🚀 How to Run

1. From the repository root, go to `Member2_Property Rental System/PropertyRentalSystemBst`.
2. Ensure `src/csv/mudah-apartment-kl-selangor.csv` remains in place (the program reads it on startup).
3. Run:
   ```
   java -cp build/classes propertyrentalsystembst.PropertyRentalSystem
   ```
The app stays in the menu until you enter `0` to exit.

---

## 🛠 Rebuilding from source

If you need to recompile:
```
cd "Member2_Property Rental System/PropertyRentalSystemBst"
javac -d build/classes src/propertyrentalsystembst/*.java
java -cp build/classes propertyrentalsystembst.PropertyRentalSystem
```

The NetBeans/Ant build file `build.xml` is included if you prefer to build a JAR yourself; no prebuilt JAR is committed.

### Files

- Source code: `src/propertyrentalsystembst/` (`Bst.java`, `BstNode.java`, `CsvLoader.java`, `Property.java`, `PropertyRentalSystem.java`, `RentalService.java`).
- Data: `src/csv/mudah-apartment-kl-selangor.csv` (also copied to `build/classes/csv/`).
- Build outputs: `build/classes/` contains compiled `.class` files ready to run with the command above.