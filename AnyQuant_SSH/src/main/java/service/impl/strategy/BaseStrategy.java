package service.impl.strategy;

import DAO.StockDataDAO;
import org.springframework.beans.factory.annotation.Autowired;
import util.MyDate;
import vo.CumRtnVO;
import vo.ReportVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 67534 on 2016/5/25.
 */
public abstract class BaseStrategy  {

    @Autowired
    StockDataDAO stockDataDAO;

    /**
     * 起始资金
     */
    public double capital;
    /**
     * 交易费率
     */
    public double taxRate;

    /**
     * 基准大盘代码
     */
    public String baseCode;

    /**
     * 回测周期开始日期
     */
    public MyDate start;

    /**
     * 回测周期结束日期
     */
    public MyDate end;




    /**
     * 卖出股票累积收益
     */
    public double income;
    /**
     * 买入股票的累积花费
     */
    public double expense;
    /**
     * 累积纯利润
     */
    public double profit;
    /**
     * 累积收益率(回测)
     */
    public double cumRtnRate;
    /**
     * 基线的累积收益率
     */
    public double baseRtnRate;


    /**
     * 每次调仓时的累积收益率
     */
    public List<CumRtnVO> cumRtnVOList;
    /**
     * 存储start--end间的交易日
     */


    public MyDate[] validDates;



    public BaseStrategy(){

    }

    public void initBaseStrategy(double capital,double taxRate,String baseCode ,
     MyDate start , MyDate end){

        this.capital=capital;
        this.taxRate=taxRate;
        this.baseCode=baseCode;
        this.start=start;
        this.end=end;

        this.income=0;
        this.expense=0;
        this.profit=0;
        this.cumRtnRate=0;
        this.baseRtnRate=0;
        this.cumRtnVOList=new ArrayList<>();

        this.computeValidDates();

    }


    public void computeValidDates(){

        List<MyDate> tempDates=stockDataDAO.getTradeDates(start,end);
        this.validDates = tempDates.toArray(new MyDate[tempDates.size()]);
        this.start=validDates[0];
        this.end=validDates[validDates.length-1];
        tempDates=null;
    }
    /**
     * 初始化算法
     */
    public abstract  void init();

    /**
     * 如果是设定了调仓频率的策略，该方法即调仓时的动作
     * 如果是无需设定调仓频率的策略，该方法即算法主体
     */
    public abstract  void handleData();

    /**
     * 面向ui的接口
     * @return
     */
    public abstract ReportVO analyse();

}
