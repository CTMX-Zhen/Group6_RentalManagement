@echo off
title Tenant BST System.bat
call C:\Users\"%USERNAME%"\miniconda3\Scripts\activate.bat myenv
python main.py
pause