

|GUIGraphical User Interface| ![](../Assets/logo.svg.png) |
:--:| :-:


#### 15.4.2025 Ondřej Kučera
---

### Dělení

|<span style="color:red;">Top-level</span>|<span style="color:green;">Komponenty</span>|<span style="color:blue;">Layout Manager</span>|
:--: | :--: |:--:
|*JFrame*|*JPanel*|*FlowLayout*|
|*JDialog*|*JButton*|*GridLayout*|
|*JWindow*|*JLabel*|*BorderLayout*|
|*JOptionPane*|*JComboBox*|*GroupLayout*|
||*JListView*||
||*JTextField*||

---

#### Ukázka

```java
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.*;

import data.Osoba;

public class MainFrame extends JFrame {
    private final List<Osoba> lideList;
    private JButton prevButton, nextButton, novyButton;
    private JLabel jmenoLabel, dnLabel, sexLabel, barva;

    public MainFrame(List<Osoba> lideList) {
        JPanel mainPanel = new JPanel(new BorderLayout());
        this.getContentPane().add(mainPanel);

        JPanel tlacitkaPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        prevButton = new JButton(text:"<<");
        nextButton = new JButoon(text:">>");
        novyButton = new JButton(text:"+");
        tlacitkaPanel.add(prevButton);
        tlacitkaPanel.add(nextButton);
        tlacitkaPanel.add(novyButton);

        this.setPreferredSize(new Dimension(width:500, height:300));

    }
}
```

