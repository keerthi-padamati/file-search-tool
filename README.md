# 🚀 file-search-tool

A high-performance, multi-threaded file discovery tool built in Java.  
This project demonstrates **industry-level concurrency and performance optimization** by scanning entire system drives in seconds.

---

## 🧐 What is this project?

`file-search-tool` is a command-line utility that searches for files or folders across your **entire computer**.

Unlike traditional file search tools that scan directories one at a time, this project:
- Breaks the workload into **hundreds of small tasks**
- Executes them **in parallel**
- Utilizes **all available CPU cores** for maximum speed

---

## ⚡ Standard Search vs `file-search-tool`

| Feature | Standard File Explorer | `file-search-tool` |
|------|-----------------------|--------------------|
| Search Logic | Sequential (one folder at a time) | **Parallel (many folders at once)** |
| CPU Usage | Low (single core) | **High (uses all cores)** |
| Algorithm | Simple recursion | **Fork-Join / Work-Stealing** |
| Speed | Often slow | **Near-instant results** |

---

## 🛠️ Performance Optimizations (Expert-Level)

This project is not a basic loop. It uses **professional-grade techniques**:

### Fork-Join Framework
- Uses `RecursiveAction` to split directory traversal into many tasks
- Idle threads automatically **steal work** from busy threads
- Ensures maximum CPU utilization

### Directory Streaming
- Uses `Files.newDirectoryStream`
- Avoids `File.listFiles()` which is slow and memory-heavy
- Efficient for directories with thousands of files

### Lock-Free Progress Tracking
- Uses `LongAdder` for tracking scanned items
- Prevents thread contention
- Allows millions of updates with zero slowdown

### Smart Error Handling
- Automatically skips protected system directories
- Handles `AccessDeniedException` safely
- Search continues without crashing

---

## 🚀 How to Run

### 1. Clone the repository
```bash
git clone https://github.com/keerthi-padamati/file-search-tool.git

2. Compile the program : javac file_searcher.java

3. Run the search tool : java file_searcher
```
---

### Sample Output
Detecting system drives...
Drive: C:\
Drive: D:\

Enter file/folder name to search: project_final
Total items scanned: 1,245,890...

--- Search Results ---
[MATCH] C:\Users\Admin\Documents\project_final.docx
[MATCH] D:\Backup\Work\source\project_final.zip

Search completed in 1.45 seconds using 16 threads.
