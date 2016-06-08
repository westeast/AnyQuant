package service.impl.strategy;

import entity.FactorEntity;
import service.helper.MathHelper;
import util.MyDate;
import util.enumration.AnalysisFactor;
import vo.ReportVO;

import java.util.*;

/**
 * 因子选股策略
 * 根据因子评价策略选出较为有效的因子,之后根据这个因子以及用户给定的其他一些参数:调仓频率,交易费率等等,
 * 提供给用户回测的数据,包括各个因子的情况
 * @author Qiang
 * @date 6/5/16
 */
public class FactorStrategy extends MultiStockStrategy {

    /**
     * 用户选择的因子和比重
     */
    Map  <AnalysisFactor,Double>  weightedFactors;

    /**
     * 每次买入前计算各个股票的综合因子
     */
    Map<String,Double> finalFactors;


    /**
     * 投资比重
     */
    double [] investWeight;

    /**
     * 交易当天的股票因子数据
     */
    List<FactorEntity> curFactorEntities;

    /**
     *
     * @param capital
     * @param taxRate
     * @param baseCode
     * @param start
     * @param end
     * @param stocks            股票池
     * @param weightedFactors  因子和权重
     * @param investWeight     投资权重,由高到低，一档是最看好的，五档是不看好的
     *                         e.g{0.4,0.2,0.2,0.1,0.1}
     * @param interval         交易间隔
     */
    public void setPara_Factor(double capital, double taxRate,
                              String baseCode , MyDate start , MyDate end,
                               List<String> stocks,  Map  <AnalysisFactor,Double>  weightedFactors,
                               double [] investWeight,int interval){
        super.setPara(capital,taxRate,baseCode,start,end);

        this.stocks = stocks;
        this.weightedFactors=weightedFactors;
        this.investWeight=investWeight;
        this.interval=interval;

        this.vol=stocks.size();
        this.lots=new int [vol];
        this.buy_Prices=new double [vol];
        this.sell_Prices=new double [vol];
    }





    @Override
    public void init() {

        curTradeDay=validDates[0];

        buyStocks();

    }

    @Override
    public void handleData() {

    }

    @Override
    public ReportVO analyse() {
        return null;
    }


    @Override
    protected void buyStocks() {

        this.curFactorEntities = new ArrayList<>();



    }

    @Override
    protected void sellStocks() {

    }


