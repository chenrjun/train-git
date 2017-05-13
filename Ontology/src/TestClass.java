import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.DC;
import org.apache.jena.vocabulary.VCARD;
import org.joone.engine.FullSynapse;
import org.joone.engine.LinearLayer;
import org.joone.engine.Monitor;
import org.joone.engine.SigmoidLayer;
import org.joone.net.NeuralNet;
import org.junit.Test;


/**
 * 
 * @author JH
 * FullSynapse is the network weighted connections
 * SigmoidLayer is the network node woth sigma function
 */
public class TestClass {

	@Test
	public void testss() {
		String xor = "0.0;0.0;0.0"
				+ "0.0;1.0;1.0"
				+ "1.0;0.0;1.0"
				+ "1.0;1.0;0.0";
	

		
		SigmoidLayer layer1 = new SigmoidLayer();
		SigmoidLayer layer2 = new SigmoidLayer();
		SigmoidLayer layer3 = new SigmoidLayer();
		FullSynapse synapse1 = new FullSynapse();
		FullSynapse synapse2 = new FullSynapse();
		
		LinearLayer input = new LinearLayer();
		SigmoidLayer hidden = new SigmoidLayer();
		SigmoidLayer output = new SigmoidLayer();
		
		input.setRows(2);
		hidden.setRows(3);
		output.setRows(1);
		
		FullSynapse synapse_IH = new FullSynapse(); /* Input  -> Hidden conn. */
		FullSynapse synapse_HO = new FullSynapse(); /* Hidden -> Output conn. */
		
		
		input.addOutputSynapse(synapse_IH);
		hidden.addInputSynapse(synapse_IH);
		
		hidden.addOutputSynapse(synapse_HO);
		output.addInputSynapse(synapse_HO);
		
		NeuralNet nnet = new NeuralNet();
		nnet.addLayer(input, NeuralNet.INPUT_LAYER);
		nnet.addLayer(hidden, NeuralNet.HIDDEN_LAYER);
		nnet.addLayer(output, NeuralNet.OUTPUT_LAYER);
		
		Monitor monitor = nnet.getMonitor();
		monitor.setLearningRate(0.7);
		monitor.setMomentum(0.5);
		
		
		
	}
	
	

}
