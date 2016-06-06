package vo;

import util.MyDate;

import java.util.List;

/**
 * 记录每一次交易
 * @author Qiang
 * @date 6/6/16
 */
public class TradeDataVO {


    public MyDate tradeDate;
    /**
     * 这次交易中具体的每条交易
     */
    public List<TradeDetailVO> tradeDetailVOs;
    /**
     * 本次交易收益
     */
    public double profit;
    /**
     * 当前总股本
     */
    public double nowCapital;


}
