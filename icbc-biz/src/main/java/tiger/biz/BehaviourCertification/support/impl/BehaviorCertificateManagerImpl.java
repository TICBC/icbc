package tiger.biz.BehaviourCertification.support.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.biz.BehaviourCertification.support.BehaviorCertificateManager;
import tiger.biz.BehaviourCertification.support.others.CalcFestivalAndHoliday;
import tiger.biz.BehaviourCertification.support.others.CalclsInterval;
import tiger.biz.BehaviourCertification.support.others.StatisticsUtility;
import tiger.common.dal.persistence.behaviour_authen.*;
import tiger.common.dal.persistence.mapper.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/1/13.
 */
@Service
public class BehaviorCertificateManagerImpl implements BehaviorCertificateManager {

    @Autowired
    Simulation_source_tableMapper simulationSourceDao;

    @Autowired
    Behavior_certificate_tableMapper behaviorCertificateDao;

    @Autowired
    All_location_tableMapper allLocationTableMapper;

    @Autowired
    Card_tableMapper cardDao;

    @Autowired
    Assist_tableMapper assistDao;

    /**
     * //@param 用户ID
     * @return 交易落在双休日的概率 float[0] 落在工作日的概率 float[1] 落在双休日的概率
     * @throws ParseException
     *
     */
    @Override
    public float[] calcWeekendProbabilty(int userId){
        try {
//            List<Simulation_source_table> simulationSources = simulationSourceDao.getSimulationSourceByuser(userId);
            Simulation_source_tableExample example = new Simulation_source_tableExample();
            Simulation_source_tableExample.Criteria criteria = example.createCriteria();
            criteria.andUserEqualTo(userId);
            List<Simulation_source_table> simulationSources = simulationSourceDao.selectByExample(example);
            int weekendCount = 0;
            for (Simulation_source_table simulationSource : simulationSources) {
                Date date = simulationSource.getEventDt();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateStr = sdf.format(date);
                Date tmpDate = sdf.parse(dateStr);
                if (CalcFestivalAndHoliday.checkHoliday(tmpDate)) {
                    weekendCount++;
                }
            }
            float[] probabilty = new float[2];
            probabilty[1] = (float) weekendCount / simulationSources.size();
            probabilty[0] = 1 - probabilty[1];
            return probabilty;
        } catch (ParseException e) {
            throw new IllegalArgumentException("日期解析出错");
        }
    }

    /**
     *
     * @param userId
     * @return float[0] 落在平时的概率 float[1] 落在节日的概率
     *
     */
    @Override
    public float[] calcFestivalProbabilty(int userId) {
        Simulation_source_tableExample example = new Simulation_source_tableExample();
        Simulation_source_tableExample.Criteria criteria = example.createCriteria();
        criteria.andUserEqualTo(userId);
        List<Simulation_source_table> simulationSources = simulationSourceDao.selectByExample(example);
        int festivalCount = 0;
        for (Simulation_source_table simulationSource : simulationSources) {
            Date date = simulationSource.getEventDt();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = sdf.format(date);
            if (CalcFestivalAndHoliday.checkFestival(dateStr)) {
                festivalCount++;
            }
        }
        float[] probabilty = new float[2];
        probabilty[1] = (float) festivalCount / simulationSources.size();
        probabilty[0] = 1 - probabilty[1];
        return probabilty;
    }
    @Override
    public float[] calcTimeIntervalProbabilty(int userId) {
        // 获得相邻时间的时间间隔
        try {
            Simulation_source_tableExample example = new Simulation_source_tableExample();
            Simulation_source_tableExample.Criteria criteria = example.createCriteria();
            criteria.andUserEqualTo(userId);
            example.setOrderByClause("timeStamp asc");
            List<Simulation_source_table> simulationSources = simulationSourceDao.selectByExample(example);
         //   List<Simulation_source_table> simulationSources = simulationSourceDao.getSimulationSourceByuserOrderByTime(userId);
            List<Long> times = new ArrayList<Long>();
            for (Simulation_source_table simulationSource : simulationSources) {
                Date temp=simulationSource.getTimeStamp();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateStr = sdf.format(temp);
                Date date = sdf.parse(dateStr);
                long time = date.getTime();
                times.add(time);
            }

            float[] data = new float[times.size()-1];
            for(int i = 0; i < times.size()-1;i++){
                long diff = times.get(i+1) - times.get(i);
                data[i] = (float) diff;
            }

            //生成对应的方差
            float[] meanAndVariance = StatisticsUtility.calcMeanVariance(data);
            float mean = meanAndVariance[0];
            float variance = meanAndVariance[1];

            int p1 = 0, p2 = 0, p3 = 0, p4 = 0;
            for (float time : data) {
                if (time >= 0 && time < mean - variance) {
                    p1++;
                } else if (time >= mean - variance && time < mean) {
                    p2++;
                } else if (time >= mean && time < mean + variance) {
                    p3++;
                } else {
                    p4++;
                }
            }
            float[] pi = new float[4];
            pi[0] =(float) p1 / data.length;
            pi[1] =(float) p2 / data.length;
            pi[2] =(float) p3 / data.length;
            pi[3] =(float) p4 / data.length;

            return pi;
        } catch (ParseException e) {
            throw new IllegalArgumentException("日期解析出错");
        }
    }

