package view.customObjects;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MovableJPanel extends JPanel {

    // Sides of a system window
    private enum WindowSide {
        TOP,
        RIGHT,
        BOTTOM,
        LEFT,
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_RIGHT,
        BOTTOM_LEFT
    }

    // which side was clicked
    private WindowSide lastWindowSide;
    
    private boolean isActivatedWindowResize = false;
    
    // The margin area for resizing
    private int borderActivationMargin = 15; // This is the property we want to expose

    // mouse position
    private int xMouse, yMouse;

    public MovableJPanel() {
        setOpaque(true);
        setMinimumSize(new Dimension(100, 500));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                xMouse = event.getX();
                yMouse = event.getY();

                lastWindowSide = getWindowSide(event);
                isActivatedWindowResize = (lastWindowSide != null);
            }

            @Override
            public void mouseReleased(MouseEvent event) {
                isActivatedWindowResize = false;
                setCursor(Cursor.getDefaultCursor());
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent event) {
                Window window = SwingUtilities.windowForComponent(MovableJPanel.this);
                if (window != null) {
                    if (isActivatedWindowResize) {
                        resizeWindow(window, event);
                    } else {
                        moveWindow(window, event);
                        
                    }
                }
            }

            @Override
            public void mouseMoved(MouseEvent event) {
                WindowSide resizeArea = getWindowSide(event);
                setCursor(resizeArea != null ? getResizingCursor(resizeArea) : Cursor.getDefaultCursor());
            }
        });
    }

    public void setMousePosition(int x, int y) {
        this.xMouse = x;
        this.yMouse = y;
    }

    public int getXMouse() {
        return xMouse;
    }

    public int getYMouse() {
        return yMouse;
    }
    
    // Getter and Setter for the borderActivationMargin property
    public int getBorderActivationMargin() {
        return borderActivationMargin;
    }

    public void setBorderActivationMargin(int borderActivationMargin) {
        this.borderActivationMargin = borderActivationMargin;
    }
    
    // Move the window based on mouse position
    private void moveWindow(Window window, MouseEvent event) {
        int x = event.getXOnScreen();
        int y = event.getYOnScreen();
        window.setLocation(x - xMouse, y - yMouse);
    }
    
    // Determine which part of the window is being interacted with
    private WindowSide getWindowSide(MouseEvent event) {
        int width = getWidth();
        int height = getHeight();

        if (event.getX() <= borderActivationMargin && event.getY() <= borderActivationMargin) {
            return WindowSide.TOP_LEFT;
        }
        if (event.getX() >= width - borderActivationMargin && event.getY() <= borderActivationMargin) {
            return WindowSide.TOP_RIGHT;
        }
        if (event.getX() <= borderActivationMargin && event.getY() >= height - borderActivationMargin) {
            return WindowSide.BOTTOM_LEFT;
        }
        if (event.getX() >= width - borderActivationMargin && event.getY() >= height - borderActivationMargin) {
            return WindowSide.BOTTOM_RIGHT;
        }
        if (event.getX() <= borderActivationMargin) {
            return WindowSide.LEFT;
        }
        if (event.getX() >= width - borderActivationMargin) {
            return WindowSide.RIGHT;
        }
        if (event.getY() <= borderActivationMargin) {
            return WindowSide.TOP;
        }
        if (event.getY() >= height - borderActivationMargin) {
            return WindowSide.BOTTOM;
        }

        return null;
    }

    // Get the appropriate resizing cursor based on the window's side
    private Cursor getResizingCursor(WindowSide windowSide) {
        switch (windowSide) {
            case TOP_LEFT:
                return Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
            case TOP_RIGHT:
                return Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);
            case BOTTOM_LEFT:
                return Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR);
            case BOTTOM_RIGHT:
                return Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR);
            case LEFT:
                return Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR);
            case RIGHT:
                return Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR);
            case TOP:
                return Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
            case BOTTOM:
                return Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
            default:
                return Cursor.getDefaultCursor();
        }
    }

    // Resize the window based on the mouse's drag position
    private void resizeWindow(Window window, MouseEvent event) {
        window.setLayout(null);
        this.setBounds(0, 0, window.getWidth(), window.getHeight());

        
        if (!isActivatedWindowResize || lastWindowSide == null) {
            return;
        }
        int initialX = window.getX();
        int initialY = window.getY();
        int x = initialX;
        int y = initialY;
        int width = window.getWidth();
        int height = window.getHeight();
        int mouseX = event.getXOnScreen();
        int mouseY = event.getYOnScreen();

        switch (lastWindowSide) {
            case TOP_LEFT:
                if (lastWindowSide == WindowSide.TOP_LEFT) {
                    width = width + (x - mouseX);
                    height = height + (y - mouseY);
                    x = mouseX;
                    y = mouseY;
                }
                break;

            case TOP_RIGHT:
                if (lastWindowSide == WindowSide.TOP_RIGHT) {
                    width = mouseX - x;
                    height = height + (y - mouseY);
                    y = mouseY;
                }
                break;

            case BOTTOM_LEFT:
                if (lastWindowSide == WindowSide.BOTTOM_LEFT) {
                    width = width + (x - mouseX);
                    height = mouseY - y;
                    x = mouseX;
                }
                break;

            case BOTTOM_RIGHT:
                if (lastWindowSide == WindowSide.BOTTOM_RIGHT) {
                    width = mouseX - x;
                    height = mouseY - y;
                }
                break;

            case LEFT:
                if (lastWindowSide == WindowSide.LEFT) {
                    width = width + (x - mouseX);
                    x = mouseX;
                }
                break;

            case RIGHT:
                if (lastWindowSide == WindowSide.RIGHT) {
                    width = mouseX - x;
                }
                break;

            case TOP:
                if (lastWindowSide == WindowSide.TOP) {
                    height = height + (y - mouseY);
                    y = mouseY;
                }
                break;

            case BOTTOM:
                if (lastWindowSide == WindowSide.BOTTOM) {
                    height = mouseY - y;
                }
                break;
        }
        
        // Verify de minimun Size
        Dimension minSize = getMinimumSize();
        if (width < minSize.width) {
            width = minSize.width;
            x = initialX;
        }
        if (height < minSize.height) {
            height = minSize.height;
            y = initialY;
        }
        window.setBounds(x, y, width , height);
    }

}
