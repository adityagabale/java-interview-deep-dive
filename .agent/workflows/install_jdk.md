---
description: Install JDK 17 using Homebrew
---
Since you are on macOS and have `brew` installed, the easiest way to install Java is via Homebrew.

1. **Install OpenJDK 17**:
// turbo
   ```bash
   brew install openjdk@17
   ```

2. **Link the newly installed JDK** so the system can find it:
// turbo
   ```bash
   sudo ln -sfn /opt/homebrew/opt/openjdk@17/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk-17.jdk
   ```
   *Note: This step requires `sudo` access. If you are not comfortable with that, you can add it to your PATH instead.*

3. **Verify installation**:
// turbo
   ```bash
   java -version
   ```