    /**
     *
     * 计算信息熵
     *
     */
    @Override
    public float calcLocationInformationEntropy(int userId) {
        Simulation_source_tableExample example = new Simulation_source_tableExample();
        Simulation_source_tableExample.Criteria criteria = example.createCriteria();
        criteria.andUserEqualTo(userId);
        example.setOrderByClause("timeStamp asc");
        List<Simulation_source_table> simulationSources = simulationSourceDao.selectByExample(example);

        Set<String> locationSet = new HashSet<String>();
        List<String> locationList = new ArrayList<String>();

        HashMap<String, Integer> locationMap = new HashMap<String, Integer>();
        for (Simulation_source_table simulationSource : simulationSources) {
            String zoneNum = simulationSource.getZoneNum();
            locationSet.add(zoneNum);
            locationList.add(zoneNum);
            //新值
            if(!locationMap.containsKey(zoneNum)){
                locationMap.put(zoneNum, 1);
            }else{
                int num = locationMap.get(zoneNum);
                locationMap.put(zoneNum, ++num);
            }
        }

        //写入常用数据库列表
        addLoactionByUser(userId,locationMap);

        //System.out.println(locationSet);
        Object[] locations = (Object[]) locationSet.toArray();
        int[] pi = new int[locationSet.size()];
        for (String zoneNum : locationList) {
            for (int i=0; i< locations.length;i++){
                if(locations[i] instanceof String){
                    if(zoneNum.equals(locations[i])){
                        pi[i] ++;
                    }
                }
            }
        }

        float[] pl = new float[locationSet.size()];
        for (int i = 0; i < pl.length; i++) {
            pl[i] = (float) pi[i] / locationList.size();
        }
        int max=getmaxlocationnum();
        System.out.println(max);
        return StatisticsUtility.calcInformationEntropy(pl,max);
    }
    @Override
    public float[] calcBalanceIntervalProbabilty(int userId) {
        // 得到一个升序的金钱列表
        Simulation_source_tableExample example = new Simulation_source_tableExample();
        Simulation_source_tableExample.Criteria criteria = example.createCriteria();
        criteria.andUserEqualTo(userId);
        List<Simulation_source_table> simulationSources = simulationSourceDao.selectByExample(example);
        List<Float> balance = new ArrayList<Float>();
        for (Simulation_source_table simulationSource : simulationSources) {
            float money = simulationSource.getTxAmt().floatValue();
            balance.add(money);
        }
        //System.out.println(balance);
        float[] data = new float[balance.size()];
        for(int i = 0; i < balance.size();i++){
            data[i] = balance.get(i);
        }

        //生成对应的方差
        float[] meanAndVariance = StatisticsUtility.calcMeanVariance(data);
        float mean = meanAndVariance[0];
        float variance = meanAndVariance[1];

        int p1 = 0, p2 = 0, p3 = 0, p4 = 0;
        for (float money : data) {
            if (money >= 0 && money < mean - variance) {
                p1++;
            } else if (money >= mean - variance && money < mean) {
                p2++;
            } else if (money >= mean && money < mean + variance) {
                p3++;
            } else {
                p4++;
            }
        }
        float[] pi = new float[4];
        pi[0] =(float) p1 / data.length;
        pi[1] =(float) p2 / data.length;
        pi[2] =(float) p3 / data.length;
        pi[3] =(float) p4 / data.length;

        return pi;
    }

