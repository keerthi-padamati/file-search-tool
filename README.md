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

## 🧰 Technologies Used

- **Java 17+** (or any modern version supporting `ForkJoinPool` and NIO)
- `java.nio.file.*` for efficient filesystem traversal
- `java.util.concurrent.*` for parallel processing

---

## 🧠 How It Works

1. Lists all **available system drives** (e.g., `C:\`, `D:\`, etc.)
2. Prompts the user to enter a file or folder name to search.
3. Spawns multiple parallel search tasks using a **ForkJoinPool**:
   - Each task recursively scans directories.
   - Matches are collected into a **thread-safe queue** (`ConcurrentLinkedQueue`).
4. When all tasks finish, it prints:
   - All matching file/folder paths  
   - Total time taken  
   - Number of threads used

---


## 💻 Example Output
Detecting drives...
C:
D:
E:\

Enter the file/folder name to search: sample.txt

Files/Folders found:
C:\Users\Keerthi\Documents\sample.txt
D:\Backup\sample.txt

Search completed in 3.27 seconds using 32 threads.


---

## How to Run

1. **Clone the repository**
   ```bash
   git clone https://github.com/keerthi-padamati/File_searcher.git

2.   Open the project in your IDE (e.g., IntelliJ, Eclipse, or VS Code).
3.   
   javac -d out src/File_searcher/file_searcher.java
4.Run the program.
java -cp out File_searcher.file_searcher


