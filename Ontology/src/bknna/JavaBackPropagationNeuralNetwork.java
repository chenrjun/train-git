package bknna;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import java.util.Map.Entry;


/**
 * JAVA 反向传输神经网络
 * @author kj021320  ， codeby 2008.12.10
 *
 */
public class JavaBackPropagationNeuralNetwork {
    /**
     * 神经元
     */
    public class Neuron {
        HashMap<Integer, Link> target = new HashMap<Integer, Link>();// 连接其他神经元的
        HashMap<Integer, Link> source = new HashMap<Integer, Link>();// 被其他神经元连接的
        double data = 0.0;
        public Link sourceGet(int index) {
            return source.get(index);
        }
        public Link targetGet(int index) {
            return target.get(index);
        }
        public boolean targetContains(Link l) {
            return target.containsValue(l);
        }
        public boolean sourceContains(Link l) {
            return source.containsValue(l);
        }
        public Link sourceLink(int index, Link l) {
            if (l.linker != this) {
                l.setLinker(this);
            }
            return source.put(index, l);
        }
        public Link targetLink(int index, Link l) {
            if (l.owner != this) {
                l.setOwner(this);
            }
            return target.put(index, l);
        }
    }

    /**
     * 神经链
     */
    public class Link {
        Neuron owner;
        public void setOwner(Neuron o) {
            owner = o;
            if (!o.targetContains(this)) {
                o.targetLink(o.target.size(), this);
            }
        }
        public Link() {
            weight = rand(-1, 1);
        }
        public void setLinker(Neuron o) {
            linker = o;
            if (!o.sourceContains(this)) {
                o.sourceLink(o.source.size(), this);
            }
        }
        @Override
        public String toString(){
            return super.toString()+" weight:"+weight;
        }
        Neuron linker;
        double weight;
    }

    Random random = new Random();
    {
        random.setSeed(System.nanoTime());
    }
    Neuron[] inputnode;    //输入层神经元
    Neuron[] hiddennode;    //隐含层神经元
    Neuron[] outputnode;    //输出层神经元
    double learnrate;// 学习速度
    double threshold;// 阀值,误差允许度

    private final int inputCount;
    private final int hiddenCount;
    private final int outputCount;
    /**
     *
     * @param input        输入层的神经元个数
     * @param hidden    隐含层的神经元个数
     * @param output    输出层的神经元的个数
     */
    public JavaBackPropagationNeuralNetwork(int input, int hidden, int output) {
        inputCount = input;
        hiddenCount = hidden;
        outputCount = output;
        build();
    }
    public void reBuildNeuralNetwork(){
        build();
    }
    private void build(){
        inputnode = new Neuron[inputCount+1];
        hiddennode = new Neuron[hiddenCount];
        outputnode = new Neuron[outputCount];
        initNeurons(inputnode);
        initNeurons(hiddennode);
        initNeurons(outputnode);
        makeLink(inputnode, hiddennode);
        makeLink(hiddennode, outputnode);
    }
    /**
     * 思考方法
     * @param inputs    前馈层神经个数相符的浮点数    -1~1之间
     * @return            思考后的结果,个数与后端的神经个数相符，每个浮点为-1~1之间
     */
    public double[] thinking(double[] inputs) {
        /**把数据映射到前馈层的神经元里面*/
        makeNeuron(inputnode, inputs);
        /**通过每个神经链的权重 从隐藏层计算到最终输出层的值*/
        thinking();
        /**把输出层的值映为return的double数组*/
        return makeMatrix();
    }
    public double[][] batchThinking(double[][] inputs){
        double[][] ret = new double[inputs.length][];
        for(int i = 0; i< inputs.length ; i++){
            makeNeuron(inputnode, inputs[i]);
            thinking();
            ret[i]=makeMatrix();
        }
        return ret;
    }
    /**
     * 总体训练
     * @param inputs       
     * @param outputs       
     * @param learnrate        学习精细度
     * @param error            容许误差
     * @param maxlearn        最大学习次数
     * @return 是否完成训练
     */
    public boolean train(double[][] inputs, double[][] outputs, double learnrate,
            double error,int maxlearn) {
        this.learnrate = learnrate;
        this.threshold = error;
        boolean complete = false;
        int count = 0;
        double e =0;
        while (!complete) {
            count++;
            e = 0;
            complete = true;
            for (int size = 0; size < inputs.length; size++) {
                e += learn(inputs[size], outputs[size]);
                if (e > threshold) {
                    complete = false;
                }
            }
            if(count>=maxlearn){
                System.err.println("convergence fail  error:"+e);
                return false;
            }
        }
        System.out.println("convergence success    error:"+e);
        return true;
    }

    /**
     * 单次学习
     *
     * @param input
     * @param output
     * @return 误差
     */
    private double learn(double[] input, double[] output) {
        /**把数据映射到前馈层的神经元里面*/
        makeNeuron(inputnode, input);
        /**通过每个神经链的权重 从隐藏层计算到最终输出层的值*/
        thinking();
        /**误差计算*/
        return evolutionComputing(output);
    }

