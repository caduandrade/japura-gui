package org.japura.examples.gui.checkcombobox;

import org.japura.examples.Country;
import org.japura.examples.gui.AbstractExample;
import org.japura.gui.Anchor;
import org.japura.gui.BatchSelection;
import org.japura.gui.CheckComboBox;
import org.japura.gui.EmbeddedComponent;
import org.japura.gui.event.ListCheckListener;
import org.japura.gui.event.ListEvent;
import org.japura.gui.i18n.DefaultGUIHandlerString;
import org.japura.gui.model.ListCheckModel;
import org.japura.util.i18n.I18nAdapter;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

public class Example8 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    List<String> countries = Country.getCountries();

    CheckComboBox ccb = new CheckComboBox();

    ListCheckModel model = ccb.getModel();
    for (String color : countries) {
      model.addElement(color);
    }

    BatchSelection bs = new CustomBatchSelection();
    EmbeddedComponent comp = new EmbeddedComponent(bs, Anchor.NORTH);
    ccb.setEmbeddedComponent(comp);

    JPanel panel = new JPanel();
    panel.setOpaque(false);
    panel.add(ccb);
    return panel;
  }

  public static void main(String[] args) {
    I18nAdapter.getAdapter().registerHandler(new DefaultGUIHandlerString());
    Example8 example = new Example8();
    example.runExample();
  }

  public static class CustomBatchSelection extends BatchSelection.Link {

    private JLabel countLabel;
    private ListCheckListener modelListener;

    private JLabel getCountLabel() {
      if (countLabel == null) {
        countLabel = new JLabel("[0]");
      }
      return countLabel;
    }

    @Override
    protected void initialization() {
      setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
      setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.gridx = 0;
      gbc.gridy = 0;
      add(getCountLabel(), gbc);

      gbc.gridx = 1;
      gbc.weightx = 1;
      gbc.anchor = GridBagConstraints.EAST;
      gbc.insets = new Insets(0, 10, 0, 0);
      add(getSelectAllButton(), gbc);

      gbc.gridx = 2;
      gbc.weightx = 0;
      add(getDeselectAllButton(), gbc);
    }

    private void updateCount(int count) {
      getCountLabel().setText("[" + count + "]");
    }

    @Override
    protected void unregisterModel() {
      super.unregisterModel();
      if (modelListener != null) {
        getModel().removeListCheckListener(modelListener);
      }
      modelListener = null;
    }

    @Override
    protected void registerModel(final ListCheckModel model) {
      super.registerModel(model);
      modelListener = new ListCheckListener() {
        @Override
        public void removeCheck(ListEvent event) {
          updateCount(model.getChecksCount());
        }

        @Override
        public void addCheck(ListEvent event) {
          updateCount(model.getChecksCount());
        }
      };
      model.addListCheckListener(modelListener);
    }
  }

}