    /**
     *
     * //@param 根据交易表的用户Id新建行为证书
     * 生成行为证书
     *
     */
    @Override
    public void addNewBehaviorCertificate(int userId) {

        Behavior_certificate_table behaviorCertificate;
        Behavior_certificate_tableExample example=new Behavior_certificate_tableExample();
        Behavior_certificate_tableExample.Criteria criteria=example.createCriteria();
        criteria.andUserEqualTo(userId);
        List<Behavior_certificate_table> temp=behaviorCertificateDao.selectByExample(example);
        behaviorCertificate=temp.get(0);
        // 新建行为证书
        if (behaviorCertificate == null) {
            behaviorCertificate = new Behavior_certificate_table();
            behaviorCertificate.setUser(userId);
            // 交易是否为工作日的概率
            float[] pw = calcWeekendProbabilty(userId);
            float max=maxfloat(pw);
            behaviorCertificate.setWeekday(pw[0]);
            behaviorCertificate.setWeekend(pw[1]);
            // 交易是否为节假日的概率
            float[] pf = calcFestivalProbabilty(userId);
            max=maxfloat(pf);
            behaviorCertificate.setNormalDay(pf[0]);
            behaviorCertificate.setFestival(pf[1]);
            // 落在四个时间区间的概率
            float[] pi = calcTimeIntervalProbabilty(userId);
            max=maxfloat(pi);
            behaviorCertificate.setInterval1(pi[0]);
            behaviorCertificate.setInterval2(pi[1]);
            behaviorCertificate.setInterval3(pi[2]);
            behaviorCertificate.setInterval4(pi[3]);
            // 地点变动信息熵
            float pl = calcLocationInformationEntropy(userId);
            behaviorCertificate.setLocation(pl);
            // 交易价格范围
            float[] pr = calcBalanceIntervalProbabilty(userId);
            max=maxfloat(pr);
            behaviorCertificate.setRange1(pr[0]);
            behaviorCertificate.setRange2(pr[1]);
            behaviorCertificate.setRange3(pr[2]);
            behaviorCertificate.setRange4(pr[3]);
            // 最后一次交易记录
            Simulation_source_tableExample example1 = new Simulation_source_tableExample();
            Simulation_source_tableExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andUserEqualTo(userId);
            List<Simulation_source_table> simulationSources = simulationSourceDao.selectByExample(example1);
            Simulation_source_table simulationSource=simulationSources.get(0);
            //这里不知道example从怎么获取用户最近交易记录，所以就直接用for循环比较字符串大小
            for(int i=1;i<simulationSources.size();i++)
            {
                if(simulationSource.getTimeStamp().compareTo(simulationSources.get(i).getTimeStamp())<0)
                {
                    simulationSource=simulationSources.get(i);
                }
            }
            //Simulation_source_table simulationSource = simulationSourceDao.getLatestTransactionByUser(userId);
            behaviorCertificate.setLastTradeTime(simulationSource.getTimeStamp());
            behaviorCertificateDao.insert(behaviorCertificate);
            System.out.println("用户：" +userId+"插入成功");

            //插入交易列表
            addCardTransByUser(userId);

            //插入辅助列表
            addAssistByUser(userId);
        }
    }
    // 判断是否曾经给转入卡号转账过