    private void thinking() {
        transmitComputing(hiddennode);
        transmitComputing(outputnode);
    }

    /**
     * 神经元传输计算
     *
     * @param ns
     */
    private void transmitComputing(Neuron[] ns) {
        for (Neuron ne : ns) {
            double sum = 0.0;
            Set<Entry<Integer, Link>> linkset = ne.source.entrySet();
            for (Entry<Integer, Link> ent : linkset) {
                Link l = ent.getValue();
                Neuron n = l.owner;
                // 这里是重点,计算神经元*神经权重
                sum += n.data * l.weight;
            }
            // 计算完毕后通过 S型激活函数把数据存储在隐藏层的神经节点上
            ne.data = sigmoid(sum);
        }
    }
    /**
     * 最速梯度下降法 来计算 delta规则
     * @param datas
     * @return
     */
    private double evolutionComputing(double[] datas) {
        double[] output_deltaDatas = new double[outputnode.length];
        double totalError = 0.0;
        for (int i = 0; i < outputnode.length; i++) {
            /**
             * Erri = Ti – Oi O is the predicted output T is the correct output
             * Δi = Erri * g’(ini) g’ is the derivative of the activation
             * function g
             */
            output_deltaDatas[i] = (datas[i] - outputnode[i].data)
                    * sigmoidDerivative(datas[i]);
        }

        double[] hidden_deltaDatas = new double[hiddennode.length];
        for (int i = 0; i < hiddennode.length; i++) {
            /**
             * Δj = g’(inj) * Σi(Wj,i * Δi)
             */
            double error = 0.0;
            Set<Entry<Integer, Link>> linkSet = hiddennode[i].target.entrySet();
            for (Entry<Integer, Link> ent : linkSet) {
                error += output_deltaDatas[ent.getKey()]
                        * ent.getValue().weight;
            }
            hidden_deltaDatas[i] = sigmoidDerivative(hiddennode[i].data)
                    * error;
        }
        /**
         * Wj,i = Wj,i + α * Hj * Δi    Hj is the activation of the hidden unit
         */
        for (int i = 0; i < hiddennode.length; i++) {
            Set<Entry<Integer, Link>> linkSet = hiddennode[i].target.entrySet();
            for (Entry<Integer, Link> ent : linkSet) {
                Link hidden2output = ent.getValue();
                hidden2output.weight += output_deltaDatas[ent.getKey()]
                        * hiddennode[ent.getKey()].data * learnrate;
                //System.out.println("hidden2output:"+hidden2output);
            }
        }
        //System.out.println();
        /**
         * Wk,j = Wk,j + α * Ik * Δj Ik is the activation of the input unit
         */
        for (int i = 0; i < inputnode.length; i++) {
            Set<Entry<Integer, Link>> linkSet = inputnode[i].target.entrySet();
            for (Entry<Integer, Link> ent : linkSet) {
                Link input2hidden = ent.getValue();
                input2hidden.weight += hidden_deltaDatas[ent.getKey()]
                        * inputnode[i].data * learnrate;
                //System.out.println("inputnode[i].data:"+inputnode[i].data+"input2hidden:"+input2hidden);
            }
        }
        //System.out.println();
        /**
         * E = 1/2 Σi((Ti – Oi)^2)
         */
        for (int i = 0; i < outputnode.length; i++) {
            double temp = outputnode[i].data - datas[i];
            totalError +=  temp * temp;
        }
        return totalError * 0.5;
    }

    /**
     * 把数据映射到每个神经元里面
     *
     * @param neurons
     * @param datas
     */
    private void makeNeuron(Neuron[] neurons, double[] datas) {
        for (int len = 0; len < neurons.length; len++) {
            if(len >= datas.length){
                neurons[len].data = 1.0;
            }else{
                neurons[len].data = datas[len];
            }
        }
    }

    /**
     * 把output的神经元数据映射为矩阵
     *
     * @return
     */
    private double[] makeMatrix() {
        double[] temp = new double[outputnode.length];
        for (int i = 0; i < outputnode.length; i++) {
            temp[i] = outputnode[i].data;
        }
        return temp;
    }

    private void initNeurons(Neuron[] startns) {
        for (int lenN = 0; lenN < startns.length; lenN++) {
            if (startns[lenN] == null) {
                startns[lenN] = new Neuron();
            }
        }
    }

    /**
     * 这里是互相交叉连接
     *
     * @param startns
     * @param endns
     */
    private void makeLink(Neuron[] startns, Neuron[] endns) {
        for (int lenN = 0; lenN < startns.length; lenN++) {
            for (int len = 0; len < endns.length; len++) {
                Link target = startns[lenN].targetGet(len);
                if (target == null) {
                    target = new Link();
                    startns[lenN].targetLink(len, target);
                }
                target.setLinker(endns[len]);
            }
        }
    }

