import java.sql.Connection; 
import java.util.Vector;

import org.encog.neural.data.NeuralData;
import org.encog.neural.data.NeuralDataPair;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.learning.SupervisedTrainingElement;
import org.neuroph.core.learning.TrainingElement;
import org.neuroph.core.learning.TrainingSet;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.DynamicBackPropagation;
import org.neuroph.nnet.learning.MomentumBackpropagation;
import org.neuroph.util.NeuronProperties;
import org.neuroph.util.TransferFunctionType;


public class NeurophXOR {
    /**
     * Runs this sample
     */
    public static void main(String[] args) {
        
        // create training set (logical XOR function)
    	
    	//setting data input column and output column
        TrainingSet trainingSet = new TrainingSet(2, 1);
        trainingSet.addElement(new SupervisedTrainingElement(new double[]{0, 0}, new double[]{0}));
        trainingSet.addElement(new SupervisedTrainingElement(new double[]{0, 1}, new double[]{1}));
        trainingSet.addElement(new SupervisedTrainingElement(new double[]{1, 0}, new double[]{1}));
        trainingSet.addElement(new SupervisedTrainingElement(new double[]{1, 1}, new double[]{0}));
        
        
        
        
        // create multi layer perceptron
//        MultiLayerPerceptron network = new MultiLayerPerceptron(TransferFunctionType.TANH, 2, 2, 1);
        NeuronProperties neuronProperties = new NeuronProperties(TransferFunctionType.TANH, false);
        Vector<Integer> layer = new Vector<>();
        layer.add(2);
        layer.add(1);
        layer.add(1);
        MultiLayerPerceptron network = new MultiLayerPerceptron(layer,neuronProperties);
        DynamicBackPropagation train = new DynamicBackPropagation();
        
        train.setLearningRate(0.5);
        train.setNeuralNetwork(network);
        network.setLearningRule(train);
        
        //開始訓練
        int epoch = 1;
        do
        {
        	train.doOneLearningIteration(trainingSet);
        	System.out.println("Epoch " + epoch + ", error=" + train.getTotalNetworkError());
        	epoch++;
        	
        } while(train.getTotalNetworkError()>0.01);
        
        System.out.println("Neural Network Results:");
        
        
        //測試資料
        for(TrainingElement element : trainingSet.trainingElements()) {
        	
        	long start = System.nanoTime();
        	network.setInput(element.getInput());
            network.calculate();
            Vector<Double> output = network.getOutput();
            SupervisedTrainingElement ste = (SupervisedTrainingElement)element;
            
			System.out.println(element.getInput().get(0) + "," + element.getInput().get(1)
					+ ", actual=" + output.get(0) + ",ideal=" + ste.getDesiredOutput().get(0));
			
			long end = System.nanoTime();
			System.out.println((end-start+"-------"));
		}
        
//        System.out.println(network.getLayers().get(0).getNeurons().get(0).getOutConnections().get(0).getWeightedInput());
//        System.out.println(network.getLayers().get(0).getNeurons().get(0).getOutConnections().get(0).getWeight());
//        System.out.println(network.getLayers().get(0).getNeurons().get(0).getOutConnections().get(1).getWeight());
//        
//        System.out.println(network.getLayers().get(0).getNeurons().get(1).getOutConnections().get(0).getWeight());
//        System.out.println(network.getLayers().get(0).getNeurons().get(1).getOutConnections().get(1).getWeight());
        
       


    }


}
