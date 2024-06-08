/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package form;
import com.formdev.flatlaf.FlatLightLaf;
import connection.DatabaseConnect;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.chart.ui.VerticalAlignment;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import model.ModelGame;
import model.ModelUser;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.ui.RectangleEdge;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import service.gameService;
public class StatisticForm extends javax.swing.JPanel {
    private ModelUser user = new ModelUser();
    private final Connection con;
    private Swing.Table table1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public StatisticForm(ModelUser user) throws SQLException {
        this.user = user;
        FlatLightLaf.setup();
        con = DatabaseConnect.getInstance().getConnection();
        initComponents();
        createChartPanel();
    }
    private void createChartPanel() throws SQLException {
    // Load the table first
    

    // Create dataset
    CategoryDataset dataset;
    if(user.getRole().equals("Developer")){
        dataset = createDatasetDev();
    }
    else if(user.getRole().equals("Admin")){
        dataset = createDatasetAdmin();
    }else{
        dataset = createDatasetUser();
    }
    
    // Create chart
    JFreeChart chart = ChartFactory.createBarChart(
            "Thống kê chi tiêu 2024", // Chart title
            "Month", // X-Axis Label
            "Value", // Y-Axis Label
            dataset, // Dataset
            PlotOrientation.VERTICAL,
            true, true, false
    );

    // Customize the chart
    customizeChart(chart);

    // Create ChartPanel and add it to the JScrollPane
    ChartPanel chartPanel = new ChartPanel(chart);
    chartPanel.setPreferredSize(new Dimension(400, 300));
    chartPanel.setBackground(Color.WHITE); // Set background color
    this.setLayout(new BorderLayout());
    this.add(chartPanel, BorderLayout.CENTER);
}


    private void customizeChart(JFreeChart chart) {
        // Set chart background color
        chart.setBackgroundPaint(Color.white);

        // Customize plot
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setOutlinePaint(null);
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.lightGray);

        // Customize the range axis
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setTickLabelFont(new Font("Tahoma", Font.BOLD, 12));
        rangeAxis.setLabelFont(new Font("Tahoma", Font.BOLD, 14));

        // Customize the domain axis
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setTickLabelFont(new Font("Tahoma", Font.BOLD, 12));
        domainAxis.setLabelFont(new Font("Tahoma", Font.BOLD, 14));

        // Customize the renderer
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setDrawBarOutline(false);
        renderer.setShadowVisible(false); // Disable shadows
        renderer.setSeriesPaint(0, new Color(129, 129, 189)); // Set a single color for all bars
        renderer.setMaximumBarWidth(0.10);

        // Add padding
        plot.setInsets(new RectangleInsets(10.0, 5.0, 5.0, 50.0));

        // Customize the legend
        chart.getLegend().setItemFont(new Font("Tahoma", Font.BOLD, 15));
    }

private CategoryDataset createDatasetUser() throws SQLException {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    // Data for each month
    String[] months = {
            "1", "2", "3", "4", "5", "6",
            "7", "8", "9", "10", "11", "12"
    };
    String userid = user.getUserID();
    ResultSet rs = con.createStatement().executeQuery(
        "WITH months AS (SELECT LEVEL AS month FROM DUAL CONNECT BY LEVEL <= 12) SELECT  m.month, NVL(p.sum_grandtotal, 0) AS sum_grandtotal  FROM months m LEFT JOIN (SELECT  EXTRACT(MONTH FROM datecreate) AS month,SUM(grandtotal) AS sum_grandtotal FROM payment WHERE EXTRACT(YEAR FROM datecreate) = 2024 and userid = '" + userid + "' GROUP BY  EXTRACT(MONTH FROM datecreate) ) p ON m.month = p.month ORDER BY m.month"
    );
    while (rs.next()) {
        Double grantotal = rs.getDouble("sum_grandtotal"); 
        String month = rs.getString("month"); 
        System.out.println(grantotal);
        dataset.addValue(grantotal, "Số tiền bỏ ra", month);
    }
    
    ResultSet rs2 = con.createStatement().executeQuery("WITH months AS (SELECT LEVEL AS month FROM DUAL CONNECT BY LEVEL <= 12) SELECT  m.month, NVL(p.sum_grandtotal, 0) AS sum_grandtotal  FROM months m LEFT JOIN (SELECT  EXTRACT(MONTH FROM useddate) AS month,SUM(value) AS sum_grandtotal FROM codegame WHERE EXTRACT(YEAR FROM useddate) = 2024 AND status = 'used' and userid = '" + userid + "' GROUP BY  EXTRACT(MONTH FROM useddate) ) p ON m.month = p.month ORDER BY m.month");
    while (rs2.next()) {
        Double grantotal = rs2.getDouble("sum_grandtotal"); 
        String month = rs2.getString("month"); 
        System.out.println(grantotal);
        dataset.addValue(grantotal, "Số tiền nạp vào", month);
    }
    return dataset;
}
private CategoryDataset createDatasetDev() throws SQLException {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    // Data for each month
    String[] months = {
            "1", "2", "3", "4", "5", "6",
            "7", "8", "9", "10", "11", "12"
    };
    String userid = user.getUserID();
    ResultSet rs = con.createStatement().executeQuery(
        "WITH months AS ( SELECT LEVEL AS month FROM DUAL CONNECT BY LEVEL <= 12 ) SELECT m.month, NVL(p.sum_grandtotal, 0)*0.8 AS sum_grandtotal FROM months m LEFT JOIN ( SELECT EXTRACT(MONTH FROM datecreate) AS month, SUM(grandtotal) AS sum_grandtotal FROM paymentdetail JOIN payment ON paymentdetail.paymentid = payment.paymentid JOIN games ON paymentdetail.gameid = games.gameid WHERE EXTRACT(YEAR FROM datecreate) = 2024 and games.userid = '" + userid + "' GROUP BY EXTRACT(MONTH FROM datecreate) ) p ON m.month = p.month ORDER BY m.month"
    );
    while (rs.next()) {
        Double grantotal = rs.getDouble("sum_grandtotal"); 
        String month = rs.getString("month"); 
        System.out.println(grantotal);
        dataset.addValue(grantotal, "Lợi nhuận", month);
    }
    return dataset;
}
private CategoryDataset createDatasetAdmin() throws SQLException {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    // Data for each month
    String[] months = {
            "1", "2", "3", "4", "5", "6",
            "7", "8", "9", "10", "11", "12"
    };
    String userid = user.getUserID();
    ResultSet rs = con.createStatement().executeQuery(
        "WITH months AS ( SELECT LEVEL AS month FROM DUAL CONNECT BY LEVEL <= 12 ) SELECT m.month, NVL(p.sum_grandtotal, 0) AS sum_grandtotal FROM months m LEFT JOIN ( SELECT EXTRACT(MONTH FROM datecreate) AS month, SUM(grandtotal)*0.2 AS sum_grandtotal FROM paymentdetail JOIN payment ON paymentdetail.paymentid = payment.paymentid WHERE EXTRACT(YEAR FROM datecreate) = 2024 GROUP BY EXTRACT(MONTH FROM datecreate) ) p ON m.month = p.month ORDER BY m.month"
    );
    while (rs.next()) {
        Double grantotal = rs.getDouble("sum_grandtotal"); 
        String month = rs.getString("month"); 
        System.out.println(grantotal);
        dataset.addValue(grantotal, "Lợi nhuận", month);
    }
    return dataset;
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jButton1.setText("Years");
        add(jButton1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
