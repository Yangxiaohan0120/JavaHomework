import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;

/**
 * 此程序用来展示各个国家累计感染人数的增长情况的Button入口界面
 */

public class LineChartGUI extends JFrame {

    private static final JDesktopPane DESKTOP_PANE = new JDesktopPane();

    public LineChartGUI(String inputfile) {
        //根据文件生成城市
        BufferedReader br = IOUtils.getTextReader(inputfile);
        String temp = null;
        String[] temps = null;
        StringBuffer sb = new StringBuffer();
        String[] citynames = null;
        try {
            while ((temp = br.readLine()) != null) {
                if (!temp.startsWith("Country")) {
                    temps = temp.split("\t");
                    sb.append(temps[0] + "\t");
                }
                continue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        citynames = sb.toString().split("\t");
        JFrame f = new JFrame("Demo");

        //根据城市生成button并连接到每个城市的linechart
        JButton[] b = new JButton[citynames.length];
        for (int i = 0; i < b.length; i++) {
            int x = 130 * (i % 4);
            int y = (i / 4) * 35;
            b[i] = new JButton();
            b[i].setBounds(x, y, 120, 30);
            b[i].setText(citynames[i]);
            String cityname = b[i].getText();
            b[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    LineChartDemo d = new LineChartDemo(inputfile, cityname);
                }
            });
        }
        for (int i = 0; i < citynames.length; i++) {
            f.add(b[i]);
        }
        f.setSize(600, 300);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setVisible(true);
    }

    public static void addIFame(JInternalFrame iframe) { // 添加子窗体的方法
        DESKTOP_PANE.add(iframe);
    }

    public static void main(String[] args) {
        new LineChartGUI(args[0]);
    }
}