    // 获得用户交易时间在工作日或者非工作日的概率
    @Override
    public float getWeekProbabilty(Simulation_source_table simulationsource) {

        Behavior_certificate_tableExample example = new Behavior_certificate_tableExample();
        Behavior_certificate_tableExample.Criteria criteria = example.createCriteria();
        criteria.andUserEqualTo(simulationsource.getUser());
        List<Behavior_certificate_table>  temp = behaviorCertificateDao.selectByExample(example);
        Behavior_certificate_table behaviorcertificate = temp.get(0);

        float week_probability;
        Calendar cal = Calendar.getInstance();
        cal.setTime(simulationsource.getEventDt());
        if (CalcFestivalAndHoliday.checkHoliday(cal))
            week_probability = behaviorcertificate.getWeekday();
        else
            week_probability = behaviorcertificate.getWeekend();
        float[] f={behaviorcertificate.getWeekday(),behaviorcertificate.getWeekend()};
        float max=maxfloat(f);
        return week_probability/max;
    }
    // 获得用户交易时间在节假日或非节假日的概率
    @Override
    public float getFestivalProbabilty(Simulation_source_table simulationsource) {


        Behavior_certificate_tableExample example = new Behavior_certificate_tableExample();
        Behavior_certificate_tableExample.Criteria criteria = example.createCriteria();
        criteria.andUserEqualTo(simulationsource.getUser());
        List<Behavior_certificate_table>  temp1 = behaviorCertificateDao.selectByExample(example);
        Behavior_certificate_table behaviorcertificate = temp1.get(0);

        float festival_probability;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String temp = sdf.format(simulationsource.getEventDt());
        if (CalcFestivalAndHoliday.checkFestival(temp)) {
            festival_probability = behaviorcertificate.getFestival();
        }

        else {
            festival_probability = behaviorcertificate.getNormalDay();
        }
        float[] f={behaviorcertificate.getFestival(),behaviorcertificate.getNormalDay()};
        float max=maxfloat(f);
        return festival_probability/max;
    }
    // 获得与上次交易时间间隔所在区间的概率
    public float getIntervalProbabilty(Simulation_source_table simulationsource) {
        float time_probability = 0;

        Behavior_certificate_tableExample example = new Behavior_certificate_tableExample();
        Behavior_certificate_tableExample.Criteria criteria = example.createCriteria();
        criteria.andUserEqualTo(simulationsource.getUser());
        List<Behavior_certificate_table>  temp1 = behaviorCertificateDao.selectByExample(example);
        Behavior_certificate_table behaviorcertificate = temp1.get(0);


        Assist_tableExample example1 = new Assist_tableExample();
        Assist_tableExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andUserEqualTo(simulationsource.getUser());
        List<Assist_table> temp2 = assistDao.selectByExample(example1);
        Assist_table assist = temp2.get(0);

        Date temp=behaviorcertificate.getLastTradeTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String start = sdf.format(temp);
        String starttime = start;
        String endtime = sdf.format(simulationsource.getEventDt());
        long diff = CalclsInterval.diff(starttime, endtime);
        diff=diff / 1000 % 60;//转换成秒为单位
        float meant, vart;
        meant = assist.getMeanTime();
        vart = assist.getVarTime();
        if ((float) diff < meant - vart) {
            time_probability = behaviorcertificate.getInterval1();
        } else if ((float) diff < meant) {
            time_probability = behaviorcertificate.getInterval2();
        } else if ((float) diff < meant + vart) {
            time_probability = behaviorcertificate.getInterval3();
        } else {
            time_probability = behaviorcertificate.getInterval4();
        }
        float[] f={behaviorcertificate.getInterval1(),behaviorcertificate.getInterval2(),behaviorcertificate.getInterval3(),behaviorcertificate.getInterval4()};
        float max=maxfloat(f);
        return time_probability/max;
    }
    // 获得信息熵
    public float getlocationProbabilty(Simulation_source_table simulationsource) {
        float loc_probability = 0;
        Behavior_certificate_tableExample example = new Behavior_certificate_tableExample();
        Behavior_certificate_tableExample.Criteria criteria = example.createCriteria();
        criteria.andUserEqualTo(simulationsource.getUser());
        List<Behavior_certificate_table>  temp1 = behaviorCertificateDao.selectByExample(example);
        Behavior_certificate_table behaviorcertificate = temp1.get(0);
//        Behavior_certificate_table behaviorcertificate = behaviorCertificateDao.getCerticateByuser(simulationsource.getUser());
        loc_probability = behaviorcertificate.getLocation();
        return loc_probability;
    }
    //获得交易金额所在区间的概率
    public float getMoneyRangeProbabilty(Simulation_source_table simulationsource) {
        Assist_tableExample example1 = new Assist_tableExample();
        Assist_tableExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andUserEqualTo(simulationsource.getUser());
        List<Assist_table> temp2 = assistDao.selectByExample(example1);
        Assist_table assist = temp2.get(0);
//        Assist_table assist = assistDao.GetAssistByUser(simulationsource.getUser());
        Behavior_certificate_tableExample example = new Behavior_certificate_tableExample();
        Behavior_certificate_tableExample.Criteria criteria = example.createCriteria();
        criteria.andUserEqualTo(simulationsource.getUser());
        List<Behavior_certificate_table>  temp1 = behaviorCertificateDao.selectByExample(example);
        Behavior_certificate_table behaviorcertificate = temp1.get(0);
//        Behavior_certificate_table behaviorcertificate = behaviorCertificateDao.getCerticateByuser(simulationsource.getUser());
        float money_probability = 0;
        float meanm, varm;
        meanm = assist.getMeanMoney();
        varm = assist.getVarMoney();
        float money = simulationsource.getTxAmt().floatValue();
        if ((float) money < meanm - varm) {
            money_probability = behaviorcertificate.getRange1();
        }
        else if ((float) money < meanm) {
            money_probability = behaviorcertificate.getRange2();
        } else if ((float) money < meanm + varm) {
            money_probability = behaviorcertificate.getRange3();
        } else {
            money_probability = behaviorcertificate.getRange4();
        }
        float[] f={behaviorcertificate.getRange1(),behaviorcertificate.getRange2(),behaviorcertificate.getRange3(),behaviorcertificate.getRange4()};
        float max=maxfloat(f);
        return money_probability/max;
    }
    //计算风险度
    public double calRiskVal(double a1, double a2, double a3, double a4, double a5, float week_probability,
                             float festival_probability, float interval_probability, float loc_probability, float money_probability) {// 计算风险值
        double ptemp = a1 * week_probability * a2 * festival_probability * a3 * interval_probability * a4
                * loc_probability * a5 * money_probability;
        ptemp = log(ptemp, 2);
        double riskval = (1 / (Math.exp(ptemp) + 1)) * 2 - 1;
        return riskval;
    }

