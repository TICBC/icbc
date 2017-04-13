package tiger.biz.BehaviourCertification.support;


import tiger.common.dal.persistence.behaviour_authen.Simulation_source_table;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/1/13.
 */
public interface BehaviorCertificateManager {
    float[] calcWeekendProbabilty(int userId);

    public float[] calcFestivalProbabilty(int userId);

    public float[] calcTimeIntervalProbabilty(int userId);

    public float calcLocationInformationEntropy(int userId);

    public float[] calcBalanceIntervalProbabilty(int userId);

    public boolean behaviorCertificate(Simulation_source_table simulationsource) throws IOException;

    public int getmaxlocationnum();

    public int maxlocationnum(List<Integer> locationNumByUser);

    public float maxfloat(float[] f);

    public boolean tranBefore(Simulation_source_table simulationsource);

    public float getWeekProbabilty(Simulation_source_table simulationsource);

    public float getFestivalProbabilty(Simulation_source_table simulationsource);

    public float getIntervalProbabilty(Simulation_source_table simulationsource);

    public float getlocationProbabilty(Simulation_source_table simulationsource);

    public float getMoneyRangeProbabilty(Simulation_source_table simulationsource);

    public double calRiskVal(double a1, double a2, double a3, double a4, double a5, float week_probability, float festival_probability, float interval_probability, float loc_probability, float money_probability);

    //增删改查操作
    public void addNewBehaviorCertificate(int userId);
}
