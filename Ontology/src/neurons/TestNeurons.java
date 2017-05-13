package neurons;

import java.io.File;

import org.joone.engine.FullSynapse;
import org.joone.engine.LinearLayer;
import org.joone.engine.Monitor;
import org.joone.engine.NeuralNetEvent;
import org.joone.engine.NeuralNetListener;
import org.joone.engine.SigmoidLayer;
import org.joone.engine.learning.TeachingSynapse;
import org.joone.io.FileInputSynapse;
import org.joone.net.NeuralNet;
import org.junit.Test;

public class TestNeurons implements NeuralNetListener {
	
	public static void main(String args[])
	{
		//線性輸入層
		LinearLayer input = new LinearLayer();
		
		SigmoidLayer hidden = new SigmoidLayer();
		SigmoidLayer output = new SigmoidLayer();
		
		input.setRows(2);
		hidden.setRows(3);
		output.setRows(1);
		
		FullSynapse input_to_hidden = new FullSynapse();
		FullSynapse hidden_to_output = new FullSynapse();
		
		input.addOutputSynapse(input_to_hidden);
		hidden.addInputSynapse(input_to_hidden);
		
		hidden.addOutputSynapse(hidden_to_output);
		output.addInputSynapse(hidden_to_output);
		
		
		NeuralNet nnet = new NeuralNet();
		nnet.addLayer(input,NeuralNet.INPUT_LAYER);
		nnet.addLayer(hidden,NeuralNet.HIDDEN_LAYER);
		nnet.addLayer(output,NeuralNet.OUTPUT_LAYER);
		
		Monitor monitor = nnet.getMonitor();
		monitor.setLearningRate(0.7);
		monitor.setMomentum(0.5);
		monitor.addNeuralNetListener(new TestNeurons());
		
		
		FileInputSynapse inputStream = new FileInputSynapse();
		inputStream.setAdvancedColumnSelector("1,2");
		//		//		//		//		//		//		//
		inputStream.setInputFile(new File("C:\\Users\\JH\\Desktop\\xortrain.txt"));
		input.addInputSynapse(inputStream);
		
		TeachingSynapse trainer = new TeachingSynapse();
		FileInputSynapse samples = new FileInputSynapse();
		samples.setInputFile(new File("C:\\Users\\JH\\Desktop\\xortrain.txt"));
		trainer.setDesired(samples);
		samples.setAdvancedColumnSelector("3");
		nnet.setTeacher(trainer);
		output.addOutputSynapse(trainer);
		monitor.setTrainingPatterns(4);
		monitor.setTotCicles(10);
		monitor.setLearning(true);
		nnet.go();
		
		input.setMonitor(monitor);
//		hidden.setMonitor(monitor);
//		output.setMonitor(monitor);
		input.start();
//		hidden.start();
//		output.start();
		
		
	}

	@Override
	public void cicleTerminated(NeuralNetEvent arg0) {
		Monitor mon = (Monitor) arg0.getSource();
		long c = mon.getCurrentCicle();
		long c1 = c/1000;
		if ( (c1 * 1000) == c )
		      System.out.println((c + " cycles remaining - Error = "
		                         + mon.getGlobalError()));
		
		
	}

	@Override
	public void errorChanged(NeuralNetEvent arg0) {
		if(arg0.getNeuralNet()!=null)
			System.out.println(arg0.getNeuralNet());
	}

	@Override
	public void netStarted(NeuralNetEvent arg0) {
		if(arg0.getNeuralNet()!=null)
			System.out.println(arg0.getNeuralNet());
	}

	@Override
	public void netStopped(NeuralNetEvent arg0) {
		if(arg0.getNeuralNet()!=null)
			System.out.println(arg0.getNeuralNet());
	}

	@Override
	public void netStoppedError(NeuralNetEvent arg0, String arg1)
	{
		System.out.println(arg1);
	}
	
	
}
