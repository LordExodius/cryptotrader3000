package cryptotrader.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import cryptotrader.trade.CoinAPIException;
import cryptotrader.trade.StrategyCreator;
import cryptotrader.trade.TraderList;
import cryptotrader.trade.TradingBroker;
import cryptotrader.trade.TradingStrategy;
import cryptotrader.user.User;
import cryptotrader.view.TradeActivityGraph;
import cryptotrader.view.TradeActivityTable;
import cryptotrader.view.TradeLog;

/**
 * A class representing the main interface of the application.
 * Implements the singleton pattern, as only one instance of the main UI
 * should exist at any given moment.
 * 
 * @author David Tran
 * @version 1.0
 */
public class MainUI extends JFrame implements ActionListener {

	// singleton instance of the UI
	private static MainUI instance;

	// panel containing the trade activity table and graph
	private JPanel stats;

	// views for the activity table and graph 
	private TradeActivityTable tradeTable;
	private TradeActivityGraph tradeGraph;

	// the table and model for the table that manages traders
	private DefaultTableModel dtm;
	private JTable table;

	/**
	 * Gets the singleton instance of this class.
	 * 
	 * @return the singleton instance of this class
	 */
	public static MainUI getInstance() {
		if (instance == null)
			instance = new MainUI();

		return instance;
	}

	private MainUI() {

		// Set window title
		super("Cryptotrader 3000");

		// Set top bar
		JPanel north = new JPanel();

		// create Perform Trade button
		JButton trade = new JButton("Perform Trade");
		trade.setActionCommand("refresh");
		trade.addActionListener(this);
		JPanel south = new JPanel();
		south.add(trade);

		initializeViews();

		table = new JTable(dtm);
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Trading Client Actions",
				TitledBorder.CENTER, TitledBorder.TOP));
		Vector<String> strategyNames = new Vector<String>();
		strategyNames.add("None");
		strategyNames.add("Strategy-A");
		strategyNames.add("Strategy-B");
		strategyNames.add("Strategy-C");
		strategyNames.add("Strategy-D");
		TableColumn strategyColumn = table.getColumnModel().getColumn(2);
		JComboBox comboBox = new JComboBox(strategyNames);
		strategyColumn.setCellEditor(new DefaultCellEditor(comboBox));
		JButton addRow = new JButton("Add Row");
		JButton remRow = new JButton("Remove Row");
		addRow.setActionCommand("addTableRow");
		addRow.addActionListener(this);
		remRow.setActionCommand("remTableRow");
		remRow.addActionListener(this);

		scrollPane.setPreferredSize(new Dimension(800, 300));
		

		JPanel east = new JPanel();
		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
		east.add(scrollPane);
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		buttons.add(addRow);
		buttons.add(remRow);
		east.add(buttons);

		// Set charts region
		JPanel west = new JPanel();
		west.setPreferredSize(new Dimension(800, 650));
		stats = new JPanel();
		stats.setLayout(new GridLayout(2, 2));

		west.add(stats);

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(west, BorderLayout.CENTER);
		getContentPane().add(south, BorderLayout.SOUTH);
	}

	/**
	 * Initialize the views (trader table, graph, and log) with data loaded
	 * in the TraderList and TradeLog.
	 */
	public void initializeViews() {
		dtm = new DefaultTableModel(new Object[] { "Trading Client", "Coin List", "Strategy Name" }, 0);

		tradeTable = new TradeActivityTable();
		tradeGraph = new TradeActivityGraph();

		User user = User.getInstance();
		TradeLog tradeLog = user.getTradeLog();
		tradeLog.attach(tradeTable);
		tradeLog.attach(tradeGraph);

		TraderList traderList = user.getTraderList();
		for (TradingBroker t : traderList.getList()) {
			Object[] row = {
					t.getName(),
					String.join(",", t.getCoinList()),
					t.getStrategy().getName(),
			};
			dtm.addRow(row);
		}
	}

	/**
	 * Update the JPanel containing the stats for the activity graph and trade log.
	 * @param component
	 */
	public void updateStats(JComponent component) {
		stats.add(component);
		stats.revalidate();
	}

	/**
	 * Clear the stats for the activity table and graph.
	 * Call this before updating the stats.
	 */
	public void removeAllStats() {
		stats.removeAll();
	}

	/**
	 * Override event handlers for "Perform Trades", "Add Row", and "Remove Row"
	 * Uses the Command pattern to select the appropriate action to perform
	 * 
	 * @param e the ActionEvent to be performed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		// "Perform Trades" command
		if ("refresh".equals(command)) {
			// set all traders to inactive: only traders currently in the list will be set to active
			User user = User.getInstance();
			TraderList traderList = user.getTraderList();
			for (TradingBroker t : traderList.getList()) {
				t.setActive(false);
			}
			// set all traders in the trader table to active and add new traders to the list
			for (int count = 0; count < dtm.getRowCount(); count++){
					Object traderObject = dtm.getValueAt(count, 0);
					if (traderObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in Trader name on line " + (count + 1) );
						return;
					}
					String traderName = traderObject.toString();
					Object coinObject = dtm.getValueAt(count, 1);
					if (coinObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in cryptocoin list on line " + (count + 1) );
						return;
					}
					String[] coinNames = coinObject.toString().replaceAll(" ", "").split(",");
					Object strategyObject = dtm.getValueAt(count, 2);
					if (strategyObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in strategy name on line " + (count + 1) );
						return;
					}
					String strategyName = strategyObject.toString();
					TradingStrategy strategy = new StrategyCreator().create(strategyName);
					TradingBroker trader = traderList.getTrader(traderName);
					if (trader == null) {
						// trader doesn't exist: create it
						trader = new TradingBroker(traderName);
					}
					if (trader.getActive() == true)
					{
						new PopupUI("A trader with the name \"" + trader.getName() + "\" already exists.");
						continue;
					}
					trader.setActive(true);
					trader.updateCoins(new ArrayList<String>(Arrays.asList(coinNames)));
					trader.updateStrategy(strategy);
					traderList.addTrader(trader);
	        }
			// perform trades on all active traders
			try {
				user.performTrades();
			} catch (CoinAPIException err) {
				new PopupUI(err.getMessage());
			}
		} else if ("addTableRow".equals(command)) {
			// "Add Row" command
			dtm.addRow(new String[3]);
		} else if ("remTableRow".equals(command)) {
			// "Remove Row" command
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1)
				dtm.removeRow(selectedRow);
		}
	}
}
