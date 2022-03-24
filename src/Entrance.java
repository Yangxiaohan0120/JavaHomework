import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Xh Yang / Bl Wang / Yu Liang
 * 此程序用于展示新冠肺炎在各国的增张情况以及在各国之间的分布情况
 * @parameter inputfile
 * 输入文件格式为国家m X 天数n的矩阵
 *
 */
public class Entrance extends JFrame {

    public Entrance() {
        //输入文件及矫正路径，将文件放在入口处，便于后续的维护
        String inputfile = System.getProperty("user.dir") + "/database/Covid.txt";
        System.out.println(inputfile);

        /*
        * 初始界面
        * 初始化button并添加：*/
        JFrame a = new JFrame("Entrance");
        JButton[] b = new JButton[2];
        for (int i = 0; i < b.length; i++) {
            b[i] = new JButton();
        }
        for (int i = 0; i < b.length; i++) {
            a.add(b[i]);
        }
        a.setSize(350, 250);
        a.setLocationRelativeTo(null);
        a.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        a.setVisible(true);

        /*
        * 设置button的位置和文字*/
        b[0].setBounds(100, 50, 150, 50);
        b[1].setBounds(100, 100, 150, 50);
        b[0].setText("PieChart");
        b[1].setText("LineChart");

        /*
        * 设置button的链接效果
        * 分别连接到piechart和linechart*/
        b[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PieChartGUI(inputfile);
                setVisible(false);
            }
        });

        b[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LineChartGUI(inputfile);
                setVisible(false);
            }
        });

    }

    public static void main(String[] args) {
        new Entrance();
    }
}