    /**
     * 这里是S型激活函数.最终目的是把所有数据都2值化
     *
     * @param x
     * @return
     */
    private double sigmoid(double x) {
        return Math.tanh(x);
    }

    /*
     * calculate a random number where: a <= rand < b def rand(a, b): return
     * (b-a)*random.random() + a
     */
    private double rand(double min, double max) {
        return (max - min) * random.nextDouble() + min;
    }

    // derivative of our sigmoid function
    private double sigmoidDerivative(double y) {
        // return (1.0 - sigmoid(y)) * sigmoid(y);
        // return 1.0-y*y;
        return 1.0 - sigmoid(y) * y;
    }

    /**
     * @param args
     * @throws Throwable
     */
    public static void main(String[] args) throws Throwable {
        //创建一个 反向传输神经网络
        JavaBackPropagationNeuralNetwork jbpn = new JavaBackPropagationNeuralNetwork(9, 4, 1);
        String inputData = FileUtils.readFileToString(new File("C:\\Users\\JH\\Desktop\\牽張input.txt"));
        String targetData = FileUtils.readFileToString(new File("C:\\Users\\JH\\Desktop\\牽張target.txt"));
        double[][] input = getRecord(inputData);
        double[][] target = getSignRecord(targetData);
        
//        String input = FileUtils.readFileToString(new File("C:\\Users\\JH\\Desktop\\爬牆訓練input.txt"));
//        String[] line = input.split("\n");
//        double[][] col = new double[500][9];
//        for(int i=0;i<line.length;i++)
//        {
//        	String colStr[] = line[i].split("	");
//        	double[] colDou = new double[9];
//        	for(int j=0;j<colStr.length;j++)
//        	{
//        		colDou[j] = Double.valueOf(colStr[j]);
//        	}
//        	col[i] = colDou;
//        }
//        System.out.println(Arrays.toString(target));
//        System.out.println(col[0].length);
//        double[][] dd = new double[][] { new double[] {0},new double[] {1},new double[] {1},new double[] {0} };
        
//        System.out.println(dd[1][0]);
//        boolean bb=true;
//        while(!jbpn.train(input, target, 0.8, 0.01, 30000))
//        {
//        	jbpn.reBuildNeuralNetwork();
//        }
        
//        System.out.println(target[4][0]);
        //训练XOR
        while(!jbpn.train(input,target,1, 6000.0,30000))
        {
            jbpn.reBuildNeuralNetwork();
        }
        
        String testinput = FileUtils.readFileToString(new File("C:\\Users\\JH\\Desktop\\牽張測試.txt"));
        double testdou[][] = getRecord(testinput);
//        System.out.println(Arrays.toString(testdou[0]));
        String resultTime="";
        for(int i=0;i<testdou.length;i++)
        {
        	long start =System.nanoTime(); 
//        	System.out.println(start);
//        	Random r = new Random();
//        	Thread.sleep(r.nextInt(1));
        	double[] res = jbpn.thinking(testdou[i]);
        	for(double s:res){
        		System.out.println("thinking:"+s);
        	}
        	long end = System.nanoTime();
//        	System.out.println(end);
        	System.out.println((end-start));
        	resultTime+=Long.valueOf((end-start)).toString()+"\n";
        }
        
        FileUtils.writeStringToFile(new File("c:\\book\\牽張時間.txt"), resultTime);
        
//        批量思考
//      double[][] ress = jbpn.batchThinking(testdou);
//      for(double[] s:ress){
//          for(double d:s){
//              System.out.print("batchThinking:"+d+"    ");
//          }
//          System.out.println();
//      }
        
        
//        //批量思考
//        double[][] ress = jbpn.batchThinking(new double[][] { new double[] { -0.8, -0.9 },new double[] { 0.7, 0.3 }, new double[] { -.6, .85 },new double[] { 1, -1 } });
//        for(double[] s:ress){
//            for(double d:s){
//                System.out.print("batchThinking:"+d+"    ");
//            }
//            System.out.println();
//        }
       
    }
    
    public static double[][] getRecord(String inputStr) throws IOException
    {
        String[] line = inputStr.split("\n");
        String[] colCount = line[0].split("	");
        
        double[][] col = new double[line.length][colCount.length];
        for(int i=0;i<line.length;i++)
        {
        	String colStr[] = line[i].split("	");
        	double[] colDou = new double[9];
        	for(int j=0;j<colStr.length;j++)
        	{
        		colDou[j] = Double.valueOf(colStr[j]);
        	}
        	col[i] = colDou;
        }
        
        return col;
    }
    
    public static double[][] getSignRecord(String inputStr)
    {
    	String[] line = inputStr.split("\n");
    	double[][] result = new double[line.length][1];
    	
    	for(int i=0;i<line.length;i++)
    	{
    		result[i][0] = Double.valueOf(line[i]);
    	}
    	return result;
    }

}