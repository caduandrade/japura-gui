package org.japura.examples.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

public abstract class AbstractExample {

  private JFrame frame;
  private JPanel rootPanel;

  protected JFrame getFrame() {
    if (frame == null) {
      frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setBackground(Color.WHITE);
    }
    return frame;
  }

  public void show() {
    getFrame().pack();
    getFrame().setLocationRelativeTo(null);
    getFrame().setVisible(true);
  }

  protected JPanel buildRootPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(buildRootLayout());
    panel.setBackground(Color.WHITE);
    return panel;
  }

  protected LayoutManager buildRootLayout() {
    return new FlowLayout();
  }

  protected JPanel getRootPanel() {
    if (rootPanel == null) {
      rootPanel = buildRootPanel();
      rootPanel.add(buildExampleComponent());
    }
    return rootPanel;
  }

  protected abstract Component buildExampleComponent();

  public void runExample() {
    JPanel panel = getRootPanel();
    getFrame().add(panel);
    show();
  }

  public void initializeNimbus() {
    try {
      for (UIManager.LookAndFeelInfo info : UIManager
        .getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    }
    catch (UnsupportedLookAndFeelException e) {
      // handle exception
    }
    catch (ClassNotFoundException e) {
      // handle exception
    }
    catch (InstantiationException e) {
      // handle exception
    }
    catch (IllegalAccessException e) {
      // handle exception
    }
  }

}
