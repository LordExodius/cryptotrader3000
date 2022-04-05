package cryptotrader.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;

import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;

import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.ArrayList;
import java.util.HashMap;

import cryptotrader.gui.MainUI;
import cryptotrader.trade.TradingBroker;

/**
 * Observer class which represents the bar graph on the UI. Acts as one of the views 
 * in the MVC, which updates whenever any TradeResults are added to the TradeLog.
 * 
 * @author Ben Asokanthan
 * @version 1.0
 */
public class TradeActivityGraph implements Observer {
    

    @Override
    public void update(Subject tradeLog) {
        createBarOutput(tradeLog);
        // createBar();
	}

    /**
     * Helper method which creates a bar output with the TradeLog as the model for the view.
     * Uses the TradeLog's list of TradeResult's to compute the number of actions
     * for each TradingBroker, and renders the bar graph with these values.
     * 
     * @param tradeLog the log of trades (or TradeResults) which is the subject of the observer.
     */
    private void createBarOutput(Subject tradeLog) {

        ArrayList<TradeResult> entries = ((TradeLog)(tradeLog)).getResults();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // A HashMap whose keys are the TradingBrokers, which have a corresponding number of actions Integer value.
        HashMap<TradingBroker, HashMap<String, Integer>> brokerActionsMap = new HashMap<TradingBroker,HashMap<String, Integer>>();

        // Counts the number of actions performed by each TradingBroker.
        for (TradeResult entry : entries) {
			if (entry == null)
				continue;
            if (brokerActionsMap.containsKey(entry.getBroker())) {
                HashMap<String, Integer> actions = brokerActionsMap.get(entry.getBroker());
                String strategyName = entry.getStrategy().getName();
				actions.put(strategyName, actions.get(strategyName) + 1);
            } else {
				HashMap<String, Integer> strategyMap = new HashMap<String, Integer>();
				strategyMap.put("Strategy-A", 0);
				strategyMap.put("Strategy-B", 0);
				strategyMap.put("Strategy-C", 0);
				strategyMap.put("Strategy-D", 0);
				String strategyName = entry.getStrategy().getName();
				strategyMap.put(strategyName, strategyMap.get(strategyName) + 1);
                brokerActionsMap.put(entry.getBroker(), strategyMap);
            }
        }

        // Uses the HashMap to add to the dataset.
        brokerActionsMap.forEach((key,value) -> {
			for(String strategyKey : value.keySet())
			{
				dataset.setValue(value.get(strategyKey), key.getName() == null ? "" : key.getName(), strategyKey);
			}
            
        });

		CategoryPlot plot = new CategoryPlot();
		BarRenderer barrenderer1 = new BarRenderer();

		plot.setDataset(0, dataset);
		plot.setRenderer(0, barrenderer1);
		CategoryAxis domainAxis = new CategoryAxis("Strategy");
		plot.setDomainAxis(domainAxis);
		NumberAxis rangeAxis = new NumberAxis("Actions(Buys or Sells)");
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

    /**
     * Helper method which creates a bar graph component with hard-coded values.
     */
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
		NumberAxis rangeAxis = new NumberAxis("Actions(Buys or Sells)");
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
