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

This project is a **Java-based Rental Management System** packaged into a runnable JAR file.
When built by the IDE, all required dependency JARs are placed into a `lib/` directory and linked inside the JAR’s manifest (`MANIFEST.MF`).

---

## 🚀 How to Run

1. Navigate to:

   ```text
   Member2_Property Rental System\RentalManagementSystem\dist\
   ```

2. Run the application:

   ```bash
   java -jar "RentalManagementSystem.jar"
   ```

The program will automatically load all JARs from the `lib/` folder.

---

## 📦 Distribution

To distribute the program:

1. Zip the entire **dist/** folder:

   ```text
   dist/
   ├── RentalManagementSystem.jar
   └── lib/
       └── (all dependency JARs)
   ```

2. Share the ZIP.
   The receiver can run the program immediately using:

   ```bash
   java -jar RentalManagementSystem.jar
   ```

---

## 🛠 Build & Classpath Details

* All JARs on the classpath are copied to `dist/lib/`.
* They are also added to the `Class-Path` attribute in the JAR manifest.
* If duplicate filenames exist, only the **first** JAR is copied.
* Only **JAR files** are copied — other file types/folders are ignored.
* If a library contains its own manifest `Class-Path`, those referenced files **must** also exist at runtime.

### Setting Main Class in IDE

To define the entry point:

1. Right-click the project
2. Select **Properties → Run**
3. Set the **Main Class**

   * or manually edit the manifest's `Main-Class` entry