    public static double log(double value, double base) {
        return Math.log(value) / Math.log(base);
    }

    // 匹配
    public boolean behaviorCertificate(Simulation_source_table simulationsource) throws IOException {
        // TODO Auto-generated method stub
        float week_probability = 0, festival_probability, interval_probability, loc_probability, money_probability;
        double a1 = 5, a2 = 1, a3 = 1, a4 = 1, a5 =1, riskval = 0, threshold = 0.5;
        boolean pass = false;
        pass = tranBefore(simulationsource);
        if (pass)
        {
            simulationsource.setPredicationResult(true);
            Simulation_source_tableExample example = new Simulation_source_tableExample();
            Simulation_source_tableExample.Criteria criteria = example.createCriteria();
            criteria.andUserEqualTo(simulationsource.getUser()).andTimeStampEqualTo(simulationsource.getTimeStamp());

            simulationSourceDao.updateByExample(simulationsource,example);

            simulationSourceDao.updateByExample(simulationsource,example);

//           // simulationSourceDao.updateSim(simulationsource);
//            File file=new File("D:/1.txt");
//            FileWriter fileWriter=new FileWriter(file,true);
//            BufferedWriter writer=new BufferedWriter(fileWriter);
//            System.out.println("风险值：0.0");
//            String ris="风险值：0.0";
//            writer.append(ris);
//            writer.newLine();
//            writer.flush();
//            writer.close();
            return true;
        }

        //获得工作日或非工作日概率
        week_probability = getWeekProbabilty(simulationsource);
        //System.out.println("工作日或非工作日概率:"+week_probability);
        //获得节假日或非节假日概率
        festival_probability = getFestivalProbabilty(simulationsource);
        //System.out.println("节假日或非节假日概率:"+festival_probability);
        //获得时间间隔概率
        interval_probability = getIntervalProbabilty(simulationsource);
        //System.out.println("时间间隔概率:"+interval_probability);
        //1或者获得信息熵

        All_location_tableExample example=new All_location_tableExample();
        All_location_tableExample.Criteria criteria=example.createCriteria();
        criteria.andUserEqualTo(simulationsource.getUser());
        List<All_location_table> alllocation=allLocationTableMapper.selectByExample(example);

        //alllocation=allLocationTableMapper.GetLocationByUser(simulationsource.getUser());
        boolean InPreviousLocation=false;
        for(int i=0;i<alllocation.size();i++)
        {
            if(simulationsource.getZoneNum().equals(alllocation.get(i).getLocation()))
            {
                InPreviousLocation=true;
            }
        }
        if(InPreviousLocation)
            loc_probability=1;
        else
            loc_probability = getlocationProbabilty(simulationsource);
        //System.out.println("信息熵:"+loc_probability);
        //获得金钱范围概率
        money_probability = getMoneyRangeProbabilty(simulationsource);
        //System.out.println("金钱范围概率:"+money_probability);
        //计算风险值
        riskval = calRiskVal(a1, a2, a3, a4, a5, week_probability, festival_probability, interval_probability,
                loc_probability, money_probability);
        if (riskval < threshold)
            pass = true;
        if(pass==true)
            simulationsource.setPredicationResult(true);
        else
            simulationsource.setPredicationResult(false);
        DecimalFormat df = new DecimalFormat("#.0000");
        System.out.println("*****************************************");
        simulationsource.setPredicationResult(true);
        Simulation_source_tableExample example1 = new Simulation_source_tableExample();
        Simulation_source_tableExample.Criteria criteria1 = example1.createCriteria();

        criteria1.andUserEqualTo(simulationsource.getUser()).andTimeStampEqualTo(simulationsource.getTimeStamp());
        simulationSourceDao.updateByExample(simulationsource,example1);
        System.out.println("*****************************************222222222222222222222222222222");
       /* File file=new File("D:/1.txt");
        FileWriter fileWriter=new FileWriter(file,true);
        BufferedWriter writer=new BufferedWriter(fileWriter);
        //System.out.println("工作日概率："+week_probability+",节假日概率："+festival_probability+"，时间间隔概率："+interval_probability+"，信息熵："+loc_probability+"，金钱区间概率："+money_probability);
        //System.out.println("风险值:"+riskval);
        String ris="工作日概率："+week_probability+",节假日概率："+festival_probability+"，时间间隔概率："+interval_probability+"，信息熵："+loc_probability+"，金钱区间概率："+money_probability+"\n风险值:"+riskval;
        System.out.println(ris);
        writer.append(ris);
        writer.newLine();
        writer.flush();
        writer.close();*/
        return pass;
    }
    public boolean tranBefore(Simulation_source_table simulation_source_table) {
        Card_tableExample example=new Card_tableExample();
        Card_tableExample.Criteria criteria=example.createCriteria();
        criteria.andUserEqualTo(simulation_source_table.getUser());
        List<Card_table> TICN=cardDao.selectByExample(example);
        boolean pass = false;
        for (int i = 0; i < TICN.size(); i++) {
            //System.out.println(TICN.get(i).getTicn());
            if (simulation_source_table.getTranInAcctNum().equals(TICN.get(i).getTicn())) {
                pass = true;
            }
        }
        return pass;
    }
    /**
     * 生成用户常用地点列表
     */
    private void addLoactionByUser(int userId,HashMap<String, Integer> locationMap) {
        for (String location : locationMap.keySet()) {
            All_location_table allLocation;
            allLocation = new All_location_table();
            allLocation.setUser(userId);
            allLocation.setNum(locationMap.get(location));
            allLocation.setLocation(location);
            allLocationTableMapper.insert(allLocation);
            System.out.println("插入地点列表成功");
        }
    }

