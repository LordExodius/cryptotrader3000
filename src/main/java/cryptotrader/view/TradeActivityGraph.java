package cryptotrader.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import java.util.ArrayList;
import java.util.HashMap;

import cryptotrader.gui.MainUI;
import cryptotrader.trade.TradingBroker;

public class TradeActivityGraph extends Observer {

    public void update(TradeLog tradeLog) {
        // TODO Auto-generated method stub
        createBarOutput(tradeLog.getResults());
	}

    public void createBarOutput(ArrayList<TradeResult> entries) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        HashMap<TradingBroker,Integer> brokerActionsMap = new HashMap<TradingBroker,Integer>();

        for (TradeResult entry : entries) {
            if (brokerActionsMap.containsKey(entry.getBroker())) {
                int actions = brokerActionsMap.get(entry.getBroker());
                brokerActionsMap.replace(entry.getBroker(), actions + 1);
            } else {
                brokerActionsMap.put(entry.getBroker(), 1);
            }
        }

        brokerActionsMap.forEach((broker,actions) -> {
            dataset.setValue(actions, broker.getName(), broker.getStrategy().getName());
        });

		CategoryPlot plot = new CategoryPlot();
		BarRenderer barrenderer1 = new BarRenderer();

		plot.setDataset(0, dataset);
		plot.setRenderer(0, barrenderer1);
		CategoryAxis domainAxis = new CategoryAxis("Strategy");
		plot.setDomainAxis(domainAxis);
		LogAxis rangeAxis = new LogAxis("Actions(Buys or Sells)");
		rangeAxis.setRange(new Range(0.1, 20.0));
		plot.setRangeAxis(rangeAxis);

		//plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		//plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

		JFreeChart barChart = new JFreeChart("Actions Performed By Traders So Far", new Font("Serif", java.awt.Font.BOLD, 18), plot,
				true);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(600, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);
		MainUI.getInstance().updateStats(chartPanel);

    }

    private void createBar() {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//		Those are hard-coded values!!!! 
//		You will have to come up with a proper datastructure to populate the BarChart with live data!
		dataset.setValue(6, "Trader-1", "Strategy-A");
		dataset.setValue(5, "Trader-2", "Strategy-B");
		dataset.setValue(0, "Trader-3", "Strategy-E");
		dataset.setValue(1, "Trader-4", "Strategy-C");
		dataset.setValue(10, "Trader-5", "Strategy-D");

		CategoryPlot plot = new CategoryPlot();
		BarRenderer barrenderer1 = new BarRenderer();

		plot.setDataset(0, dataset);
		plot.setRenderer(0, barrenderer1);
		CategoryAxis domainAxis = new CategoryAxis("Strategy");
		plot.setDomainAxis(domainAxis);
		LogAxis rangeAxis = new LogAxis("Actions(Buys or Sells)");
		rangeAxis.setRange(new Range(0.1, 20.0));
		plot.setRangeAxis(rangeAxis);

		//plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		//plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

		JFreeChart barChart = new JFreeChart("Actions Performed By Traders So Far", new Font("Serif", java.awt.Font.BOLD, 18), plot,
				true);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(600, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);
		MainUI.getInstance().updateStats(chartPanel);
	}
    
}
