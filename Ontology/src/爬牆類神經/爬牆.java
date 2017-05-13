package 爬牆類神經;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import org.apache.commons.io.FileUtils;
import org.neuroph.core.learning.SupervisedTrainingElement;
import org.neuroph.core.learning.TrainingSet;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.DynamicBackPropagation;
import org.neuroph.util.NeuronProperties;
import org.neuroph.util.TransferFunctionType;


public class 爬牆 {
	
	
	private TrainingSet trainingSet;
	
	private MultiLayerPerceptron network;
	
	private DynamicBackPropagation train;
	
	public 爬牆(int inputCount,int outputCount){
		trainingSet = new TrainingSet(inputCount,outputCount);
		
		System.out.println("this is test file");
	}
	
	
	public void addData(double[] input,double[] output)
	{
		this.trainingSet.addElement(new SupervisedTrainingElement(input, output));
	}
	
	
	public void setLayer(int input,int hidden,int output){
		NeuronProperties neuronProperties = new NeuronProperties(TransferFunctionType.TANH, false);
		Vector layer = new Vector();
		layer.add(input);
		layer.add(hidden);
		layer.add(output);
		
		 this.network = new MultiLayerPerceptron(layer,neuronProperties);
		 this.train = new DynamicBackPropagation();
		 
		 train.setNeuralNetwork(network);
		 network.setLearningRule(train);
	}
	
	//訓練方法
	public void doTrain()
	{
		int epoch=1;
		do{
			this.train.doOneLearningIteration(this.trainingSet);
			System.out.println("目前循環次數:"+epoch);
			epoch++;
		}while(this.train.getTotalNetworkError() >0.01);
	}
	
	public void doTestAndOutput(double[] inputArr) throws IOException{
		
		String col="花費時間,測試輸出";
		
		long start = System.nanoTime();
		this.network.setInput(inputArr);
		network.calculate();
		Vector<Double> output = network.getOutput();
		long end = System.nanoTime();
		
		//花費時間(奈秒)
		long spandTime = end-start;
		//測試資料輸出
		double testOutput = output.get(0);
		String data = spandTime + "," + testOutput;
		FileUtils.writeStringToFile(new File("C:\\Users\\JH\\Desktop\\爬牆.txt"), data);
		
		
	}
	
	
	
	
	
}