    /**
     * 生成用户转入卡号列表
     */

    private void addCardTransByUser(int userId){
        Simulation_source_tableExample example = new Simulation_source_tableExample();
        Simulation_source_tableExample.Criteria criteria = example.createCriteria();
        criteria.andUserEqualTo(userId);
        List<Simulation_source_table> simulationSources = simulationSourceDao.selectByExample(example);
       // List<Simulation_source_table> simulationSources = simulationSourceDao.getSimulationSourceByuser(userId);
        Set<String> tranInAcctSet = new HashSet<String>();
        for (Simulation_source_table simulationSource : simulationSources) {
            tranInAcctSet.add(simulationSource.getTranInAcctNum());
        }

        for (String tranInAcct : tranInAcctSet) {
            Card_table card = new Card_table();
            card.setUser(userId);
            card.setTicn(tranInAcct);
            cardDao.insert(card);
            System.out.println("插入交易列表成功");
        }
    }

    /**
     * 生成assit辅助列表
     */

    private void addAssistByUser(int userId){
        try {
            Simulation_source_tableExample example = new Simulation_source_tableExample();
            Simulation_source_tableExample.Criteria criteria = example.createCriteria();
            criteria.andUserEqualTo(userId);
            List<Simulation_source_table> balanceSimulationSources = simulationSourceDao.selectByExample(example);
           // List<Simulation_source_table> balanceSimulationSources = simulationSourceDao.getSimulationSourceByuser(userId);
            //生成balance 均值方差
            List<Float> balance = new ArrayList<Float>();
            for (Simulation_source_table simulationSource : balanceSimulationSources) {
                float money = simulationSource.getTxAmt().floatValue();
                balance.add(money);
            }

            float[] data = new float[balance.size()];
            for(int i = 0; i < balance.size();i++){
                data[i] = balance.get(i);
            }

            //生成对应的方差
            float[] balanceMeanAndVariance = StatisticsUtility.calcMeanVariance(data);
            float balanceMean = balanceMeanAndVariance[0];
            float balanceVariance = balanceMeanAndVariance[1];

            Simulation_source_tableExample example1 = new Simulation_source_tableExample();
            Simulation_source_tableExample.Criteria criteria1 = example1.createCriteria();
            criteria.andUserEqualTo(userId);
            example1.setOrderByClause("timeStamp asc");
            List<Simulation_source_table> timeSimulationSources = simulationSourceDao.selectByExample(example1);
            //List<Simulation_source_table> timeSimulationSources = simulationSourceDao.getSimulationSourceByuserOrderByTime(userId);
            //生成交易时间 均值方差
            List<Long> times = new ArrayList<Long>();
            for (Simulation_source_table simulationSource : timeSimulationSources) {
                Date temp=simulationSource.getTimeStamp();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateStr = sdf.format(temp);
                Date date = sdf.parse(dateStr);
                long time = date.getTime();
                times.add(time);
            }

            float[] timeData = new float[times.size()-1];
            for(int i = 0; i < times.size()-1;i++){
                long diff = times.get(i+1) - times.get(i);
                timeData[i] = (float) diff;
            }

            //生成对应的方差
            float[] timeMeanAndVariance = StatisticsUtility.calcMeanVariance(timeData);
            float timeMean = timeMeanAndVariance[0];
            float timeVariance = timeMeanAndVariance[1];

            Assist_table assist = new Assist_table();
            assist.setUser(userId);
            assist.setMeanTime(timeMean);
            assist.setVarTime(timeVariance);
            assist.setMeanMoney(balanceMean);
            assist.setVarMoney(balanceVariance);
            assistDao.insert(assist);
            System.out.println("辅助列表插入成功");
        } catch (ParseException e) {
            throw new IllegalArgumentException("日期解析出错");
        }
    }

