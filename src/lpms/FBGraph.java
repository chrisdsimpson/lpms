

package lpms;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.chart.axis.NumberTickUnit;

public class FBGraph extends JPanel 
{
  /** Time series for total memory used. */
  private TimeSeries total;

  /** Time series for free memory. */
  private TimeSeries free;

  /**
   * Creates a new application.
   *
   * @param maxAge  the maximum age (in milliseconds).
   */
  public FBGraph(int maxAge) 
  {
    super(new BorderLayout());

    // create two series that automatically discard data more than 30
    // seconds old...
    //this.total = new TimeSeries("Total Memory");
    //this.total.setMaximumItemAge(maxAge);
    this.free = new TimeSeries("Measured Power in Watts");
    this.free.setMaximumItemAge(maxAge);
    TimeSeriesCollection dataset = new TimeSeriesCollection();
    //dataset.addSeries(this.total);
    dataset.addSeries(this.free);

    DateAxis domain = new DateAxis("Time");
    NumberAxis range = new NumberAxis("Power");
    domain.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
    range.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
    domain.setLabelFont(new Font("SansSerif", Font.PLAIN, 14));
    range.setLabelFont(new Font("SansSerif", Font.PLAIN, 14));

    XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
    renderer.setSeriesPaint(0, Color.red);
    renderer.setSeriesPaint(1, Color.green);
    renderer.setSeriesStroke(0, new BasicStroke(3f, BasicStroke.CAP_BUTT,
                             BasicStroke.JOIN_BEVEL));
    renderer.setSeriesStroke(1, new BasicStroke(3f, BasicStroke.CAP_BUTT,
                             BasicStroke.JOIN_BEVEL));
    XYPlot plot = new XYPlot(dataset, domain, range, renderer);
    domain.setAutoRange(true);
    domain.setLowerMargin(0.0);
    domain.setUpperMargin(0.0);
    domain.setTickLabelsVisible(true);

    range.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

    //range.setRange(0.0, 1.0);
    range.setTickUnit(new NumberTickUnit(0.1));
    
    JFreeChart chart = new JFreeChart("Measured Power",
                                      new Font("SansSerif", Font.BOLD, 24), plot, true);

    ChartUtilities.applyCurrentTheme(chart);

    ChartPanel chartPanel = new ChartPanel(chart, true);
    chartPanel.setBorder(BorderFactory.createCompoundBorder(
                         BorderFactory.createEmptyBorder(4, 4, 4, 4),
                         BorderFactory.createLineBorder(Color.black)));
    add(chartPanel);

  }

  /**
   * Adds an observation to the 'total memory' time series.
   *
   * @param y  the total memory used.
   */
  private void addTotalObservation(double y) 
  {
    this.total.add(new Millisecond(), y);
  }

  /**
   * Adds an observation to the 'free memory' time series.
   *
   * @param y  the free memory.
   */
  private void addFreeObservation(double y) 
  {
    this.free.add(new Millisecond(), y);
  }

  /**
   * The data generator.
   */
  class DataGenerator extends Timer implements ActionListener 
  {

    /**
     * Constructor.
     *
     * @param interval  the interval (in milliseconds)
     */
    DataGenerator(int interval) 
    {
      super(interval, null);
      addActionListener(this);
    }

    /**
     * Adds a new free/total memory reading to the dataset.
     *
     * @param event  the action event.
     */
    public void actionPerformed(ActionEvent event) 
    {
    	String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(Calendar.getInstance().getTime());  	
    	double p = Double.parseDouble(FBSerial.getReceiveBuffer());
    	addFreeObservation(p);
    	pw.println(timeStamp + ", " + p);
    	
    	//long f = Runtime.getRuntime().freeMemory();
      //long t = Runtime.getRuntime().totalMemory();
      //addFreeObservation(f);
      //addTotalObservation(t);
    }

  }

  /**
   * Opens the plot window and starts the plot 
   */
  public static void plot()
  {
  	
  	String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
  	file = new File("C:\\Users\\Public\\Documents\\lpmsdata_" + timeStamp + ".csv");
  	  	
  	try 
  	{
      fw = new FileWriter(file, true);
      pw = new PrintWriter(fw);
    } 
  	catch(IOException e) 
  	{
      e.printStackTrace();
    } 
  		
  	JFrame frame = new JFrame("Laser Power");
    FBGraph panel = new FBGraph(30000);
    frame.getContentPane().add(panel, BorderLayout.CENTER);
    frame.setBounds(200, 120, 1000, 480);
    frame.setVisible(true);
    panel.new DataGenerator(100).start();
    
    frame.addWindowListener(new WindowAdapter() 
    {
      public void windowClosing(WindowEvent e) 
      {
      	/* Stop the meter from sending data */
      	FBSerial.SerialWriter("*RST");
      	
      	if(pw != null) 
      	{
          pw.close();
        }
      	
      	if(fw != null) 
      	{
          try
					{
						fw.close();
					} 
          catch (IOException e1)
					{
						e1.printStackTrace();
					}
        }
      }
    });
  }
    
    
  /**
   * Entry point for the sample application.
   *
   * @param args  ignored.
   */
  public static void main(String[] args) 
  {
    JFrame frame = new JFrame("Laser Power");
    FBGraph panel = new FBGraph(30000);
    frame.getContentPane().add(panel, BorderLayout.CENTER);
    frame.setBounds(200, 120, 1000, 580);
    frame.setVisible(true);
    panel.new DataGenerator(100).start();

    frame.addWindowListener(new WindowAdapter() 
    {
      public void windowClosing(WindowEvent e) 
      {
        System.exit(0);
      }
    });
  }

  static File file = null;
	static FileWriter fw = null;
	static PrintWriter pw = null;
	

}


