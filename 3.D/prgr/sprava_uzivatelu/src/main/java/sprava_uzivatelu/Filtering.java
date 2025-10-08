package sprava_uzivatelu;

import java.util.stream.Stream;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

public class Filtering {

    public static void searchfilter(String searchTitle, int columnIndex, JTable table, JPanel filterPanel) {
        JTextField Search = new JTextField(8);
        filterPanel.add(new JLabel(searchTitle + ":"));
        filterPanel.add(Search);

        Search.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {

            private void updateFilter() {
                String text = Search.getText();

                TableRowSorter<?> sorter = (TableRowSorter<?>) table.getRowSorter();
                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, columnIndex));
                }
            }

            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                updateFilter();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                updateFilter();
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                updateFilter();
            }

        });

    }

    public static void booleanfilter(String filterTitle, int columnIndex, JTable table, JPanel filterPanel) {
        String[] options = { "All", "YES", "NO" };
        JComboBox<String> comboBox = new JComboBox<>(options);
        filterPanel.add(new JLabel(filterTitle + ":"));
        filterPanel.add(comboBox);

        comboBox.addActionListener(e -> {
            String selectedOption = (String) comboBox.getSelectedItem();
            TableRowSorter<?> sorter = (TableRowSorter<?>) table.getRowSorter();
            if (selectedOption.equals("All")) {
                sorter.setRowFilter(null);
            } else if (selectedOption.equals("YES")) {
                sorter.setRowFilter(RowFilter.regexFilter("YES", columnIndex));
            } else if (selectedOption.equals("NO")) {
                sorter.setRowFilter(RowFilter.regexFilter("NO", columnIndex));
            }
        });
    }

    // TODO I am lazy to make this reusable
    public static void countryfilter(JTable table, JPanel filterPanel, PersonsTable personsTable) {
        Stream<String> countryStream = personsTable.getAllPersons().values().stream()
                .map(Person::getCountryCode).distinct().sorted();
        String[] countryCodes = Stream.concat(Stream.of("All"), countryStream).toArray(String[]::new);
        JComboBox<String> countryCodeBox = new JComboBox<>(countryCodes);
        filterPanel.add(new JLabel("Country:"));
        filterPanel.add(countryCodeBox);

        countryCodeBox.addActionListener(e -> {
            String selectedCountry = (String) countryCodeBox.getSelectedItem();
            TableRowSorter<?> sorter = (TableRowSorter<?>) table.getRowSorter();
            if (selectedCountry.equals("All")) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("^" + selectedCountry + "$", 7));
            }
        });

    }
}
