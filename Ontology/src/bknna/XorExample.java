package bknna;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import org.joone.engine.*;
import org.joone.engine.learning.*;
import org.joone.io.*;

/**
 * Example: The XOR Problem with JOONE
 *
 * @author Jeff Heaton
 * @version 1.0
 */
public class XorExample extends JFrame implements
		ActionListener, NeuralNetListener {

	FullSynapse t1, t2;

	/**
	 * The train button.
	 */
	JButton btnTrain;

	/**
	 * The run button.
	 */
	JButton btnRun;

	/**
	 * The quit button.
	 */
	JButton btnQuit;

	/**
	 * The name of the input file.
	 */
	JTextField inputFile;

	/**
	 * The name of the result file.
	 */
	JTextField resultFile;

	/**
	 * The status line.
	 */
	JLabel status;

	/**
   * Constructor. Set up the components.
   */
  public XorExample()
  {
    setTitle("XOR Solution");

    Container content = getContentPane();

    GridBagLayout gridbag = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
    content.setLayout(gridbag);

    c.fill = GridBagConstraints.NONE;
    c.weightx = 1.0;

    // Training input label
    c.gridwidth = GridBagConstraints.REMAINDER; //end row
    c.anchor = GridBagConstraints.NORTHWEST;
    content.add(
               new JLabel("Enter the name of the training inputfile:"),c);

    // Training input filename
    c.gridwidth = GridBagConstraints.REMAINDER; //end row
    c.anchor = GridBagConstraints.NORTHWEST;
    content.add(
               inputFile = new JTextField(40),c);
    inputFile.setText("./train.txt");

    // Training input label
    c.gridwidth = GridBagConstraints.REMAINDER; //end row
    c.anchor = GridBagConstraints.NORTHWEST;
    content.add(
               new JLabel("Enter the name of the result file:")
                           ,c);

    // Training input filename
    c.gridwidth = GridBagConstraints.REMAINDER; //end row
    c.anchor = GridBagConstraints.NORTHWEST;
    content.add(
               resultFile = new JTextField(40),c);
    resultFile.setText("./result.txt");

    // the button panel
    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(btnTrain = new JButton("Train"));
    buttonPanel.add(btnRun = new JButton("Run"));
    buttonPanel.add(btnQuit = new JButton("Quit"));
    btnTrain.addActionListener(this);
    btnRun.addActionListener(this);
    btnQuit.addActionListener(this);

    // Add the button panel
    c.gridwidth = GridBagConstraints.REMAINDER; //end row
    c.anchor = GridBagConstraints.CENTER;
    content.add(buttonPanel,c);

    // Training input label
    c.gridwidth = GridBagConstraints.REMAINDER; //end row
    c.anchor = GridBagConstraints.NORTHWEST;
    content.add(
               status = new JLabel("Click train to begintraining..."),c);




    // adjust size and position
    pack();
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension d = toolkit.getScreenSize();
    setLocation(
               (int)(d.width-this.getSize().getWidth())/2,
               (int)(d.height-this.getSize().getHeight())/2 );
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    setResizable(false);
  }

	/**
	 * The main function, just display the JFrame.
	 * 
	 * @param args
	 *            No arguments are used.
	 */
	public static void main(String args[]) {
		(new XorExample()).show(true);
	}

	/**
	 * Called when the user clicks one of the three buttons.
	 * 
	 * @param e
	 *            The event.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnQuit)
			System.exit(0);
		else if (e.getSource() == btnTrain)
			train();
		else if (e.getSource() == btnRun)
			run();
	}

	/**
	 * Called when the user clicks the run button.
	 */
	protected void run() {
		long start = System.nanoTime();
		output.removeOutputSynapse(trainer);

		inputStream.resetInput();
		
		samples.resetInput();
		inputStream.setFileName("C:\\Users\\JH\\Desktop\\testdata.txt");
		
		FileOutputSynapse results = new FileOutputSynapse();
		results.setFileName(resultFile.getText());
//		results.setFileName("C:\\Users\\JH\\Desktop\\testdata.txt");
		results.setMonitor(monitor);
		output.addOutputSynapse(results);

		input.start();
		hidden.start();
		output.start();
//		trainer.start();

		// number of rows (patterns) contained in the input file
//		monitor.setPatterns(4);
		monitor.setTrainingPatterns(4);
		// How many times the net must be trained on the input
		// patterns
		monitor.setTotCicles(1);
		// The net must be trained
		monitor.setLearning(false);
		// The net starts the training job
		monitor.Go();
		status.setText("Results written to " + resultFile.getText());
		long end = System.nanoTime();
		System.out.println((end-start));
	}

	/**
	 * The input layer of neurons.
	 */
	SigmoidLayer input;

	/**
	 * The hidden layer of neurons.
	 */
	SigmoidLayer hidden;

	/**
	 * The output layer of neurons.
	 */
	SigmoidLayer output;

	/**
	 * The monitor. Used to pass parameters to all of the JOONE objects.
	 */
	Monitor monitor;

	/**
	 * The file input stream.
	 */
	FileInputSynapse inputStream;

	/**
	 * Used to train the neural network.
	 */
	TeachingSynapse trainer;

	/**
	 * The training data.
	 */
	FileInputSynapse samples;

	/**
	 * Called when the user clicks the train button.
	 */
	protected void train() {
		// First, creates the three Layers
		input = new SigmoidLayer();
		hidden = new SigmoidLayer();
		output = new SigmoidLayer();
		input.setLayerName("input");
		hidden.setLayerName("hidden");
		output.setLayerName("output");

		// sets their dimensions
		input.setRows(2);
		hidden.setRows(3);
		output.setRows(1);

		// Now create the two Synapses
		// input -> hidden conn.
		FullSynapse synapse_IH = new FullSynapse();
		// hidden -> output conn.
		FullSynapse synapse_HO = new FullSynapse();

		synapse_IH.setName("IH");
		synapse_HO.setName("HO");
		t1 = synapse_IH;
		t2 = synapse_HO;

		// Connect the input layer with the hidden layer
		input.addOutputSynapse(synapse_IH);
		hidden.addInputSynapse(synapse_IH);

		// Connect the hidden layer with the output layer
		hidden.addOutputSynapse(synapse_HO);
		output.addInputSynapse(synapse_HO);

		// Create the Monitor object and set the learning parameters
		monitor = new Monitor();
		monitor.setLearningRate(0.8);
		monitor.setMomentum(0.3);

		// Pass the Monitor to all components
		input.setMonitor(monitor);
		hidden.setMonitor(monitor);
		output.setMonitor(monitor);

		// The application registers itself as monitor's listener
		// so it can receive the notifications of termination from
		// the net.

		monitor.addNeuralNetListener(this);

		inputStream = new FileInputSynapse();

		// The first two columns contain the input values
		inputStream.setFirstCol(1);
		inputStream.setLastCol(2);

		// This is the file that contains the input data
		inputStream.setFileName(inputFile.getText());
		input.addInputSynapse(inputStream);

		trainer = new TeachingSynapse();
		trainer.setMonitor(monitor);

		// Setting of the file containing the desired responses,
		// provided by a FileInputSynapse
		samples = new FileInputSynapse();
		samples.setFileName(inputFile.getText());

		// The output values are on the third column of the file
		samples.setFirstCol(3);
		samples.setLastCol(3);
		trainer.setDesired(samples);

		// Connects the Teacher to the last layer of the net
		output.addOutputSynapse(trainer);

		// All the layers must be activated invoking their method
		// start; the layers are implemented as Runnable objects, then
		// they are allocated on separated threads. The threads will
		// stop after training and will need to be restarted later.
		input.start();
		hidden.start();
		output.start();
//		trainer.start();

		// number of rows (patterns) contained in the input file
//		monitor.setPatterns(4);
		monitor.setTrainingPatterns(4);
		// How many times the net must be trained on the input
		// patterns
		monitor.setTotCicles(20000);
		// The net must be trained
		monitor.setLearning(true);
		// The net starts the training job
		monitor.Go();
	}

	/**
	 * JOONE Callback: called when the neural network stops. Not used.
	 * 
	 * @param e
	 *            The JOONE event
	 */
	public void netStopped(NeuralNetEvent e) {
	}

	/**
	 * JOONE Callback: called to update the progress of the neural network. Used
	 * to update the status line.
	 * 
	 * @param e
	 *            The JOONE event
	 */
	public void cicleTerminated(NeuralNetEvent e) {
		Monitor mon = (Monitor) e.getSource();
		long c = mon.getCurrentCicle();
		long cl = c / 1000;
		// print the results every 1000 cycles
		if ((cl * 1000) == c)
			status.setText(c + " cycles remaining - Error = "
					+ mon.getGlobalError());
	}

	/**
	 * JOONE Callback: Called when the network is starting up. Not used.
	 * 
	 * @param e
	 *            The JOONE event.
	 */
	public void netStarted(NeuralNetEvent e) {
	}

	@Override
	public void errorChanged(NeuralNetEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void netStoppedError(NeuralNetEvent arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

}