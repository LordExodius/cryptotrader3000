package cryptotrader.view;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import java.util.ArrayList;

import cryptotrader.gui.MainUI;

/**
 * Observer class which represents the trade activity table on the UI. Acts as one of the views 
 * in the MVC, which updates whenever any TradeResults are added to the TradeLog.
 * 
 * @author Ben Asokanthan
 * @version 1.0
 */
public class TradeActivityTable implements Observer {

	@Override
    public void update(Subject tradeLog) {
        // createTableOutput(tradeLog.getResults());
		createTableOutput(tradeLog);
    }

	/**
     * Helper method which creates a table of the past trades with the TradeLog as 
	 * the model for the view. Uses the TradeLog's list of TradeResult's to 
	 * compute the number of actions for each TradingBroker, and renders the bar 
	 * graph with these values.
	 * 
     * @param tradeLog the log of trades (or TradeResults) which is the subject of the observer.
     */
    public void createTableOutput(Subject tradeLog) {

		ArrayList<TradeResult> entries = ((TradeLog)(tradeLog)).getResults();

        Object[] columnNames = {"Trader","Strategy","CryptoCoin","Action","Quantity","Price","Date"};

        Object[][] data = new Object[entries.size()][7];

        for (int i = 0; i < entries.size(); i++) {
            data[i] = entries.get(i).getResultObj();
        }

        JTable table = new JTable(data, columnNames);
		//table.setPreferredSize(new Dimension(600, 300));
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Trader Actions",
                TitledBorder.CENTER,
                TitledBorder.TOP));
		
	
		
		scrollPane.setPreferredSize(new Dimension(800, 300));
		table.setFillsViewportHeight(true);;
		
		MainUI.getInstance().updateStats(scrollPane);

    }

	/**
     * Helper method which creates a activity table component with hard-coded values.
     */
    public static void createTableOutput() {

        // Dummy dates for demo purposes. These should come from selection menu
		Object[] columnNames = {"Trader","Strategy","CryptoCoin","Action","Quantity","Price","Date"};
		
		// Dummy data for demo purposes. These should come from actual fetcher
		Object[][] data = {
				{"Trader-1", "Strategy-A", "ETH", "Buy", "500", "150.3","13-January-2022"},
				{"Trader-2", "Strategy-B", "BTC", "Sell", "200", "50.2","13-January-2022"},
				{"Trader-3", "Strategy-C", "USDT", "Buy", "1000", "2.59","15-January-2022"},
				{"Trader-1", "Strategy-A", "USDC", "Buy", "500", "150.3","16-January-2022"},
				{"Trader-2", "Strategy-B", "ADA", "Sell", "200", "50.2","16-January-2022"},
				{"Trader-3", "Strategy-C", "SOL", "Buy", "1000", "2.59","17-January-2022"},
				{"Trader-1", "Strategy-A", "ONE", "Buy", "500", "150.3","17-January-2022"},
				{"Trader-2", "Strategy-B", "MANA", "Sell", "200", "50.2","17-January-2022"},
				{"Trader-3", "Strategy-C", "AVAX", "Buy", "1000", "2.59","19-January-2022"},
				{"Trader-1", "Strategy-A", "LUNA", "Buy", "500", "150.3","19-January-2022"},
				{"Trader-2", "Strategy-B", "FTM", "Sell", "200", "50.2","19-January-2022"},
				{"Trader-3", "Strategy-C", "HNT", "Buy", "1000", "2.59","20-January-2022"}
		};
		

		JTable table = new JTable(data, columnNames);
		//table.setPreferredSize(new Dimension(600, 300));
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Trader Actions",
                TitledBorder.CENTER,
                TitledBorder.TOP));
		
	
		
		scrollPane.setPreferredSize(new Dimension(800, 300));
		table.setFillsViewportHeight(true);;
		
		MainUI.getInstance().updateStats(scrollPane);
        
    }

	public static void main(String[] args) {
		JFrame frame = MainUI.getInstance();
		frame.setSize(900, 600);
		frame.pack();
		frame.setVisible(true);

		TradeActivityTable table = new TradeActivityTable();
		table.createTableOutput();
	}
    
}
