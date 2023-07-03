package org.example;

import org.graphstream.ui.geom.Point2;
import org.graphstream.ui.geom.Point3;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.camera.Camera;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class ControlManager
{
    private final View view;
    public ControlManager(View view) { this.view = view; }

    public void enableKeyboardControls(Viewer viewer)
    {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            final IsKeyPressed keyPressed = new IsKeyPressed();

            @Override
            public boolean dispatchKeyEvent(KeyEvent ke) {
                synchronized (IsKeyPressed.class) {
                    switch (ke.getID()) {
                        case KeyEvent.KEY_PRESSED:
                            if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                keyPressed.isPressed = true;
                                viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.CLOSE_VIEWER);
                                viewer.close();
                                System.exit(0);

                            } else if (ke.getKeyCode() == KeyEvent.VK_R) {
                                keyPressed.isPressed = true;
                                view.getCamera().resetView();

                            } else if (ke.getKeyCode() == KeyEvent.VK_F) {
                                keyPressed.isPressed = true;
                            }
                            break;
                        case KeyEvent.KEY_RELEASED:
                            if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                keyPressed.isPressed = false;
                            }
                            break;
                    }
                    return false;
                }
            }
        });
    }
    public void enableMouseControls()
    {
        ((Component) view).addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                e.consume();
                int i = e.getWheelRotation();
                double factor = Math.pow(1.25, i);
                Camera cam = view.getCamera();
                double zoom = cam.getViewPercent() * factor;
                Point2 pxCenter = cam.transformGuToPx(cam.getViewCenter().x, cam.getViewCenter().y, 0);
                Point3 guClicked = cam.transformPxToGu(e.getX(), e.getY());
                double newRatioPx2Gu = cam.getMetrics().ratioPx2Gu / factor;
                double x = guClicked.x + (pxCenter.x - e.getX()) / newRatioPx2Gu;
                double y = guClicked.y - (pxCenter.y - e.getY()) / newRatioPx2Gu;
                cam.setViewCenter(x, y, 0);
                cam.setViewPercent(zoom);
            }
        });
    }

}
 class IsKeyPressed {
    static volatile boolean isPressed = false;

    public static boolean keyPressed() {
        synchronized (IsKeyPressed.class) {
            return isPressed;
        }
    }
}