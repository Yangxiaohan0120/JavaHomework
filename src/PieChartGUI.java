import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;

/**
 * 此程序用来展示各个国家累计感染人数的分布情况的Button入口界面
 */

public class PieChartGUI extends JFrame {

    private static final JDesktopPane DESKTOP_PANE = new JDesktopPane();

    public PieChartGUI(String inputfile) {
        JFrame f = new JFrame("Demo");

        //根据文件，查看天数
        BufferedReader br = IOUtils.getTextReader(inputfile);
        String temp = null;
        String[] temps = null;
        StringBuffer sb = new StringBuffer();
        String[] dates = null;
        try {
            temp = br.readLine();
            temps = temp.split("\t");
            for (int i = 1; i < temps.length; i++) {
                sb.append(temps[i] + "\t");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        dates = sb.toString().split("\t");

        //根据天数生成button并链接至对应天数的piechart
        JButton[] b = new JButton[dates.length];
        for (int i = 0; i < b.length; i++) {
            int x = 130 * (i % 5);
            int y = (i / 5) * 35;
            b[i] = new JButton();
            b[i].setBounds(x, y, 120, 30);
            b[i].setText("NO." + dates[i] + " day");
            String date = b[i].getText().replace(" day", "").split("NO.")[1];

            b[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    PieChartDemo d = new PieChartDemo(inputfile, date);
                }
            });
        }
        for (int i = 0; i < dates.length; i++) {
            f.add(b[i]);
        }
        f.setSize(750, 750);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setVisible(true);
    }

    public static void addIFame(JInternalFrame iframe) { // 添加子窗体的方法
        DESKTOP_PANE.add(iframe);
    }

    public static void main(String[] args) {
        new PieChartGUI(args[0]);
    }
}