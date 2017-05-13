package 爬牆類神經;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class Mains {
	
	public static void main(String[] args) throws IOException
	{
		String traindatastr = FileUtils.readFileToString(new File("D:\\類神經訓練資料\\爬牆訓練(10).txt"));
//		String testdatastr = FileUtils.readFileToString(new File("D:\\類神經訓練資料.txt"));
		
		//row
		String[] trainData = traindatastr.split("\n");
		
		
		爬牆 fs = new 爬牆(9,1);
		fs.setLayer(9, 4, 1);
		
		
		for(int i=1;i<trainData.length;i++)
		{
			String oneSet = trainData[i];
			
			String[] trainCol = oneSet.split("	");
			double[] input = new double[8];
			double[] output = new double[1];
			for(int j=0;j<10;j++)
			{
				if(j!=9)
					input[j]=Double.valueOf(trainCol[i]).doubleValue();
				else
					output[0]=Double.valueOf(trainCol[9]).doubleValue();
			}
			fs.addData(input, output);
		}
		
		
		
		
		
		
		
	}
	
	
}