    public int getmaxlocationnum() {
        // TODO Auto-generated method stub

        //AllLocationTableMapper loc=new AllLocationDaoImpl();
        //所有用户的编号列表
        Simulation_source_tableExample example = new Simulation_source_tableExample();
        Simulation_source_tableExample.Criteria criteria = example.createCriteria();
        example.setDistinct(true);
        List<Simulation_source_table> simulation=simulationSourceDao.selectByExample(example);
        List<Integer> user=null;
        for(int i=0;i<simulation.size();i++)
        {
            user.add(simulation.get(i).getUser());
        }

        //每个用户的地点个数列表
        List<Integer> locationNumByUser=new ArrayList<Integer>();
        for(int i=0;i<user.size();i++)
        {
            List<String> locationByUser=null;
            for(int j=0;j<simulation.size();j++)
            {
                user.add(simulation.get(i).getUser());
                locationByUser.add(simulation.get(j).getZoneNum());
            }

            locationNumByUser.add(locationByUser.size());
        }
        System.out.println(locationNumByUser);
        int max=maxlocationnum(locationNumByUser);
        return max;
    }

    public int maxlocationnum(List<Integer> locationNumByUser) {
        // TODO Auto-generated method stub
        int max=locationNumByUser.get(0);
        for(int i=1;i<locationNumByUser.size();i++)
        {
            if(max<locationNumByUser.get(i))
            {
                max=locationNumByUser.get(i);
            }
        }
        return max;
    }

    public float maxfloat(float[] f) {
        // TODO Auto-generated method stub
        float max=f[0];
        for(int i=1;i<f.length;i++)
        {
            if(max<f[i])
            {
                max=f[i];
            }
        }
        return max;
    }
}
