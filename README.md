# Movable-ResizableJPanel

## Description

`MovableJPanel` is an extension of `JPanel` that allows you to create panels in a graphical interface that are both **movable** and **resizable**. This component provides an easy way to add drag and resize functionality to your Swing interfaces. Users can move the panel by dragging it anywhere within the panel, and resize it by dragging the edges or corners of the panel.

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
