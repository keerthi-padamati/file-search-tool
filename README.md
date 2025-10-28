#  File Searcher

**File Searcher** is a fast, concurrent Java utility that searches your entire computer for files or folders by name.  
It scans all available drives in parallel using a **ForkJoinPool**, providing quick and efficient results even across large file systems.

---

## ⚙️ Features

- **Multi-threaded search** using Java’s `ForkJoinPool`
-  Scans all available system drives automatically
-  Supports both **file and folder** name searches
-  Case-insensitive matching
-  Displays total search time and thread usage
-  Safe concurrent result collection using `ConcurrentLinkedQueue`

---
