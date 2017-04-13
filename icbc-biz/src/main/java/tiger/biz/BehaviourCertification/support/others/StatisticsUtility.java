package tiger.biz.BehaviourCertification.support.others;

/**
 * 
 * @author Demon Song
 *
 */

public abstract class StatisticsUtility {

	/**
	 * 
	 * @param data 
	 * @return long[0] = mean, long[1] = variance
	 */
	public static long[] calcMeanVariance (long[] data){
		long[] result = new long[2];
		//计算均值
		long sum = 0;
		for(int i = 0; i < data.length; i++){
			sum += data[i];
		}
		result[0] = sum / data.length;
		//计算方差
		sum =0;
        for(int i = 0; i < data.length; i++){
            sum += Math.sqrt((data[i] -result[0]) * (data[i] -result[0]));
        }
        result[1] = sum / (data.length - 1);
		return result;
	}
	
	/**
	 * 
	 * @param data 
	 * @return float[0] = mean, float[1] = variance
	 */
	public static float[] calcMeanVariance (float[] data){
		float[] result = new float[2];
		//计算均值
		float sum = 0;
		for(int i = 0; i < data.length; i++){
			sum += data[i];
		}
		result[0] = sum / data.length;
		//计算方差
		sum =0;
        for(int i = 0; i < data.length; i++){
            sum += Math.sqrt((data[i] -result[0]) * (data[i] -result[0]));
        }
        result[1] = sum / (data.length - 1);
		return result;
	}
	
	/**
	 * 
	 * @param fis 必须满足概率定义 ∑fi=1
	 * @return 计算信息熵
	 */
	public static float calcInformationEntropy(float[] fis,int max){
		int m = fis.length;
		float entropy = 0;
		for (int i =0; i<m; i++){
			if(fis[i] ==0){
				entropy +=0;
			}
			else{
				entropy += fis[i] * (Math.log(1/fis[i]) /Math.log(max));
			}
		}
		return entropy;
	}
	
}