    /**
     *
     * @param factorEntities  交易日当天的股票池中各个股票的因子数据
     * @return  各个股票及其综合因子
     */
    private Map<String,Double>  computeValidFactors(List<FactorEntity> factorEntities){
        Map<String,Double>  resultMap = new HashMap<String,Double>();
        double [] PEs = new double[0];   double avg_pe=0; double svar_pe=0;
        double [] PBs= new double[0];    double avg_pb=0; double svar_pb=0;
        double [] PSs= new double[0];    double avg_ps=0; double svar_ps=0;
        double [] PCFs= new double[0];   double avg_pcf=0;double svar_pcf=0;
        double [] VOL5s= new double[0];  double avg_vol5=0;double svar_vol5=0;
        double [] VOL10s= new double[0]; double avg_vol10=0;double svar_vol10=0;
        double [] VOL60s= new double[0]; double avg_vol60=0;double svar_vol60=0;
        double [] VOL120s= new double[0];double avg_vol120=0;double svar_vol120=0;
        /**
         * 遍历factorEntities对各个数组进行赋值
        */
        for(int i = 0; i< factorEntities.size(); i++){

            for(Map.Entry<AnalysisFactor,Double>
                    entry: weightedFactors.entrySet()){
                switch(entry.getKey()){
                    case PE:
                        if(i==0){
                            PEs = new double[factorEntities.size()];
                        }

                        PEs[i]= factorEntities.get(i).getPe();
                        break;

                    case PB:
                        if(i==0){
                            PBs = new double[factorEntities.size()];
                        }

                        PBs[i]= factorEntities.get(i).getPb();
                        break;

                    case PS:
                        if(i==0){
                            PSs = new double[factorEntities.size()];
                        }

                        PSs[i]= factorEntities.get(i).getPs();
                        break;

                    case PCF:
                        if(i==0){
                            PCFs = new double[factorEntities.size()];
                        }

                        PCFs[i]= factorEntities.get(i).getPcf();
                        break;

                    case VOL5:
                        if(i==0){
                            VOL5s = new double[factorEntities.size()];
                        }

                        VOL5s[i]= factorEntities.get(i).getVol5();

                        break;
                    case VOL10:
                        if(i==0){
                            VOL10s = new double[factorEntities.size()];
                        }

                        VOL10s[i]= factorEntities.get(i).getVol10();

                        break;
                    case VOL60:
                        if(i==0){
                            VOL60s = new double[factorEntities.size()];
                        }

                        VOL60s[i]= factorEntities.get(i).getVol60();

                        break;
                    case VOL120:
                        if(i==0){
                            VOL120s = new double[factorEntities.size()];
                        }

                        VOL120s[i]= factorEntities.get(i).getVol120();
                        break;

                    default:
                        break;
                }
            }

        }


        /**
        *计算各类因子的平均值和标准差
        */
        for(Map.Entry<AnalysisFactor,Double>
                entry: weightedFactors.entrySet()){

            switch(entry.getKey()){

                case PE:
                    avg_pe= MathHelper.computeAverage(PEs);
                    svar_pe=MathHelper.computeStandardVar(PEs);
                    break;
                case PB:
                    avg_pb= MathHelper.computeAverage(PBs);
                    svar_pb=MathHelper.computeStandardVar(PBs);
                    break;

                case PS:
                    avg_ps= MathHelper.computeAverage(PSs);
                    svar_ps=MathHelper.computeStandardVar(PSs);
                    break;
                case PCF:
                    avg_pcf= MathHelper.computeAverage(PCFs);
                    svar_pcf=MathHelper.computeStandardVar(PCFs);
                    break;
                case VOL5:
                    avg_vol5= MathHelper.computeAverage(VOL5s);
                    svar_vol5=MathHelper.computeStandardVar(VOL5s);
                    break;
                case VOL10:
                    avg_vol10= MathHelper.computeAverage(VOL10s);
                    svar_vol10=MathHelper.computeStandardVar(VOL10s);
                    break;
                case VOL60:
                    avg_vol60= MathHelper.computeAverage(VOL60s);
                    svar_vol60=MathHelper.computeStandardVar(VOL60s);
                    break;
                case VOL120:
                    avg_vol120= MathHelper.computeAverage(VOL120s);
                    svar_vol120=MathHelper.computeStandardVar(VOL120s);
                    break;
                default:
                    break;

            }

        }

        double  st_pe=0,st_pb=0,st_ps=0,st_pcf=0;
        double st_vol5=0,st_vol10=0,st_vol60=0,st_vol120=0;
        double final_Factor=0;

        /**
         * 遍历每只股票信息，遍历所有因子，计算无量纲化的因子值，累加成finalFactor并添加到map中
         */
        for(int i=0; i<factorEntities.size();i++){
            for(Map.Entry<AnalysisFactor,Double>
                    entry: weightedFactors.entrySet()){

                switch(entry.getKey()){

                    case PE:
                        st_pe=getStandardizedFactorValue(factorEntities.get(i).getPe(),
                               avg_pe,svar_pe);
                        st_pe=st_pe*entry.getValue();

                        final_Factor+=st_pe;

                        break;
                    case PB:
                        st_pb=getStandardizedFactorValue(factorEntities.get(i).getPb(),
                                avg_pb,svar_pb);
                        st_pb=st_pb*entry.getValue();

                        final_Factor+=st_pb;
                        break;

                    case PS:
                        st_ps=getStandardizedFactorValue(factorEntities.get(i).getPs(),
                                avg_ps,svar_ps);
                        st_ps=-1*st_ps*entry.getValue();
                        final_Factor+=st_ps;
                        break;
                    case PCF:
                        st_pcf=getStandardizedFactorValue(factorEntities.get(i).getPcf(),
                                avg_pcf,svar_pcf);
                        st_pcf=-1*avg_pcf*entry.getValue();
                        final_Factor+=st_pcf;
                        break;
                    case VOL5:
                        st_vol5=getStandardizedFactorValue(factorEntities.get(i).getVol5(),
                                avg_vol5,svar_vol5);
                        st_vol5=avg_vol5*entry.getValue();
                        final_Factor+=st_vol5;
                        break;
                    case VOL10:
                        st_vol10=getStandardizedFactorValue(factorEntities.get(i).getVol10(),
                                avg_vol10,svar_vol10);
                        st_vol10=st_vol10*entry.getValue();
                        final_Factor+=st_vol10;
                        break;
                    case VOL60:
                        st_vol60=getStandardizedFactorValue(factorEntities.get(i).getVol60(),
                                avg_vol60,svar_vol60);
                        st_vol60=st_vol60*entry.getValue();
                        final_Factor+=st_vol60;
                        break;
                    case VOL120:
                        st_vol120=getStandardizedFactorValue(factorEntities.get(i).getVol120(),
                                avg_vol120,svar_vol120);
                        st_vol120=st_vol120*entry.getValue();
                        final_Factor+=st_vol120;
                        break;
                    default:
                        break;

                }
            }

            resultMap.put(factorEntities.get(i).getCode(),final_Factor);
        }





        return new HashMap<>();
    }


    private List<Map.Entry<String,Double>> sortMap(Map<String,Double> map){
        List<Map.Entry<String,Double>> result = new ArrayList<>(map.entrySet());
        result.sort( (c1 ,c2) -> Double.compare(c1.getValue() , c2.getValue()));
        return  result;
    }

    private double getStandardizedFactorValue(double target, double avg , double var){
        double result=0;
        /**
         * x'= (x-u)/v  标准化为正态分布
         */
        result = (target-avg)/var;
        return result;
    }
}