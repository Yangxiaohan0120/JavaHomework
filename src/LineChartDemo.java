import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 此程序用来展示各个国家累计感染人数的增长情况
 */
public class LineChartDemo {

    //添加linechart的坐标、标题
    public LineChartDemo(String inputfile, String cityname) {
        CategoryDataset mDataset = GetDataset(inputfile, cityname);
        JFreeChart mChart = ChartFactory.createLineChart(
                "新冠肺炎感染人数增长图_" + cityname,//图名字
                "天数/天",//横坐标
                "累计感染人数/人",//纵坐标
                mDataset,//数据集
                PlotOrientation.VERTICAL,
                true, // 显示图例
                true, // 采用标准生成器
                false);// 是否生成超链接

        CategoryPlot mPlot = (CategoryPlot) mChart.getPlot();
        mPlot.setBackgroundPaint(Color.WHITE);
        mPlot.setRangeGridlinePaint(Color.BLUE);//背景底部横虚线
        mPlot.setOutlinePaint(Color.RED);//边界线

        ChartFrame mChartFrame = new ChartFrame("折线图", mChart);
        mChartFrame.pack();
        mChartFrame.setVisible(true);
    }

    //根据Cityname在linechart中导入数据
    public CategoryDataset GetDataset(String inputfile, String cityname) {
        DefaultCategoryDataset mDataset = new DefaultCategoryDataset();
        HashMap<Number, Number> increaseline = new HashMap<>();
        increaseline = getInformationLineChart(inputfile, cityname);
        for (int i = 0; i < increaseline.size(); i++) {
            int day = i;
            String day1 = String.valueOf(day);
            mDataset.addValue(increaseline.get(day), cityname, day1);
        }
        return mDataset;
    }

    //返回一个关于cityname这个城市的每天人数hashmap
    public HashMap<Number, Number> getInformationLineChart(String inputfile, String cityname) {
        String temp = null;
        String[] temps = null;
        HashMap<Number, Number> increaseLine = new HashMap<>();
        HashMap<String, String> CityInformation = new HashMap<>();
        CityInformation = citysite(inputfile);
        temp = CityInformation.get(cityname);
        temps = temp.split("\t");
        try {
            for (int i = 1; i < temps.length; i++) {
                int day = i;
                increaseLine.put(day, Integer.parseInt(temps[i]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return increaseLine;
    }

    //返回一个关于不同城市名字和对应信息的hashmap
    public HashMap<String, String> citysite(String inputfile) {
        BufferedReader br = IOUtils.getTextReader(inputfile);
        String temp = null;
        String[] temps = null;
        HashSet<String> CityName = new HashSet<>();
        HashMap<String, String> CityInformation = new HashMap<>();
        try {
            br.readLine();
            while ((temp = br.readLine()) != null) {
                temps = temp.split("\t");
                String cityname = temps[0];
                CityName.add(cityname);
                CityInformation.put(cityname, temp);
            }
            br.close();
            return CityInformation;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CityInformation;
    }

    public static void main(String[] args) {
        String inputfile = args[0];
        String cityname = args[1];
        new LineChartDemo(inputfile, cityname);

    }
}