# Movable-ResizableJPanel

## Description

`MovableJPanel` is an extension of `JPanel` designed to allow you to create your own customizable window, ideal for custom **UX/UI** implementations. It provides functionality to create panels in a graphical interface that are both **movable** and **resizable**. This component offers an easy way to add drag and resize capabilities to your Swing interfaces. Users can move the panel by dragging it anywhere within the window, and resize it by dragging the edges or corners of the panel.

### Features:
- **Movable**: You can drag the panel around the window.
- **Resizable**: You can resize the panel from any of its edges or corners.
- **Customizable**: The active resizing area can be modified through the `borderActivationMargin` property, and the minimum panel size is also configurable.

## Installation

1. Clone or download the repository.
2. Compile the code or use the `.jar` file from the `dist/` folder to add it to your project.

   If you're using an IDE like Eclipse or IntelliJ, simply add the `.jar` file to your project libraries.

## Usage

You can use `MovableJPanel` just like any other `JPanel` in a Swing interface. Below is an example of how to integrate it into a `JFrame`.

### Example:

```java
import javax.swing.*;
import view.customObjects.MovableJPanel;

public class Main {
    public static void main(String[] args) {
        // Create a new instance of MovableJPanel
        MovableJPanel movablePanel = new MovableJPanel();
        
        // Create a JFrame to contain the panel
        JFrame frame = new JFrame("Movable and Resizable Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400); // Initial JFrame size
        
        // Add the movable and resizable panel to the JFrame
        frame.add(movablePanel);
        
        // Show the window
        frame.setVisible(true);
    }
}
### Manifest and .jar Creation

If you modify the `MovableJPanel` class and want to regenerate the `.jar` file, follow these steps:

1. **Compile the Java Class**: In your terminal or command prompt, navigate to the directory containing `MovableJPanel.java` and run:

   ```bash
   javac .\MovableJPanel.java

This will generate the .class files needed for the .jar.

Generate the .jar: Once you've compiled the Java class, create the .jar file using the following command:
jar cfm MovableJPanel.jar manifest.txt .\MovableJPanel.class
Make sure to include a manifest.txt file to specify the necessary metadata.

Adding to Your Project
You can add MovableJPanel to your Swing project in two ways:

Using the .jar: If you've created the .jar file, you can simply add it to your project libraries and use the class as any other Swing component.

Copying the .java file: You can directly copy the MovableJPanel.java file into your project. Once added, simply drag it onto your JFrame form in your IDE. Be sure to set the JFrame to undecorated when using this component, as it is designed to allow you to create your own customizable window, which is ideal for custom UX/UI implementations.

